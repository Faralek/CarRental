package com.kodilla.rentalcars.service;

import com.kodilla.rentalcars.domain.Extras;
import com.kodilla.rentalcars.log.Log;
import com.kodilla.rentalcars.log.LogService;
import com.kodilla.rentalcars.repository.ExtrasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExtrasDbService {
    @Autowired
    private ExtrasRepository extrasRepository;
    @Autowired
    private LogService logService;

    public List<Extras> getAllExtras(){
        return extrasRepository.findAll();
    }

    public Extras saveExtras(final Extras extras){
        logService.saveLog(new Log("Created new Extra" , LocalDate.now()));
        return extrasRepository.save(extras);
    }

    public void deleteById(final Long id){
        logService.saveLog(new Log("Deleted Extra" , LocalDate.now()));
        extrasRepository.deleteById(id);
    }

    public Optional<Extras> getExtrasById(final Long id){
        return extrasRepository.findById(id);
    }

    public List<Extras> findByName(final String name){
        return extrasRepository.findByName(name);
    }
}
