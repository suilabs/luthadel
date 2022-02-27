package com.suilabs.luthadel.service;

import com.suilabs.luthadel.model.Album;
import com.suilabs.luthadel.model.Media;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IAlbumService {
    List<Album> findAll();
    Album getById(Long id);
    Album create(Album album);
    Album update(Long id, Album album);
    void delete(Long id);

    Album addMedia(Long id, List<Media> mediaToAdd);
}