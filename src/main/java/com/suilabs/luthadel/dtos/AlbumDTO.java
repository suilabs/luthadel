package com.suilabs.luthadel.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

public class AlbumDTO extends RepresentationModel<AlbumDTO> {
    private String name;
    private String urlPath;
    private LocalDate date;
}
