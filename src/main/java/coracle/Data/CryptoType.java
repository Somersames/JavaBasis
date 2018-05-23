package coracle.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/7/5.
 * @update
 */

public enum CryptoType {
    /**
     * 不加密
     */
    NONE("NONE"),


    AEM("AEM");
    // , REM("REM"), DEM("DEM"), RSA("RSA"); //暂不支持


    private static final Map<String, CryptoType> mappings = new HashMap<>(4);

    static {
        for (CryptoType cryptoType : values()) {
            mappings.put(cryptoType.name(), cryptoType);
        }
    }

    private String mode;
    CryptoType(String mode) {
        this.mode = mode;
    }

    public static CryptoType resolve(String mode) {
        return (mode != null ? mappings.get(mode) : NONE);
    }

    public boolean matches(String mode) {
        return (this == resolve(mode));
    }

    @Override
    public String toString() {
        return this.name();
    }
}
