package com.suilabs.luthadel.service;

import com.suilabs.luthadel.assembler.MediaModelAssembler;
import com.suilabs.luthadel.dtos.MediaDTO;
import com.suilabs.luthadel.model.Media;
import com.suilabs.luthadel.repository.MediaRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MediaService implements IMediaService {
    private final MediaRepository repository;
    private final MediaModelAssembler mediaAssembler;

    public MediaService(MediaRepository repository, MediaModelAssembler mediaAssembler) {
        this.mediaAssembler = mediaAssembler;
        this.repository = repository;
    }

    @Override
    public CollectionModel<MediaDTO> findAll() {
        List<Media> allMedia = repository.findAll();
        if (!allMedia.isEmpty()) {
            return mediaAssembler.toCollectionModel(allMedia);
        }
        return null;
    }

    @Override
    public MediaDTO getById(Long id) {
        Media media = repository.findById(id).orElse(null);
        if (media != null) {
            return mediaAssembler.toModel(media);
        }
        return null;
    }

    @Override
    public MediaDTO create(Media media) {
        return mediaAssembler.toModel(repository.save(media));
    }

    @Transactional
    @Override
    public MediaDTO update(Long id, Media media) {
        Media updatedMedia = repository.findById(id).orElse(null);

        if (updatedMedia == null) {
            return null;
        }

        updatedMedia.setName(media.getName());
        updatedMedia.setUrl(media.getUrl());
        return mediaAssembler.toModel(repository.save(updatedMedia));
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}