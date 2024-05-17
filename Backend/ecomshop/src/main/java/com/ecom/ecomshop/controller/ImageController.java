// package com.ecom.ecomshop.controller;

// import org.springframework.core.io.ClassPathResource;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RestController;

// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;

// @RestController
// public class ImageController {

//     @GetMapping("/images/{imageName}")
//     public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
//         // Charger l'image depuis le chemin du classpath
//         ClassPathResource imgFile = new ClassPathResource("ecomshop/src/main/resources/static/images/" + imageName);
//         if (imgFile.exists()) {
//             byte[] bytes = Files.readAllBytes(imgFile.getFile().toPath());
//             return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(bytes);
//         } else {
//             return ResponseEntity.notFound().build();
//         }
//     }
// }
