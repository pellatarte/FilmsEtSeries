package com.basespringboot.controller;
import com.basespringboot.service.GenreService;
import com.basespringboot.dto.GenreDTO;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<GenreDTO> getAll() {
        return genreService.findAll();
    }

    @GetMapping("/{id}")
    public GenreDTO getById(@PathVariable Long id) {
        return genreService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)   // renvoie 201 au lieu de 200
    public GenreDTO create(@RequestBody GenreDTO dto) {
        return genreService.create(dto);
    }

    @PutMapping("/{id}")
    public GenreDTO update(@PathVariable Long id, @RequestBody GenreDTO dto) {
        return genreService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // renvoie 204 (rien à renvoyer)
    public void delete(@PathVariable Long id) {
        genreService.delete(id);
    }
}
