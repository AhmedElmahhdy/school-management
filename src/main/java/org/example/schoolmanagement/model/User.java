package org.example.schoolmanagement.model;

import lombok.*;
import org.example.schoolmanagement.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Data
@Builder
public class User implements UserDetails {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private Boolean isActive;
    private Role role;

    public User() {

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_" + this.getRole().name());
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
