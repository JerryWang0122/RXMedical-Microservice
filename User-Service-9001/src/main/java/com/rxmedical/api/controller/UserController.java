package com.rxmedical.api.controller;

import com.rxmedical.api.client.JWTServiceClient;
import com.rxmedical.api.model.dto.*;
import com.rxmedical.api.model.response.ApiResponse;
import com.rxmedical.api.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
//@CrossOrigin
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JWTServiceClient jwtServiceClient;
	
	@GetMapping("/test")
	public String getTest() {
		return "User API 連接成功";
	}

	// 登入防止CSRF

	@PostMapping("/user/CSRFToken")
	public ResponseEntity<ApiResponse<List<String>>> getUserToken() {
		return ResponseEntity.ok(new ApiResponse<>(true, "CSRF令牌", jwtServiceClient.getCSRFToken().getData()));
	}


	// 判斷使用者登入
	@PostMapping("/user/login")
	public ResponseEntity<ApiResponse<UserUsageDto>> postUserLogin(@RequestBody UserLoginDto userLoginDto, HttpServletRequest request) throws NoSuchAlgorithmException {
		CSRFVerifyDTO dto = new CSRFVerifyDTO(userLoginDto.token(), request.getHeader("Authorization"));
		if (!jwtServiceClient.checkCSRFToken(dto).getState()) {
			return ResponseEntity.ok(new ApiResponse<>(false, "CSRF驗證失敗", null));
		}

		UserUsageDto userInfo = userService.checkUserLogin(userLoginDto);

		if (userInfo == null) {
			return ResponseEntity.ok(new ApiResponse<>(false, "帳號或密碼不正確!", null));
		}

		return ResponseEntity.ok(new ApiResponse<>(true, "使用者資訊", userInfo));
	}
	
	// 登出
	public void getLogout() {
		// TODO: 請實作
	}
	
	// 註冊
	@PostMapping("/user/register")
	public ResponseEntity<ApiResponse<Object>> registerUserInfo(@RequestBody UserRegisterDto userRegisterDto, HttpServletRequest request) throws NoSuchAlgorithmException {
		CSRFVerifyDTO dto = new CSRFVerifyDTO(userRegisterDto.token(), request.getHeader("Authorization"));
		if (!jwtServiceClient.checkCSRFToken(dto).getState()) {
			return ResponseEntity.ok(new ApiResponse<>(false, "CSRF驗證失敗", null));
		}
		Boolean registerSuccess = userService.registerUserInfo(userRegisterDto);

		if (!registerSuccess) {
			return ResponseEntity.ok(new ApiResponse<>(false, "註冊失敗", null));
		}
		return ResponseEntity.ok(new ApiResponse<>(true, "註冊成功", null));
	}
	
	// 取得個人資訊
	@PostMapping("/user/profile")
	public ResponseEntity<ApiResponse<UserInfoDto>> getUserInfo (CurrUserDto currUserDto) {

		UserInfoDto info = userService.getUserInfo(currUserDto.getUserId());

		if (info == null) {
			return ResponseEntity.ok(new ApiResponse<>(false, "使用者資訊不存在", null));
		}
		return ResponseEntity.ok(new ApiResponse<>(true, "使用者資訊", info));
	}
	
	// 修改個人資料
	@PutMapping("/user/profile")
	public ResponseEntity<ApiResponse<UserUsageDto>> editUserInfo(@RequestBody UserEditInfoDto userEditInfoDto) {

		UserUsageDto userInfo = userService.updateUserInfo(userEditInfoDto);

		if (userInfo == null) {
			return ResponseEntity.ok(new ApiResponse<>(false, "使用者資訊更新失敗", null));
		}
		return ResponseEntity.ok(new ApiResponse<>(true, "使用者資訊", userInfo));
	}


	// 取得個人衛材清單歷史
	@PostMapping("/user/purchase")
	public ResponseEntity<ApiResponse<List<PurchaseHistoryDto>>> getPurchaseHistoryList(CurrUserDto currUserDto) {
		List<PurchaseHistoryDto> userPurchaseHistoryList = userService.getUserPurchaseHistoryList(currUserDto.getUserId());

		if (userPurchaseHistoryList == null) {
			return ResponseEntity.ok(new ApiResponse<>(false, "無此人員", null));
		}
		return ResponseEntity.ok(new ApiResponse<>(true, "歷史衛材申請資料", userPurchaseHistoryList));
	}

	// 前台使用者，取得訂單明細
	@PostMapping("/user/purchase/detail")
	public ResponseEntity<ApiResponse<List<OrderDetailDto>>> getPurchaseDetails(@RequestBody RecordDto recordDto) {
		List<OrderDetailDto> purchaseDetails = userService.getPurchaseDetails(recordDto);
		if (purchaseDetails == null) {
			return ResponseEntity.ok(new ApiResponse<>(false, "存在問題", null));
		}
		return ResponseEntity.ok(new ApiResponse<>(true, "訂單明細", purchaseDetails));
	}

	// 前台使用者完成訂單流程
	@PostMapping("/user/purchase/finish")
	public ResponseEntity<ApiResponse<String>> finishOrder(@RequestBody RecordDto recordDto) {
		String errorMsg = userService.finishOrder(recordDto);
		if (errorMsg == null) {
			return ResponseEntity.ok(new ApiResponse<>(true, "訂單完成", null));
		}
		return ResponseEntity.ok(new ApiResponse<>(false, errorMsg, null));
	}

	// 後台查詢所有使用者
	@PostMapping("/admin/member")
	public ResponseEntity<ApiResponse<List<MemberInfoDto>>> getMemberList() {

		List<MemberInfoDto> memberList = userService.getMemberList();

		return ResponseEntity.ok(new ApiResponse<>(true, "員工權限資訊", memberList));
	}

	// root 使用者調整會員權限
	@PutMapping("/root/member")
	public ResponseEntity<ApiResponse<Boolean>> changeMemberAuthLevel(@RequestBody ChangeMemberAuthDto memberAuthDto) {

		Boolean success = userService.updateMemberAuthLevel(memberAuthDto);

		if (success) {
			return ResponseEntity.ok(new ApiResponse<>(true, "權限更新成功", true));
		}
		return ResponseEntity.ok(new ApiResponse<>(false, "權限更新失敗", false));
	}

	// 取得運送人員清單
	@PostMapping("/admin/transporter")
	public ResponseEntity<ApiResponse<List<TransporterDto>>> getTransporterList() {
		List<TransporterDto> transporterList = userService.getTransporterList();
		return ResponseEntity.ok(new ApiResponse<>(true, "運送人員資訊", transporterList));
	}

}
