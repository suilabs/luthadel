package com.suilabs.luthadel.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.net.URI;
import java.util.Objects;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Table(name="media")
public class Media implements Serializable {
    @Id
    @SequenceGenerator(name="media_generator", sequenceName = "media_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "media_seq")
    private Long id;

    private String name;
    private String url;
}
