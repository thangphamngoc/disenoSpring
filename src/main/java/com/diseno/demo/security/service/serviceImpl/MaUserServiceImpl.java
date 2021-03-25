package com.diseno.demo.security.service.serviceImpl;

import com.diseno.demo.common.ErrorsConstant;
import com.diseno.demo.entity.user.MaUser;
import com.diseno.demo.repository.UserRepository;
import com.diseno.demo.security.model.UserPrincipal;
import com.diseno.demo.security.service.MaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * date 2021-03-24 15:02
 *
 * @author Phạm Ngọc Thắng
 */
@Service
public class MaUserServiceImpl implements MaUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        MaUser maUser = userRepository.findByUsername(username).orElseThrow();
        if (maUser == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsConstant.ERROR_NO_USER);
        }
        return new UserPrincipal(maUser);
    }
}
