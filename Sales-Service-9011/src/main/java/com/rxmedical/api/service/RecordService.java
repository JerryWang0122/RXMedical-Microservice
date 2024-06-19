package com.rxmedical.api.service;

import com.rxmedical.api.model.po.Record;
import com.rxmedical.api.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecordService {

    @Autowired
    private RecordRepository recordRepository;

    public Record findRecordById(Integer recordId) {
        Optional<Record> optionalRecord = recordRepository.findById(recordId);
        return optionalRecord.orElse(null);
    }

    public Record saveRecord(Record record) {
        return recordRepository.save(record);
    }

    public List<Record> findByStatus(String status) {
        return recordRepository.findByStatus(status);
    }

    public boolean isCodeExist(String code) {
        return recordRepository.existsByCode(code);
    }
}
