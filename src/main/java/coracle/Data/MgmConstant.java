package coracle.Data;


/**
 * 添加key的规则
 * 前缀指定类型HEADER PARAM 或者其他。。
 * PARAM系的值全小写，下划线分隔单词
 * HEADER系的分隔使用横杠分隔单词
 *
 * @author Tung
 * @version 1.0
 * @date 2017/6/30.
 * @update
 */

public interface MgmConstant {

    String HEADER_AUTH = "X-Authorization";

    String ATTR_PUBLIC_API_PATH_PREFIX = "/v3/agent/public";


    String HEADER_MGM_CONSOLE_TOKEN = "X-xSimple-MgmConsole-token";

    String HEADER_AGENT = "X-xSimple-agent";

    String PARAM_DEBUG = "x_debug";

    String HEADER_DEBUG = "X-xSimple-debug";

    String HEADER_APPKEY = "X-xSimple-appKey";

    String PARAM_APPKEY = "x_appkey";

    String HEADER_REWRITE_URL = "X-xSimple-rewrite";

    String PARAM_REWRITE_URL = "x_rewrite";


    String PARAM_BU_KEY = "x_buKey";


    String PARAM_API_KEY = "x_apiKey";


    String PARAM_API_URI = "x_apiUri";

    String HEADER_REAL_METHOD = "X-xSimple-method";

    String PARAM_REAL_METHOD = "x_method";


    /* 客户端指定当前接口是否需要应用验证。 【没用，验不验证由库决定】
    * */
    String HEADER_IS_APP_AUTH = "X-xSimple-isAppAuth";

    /* 客户端指定当前接口是否进行身份验证， 【没用，也是由库决定】
    * */
    String HEADER_IS_AUTH = "X-xSimple-isAuth";



    /* 客户端指定当前接口客户端是否参数签名 【库配置不签名，而客户端配置签名---通过并校验； 库配置签名，客户端就必需签名；】
    * */
    String HEADER_IS_SIGN = "X-xSimple-isSign";
    String PARAM_IS_SIGN = "x_isSign";

    /**
     * 客户端指定当前接口是否 需要令牌机制 【库配置不需令牌，而客户端配置令牌-通过并校验；库配置需令牌，则必需令牌】
     */
    String HEADER_SCY_TOKEN = "X-xSimple-SCY-token";
    String PARAM_SCY_TOKEN = "x_SCY_token";

    /* 客户端指定当前接口客户端是否做了加密， 【库配置不加密，而客户端配置加密---通过并校验； 库配置加密，客户端就必需加密；】
    * */
//    String HEADER_IS_CRYPTO = "X-xSimple-isCrypto";
//    String PARAM_IS_CRYPTO = "x_cryptoType";
    String HEADER_CRYPTO_TYPE = "X-xSimple-EM";
    String PARAM_CRYPTO_TYPE = "x_EM";

    String PARAM_MGM_TOKEN_EXPIRE = "expire";
    
    public final String USER_TYPE_ADMIN ="ADMIN";
    
    public final String USER_TYPE_DEVELOPER ="DEVELOPER";
    /**
     * 访问者黑名单，访问者token
     */
    String HEADER_ACCESS_TOKEN = "X-xSimple-accessToken";
    
    public final static String ADD_TYPE_PARENT ="parent";
    
    public final static String ADD_TYPE_CHILD ="child";
    
    public final static String COMMON_AGENT = "2-1";
    
    public final static String CONFIG_AGENT = "2-2";
    
    public final static String SCRIPT_AGENT = "2-3";
    
    public final static String PORT_LIST = "2-4";
    
    public final static String APP_LIST = "3-1";
    
    public final static String ACCOUNT_LIST = "4-1";
    
    public final static String ACCESS_LIST = "4-2";
    
    public final static String GLOBAL_CONFIG = "5-1";
    
    public final static String LOGIN_SCRIPT = "5-2";
    
    public final static String MONITOR_URL = "MONITOR_URL";
    
    public final static String APP_QUERY_URL = "api/v1/appstore/getAppList";
    
    public final static Integer PORT_TYPE_BLACKLIST = 1;
    
    public final static Integer PORT_TYPE_WHITELIST = 0;
    
    public final static Integer ACCESS_STATUS_VALID = 0;
    
    public final static Integer ACCESS_STATUS_INVALID = 1;
    
    public final static Integer ACCESS_TYPE_BLACK = 1;
    
    public final static Integer ACCESS_TYPE_WHITE = 0; 
}
