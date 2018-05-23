package coracle.Data;

import coracle.Data.CryptoType;
import org.coracle.xsimple.common.codec.CoracleCoder;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/7/5.
 * @update
 */
public class CryptoSDK {

    public static String withAaemEncrypt(String src) {

        return CoracleCoder.enc(src, CryptoType.AEM.name());
    }

    public static String withAemDecrypt(String src) {
        return CoracleCoder.dec(src,  CryptoType.AEM.name());
    }
    
    public static void main(String[] args) {
		System.out.println(withAaemEncrypt("false"));
//    	System.out.println(withAemDecrypt("70IcNz9zTl+3rSGw6LCdrw=="));
	}
}
