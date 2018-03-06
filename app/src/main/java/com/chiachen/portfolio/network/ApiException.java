package com.chiachen.portfolio.network;

/**
 * Created by jianjiacheng on 07/03/2018.
 */

public class ApiException extends RuntimeException {

    public static final int USER_NOT_EXIST = 100;
    public static final int WRONG_PASSWORD = 101;

    public ApiException(int resultCode) {
        this(getApiExceptionMessage(resultCode));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * @param code
     * @return
     */
    private static String getApiExceptionMessage(int code){
        String message = "";
        switch (code) {
            case USER_NOT_EXIST:
                message = "使用者不存在";
                break;
            case WRONG_PASSWORD:
                message = "密碼錯誤";
                break;
            default:
                message = "Unknown error";

        }
        return message;
    }
}
