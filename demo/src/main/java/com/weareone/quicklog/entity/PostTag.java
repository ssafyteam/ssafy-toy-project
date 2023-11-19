package com.weareone.quicklog.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class PostTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_tag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public void setPost(Post post) {
        this.post = post;
    }

    public void addPostAndTag(Post post, Tag tag) {
        this.post = post;
        this.tag = tag;
    }
}
