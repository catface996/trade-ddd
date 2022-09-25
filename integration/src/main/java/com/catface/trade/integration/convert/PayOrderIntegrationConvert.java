package com.catface.trade.integration.convert;

import com.catface.pay.api.order.request.CreatePayOrderRequest;
import com.catface.trade.integration.pay.param.CreatePayOrderParam;
import org.springframework.beans.BeanUtils;

/**
 * @author catface
 * @since 2022/9/25
 */
public class PayOrderIntegrationConvert {

  public static CreatePayOrderRequest convert(CreatePayOrderParam param) {
    CreatePayOrderRequest request = new CreatePayOrderRequest();
    BeanUtils.copyProperties(param, request);
    return request;
  }

}
