package sdh.qqbot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import sdh.qqbot.dao.User;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author SDH
 * @since 2022-02-01
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
