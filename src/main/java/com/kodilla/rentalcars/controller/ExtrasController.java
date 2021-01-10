package com.kodilla.rentalcars.controller;

import com.kodilla.rentalcars.dto.ExtrasDto;
import com.kodilla.rentalcars.exception.ExtrasNotFoundException;
import com.kodilla.rentalcars.mapper.ExtrasMapper;
import com.kodilla.rentalcars.service.ExtrasDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
public class ExtrasController {
    @Autowired
    private ExtrasDbService extrasDbService;
    @Autowired
    private ExtrasMapper extrasMapper;

    @RequestMapping(method = RequestMethod.POST, value = "/extras", consumes = APPLICATION_JSON_VALUE)
    public void createExtras(@RequestBody ExtrasDto extrasDto) {
        extrasDbService.saveExtras(extrasMapper.mapToExtras(extrasDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/extras")
    public ExtrasDto updateCar(@RequestBody ExtrasDto extrasDto) {
        return extrasMapper.mapToExtrasDto(extrasDbService.saveExtras(extrasMapper.mapToExtras(extrasDto)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/extras")
    public List<ExtrasDto> getExtrasList() {
        return extrasMapper.mapToExtrasDtoList(extrasDbService.getAllExtras());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/extras/{extrasId}")
    public ExtrasDto getExtras(@PathVariable Long extrasId) throws ExtrasNotFoundException {
        return extrasMapper.mapToExtrasDto(extrasDbService.getExtrasById(extrasId).orElseThrow(() -> new ExtrasNotFoundException("Extras not found " + extrasId)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/extras/{extrasId}")
    public void deleteExtras(@PathVariable Long extrasId) throws ExtrasNotFoundException {
        try {
            extrasDbService.deleteById(extrasId);
        } catch (EmptyResultDataAccessException e) {
            throw new ExtrasNotFoundException("Car not found " + extrasId, e);
        }
    }
}
