// ignore_for_file: non_constant_identifier_names, file_names

import 'package:flutter/material.dart';
import 'package:get/get.dart';

Duration duration = const Duration(seconds: 5);
Duration animationduration = const Duration(milliseconds: 1800);

class MySnackbar {
  static Error(String msg) {
    Get.snackbar(
      "Error :",
      "",
      icon: const Icon(
        Icons.error,
        color: Colors.red,
      ),
      messageText: Text(
        msg,
        style: const TextStyle(
            color: Colors.red, fontSize: 15, fontWeight: FontWeight.w400),
        textAlign: TextAlign.center,
      ),
      // snackStyle: SnackStyle.FLOATING,
      snackPosition: SnackPosition.BOTTOM,
      backgroundColor: const Color(0xEEFDF7E5),
      duration: duration,
      animationDuration: animationduration,
      reverseAnimationCurve: const Cubic(0, 0, 0, 2),
      forwardAnimationCurve: const Cubic(0, 0, 0, 10),
      borderRadius: 10,
      margin: const EdgeInsets.symmetric(horizontal: 20, vertical: 10),
      colorText: Colors.red,
    );
  }

  static Warnning(String msg) {
    Get.snackbar(
      "Warnning :",
      "",
      icon: const Icon(
        Icons.warning,
        color: Colors.orange,
      ),
      messageText: Text(
        msg,
        style: const TextStyle(
            color: Colors.orange, fontSize: 15, fontWeight: FontWeight.w400),
        textAlign: TextAlign.center,
      ),
      // snackStyle: SnackStyle.FLOATING,
      snackPosition: SnackPosition.BOTTOM,
      backgroundColor: const Color(0xEEFDF7E5),
      duration: duration,
      animationDuration: animationduration,
      reverseAnimationCurve: const Cubic(0, 0, 0, 2),
      forwardAnimationCurve: const Cubic(0, 0, 0, 10),
      borderRadius: 10,
      margin: const EdgeInsets.symmetric(horizontal: 20, vertical: 10),
      colorText: Colors.orange,
    );
  }

  static Seccess(String msg) {
    Get.snackbar(
      "Seccess :",
      "",
      icon: const Icon(
        Icons.check,
        color: Colors.green,
      ),
      messageText: Text(
        msg,
        style: const TextStyle(
            color: Colors.green, fontSize: 15, fontWeight: FontWeight.w400),
        textAlign: TextAlign.center,
      ),
      // snackStyle: SnackStyle.FLOATING,
      snackPosition: SnackPosition.BOTTOM,
      backgroundColor: const Color(0xFFFDF7E5),
      duration: duration,
      animationDuration: animationduration,
      reverseAnimationCurve: const Cubic(0, 0, 0, 2),
      forwardAnimationCurve: const Cubic(0, 0, 0, 10),
      borderRadius: 10,
      margin: const EdgeInsets.symmetric(horizontal: 20, vertical: 10),
      colorText: Colors.green,
    );
  }
}
