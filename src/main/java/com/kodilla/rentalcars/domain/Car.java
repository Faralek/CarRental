package com.kodilla.rentalcars.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Entity
@Table(name = "CARS")
public class Car {

    private Long id;
    private String description;
    private String name;
    private BigDecimal dailyPrice;
    private List<Extras> extras = new ArrayList<>();
    private Cart cart;
    private Order order;

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    @Column(name = "DAILYPRICE")
    public BigDecimal getDailyPrice() {
        return dailyPrice;
    }

    @ManyToMany
    @JoinTable(
            name = "JOIN_CAR_EXTRAS",
            joinColumns = {@JoinColumn(name = "CAR_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "EXTRAS_ID", referencedColumnName = "ID")}
    )
    public List<Extras> getExtras() {
        return extras;
    }

    @ManyToOne
    @JoinColumn(name = "CART")
    public Cart getCart() {
        return cart;
    }

    @ManyToOne
    @JoinColumn(name = "ORDER")
    public Order getOrder() {
        return order;
    }
}
