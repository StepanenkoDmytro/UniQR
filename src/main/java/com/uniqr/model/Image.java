package com.uniqr.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "images")
@Data
public class Image {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "image_name")
    private String name;
    @Column(name = "image_origin_file_name")
    private String originFileName;
    @Column(name = "image_size")
    private Long size;
    @Column(name = "image_content_type")
    private String contentType;
    @Column(name = "bytes")
    private byte[] bytes;
    @OneToOne(cascade = CascadeType.REFRESH, mappedBy = "image")
    private Session session;

    public Image() {
        this.id = UUID.randomUUID().toString();
    }
}
