package com.diseno.demo.security.service;

import com.diseno.demo.security.model.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * date 2021-03-24 15:02
 *
 * @author Phạm Ngọc Thắng
 */
public interface MaUserService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);
}
