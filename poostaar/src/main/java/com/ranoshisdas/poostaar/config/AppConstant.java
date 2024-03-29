package com.ranoshisdas.poostaar.config;

import com.ranoshisdas.poostaar.entity.Post;
import com.ranoshisdas.poostaar.payload.CategoryDto;
import com.ranoshisdas.poostaar.payload.PostDto;
import com.ranoshisdas.poostaar.payload.Response;
import com.ranoshisdas.poostaar.payload.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;

public class AppConstant {
    public static final String PAGE_NUMBER="0";
    public static final String PAGE_SIZE="10";
    public static final String SORT_BY="addedDate";
    public static final String SORT_DIR="dsc";
    public static final String ID="Id";
    public static final ModelMapper modelMapper = new ModelMapper();
    public static Response getResponse(Page<?> all, List<?> collect) {
        Response response=new Response();
        response.setContent(collect);
        response.setPageNumber(all.getNumber()+1);
        response.setPageSize(all.getSize());
        response.setTotalElement(all.getTotalElements());
        response.setTotalPages(all.getTotalPages());
        response.setLastPage(all.isLast());

        return response;
    }
    public static PostDto POST_TO_POSTDTO (Post post){
    return new PostDto(post.getPostId(),
                post.getAddedDate(), post.getTitle(),
                        post.getContent(), post.getImageData(),
                        AppConstant.modelMapper.map(post.getUser(),UserDto .class),
            AppConstant.modelMapper.map(post.getCategory(), CategoryDto .class));}

}
