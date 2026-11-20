package com.basespringboot.controller;
import com.basespringboot.service.GenreService;
import com.basespringboot.dto.GenreDTO;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController // Indicates that this is the controller for the API (and the entity)
@RequestMapping("/genres") // Mapps all requests to the ```genre/``` repository to the API
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }
    /*
    THis sections indcated how to map all requets on 
    the ```genres/``` directory. Here we code :
    [Sprint 1: CRUD]
    - ```GET```,
    - ```POST```,
    - ```PUT```,
    - ```DELETE```.
    Some may be added later.
    */

    /*  
    Indicates that all the ```GET``` requests are mapped to 
    this function implementing pre-made functions : ```findAll()```
    and ```getAll()```
    => Needs the DTO to work (which is why we import it)
    */
    @GetMapping
    public List<GenreDTO> getAll() {
        return genreService.findAll();
    }

    /*
    Get a ```Genre``` object using its' ID
    */
    @GetMapping("/{id}")
    public GenreDTO getById(@PathVariable Long id) { // Function using a parameter in the URL
        return genreService.findById(id);
    }

    /*
    Mapping the ```POST``` HTTP request to the ```create()```
    function
    => Create a new ```Genre``` object
    */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)   // Sends a 201 message : created the object
    public GenreDTO create(@RequestBody GenreDTO dto) { // Indicates that the request contains a JSON body
        return genreService.create(dto);
    }

    /*
    Mapping the ```PUT``` HTTP request to the ```update()```
    function
    => Update or replace an existing ```Genre``` object
    */
    @PutMapping("/{id}")
    public GenreDTO update(@PathVariable Long id, @RequestBody GenreDTO dto) {
        return genreService.update(id, dto);
    }

    /*
    Mapping the ```DELETE``` HTTP request to the ```delete()```
    function
    => Delete an existing ```Genre``` object
    => Thew object needs to eist first, so we check if the ID is 
    linked to an existing object
    */
    @DeleteMapping("/{id}") // Depending on the ID sent in the HTTP request
    @ResponseStatus(HttpStatus.NO_CONTENT) // sends a 204 message : nothing to delete
    public void delete(@PathVariable Long id) {
        genreService.delete(id);
    }
}
