package com.kodilla.rentalcars.repository;

import com.kodilla.rentalcars.domain.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CarRepository extends CrudRepository<Car, Long> {
    @Override
    List<Car> findAll();

    @Override
    Car save(Car car);

    @Override
    Optional<Car> findById(Long id);

    @Override
    void deleteById(Long id);

    @Override
    long count();

    List<Car> findByName(String name);
}
