// ignore_for_file: unused_local_variable, non_constant_identifier_names
import 'package:ecommerce_mobile_app/models/User.dart';
import 'package:get/get_state_manager/get_state_manager.dart';
import 'package:shared_preferences/shared_preferences.dart';

class MyStorage extends GetxService {
  late SharedPreferences? prefs;

  Future<MyStorage> init() async {
    prefs = await SharedPreferences.getInstance();
    return this;
  }

  Future SaveUser(User user) async {
    try {
      await prefs!.setInt("user_id", user.user_id);
      await prefs!.setString("email", user.email);
      await prefs!.setString("nom", user.nom);
      await prefs!.setString("prenom", user.prenom);
      await prefs!.setString("telephone", user.telephone);
      await prefs!.setString("adresse", user.adresse);
      print("data user is saved");
    } catch (e) {
      print("error:$e");
    }
  }

  getUser() {
    try {
      int user_id = prefs!.getInt("user_id")!;
      String nom = prefs!.getString("nom")!;
      String prenom = prefs!.getString("prenom")!;
      String email = prefs!.getString("email")!;
      String telephone = prefs!.getString("telephone")!;
      String adresse = prefs!.getString("adresse")!;

      User user = User(user_id, nom, prenom, email, adresse, telephone);

      return user;
    } catch (e) {
      print("error for geting data user from storage: $e");
      return null;
    }
  }

  Future<bool?> delete() async {
    try {
      await prefs!.remove("user_id");
      await prefs!.remove("nom");
      await prefs!.remove("prenom");
      await prefs!.remove("email");
      await prefs!.remove("telephone");
      await prefs!.remove("adresse");
      print("data user is deteled");
      return true;
    } catch (e) {
      print("error:$e");
      return null;
    }
  }
}
