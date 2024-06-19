package com.rxmedical.api.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {

	private Integer id;
	private String code;	// 文件公文號

	/**
	 *  [status] 訂單狀態 -> 有六種
	 *  待確認(unchecked:) 訂單剛送出但尚未被接受
	 *  待撿貨(picking): 訂單接受，但尚未收集完成
	 *  待出貨(waiting): 收集完成，等待指定配送
	 *  運送中(transporting): admin開始配送
	 *  已完成(finish): 配送完成
	 *  取消(rejected): 取消
	 */
	private String status;	    // 訂單狀態
	private User demander;		// User 類 -> 申請人
	private User transporter;	// User 類 -> 運送人
	private Date createDate;	// 建立日期
	private Date updateDate;	// 更新日期

	public String getChineseStatus() {
		switch (status) {
			case "unchecked":
				return "待確認";
			case "picking":
				return "待撿貨";
			case "waiting":
				return "待出貨";
			case "transporting":
				return "運送中";
			case "finish":
				return "已完成";
			case "rejected":
				return "取消";
		}
		return null;
	}
}
