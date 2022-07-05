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
    public static final String ST_URL = "https://api.lolicon.app/setu/v2?size=small&num=10";
}
