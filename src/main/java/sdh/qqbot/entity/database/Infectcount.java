package sdh.qqbot.entity.database;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 中国疫情每日数据总和汇总
 * </p>
 *
 * @author SDH
 * @since 2022-07-17
 */
@Getter
@Setter
@TableName("t_infectcount")
public class Infectcount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 国家名
     */
    private String countryName;

    /**
     * 省市名
     */
    private String provinceName;

    /**
     * 城市名
     */
    private String cityName;

    /**
     * 今日累计确诊
     */
    private Integer todayConfirm;

    /**
     * 今日治愈
     */
    private Integer todayHeal;

    /**
     * 今日死亡
     */
    private Integer todayDead;

    /**
     * 今日现有确诊
     */
    private Integer todayStoreconfirm;

    /**
     * 今日境外输入
     */
    private Integer todayInput;

    /**
     * 今日无症状
     */
    private Integer todaySuspect;

    /**
     * 今日危重症患者
     */
    private Integer todaySevere;
    /**
     * 今日新增本土确诊
     */
    private Integer todayLocation;

    /**
     * 总计累计确诊
     */
    private Integer totalConfirm;

    /**
     * 总计治愈
     */
    private Integer totalHeal;

    /**
     * 总计死亡
     */
    private Integer totalDead;

    /**
     * 总计现有确诊
     */
    private Integer totalStoreconfirm;

    /**
     * 总计境外输入
     */
    private Integer totalInput;

    /**
     * 总计无症状
     */
    private Integer totalSuspect;

    /**
     * 总计危重症患者
     */
    private Integer totalSevere;
    /**
     * 现有本土确诊
     */
    private Integer totalLocation;
    /**
     * 数据更新时间
     */
    private Date updateDate;


}
