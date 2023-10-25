package com.weareone.quicklog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profile_image")
public class ProfileImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profile_image_id;
    @Column(nullable = false)
    private String img_name;
    @Column(nullable = false)
    private String img_url;
    @Column(nullable = false)
    private String origin_name;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
}
