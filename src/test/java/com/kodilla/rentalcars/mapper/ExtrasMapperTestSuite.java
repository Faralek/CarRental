package com.kodilla.rentalcars.mapper;

import com.kodilla.rentalcars.domain.Extras;
import com.kodilla.rentalcars.dto.ExtrasDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ExtrasMapperTestSuite {
    @InjectMocks
    private ExtrasMapper extrasMapper;

    @Test
    public void testExtrasMapper() {
        //Given
        ExtrasDto extrasDto = new ExtrasDto(1L, "example", new BigDecimal(0), new ArrayList<>());
        ExtrasDto extrasDto1;

        Extras extras = new Extras(1L, "example", new BigDecimal(0), new ArrayList<>());
        Extras extras1;

        List<ExtrasDto> extrasDtoList = new ArrayList<>();
        List<ExtrasDto> extrasDtoList1;
        List<Extras> extrasList = new ArrayList<>();
        List<Extras> extrasList1;

        //WHEN
        extras1 = extrasMapper.mapToExtras(extrasDto);
        extrasDto1 = extrasMapper.mapToExtrasDto(extras);

        extrasDtoList.add(extrasDto);
        extrasList.add(extras);

        extrasDtoList1 = extrasMapper.mapToExtrasDtoList(extrasList);
        extrasList1 = extrasMapper.mapToExtrasList(extrasDtoList);

        //THEN
        Assert.assertEquals(extrasDtoList1.size(),extrasList.size());
        Assert.assertEquals(extrasList1.size(),extrasDtoList.size());
        assertThat(extras).isEqualToComparingFieldByField(extrasDto1);
        assertThat(extrasDto).isEqualToComparingFieldByField(extras1);
    }
}

