package com.basespringboot.service;
import com.basespringboot.dto.GenreDTO;
import com.basespringboot.entity.Genre;
import com.basespringboot.repository.GenreRepository;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException; // Packages pour gérer les exceptions


@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<GenreDTO> findAll() {
        return genreRepository.findAll()      // ← minuscule ici
                .stream()
                .map(this::toDto)
                .toList();
    }

    // Méthodes HTTP : appliquer le CRUD => GET, POST, PUT, DELETE

    @Override
    public GenreDTO findById(Long id) {
        return toDto(getGenreOrThrow(id));
    }

    // Méthode POST : ajout d'objets en base grâce à l'API
    @Override
    public GenreDTO create(GenreDTO dto) {
        Genre genre = toEntity(dto);                   // 1. DTO → entité
        Genre saved = genreRepository.save(genre);     // 2. on enregistre en base
        return toDto(saved);                           // 3. entité → DTO (avec l'id généré)
    }

    // Méthode PUT : modifier un objet existant
    @Override
    public GenreDTO update(Long id, GenreDTO dto) {
        Genre genre = getGenreOrThrow(id);        // 1. Look for the genre using the ID or return 404 if not found
        genre.setNom(dto.nom());                  // 2. Alter the objet's values
        genre.setDescription(dto.description());
        Genre saved = genreRepository.save(genre); // 3. Save the changes
        return toDto(saved);                      // 4. Using the DTO : sending the updated
    }

    // Méthode DELETE : delete existing objects (in this case, genres)
    // We're using pre-made function ```delete()```
    @Override
    public void delete(Long id) {
        Genre genre = getGenreOrThrow(id);   // 1. on vérifie qu'il existe (sinon 404)
        genreRepository.delete(genre);       // 2. on le supprime
    }

    // Convert the entity in a usable object (DTO)
    private GenreDTO toDto(Genre genre) {
        return new GenreDTO(genre.getId(), genre.getNom(), genre.getDescription());
    }

    // Convert the DTO in an entity to insert
    private Genre toEntity(GenreDTO dto) {
        Genre genre = new Genre();
        genre.setNom(dto.nom());
        genre.setDescription(dto.description());
        return genre;
    }

    // Look for a genre and show it or return 404 if not found
    private Genre getGenreOrThrow(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Genre introuvable avec l'id " + id));
    }
}