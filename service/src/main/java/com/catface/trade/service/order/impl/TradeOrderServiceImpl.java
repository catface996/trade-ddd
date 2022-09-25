package com.catface.trade.service.order.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.catface.common.exception.CatfaceException;
import com.catface.trade.integration.pay.PayService;
import com.catface.trade.integration.pay.param.CreatePayOrderParam;
import com.catface.trade.repository.entity.PayOrder;
import com.catface.trade.repository.entity.TradeOrder;
import com.catface.trade.repository.service.PayOrderRpService;
import com.catface.trade.repository.service.TradeOrderRpService;
import com.catface.trade.service.order.TradeOrderService;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
public class TradeOrderServiceImpl implements TradeOrderService {

  private final TradeOrderTransServiceImpl tradeOrderTransServiceImpl;

  private final PayService payService;

  private final TradeOrderRpService tradeOrderRpService;

  private final PayOrderRpService payOrderRpService;

  public TradeOrderServiceImpl(TradeOrderTransServiceImpl tradeOrderTransServiceImpl,
      PayService payService,
      TradeOrderRpService tradeOrderRpService, PayOrderRpService payOrderRpService) {
    this.tradeOrderTransServiceImpl = tradeOrderTransServiceImpl;
    this.payService = payService;
    this.tradeOrderRpService = tradeOrderRpService;
    this.payOrderRpService = payOrderRpService;
  }

  /**
   * 1号版本,在事务中发起rpc请求
   *
   * @param entity
   */
  @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  @Override
  public void createTradeOrderV1(TradeOrder entity) {

    // 本地保存交易订单
    tradeOrderRpService.save(entity);

    // rpc调用,创建支付单
    CreatePayOrderParam param = convert(entity);
    param.setId(IdWorker.getId());
    payService.createPayOrder(param);

    // 交易金额为2,或者为4是,抛出异常,回滚本地事务
    rollbackThrowException(entity);
  }

  /**
   * 2号版本,不在事务中发起rpc请求
   *
   * @param entity
   */
  @Override
  public void createTradeOrderV2(TradeOrder entity) {

    // (本地事务) 创建交易单和支付单镜像
    PayOrder payOrderMirror = tradeOrderTransServiceImpl.createTradeOrder(entity);

    // 调用rpc,在支付系统创建支付单
    boolean rpcResult = payService.createPayOrder(convert(payOrderMirror));

    // 根据结果,决定是否更改本地的支付单状态
    if (rpcResult) {
      // (本地事务) 更新本地支付单镜像状态
      tradeOrderTransServiceImpl.updatePayOrderStatus(payOrderMirror.getId(), 1);
    }

  }


  public CreatePayOrderParam convert(PayOrder entity) {
    CreatePayOrderParam param = new CreatePayOrderParam();
    BeanUtils.copyProperties(entity, param);
    return param;
  }

  public CreatePayOrderParam convert(TradeOrder entity) {
    CreatePayOrderParam param = new CreatePayOrderParam();
    param.setTradeOrderId(entity.getId());
    param.setPayAmount(entity.getTradeAmount());
    return param;
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
