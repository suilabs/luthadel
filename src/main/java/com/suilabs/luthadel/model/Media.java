package com.suilabs.luthadel.model;

import javax.persistence.*;
import java.net.URI;
import java.util.Objects;

@Entity
@Table(name="media")
public class Media {
    //TODO check how this can be autoincrement and primary key
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String url;

    public Media(){}

    public Media(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.url);
    }

    @Override
    public boolean equals(Object obj) {
        boolean nullOrDifferentType = obj == null
                || obj.getClass() != getClass();
        final Media other = (Media) obj;
        return !nullOrDifferentType
                && !Objects.equals(other.name, this.name)
                && !Objects.equals(other.url, this.url)
                && !Objects.equals(other.id, this.id);
    }

    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url=" + url +
                '}';
    }
}
