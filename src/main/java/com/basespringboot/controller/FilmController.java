package com.basespringboot.controller;

import com.basespringboot.dto.FilmDTO;
import com.basespringboot.service.FilmService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public List<FilmDTO> listerFilms() {
        return filmService.obtenirTousLesFilms();
    }

    @GetMapping("/{id}")
    public FilmDTO obtenirFilm(@PathVariable Long id) {
        return filmService.obtenirFilmParId(id);
    }

    @PostMapping
    public ResponseEntity<FilmDTO> creerFilm(@Valid @RequestBody FilmDTO dto) {
        FilmDTO cree = filmService.creerFilm(dto);
        return ResponseEntity.created(URI.create("/films/" + cree.id())).body(cree);
    }

    @PutMapping("/{id}")
    public FilmDTO modifierFilm(@PathVariable Long id, @Valid @RequestBody FilmDTO dto) {
        return filmService.modifierFilm(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerFilm(@PathVariable Long id) {
        filmService.supprimerFilm(id);
        return ResponseEntity.noContent().build();
    }
}
