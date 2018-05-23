package coracle.Data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密不可逆
 * 
 * @author zoubaoqi
 * 
 */
public abstract class Md5Utils {

	public static String getMd5Code(String inString) {

		// 要加密的字符串字节型数组
		byte defaultBytes[] = inString.getBytes();
		// 加密后的字符串
		StringBuffer hexString;
		// 得到MessageDigest加密对象
		MessageDigest algorithm = null;
		try {
			algorithm = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// 重置上面字节数
		algorithm.reset();
		// 使用指定的字节数组更新
		algorithm.update(defaultBytes);

		byte messageDigest[] = algorithm.digest();
		hexString = new StringBuffer();
		for (int i = 0; i < messageDigest.length; i++) {
			String hex = Integer.toHexString(0xff & messageDigest[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();

	}
}
