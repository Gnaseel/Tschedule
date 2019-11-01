package com.aio.ex.page;

public class Paging {
	private static int pageCount=1;
	private int blockStartnum=0;
	private int blockLastNum=0;
	private int lastPageNum=0;
	public static int getPageCount() {
		return pageCount;
	}
	public static void setPageCount(int pageCount) {
		Paging.pageCount = pageCount;
	}
	public int getBlockStartnum() {
		return blockStartnum;
	}
	public void setBlockStartnum(int blockStartnum) {
		this.blockStartnum = blockStartnum;
	}
	public int getBlockLastNum() {
		return blockLastNum;
	}
	public void setBlockLastNum(int blockLastNum) {
		this.blockLastNum = blockLastNum;
	}
	public int getLastPageNum() {
		return lastPageNum;
	}
	public void setLastPageNum(int lastPageNum) {
		this.lastPageNum = lastPageNum;
	}
	public void makeBlock(int curPage) {
		int blockNum=0;
		blockNum=(int)Math.floorDiv(curPage-1, pageCount);
		
	}
}
