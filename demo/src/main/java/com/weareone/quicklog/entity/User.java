package com.weareone.quicklog.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;
    private String password;
    private LocalDate birth;
    private String name;
    private String nickName;

    @ElementCollection(fetch = FetchType.EAGER) //roles 컬렉션
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

//    public User(String email, String password, LocalDate birth, String name, String nickName) {
//        this.email = email;
//        this.password = password;
//        this.birth = birth;
//        this.name = name;
//        this.nickName = nickName;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
