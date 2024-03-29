package com.ranoshisdas.poostaar.service.implimentation;

import com.ranoshisdas.poostaar.config.AppConstant;
import com.ranoshisdas.poostaar.entity.Category;
import com.ranoshisdas.poostaar.entity.ImageData;
import com.ranoshisdas.poostaar.entity.Post;
import com.ranoshisdas.poostaar.entity.User;
import com.ranoshisdas.poostaar.exception.ResourceNotFoundException;
import com.ranoshisdas.poostaar.payload.CategoryDto;
import com.ranoshisdas.poostaar.payload.PostDto;
import com.ranoshisdas.poostaar.payload.Response;
import com.ranoshisdas.poostaar.payload.UserDto;
import com.ranoshisdas.poostaar.repo.CategoryRepo;
import com.ranoshisdas.poostaar.repo.PostRepo;
import com.ranoshisdas.poostaar.repo.UserRepo;
import com.ranoshisdas.poostaar.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    /////All Methode starts from here
    @Override
    public PostDto createPost(Integer userId, Integer catId, PostDto postDto){
        //Finding User and getting User
        User user = this.userRepo.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("User",AppConstant.ID, userId));
        //Finding Category and getting category
        Category category = this.categoryRepo.findById(catId).
                orElseThrow(() -> new ResourceNotFoundException("Department",AppConstant.ID, catId));
        //Set all Element in post form postDto
        Post mapped = AppConstant.modelMapper.map(postDto, Post.class);

        mapped.setUser(user);
        mapped.setCategory(category);
        //get time and set as string
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime now=LocalDateTime.now();
        mapped.setAddedDate(now.format(formatter));
        //save the post in db
        Post saved = this.postRepo.save(mapped);

        return AppConstant.modelMapper.map(saved, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {

        Post post = this.postRepo.findById(postId).
                orElseThrow(() -> new ResourceNotFoundException("Post",AppConstant.ID, postId));
post.setTitle(postDto.getTitle());
post.setContent(postDto.getContent());
//save the updated post
        Post saved = this.postRepo.save(post);
        return AppConstant.modelMapper.map(saved, PostDto.class);
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId).
                orElseThrow(() -> new ResourceNotFoundException("Post",AppConstant.ID, postId));

        return AppConstant.POST_TO_POSTDTO(post);
    }

    @Override
    public Response getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
 Sort sort= !sortDir.equalsIgnoreCase(AppConstant.SORT_DIR)?
         Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable p= PageRequest.of(pageNumber,pageSize, sort);
        Page<Post> all = this.postRepo.findAll(p);
        List<Post> posts=all.getContent();
        List<PostDto> collect = posts.stream().map((post) -> AppConstant.POST_TO_POSTDTO(post)).
                collect(Collectors.toList());
        return AppConstant.getResponse(all, collect);
    }

    @Override
    public Response getPostByUser(Integer userId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort= !sortDir.equalsIgnoreCase(AppConstant.SORT_DIR)?
                Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        User user = this.userRepo.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("User",AppConstant.ID, userId));
        Pageable p= PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> all= this.postRepo.findByUser(p,user);
        List<Post> posts=all.getContent();
        List<PostDto> collect = posts.stream().map((post) ->AppConstant.POST_TO_POSTDTO(post))
                .collect(Collectors.toList());

        return AppConstant.getResponse(all, collect);
    }


    @Override
    public Response getPostByCategory(Integer catId,Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
        Sort sort= !sortDir.equalsIgnoreCase(AppConstant.SORT_DIR)?
                Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Category category = this.categoryRepo.findById(catId).
                orElseThrow(() -> new ResourceNotFoundException("User",AppConstant.ID, catId));

        Pageable p= PageRequest.of(pageNumber,pageSize,sort);
        Page<Post>posts=this.postRepo.findByCategory(p,category);
        List<PostDto> collect = posts.stream().map((post) -> AppConstant.POST_TO_POSTDTO(post))
                .collect(Collectors.toList());
        return AppConstant.getResponse(posts, collect);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).
                orElseThrow(() -> new ResourceNotFoundException("Post",AppConstant.ID, postId));
this.postRepo.delete(post);
    }

    @Override
    public Response searchPost(String keyword,Integer pageNumber,Integer pageSize) {
        Pageable p= PageRequest.of(pageNumber,pageSize);
        Page<Post> posts = this.postRepo.findByTitleContaining(p, keyword);
        List<PostDto> collect = posts.stream().map((post)-> AppConstant.POST_TO_POSTDTO(post))
                .collect(Collectors.toList());
        return AppConstant.getResponse(posts,collect);
    }
}
