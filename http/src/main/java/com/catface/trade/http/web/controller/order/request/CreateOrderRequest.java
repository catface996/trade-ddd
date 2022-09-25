package com.catface.trade.http.web.controller.order.request;

import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author catface
 * @since 2022/9/25
 */
@Data
public class CreateOrderRequest {

  @ApiModelProperty(value = "交易金额", required = true, example = "1")
  @NotNull(message = "交易金额不能为空")
  private BigDecimal tradeAmount;

  @ApiModelProperty(value = "购买商品数", required = true, example = "22")
  @NotNull(message = "购买商品数量不能为空")
  private Integer tradeItemNum;

}
