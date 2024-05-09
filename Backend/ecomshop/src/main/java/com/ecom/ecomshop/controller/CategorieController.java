import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecom.ecomshop.model.Categorie;
import com.ecom.ecomshop.repository.CategorieRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class CategorieController {

    @Autowired
    private CategorieRepository categorieRepository;

    @GetMapping("/categorie")
    public List<Categorie> listeCategories() {
        return categorieRepository.findAll();
    }

    @PostMapping("/categorie")
    public String ajouterCategorie(@RequestBody Categorie categorie) {
        categorieRepository.save(categorie);
        return "Catégorie ajoutée avec succès";
    }

    @PutMapping("/categorie/{id}")
    public String modifierCategorie(@PathVariable Long id, @RequestBody Categorie categorieModifie) {
        Optional<Categorie> categorieOptional = categorieRepository.findById(id);
        if (categorieOptional.isPresent()) {
            Categorie categorie = categorieOptional.get();
            if (categorieModifie.getCatNom() != null) {
                categorie.setCatNom(categorieModifie.getCatNom());
            }
            // Vérifier si des modifications ont été apportées avant de sauvegarder
            if (!categorie.equals(categorieModifie)) {
                categorieRepository.save(categorie);
                return "Catégorie modifiée avec succès";
            } else {
                return "Aucune modification détectée";
            }
        } else {
            return "Catégorie non trouvée";
        }
    }

    @DeleteMapping("/categorie/{id}")
    public String supprimerCategorie(@PathVariable Long id) {
        categorieRepository.deleteById(id);
        return "Catégorie supprimée avec succès";
    }
}
