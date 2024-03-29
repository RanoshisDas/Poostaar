package com.ranoshisdas.poostaar.repo;

import com.ranoshisdas.poostaar.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
