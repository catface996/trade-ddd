package com.catface.trade.repository.service;

import com.catface.trade.repository.entity.PayOrder;
import com.catface.trade.repository.entity.TradeOrder;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author by catface
 * @date 2020/12/14
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRpServiceTest {

  @Autowired
  private TradeOrderRpService tradeOrderRpService;

  @Autowired
  private PayOrderRpService payOrderRpService;


  @Test
  public void testSaveTradeOrder() {
    TradeOrder entity = new TradeOrder();
    entity.setTradeAmount(new BigDecimal("10.00"));
    entity.setTradeItemNum(2);
    tradeOrderRpService.save(entity);
  }

  @Test
  public void testSavePayOrder() {
    PayOrder entity = new PayOrder();
    entity.setPayAmount(new BigDecimal("10.00"));
    entity.setTradeOrderId(11111L);
    payOrderRpService.save(entity);
  }

}