package com.kodilla.rentalcars.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @Column(name = "ID")
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

    @ManyToMany(mappedBy = "extrasList")
    @JsonBackReference
    public List<Car> getCars() {
        return cars;
    }

    @Override
    public String toString() {
        return "Extras{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
