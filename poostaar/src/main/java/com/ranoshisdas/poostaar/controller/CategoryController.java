package com.ranoshisdas.poostaar.controller;

import com.ranoshisdas.poostaar.config.AppConstant;
import com.ranoshisdas.poostaar.payload.ApiResponse;
import com.ranoshisdas.poostaar.payload.CategoryDto;
import com.ranoshisdas.poostaar.payload.Response;
import com.ranoshisdas.poostaar.service.implimentation.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("poostaar/department")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory
            (@Valid@RequestBody CategoryDto categoryDto){
        CategoryDto newCategory = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{catId}")
    public  ResponseEntity<CategoryDto> updateCategory
            (@Valid@RequestBody CategoryDto categoryDto, @PathVariable Integer catId){
        CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, catId);
        return ResponseEntity.ok(updatedCategory);
    }

    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getById(@PathVariable Integer catId){
        return ResponseEntity.ok(this.categoryService.getCategory(catId));
    }

    @GetMapping("/")
    public ResponseEntity<Response> getAllCategory(
            @RequestParam(value = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false)Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AppConstant.PAGE_SIZE,required = false)Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "categoryDescription",required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstant.SORT_DIR,required = false)String sortDir){
        return ResponseEntity.ok(this.categoryService.getAllCategory(pageNumber,pageSize,sortBy,sortDir));
    }
    @DeleteMapping("/{catId}")
    public  ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){
        this.categoryService.deleteCategory(catId);
        return new ResponseEntity<>(new ApiResponse("Category Deleted",true),HttpStatus.OK);
    }
}
