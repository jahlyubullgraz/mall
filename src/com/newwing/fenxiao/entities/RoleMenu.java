package com.newwing.fenxiao.entities;

/**
 * 角色权限
 */
public class RoleMenu extends BaseBean {
	
	private static final long serialVersionUID = 1L;

	private String roleId;//角色ID
	private String menuId;//菜单ID
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	
}
