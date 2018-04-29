package com.sefarm.service.impl.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sefarm.common.base.BaseServiceImpl;
import com.sefarm.common.util.PageKit;
import com.sefarm.common.util.StrKit;
import com.sefarm.dao.system.AdvertMapper;
import com.sefarm.model.system.AdvertDO;
import com.sefarm.service.system.IAdvertService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 广告的服务接口实现
 *
 * @author mc
 * @date 2018-3-24
 */
@Service("advertService")
public class AdvertServiceImpl extends BaseServiceImpl<AdvertMapper, AdvertDO> implements IAdvertService {

    @Override
    public PageInfo<AdvertDO> getAdvertDOList(Integer pageIndex, Integer pageSize, String sortStr, String orderStr, String name, String createTimeBegin, String createTimeEnd) {
        if (pageIndex != null && pageIndex > 0 && pageSize != null && pageSize > 0) {
            PageHelper.startPage(pageIndex, pageSize);
        }
        List<AdvertDO> list = getMapper().getAdvertDOList(name, createTimeBegin, createTimeEnd, StrKit.changeDBfieldPattern("", sortStr), orderStr);
        PageInfo<AdvertDO> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public List<AdvertDO> getAdvertPageList(Integer pageIndex, Integer pageSize) {
        Integer[] offsetArray = PageKit.transToPageOffset(pageIndex, pageSize);
        return getMapper().getAdvertPageList(offsetArray[0], offsetArray[1]);
    }

}
