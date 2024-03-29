package com.ranoshisdas.poostaar.repo;

import com.ranoshisdas.poostaar.entity.Category;
import com.ranoshisdas.poostaar.entity.Post;
import com.ranoshisdas.poostaar.entity.User;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post,Integer> {
    Page<Post> findByCategory(Pageable pageable,Category category);
    Page<Post> findByUser(Pageable pageable, User user);

  Page<Post> findByTitleContaining (Pageable pageable,String title);



}
