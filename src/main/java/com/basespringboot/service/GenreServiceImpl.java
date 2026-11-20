package com.basespringboot.service;
import com.basespringboot.dto.GenreDTO;
import com.basespringboot.entity.Genre;
import com.basespringboot.repository.GenreRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException; // Packages pour gérer les exceptions


@Service
@RequiredArgsConstructor 
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

 /*    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }
        */

    @Override
    public List<GenreDTO> findAll() {
        return genreRepository.findAll()      // find all the data in the repository
                .stream()
                .map(this::toDto)  // Convert the data to a DTO
                .toList();
    }

    /* 
    Implementing the CRUD functions 
    => they are mapped to HTTP GET/POST/PUT/DELETE 
    HTTP requests in the controller.
    */ 

    // GET method
    @Override
    public GenreDTO findById(Long id) {
        return toDto(getGenreOrThrow(id));
    }

    // Implementing create : corresponds to the POST method
    @Override
    public GenreDTO create(GenreDTO dto) {
        Genre genre = toEntity(dto);                   // converts the DTO in a ```Genre``` object
        Genre saved = genreRepository.save(genre);     // saves the data in the database
        return toDto(saved);                           // the saved data is converted to a DTO and sent to our code
    }

    // Implementing the GET method using setters and the ```save()``` native function
    @Override
    public GenreDTO update(Long id, GenreDTO dto) {
        Genre genre = getGenreOrThrow(id);        // 1. Look for the genre using the ID or return 404 if not found
        genre.setName(dto.name());                  // 2. Alter the objet's values
        genre.setDescription(dto.description());
        Genre saved = genreRepository.save(genre); // 3. Save the changes
        return toDto(saved);                      // 4. Using the DTO : sending the updated
    }

    /*
    Implementing the ```delete()``` function
    => corresponds to the ```DELETE``` HTTP request
    */
    @Override
    public void delete(Long id) {
        Genre genre = getGenreOrThrow(id);   // 1. on vérifie qu'il existe (sinon 404)
        genreRepository.delete(genre);       // 2. on le supprime
    }

    // Convert the entity in a usable object (DTO)
    private GenreDTO toDto(Genre genre) {
        return new GenreDTO(genre.getId(), genre.getName(), genre.getDescription());
    }

    // Convert the DTO in an entity to insert
    private Genre toEntity(GenreDTO dto) {
        Genre genre = new Genre();
        genre.setName(dto.name());
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