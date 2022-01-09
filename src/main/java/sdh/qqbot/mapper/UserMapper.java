package sdh.qqbot.mapper;

import sdh.qqbot.dao.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author SDH
 * @since 2022-01-08
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
