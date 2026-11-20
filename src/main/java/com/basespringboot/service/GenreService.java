package com.basespringboot.service;

import com.basespringboot.dto.GenreDTO;
import java.util.List;

public interface GenreService {
    List<GenreDTO> findAll();
    GenreDTO findById(Long id);
    GenreDTO create(GenreDTO dto);
    GenreDTO update(Long id, GenreDTO dto);
    void delete(Long id);
}
