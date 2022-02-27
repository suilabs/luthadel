package com.suilabs.luthadel.service;

import com.suilabs.luthadel.dtos.MediaDTO;
import com.suilabs.luthadel.model.Media;
import org.springframework.hateoas.CollectionModel;

import java.util.List;

public interface IMediaService {
    CollectionModel<MediaDTO> findAll();
    MediaDTO getById(Long id);
    MediaDTO create(Media media);
    MediaDTO update(Long id, Media media);
    void delete(Long id);
}
