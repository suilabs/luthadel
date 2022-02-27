package com.suilabs.luthadel.assembler;

import com.suilabs.luthadel.controller.MediaController;
import com.suilabs.luthadel.dtos.MediaDTO;
import com.suilabs.luthadel.model.Media;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MediaModelAssembler implements RepresentationModelAssembler<Media, MediaDTO> {
    @Override
    public MediaDTO toModel(Media entity) {
        MediaDTO userDTO = new MediaDTO(entity.getName(), entity.getUrl());
        userDTO.add(linkTo(methodOn(MediaController.class).all()).withRel("all"));
        userDTO.add(linkTo(methodOn(MediaController.class).one(entity.getId())).withSelfRel());
        return userDTO;
    }
}
