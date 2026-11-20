package com.basespringboot.controller;

import com.basespringboot.dto.SerieDTO;
import com.basespringboot.service.SerieService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    private final SerieService serieService;

    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }

    @GetMapping
    public List<SerieDTO> getAll() {
        return serieService.getAll();
    }

    @GetMapping("/{id}")
    public SerieDTO getById(@PathVariable Long id) {
        return serieService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SerieDTO create(@RequestBody SerieDTO serieDTO) {
        return serieService.create(serieDTO);
    }

    @PutMapping("/{id}")
    public SerieDTO update(@PathVariable Long id, @RequestBody SerieDTO serieDTO) {
        return serieService.update(id, serieDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        serieService.delete(id);
    }
}