package com.rxmedical.api.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	private Integer id;
	private String empCode;		// 員工識別碼ID
	private String name;	// 姓名
	private String salt;	// 鹽
	private String password;  // 密碼
	private String dept;      // 部門
	private String title;    // 職稱
	private String email;    // 信箱

	/**
	 *     authLevel 權限分五級
	 * 		1. off : 關閉權限
	 * 		2. register: 完成註冊
	 *   ---- (以上無法登入使用) ----
	 * 		3. staff: 一般員工
	 * 		4. admin: 管理人員
	 * 		5.  root: 最高管理者
	 */
	private String authLevel;  // 權限
	private Date createDate;  // 建立日期
	private Date updateDate;  // 更新日期
}
