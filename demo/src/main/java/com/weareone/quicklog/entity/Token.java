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
        @Id
        @Column(nullable = false)
        private String refreshToken;

}
