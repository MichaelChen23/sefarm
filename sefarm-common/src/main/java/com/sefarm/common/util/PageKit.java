package com.sefarm.common.util;

import com.sefarm.common.Constant;

/**
 * 分页工具类
 *
 * @author mc
 * @date 2018-3-30
 */
public class PageKit {

	/**
	 * 将页数和每页条目数转换为开始位置和结束位置<br>
	 * 此方法用于不包括结束位置的分页方法<br>
	 * 例如：<br>
	 * 页码：1，每页10 -> [0, 10]<br>
	 * 页码：2，每页10 -> [10, 20]<br>
	 * 。。。<br>
	 * 
	 * @param pageNo
	 *            页码（从1计数）
	 * @param countPerPage
	 *            每页条目数
	 * @return 第一个数为开始位置，第二个数为结束位置
	 */
	public static int[] transToStartEnd(int pageNo, int countPerPage) {
		if (pageNo < 1) {
			pageNo = 1;
		}

		if (countPerPage < 1) {
			countPerPage = 0;
//			LogKit.warn("Count per page  [" + countPerPage + "] is not valid!");
		}

		int start = (pageNo - 1) * countPerPage;
		int end = start + countPerPage;

		return new int[] { start, end };
	}

	/**
	 * 根据总数计算总页数
	 * 
	 * @param totalCount
	 *            总数
	 * @param numPerPage
	 *            每页数
	 * @return 总页数
	 */
	public static int totalPage(int totalCount, int numPerPage) {
		if (numPerPage == 0) {
			return 0;
		}
		return totalCount % numPerPage == 0 ? (totalCount / numPerPage)
				: (totalCount / numPerPage + 1);
	}

	/**
	 * 根据页数和页码转化为数据库sql的limit的offset, row，
	 * add by mc 2018-4-23
	 * 例如：<br>
	 * 页码：1，每页10 -> [0, 10]<br>
	 * 页码：2，每页10 -> [10, 20]<br>
	 *
	 * @param pageIndex 页数 默认第1页
	 * @param pageSize 页码，每页多少行，默认10行
	 * @return 第一个数为开始位置offset，第二个数为接下去的第几行row
	 */
	public static Integer[] transToPageOffset(Integer pageIndex, Integer pageSize) {
		if (pageIndex < 1) {
			//默认为第一页
			pageIndex = Constant.DEFAULT_PAGE_INDEX;
		}

		if (pageSize < 1) {
			//默认为每页10行
			pageSize = Constant.DEFAULT_ROWS;
		}

		Integer offset = (pageIndex - 1) * pageSize;
		Integer row = pageSize;

		return new Integer[] { offset, row };
	}
}
