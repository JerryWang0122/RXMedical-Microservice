package com.rxmedical.api.controller;

import com.rxmedical.api.model.po.Record;
import com.rxmedical.api.model.po.User;
import com.rxmedical.api.model.response.ApiResponse;
import com.rxmedical.api.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tool/records")
public class RecordToolController {
    @Autowired
    private RecordService recordService;

    @GetMapping("{recordId}")
	public ResponseEntity<ApiResponse<Record>> findRecordById(@PathVariable Integer recordId) {
		return ResponseEntity.ok(new ApiResponse<>(true, "訂單資訊", recordService.findRecordById(recordId)));
	}

    @PutMapping("/save")
    public ResponseEntity<ApiResponse<Object>> saveRecord(@RequestBody Record record) {
        return ResponseEntity.ok(new ApiResponse<>(true, "儲存成功", recordService.saveRecord(record)));
    }

    @PostMapping("/demander")
    public ResponseEntity<ApiResponse<List<Record>>> findRecordsByDemander(@RequestBody User user) {
        return ResponseEntity.ok(new ApiResponse<>(true, "訂單資訊", recordService.findByDemander(user)));
    }
}
