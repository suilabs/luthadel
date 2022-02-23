package com.suilabs.luthadel.service;

import com.suilabs.luthadel.exceptions.MediaNotFoundException;
import com.suilabs.luthadel.model.Media;
import com.suilabs.luthadel.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MediaService implements IMediaService {
    @Autowired
    private MediaRepository repository;

    @Override
    public List<Media> findAll() {
        return repository.findAll();
    }

    @Override
    public Media getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new MediaNotFoundException(id));
    }

    @Override
    public Media create(String name, String url) {
        Media media = new Media();

        media.setName(name);
        media.setUrl(url);
        return repository.save(media);
    }

    @Override
    public Media update(Long id, String name, String url) {
        Media media = repository.findById(id).orElseThrow(() -> new MediaNotFoundException(id));

        media.setName(Optional.ofNullable(name).orElse(media.getName()));
        media.setUrl(Optional.ofNullable(url).orElse(media.getUrl()));
        return repository.save(media);
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}