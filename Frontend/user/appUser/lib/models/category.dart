class Category {
  final String title;
  final String image;

  Category({
    required this.title,
    required this.image,
  });
  factory Category.fromJson(Map<String, dynamic> json) {
    return Category(
      title: json['catNom'],
      image: 'images/${json['image']}',
    );
  }
}

final List<Category> categoriesList = [
  Category(
    title: "All",
    image: "images/all.png",
  ),
  Category(
    title: "parfum",
    image: "images/shoes.png",
  ),
  Category(
    title: "Beauty",
    image: "images/beauty.png",
  ),
  Category(
    title: "Women's\nFashion",
    image: "images/image1.png",
  ),
  Category(
    title: "Jewelry",
    image: "images/jewelry.png",
  ),
  Category(
    title: "Men's\nFashion",
    image: "images/men.png",
  ),
];
