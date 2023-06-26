package com.example.ProjektniZadatak.Controllers;

import com.example.ProjektniZadatak.Repository.OrdersRepository;
import com.example.ProjektniZadatak.data.Entities.Orders;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600, allowCredentials = "true")
@AllArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrdersRepository ordersRepository;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Orders>> getAllOrders(@RequestParam(required = false) BigDecimal totalPrice) {
        try {
            List<Orders> lessons = new ArrayList<>();

            if (totalPrice == null)
                ordersRepository.findAll().forEach(lessons::add);
            else
                ordersRepository.findByTotalPrice(totalPrice).forEach(lessons::add);

            if (lessons.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(lessons, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Orders> createLesson(@RequestBody Orders orders) {

        return ResponseEntity.ok(ordersRepository.save(orders));
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Orders> updateLesson(@RequestBody Orders lesson) {
        return ResponseEntity.ok(ordersRepository.save(lesson));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteLesson(@PathVariable("id") long id) {
        try {
            ordersRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteAllLessons() {
        try {
            ordersRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

