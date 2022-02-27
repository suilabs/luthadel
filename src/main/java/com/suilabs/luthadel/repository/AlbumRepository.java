package com.suilabs.luthadel.repository;

import com.suilabs.luthadel.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
}
