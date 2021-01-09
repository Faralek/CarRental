package com.kodilla.rentalcars.repository;

import com.kodilla.rentalcars.domain.Extras;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ExtrasRepository extends CrudRepository<Extras, Long> {
    @Override
    List<Extras> findAll();

    @Override
    Extras save(Extras extras);

    @Override
    Optional<Extras> findById(Long id);

    @Override
    void deleteById(Long id);

    @Override
    long count();

    List<Extras> findByName(String name);
}
