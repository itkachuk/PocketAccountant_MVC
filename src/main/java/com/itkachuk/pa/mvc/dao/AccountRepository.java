package com.itkachuk.pa.mvc.dao;

import com.itkachuk.pa.mvc.model.AccountEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Ваня on 09.12.2015.
 */
@Repository
public class AccountRepository {

    @PersistenceContext(name = "PAPersistenceUnit")
    private EntityManager entityManager;

    public List<AccountEntity> getAccountsList() {
        return entityManager.createQuery("from AccountEntity").getResultList();
    }

    public AccountEntity getAccountById(int id) {
        return entityManager.find(AccountEntity.class, id);
    }

    public AccountEntity getAccountByName(String name) {
        return (AccountEntity) entityManager.createQuery("from AccountEntity A where A.name = " + name).getSingleResult();
    }

    public void createNewAccount(AccountEntity accountEntity) {
        entityManager.persist(accountEntity);
    }

    public void updateAccount(AccountEntity accountEntity) {
        entityManager.merge(accountEntity);
    }

    public void deleteAccount(AccountEntity accountEntity) {
        entityManager.remove(accountEntity);
    }
}
