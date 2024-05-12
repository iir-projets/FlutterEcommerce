import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:ecommerce_mobile_app/Fuctions/Functions.dart';
import 'package:ecommerce_mobile_app/constants.dart';
import 'package:ecommerce_mobile_app/models/User.dart';
import 'package:ecommerce_mobile_app/screens/Loading.dart';

class ModifyProfileScreen extends StatefulWidget {
  @override
  _ModifyProfileScreenState createState() => _ModifyProfileScreenState();
}

class _ModifyProfileScreenState extends State<ModifyProfileScreen> {
  bool loadWait = false;
  GlobalKey<FormState> formstate = GlobalKey<FormState>();
  late TextEditingController nomController;
  late TextEditingController prenomController;
  late TextEditingController emailController;
  late TextEditingController telephoneController;
  late TextEditingController adresseController;
  late TextEditingController passwordController;

  @override
  void initState() {
    nomController = TextEditingController();
    prenomController = TextEditingController();
    emailController = TextEditingController();
    telephoneController = TextEditingController();
    adresseController = TextEditingController();
    passwordController = TextEditingController();
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    if (loadWait == true) {
      return Loading();
    } else {
      return buildEdit(context);
    }
  }

  waitingLoad() async {
    await Future.delayed(Duration(seconds: 5));
    setState(() {
      loadWait = false;
    });
  }

  Widget buildEdit(BuildContext context) {
    return Scaffold(
      backgroundColor: kcontentColor,
      appBar: AppBar(
        backgroundColor: Colors.transparent,
        elevation: 0,
        systemOverlayStyle: SystemUiOverlayStyle.dark,
      ),
      body: SafeArea(
        child: Center(
          child: SingleChildScrollView(
            padding: const EdgeInsets.all(20.0),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                FutureBuilder<User>(
                  future: getUserDetails(),
                  builder: (context, snapshot) {
                    if (snapshot.hasData) {
                      final user = snapshot.data!;
                      // Initialize controllers with user data
                      nomController.text = user.nom;
                      prenomController.text = user.prenom;
                      emailController.text = user.email;
                      telephoneController.text = user.telephone;
                      adresseController.text = user.adresse;
                      return Form(
                        key: formstate,
                        child: Column(
                          children: [
                            Stack(
                              alignment: Alignment.center,
                              children: [
                                CircleAvatar(
                                  radius: 50,
                                  backgroundColor: kprimaryColor,
                                  child: Text('Profile',
                                      style: TextStyle(
                                          fontSize: 20, color: Colors.white)),
                                ),
                              ],
                            ),
                            SizedBox(height: 30),
                            TextFormField(
                              controller: nomController,
                              decoration: InputDecoration(
                                labelText: 'Nom',
                                prefixIcon: Icon(Icons.person),
                              ),
                              validator: (value) {
                                if (value!.isEmpty) {
                                  return 'Veuillez entrer votre nom';
                                }
                                return null;
                              },
                              onSaved: (value) {
                                user.nom = value!;
                              },
                            ),
                            SizedBox(height: 15),
                            TextFormField(
                              controller: prenomController,
                              decoration: InputDecoration(
                                labelText: 'Prénom',
                                prefixIcon: Icon(Icons.person),
                              ),
                              validator: (value) {
                                if (value!.isEmpty) {
                                  return 'Veuillez entrer votre prénom';
                                }
                                return null;
                              },
                              onSaved: (value) {
                                user.prenom = value!;
                              },
                            ),
                            SizedBox(height: 15),
                            TextFormField(
                              controller: emailController,
                              decoration: InputDecoration(
                                labelText: 'Email',
                                prefixIcon: Icon(Icons.email),
                              ),
                              validator: (value) {
                                if (value!.isEmpty) {
                                  return 'Veuillez entrer votre adresse email';
                                } else if (!RegExp(
                                        r'^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$')
                                    .hasMatch(value)) {
                                  return 'Veuillez entrer une adresse email valide';
                                }
                                return null;
                              },
                              onSaved: (value) {
                                user.email = value!;
                              },
                            ),
                            SizedBox(height: 15),
                            TextFormField(
                              controller: telephoneController,
                              decoration: InputDecoration(
                                labelText: 'Téléphone',
                                prefixIcon: Icon(Icons.phone),
                              ),
                              validator: (value) {
                                if (value!.isEmpty) {
                                  return 'Veuillez entrer votre numéro de téléphone';
                                }
                                return null;
                              },
                              onSaved: (value) {
                                user.telephone = value!;
                              },
                            ),
                            SizedBox(height: 15),
                            TextFormField(
                              controller: adresseController,
                              decoration: InputDecoration(
                                labelText: 'Adresse',
                                prefixIcon: Icon(Icons.location_on),
                              ),
                              validator: (value) {
                                if (value!.isEmpty) {
                                  return 'Veuillez entrer votre adresse';
                                }
                                return null;
                              },
                              onSaved: (value) {
                                user.adresse = value!;
                              },
                            ),
                            SizedBox(height: 30),
                            MaterialButton(
                              shape: RoundedRectangleBorder(
                                borderRadius: BorderRadius.circular(18.0),
                              ),
                              color: kprimaryColor,
                              padding: EdgeInsets.symmetric(
                                  vertical: 15, horizontal: 50),
                              child: Text('Update Profile',
                                  style: TextStyle(
                                      color: Colors.white, fontSize: 16)),
                              onPressed: () async {
                                if (formstate.currentState!.validate()) {
                                  formstate.currentState!.save();
                                  setState(() {
                                    loadWait = true;
                                  });
                                  await Future.delayed(Duration(seconds: 5));
                                  bool response = await editProfile(user);
                                  if (response) {
                                    // Show success message
                                    ScaffoldMessenger.of(context).showSnackBar(
                                      SnackBar(
                                        content: Text(
                                            'Profile updated successfully!'),
                                        backgroundColor: Colors.green,
                                      ),
                                    );
                                  } else {
                                    // Show error message
                                    ScaffoldMessenger.of(context).showSnackBar(
                                      SnackBar(
                                        content: Text(
                                            'Failed to update profile. Please try again.'),
                                        backgroundColor: Colors.red,
                                      ),
                                    );
                                  }
                                  setState(() {
                                    loadWait = false;
                                  });
                                }
                              },
                            ),
                          ],
                        ),
                      );
                    } else if (snapshot.hasError) {
                      return Text("Erreur: ${snapshot.error}");
                    }
                    return CircularProgressIndicator();
                  },
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
