package com.rxmedical.api.client;

import com.rxmedical.api.model.po.History;
import com.rxmedical.api.model.po.Record;
import com.rxmedical.api.model.po.User;
import com.rxmedical.api.model.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "Sales-Service-9011")
public interface SaleServiceClient {
    @GetMapping("/tool/records/{recordId}")
    public ApiResponse<Record> findRecordById(@PathVariable Integer recordId);

    @PutMapping("/tool/records/save")
    public ApiResponse<Object> saveRecord(@RequestBody Record record);

    @PostMapping("/tool/records/demander")
    public ApiResponse<List<Record>> findRecordsByDemander(@RequestBody User user);

    @PostMapping("/tool/histories")
    public ApiResponse<List<History>> getRecordDetails(@RequestBody Record record);

    @PostMapping("/tool/histories/count")
    public ApiResponse<Integer> getApplyItemsCount(@RequestBody Record record);
}
