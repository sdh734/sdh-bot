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
    public static final String SADSAYINGS_URL = "https://api.wuxixindong.top/api/sgyl.php";
    //二次元壁纸Api
    public static final String TWODIMENSIONALSPACE_URL = "https://tenapi.cn/acg?return=json";
    //每日早报api
    public static final String DAILYNEWS_URL = "https://v2.alapi.cn/api/zaobao";
    //疫情风险地区api
    public static final String NCOV_URL = "https://v2.alapi.cn/api/zaobao";
    //摸鱼人Api
    public static final String SLACKOFF = "https://api.j4u.ink/v1/store/other/proxy/remote/moyu.json";

    //网易疫情数据接口
    public final static String NETEASYNCOV_API = "https://c.m.163.com/ug/api/wuhan/app/data/list-total";
    //百度疫情数据接口
    public final static String BAIDUNCOV_API = "https://voice.baidu.com/newpneumonia/getv2?from=mola-virus&stage=publish&target=trend&area=";
    //腾讯疫情接口
    public final static String TENCENTNCOVALL_API = "https://api.inews.qq.com/newsqa/v1/query/inner/publish/modules/list?modules=diseaseh5Shelf";
    //腾讯按城市查询疫情接口
    public final static String TENCENTNCOVCITY_API = "https://api.inews.qq.com/newsqa/v1/query/pubished/daily/list?limit=1&adCode=";
    //腾讯疫情风险地区接口
    public final static String TENCENTNCOVAREA_API = "https://v.qq.com/cache/wuji/object?appid=disease_map&schemaid=riskArea&schemakey=c037e2cccdb54682ba7d3fa516a5b830&filter=";
    //cos图片api
    public final static String COSIMG_API = "https://yang520.ltd/api/cos.php";
    //点歌api，用的酷我音乐接口
    public final static String SONG_API = "https://yang520.ltd/api/kw.php";
    //点歌api搜索歌曲id（网易云）
    public final static String SONG_163_API = "http://music.163.com/api/search/pc";
    //早安语录api
    public final static String GOODMORNING_API = "https://yang520.ltd/api/getNewTheme.php";
    //妹纸图片Api
    public final static String THEGIRLIMG_API = "https://yang520.ltd/api/sjmnt.php";

}
