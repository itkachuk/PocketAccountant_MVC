package com.itkachuk.pa.mvc.converter;

import com.itkachuk.pa.mvc.model.CategoryEntity;
import com.itkachuk.pa.mvc.service.CategoryService;

import java.beans.PropertyEditorSupport;

/**
 * Created with IntelliJ IDEA.
 * User: itkachuk
 * Date: 12/11/2015 1:16 PM
 */
public class CategoryEditor extends PropertyEditorSupport {

    private CategoryService categoryService;

    public CategoryEditor(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Converts a String ID to a CategoryEntity (when submitting form)
    @Override
    public void setAsText(String id) {
        CategoryEntity categoryEntity = categoryService.getCategoryById(Integer.parseInt(id));
        this.setValue(categoryEntity);
    }
}
