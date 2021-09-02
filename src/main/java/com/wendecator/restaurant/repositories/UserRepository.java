package com.wendecator.restaurant.repositories;

import com.wendecator.restaurant.models.ApiUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<ApiUser, Long> {
    ApiUser findByUsername(String username);
}
