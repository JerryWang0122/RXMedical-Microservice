package com.rxmedical.api.aop;

import com.rxmedical.api.client.JWTServiceClient;
import com.rxmedical.api.model.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Map;

// 檢查前台使用者登入狀態
@Component
@Aspect
public class CheckUserLoginAOP {

	@Autowired
	private JWTServiceClient jwtServiceClient;

	// 設定切點
	// ---------------- Product -------------------
	@Pointcut(value = "execution(* com.rxmedical.api.controller.ProductController.getProductList(..))")
	public void getProductList() {}

	@Pointcut(value = "execution(* com.rxmedical.api.controller.ProductController.getProductItemInfo(..))")
	public void getProductItemInfo() {}


	// ------------- 開切 ---------------
	@Around(value = "getProductList() || getProductItemInfo()")
	public Object aroundCheckLogin(ProceedingJoinPoint joinPoint) {

		Object result = null;

		try {
//			System.out.println("測試前置");

			// 驗證JWT
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
			Map<String, Object> userInfoMap = jwtServiceClient.verifyUserUsageToken(request.getHeader("Authorization")).getData();
			if (userInfoMap == null) {  // 驗證失敗
				result = ResponseEntity.ok(new ApiResponse<>(false, "JWT Verify Error", null));
			} else if ("register".equals(userInfoMap.get("authLevel")) || "off".equals(userInfoMap.get("authLevel"))) { // 權限不足
				result = ResponseEntity.ok(new ApiResponse<>(false, "權限錯誤", null));
			} else {  // 驗證成功
				Object[] args = joinPoint.getArgs();
				if (args.length != 0){
					Method setUserId = args[0].getClass().getDeclaredMethod("setUserId", Integer.class);
					setUserId.invoke(args[0], userInfoMap.get("userId"));
				}

				result = joinPoint.proceed();
			}

		} catch (Throwable e) {
			e.printStackTrace();
			result = ResponseEntity.ok(new ApiResponse<>(false, "伺服器發生錯誤", null));
		}
		return result;
	}
}
