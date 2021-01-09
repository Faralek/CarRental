package com.kodilla.rentalcars.mapper;

import com.kodilla.rentalcars.domain.Extras;
import com.kodilla.rentalcars.dto.CarDto;
import com.kodilla.rentalcars.dto.ExtrasDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class ExtrasMapper {
    @Autowired
    private CarMapper carMapper;

    public Extras mapToExtrasDto(ExtrasDto extrasDto) {
        return new Extras(extrasDto.getId(),
                extrasDto.getName(),
                extrasDto.getPrice(),
                carMapper.mapToCarList(extrasDto.getCars()));
    }

    public ExtrasDto mapToExtras(Extras extras) {
        return new ExtrasDto(extras.getId(),
                extras.getName(),
                extras.getPrice(),
                carMapper.mapToCarDtoList(extras.getCars()));
    }

    public List<ExtrasDto> mapToExtrasDtoList(List<Extras> extras) {
        return extras.stream()
                .map(e -> new ExtrasDto(
                        e.getId(),
                        e.getName(),
                        e.getPrice(),
                        carMapper.mapToCarDtoList(e.getCars())))
                .collect(Collectors.toList());
    }

    public List<Extras> mapToExtrasList(List<ExtrasDto> extrasDtos) {
        return extrasDtos.stream()
                .map(e -> new Extras(
                        e.getId(),
                        e.getName(),
                        e.getPrice(),
                        carMapper.mapToCarList(e.getCars())))
                .collect(Collectors.toList());
    }
}
