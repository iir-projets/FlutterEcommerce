import 'package:ecommerce_mobile_app/Fuctions/Functions.dart';
import 'package:ecommerce_mobile_app/models/product_model.dart';
import 'package:flutter/material.dart';
import 'package:ecommerce_mobile_app/screens/Home/Widget/product_cart.dart'; // Assurez-vous d'importer correctement les fichiers nécessaires
import 'package:ecommerce_mobile_app/screens/Home/Widget/search_bar.dart';
import 'package:ecommerce_mobile_app/responsive.dart';
import '../../models/category.dart';
import 'Widget/home_app_bar.dart';
import 'Widget/image_slider.dart';
import '../Loading.dart';
// import '../../services/product_service.dart'; // Importez le service ProductService où se trouve la fonction checkAllProducts

class HomeScreen extends StatefulWidget {
  const HomeScreen({Key? key}) : super(key: key);

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  int currentSlider = 0;
  int selectedIndex = 0;
  bool loadWait = true;

  @override
  void initState() {
    super.initState();
    Future.delayed(Duration(seconds: 3)).then((_) {
      setState(() {
        loadWait = false;
      });
      print('Waited for 5 seconds');
    });

    // Appel de la fonction pour tester
    checkAllProducts();
  }

  @override
  Widget build(BuildContext context) {
    List<List<Product>> selectcategories = [
      shoes,
      beauty,
      womenFashion,
      jewelry,
      menFashion
    ];

    return loadWait == true
        ? Loading()
        : Scaffold(
            backgroundColor: Colors.white,
            body: SingleChildScrollView(
              child: Padding(
                padding: EdgeInsets.all(
                    Responsive.screenWidth(context) < 600 ? 10 : 20),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    SizedBox(
                        height:
                            Responsive.screenWidth(context) < 600 ? 20 : 35),
                    const CustomAppBar(),
                    SizedBox(
                        height:
                            Responsive.screenWidth(context) < 600 ? 10 : 20),
                    const MySearchBAR(),
                    SizedBox(
                        height:
                            Responsive.screenWidth(context) < 600 ? 10 : 20),
                    ImageSlider(
                      currentSlide: currentSlider,
                      onChange: (value) {
                        setState(
                          () {
                            currentSlider = value;
                          },
                        );
                      },
                    ),
                    SizedBox(
                        height:
                            Responsive.screenWidth(context) < 600 ? 10 : 20),
                    categoryItems(),
                    SizedBox(
                        height:
                            Responsive.screenWidth(context) < 600 ? 10 : 20),
                    if (selectedIndex == 0)
                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: [
                          Text(
                            "Special For You",
                            style: TextStyle(
                              fontSize: Responsive.isDesktop(context) ? 25 : 18,
                              fontWeight: FontWeight.w800,
                            ),
                          ),
                          Text(
                            "See all",
                            style: TextStyle(
                              fontWeight: FontWeight.w500,
                              fontSize: Responsive.isDesktop(context) ? 16 : 12,
                              color: Colors.black54,
                            ),
                          ),
                        ],
                      ),
                    SizedBox(height: Responsive.isDesktop(context) ? 10 : 5),
                    GridView.builder(
                      padding: EdgeInsets.zero,
                      physics: const NeverScrollableScrollPhysics(),
                      shrinkWrap: true,
                      gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                          crossAxisCount: Responsive.isDesktop(context) ? 4 : 2,
                          childAspectRatio:
                              Responsive.isDesktop(context) ? 1 : 0.75,
                          crossAxisSpacing: 20,
                          mainAxisSpacing: 20),
                      itemCount: selectcategories[selectedIndex].length,
                      itemBuilder: (context, index) {
                        return ProductCard(
                          product: selectcategories[selectedIndex][index],
                        );
                      },
                    )
                  ],
                ),
              ),
            ),
          );
  }

  SizedBox categoryItems() {
    return SizedBox(
      height: 140,
      child: ListView.separated(
        scrollDirection: Axis.horizontal,
        itemCount: categoriesList.length,
        physics: const BouncingScrollPhysics(),
        separatorBuilder: (context, index) => const SizedBox(width: 30),
        itemBuilder: (context, index) {
          return GestureDetector(
            onTap: () {
              setState(() {
                selectedIndex = index;
              });
            },
            child: Container(
              padding: const EdgeInsets.all(8),
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(20),
                color: selectedIndex == index
                    ? Colors.grey[200]
                    : Colors.transparent,
              ),
              child: Column(
                children: [
                  Container(
                    height: 65,
                    width: 65,
                    decoration: BoxDecoration(
                      shape: BoxShape.circle,
                      image: DecorationImage(
                        image: AssetImage(categoriesList[index].image),
                        fit: BoxFit.cover,
                      ),
                    ),
                    padding: const EdgeInsets.only(bottom: 5),
                  ),
                  SizedBox(height: Responsive.isDesktop(context) ? 6 : 3),
                  Text(
                    categoriesList[index].title,
                    style: TextStyle(
                      fontSize: Responsive.isDesktop(context) ? 16 : 14,
                      fontWeight: FontWeight.bold,
                    ),
                  )
                ],
              ),
            ),
          );
        },
      ),
    );
  }
}
