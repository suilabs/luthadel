package com.suilabs.luthadel.service;

import com.suilabs.luthadel.exceptions.AlbumNotFoundException;
import com.suilabs.luthadel.model.Album;
import com.suilabs.luthadel.model.Media;
import com.suilabs.luthadel.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class AlbumService implements IAlbumService {
    @Autowired
    private AlbumRepository repository;

    @Override
    public List<Album> findAll() {
        return repository.findAll();
    }

    @Override
    public Album getById(Long id) throws AlbumNotFoundException {
        return repository.findById(id).orElseThrow(() -> new AlbumNotFoundException(id));
    }

    @Override
    public Album create(Album album) {
        return repository.save(album);
    }

    @Override
    public Album update(Long id, Album album) {
        Album existingAlbum = getById(id);

        album.setId(id);
        return repository.save(album);
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

    @Override
    public Album addMedia(Long id, List<Media> mediaToAdd) {
        Album album = getById(id);

        mediaToAdd.forEach(album::addMedia);
        return repository.save(album);
    }
}
