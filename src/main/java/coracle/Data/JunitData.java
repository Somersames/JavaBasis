package coracle.Data;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 对Coracle的api进行测试
 *
 * @author szh
 * @create 2018-03-27 15:17
 **/
public class JunitData {
    public static final MediaType JSONMe = MediaType.parse("application/json; charset=utf-8");

    @Test
    public void uu(){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        java.util.Calendar Cal=java.util.Calendar.getInstance();
        Cal.setTime(now);
        Cal.add(Calendar.MINUTE,30);
        System.out.println("date:"+dateFormat.format(Cal.getTime()));
    }



    @Test
    public void sendData() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().build();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user", "zhouhaoxiang");
        map.put("password", "8GTxy9iTj49asNhe0Gqamw==");
        map.put("remember", "true");
        String url = "http://172.16.23.169:8080/mchl/v3/agent/public/api/login";
        String json = JSON.toJSONString(map);
        RequestBody body = RequestBody.create(JSONMe, json);

        Request request = new Request.Builder()
                .url(url)
                .header("X-xSimple-appKey", "8b73e4ee-696c-4c8c-aabd-0c893ba82e73")
                .post(body)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        System.out.println(response.body().string());
    }

    @Test
    public void getData() throws IOException {

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().build();
        Map<String, Object> map = new HashMap<String, Object>();
        String url = "http://172.16.23.169:8080/mchl/v3/agent/bu";

        RequestBody body = RequestBody.create(JSONMe, "");

        String agent ="x_apiKey=serverInfo&x_buKey=mxm&x_method=POST&x_EM=AEM&x_isSign=true";
        String appkey=JwtSDK.calSecretKey("8b73e4ee-696c-4c8c-aabd-0c893ba82e73");
        System.out.println(appkey);
        String result=JwtSDK.createJWT(appkey,agent);
        System.out.println(result);

        Request request = new Request.Builder()
                .url(url)
                .header("X-Authorization", "eyJhbGciOiJIUzI1NiJ9.eyJkYXRhIjoie1widXNlcm5hbWVcIjpcInpob3VoYW94aWFuZ1wifSJ9.9qnhk1ioN2z4BBDe1l5dpsyy594KGq0j8hBHHEnNn0w")
                .header("X-xSimple-debug", "false") //加密需要转成false
                .header("X-xSimple-appKey", "8b73e4ee-696c-4c8c-aabd-0c893ba82e73")
//                .header("X-xSimple-agent", "x_apiKey=serverInfo&x_buKey=mxm&x_method=POST&x_EM=NONE&x_isSign=false")// jwt签名
                .header("X-xSimple-agent", result)// jwt签名 AEM

//                .header("X-xSimple-agent", "x_apiKey=serverInfo&x_buKey=mxm&x_method=POST")// 未加密
                .header("X-xSimple-IMEI","5498313987f90080d23805082da531ec1b11f895")
                .post(body) // AEM
                .build();

        Response response = okHttpClient.newCall(request).execute();
//        System.out.println(response.body().string());
        System.out.println(CryptoSDK.withAemDecrypt(response.body().string()));
    }
}
