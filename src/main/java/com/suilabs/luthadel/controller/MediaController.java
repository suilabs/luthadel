package com.suilabs.luthadel.controller;

import com.suilabs.luthadel.dtos.MediaDTO;
import com.suilabs.luthadel.model.Media;
import com.suilabs.luthadel.service.IMediaService;
import com.suilabs.luthadel.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/media")
public class MediaController {
    @Autowired
    private final IMediaService mediaService;

    MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<MediaDTO>> all() {
        CollectionModel<MediaDTO> media = mediaService.findAll();
        if (media == null) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(media);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MediaDTO> one(@PathVariable Long id) {
        MediaDTO media = mediaService.getById(id);
        if (media == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(media);
    }

    @PostMapping
    ResponseEntity<MediaDTO> create(@RequestBody Media media) {
        MediaDTO mediaDTO = mediaService.create(media);
        return ResponseEntity.created(mediaDTO.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(mediaDTO);
    }

    @PutMapping("/{id}")
    ResponseEntity<MediaDTO> update(@RequestBody Media media, @PathVariable Long id) {
        MediaDTO mediaDTO = mediaService.update(id, media);

        if (mediaDTO == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(mediaDTO);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        mediaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}