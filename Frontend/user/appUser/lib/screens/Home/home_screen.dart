import 'dart:async';

import 'package:ecommerce_mobile_app/models/product_model.dart';
import 'package:ecommerce_mobile_app/screens/Home/Widget/product_cart.dart';
import 'package:ecommerce_mobile_app/screens/Home/Widget/search_bar.dart';
import 'package:ecommerce_mobile_app/responsive.dart';
import 'package:flutter/material.dart';

import '../../models/category.dart';
import 'Widget/home_app_bar.dart';
import 'Widget/image_slider.dart';
import '../Loading.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

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
  }

  @override
  Widget build(BuildContext context) {
    List<List<Product>> selectcategories = [
      all,
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
                padding: EdgeInsets.all(Responsive.isMobile(context) ? 10 : 20),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    SizedBox(height: Responsive.isMobile(context) ? 20 : 35),
                    // for custom appbar
                    const CustomAppBar(),
                    SizedBox(height: Responsive.isMobile(context) ? 10 : 20),
                    // for search bar
                    const MySearchBAR(),
                    SizedBox(height: Responsive.isMobile(context) ? 10 : 20),
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
                    SizedBox(height: Responsive.isMobile(context) ? 10 : 20),
                    // for category selection
                    categoryItems(),

                    SizedBox(height: Responsive.isMobile(context) ? 10 : 20),
                    if (selectedIndex == 0)
                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: [
                          Text(
                            "Special For You",
                            style: TextStyle(
                              fontSize: Responsive.isMobile(context) ? 18 : 25,
                              fontWeight: FontWeight.w800,
                            ),
                          ),
                          Text(
                            "See all",
                            style: TextStyle(
                              fontWeight: FontWeight.w500,
                              fontSize: Responsive.isMobile(context) ? 12 : 16,
                              color: Colors.black54,
                            ),
                          ),
                        ],
                      ),
                    // for shopping items
                    SizedBox(height: Responsive.isMobile(context) ? 5 : 10),
                    GridView.builder(
                      padding: EdgeInsets.zero,
                      physics: const NeverScrollableScrollPhysics(),
                      shrinkWrap: true,
                      gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                          crossAxisCount: Responsive.isMobile(context) ? 1 : 2,
                          childAspectRatio: Responsive.isMobile(context) ? 1.2 : 0.75,
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
        separatorBuilder: (context, index) =>
            const SizedBox(width: 30), // space between each element
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
                    padding:
                        const EdgeInsets.only(bottom: 5), // padding at the bottom
                  ),
                  SizedBox(height: Responsive.isMobile(context) ? 3 : 6),
                  Text(
                    categoriesList[index].title,
                    style: TextStyle(
                      fontSize: Responsive.isMobile(context) ? 14 : 16,
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
