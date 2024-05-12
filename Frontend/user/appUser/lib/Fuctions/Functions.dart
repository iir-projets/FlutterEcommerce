import 'dart:convert';
import 'dart:io';

import 'package:connectivity_plus/connectivity_plus.dart';
import 'package:ecommerce_mobile_app/Fuctions/MySnackbar.dart';
import 'package:ecommerce_mobile_app/Fuctions/MyStorage.dart';
import 'package:ecommerce_mobile_app/models/User.dart';
import 'package:get/get.dart';
import 'package:http/http.dart' as http;

var headers = {'Accept': 'application/json'};
String urlAPI = "http://192.168.56.1:8081/user";

/**************************************************** LOGIN START ****************************************************/
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
/**************************************************** LOGIN END ****************************************************/

//  function CheckInternet bach tchof wach kayna connexions
Future<bool> CheckInternet() async {
  var connectivityResult = await Connectivity().checkConnectivity();
  return connectivityResult != ConnectivityResult.none;
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

/**************************************************** REGISTER START ****************************************************/

// Register User :
// Fonction pour l'inscription d'un utilisateur
Future<bool> register(String nom, String prenom, String email, String telephone,
    String adresse, String password) async {
  var body = {
    "nom": nom,
    "prenom": prenom,
    "email": email,
    "telephone": telephone,
    "address": adresse,
    "password": password,
  };

  try {
    if (await CheckInternet()) {
      var response = await http.post(Uri.parse("$urlAPI/inscription"),
          headers: {
            'Content-Type': 'application/json', // Ajout du type de contenu
            'Accept': 'application/json'
          },
          body: jsonEncode(body)); // Encodage du corps de la requête en JSON

      print(response.body);

      if (response.statusCode == 200 ||
          response.statusCode == 201 ||
          response.statusCode == 202) {
        print("Inscription réussie");
        return true;
      } else {
        print(response.statusCode);
        print("Erreur lors de la requête");
        // MySnackbar.Warnning("Erreur du serveur");
        return false;
      }
    } else {
      MySnackbar.Warnning("Veuillez vérifier votre connexion");
      return false;
    }
  } catch (e) {
    print("Une erreur s'est produite: $e");
    MySnackbar.Warnning(
        "Un problème est survenu, veuillez réessayer plus tard");
    return false;
  }
}

/**************************************************** REGISTER END ****************************************************/

// Fonction pour récupérer les détails de l'utilisateur connecté
Future<User> getUserDetails() async {
  MyStorage storage = Get.find();
  return await storage.getUser();
}

/**************************************************** EDIT START ****************************************************/

// Fonction pour envoyer les modifications du profil de l'utilisateur
// Fonction pour envoyer les modifications du profil de l'utilisateur
Future<bool> editProfile(User user) async {
  var body = {
    "user_id": user.user_id.toString(),
    "nom": user.nom,
    "prenom": user.prenom,
    "email": user.email,
    "telephone": user.telephone,
    "address": user.adresse,
  };

  try {
    if (await CheckInternet()) {
      var response = await http.put(
        Uri.parse("$urlAPI/modifierUser/${user.user_id}"),
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
        },
        body: jsonEncode(body),
      );

      if (response.statusCode == 200) {
        print("Profil modifié avec succès");

        // Update local user data after successful server update
        MyStorage storage = Get.find();
        await storage.SaveUser(user);

        return true;
      } else {
        print("Erreur lors de la requête: ${response.statusCode}");
        return false;
      }
    } else {
      MySnackbar.Warnning("Veuillez vérifier votre connexion");
      return false;
    }
  } catch (e) {
    print("Une erreur s'est produite: $e");
    MySnackbar.Warnning("Un problème est survenu, veuillez réessayer plus tard");
    return false;
  }
}


/**************************************************** EDIT END ****************************************************/ 

// creer fct li katjib lik data dyal product mn database kifma derna flogin ghir howa maghan7tajoch storage 