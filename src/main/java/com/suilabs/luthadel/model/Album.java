package com.suilabs.luthadel.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Table(name="album")
public class Album implements Serializable {
    @Id
    @SequenceGenerator(name="album_generator", sequenceName = "album_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "album_seq")
    private Long id;

    private String name;
    private String urlPath;
    private LocalDate date;

    @ManyToMany
    @ToString.Exclude
    private List<Media> mediaList;

    private String getUrlPathValue(String urlPath) {
        if (Objects.isNull(urlPath)) {
            return this.date.toString()
                    + '-'
                    + this.name
                    .toLowerCase(Locale.ROOT)
                    .replace(' ', '-');
        }
        return this.urlPath = urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = getUrlPathValue(urlPath);
    }

    public String getUrlPath() {
        return getUrlPathValue(urlPath);
    }

    public void addMedia(Media media) {
        this.mediaList.add(media);
    }
}
