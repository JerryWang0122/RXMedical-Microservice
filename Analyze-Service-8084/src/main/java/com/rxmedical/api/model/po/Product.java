package com.rxmedical.api.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	private Integer id;
	private String code;    // 產品識別號
	private String name;    // 產品名稱
	private Integer stock;  // 庫存量
	private Integer safetyThreshold;  // 安全庫存
	private String description;     // 產品描述
	private String storage;     // 儲存位置
	private String picture;     // 圖片儲存位置
	private String category;     // 商品種類
	private Date createDate;     // 建立日期
	private Date updateDate;     // 更新日期
}
