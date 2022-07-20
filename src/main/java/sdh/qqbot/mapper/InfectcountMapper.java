package sdh.qqbot.mapper;

import sdh.qqbot.entity.database.Infectcount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 中国疫情每日数据总和汇总 Mapper 接口
 * </p>
 *
 * @author SDH
 * @since 2022-07-19
 */
@Mapper
public interface InfectcountMapper extends BaseMapper<Infectcount> {

}
