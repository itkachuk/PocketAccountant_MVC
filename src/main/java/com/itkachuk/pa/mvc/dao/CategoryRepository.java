package com.itkachuk.pa.mvc.dao;

import com.itkachuk.pa.mvc.model.CategoryEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Ваня on 09.12.2015.
 */
@Repository
public class CategoryRepository {
    @PersistenceContext(name = "PAPersistenceUnit")
    private EntityManager entityManager;

    public List<CategoryEntity> getCategoriesList() {
        return entityManager.createQuery("from CategoryEntity").getResultList();
    }

    public CategoryEntity getCategoryById(int id) {
        return entityManager.find(CategoryEntity.class, id);
    }

    public void createNewCategory(CategoryEntity categoryEntity) {
        entityManager.persist(categoryEntity);
    }

    public void updateCategory(CategoryEntity categoryEntity) {
        entityManager.merge(categoryEntity);
    }

    public void deleteCategory(CategoryEntity categoryEntity) {
        entityManager.remove(categoryEntity);
    }
}
