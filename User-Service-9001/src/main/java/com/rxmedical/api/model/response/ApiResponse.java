package com.rxmedical.api.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {   // api 回應
    private Boolean state;  // 是否成功
    private String message;   // 返回訊息
    private T data;           // 返回資料
}
