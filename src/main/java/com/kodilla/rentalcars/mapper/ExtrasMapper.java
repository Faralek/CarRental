package com.kodilla.rentalcars.mapper;

import com.kodilla.rentalcars.domain.Extras;
import com.kodilla.rentalcars.dto.ExtrasDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExtrasMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public Extras mapToExtras(ExtrasDto extrasDto) {
        Extras extras = modelMapper.map(extrasDto, Extras.class);
        return extras;
    }

    public ExtrasDto mapToExtrasDto(Extras extras) {
        ExtrasDto extrasDto = modelMapper.map(extras, ExtrasDto.class);
        return extrasDto;
    }

    public List<ExtrasDto> mapToExtrasDtoList(final List<Extras> extras) {
        if (extras.isEmpty()) {
            return new ArrayList<>();
        } else {
            return extras.stream()
                    .map(this::mapToExtrasDto)
                    .collect(Collectors.toList());
        }
    }

    public List<Extras> mapToExtrasList(final List<ExtrasDto> extrasDtos) {
        if (extrasDtos.isEmpty()) {
            return new ArrayList<>();
        } else {
            return extrasDtos.stream()
                    .map(this::mapToExtras)
                    .collect(Collectors.toList());
        }
    }
}
