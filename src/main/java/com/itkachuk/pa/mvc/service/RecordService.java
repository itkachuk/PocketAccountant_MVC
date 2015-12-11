package com.itkachuk.pa.mvc.service;

import com.itkachuk.pa.mvc.dao.RecordRepository;
import com.itkachuk.pa.mvc.model.RecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ваня on 21.11.2015.
 */
@Service
public class RecordService {

    @Autowired
    private RecordRepository recordRepository;

    @Transactional
    public List<RecordEntity> getRecordsList() {
        return recordRepository.getRecordsList();
    }

    @Transactional
    public List<RecordEntity> getRecordsListByAccountId(int accountId) {
        return recordRepository.getRecordsListByAccountId(accountId);
    }

    @Transactional
    public void createNewRecord(RecordEntity recordEntity) {
        recordRepository.createNewRecord(recordEntity);
    }
}
