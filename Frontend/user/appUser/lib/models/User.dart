class User {
  final int user_id;
  final String nom;
  final String prenom;
  final String email;
  final String adresse;
  final String telephone;

  User(
    this.user_id,
    this.nom,
    this.prenom,
    this.email,
    this.adresse,
    this.telephone,
  );

  // Méthode factory pour convertir JSON en User
  factory User.fromJson(Map<String, dynamic> json) {
    return User(
      json['user_id'],
      json['nom'],
      json['prenom'],
      json['email'],
      json['adresse'],
      json['telephone'],
    );
  }

  // Méthode pour convertir User en JSON
  Map<String, dynamic> toJson() {
    return {
      'user_id': user_id,
      'nom': nom,
      'prenom': prenom,
      'email': email,
      'adresse': adresse,
      'telephone': telephone,
    };
  }
}
