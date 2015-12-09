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

    @Transactional
    public List<AccountEntity> getAccountsList() {
        return entityManager.createQuery("from AccountEntity").getResultList();
    }

    @Transactional
    public AccountEntity getAccountById(int id) {
        return entityManager.find(AccountEntity.class, id);
    }

    @Transactional
    public AccountEntity getAccountByName(String name) {
        return (AccountEntity) entityManager.createQuery("from AccountEntity A where A.name = " + name).getSingleResult();
    }

    @Transactional
    public void createNewAccount(AccountEntity accountEntity) {
        entityManager.persist(accountEntity);
    }

    @Transactional
    public void updateAccount(AccountEntity accountEntity) {
        entityManager.merge(accountEntity);
    }

    @Transactional
    public void deleteAccount(AccountEntity accountEntity) {
        entityManager.remove(accountEntity);
    }
}
