package com.ranoshisdas.poostaar.service;

import com.ranoshisdas.poostaar.payload.PostDto;
import com.ranoshisdas.poostaar.payload.Response;

public interface PostService {
    PostDto createPost(Integer userId, Integer catId, PostDto postDto);
    PostDto updatePost(PostDto postDto,Integer postId);
    PostDto getPostById(Integer postId);
    Response getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);
    Response getPostByUser(Integer userId,Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
    Response getPostByCategory(Integer catId,Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
    void deletePost(Integer postId);
    Response searchPost(String keyword, Integer pageNumber, Integer pageSize);
}
