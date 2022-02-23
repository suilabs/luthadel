package com.suilabs.luthadel.assembler;

import com.suilabs.luthadel.controller.MediaController;
import com.suilabs.luthadel.model.Media;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MediaModelAssembler implements RepresentationModelAssembler<Media, EntityModel<Media>> {
    @Override
    public EntityModel<Media> toModel(Media media) {
        return EntityModel.of(media,
                linkTo(methodOn(MediaController.class).one(media.getId())).withSelfRel(),
                linkTo(methodOn(MediaController.class).all()).withRel("all"));
    }

    public CollectionModel<EntityModel<Media>> toCollectionModel(List<? extends Media> media) {
        List<EntityModel<Media>> mediaList = media.stream().map(this::toModel).toList();
        return CollectionModel.of(mediaList,
                linkTo(methodOn(MediaController.class).all()).withSelfRel());
    }
}
