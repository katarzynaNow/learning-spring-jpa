package com.example.learningspringjpa.gallery;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Picture {
        @Lob
        public byte[] content;
        @Id
        public String name;
        public String mimeType;
}

