package com.example.ProjektniZadatak.Controllers;

import com.example.ProjektniZadatak.Repository.ProductRepository;
import com.example.ProjektniZadatak.data.Entities.Product;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600, allowCredentials = "true")
@AllArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String name) {
        try {
            List<Product> lessons = new ArrayList<>();

            if (name == null)
                productRepository.findAll().forEach(lessons::add);
            else
                productRepository.findByName(name).forEach(lessons::add);

            if (lessons.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(lessons, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
        Optional<Product> lesson = productRepository.findById(id);

        if (lesson.isPresent()) {
            return new ResponseEntity<>(lesson.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {

        return ResponseEntity.ok(productRepository.save(product));
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> updateLesson(@RequestBody Product product) {
        return ResponseEntity.ok(productRepository.save(product));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long id) {
        try {
            productRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteAllProducts() {
        try {
            productRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



}

