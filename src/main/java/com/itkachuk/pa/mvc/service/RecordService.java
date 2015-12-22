package com.itkachuk.pa.mvc.service;

import com.itkachuk.pa.mvc.dao.RecordRepository;
import com.itkachuk.pa.mvc.model.RecordEntity;
import com.itkachuk.pa.mvc.utils.DateUtils;
import com.itkachuk.pa.mvc.utils.TimeRange;
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
    public List<RecordEntity> getRecordsList(String userName) {
        return recordRepository.getRecordsList(userName);
    }

    @Transactional
    public List<RecordEntity> getRecordsListByAccountId(String userName, int accountId, int rowLimit) {
        return recordRepository.getRecordsListByAccountId(userName, accountId, rowLimit);
    }

    @Transactional
    public void createNewRecord(RecordEntity recordEntity) {
        recordRepository.createNewRecord(recordEntity);
    }


    // Calculation methods for Common report
    @Transactional
    public long[] calculateAmounts(int accountId) {
        //Log.d(TAG, "Populate tables with amounts");
        double[] amounts = new double[10];
        long[] roundedAmounts = new long[10];

        TimeRange currentMonthInterval = DateUtils.getTimeRange(DateUtils.MONTH, false);
        TimeRange pastMonthInterval = DateUtils.getTimeRange(DateUtils.MONTH, true);

        // currentMonthExpenseValue =
        amounts[0] = recordRepository.getSumOfRecords(accountId, true, currentMonthInterval);
        // currentMonthIncomeValue =
        amounts[1] = recordRepository.getSumOfRecords(accountId, false, currentMonthInterval);

        //String pastMonthExpenseValue =
        amounts[2] = recordRepository.getSumOfRecords(accountId, true, pastMonthInterval);
        //String pastMonthIncomeValue =
        amounts[3] = recordRepository.getSumOfRecords(accountId, false, pastMonthInterval);

        //String currentMonthProfitValue =
        amounts[4] = amounts[1] - amounts[0];
        //String pastMonthProfitValue =
        amounts[5] = amounts[3] - amounts[2];


        // Profit for quarters
        TimeRange currentQuarterInterval = DateUtils.getTimeRange(DateUtils.QUARTER, false);
        TimeRange pastQuarterInterval = DateUtils.getTimeRange(DateUtils.QUARTER, true);
        Double currentQuarterExpenseValue = recordRepository.getSumOfRecords(accountId, true, currentQuarterInterval);
        Double currentQuarterIncomeValue = recordRepository.getSumOfRecords(accountId, false, currentQuarterInterval);
        Double pastQuarterExpenseValue = recordRepository.getSumOfRecords(accountId, true, pastQuarterInterval);
        Double pastQuarterIncomeValue = recordRepository.getSumOfRecords(accountId, false, pastQuarterInterval);
        //Double currentQuarterProfitValue =
        amounts[6] = currentQuarterIncomeValue - currentQuarterExpenseValue;
        //Double pastQuarterProfitValue =
        amounts[7] = pastQuarterIncomeValue - pastQuarterExpenseValue;


        // Profit for years
        TimeRange currentYearInterval = DateUtils.getTimeRange(DateUtils.YEAR, false);
        TimeRange pastYearInterval = DateUtils.getTimeRange(DateUtils.YEAR, true);
        Double currentYearExpenseValue = recordRepository.getSumOfRecords(accountId, true, currentYearInterval);
        Double currentYearIncomeValue = recordRepository.getSumOfRecords(accountId, false, currentYearInterval);
        Double pastYearExpenseValue = recordRepository.getSumOfRecords(accountId, true, pastYearInterval);
        Double pastYearIncomeValue = recordRepository.getSumOfRecords(accountId, false, pastYearInterval);
        //Double currentYearProfitValue =
        amounts[8] = currentYearIncomeValue - currentYearExpenseValue;
        //Double pastYearProfitValue =
        amounts[9] = pastYearIncomeValue - pastYearExpenseValue;

        // Round all results - trim rational part
        for (int i=0; i < amounts.length; i++) {
            roundedAmounts[i] = Math.round(amounts[i]);
        }

        return roundedAmounts;
    }
}
