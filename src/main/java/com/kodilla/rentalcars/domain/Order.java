package com.kodilla.rentalcars.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ORDERS")
public class Order {
    private Long id;
    private Cart cart;
    private List<Car> cars = new ArrayList<>();
    private User user;
    private BigDecimal sum = new BigDecimal(0);

    public Order(Long id, Cart cart, List<Car> cars, BigDecimal sum) {
        this.id = id;
        this.cart = cart;
        this.cars = cars;
        this.sum = sum;
    }

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    public Long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "CART_ID")
    @JsonBackReference
    public Cart getCart() {
        return cart;
    }

    @OneToMany(
            targetEntity = Car.class,
            mappedBy = "order",
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
    public List<Car> getCars() {
        return cars;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @JsonBackReference
    public User getUser() {
        return user;
    }


}
