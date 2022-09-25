package com.catface.trade.service.order.impl;

import com.catface.common.exception.CatfaceException;
import com.catface.trade.repository.entity.PayOrder;
import com.catface.trade.repository.entity.TradeOrder;
import com.catface.trade.repository.service.PayOrderRpService;
import com.catface.trade.repository.service.TradeOrderRpService;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author catface
 * @since 2022/9/25
 */
@Slf4j
@Service
public class TradeOrderTransServiceImpl {

  private final TradeOrderRpService tradeOrderRpService;

  private final PayOrderRpService payOrderRpService;

  public TradeOrderTransServiceImpl(TradeOrderRpService tradeOrderRpService,
      PayOrderRpService payOrderRpService) {
    this.tradeOrderRpService = tradeOrderRpService;
    this.payOrderRpService = payOrderRpService;
  }

  /**
   * 创建交易订单和支付单镜像
   *
   * @param entity
   */
  @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public PayOrder createTradeOrder(TradeOrder entity) {
    // 保存交易订单
    tradeOrderRpService.save(entity);
    PayOrder payOrder = new PayOrder();
    payOrder.setTradeOrderId(entity.getId());
    payOrder.setPayAmount(entity.getTradeAmount());
    // 保存支付单
    payOrderRpService.save(payOrder);
    // 交易金额是2或者4时,回滚
    rollbackThrowException(entity);
    return payOrder;
  }

  /**
   * 更新支付单镜像状态
   *
   * @param payOrderId 支付单ID
   * @param status     支付单镜像状态
   */
  @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void updatePayOrderStatus(Long payOrderId, Integer status) {
    // for update
    PayOrder entity = new PayOrder();
    entity.setId(payOrderId);
    entity.setStatus(status);
    payOrderRpService.updateById(entity);
  }

  private void rollbackThrowException(TradeOrder entity) {
    if (entity.getTradeAmount().equals(new BigDecimal("2"))) {
      log.info("金额为2,回滚");
      throw new CatfaceException("invalid_trade_amount", "指定金额故意失败回滚");
    }
    if (entity.getTradeAmount().equals(new BigDecimal("4"))) {
      log.info("金额为4,回滚");
      throw new CatfaceException("invalid_trade_amount", "指定金额故意失败回滚");
    }
  }

}
