package com.rxmedical.api.service;

import com.rxmedical.api.model.dto.GetProductHistoryDto;
import com.rxmedical.api.model.po.History;
import com.rxmedical.api.model.po.Record;
import com.rxmedical.api.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    public History findHistoryById(Integer historyId) {
        Optional<History> optionalHistory = historyRepository.findById(historyId);
        return optionalHistory.orElse(null);
    }

    public boolean isAllItemTaken(Record record) {
        return historyRepository.countByRecordAndUserIsNull(record) != 0;
    }

    public List<History> findRecordDetails(Record record) {
        return historyRepository.findByRecord(record);
    }

    public History saveHistory(History history) {
        return historyRepository.save(history);
    }

    public Integer getApplyItemsCount(Record record) {
        return historyRepository.countByRecord(record);
    }

    public List<History> getProductHistoryAfter(GetProductHistoryDto dto) {
        return historyRepository.findByProductAndUpdateDateAfter(dto.product(), dto.date());
    }
}
