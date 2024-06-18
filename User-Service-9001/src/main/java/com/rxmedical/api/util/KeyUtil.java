package com.rxmedical.api.util;

import java.security.SecureRandom;
import java.util.Base64;

public class KeyUtil {

    /**
     * 可將字節陣列轉換為十六進制格式的字符串。
     * 這通常用於方便地顯示二進制數據，如數字簽名、摘要或加密的數據。
     *
     * @param bytes 要轉換的字節陣列
     * @return 表示給定字節的十六進制格式的字符串
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    /**
     * 要從十六進制格式的雜湊字串轉回 byte[]
     *
     * @return 返回 byte[]。
     */
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    // JWT
    /**
     * 生成指定長度的隨機密鑰，並將其 Base64 編碼。
     *
     * @param byteLength 欲生成的密鑰的字節長度。例如，若要生成 256 位的密鑰，字節長度應為 32。
     * @return 返回 Base64 編碼的密鑰。
     *
     * 使用方法:
     * - SecureRandom 用於生成加密安全的隨機數。
     * - 這個方法首先使用 SecureRandom 生成指定長度的隨機密鑰。
     * - 然後將生成的密鑰進行 Base64 編碼，這樣可以將它安全地存儲或轉輸，同時避免任何不打印或特殊的字符。
     */
    public static String generateSecret(int byteLength) {
        SecureRandom secureRandom = new SecureRandom();   // 創建一個加密安全的隨機數生成器
        byte[] key = new byte[byteLength];                // 分配用於保存密鑰的空間
        secureRandom.nextBytes(key);                      // 生成隨機密鑰
        return Base64.getEncoder().encodeToString(key);   // 將密鑰 Base64 編碼並返回
    }



}
