import 'dart:convert';
import 'dart:io';

import 'package:ecommerce_mobile_app/Fuctions/MySnackbar.dart';
import 'package:ecommerce_mobile_app/Fuctions/MyStorage.dart';
import 'package:ecommerce_mobile_app/models/User.dart';
import 'package:get/get.dart';
import 'package:http/http.dart' as http;
import 'package:connectivity/connectivity.dart';

var headers = {'Accept': 'application/json'};

// function bach dir login
Future<bool> Login(String email, String password) async {
  var body = {'email': email, 'password': password};
  try {
    if (await CheckInternet()) {
      // var response = await http.post(Uri.parse("Urlapi/connexion"),
      //     headers: {'Accept': 'application/json'}, body: body);
      // if (response.statusCode == 200 || response.statusCode == 201) {

      // star 18 19 20 ghadi tremplacihom b hada li lta7t"if (1 == 1) {"
      if (1 == 1) {
        MyStorage storage = Get.find();
        // var jsonResponse = jsonDecode(response.body) as Map<String, dynamic>;
        if (email == "email@gmail.com" && password == "12345678") {
          print("login seccessufly");
          //////////////////////////////////////////////////////////////////////////
          // fhad lblassa ghadi t9adi data ou diriha f variable samih user
          User user = User(1, "yahya", "bouibi", "email@gmail.com", "adress",
              "+212+548d5d69");
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
        MySnackbar.Warnning("Error de serveur");
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
