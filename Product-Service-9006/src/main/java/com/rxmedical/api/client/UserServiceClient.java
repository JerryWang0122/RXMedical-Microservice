package com.rxmedical.api.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "User-Service-9001")
public interface UserServiceClient {
}
