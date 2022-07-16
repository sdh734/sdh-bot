package sdh.qqbot.controller;

import com.alibaba.fastjson.JSON;
import sdh.qqbot.config.ApiUrlConfig;
import sdh.qqbot.entity.TwoDimensionalSpaceEntity;
import sdh.qqbot.utils.OkHttpUtil;

/**
 * 二次元接口
 * @author fusheng
 */
public class TwoDimensionalSpaceController {

    public static TwoDimensionalSpaceEntity queryTwoDimensionalSpace(){
        String url = ApiUrlConfig.TWODIMENSIONALSPACE;
        String json = OkHttpUtil.get(url);
        return JSON.parseObject(json, TwoDimensionalSpaceEntity.class);
    }
}
