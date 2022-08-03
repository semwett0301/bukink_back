package com.example.code.services.CityService;

import com.example.code.model.dto.ResponseCityDTO;
import com.example.code.model.mappers.CityMapper;
import com.example.code.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImplementation implements CityService{
    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImplementation(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<ResponseCityDTO> getCities() {
        return cityRepository.findAll(Sort.by(Sort.Direction.DESC, "popularity")).stream()
                .map(elem -> CityMapper.INSTANCE.toResponseDTO(elem, elem.getCountry()))
                .collect(Collectors.toList());
    }
}