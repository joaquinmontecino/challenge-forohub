package com.alura.forohub.domain.models;

import com.alura.forohub.domain.dto.user.UserCreationDTO;
import com.alura.forohub.domain.dto.user.UserUpdateDTO;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "user")
@Entity(name = "User")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Boolean active;

    public User(UserCreationDTO userCreationDTO, String encodedPassword) {
        this.name = userCreationDTO.name();
        this.email = userCreationDTO.email();
        this.password = encodedPassword;
        this.role = Role.USER;
        this.active = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    public void updateUserData(UserUpdateDTO userUpdateDTO, String encodedPassword) {
        if (userUpdateDTO.password() != null) this.password = encodedPassword;
        if (userUpdateDTO.name() != null) this.name = userUpdateDTO.name();
        if (userUpdateDTO.role() != null) this.role = userUpdateDTO.role();

    }

    public void removeUser() {
        this.active = false;
    }
}
