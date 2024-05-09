-- MySQL Script to create the database and tables

-- Optional: Create or use an existing database
CREATE DATABASE IF NOT EXISTS ecom;
USE ecom;

-- Create the `user` table
CREATE TABLE `user` (
    UserId INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    prenom VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telephone VARCHAR(255),
    address VARCHAR(255),
    password VARCHAR(255) NOT NULL
);

-- Create the `categorie` table
CREATE TABLE `categorie` (
    catId INT AUTO_INCREMENT PRIMARY KEY,
    catNom VARCHAR(255) NOT NULL
);

-- Create the `article` table
CREATE TABLE `article` (
    articleId INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    desc TEXT,
    price DOUBLE NOT NULL,
    image BLOB
);

-- Create the `transaction` table
CREATE TABLE `transaction` (
    transactionId INT AUTO_INCREMENT PRIMARY KEY,
    transactionDate DATE NOT NULL,
    transactionStatus VARCHAR(255) NOT NULL,
    UserId INT,
    FOREIGN KEY (UserId) REFERENCES user(UserId)
);

-- Create the `detail-commande` table
CREATE TABLE `detail-commande` (
    detailId INT AUTO_INCREMENT PRIMARY KEY,
    articleId INT NOT NULL,
    transactionId INT NOT NULL,
    Qty INT NOT NULL,
    FOREIGN KEY (articleId) REFERENCES article(articleId),
    FOREIGN KEY (transactionId) REFERENCES transaction(transactionId)
);
