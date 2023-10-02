package com.springboot.security.repository;

import com.springboot.security.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {
    Optional<UserInfo> findByName(String userName);

    public UserInfo save(UserInfo userInfo);
}
