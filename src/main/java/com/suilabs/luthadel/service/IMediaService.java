package com.suilabs.luthadel.service;

import com.suilabs.luthadel.model.Media;

import java.util.List;

public interface IMediaService {
    List<Media> findAll();
    Media getById(Long id);
    Media create(String name, String url);
    Media update(Long id, String name, String url);
    void delete(Long id);
}
