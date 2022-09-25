package com.catface.trade.repository.service.impl;

import com.catface.trade.repository.entity.PayOrder;
import com.catface.trade.repository.mapper.PayOrderMapper;
import com.catface.trade.repository.service.PayOrderRpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付单 服务实现类
 * </p>
 *
 * @author catface
 * @since 2022-09-25
 */
@Service
public class PayOrderRpServiceImpl extends ServiceImpl<PayOrderMapper, PayOrder> implements PayOrderRpService {

}
