import 'package:flutter/material.dart';
import 'package:ecommerce_mobile_app/constants.dart';
import 'editProfile.dart'; // Importez la page PageEditProfil.dart
import '../Login/signIn.dart'; // Importez la page PageLogin.dart
import './editProfile.dart';

class Profile extends StatelessWidget {
  const Profile({super.key});

  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    return Scaffold(
      body: Stack(
        alignment: Alignment.bottomCenter, // Alignement au bas de la page
        children: [
          Container(
            color: kprimaryColor,
            height: size.height,
            width: size.width,
          ),
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 9, vertical: 20),
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
                                        color: Color.fromARGB(255, 95, 225, 99),
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
                          const Column(
                            mainAxisAlignment: MainAxisAlignment.center,
                            crossAxisAlignment: CrossAxisAlignment.center,
                            children: [
                              Text(
                                "Winnie Vasquez",
                                style: TextStyle(
                                  fontWeight: FontWeight.w800,
                                  fontSize: 35,
                                ),
                                textAlign: TextAlign.center,
                              ),
                              Text(
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
                                style: ButtonStyle(
                                  backgroundColor:
                                      MaterialStateProperty.all<Color>(
                                          Colors.white), // Fond blanc
                                  foregroundColor: MaterialStateProperty.all<
                                          Color>(
                                      kprimaryColor), // Texte avec la couleur kprimaryColor
                                  shape: MaterialStateProperty.all<
                                          RoundedRectangleBorder>(
                                      RoundedRectangleBorder(
                                    borderRadius: BorderRadius.circular(18.0),
                                  )), // Forme du bouton
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
                                        builder: (context) => LoginPage()),
                                  );
                                },
                                style: ButtonStyle(
                                  backgroundColor: MaterialStateProperty.all<
                                          Color>(
                                      kprimaryColor), // Fond avec la couleur kprimaryColor
                                  foregroundColor:
                                      MaterialStateProperty.all<Color>(
                                          Colors.white), // Texte en blanc
                                  shape: MaterialStateProperty.all<
                                          RoundedRectangleBorder>(
                                      RoundedRectangleBorder(
                                    borderRadius: BorderRadius.circular(18.0),
                                  )), // Forme du bouton
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
