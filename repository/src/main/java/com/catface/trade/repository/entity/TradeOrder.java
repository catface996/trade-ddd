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
 * 交易订单
 * </p>
 *
 * @author catface
 * @since 2022-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TradeOrder对象", description="交易订单")
public class TradeOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private BigDecimal tradeAmount;

    @ApiModelProperty(value = "购买商品数量")
    private Integer tradeItemNum;


}
