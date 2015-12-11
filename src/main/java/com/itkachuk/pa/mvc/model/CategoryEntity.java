package com.itkachuk.pa.mvc.model;

import javax.persistence.*;

/**
 * Created by Ваня on 21.11.2015.
 */
@Entity
@Table(name = "category", schema = "", catalog = "")
public class CategoryEntity {
    private String name;
    private Integer isExpense;
    private Integer id;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "isExpense")
    public Integer getIsExpense() {
        return isExpense;
    }

    public void setIsExpense(Integer isExpense) {
        this.isExpense = isExpense;
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryEntity that = (CategoryEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (isExpense != null ? !isExpense.equals(that.isExpense) : that.isExpense != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (isExpense != null ? isExpense.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
