package com.newwing.fenxiao.entities;

/**
 * 系统菜单
 */
public class Menu extends BaseBean {
	
	private String menuName;//菜单名称
	private String menuUrl;//菜单URL
	
	private Integer sort;//顺序

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}
