package com.kodilla.rentalcars.controller;

import com.google.gson.Gson;
import com.kodilla.rentalcars.domain.Extras;
import com.kodilla.rentalcars.dto.ExtrasDto;
import com.kodilla.rentalcars.mapper.ExtrasMapper;
import com.kodilla.rentalcars.service.ExtrasDbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ExtrasController.class)
public class ExtrasControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ExtrasDbService extrasDbService;
    @MockBean
    private ExtrasMapper extrasMapper;

    @Test
    public void shouldCreateEmptyExtrasList() throws Exception {

        //Given
        List<ExtrasDto> extrasDtoList = new ArrayList<>();
        List<Extras> extrasList = new ArrayList<>();
        when(extrasDbService.getAllExtras()).thenReturn(extrasList);
        when(extrasMapper.mapToExtrasDtoList(extrasList)).thenReturn(extrasDtoList);

        //When & Then
        mockMvc.perform(get("/v1/extras").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    public void shouldGetExtrasList() throws Exception {
        //Given
        List<ExtrasDto> extrasDtoList = new ArrayList<>();
        extrasDtoList.add(new ExtrasDto(1L, "example", new BigDecimal(0), new ArrayList<>()));

        List<Extras> extrasList = new ArrayList<>();
        extrasList.add(new Extras(1L, "example", new BigDecimal(0), new ArrayList<>()));

        when(extrasDbService.getAllExtras()).thenReturn(extrasList);
        when(extrasMapper.mapToExtrasDtoList(extrasList)).thenReturn(extrasDtoList);

        //When & Then
        mockMvc.perform(get("/v1/extras").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("example")))
                .andExpect(jsonPath("$[0].cars", is(new ArrayList<>())));
    }

    @Test
    public void shouldGetExtras() throws Exception {

        //Given
        ExtrasDto extrasDto = new ExtrasDto(1L, "example", new BigDecimal(0), new ArrayList<>());

        Extras extras = new Extras(1L, "example", new BigDecimal(0), new ArrayList<>());

        when(extrasDbService.getExtrasById(1L)).thenReturn(Optional.of(extras));
        when(extrasMapper.mapToExtrasDto(any(Extras.class))).thenReturn(extrasDto);

        //When & Then
        mockMvc.perform(get("/v1/extras/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("example")))
                .andExpect(jsonPath("$.cars", is(new ArrayList<>())));
    }

    @Test
    public void shouldDeleteExtras() throws Exception {
        //Given
        Extras extras = new Extras(1L, "example", new BigDecimal(0), new ArrayList<>());

        when(extrasDbService.saveExtras(any(Extras.class))).thenReturn(extras);
        extrasDbService.saveExtras(extras);

        // When & Then
        mockMvc.perform(delete("/v1/extras/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldUpdateExtras() throws Exception {
        //Given
        ExtrasDto extrasDto = new ExtrasDto(1L, "example", new BigDecimal(0), new ArrayList<>());

        Extras extras = new Extras(1L, "example", new BigDecimal(0), new ArrayList<>());

        when(extrasDbService.saveExtras(any(Extras.class))).thenReturn(extras);
        when(extrasMapper.mapToExtras(any(ExtrasDto.class))).thenReturn(extras);
        when(extrasMapper.mapToExtrasDto(any(Extras.class))).thenReturn(extrasDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(extrasDto);

        //When&Then
        mockMvc.perform(put("/v1/extras").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("example")))
                .andExpect(jsonPath("$.cars", is(new ArrayList<>())));
    }

    @Test
    public void shouldCreateExtras() throws Exception {

        //Given
        ExtrasDto extrasDto = new ExtrasDto(1L, "example", new BigDecimal(0), new ArrayList<>());

        Extras extras = new Extras(1L, "example", new BigDecimal(0), new ArrayList<>());

        when(extrasDbService.saveExtras(any(Extras.class))).thenReturn(extras);
        when(extrasMapper.mapToExtras(any(ExtrasDto.class))).thenReturn(extras);
        when(extrasMapper.mapToExtrasDto(any(Extras.class))).thenReturn(extrasDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(extrasDto);

        //When & Then
        mockMvc.perform(post("/v1/extras")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());

    }
}
