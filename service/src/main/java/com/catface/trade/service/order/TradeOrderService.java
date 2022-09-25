package com.catface.trade.service.order;

import com.catface.trade.repository.entity.TradeOrder;

/**
 * @author catface
 * @since 2022/9/25
 */
public interface TradeOrderService {

  /**
   * 1号版本,在事务中发起rpc请求
   *
   * @param entity
   */
  void createTradeOrderV1(TradeOrder entity);

  /**
   * 2号版本,不在事务中发起rpc请求
   *
   * @param entity
   */
  void createTradeOrderV2(TradeOrder entity);


}
