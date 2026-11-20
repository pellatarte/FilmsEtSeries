package com.basespringboot.service;

import com.basespringboot.dto.SerieDTO;

import java.util.List;

public interface SerieService {

    List<SerieDTO> getAll();

    SerieDTO getById(Long id);

    SerieDTO create(SerieDTO serieDTO);

    SerieDTO update(Long id, SerieDTO serieDTO);

    void delete(Long id);
}