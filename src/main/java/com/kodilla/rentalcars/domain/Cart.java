package com.kodilla.rentalcars.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    public Cart(Long id, BigDecimal sum, List<Car> cars, List<Order> orders) {
        this.id = id;
        this.sum = sum;
        this.cars = cars;
        this.orders = orders;
    }

    @Id
    @GeneratedValue
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
            cascade = CascadeType.ALL,
            targetEntity = Car.class,
            mappedBy = "cart",
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
    public List<Car> getCars() {
        return cars;
    }
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Order.class, mappedBy = "cart", fetch = FetchType.LAZY)
    public List<Order> getOrders() {
        return orders;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    @JsonBackReference
    public User getUser() {
        return user;
    }

    public List<Car> addCar(Car car, int duration) {
        BigDecimal carPrice = car.getDailyPrice().multiply(BigDecimal.valueOf(duration));
        sum = sum.add(carPrice);
        cars.add(car);
        return cars;
    }

    public void removeCar(Car car, int duration) {
        if (duration > 0) {
            BigDecimal carPrice = car.getDailyPrice().multiply(BigDecimal.valueOf(duration));
            sum = sum.subtract(carPrice);
            cars.remove(car);
        }
    }
}
