package com.suilabs.luthadel.controller;

import com.suilabs.luthadel.assembler.AlbumModelAssembler;
import com.suilabs.luthadel.model.Album;
import com.suilabs.luthadel.service.IAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {
    @Autowired
    private final IAlbumService albumService;

    private final AlbumModelAssembler assembler;

    AlbumController(IAlbumService albumService, AlbumModelAssembler assembler) {
        this.albumService = albumService;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Album>> all() {
        List<Album> albumList = albumService.findAll();
        return assembler.toCollectionModel(albumList);
    }

    @GetMapping("/{id}")
    public EntityModel<Album> one(@PathVariable Long id) {
        Album album = albumService.getById(id);
        return assembler.toModel(album);
    }

    @PostMapping
    ResponseEntity<EntityModel<Album>> create(@RequestBody Album album) {
        Long newId = albumService.create(album).getId();

        Album newAlbum = albumService.getById(newId);

        return assembler.toCreatedModel(newAlbum);
    }

    @PutMapping("/{id}")
    EntityModel<Album> update(@RequestBody Album album, @PathVariable Long id) {
        return assembler.toModel(albumService.update(id, album));
    }

    @DeleteMapping("/{id}")
    public Album delete(@PathVariable Long id) {
        albumService.delete(id);
        return null;
    }
}
