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

// 檢查使用者是否有後台權限
@Component
@Aspect
public class BackendAuthCheckAOP {

    @Autowired
    private JWTServiceClient jwtServiceClient;

    // 設定切點
    // ---------------- Product -------------------
    @Pointcut(value = "execution(* com.rxmedical.api.controller.ProductController.materialInfoUpload(..))")
    public void materialInfoUpload(){}

    @Pointcut(value = "execution(* com.rxmedical.api.controller.ProductController.getMaterialList(..))")
    public void getMaterialList(){}

    @Pointcut(value = "execution(* com.rxmedical.api.controller.ProductController.getMaterialInfo(..))")
    public void getMaterialInfo(){}

    @Pointcut(value = "execution(* com.rxmedical.api.controller.ProductController.materialInfoUpdate(..))")
    public void materialInfoUpdate(){}

    // ---------------- 開切 -------------------------
    @Around(value = "getMaterialList() || materialInfoUpload() || getMaterialInfo() || materialInfoUpdate() ")
    public Object aroundCheckAuth(ProceedingJoinPoint joinPoint) {

        Object result = null;

        try {
//            System.out.println("測試前置");

            // 驗證JWT
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            Map<String, Object> userInfoMap = jwtServiceClient.verifyUserUsageToken(request.getHeader("Authorization")).getData();
            if (userInfoMap == null) {  // 驗證失敗
                result = ResponseEntity.ok(new ApiResponse<>(false, "JWT Verify Error", null));
            } else if (!("admin".equals(userInfoMap.get("authLevel")) || "root".equals(userInfoMap.get("authLevel")))) { // 權限不足
                result = ResponseEntity.ok(new ApiResponse<>(false, "權限錯誤", null));
            } else {  // 驗證成功
                Object[] args = joinPoint.getArgs();
                if (args.length != 0){
                    System.out.println(args[0]);
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
