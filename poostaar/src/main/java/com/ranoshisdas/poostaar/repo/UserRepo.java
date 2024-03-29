package com.ranoshisdas.poostaar.repo;

import com.ranoshisdas.poostaar.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
}
