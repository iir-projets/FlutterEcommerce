import 'package:ecommerce_mobile_app/Fuctions/Functions.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import '../nav_bar_screen.dart';
import './signUp.dart';
import './pwd.dart';
import '../../constants.dart';
import '../Home/home_screen.dart';
import 'dart:async';
import '../Loading.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: LoginPage(),
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

class LoginPage extends StatefulWidget {
  @override
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  bool loadWait = false;
  GlobalKey<FormState> formstate = GlobalKey<FormState>();
  late TextEditingController email;
  late TextEditingController password;

  @override
  void initState() {
    email = TextEditingController();
    password = TextEditingController();
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return loadWait == true ? Loading() : buildLoginForm();
  }

  waitingLoad() async {
    await Future.delayed(Duration(seconds: 3));
    // bhala kandiro refrech
    setState(() {
      print("Succeeessss");
      loadWait = false;
    });
  }

  Widget buildLoginForm() {
    return Scaffold(
      backgroundColor: kcontentColor,
      body: SafeArea(
        child: Center(
          child: SingleChildScrollView(
            // Added to make the screen scrollable when keyboard appears
            padding: const EdgeInsets.all(20.0),
            child: Form(
              key: formstate,
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: <Widget>[
                  Text(
                    'KLAYDO',
                    style: TextStyle(
                        fontSize: 24,
                        fontWeight: FontWeight.bold,
                        color: priceColor),
                  ),
                  SizedBox(height: 30),
                  TextFormField(
                    autovalidateMode: AutovalidateMode.onUserInteraction,
                    controller: email,
                    validator: (vall) {
                      return validateInput(vall!, 50, 6, "email");
                    },
                    decoration: InputDecoration(
                      labelText: 'Email',
                      prefixIcon: Icon(Icons.email),
                    ),
                  ),
                  SizedBox(height: 15),
                  TextFormField(
                    autovalidateMode: AutovalidateMode.onUserInteraction,
                    controller: password,
                    validator: (vall) {
                      return validateInput(vall!, 50, 8, "password");
                    },
                    obscureText: true,
                    decoration: InputDecoration(
                      labelText: 'Password',
                      prefixIcon: Icon(Icons.lock),
                    ),
                  ),
                  SizedBox(height: 30),
                  MaterialButton(
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(18.0),
                    ),
                    color: kprimaryColor,
                    padding: EdgeInsets.symmetric(vertical: 15, horizontal: 50),
                    child: Text('SE CONNECTER',
                        style: TextStyle(color: Colors.white, fontSize: 16)),
                    onPressed: () async {
                      var formdata = formstate.currentState;
                      if (formdata!.validate()) {
                        setState(() {
                          print("Loadiiiiing");
                          loadWait = true;
                        });
                        bool response = await Login(email.text, password.text);
                        setState(() {
                          response
                              ? (
                                  Navigator.push(
                                    context,
                                    MaterialPageRoute(
                                        builder: (context) => BottomNavBar()),
                                  ),
                                  loadWait = true
                                )
                              : loadWait = false;
                        });
                      }

                      // waitingLoad();
                      // Navigator.push(
                      //   context,
                      //   MaterialPageRoute(builder: (context) => BottomNavBar()),
                      // );
                    },
                  ),
                  SizedBox(height: 10), // Added spacing
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Text(
                        "Vous n'avez pas de compte ?",
                        style: TextStyle(color: Colors.black),
                      ),
                      TextButton(
                        onPressed: () {
                          Navigator.push(
                            context,
                            MaterialPageRoute(
                                builder: (context) => SignUpScreen()),
                          );
                        },
                        child: Text(
                          'S\'INSCRIRE',
                          style: TextStyle(
                            color: priceColor,
                            decoration: TextDecoration.underline,
                            decorationColor: kprimaryColor,
                          ),
                        ),
                      ),
                    ],
                  ),
                  SizedBox(height: 10), // Added spacing
                  TextButton(
                    // Added a link to reset password
                    onPressed: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(builder: (context) => PwdOublie()),
                      );
                    },
                    child: Text(
                      'Mot de passe oublié ?',
                      style: TextStyle(
                        color: priceColor,
                        decoration: TextDecoration.underline,
                        decorationColor: kprimaryColor,
                      ),
                    ),
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
