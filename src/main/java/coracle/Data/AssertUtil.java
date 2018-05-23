package coracle.Data;

/**
 * 断言工具类，不符合断言时抛出IllegalArgumentException
 *
 * @author Tung
 * @version 1.0
 * @date 2017/6/24.
 * @update
 */

public class AssertUtil {

	public static void hasLength(String text, String message) {

		if (!hasLength(text)) {
			throw new IllegalArgumentException(message);
		}
	}

	public static boolean hasLength(String str) {

		return hasLength((CharSequence) str);
	}

	public static boolean hasLength(CharSequence str) {

		return (str != null && str.length() > 0);
	}

	/**
	 * 当传入字符串为null， empty或者blank时，抛出IllegalArgumentException。
	 *
	 * @param str
	 * @param msg
	 */
	public static void hasText(String str, String msg) {

		hasText((CharSequence) str, msg);
	}

	/**
	 * 当传入字符序列为null， empty或者blank时，抛出IllegalArgumentException。
	 *
	 * @param str
	 * @param msg
	 */
	public static void hasText(CharSequence str, String msg) {

		if ((str != null && str.length() > 0)) {
			int strLen = str.length();
			for (int i = 0; i < strLen; i++) {
				if (!Character.isWhitespace(str.charAt(i))) {
					return;
				}
			}
		}
		throw new IllegalArgumentException(msg);
	}

	/**
	 * 当传入的对象为null时，抛出IllegalArgumentException。
	 * @param obj
	 * @param msg
	 */
	public static void notNull(Object obj, String msg) {

		if (obj == null) {
			throw new IllegalArgumentException(msg);
		}
	}
}
