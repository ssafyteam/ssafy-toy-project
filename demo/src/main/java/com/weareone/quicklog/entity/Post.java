package com.weareone.quicklog.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private String title;
    private String category;
    private LocalDate createdAt;
    private String contents;
    private boolean isPublic;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostTag> postTags = new ArrayList<>();

    public Post(User user, String title, String category, String contents, boolean isPublic) {
        setUser(user);
        this.title = title;
        this.category = category;
        this.createdAt = LocalDate.now();
        this.contents = contents;
        this.isPublic = isPublic;
    }

    public void changePostInfo(String title, String category, String contents, boolean isPublic) {
        this.title = title;
        this.category = category;
        this.createdAt = LocalDate.now();
        this.contents = contents;
        this.isPublic = isPublic;
    }

    public void setUser(User user) {
        this.user = user;
        user.getPosts().add(this);
    }

    public void addImage(Image image) {
        images.add(image);
        image.setPost(this);
    }

    public void addTag(PostTag postTag) {
        postTags.add(postTag);
        postTag.setPost(this);
    }
}
