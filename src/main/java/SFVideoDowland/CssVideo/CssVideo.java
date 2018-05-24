package SFVideoDowland.CssVideo;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author szh
 * @create 2018-05-24 19:21
 **/
public class CssVideo {
    protected static void main(String[] args) {
        start();
    }
    private static void start(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        int i=18;
//        while (i<290){


        try {
            HttpGet httpGet=null;
            if(i<10){
                 httpGet = new HttpGet("https://media.video.segmentfault.com/eab963VTCl4LPZ4y8phbYaCjKqg=/lgOR6NwUEuGWs5MuNnHEW67PeDzu/00000"+String.valueOf(i)+".ts");
            }else if(i<100){
                 httpGet = new HttpGet("https://media.video.segmentfault.com/eab963VTCl4LPZ4y8phbYaCjKqg=/lgOR6NwUEuGWs5MuNnHEW67PeDzu/0000"+String.valueOf(i)+".ts");
            }else{
                 httpGet = new HttpGet("https://media.video.segmentfault.com/eab963VTCl4LPZ4y8phbYaCjKqg=/lgOR6NwUEuGWs5MuNnHEW67PeDzu/000"+String.valueOf(i)+".ts");
            }

//            httpGet.setHeader("accept-ranges", "bytes6");
//            httpGet.setHeader("access-control-allow-origin", "*");
//            httpGet.setHeader("access-control-expose-headers", "X-Log, X-Reqid");
//            httpGet.setHeader("access-control-max-age", "2592000");
//            httpGet.setHeader("age", "0");
//            httpGet.setHeader("cache-control", "max-age=2592000");
//            httpGet.setHeader("content-disposition", "inline; filename=\"000001.ts\"; filename*=utf-8' '000001.ts"); //TODO
//            httpGet.setHeader("content-length", "558172");
//            httpGet.setHeader("content-transfer-encoding", "tbinary");
//            httpGet.setHeader("date", "video/mp2t");
//            httpGet.setHeader("content-type", "Thu, 24 May 2018 11:05:04 GMT");
//            httpGet.setHeader("etag", "FunorSPF5uS7h3Tn4lxYCLZu0zGv");
//            httpGet.setHeader("expires", "Sat, 23 Jun 2018 11:05:04 GMT");
//            httpGet.setHeader("expires", "Sat, 23 Jun 2018 11:05:04 GMT");
//            httpGet.setHeader("last-modified", "Tue, 02 Jan 2018 06:50:51 GMT");
//            httpGet.setHeader("server", "marco/2.2");
//            httpGet.setHeader("status", "200");
//            httpGet.setHeader("via", "T.11.-, V.mix-gd-can-005, T.69.N, M.cun-hb-wuh-069");
//            httpGet.setHeader("x-log", "mc.g/404;rs12_12.sel/not found;rs11_12.sel;rwro.get:1;RS.dbs:1;RS:1;mc.s;mc.g;IO:33");
//            httpGet.setHeader("x-m-log", "QNM:xs446;QNM3:23");
//            httpGet.setHeader("x-m-reqid", "1RkAACcY3UsWOygV");
//            httpGet.setHeader("x-qiniu-zone", "1");
//            httpGet.setHeader("x-qnm-cache", "Hit");
//            httpGet.setHeader("x-reqid", "q1QAAK99xfjqEiYV");
//            httpGet.setHeader("x-request-id", "36e9b5c2525194a6147b248e0cf2be26");
//            httpGet.setHeader("x-slice-complete-length", "558172");
//            httpGet.setHeader("x-slice-etag", "T.11.-, V.mix-gd-can-005, T.69.N, M.cun-hb-wuh-069");
//            httpGet.setHeader("x-slice-size", "FunorSPF5uS7h3Tn4lxYCLZu0zGv");
//            httpGet.setHeader("x-source", "C/200");
//            httpGet.setHeader("x-svr", "IO");
            httpGet.setHeader("Origin", "https://segmentfault.com");
            httpGet.setHeader("Referer", "https://segmentfault.com/l/1500000012666812/play");
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36");
            CloseableHttpResponse response = httpClient.execute(httpGet);
            try {
                HttpEntity httpEntity = response.getEntity();
                if (httpClient != null) {
                    InputStream inputStream = httpEntity.getContent();
                    File file = new File("D:\\JavaBasis\\cookies\\0000"+String.valueOf(i)+".ts");
                    FileOutputStream fileout = new FileOutputStream(file);
                    byte[] bytes = new byte[1024];
                    int len;
                    while ((len = inputStream.read(bytes)) != -1) {
//                        String s = new String(bytes, 0, len);
                        fileout.write(bytes, 0, len);
                        fileout.flush();
                        }
                    fileout.close();
                    inputStream.close();
                    }

//                    int len =0;
//                    while ((len=inputStream.read()) !=-1){
//
//
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        i++;
//        }
    }
    public void te(){

    }
}
