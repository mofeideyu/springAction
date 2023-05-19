package top.mofeideyu.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 产品对象 product
 * 
 * @author zheng
 * @date 2022-03-15
 */
@Data
@TableName("product")
public class Product {
    
    private static final long serialVersionUID = 1L;

    /** 产品ID */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 物料编号 */
    private String materialNo;

    /** 产品类型 */
    private Integer productType;

    /** 目录ID */
    private Long catalogId;

    /** 目录名称 */
    private String catalogName;

    /** 产品名称 */
    private String productName;

    /** 产品型号 */
    private String productModel;

    /** 产品版本 */
    private String productEdition;

    /** 封装规格 */
    private String packageSpecifications;

    /** 最小包装量 */
    private String minQuantity;

    /** 供应商ID */
    private Long supplierId;

    /** 供应商名称 */
    private String supplierName;

    /** 品牌ID */
    private Long brandId;

    /** 品牌名称 */
    private String brandName;

    /** 厂家型号 */
    private String manufacturerModel;

    /** 成本 */
    private BigDecimal cost;

    /** 销售指导价 */
    private BigDecimal sellingPrice;

    /** 是否开放 */
    @TableField("is_open")
    private Integer open;

    /**是否对外销售开放*/
    private Integer saleOpen;

    /** 库存总量 */
    private Integer stockNumber;

    /** 占用数量 */
    private Integer occupyNumber;

    /** 审核状态（-1未提交 0待审核 1审核通过 2审核不通过） */
    private Integer auditStatus;

    /** 审批实例 */
    private String processInstanceId;

    /** 产品更新ID */
    private Long updateId;

    /** 创建人ID */
    private Long createId;
}
