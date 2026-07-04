package com.tracking.api_tracking;

import com.tracking.api_tracking.models.Users;
import com.tracking.api_tracking.repository.UserRepository;
import com.tracking.api_tracking.response.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Unit {
    @Autowired
    private UserRepository userRepository;
    public Users auth()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalArgumentException("Unauthorized: Bạn chưa đăng nhập.");
        }

        Object principal = authentication.getPrincipal();
        Users auth = null;

        // Kiểm tra xem principal là object hay là chuỗi String
        if (principal instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) principal;
            auth = userRepository.findById(userDetails.getId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
        } else if (principal instanceof String) {
            String username = (String) principal;
            if ("anonymousUser".equals(username)) {
                throw new IllegalArgumentException("Unauthorized: Không có quyền truy cập.");
            }
            // Gọi database tìm User dựa trên username
            auth = userRepository.findByUserName(username)
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
        } else {
            throw new IllegalArgumentException("Không thể xác định thông tin người dùng.");
        }
        return auth;
    }
}
