package com.ecom.ecomshop.repository;

import com.ecom.ecomshop.model.Categorie;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {

	Optional<Categorie> findByCatNom(String categorieName);
}
