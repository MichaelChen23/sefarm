package com.sefarm.config.beetl;

import com.sefarm.util.ToolUtil;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;

/**
 * 在html页面调用该beetl模板的java方法
 *
 * @author mc
 * @date 2018-3-30
 */
public class BeetlConfiguration extends BeetlGroupUtilConfiguration {

	@Override
	public void initOther() {

//		groupTemplate.registerFunctionPackage("shiro", new ShiroExt());
		groupTemplate.registerFunctionPackage("tool", new ToolUtil());

	}

}
