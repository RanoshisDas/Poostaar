package com.ranoshisdas.poostaar.controller;

import com.ranoshisdas.poostaar.config.AppConstant;
import com.ranoshisdas.poostaar.payload.ApiResponse;
import com.ranoshisdas.poostaar.payload.PostDto;
import com.ranoshisdas.poostaar.payload.Response;
import com.ranoshisdas.poostaar.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("poostaar/")
public class PostController {
    @Autowired
    private PostService postService;

    //Create post
    @PostMapping("user/{userId}/{catId}/post")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer catId){
        PostDto createPost = this.postService.createPost(userId,catId,postDto);
        return new ResponseEntity<>(createPost, HttpStatus.CREATED);
    }

    //update posts
    @PutMapping("post/{postId}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,
                                              @PathVariable Integer postId){
        PostDto updatedPost=this.postService.updatePost(postDto,postId);
        return ResponseEntity.ok(updatedPost);
    }

    //get by id
    @GetMapping("post/{postId}")
    public ResponseEntity<PostDto>getPost(@PathVariable Integer postId){
        return ResponseEntity.ok(this.postService.getPostById(postId));
    }

    //Get all Posts
    @GetMapping("all-posts/")
    public ResponseEntity<Response> getPosts(
@RequestParam(value = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false)Integer pageNumber,
@RequestParam(value = "pageSize",defaultValue = AppConstant.PAGE_SIZE,required = false)Integer pageSize,
@RequestParam(value = "sortBy",defaultValue = AppConstant.SORT_BY,required = false)String sortBy,
@RequestParam(value = "sortDir",defaultValue = AppConstant.SORT_DIR,required = false)String sortDir){
        return ResponseEntity.ok(this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir));
    }

    //user's posts
    @GetMapping("user/{userId}/posts")
    public ResponseEntity<Response> getPostsByUser(@PathVariable Integer userId,
@RequestParam(value = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false)Integer pageNumber,
@RequestParam(value = "pageSize",defaultValue = AppConstant.PAGE_SIZE,required = false)Integer pageSize,
@RequestParam(value = "sortBy",defaultValue = AppConstant.SORT_BY,required = false)String sortBy,
@RequestParam(value = "sortDir",defaultValue = AppConstant.SORT_DIR,required = false)String sortDir){
        return ResponseEntity.ok(this.postService.getPostByUser(userId,pageNumber,pageSize,sortBy,sortDir));
    }

    //category posts
    @GetMapping("department/{catId}/posts")
    public ResponseEntity<Response> getPostsByCategory(@PathVariable Integer catId,
                                                       @RequestParam(value = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false)Integer pageNumber,
                                                       @RequestParam(value = "pageSize",defaultValue = AppConstant.PAGE_SIZE,required = false)Integer pageSize,
                                                       @RequestParam(value = "sortBy",defaultValue = AppConstant.SORT_BY,required = false)String sortBy,
                                                       @RequestParam(value = "sortDir",defaultValue = AppConstant.SORT_DIR,required = false)String sortDir){
        return ResponseEntity.ok(this.postService.getPostByCategory(catId,pageNumber,pageSize,sortBy,sortDir));
    }

    //Delete post
    @DeleteMapping("post/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post Deleted",true),HttpStatus.OK);
    }

    //Search Post
    @GetMapping("post/search/{keyword}")
    public ResponseEntity<Response> searchPostsByTitle(@PathVariable String keyword,
@RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber, 
@RequestParam(value = "pageSize",defaultValue = "10",required = false)Integer pageSize){
       return ResponseEntity.ok(this.postService.searchPost(keyword, pageNumber, pageSize));}

    /*
Upload Image
@PostMapping("post/image/{postId}")
    public ResponseEntity<PostDto> uploadImage(@RequestParam("image")MultipartFile image,
                                                     @PathVariable Integer postId)throws IOException {
    PostDto postDto = this.postService.getPostById(postId);
    String fileName=this.fileService.uploadImage(path,image);
    postDto.setImageName(fileName);
    return ResponseEntity.ok(this.postService.updatePost(postDto,postId));
}

//get Image
    @GetMapping(value = "post/image/{imageName}",produces=MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName,
                              HttpServletResponse response)throws IOException{

        InputStream resource=this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
*/
}
