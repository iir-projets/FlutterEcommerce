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
      await prefs!.setInt("id", user.id);
      await prefs!.setString("email", user.email);
      await prefs!.setString("name", user.name);
      await prefs!.setString("prenom", user.prenom);
      await prefs!.setString("phone", user.Phone);
      await prefs!.setString("adress", user.adress);
      print("data user is saved");
    } catch (e) {
      print("error:$e");
    }
  }

  getUser() {
    try {
      int id = prefs!.getInt("id")!;
      String name = prefs!.getString("name")!;
      String prenom = prefs!.getString("prenom")!;
      String email = prefs!.getString("email")!;
      String phone = prefs!.getString("phone")!;
      String adress = prefs!.getString("adress")!;

      User user = User(id, name, prenom, email, adress, phone);

      return user;
    } catch (e) {
      print("error for geting data user from storage: $e");
      return null;
    }
  }

  Future<bool?> delete() async {
    try {
      await prefs!.remove("id");
      await prefs!.remove("name");
      await prefs!.remove("prenom");
      await prefs!.remove("email");
      await prefs!.remove("phone");
      await prefs!.remove("adress");
      print("data user is deteled");
      return true;
    } catch (e) {
      print("error:$e");
      return null;
    }
  }
}
