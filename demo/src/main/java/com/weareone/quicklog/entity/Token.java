package com.weareone.quicklog.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "token")
public class Token {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long token_id;
//    @Column(nullable = false, unique = true)
//    private String refreshToken;
        @Id
        @Column(nullable = false)
        private String refreshToken;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id",nullable = false)
//    private User user;
}
