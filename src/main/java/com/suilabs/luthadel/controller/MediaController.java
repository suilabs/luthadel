package com.suilabs.luthadel.controller;

import com.suilabs.luthadel.assembler.MediaModelAssembler;
import com.suilabs.luthadel.exceptions.MediaNotFoundException;
import com.suilabs.luthadel.model.Media;
import com.suilabs.luthadel.service.IMediaService;
import com.suilabs.luthadel.service.MediaService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/media")
public class MediaController {
    @Autowired
    private final IMediaService mediaService;

    private final MediaModelAssembler assembler;

    MediaController(MediaService mediaService, MediaModelAssembler assembler) {
        this.mediaService = mediaService;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Media>> all()  {
        List<Media> mediaList = mediaService.findAll();
        return assembler.toCollectionModel(mediaList);
    }

    @GetMapping("/{id}")
    public EntityModel<Media> one(@PathVariable Long id) {
        Media media = mediaService.getById(id);
        return assembler.toModel(media);
    }

    @PostMapping
    EntityModel<Media> create(@RequestBody JSONObject body) {
        String name = body.getAsString("name");
        String url = body.getAsString("url");

        return assembler.toModel(mediaService.create(name, url));
    }

    @PutMapping("/{id}")
    EntityModel<Media> update(@RequestBody JSONObject body, @PathVariable Long id) {
        String name = body.getAsString("name");
        String url = body.getAsString("url");

        return assembler.toModel(mediaService.update(id, name, url));
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        mediaService.delete(id);
    }
}