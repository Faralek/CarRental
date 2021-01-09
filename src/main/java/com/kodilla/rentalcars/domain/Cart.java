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
@Table(name = "CARTS")
public class Cart {

    private Long id;
    private BigDecimal sum = new BigDecimal(0);
    private List<Car> cars = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID", unique = true)
    public Long getId() {
        return id;
    }

    @Column(name = "SUM")
    public BigDecimal getSum() {
        return sum;
    }

    @OneToMany(
            targetEntity = Car.class,
            mappedBy = "cart",
            fetch = FetchType.LAZY
    )
    public List<Car> getCars() {
        return cars;
    }

    @OneToMany(targetEntity = Order.class, mappedBy = "cart", fetch = FetchType.LAZY)
    public List<Order> getOrders() {
        return orders;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }

}
