package httpgrab;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by szh on 2017/3/28.
 */
public class Grab {
    static Map<String, Boolean> map = new HashMap<>(); // 页面链接
    static Set<String> set = new HashSet<>(); // 图片列表

    public static void main(String args[]) {
        Grab gr = new Grab();
//        gr.sengRe();
//        gr.RequestPhoto("http://bbs.zhiyoo.com/thread-13279411-1-1.html");
        gr.sengRe(); // 发出页面请求
//        gr.RequestPhoto("http://bbs.zhiyoo.com/thread-12615850-1-2.html");
    }

    public void sengRe() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet("http://bbs.zhiyoo.com/tforum-98-1192-1.html");
            httpGet.setHeader("Cookie", readCookie("D:\\JavaBasis\\cookies\\cookies.txt"));
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
            httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
            CloseableHttpResponse response = httpClient.execute(httpGet);

            try {
                HttpEntity httpEntity = response.getEntity();
                Header[] headers = response.getAllHeaders();
                if (httpClient != null) {
                    InputStream inputStream = httpEntity.getContent();
                    readFormServer(inputStream);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    获取每一个页面的的具体帖子链接
     */
    public void readFormServer(InputStream inputStream) throws IOException {
        File file = new File("D:\\JavaBasis\\cookies\\data.txt");
        FileOutputStream fileout = new FileOutputStream(file);
        byte[] bytes = new byte[1024];
        int len;
        Pattern parent = null;
        Matcher matcher = null;
        parent = Pattern.compile("[a-zA-z]+://[^\\s]*(?:3D1192\\b)");
        while ((len = inputStream.read(bytes)) != -1) {
            String s = new String(bytes, 0, len);
            matcher = parent.matcher(s);
            if (matcher.find()) {
                String cutresult = matcher.group().toString();
                if (map.get(cutresult) == null)
                    map.put(cutresult, false);
            }
            fileout.write(bytes, 0, len);
            fileout.flush();
        }
        showmap();
    }

    public String readCookie(String fileName) throws IOException {
        File file = new File(fileName);
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        int len = 0;
        StringBuffer sb = new StringBuffer();
        while ((len = fileInputStream.read(bytes)) != -1) {
            sb.append(new String(bytes, 0, len));
        }
        return sb.toString();
    }

    public void showmap() {
        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            String name=entry.getKey();
            int index=name.indexOf("tid");
            int index2=name.indexOf("extra");
            String tid =name.substring(index+4,index2-1);
            System.out.println(tid+"---------tid");
            RequestPhoto(tid);
            System.out.println(entry.getKey() + "------------map里面的url");
        }
    }

    public void RequestPhoto(String tid) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("mod", "viewthread"));
        params.add(new BasicNameValuePair("tid", tid));
        params.add(new BasicNameValuePair("extra", "page=1&filter=typeid&typeid=1192&typeid=1192"));
        String str = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            str = EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8));
            HttpGet httpGet = new HttpGet("http://bbs.zhiyoo.com/forum.php" + "?" + str);
            httpGet.setHeader("Cookie", readCookie("D:\\JavaBasis\\cookies\\downloadcookies.txt"));
            httpGet.setHeader("Refer", "http://bbs.zhiyoo.com/tforum-98-1192-1.html");
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
            httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
            Thread.sleep(1000);
            System.out.println("是否进入帖子");
            CloseableHttpResponse response = httpClient.execute(httpGet);
            try {
                HttpEntity httpEntity = response.getEntity();
                if (httpClient != null) {
                    InputStream inputStream = httpEntity.getContent();
                    SavePhoto(inputStream); // 保存具体的图片url
                }
                Header[] headers = response.getAllHeaders();
                for (int i = 0; i < headers.length; i++) {
                    System.out.println(headers[i]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
    请求图片地址获取图片然后保存
     */
    public void SavePhoto(InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        byte[] bytes = new byte[1024];
        int len = 0;
        Pattern pattern = null;
        Matcher matcher = null;
        pattern = Pattern.compile("(file=).*(?:jpg|bmp|gif)"); //匹配每一个图片的url
        while ((len = dataInputStream.read(bytes)) != -1) {
            matcher = pattern.matcher(new String(bytes, 0, len));
            if (matcher.find()) {
                /*
                对截取的html进行裁剪，使其符合规范
                 */
                System.out.println(matcher.group() + "原始");
                String cutresult = matcher.group().toString().replace("\"", "");
                int m = cutresult.indexOf(".jpg", 0);
                System.out.println(cutresult.substring(5, m + 4));
                String newSteing = cutresult.substring(5, m + 4);
                System.out.println(newSteing + "--------------截取的图片url");
                if (!set.contains(newSteing))
                    set.add(newSteing);
            }
        }
        showSet();
    }

    /*
    测试Set
     */
    public void showSet() {
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            System.out.println(s + "------------set里面的");
            int index = s.lastIndexOf("/");
            String fileName = s.substring(index + 1, s.length());
            downloadPhotoRequest(s, fileName);
        }
    }

    public void downloadPhotoRequest(String url, String fileName) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Cookie", readCookie("D:\\JavaBasis\\cookies\\downloadcookies.txt"));
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
            httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
            Thread.sleep(1000); // 每隔一秒钟爬取一次
            CloseableHttpResponse response = httpClient.execute(httpGet);
            try {
                HttpEntity httpEntity = response.getEntity();
                if (httpClient != null) {
                    InputStream inputStream = httpEntity.getContent();
                    download(fileName, inputStream); //下载图片到本地
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void download(String fileName, InputStream inputStream) throws IOException {
        File file = new File("D:\\JavaBasis\\photo\\" + fileName);
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = dataInputStream.read(bytes)) != -1) {
            fileOutputStream.write(bytes, 0, len);
            fileOutputStream.flush();
        }
    }

}
