package sdh.qqbot.config;

/**
 * 保存需要的api链接。
 */
public class ApiUrlConfig {
    //正向WS连接地址
    public static final String WS_URL = "ws://127.0.0.1:6700";
    //WallHaven搜索Api
    public static final String WALLHAVEN_BASE_SEARCH_URL = "https://wallhaven.cc/api/v1/search";
    //WallHaven图片详情Api
    public static final String WALLHAVEN_BASE_INFO_URL = "https://wallhaven.cc/api/v1/w/";
    //高德地图地理位置编码Api
    public static final String AMAP_URL = "https://restapi.amap.com/v3/geocode/geo?key=";
    //彩云天气获取天气Api
    public static final String CY_URL = "https://api.caiyunapp.com/v2.5/";
    //色图Api
    public static final String ST_URL = "https://api.lolicon.app/setu/v2?size=original&size=small&num=10";
    //Server酱通知Api
    public static final String SERVERCHAN_URL = "https://sctapi.ftqq.com/" + ApiKeyConfig.SERVERCHAN_TOKEN + ".send";
    //PushPlus推送Api
    public static final String PUSHPLUS_URL = "http://www.pushplus.plus/send";
    //语录Api
    public static final String SAYING_URL = "https://api.oddfar.com/yl/q.php";
    //每日一言Api
    public static final String AWORDADAY_URL = "https://v.api.aa1.cn/api/yiyan/index.php";
    //古诗词一言Api
    public static final String GUSHI_URL = "https://v1.jinrishici.com/";
    //舔狗日记Api
    public static final String LICKTHEDOGDIARY_URL = "https://api.ixiaowai.cn/tgrj/index.php/?code=json";
    //历史上的今天Api
    public static final String TODAYONHISTORY_URL = "https://tenapi.cn/lishi/?format=json";
    //伤感语录Api
    public static final String SADSAYINGS_URL = "http://api.wuxixindong.top/api/sgyl.php";
    //手绘壁纸Api
    public static final String  TWODIMENSIONALSPACE = "https://tenapi.cn/acg?return=json";
    public static final String SADSAYINGS_URL = "https://api.wuxixindong.top/api/sgyl.php";
    //每日早报api
    public static final String DAILYNEWS_URL = "https://v2.alapi.cn/api/zaobao";
    //疫情风险地区api
    public static final String NCOV_URL = "https://v2.alapi.cn/api/zaobao";
}
