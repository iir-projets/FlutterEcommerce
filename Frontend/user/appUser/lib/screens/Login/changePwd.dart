import 'package:flutter/material.dart';
import 'package:ecommerce_mobile_app/screens/Login/signIn.dart'; // Assurez-vous que le chemin d'accès est correct

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: ChangePwd(),
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

class ChangePwd extends StatefulWidget {
  @override
  _ChangePwdState createState() => _ChangePwdState();
}

class _ChangePwdState extends State<ChangePwd> {
  final TextEditingController passwordController = TextEditingController();
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
                  'REINITIALISE LE MOT DE PASSE OUBLIÉ',
                  style: TextStyle(
                    fontSize: 24,
                    fontWeight: FontWeight.bold,
                    color: Colors.black,
                  ),
                ),
                SizedBox(height: 30),
                TextField(
                  controller: passwordController,
                  keyboardType: TextInputType.visiblePassword,
                  decoration: InputDecoration(
                    labelText: 'Mot de passe',
                    prefixIcon: Icon(Icons.password),
                  ),
                ),
                SizedBox(height: 30),
                TextField(
                  controller: passwordController,
                  keyboardType: TextInputType.visiblePassword,
                  decoration: InputDecoration(
                    labelText: 'Re-saisir le mot de passe',
                    prefixIcon: Icon(Icons.password),
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
                  onPressed: () {
                    // Afficher l'indicateur de chargement
                    setState(() {
                      _loading = true;
                    });

                    // Attendre 5 secondes avant de naviguer vers la page de connexion
                    Future.delayed(Duration(seconds: 5), () {
                      setState(() {
                        _loading = false;
                      });
                      Navigator.pushReplacement(
                        context,
                        MaterialPageRoute(builder: (context) => LoginPage()),
                      );
                    });
                  },
                  child: _loading
                      ? CircularProgressIndicator(
                          valueColor:
                              AlwaysStoppedAnimation<Color>(Colors.white),
                        )
                      : Text('VALIDER', style: TextStyle(color: Colors.white)),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
