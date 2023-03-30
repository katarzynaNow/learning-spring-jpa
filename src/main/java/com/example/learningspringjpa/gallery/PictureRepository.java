package com.example.learningspringjpa.gallery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface PictureRepository extends JpaRepository<Picture, String> {

    List<Picture> findAllByMimeType(String mimeType);

    @Query("select p.mimeType from Picture p")
    Set<String> mimeTypes();
}
