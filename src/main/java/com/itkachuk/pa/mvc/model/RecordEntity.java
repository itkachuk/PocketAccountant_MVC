package com.itkachuk.pa.mvc.model;

import javax.persistence.*;

/**
 * Created by Ваня on 21.11.2015.
 */
@Entity
@Table(name = "incomeorexpenserecord", schema = "", catalog = "")
public class RecordEntity {
    private AccountEntity account;
    private String description;
    private CategoryEntity category;
    private String date;
    private Double amount;
    private Long timestamp;
    private Integer isExpense;
    private Integer isPlanned;
    private Integer isRegular;
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "account")
    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    @JoinColumn(name = "category")
    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    @Basic
    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "amount")
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "timestamp")
    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Basic
    @Column(name = "isExpense")
    public Integer getIsExpense() {
        return isExpense;
    }

    public void setIsExpense(Integer isExpense) {
        this.isExpense = isExpense;
    }

    @Basic
    @Column(name = "isPlanned")
    public Integer getIsPlanned() {
        return isPlanned;
    }

    public void setIsPlanned(Integer isPlanned) {
        this.isPlanned = isPlanned;
    }

    @Basic
    @Column(name = "isRegular")
    public Integer getIsRegular() {
        return isRegular;
    }

    public void setIsRegular(Integer isRegular) {
        this.isRegular = isRegular;
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

        RecordEntity that = (RecordEntity) o;

        if (account != null ? !account.equals(that.account) : that.account != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (isExpense != null ? !isExpense.equals(that.isExpense) : that.isExpense != null) return false;
        if (isPlanned != null ? !isPlanned.equals(that.isPlanned) : that.isPlanned != null) return false;
        if (isRegular != null ? !isRegular.equals(that.isRegular) : that.isRegular != null) return false;
        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = account != null ? account.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (isExpense != null ? isExpense.hashCode() : 0);
        result = 31 * result + (isPlanned != null ? isPlanned.hashCode() : 0);
        result = 31 * result + (isRegular != null ? isRegular.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
