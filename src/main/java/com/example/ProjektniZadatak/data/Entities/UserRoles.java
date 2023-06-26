package com.example.ProjektniZadatak.data.Entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "user_roles", schema = "prodavnica", catalog = "")
public class UserRoles {
    @Basic
    @Column(name = "user_id", nullable = false)
    private long userId;
    @Basic
    @Column(name = "role_id", nullable = false)
    private int roleId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoles userRoles = (UserRoles) o;
        return userId == userRoles.userId && roleId == userRoles.roleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleId);
    }
}
