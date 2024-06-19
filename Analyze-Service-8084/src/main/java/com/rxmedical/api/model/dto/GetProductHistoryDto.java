package com.rxmedical.api.model.dto;

import com.rxmedical.api.model.po.Product;

import java.util.Date;

/**
 * [INPUT] 查詢特定商品在特定日期後的所有交易紀錄用的DTO
 * @param product
 * @param date
 */
public record GetProductHistoryDto(Product product,
                                   Date date) {
}
