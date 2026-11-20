
package com.basespringboot.service;

import com.basespringboot.dto.FilmDTO;
import com.basespringboot.entity.Film;
import com.basespringboot.entity.Genre;
import com.basespringboot.repository.FilmRepository;
import com.basespringboot.repository.GenreRepository;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final GenreRepository genreRepository;

    public FilmServiceImpl(
            FilmRepository filmRepository,
            GenreRepository genreRepository) {
        this.filmRepository = filmRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public FilmDTO creerFilm(FilmDTO dto) {
        Film film = new Film();
        copierDonnees(dto, film);
        return versDTO(filmRepository.save(film));
    }

    @Override
    @Transactional(readOnly = true)
    public List<FilmDTO> obtenirTousLesFilms() {
        return filmRepository.findAll()
                .stream()
                .map(this::versDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public FilmDTO obtenirFilmParId(Long id) {
        return versDTO(trouverFilm(id));
    }

    @Override
    public FilmDTO modifierFilm(Long id, FilmDTO dto) {
        Film film = trouverFilm(id);
        copierDonnees(dto, film);
        return versDTO(filmRepository.save(film));
    }

    @Override
    public void supprimerFilm(Long id) {
        Film film = trouverFilm(id);
        filmRepository.delete(film);
    }

    private Film trouverFilm(Long id) {
        return filmRepository.findById(id)
                .orElseThrow(() ->
                    new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Film introuvable : " + id
                    )
                );
    }

    private void copierDonnees(FilmDTO dto, Film film) {

        Genre genre = genreRepository.findById(dto.genreId())
                .orElseThrow(() ->
                    new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Genre introuvable : " + dto.genreId()
                    )
                );

        film.setTitre(dto.titre());
        film.setAnneeSortie(dto.anneeSortie());
        film.setDureeMinutes(dto.dureeMinutes());
        film.setGenre(genre);
    }

    private FilmDTO versDTO(Film film) {
        return new FilmDTO(
                film.getId(),
                film.getTitre(),
                film.getAnneeSortie(),
                film.getDureeMinutes(),
                film.getGenre().getId()
        );
    }
}
