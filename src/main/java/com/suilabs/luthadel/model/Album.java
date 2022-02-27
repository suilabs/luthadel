package com.suilabs.luthadel.model;

import javax.persistence.*;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name="album")
public class Album {
    @Id
    @GeneratedValue(generator = "album_seq")
    private Long id;

    private String name;
    private String urlPath;
    private LocalDate date;

    @ManyToMany
    private List<Media> mediaList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Media> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }

    public void addMedia(Media media) {
        this.mediaList.add(media);
    }

    @Override
    public int hashCode() { return Objects.hash(this.id, this.name, this.date, this.mediaList.hashCode()); }

    @Override
    public boolean equals(Object obj) {
        boolean nullOrDifferentType = obj == null
                || obj.getClass() != getClass();
        final Album other = (Album) obj;
        return !nullOrDifferentType
                && !Objects.equals(other.name, this.name)
                && !Objects.equals(other.date, this.date)
                && !Objects.equals(other.mediaList, this.mediaList);
    }

    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", name=" + name +
                ", media=" + mediaList +
                '}';
    }
}
