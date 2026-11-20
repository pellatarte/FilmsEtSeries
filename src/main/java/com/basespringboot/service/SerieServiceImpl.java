package com.basespringboot.service;

import com.basespringboot.dto.SerieDTO;
import com.basespringboot.entity.Serie;
import com.basespringboot.repository.SerieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional 
public class SerieServiceImpl implements SerieService {

    private final SerieRepository serieRepository;

    public SerieServiceImpl(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    @Override
    public List<SerieDTO> getAll() {
        return serieRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SerieDTO getById(Long id) {
        return toDTO(findSerieOrThrow(id));
    }

    @Override
    public SerieDTO create(SerieDTO dto) {
        Serie serie = new Serie();
        applyDto(serie, dto);
        return toDTO(serieRepository.save(serie));
    }

    @Override
    public SerieDTO update(Long id, SerieDTO dto) {
        Serie serie = findSerieOrThrow(id);
        applyDto(serie, dto);
        return toDTO(serieRepository.save(serie));
    }

    @Override
    public void delete(Long id) {
        if (!serieRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Série introuvable : " + id);
        }
        serieRepository.deleteById(id);
    }

    // ----- Méthodes privées -----

    private Serie findSerieOrThrow(Long id) {
        return serieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Série introuvable : " + id));
    }

    // Copie les données du DTO sur l'entité (l'id n'est jamais copié)
    private void applyDto(Serie serie, SerieDTO dto) {
        serie.setTitle(dto.getTitle());
        serie.setSeasons(dto.getSeasons());
        serie.setReleaseYear(dto.getReleaseYear());
    }

    private SerieDTO toDTO(Serie serie) {
        SerieDTO dto = new SerieDTO();
        dto.setId(serie.getId());
        dto.setTitle(serie.getTitle());
        dto.setSeasons(serie.getSeasons());
        dto.setReleaseYear(serie.getReleaseYear());
        return dto;
    }
}