package com.ecom.ecomshop.repository;

import com.ecom.ecomshop.model.DetailsCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailCommandeRepository extends JpaRepository<DetailsCommande, Long> {
}
