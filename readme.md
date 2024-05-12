# E-commerce Boutique en Ligne Flutter app

## Description
Cette application de boutique en ligne permet aux entreprises de vendre leurs produits directement aux consommateurs via Internet. Les fonctionnalités incluent la navigation dans le catalogue de produits, l'ajout au panier, un paiement en ligne sécurisé, et la gestion des commandes.

## Fonctionnalités Principales
- **Gestion du Compte:** Créez un compte, connectez-vous, ou récupérez votre mot de passe.
- **Catalogue de Produits:** Parcourez les produits par catégorie, recherchez des articles spécifiques et visualisez leurs détails.
- **Panier d'Achat:** Ajoutez des produits au panier, mettez à jour les quantités et passez à la caisse.
- **Paiement Sécurisé:** Effectuez un paiement en ligne sécurisé pour finaliser la commande.
- **Suivi de Commande:** Consultez l'historique de commandes et suivez l'état d'avancement des livraisons.

## Configuration et Installation
1. **Clonez le Repository**
    - Clonez le projet en utilisant la commande :
    ```bash
    git clone https://github.com/Safaamigo/FlutterEcommerce.git
    ```
2. **Installez les Dépendances**
    - Accédez au répertoire du projet et installez les dépendances requises :
    ```bash
    cd ecommerce-boutique
    flutter pub get
    ```
3. **Exécution Locale**
    - Lancez l'application localement en exécutant la commande suivante :
    ```bash
    flutter run
    ```

## Backend avec Spring Boot

### Configuration
- Assurez-vous que Java et Maven sont installés sur votre système.
- Configurez les variables d'environnement nécessaires, telles que les informations de connexion à la base de données.

### Exécution Locale
1. **Accédez au répertoire du Backend**
    - Ouvrez une fenêtre de terminal et naviguez jusqu'au répertoire du backend :
    ```bash
    cd chemin/vers/backend
    ```

2. **Compilation et Dépendances**
    - Compilez le projet et téléchargez les dépendances nécessaires avec Maven :
    ```bash
    mvn clean install
    ```

3. **Lancer l'Application**
    - Exécutez l'application en utilisant Maven :
    ```bash
    mvn spring-boot:run
    ```

Cela démarrera le serveur backend sur le port par défaut, généralement `8080`, à moins qu'un autre port ne soit spécifié dans les propriétés de configuration de Spring Boot. Vous pouvez alors accéder aux endpoints API via `http://localhost:8080`.

### Notes Supplémentaires
- Pour changer le port ou d'autres paramètres de configuration, modifiez le fichier `application.properties` ou `application.yml` dans le répertoire `src/main/resources`.
- Assurez-vous que les services externes, comme la base de données, sont accessibles et configurés correctement.

## Contribution
Les contributions sont les bienvenues ! Merci de soumettre vos pull requests sur une branche `feature/` ou `bugfix/` en suivant les conventions de branche et de commit. Assurez-vous que vos modifications passent tous les tests unitaires.
