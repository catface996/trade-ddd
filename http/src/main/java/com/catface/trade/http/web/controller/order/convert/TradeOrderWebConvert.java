package com.catface.trade.http.web.controller.order.convert;

import com.catface.trade.http.web.controller.order.request.CreateOrderRequest;
import com.catface.trade.repository.entity.TradeOrder;
import org.springframework.cglib.beans.BeanCopier;

/**
 * @author catface
 * @since 2022/9/25
 */
public class TradeOrderWebConvert {

  private static final BeanCopier REQUEST_2_ENTITY = BeanCopier.create(CreateOrderRequest.class,
      TradeOrder.class, false);

  public static TradeOrder convert(CreateOrderRequest request) {
    TradeOrder entity = new TradeOrder();
    REQUEST_2_ENTITY.copy(request, entity, null);
    return entity;
  }

}
