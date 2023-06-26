package com.example.ProjektniZadatak.data.Entities;

import com.example.ProjektniZadatak.data.Enums.RoleType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@Data
@Table(name = "roles")
public class Roles {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = true)
    private Object name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roles roles = (Roles) o;
        return id == roles.id && Objects.equals(name, roles.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Enumerated(EnumType.STRING)
    private RoleType roleType;
}
