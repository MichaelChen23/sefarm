package com.sefarm.dao.product;

import com.sefarm.common.base.SeFarmMapper;
import com.sefarm.common.vo.ProductCommentVO;
import com.sefarm.model.product.ProductCommentDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 产品评论的数据访问层Mapper
 *
 * @author mc
 * @date 2018-3-24
 */
public interface ProductCommentMapper extends SeFarmMapper<ProductCommentDO> {

    List<ProductCommentVO> getProductCommentVOList(@Param("name")String name, @Param("productName")String productName, @Param("orderNo")String orderNo, @Param("stars")Integer stars,@Param("createTimeBegin")String createTimeBegin, @Param("createTimeEnd")String createTimeEnd, @Param("sortName")String sort, @Param("sortOrder")String order);

    ProductCommentVO getProductCommentVO(@Param("commentId") Long commentId);

}