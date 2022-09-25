package com.catface.trade.http.web.controller.order;

import com.catface.common.model.JsonResult;
import com.catface.trade.http.config.swagger.SwaggerTagConst;
import com.catface.trade.http.web.controller.order.convert.TradeOrderWebConvert;
import com.catface.trade.http.web.controller.order.request.CreateOrderRequest;
import com.catface.trade.repository.entity.TradeOrder;
import com.catface.trade.service.order.TradeOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author catface
 * @since 2022/9/25
 */
@Api(tags = {SwaggerTagConst.TRADE_ORDER})
@Slf4j
@RestController
public class TradeOrderController {

  private final TradeOrderService tradeOrderService;

  public TradeOrderController(TradeOrderService tradeOrderService) {
    this.tradeOrderService = tradeOrderService;
  }

  @ApiOperation(value = "创建交易订单V1")
  @PostMapping(value = "/public/trade/createOrderV1")
  public JsonResult<Boolean> createOrderV1(@RequestBody @Valid CreateOrderRequest request) {
    TradeOrder entity = TradeOrderWebConvert.convert(request);
    tradeOrderService.createTradeOrderV1(entity);
    return JsonResult.success(true);
  }

  @ApiOperation(value = "创建交易订单V2")
  @PostMapping(value = "/public/trade/createOrderV2")
  public JsonResult<Boolean> createOrderV2(@RequestBody @Valid CreateOrderRequest request) {
    TradeOrder entity = TradeOrderWebConvert.convert(request);
    tradeOrderService.createTradeOrderV2(entity);
    return JsonResult.success(true);
  }


}
