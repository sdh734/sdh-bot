package sdh.qqbot.service.impl;

import sdh.qqbot.entity.database.Infectcount;
import sdh.qqbot.mapper.InfectcountMapper;
import sdh.qqbot.service.IInfectcountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 中国疫情每日数据总和汇总 服务实现类
 * </p>
 *
 * @author SDH
 * @since 2022-07-19
 */
@Service
public class InfectcountServiceImpl extends ServiceImpl<InfectcountMapper, Infectcount> implements IInfectcountService {

}
