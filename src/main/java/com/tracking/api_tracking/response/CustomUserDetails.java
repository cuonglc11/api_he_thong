package com.tracking.api_tracking.response;

import com.tracking.api_tracking.models.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public static CustomUserDetails build(Users user) {
        Collection<? extends GrantedAuthority> authorities =
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRoles()));

        return new CustomUserDetails(
                user.getId(),
                user.getUserName(), // Đảm bảo Users có hàm getUserName()
                user.getPassword(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // Các hàm của UserDetails (isAccountNonExpired, isAccountNonLocked...)
    // nếu bạn dùng bản Java mới/Spring Security mới thì nó có default method là true.
    // Nếu bị báo đỏ bắt override, bạn hãy override và return true nhé.
}