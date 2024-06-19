package com.rxmedical.api.client;

import com.rxmedical.api.model.dto.GetProductHistoryDto;
import com.rxmedical.api.model.po.History;
import com.rxmedical.api.model.po.Record;
import com.rxmedical.api.model.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@FeignClient(name = "Sales-Service-9011")
public interface SaleServiceClient {
    @PostMapping("/tool/histories")
    public ApiResponse<List<History>> getRecordDetails(@RequestBody Record record);

    @PostMapping("/tool/histories/product")
    public ApiResponse<List<History>> findProductHistoryAfter(@RequestBody GetProductHistoryDto dto);

    @PostMapping("/tool/records/finish")
    public ApiResponse<List<Record>> getFinishRecordsAfter(@RequestBody Date monthAgoDate);

}
