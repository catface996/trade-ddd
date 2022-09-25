package com.catface.trade.repository.service.impl;

import com.catface.trade.repository.entity.TradeOrder;
import com.catface.trade.repository.mapper.TradeOrderMapper;
import com.catface.trade.repository.service.TradeOrderRpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 交易订单 服务实现类
 * </p>
 *
 * @author catface
 * @since 2022-09-25
 */
@Service
public class TradeOrderRpServiceImpl extends ServiceImpl<TradeOrderMapper, TradeOrder> implements TradeOrderRpService {

}
