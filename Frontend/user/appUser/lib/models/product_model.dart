class Product {
  final int article_id;
  final String title;
  final String description;
  final String image;
  final double price;
  final String category;
  final double rate;
  int quantity;

  Product({
    required this.article_id,
    required this.title,
    required this.description,
    required this.image,
    required this.price,
    required this.category,
    required this.rate,
    required this.quantity,
  });

  factory Product.fromJson(Map<String, dynamic> json) {
    return Product(
      article_id: json['article_id'] as int,
      title: json['title'] as String,
      description: json['description'] as String,
      image: json['image'] as String,
      price: (json['price'] as num).toDouble(),
      category: json['category'] as String,
      rate: (json['rate'] as num).toDouble(),
      quantity: json['quantity'] as int,
    );
  }
}

final List<Product> all = [
  Product(
    article_id: 1,
    title: "Wireless Headphones",
    description:
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Donec massa sapien faucibus et molestie ac feugiat. In massa tempor nec feugiat nisl. Libero id faucibus nisl tincidunt.",
    image: "images/all/wireless.png",
    price: 120,
    category: "Electronics",
    rate: 4.8,
    quantity: 1,
  ),
];
final List<Product> shoes = [
  Product(
    article_id: 1,
    title: "Wireless Headphones",
    description:
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Donec massa sapien faucibus et molestie ac feugiat. In massa tempor nec feugiat nisl. Libero id faucibus nisl tincidunt.",
    image: "images/all/wireless.png",
    price: 120,
    category: "Electronics",
    rate: 4.8,
    quantity: 1,
  ),
];
final List<Product> beauty = [
  Product(
    article_id: 1,
    title: "Wireless Headphones",
    description:
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Donec massa sapien faucibus et molestie ac feugiat. In massa tempor nec feugiat nisl. Libero id faucibus nisl tincidunt.",
    image: "images/all/wireless.png",
    price: 120,
    category: "Electronics",
    rate: 4.8,
    quantity: 1,
  ),
];
final List<Product> womenFashion = [
  Product(
    article_id: 1,
    title: "Wireless Headphones",
    description:
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Donec massa sapien faucibus et molestie ac feugiat. In massa tempor nec feugiat nisl. Libero id faucibus nisl tincidunt.",
    image: "images/all/wireless.png",
    price: 120,
    category: "Electronics",
    rate: 4.8,
    quantity: 1,
  ),
];
final List<Product> jewelry = [
  Product(
    article_id: 1,
    title: "Wireless Headphones",
    description:
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Donec massa sapien faucibus et molestie ac feugiat. In massa tempor nec feugiat nisl. Libero id faucibus nisl tincidunt.",
    image: "images/all/wireless.png",
    price: 120,
    category: "Electronics",
    rate: 4.8,
    quantity: 1,
  ),
];
final List<Product> menFashion = [
  Product(
    article_id: 1,
    title: "Wireless Headphones",
    description:
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Donec massa sapien faucibus et molestie ac feugiat. In massa tempor nec feugiat nisl. Libero id faucibus nisl tincidunt.",
    image: "images/all/wireless.png",
    price: 120,
    category: "Electronics",
    rate: 4.8,
    quantity: 1,
  ),
];
