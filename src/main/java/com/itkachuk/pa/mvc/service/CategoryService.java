package com.itkachuk.pa.mvc.service;

import com.itkachuk.pa.mvc.dao.CategoryRepository;
import com.itkachuk.pa.mvc.model.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ваня on 10.12.2015.
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public List<CategoryEntity> getCategoriesList() {
        return categoryRepository.getCategoriesList();
    }

    @Transactional
    public CategoryEntity getCategoryById(int id) {
        return categoryRepository.getCategoryById(id);
    }
}
