package sdh.qqbot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import sdh.qqbot.entity.database.Winners;

/**
 *  抽奖获胜者Mapper 接口
 *
 * @author SDH
 * @since 2022-01-09
 */
@Mapper
public interface WinnersMapper extends BaseMapper<Winners> {

}
