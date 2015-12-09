package com.itkachuk.pa.mvc.dao;

import com.itkachuk.pa.mvc.model.RecordEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Ваня on 21.11.2015.
 */
@Repository
public class RecordRepository {

    @PersistenceContext(name = "PAPersistenceUnit")
    private EntityManager entityManager;

    @Transactional
    public List<RecordEntity> getRecordsList() {
        return entityManager.createQuery("from RecordEntity").getResultList();
    }

    @Transactional
    public List<RecordEntity> getRecordsListByAccountId(int accountId) {
        return entityManager.createQuery("from RecordEntity R where R.account.id = " + accountId).getResultList();
    }

    @Transactional
    public RecordEntity getRecordById(int id) {
        return entityManager.find(RecordEntity.class, id);
    }

    @Transactional
    public void createNewRecord(RecordEntity recordEntity) {
        entityManager.persist(recordEntity);
    }

    @Transactional
    public void updateRecord(RecordEntity recordEntity) {
        entityManager.merge(recordEntity);
    }

    @Transactional
    public void deleteRecord(RecordEntity recordEntity) {
        entityManager.remove(recordEntity);
    }


}
