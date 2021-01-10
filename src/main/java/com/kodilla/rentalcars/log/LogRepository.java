package com.kodilla.rentalcars.log;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface LogRepository extends CrudRepository<Log,Long> {
    @NotNull
    @Override
    Log save(@NotNull Log log);

    @Override
    List<Log> findAll();

    @Override
    Optional<Log> findById(Long id);
}
