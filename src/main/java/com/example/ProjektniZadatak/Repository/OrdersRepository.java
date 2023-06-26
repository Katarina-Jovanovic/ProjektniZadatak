package com.example.ProjektniZadatak.Repository;

import com.example.ProjektniZadatak.data.Entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {



    List<Orders> findByTotalPrice(BigDecimal totalPrice);

    List<Orders> findByUserId(Long userId);
}
