package com.springbootproject.learning.repository;

import com.springbootproject.learning.model.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo, Integer> {

    UserInfo findByEmail(String email);
}
