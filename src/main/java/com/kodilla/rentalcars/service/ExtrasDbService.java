package com.kodilla.rentalcars.service;

import com.kodilla.rentalcars.domain.Extras;
import com.kodilla.rentalcars.repository.ExtrasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExtrasDbService {
    @Autowired
    private ExtrasRepository extrasRepository;
    public List<Extras> getAllExtras(){
        return extrasRepository.findAll();
    }

    public Extras saveExtras(final Extras extras){
        return extrasRepository.save(extras);
    }

    public void deleteById(final Long id){
        extrasRepository.deleteById(id);
    }

    public Optional<Extras> getExtrasById(final Long id){
        return extrasRepository.findById(id);
    }

    public List<Extras> findByName(final String name){
        return extrasRepository.findByName(name);
    }
}
