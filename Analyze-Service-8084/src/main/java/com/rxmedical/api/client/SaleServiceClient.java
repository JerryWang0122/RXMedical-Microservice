package com.rxmedical.api.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "Sales-Service-9011")
public interface SaleServiceClient {
}
