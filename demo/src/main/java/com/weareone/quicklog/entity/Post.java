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
//@Setter(AccessLevel.PRIVATE)
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
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    private LocalDate createdAt;
    private String contents;
    private boolean isPublic;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostTag> postTags = new ArrayList<>();

    public Post(User user, String title, Category category,String contents, boolean isPublic) {
        if (user != null) {
            setUser(user);
        }
        this.title = title;
        if (category != null) {
            setCategory(category);
        }
        this.createdAt = LocalDate.now();
        this.contents = contents;
        this.isPublic = isPublic;
    }

    public void changePostInfo(Category category, String title, String contents, boolean isPublic) {
        if (category != null) {
            setCategory(category);
        }
        this.title = title;
        this.contents = contents;
        this.isPublic = isPublic;
    }

    public void setUser(User user) {
        this.user = user;
        user.getPosts().add(this);
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void addImage(Image image) {
        images.add(image);
        image.setPost(this);
    }

}
