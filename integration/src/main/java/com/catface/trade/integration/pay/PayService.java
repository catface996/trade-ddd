package com.catface.trade.integration.pay;

import com.catface.common.model.JsonResult;
import com.catface.pay.api.order.PayApi;
import com.catface.pay.api.order.request.CreatePayOrderRequest;
import com.catface.trade.integration.convert.PayOrderIntegrationConvert;
import com.catface.trade.integration.pay.param.CreatePayOrderParam;
import org.springframework.stereotype.Service;

/**
 * @author ：Ds
 * @description： xxx
 * @date ：2020/12/18 18:34
 */
@Service
public class PayService {

  private final PayApi payApi;

  public PayService(PayApi payApi) {
    this.payApi = payApi;
  }

  /**
   * 创建支付单
   *
   * @param param 创建支付单参数
   */
  public boolean createPayOrder(CreatePayOrderParam param) {
    CreatePayOrderRequest request = PayOrderIntegrationConvert.convert(param);
    JsonResult<Boolean> jsonResult = payApi.createPayOrder(request);
    if (jsonResult.getSuccess()) {
      return jsonResult.getData();
    }
    return false;
  }

}
