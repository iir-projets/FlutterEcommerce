package com.ecom.ecomshop.controller;

import com.ecom.ecomshop.model.DetailsCommande;
import com.ecom.ecomshop.repository.DetailCommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DetailsCommandeController {

    @Autowired
    private DetailCommandeRepository detailCommandeRepository;

    @GetMapping("/detailCommande")
    public List<DetailsCommande> listeDetailsCommande() {
        return detailCommandeRepository.findAll();
    }

    @PostMapping("/ajouterDetailCommande")
    public String ajouterDetailCommande(@RequestBody DetailsCommande detailsCommande) {
        detailCommandeRepository.save(detailsCommande);
        return "Détail de commande ajouté avec succès";
    }

    @PutMapping("/detailCommande/{id}")
    public String modifierDetailCommande(@PathVariable Long id, @RequestBody DetailsCommande detailsCommandeModifie) {
        Optional<DetailsCommande> detailsCommandeOptional = detailCommandeRepository.findById(id);
        if (detailsCommandeOptional.isPresent()) {
            DetailsCommande detailsCommande = detailsCommandeOptional.get();
            if (detailsCommandeModifie.getQty() != null) {
                detailsCommande.setQty(detailsCommandeModifie.getQty());
            }
            // Vous pouvez ajouter des conditions similaires pour d'autres attributs si nécessaire
            detailCommandeRepository.save(detailsCommande);
            return "Détail de commande modifié avec succès";
        } else {
            return "Détail de commande non trouvé";
        }
    }

    @DeleteMapping("/detailCommande/{id}")
    public String supprimerDetailCommande(@PathVariable Long id) {
        detailCommandeRepository.deleteById(id);
        return "Détail de commande supprimé avec succès";
    }
}
