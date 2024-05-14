package com.ecom.ecomshop.controllersTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ecom.ecomshop.controller.ArticleController;
import com.ecom.ecomshop.model.Article;
import com.ecom.ecomshop.repository.ArticleRepository;
import com.ecom.ecomshop.repository.CategorieRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleController.class)
public class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticleRepository articleRepository;

    @MockBean
    private CategorieRepository categorieRepository;

   /* @Test
    public void testGetArticles() throws Exception {
        Article article = new Article();
        article.setId(1L);
        article.setName("Test Article");
        article.setDescription("Test Description");
        article.setPrice(new BigDecimal("19.99"));
        article.setQuantite(100);
        article.setImage("test.jpg");

        when(articleRepository.findAll()).thenReturn(Arrays.asList(article));

        mockMvc.perform(MockMvcRequestBuilders.get("/articles"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Test Article"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(19.99));
    }*/

   /* @Test
    public void testAddArticle() throws Exception {
        Article article = new Article();
        article.setName("New Article");
        article.setDescription("New Description");
        article.setPrice(new BigDecimal("29.99"));
        article.setQuantite(50);
        article.setImage("new.jpg");

        mockMvc.perform(MockMvcRequestBuilders.post("/addArticle")
                .param("name", "New Article")
                .param("description", "New Description")
                .param("price", "29.99")
                .param("quantite", "50")
                .param("categorieId", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"New Article\",\"description\":\"New Description\",\"price\":29.99,\"quantite\":50,\"categorieId\":1}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Article ajouté avec succès: " + article.getId()));
    }*/

    @Test
    public void testUpdateArticle() throws Exception {
        Article existingArticle = new Article();
        existingArticle.setId(1L);
        existingArticle.setName("Old Article");
        existingArticle.setDescription("Old Description");
        existingArticle.setPrice(new BigDecimal("15.99"));
        existingArticle.setQuantite(30);
        existingArticle.setImage("old.jpg");

        when(articleRepository.findById(1L)).thenReturn(Optional.of(existingArticle));

        mockMvc.perform(MockMvcRequestBuilders.put("/updaateArticle/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Updated Article\",\"description\":\"Updated Description\",\"price\":20.99,\"quantite\":25}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Article modifié avec succès"));
    }

    @Test
    public void testDeleteArticle() throws Exception {
        when(articleRepository.existsById(1L)).thenReturn(true);
        doNothing().when(articleRepository).deleteById(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/dropArticle/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Article supprimé avec succès"));
    }
}