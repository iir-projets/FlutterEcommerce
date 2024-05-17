import 'dart:async';
import 'dart:math';
import 'package:flutter/material.dart';
import 'package:ecommerce_mobile_app/Fuctions/Functions.dart'; // Assurez-vous que le chemin est correct
import 'package:ecommerce_mobile_app/screens/Login/sendCode.dart';
import 'package:http/http.dart' as http;

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

class PwdOublie extends StatefulWidget {
  @override
  _PwdOublieState createState() => _PwdOublieState();
}

class _PwdOublieState extends State<PwdOublie> {
  final TextEditingController emailController = TextEditingController();
  static const Color kprimaryColor = Color.fromARGB(197, 155, 110, 110);
  bool _loading = false;

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
                  onPressed: _loading ? null : () => _sendCode(),
                  child: _loading
                      ? CircularProgressIndicator(
                          valueColor:
                              AlwaysStoppedAnimation<Color>(Colors.white),
                        )
                      : Text(
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

  void _sendCode() async {
    setState(() {
      _loading = true;
    });

    String emailAdresse = emailController.text;
    int randomNumber = generateRandomNumber();
    print(randomNumber);

    // Envoi de l'email
    bool emailSent = await sendEmail(
        emailAdresse,
        'Code de reinitialisation de mot de passe',
        'votre code de verification est :' + randomNumber.toString());

    if (emailSent) {
      // Afficher une boîte de dialogue ou effectuer toute autre action en cas de succès
      print('Email envoyé avec succès');

      // Attendre 5 secondes
      await Future.delayed(Duration(seconds: 5));

      // Rediriger vers sendCode.dart
      Navigator.push(
        context,
        MaterialPageRoute(
          builder: (context) =>
              PhoneNumberVerificationScreen(), // Assurez-vous que cette ligne est correcte
        ),
      );
    } else {
      // Afficher une boîte de dialogue ou effectuer toute autre action en cas d'échec
      print('Échec de l\'envoi de l\'email');
    }

    setState(() {
      _loading = false;
    });
  }

  int generateRandomNumber() {
    return 1000 + Random().nextInt(9000);
  }
}
