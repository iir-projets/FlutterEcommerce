import 'package:flutter/material.dart';
import 'package:ecommerce_mobile_app/models/User.dart';
import 'package:ecommerce_mobile_app/Fuctions/Functions.dart';
import 'package:ecommerce_mobile_app/constants.dart';
import 'editProfile.dart'; // Importez la page PageEditProfil.dart
import '../Login/signIn.dart'; // Importez la page PageLogin.dart

class Profile extends StatefulWidget {
  const Profile({Key? key});

  @override
  _ProfileState createState() => _ProfileState();
}

class _ProfileState extends State<Profile> {
  late User currentUser;
  bool isLoading = true;

  @override
  void initState() {
    super.initState();
    fetchUserData();
  }

  void fetchUserData() async {
    try {
      currentUser = (await getUserDetails())!;
      print(
          "Data fetched: ${currentUser.toJson()}"); // Ensure toJson() is implemented in User model to log this
      setState(() {
        isLoading = false;
      });
    } catch (error) {
      print("Failed to fetch user details: $error");
    }
  }

  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    const kPrimaryColor = Color.fromARGB(197, 155, 110, 110);

    return Scaffold(
      body: isLoading
          ? Center(
              child: CircularProgressIndicator(),
            )
          : Stack(
              alignment: Alignment.bottomCenter, // Alignement au bas de la page
              children: [
                Container(
                  color: kPrimaryColor,
                  height: size.height,
                  width: size.width,
                ),
                Padding(
                  padding:
                      const EdgeInsets.symmetric(horizontal: 9, vertical: 20),
                  child: Card(
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(15),
                    ),
                    // Ajustez le padding pour déplacer le contenu vers le bas
                    child: SizedBox(
                      height: size.height * 0.5,
                      child: Column(
                        children: [
                          const SizedBox(
                              height: 20), // Ajout de l'espace avant l'image
                          Padding(
                            padding: const EdgeInsets.symmetric(horizontal: 15),
                            child: Column(
                              children: [
                                Row(
                                  mainAxisAlignment: MainAxisAlignment.center,
                                  children: [
                                    Stack(
                                      children: [
                                        const CircleAvatar(
                                          radius: 42,
                                          backgroundImage:
                                              AssetImage("images/profile3.png"),
                                        ),
                                        Positioned(
                                          bottom: 0,
                                          right: 0,
                                          child: Container(
                                            height: 25,
                                            width: 25,
                                            decoration: const BoxDecoration(
                                              shape: BoxShape.circle,
                                              color: Color.fromARGB(
                                                  255, 95, 225, 99),
                                            ),
                                            child: const Icon(
                                              Icons.check,
                                              color: Colors.white,
                                              size: 20,
                                            ),
                                          ),
                                        )
                                      ],
                                    ),
                                  ],
                                ),
                                const SizedBox(height: 10),
                                Column(
                                  mainAxisAlignment: MainAxisAlignment.center,
                                  crossAxisAlignment: CrossAxisAlignment.center,
                                  children: [
                                    Text(
                                      currentUser.nom +
                                          " " +
                                          currentUser
                                              .prenom, // Afficher le nom de l'utilisateur connecté
                                      style: const TextStyle(
                                        fontWeight: FontWeight.w800,
                                        fontSize: 35,
                                      ),
                                      textAlign: TextAlign.center,
                                    ),
                                    const Text(
                                      "Flutter Developer",
                                      style: TextStyle(
                                        fontWeight: FontWeight.w800,
                                        fontSize: 16,
                                        color: Colors.black45,
                                      ),
                                      textAlign: TextAlign.center,
                                    ),
                                  ],
                                ),
                                const SizedBox(height: 60),
                                Column(
                                  mainAxisAlignment: MainAxisAlignment.center,
                                  children: [
                                    ElevatedButton(
                                      onPressed: () {
                                        // Redirection vers la page de connexion
                                        Navigator.pushReplacement(
                                          context,
                                          MaterialPageRoute(
                                              builder: (context) =>
                                                  ModifyProfileScreen()),
                                        );
                                      },
                                      style: ElevatedButton.styleFrom(
                                        foregroundColor: kPrimaryColor,
                                        backgroundColor: Colors
                                            .white, // Texte avec la couleur kPrimaryColor
                                        shape: RoundedRectangleBorder(
                                          borderRadius:
                                              BorderRadius.circular(18.0),
                                        ), // Forme du bouton
                                      ),
                                      child: const Text("Editer"),
                                    ),
                                    const SizedBox(height: 10),
                                    ElevatedButton(
                                      onPressed: () {
                                        // Redirection vers la page de connexion
                                        Navigator.pushReplacement(
                                          context,
                                          MaterialPageRoute(
                                              builder: (context) =>
                                                  LoginPage()),
                                        );
                                      },
                                      style: ElevatedButton.styleFrom(
                                        foregroundColor: Colors.white,
                                        backgroundColor:
                                            kPrimaryColor, // Texte en blanc
                                        shape: RoundedRectangleBorder(
                                          borderRadius:
                                              BorderRadius.circular(18.0),
                                        ), // Forme du bouton
                                      ),
                                      child: const Text("Déconnexion"),
                                    ),
                                  ],
                                ),
                              ],
                            ),
                          ),
                          const Spacer(),
                          const Divider(
                            color: Colors.black12,
                          ),
                        ],
                      ),
                    ),
                  ),
                ),
              ],
            ),
    );
  }
}
