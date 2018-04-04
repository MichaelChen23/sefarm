package com.sefarm.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sefarm.common.node.ZTreeNode;
import com.sefarm.controller.common.BaseController;
import com.sefarm.service.system.ISysDeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 系统部门组织的Controller
 *
 * @author mc
 * @date 2018-4-4
 */
@Controller
@RequestMapping("/sys-dept")
public class SysDeptController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysDeptController.class);

    /**
     * 网页路径的前缀
     */
    private static String PREFIX = "/system/sysdept/";

    @Reference(version = "1.0.0", timeout = 10000)
    public ISysDeptService sysDeptService;

    /**
     * 跳转到查看系统部门列表的页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "sysdept.html";
    }

    /**
     * 获取部门的tree列表
     */
    @RequestMapping(value = "/getZtree")
    @ResponseBody
    public List<ZTreeNode> getZtree() {
        List<ZTreeNode> tree = sysDeptService.getDeptZtree();
        tree.add(ZTreeNode.createParent());
        return tree;
    }


}
