package sdh.qqbot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import sdh.qqbot.entity.database.Blacklist;

/**
 *  黑名单Mapper 接口
 *
 * @author SDH
 * @since 2022-01-08
 */
@Mapper
public interface BlacklistMapper extends BaseMapper<Blacklist> {

}
