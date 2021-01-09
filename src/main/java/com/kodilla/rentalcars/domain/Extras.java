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
@Table(name = "EXTRAS")
public class Extras {
    private Long id;
    private String name;
    private BigDecimal price;
    private List<Car> cars = new ArrayList<>();

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "EXTRAS_ID")
    public Long getId() {
        return id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }
    @Column(name = "PRICE")
    public BigDecimal getPrice() {
        return price;
    }
    @ManyToMany(mappedBy = "extras")
    public List<Car> getCars() {
        return cars;
    }
}
