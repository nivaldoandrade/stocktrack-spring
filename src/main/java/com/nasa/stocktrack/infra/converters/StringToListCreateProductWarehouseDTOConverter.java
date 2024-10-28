package com.nasa.stocktrack.infra.converters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nasa.stocktrack.interfaces.dtos.productWarehouse.CreateProductWarehouseRequestDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StringToListCreateProductWarehouseDTOConverter implements Converter<String, List<CreateProductWarehouseRequestDTO>> {

    private final ObjectMapper objectMapper;

    public StringToListCreateProductWarehouseDTOConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public List<CreateProductWarehouseRequestDTO> convert(String source) {
        try {
            return objectMapper.readValue(
                    source,
                    new TypeReference<List<CreateProductWarehouseRequestDTO>>() {}
            );
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid format for stockPerWarehouse JSON", e);
        }
    }
}
