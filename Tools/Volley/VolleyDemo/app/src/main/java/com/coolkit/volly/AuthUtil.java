package com.coolkit.volly;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AuthUtil {

    private SecretKey mAppSecret;

    // 单例
    private AuthUtil(){
        //还原密钥
        mAppSecret = new SecretKeySpec(AppInfo.getAppSecret().getBytes(), "HmacSHA256");
    }

    private static class AuthUtilHolder{
        private final static AuthUtil instance=new AuthUtil();
    }
    public static AuthUtil getInstance(){
        return AuthUtilHolder.instance;
    }


    // 加密信息为SHA256
    public String encodeHmacSHA256(String data){
        //实例化mac
        Mac mac = null;
        try {
            byte[] values = data.getBytes("utf-8");
            mac = Mac.getInstance(mAppSecret.getAlgorithm());
            //init
            mac.init(mAppSecret);
            byte[] digest = mac.doFinal(values);
            return Base64.encodeToString(digest,Base64.DEFAULT);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";

    }

}
