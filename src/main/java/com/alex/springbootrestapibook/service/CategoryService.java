package com.alex.springbootrestapibook.service;

import com.alex.springbootrestapibook.entity.Category;
import com.alex.springbootrestapibook.model.CategoryModel;
import com.alex.springbootrestapibook.repository.CategroyRepo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryService {
    @Autowired
    private CategroyRepo categroyRepo;
    private ModelMapper mapper;

    public void saveCategory(CategoryModel categoryModel) {
        Category category = mapper.map(categoryModel, Category.class);
        categroyRepo.save(category);
    }

    public List<CategoryModel> getAllCategory() {
        List<Category> categories = categroyRepo.findAll();
        List<CategoryModel> result = categories.stream().map(this::convertCategoryToCategoryModel).collect(Collectors.toList());
        return result;
    }

    public CategoryModel getCategoryById(Long id) {
        CategoryModel categoryModel = mapper.map(categroyRepo.findById(id), CategoryModel.class);
        return categoryModel;
    }


    private CategoryModel convertCategoryToCategoryModel(Category category) {
        return mapper.map(category, CategoryModel.class);
    }
}
