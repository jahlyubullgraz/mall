package com.newwing.fenxiao.entities;

import java.util.List;

/**
 * 系统部门
 */
public class Dept extends BaseBean {

	private static final long serialVersionUID = 1L;

	public static final String STATUS_NORMAL = "1";
	public static final String STATUS_FORBIDEN = "0";
	
	private String parentDeptId;//上级部门id
	private String deptName;//部门名称
	private String deptLevel;//部门级别
	private String deptType;//部门类型
	private String status;//状态
	
	private List<Dept> subDeptList;// 下级部门
	
	private String url;// 跳转链接
	private String target;// 跳转方式
	
	public String getParentDeptId() {
		return parentDeptId;
	}
	
	public void setParentDeptId(String parentDeptId) {
		this.parentDeptId = parentDeptId;
	}
	
	public String getDeptName() {
		return deptName;
	}
	
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public String getDeptLevel() {
		return deptLevel;
	}
	
	public void setDeptLevel(String deptLevel) {
		this.deptLevel = deptLevel;
	}
	
	public String getDeptType() {
		return deptType;
	}
	
	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public List<Dept> getSubDeptList() {
		return subDeptList;
	}

	public void setSubDeptList(List<Dept> subDeptList) {
		this.subDeptList = subDeptList;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

}
