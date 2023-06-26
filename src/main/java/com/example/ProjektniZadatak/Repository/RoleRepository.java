package com.example.ProjektniZadatak.Repository;

import com.example.ProjektniZadatak.data.Entities.Roles;
import com.example.ProjektniZadatak.data.Enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByName(RoleType name);
}
