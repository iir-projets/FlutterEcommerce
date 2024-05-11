import 'dart:convert';
import 'dart:io';

import 'package:connectivity_plus/connectivity_plus.dart';
import 'package:ecommerce_mobile_app/Fuctions/MySnackbar.dart';
import 'package:ecommerce_mobile_app/Fuctions/MyStorage.dart';
import 'package:ecommerce_mobile_app/models/User.dart';
import 'package:get/get.dart';
import 'package:http/http.dart' as http;

var headers = {'Accept': 'application/json'};
String urlAPI = "http://192.168.56.1:8081/etud";

// function bach dir login
Future<bool> Login(String email, String password) async {
  var body = {'email': email, 'password': password};
  try {
    if (await CheckInternet()) {
      var response = await http.post(Uri.parse("${urlAPI}/connexion"),
          headers: {'Accept': 'application/json'}, body: body);
      if (response.statusCode == 200 ||
          response.statusCode == 201 ||
          response.statusCode == 202) {
        // pour afficher data
        // print(response.body);
        print(response.body);
        print(
            "////////////////////////////////////////////////////////////////////////////////");
        MyStorage storage = Get.find();
        var jsonResponse = jsonDecode(response.body) as Map<String, dynamic>;
        if (jsonResponse["login"] == true) {
          print("login seccessufly");
          //////////////////////////////////////////////////////////////////////////
          // fhad lblassa ghadi t9adi data ou diriha f variable samih user
          User user = User.fromJson(jsonResponse["user"]);
          await storage.SaveUser(user);
          print("login seccess");
          //////////////////////////////////////////////////////////////////////////////
          return true;
        } else {
          MySnackbar.Warnning("Username or Password Invalid");
          print("Username or Password Invalid");

          return false;
        }
      } else {
        MySnackbar.Warnning("email ou Mot de passe incorrect");
        return false;
      }
    } else {
      MySnackbar.Warnning("check votre connexion");
      return false;
    }
  } catch (e) {
    print("you hane an error: $e");
    MySnackbar.Warnning("some problem try another time");
    return false;
  }
}

//  function CheckInternet bach tchof wach kayna connexions
Future<bool> CheckInternet() async {
  try {
    var connectivityResult = await Connectivity().checkConnectivity();
    final result = await InternetAddress.lookup('google.com');
    if ((connectivityResult == ConnectivityResult.mobile ||
            connectivityResult == ConnectivityResult.wifi) &&
        result.isNotEmpty &&
        result[0].rawAddress.isNotEmpty) {
      print("You have internet connectivity");
      return true;
    } else {
      print("You don't have internet connectivity");
      return false;
    }
  } catch (e) {
    print("Error checking internet connectivity: $e");
    return false;
  }
}

//pour initialiser storage
Future InitialStorage() async {
  await Get.putAsync(() => MyStorage().init());
}

// pour validation input
validateInput(String val, int max, int min, String type) {
  if (type == "username") {
    if (!GetUtils.isUsername(val)) {
      return "not valid username";
    }
  }
  if (type == "email") {
    if (!GetUtils.isEmail(val)) {
      return "not valid email";
    }
  }
  if (type == "phone") {
    if (!GetUtils.isPhoneNumber(val)) {
      return "not valid Phone";
    }
  }
  if (val.isEmpty) {
    return "can't be empty";
  }
  if (val.length < min) {
    return "can't be less than $min";
  }
  if (val.length > max) {
    return "can't be more than $max";
  }
  return null;
}


// creer fct li katjib lik data dyal product mn database kifma derna flogin ghir howa maghan7tajoch storage 