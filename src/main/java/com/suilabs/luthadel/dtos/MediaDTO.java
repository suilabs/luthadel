package com.suilabs.luthadel.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "media")
@Data
@AllArgsConstructor
public class MediaDTO extends RepresentationModel<MediaDTO> {
    private String name;
    private String url;
}
