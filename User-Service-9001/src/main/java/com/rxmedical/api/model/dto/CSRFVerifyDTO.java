package com.rxmedical.api.model.dto;

public record CSRFVerifyDTO(String token,
                            String authorizationHeader) {
}
