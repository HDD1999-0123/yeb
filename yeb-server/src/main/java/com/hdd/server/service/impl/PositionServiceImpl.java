package com.hdd.server.service.impl;

import com.hdd.server.pojo.Position;
import com.hdd.server.mapper.PositionMapper;
import com.hdd.server.service.IPositionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yfxu
 * @since 2021-04-21
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements IPositionService {

}
