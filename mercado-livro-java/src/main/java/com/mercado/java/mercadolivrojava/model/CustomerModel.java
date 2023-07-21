package com.mercado.java.mercadolivrojava.model;

import com.mercado.java.mercadolivrojava.enums.CustomerStatus;
import com.mercado.java.mercadolivrojava.enums.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "customer")
public class CustomerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = null;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    @Enumerated(EnumType.STRING)
    private CustomerStatus status;

    @Column
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "customer_roles", joinColumns = @JoinColumn(name = "customer_id"))
    private Set<Role> roles = new HashSet<>();

    public CustomerModel(){
    }

    public CustomerModel(Integer id, String name, String email, CustomerStatus status, String password, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
        this.password = password;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerStatus status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
