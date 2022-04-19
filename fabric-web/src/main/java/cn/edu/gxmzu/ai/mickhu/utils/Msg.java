package cn.edu.gxmzu.ai.mickhu.utils;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author mickhu
 * @describe
 * @date 2022-04-08 16:13
 */

public class Msg {
    public final String ACCOUNT_ALREADY_EXISTS = "405";
    public String message;
    public String code;
    public boolean OK;

    public Msg(){ }

    public Msg(String message, String code, boolean OK) {
        this.code = code;
        this.message = message;
        this.OK = OK;
    }
    public String Error(String errorMessage, String code) {
        try {
            return (new ObjectMapper()).writeValueAsString(new Msg(errorMessage, code, false));
        } catch (Throwable e) {
            return "{\"code\":'" + code + "', \"message\":'" + e.getMessage() + " AND " + errorMessage + "', \"OK\":" + false + "}";
        }
    }

    public String Success(String successMessage) {
        try {
            return (new ObjectMapper()).writeValueAsString(new Msg(successMessage, "200", true));
        } catch (Throwable e) {
            return "{\"message\":'" + e.getMessage() + " BUT " + successMessage + " (NO COMMIT)', \"OK\":" + false + "}";
        }
    }

    public String SuccessObject(String object) {
        return "{\"message\":" + object + ", \"OK\":" + true + "}";
    }

    public boolean checkString(String str) {
        if (str.trim().length() <= 0 || str == null)
            return false;
        return true;
    }
}

