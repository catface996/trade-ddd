package com.catface.trade.integration.pay.param;

import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Data;

/**
 * @author catface
 * @since 2022/9/25
 */
@Data
public class CreatePayOrderParam {

  @ApiModelProperty(value = "支付单ID")
  private Long id;

  @ApiModelProperty(value = "交易订单ID")
  private Long tradeOrderId;

  @ApiModelProperty(value = "支付金额")
  private BigDecimal payAmount;

}
