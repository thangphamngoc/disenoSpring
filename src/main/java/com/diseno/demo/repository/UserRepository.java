package com.diseno.demo.repository;

import com.diseno.demo.entity.user.MaUser;
import com.diseno.demo.repository.repositoryCustom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * date 2021-03-24 14:54
 *
 * @author Phạm Ngọc Thắng
 */
public interface UserRepository extends JpaRepository<MaUser, Long>, UserRepositoryCustom {

    Optional<MaUser> findByUsername(String username);
}
