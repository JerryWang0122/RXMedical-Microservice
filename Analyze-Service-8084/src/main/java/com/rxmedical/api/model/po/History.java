package com.rxmedical.api.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class History {

	private Integer id;
	private Integer quantity;	// 該次進、銷變化量
	private Integer price;		// 該次進、銷貨金額（售統一為 0）
	private String flow;	// 進、銷、售
	private Record record;		// Record 類 -> 對應哪一筆衛材單
	private Product product;	// Product 類 -> 對應哪一項衛材
	private User user;		// User 類 -> 誰去取貨或進貨
	private Date createDate;	// 建立日期
	private Date updateDate;	// 更新日期
}
