import 'package:flutter/material.dart';

class Responsive {
  static const double mobile = 320;
  static const double tablet = 768;
  static const double desktop = 1024;

  static double screenWidth(BuildContext context) => MediaQuery.of(context).size.width;
  static double screenHeight(BuildContext context) => MediaQuery.of(context).size.height;

  static bool isMobile(BuildContext context) => screenWidth(context) <= tablet;
  static bool isTablet(BuildContext context) => screenWidth(context) > tablet && screenWidth(context) <= desktop;
  static bool isDesktop(BuildContext context) => screenWidth(context) > desktop;
}
