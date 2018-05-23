package coracle.Data;

import coracle.Data.Md5Utils;
import coracle.Data.AssertUtil;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.Base64UrlCodec;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * 平台jwt 生成解密sdk。
 *
 * @author Tung
 * @version 1.0
 * @date 2017/6/29.
 * @update
 */
public class JwtSDK {
    private static final String DATA_PARAM_KEY = "data";

    public JwtSDK() {

    }

    /**
     * 根据appkey计算secretKey
     * @param appkey
     * @return
     */
    public static String calSecretKey(String appkey) {
        return Md5Utils.getMd5Code(appkey);
    }
    
    private static void checkSecretKey(String secretKey) {
        AssertUtil.hasText((CharSequence) secretKey, "JWT所使用的Secret Key为null或者blank， 请设置AppKey");
    }

    /**
     *  构造JWT，将数据放入约定好的payload的data属性中。
     * @param secretKey 签名时使用的密文
     * @return
     */
    public static String createJWT(String secretKey,String data) {
        return createJWT(secretKey, data, 0L);
    }

    /**
     * 构造JWT，将数据放入约定好的payload的data属性中，并设置有效时效，单位秒 <br />
     * 有效时间不是绝对准确的，因为各个节点之间的系统时间不一定完全同步。
     * @param secretKey 签名时使用的密文
     * @param data 要传入payload的参数，
     * @param expired 时效，单位秒
     * @return
     */
    public static String createJWT(String secretKey,  String data, long expired) {
        checkSecretKey(secretKey);
        JwtBuilder builder = Jwts.builder()
                .claim(DATA_PARAM_KEY, data)
//                .signWith(SignatureAlgorithm.HS256, Base64UrlCodec.BASE64URL.encode(secretKey));
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes(Charset.forName("UTF-8")));
        if (expired > 0L) {
            builder.setExpiration(new Date(System.currentTimeMillis() + 1000*expired));
        }
        return builder.compact();
    }

    /**
     * 根据所给的JWT字符串，获取其中约定好的数据。 <br />
     * 若JWT中没有所约定的data属性时，返回null
     * @param src
     * @param secretKey
     * @return
     * @throws SignatureException JWT签名不匹配时抛出
     * @throws ExpiredJwtException JWT过期时抛出
     */
    public static String fetchData(String src, String secretKey) throws SignatureException, ExpiredJwtException {
        checkSecretKey(secretKey);
        Claims jws = Jwts.parser()
//                .setSigningKey(Base64UrlCodec.BASE64URL.encode(secretKey))
                .setSigningKey(secretKey.getBytes(Charset.forName("UTF-8")))
                .parseClaimsJws(src).getBody();

        return (String) jws.get(DATA_PARAM_KEY);
    }
}
