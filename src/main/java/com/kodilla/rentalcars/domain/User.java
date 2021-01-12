package com.kodilla.rentalcars.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "USERS")
public class User {

    private Long id;
    private String username;
    private String password;
    private Cart cart;
    private List<Order> orders = new ArrayList<>();

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    @Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CART", referencedColumnName = "ID")
    @JsonManagedReference
    public Cart getCart() {
        return cart;
    }

    @OneToMany(targetEntity = Order.class, mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference
    public List<Order> getOrders() {
        return orders;
    }
}

