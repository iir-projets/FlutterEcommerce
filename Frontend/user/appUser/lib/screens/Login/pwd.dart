import 'dart:async';
import 'dart:math';
import 'package:flutter/material.dart';
import 'package:ecommerce_mobile_app/Fuctions/Functions.dart';
import 'package:ecommerce_mobile_app/screens/Login/sendCode.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: PwdOublie(),
      theme: ThemeData(
        inputDecorationTheme: InputDecorationTheme(
          border: OutlineInputBorder(
            borderRadius: BorderRadius.circular(15.0),
          ),
        ),
      ),
    );
  }
}

class PwdOublie extends StatelessWidget {
  final TextEditingController emailController = TextEditingController();
  static const Color kprimaryColor = Color.fromARGB(197, 155, 110, 110);
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: SafeArea(
        child: Center(
          child: SingleChildScrollView(
            padding: const EdgeInsets.all(20.0),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Text(
                  'MOT DE PASSE OUBLIÉ',
                  style: TextStyle(
                      fontSize: 24,
                      fontWeight: FontWeight.bold,
                      color: Colors.black),
                ),
                SizedBox(height: 30),
                TextField(
                  controller: emailController,
                  keyboardType: TextInputType.emailAddress,
                  decoration: InputDecoration(
                    labelText: 'adresse email',
                    prefixIcon: Icon(Icons.email),
                  ),
                ),
                SizedBox(height: 30),
                ElevatedButton(
                  style: ElevatedButton.styleFrom(
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(18.0),
                    ),
                    backgroundColor: kprimaryColor,
                    padding: EdgeInsets.symmetric(vertical: 15, horizontal: 50),
                  ),
                  onPressed: () async {
                    String emailAdresse = emailController.text;
                    int randomNumber = generateRandomNumber();
                    print(randomNumber);

                    // Afficher le chargement pendant 5 secondes
                    showDialog(
                      context: context,
                      barrierDismissible: false,
                      builder: (BuildContext context) {
                        return Center(
                          child: CircularProgressIndicator(),
                        );
                      },
                    );

                    // Attendez 5 secondes avant de rediriger
                    await Future.delayed(Duration(seconds: 5));

                    // Supprimer la boîte de dialogue de chargement
                    Navigator.of(context).pop();

                    // Rediriger vers PhoneNumberVerificationScreen
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => PhoneNumberVerificationScreen(),
                      ),
                    );
                  },
                  child: Text(
                    'ENVOYÉ CODE',
                    style: TextStyle(color: Colors.white, fontSize: 16),
                  ),
                ),
                SizedBox(height: 10),
              ],
            ),
          ),
        ),
      ),
    );
  }
}

int generateRandomNumber() {
  return 1000 + Random().nextInt(9000);
}
