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
  GlobalKey<FormState> formState = GlobalKey<FormState>();
  late TextEditingController nomController;
  late TextEditingController prenomController;
  late TextEditingController emailController;
  late TextEditingController telephoneController;
  late TextEditingController adresseController;
  late User currentUser;

  @override
  void initState() {
    super.initState();
    nomController = TextEditingController();
    prenomController = TextEditingController();
    emailController = TextEditingController();
    telephoneController = TextEditingController();
    adresseController = TextEditingController();
    fetchUserData();
  }

 void fetchUserData() async {
  setState(() => loadWait = true);
  try {
    currentUser = await getUserDetails();
    print("Data fetched: ${currentUser.toJson()}");  // Ensure toJson() is implemented in User model to log this
    setState(() {
      nomController.text = currentUser.nom;
      prenomController.text = currentUser.prenom;
      emailController.text = currentUser.email;
      telephoneController.text = currentUser.telephone;
      adresseController.text = currentUser.adresse;
    });
  } catch (error) {
    print("Failed to fetch user details: $error");
  } finally {
    setState(() => loadWait = false);
  }
}






  @override
  Widget build(BuildContext context) {
    if (loadWait) {
      return Loading();
    } else {
      return buildEditForm(context);
    }
  }

  Widget buildEditForm(BuildContext context) {
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
            child: Form(
              key: formState,
              child: Column(
                children: [
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
                      currentUser.nom = value!;
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
                      currentUser.prenom = value!;
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
                      currentUser.email = value!;
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
                      currentUser.telephone = value!;
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
                      currentUser.adresse = value!;
                    },
                  ),
                  SizedBox(height: 30),
                  MaterialButton(
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(18.0),
                    ),
                    color: kprimaryColor,
                    padding: EdgeInsets.symmetric(vertical: 15, horizontal: 50),
                    child: Text('Update Profile',
                        style: TextStyle(color: Colors.white, fontSize: 16)),
                    onPressed: () async {
                      if (formState.currentState!.validate()) {
                        formState.currentState!.save();
                        setState(() => loadWait = true);
                        bool response = await editProfile(currentUser);
                        if (response) {
                          fetchUserData();
                          ScaffoldMessenger.of(context).showSnackBar(
                            SnackBar(
                              content: Text(
                                  'Profile updated successfully!'),
                              backgroundColor: Colors.green,
                            ),
                          );
                        } else {
                          ScaffoldMessenger.of(context).showSnackBar(
                            SnackBar(
                              content: Text(
                                  'Failed to update profile. Please try again.'),
                              backgroundColor: Colors.red,
                            ),
                          );
                        }
                        setState(() => loadWait = false);
                      }
                    },
                  ),
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }
}
