package com.catface.trade.repository.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 支付单
 * </p>
 *
 * @author catface
 * @since 2022-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PayOrder对象", description="支付单")
public class PayOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "支付单ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "交易订单ID")
    private Long tradeOrderId;

    @ApiModelProperty(value = "支付金额")
    private BigDecimal payAmount;


}
