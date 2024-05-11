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

// nefs lhaja banisba l product model 
  static User fromJson(Map<String, dynamic> json) {
    return User(
      json['user_id'],
      json['nom'],
      json['prenom'],
      json['email'],
      json['adresse'],
      json['telephone'],
    );
  }
}
