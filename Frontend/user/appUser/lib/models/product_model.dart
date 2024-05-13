class Product {
  final int article_id;
  final String name;
  final String description;
  final String image;
  final double price;
  final int categorie_id;
  final double rate;
  int quantity;

  Product({
    required this.article_id,
    required this.name,
    required this.description,
    required this.image,
    required this.price,
    required this.categorie_id,
    required this.rate,
    required this.quantity,
  });

  factory Product.fromJson(Map<String, dynamic> json) {
    return Product(
      article_id: json['article_id'] as int,
      name: json['name'] as String,
      description: json['description'] as String,
      image: json['image'] as String,
      price: (json['price'] as num).toDouble(),
      categorie_id: json['categorie_id'] as int,
      rate: (json['rate'] as num).toDouble(),
      quantity: json['quantity'] as int,
    );
  }
}

final List<Product> electronics = [];
final List<Product> shoes = [];
final List<Product> beauty = [];
final List<Product> womenFashion = [];
final List<Product> jewelry = [];
final List<Product> menFashion = [];

void addProduct(Product product) {
  switch (product.categorie_id) {
    case 1: // Assuming '1' is the ID for electronics
      electronics.add(product);
      break;
    case 2: // Assuming '2' is the ID for shoes
      shoes.add(product);
      break;
    case 3: // Assuming '3' is the ID for beauty
      beauty.add(product);
      break;
    case 4: // Assuming '4' is the ID for women's fashion
      womenFashion.add(product);
      break;
    case 5: // Assuming '5' is the ID for jewelry
      jewelry.add(product);
      break;
    case 6: // Assuming '6' is the ID for men's fashion
      menFashion.add(product);
      break;
    default:
      // Handle unknown category
      break;
  }
}

// Example products


// You can add more sample products to test the categorization.
