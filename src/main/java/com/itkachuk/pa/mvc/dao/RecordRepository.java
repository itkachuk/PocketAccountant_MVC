package com.itkachuk.pa.mvc.dao;

import com.itkachuk.pa.mvc.model.RecordEntity;
import com.itkachuk.pa.mvc.utils.DateUtils;
import com.itkachuk.pa.mvc.utils.TimeRange;
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

    public List<RecordEntity> getRecordsList() {
        return entityManager.createQuery("from RecordEntity").getResultList();
    }

    public List<RecordEntity> getRecordsListByAccountId(int accountId, int rowLimit) {
        return entityManager.createQuery(
                "from RecordEntity R " +
                        "where R.account.id = " + accountId +
                        "order by R.timestamp desc").setMaxResults(rowLimit).getResultList();
    }

    public RecordEntity getRecordById(int id) {
        return entityManager.find(RecordEntity.class, id);
    }

    public void createNewRecord(RecordEntity recordEntity) {
        entityManager.persist(recordEntity);
    }

    public void updateRecord(RecordEntity recordEntity) {
        entityManager.merge(recordEntity);
    }

    public void deleteRecord(RecordEntity recordEntity) {
        entityManager.remove(recordEntity);
    }

    // Calculation methods
    public Double getSumOfRecords(int accountFilter, boolean isExpense, TimeRange timeRange)  {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("select sum(R.amount) from RecordEntity R where R.isExpense = ");
        if (isExpense) queryBuilder.append("1");
        else queryBuilder.append("0");
        if (accountFilter != -1)
            queryBuilder.append(" and R.account.id = ").append(accountFilter);
        //queryBuilder.append(" and R.isPlanned = 0");
        if (timeRange != null && ((timeRange.getStartTime() > DateUtils.DEFAULT_START_DATE) || (timeRange.getEndTime() < DateUtils.DEFAULT_END_DATE)))
            queryBuilder.append(" and R.timestamp >= ").append(timeRange.getStartTime()).append(" and R.timestamp < ").append(timeRange.getEndTime());

        Double result = (Double) entityManager.createQuery(queryBuilder.toString()).getSingleResult();
        return result != null ? result : 0;
        //Log.d(TAG, "getSumOfRecords query = " + queryBuilder.toString());
    }
}
