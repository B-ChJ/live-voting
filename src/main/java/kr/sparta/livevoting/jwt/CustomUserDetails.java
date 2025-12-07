package kr.sparta.livevoting.jwt;

import kr.sparta.livevoting.entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private final String loginId;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(Users user) {
        this.loginId = user.getLoginId();
        this.password = user.getPassword();
        this.authorities = List.of(new SimpleGrantedAuthority(String.valueOf(user.getRole())));
    }

    public String getLoginId() {
        return loginId;
    }

    @Override // 상위 클래스, 또는 인터페이스의 메서드를 재정의한다.
    public String getUsername() {
        return loginId;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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