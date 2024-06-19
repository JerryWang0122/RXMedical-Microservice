package com.rxmedical.api.controller;

import com.rxmedical.api.model.dto.GetProductHistoryDto;
import com.rxmedical.api.model.po.History;
import com.rxmedical.api.model.po.Record;
import com.rxmedical.api.model.response.ApiResponse;
import com.rxmedical.api.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tool/histories")
public class HistoryToolController {

    @Autowired
    private HistoryService historyService;

    @PostMapping
    public ResponseEntity<ApiResponse<List<History>>> getRecordDetails(@RequestBody Record record) {
        return ResponseEntity.ok(new ApiResponse<>(true, "歷史紀錄", historyService.findRecordDetails(record)));
    }

    @PostMapping("/count")
    public ResponseEntity<ApiResponse<Integer>> getApplyItemsCount(@RequestBody Record record) {
        return ResponseEntity.ok(new ApiResponse<>(true, "申請項目數量", historyService.getApplyItemsCount(record)));
    }

    @PutMapping("/save")
    public ResponseEntity<ApiResponse<Object>> saveHistory(@RequestBody History history) {
        return ResponseEntity.ok(new ApiResponse<>(true, "儲存成功", historyService.saveHistory(history)));
    }

    @PostMapping("/product")
    public ResponseEntity<ApiResponse<List<History>>> findProductHistoryAfter(@RequestBody GetProductHistoryDto dto) {
        return ResponseEntity.ok(new ApiResponse<>(true, "歷史紀錄", historyService.getProductHistoryAfter(dto)));
    }

}
