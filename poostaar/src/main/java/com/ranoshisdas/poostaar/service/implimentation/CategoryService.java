package com.ranoshisdas.poostaar.service.implimentation;

import com.ranoshisdas.poostaar.config.AppConstant;
import com.ranoshisdas.poostaar.entity.Category;
import com.ranoshisdas.poostaar.exception.ResourceNotFoundException;
import com.ranoshisdas.poostaar.payload.CategoryDto;
import com.ranoshisdas.poostaar.payload.Response;
import com.ranoshisdas.poostaar.repo.CategoryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    private ModelMapper modelMapper=new ModelMapper();
    private Response getCategoryResponse(Page<Category> all, List<CategoryDto> collect) {
        Response Response=new Response();
        Response.setContent(collect);
        Response.setPageNumber(all.getNumber()+1);
        Response.setPageSize(all.getSize());
        Response.setTotalElement(all.getTotalElements());
        Response.setTotalPages(all.getTotalPages());
        Response.setLastPage(all.isLast());

        return Response;
    }
    //Create category

    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category map = this.modelMapper.map(categoryDto, Category.class);
        Category save=this.categoryRepo.save(map);
        return this.modelMapper.map(save, CategoryDto.class);
    }

    //Update category

    public CategoryDto updateCategory(CategoryDto categoryDto, Integer catId) {
        Category category = this.categoryRepo.findById(catId).
                orElseThrow(() -> new ResourceNotFoundException("Department",AppConstant.ID, catId));
        category.setCourse(categoryDto.getCourse());
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category save=this.categoryRepo.save(category);
        return this.modelMapper.map(save, CategoryDto.class);
    }

    //Get category

    public CategoryDto getCategory(Integer catId) {
        Category category = this.categoryRepo.findById(catId).
                orElseThrow(() -> new ResourceNotFoundException("Department",AppConstant.ID, catId));
        return this.modelMapper.map(category, CategoryDto.class);
    }

    //Get all category
    public Response getAllCategory(Integer pageNumber, Integer pageSize,
                                   String sortBy, String sortDir)
    {
Sort sort= !sortDir.equalsIgnoreCase(AppConstant.SORT_DIR)?
        Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable p= PageRequest.of(pageNumber,pageSize,sort);
        Page<Category> all =  this.categoryRepo.findAll(p);
        List<Category> allCat=all.getContent();

        List<CategoryDto> collect = allCat.stream().map((cat) -> modelMapper.
                map(cat, CategoryDto.class)).collect(Collectors.toList());
        return getCategoryResponse(all,collect);
    }

    //Delete category
    public void deleteCategory(Integer catID) {
        Category cat = this.categoryRepo.findById(catID).
                orElseThrow(() -> new ResourceNotFoundException("Category",AppConstant.ID, catID));
        this.categoryRepo.delete(cat);
    }
}
