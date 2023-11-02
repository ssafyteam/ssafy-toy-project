package com.weareone.quicklog.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_image_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
    private String imageName;
    private String imageUrl;
    private String originName;

    public void setPost(Post post) {
        this.post = post;
    }

    public Image(String imageName, String imageUrl, String originName) {
        this.imageName = imageName;
        this.imageUrl = imageUrl;
        this.originName = originName;
    }
}
