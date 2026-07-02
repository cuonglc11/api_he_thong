package com.tracking.api_tracking.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter  extends OncePerRequestFilter {
    @Autowired
    private JwtProvider jwtProvider;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       try {
         String jwt =  getJwtFromRequest(request);
         if(StringUtils.hasText(jwt) &&  jwtProvider.validateToken(jwt)) {
             // 1. Lấy username từ token
             String username  = jwtProvider.getClaims(jwt).getSubject();
             // 2. Lấy danh sách roles từ token
             List<String> roles = jwtProvider.getRolesFromToken(jwt);
             // 3. Chuyển đổi roles thành GrantedAuthority (Cực kỳ quan trọng: Thêm tiền tố ROLE_)
             List<SimpleGrantedAuthority> authorities = roles.stream()
                     .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                     .collect(Collectors.toList());
             // 4. Set thông tin người dùng vào SecurityContext
             UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username , null , authorities);
             authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

             SecurityContextHolder.getContext().setAuthentication(authentication);


         }
       } catch (Exception ex) {
           logger.error("Could not set user authentication in security context", ex);
       }
        filterChain.doFilter(request, response);
    }
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Cắt bỏ chữ "Bearer " để lấy token thuần
        }
        return null;
    }
}
