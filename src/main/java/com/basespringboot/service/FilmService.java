package com.basespringboot.service;

import com.basespringboot.dto.FilmDTO;
import java.util.List;

public interface FilmService {
    FilmDTO creerFilm(FilmDTO filmDTO);
    List<FilmDTO> obtenirTousLesFilms();
    FilmDTO obtenirFilmParId(Long id);
    FilmDTO modifierFilm(Long id, FilmDTO filmDTO);
    void supprimerFilm(Long id);
}
