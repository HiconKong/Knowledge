package com.coolkit.volly;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private HashMap<String, Object> mDefaultParams;
    public String account = "+8613760462607";
    public String password = "11111111";
    private String mNonce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
    }

    private void Login() {
        String url = "https://cn-api.coolkit.cc/api/user/login";
        //将map转化为JSONObject对象
        JSONObject jsonObject = new JSONObject(getParams());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {//jsonObject为请求返回的Json格式数据
                        Log.e("hiconkong","onResponse:"+jsonObject);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.i("hiconkong","onErrorResponse:"+volleyError);
                    }
                })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
            return getHttpHeader();
        }
        };
        //设置请求的Tag标签，可以在全局请求队列中通过Tag标签进行请求的查找
        request.setTag("testPost");

        //重试机制
        request.setRetryPolicy(new DefaultRetryPolicy(
                3 * 1000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        //将请求加入全局队列中
        Volley.newRequestQueue(this).add(request);
    }

    public HashMap<String, String> getHttpHeader() {
        HashMap<String, String> httpHeaderDefault = this.getDefaultHeader();
        HashMap<String, String> httpHeaderRequest = this.getRequestHeader();
        httpHeaderDefault.putAll(httpHeaderRequest);
        return httpHeaderDefault;
    }

    public HashMap<String, String> getRequestHeader() {
        HashMap<String,String> requestHeader = new HashMap<>();
        HashMap<String, Object> params = this.getParams();
        JSONObject jsonObj = new JSONObject(params);
        requestHeader.put("Authorization","Sign "+ AuthUtil.getInstance().encodeHmacSHA256(jsonObj.toString()));
        return requestHeader;
    }

    public HashMap<String, String> getDefaultHeader() {
        HashMap<String, String> defaultHeader = new HashMap<>();
        defaultHeader.put("Accept", "application/json");
        defaultHeader.put("Content-Type", "application/json;charset=UTF-8");
        defaultHeader.put("Package-Name", "com.coolkit");
        defaultHeader.put("Cache-Control", "no-store");
        return defaultHeader;
    }

    private HashMap<String, Object> getParams() {
        HashMap<String, Object> defaultParams = this.getDefaultParams();
        HashMap<String, Object> requestParams = this.getRequestParams();
        defaultParams.putAll(requestParams);
        return defaultParams;
    }

    public HashMap<String, Object> getDefaultParams() {
        if (mDefaultParams == null) {
            mDefaultParams = new HashMap<>();
            mDefaultParams.put("version", BuildConfig.VERSION_NAME);
            mDefaultParams.put("ts", System.currentTimeMillis() / 1000);
            mDefaultParams.put("nonce", getNonce());
            mDefaultParams.put("appid", AppInfo.getAppID());
        }
        return mDefaultParams;
    }

    public HashMap<String, Object> getRequestParams() {
        HashMap<String,Object> requestParams = new HashMap<>();
        if (account.contains("@"))
        {
            requestParams.put("email",account);
        }else {
            requestParams.put("phoneNumber",account);
        }
        requestParams.put("password",password);
        return requestParams;
    }

    private String getNonce() {

        if (mNonce == null) {
            //生成随机数
            String base = "abcdefghijklmnopqrstuvwxyz0123456789";
            Random random = new Random();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 8; i++) {
                int number = random.nextInt(base.length());
                sb.append(base.charAt(number));
            }
            mNonce = sb.toString();
        }
        return mNonce;
    }

}
