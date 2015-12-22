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

    public List<AccountEntity> getAccountsList(String username) {
        return entityManager.createQuery("from AccountEntity" + (username != null ? " A where A.user.username = '" + username + "'" : "")).getResultList();
    }

    public AccountEntity getAccountById(int id) {
        return entityManager.find(AccountEntity.class, id);
    }

    public AccountEntity getAccountByName(String accountName, String username) {
        return (AccountEntity) entityManager.createQuery("from AccountEntity A where A.name = '" + accountName + "'" + (username != null ? " AND A.user.username = '" + username + "'" : "")).getSingleResult();
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
