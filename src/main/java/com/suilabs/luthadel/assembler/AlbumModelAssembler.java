package com.suilabs.luthadel.assembler;

import com.suilabs.luthadel.controller.AlbumController;
import com.suilabs.luthadel.model.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AlbumModelAssembler implements RepresentationModelAssembler<Album, EntityModel<Album>> {
    @Autowired
    MediaModelAssembler mediaModelAssembler;

    @Override
    public EntityModel<Album> toModel(Album album) {
        return EntityModel.of(album,
                linkTo(methodOn(AlbumController.class).one(album.getId())).withSelfRel(),
                linkTo(methodOn(AlbumController.class).all()).withRel("all"));
    }

    public CollectionModel<EntityModel<Album>> toCollectionModel(List<? extends Album> albums) {
        List<EntityModel<Album>> albumList = albums.stream().map(this::toModel).toList();
        return CollectionModel.of(albumList,
                linkTo(methodOn(AlbumController.class).all()).withSelfRel());
    }

    public ResponseEntity<EntityModel<Album>> toCreatedModel(Album album) {
        return ResponseEntity.created(linkTo(methodOn(AlbumController.class).one(album.getId())).toUri())
                .body(toModel(album));
    }
}
