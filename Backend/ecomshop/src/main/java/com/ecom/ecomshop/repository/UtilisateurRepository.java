package com.ecom.ecomshop.repository;

import com.ecom.ecomshop.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    @Query(value = "SELECT * FROM utilisateur WHERE email = ?1 AND password = ?2", nativeQuery = true)
    Utilisateur findByEmailAndPassword(String email, String password);

    Utilisateur findByEmail(String email);
}
