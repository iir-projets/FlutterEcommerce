import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:ecommerce_mobile_app/constants.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: ModifyProfileScreen(),
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

class ModifyProfileScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: kcontentColor,
      appBar: AppBar(
        backgroundColor: Colors.transparent,
        elevation: 0,
        systemOverlayStyle:
            SystemUiOverlayStyle.dark, // For light status bar icons
      ),
      body: SafeArea(
        child: Center(
          child: SingleChildScrollView(
            padding: const EdgeInsets.all(20.0),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Stack(
                  alignment: Alignment.center,
                  children: [
                    CircleAvatar(
                      radius: 50,
                      backgroundColor: kprimaryColor,
                      child: Text('Profile',
                          style: TextStyle(
                              fontSize: 20,
                              color: Colors
                                  .white)), // Placeholder for profile image
                    ),
                    // Positioned(
                    //   right: 0,
                    //   bottom: 0,
                    //   child: IconButton(
                    //     icon: Icon(Icons.edit, color: priceColor),
                    //     onPressed: () {},
                    //   ),
                    // ),
                  ],
                ),
                SizedBox(height: 30),
                TextField(
                  decoration: InputDecoration(
                    labelText: 'Nom',
                    prefixIcon: Icon(Icons.person),
                  ),
                ),
                SizedBox(height: 15),
                TextField(
                  decoration: InputDecoration(
                    labelText: 'Prénom',
                    prefixIcon: Icon(Icons.person),
                  ),
                ),
                SizedBox(height: 15),
                TextField(
                  decoration: InputDecoration(
                    labelText: 'Email',
                    prefixIcon: Icon(Icons.email),
                  ),
                ),
                SizedBox(height: 15),
                TextField(
                  decoration: InputDecoration(
                    labelText: 'Phone',
                    prefixIcon: Icon(Icons.phone),
                  ),
                ),
                SizedBox(height: 15),
                TextField(
                  decoration: InputDecoration(
                    labelText: 'Adresse',
                    prefixIcon: Icon(Icons.location_on),
                  ),
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
                  onPressed: () {},
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
