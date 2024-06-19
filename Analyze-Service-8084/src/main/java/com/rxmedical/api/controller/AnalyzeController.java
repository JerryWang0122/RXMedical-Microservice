package com.rxmedical.api.controller;

import com.rxmedical.api.model.dto.CallAdviceDto;
import com.rxmedical.api.model.dto.GetMaterialInfoDto;
import com.rxmedical.api.model.dto.SafetyRatioDto;
import com.rxmedical.api.model.response.ApiResponse;
import com.rxmedical.api.service.AnalyzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/analyze")
public class AnalyzeController {

    @Autowired
    private AnalyzeService analyzeService;

    @GetMapping("/test")
    public String getTest() {
        return "Analyze API 連接成功";
    }

    // 後台取得勞動積分
//    @PostMapping("/laborScore")
//    public ResponseEntity<ApiResponse<Map<String, Integer>>> getLaborScore() {
//        return ResponseEntity.ok(new ApiResponse<>(true, "勞動積分", analyzeService.getLaborScore()));
//    }

    // 後台取得庫存safety threshold ratio
    @PostMapping("/materialSafetyRatio")
    public ResponseEntity<ApiResponse<List<SafetyRatioDto>>> getMaterialSafetyRatio() {
        return ResponseEntity.ok(new ApiResponse<>(true, "庫存安全比例", analyzeService.getMaterialSafetyRatio()));
    }

    // 後台取得建議進貨量趨勢圖
//    @PostMapping("/callMaterialDiagram")
//    public ResponseEntity<ApiResponse<List<CallAdviceDto>>> getCallMaterialDiagram(@RequestBody GetMaterialInfoDto infoDto) {
//        return ResponseEntity.ok(new ApiResponse<>(true, "進貨建議圖表", analyzeService.getCallMaterialDiagram(infoDto.getMaterialId())));
//    }
}
