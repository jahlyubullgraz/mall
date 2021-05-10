package com.newwing.fenxiao.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.newwing.fenxiao.entities.Admin;
import com.newwing.fenxiao.entities.Role;
import com.newwing.fenxiao.service.IRoleService;
import com.newwing.fenxiao.utils.BjuiJson;
import com.newwing.fenxiao.utils.FreemarkerUtils;

import freemarker.template.Configuration;

@Controller("roleAction")
@Scope("prototype")
public class RoleAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Resource(name = "roleService")
	private IRoleService<Role> roleService;
	
	private Role role;

	public void list() {
		this.cfg = new Configuration();
		this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/admin");
		List roleList = this.roleService.list("from Role where deleted = 0 order by id desc");
		Map root = new HashMap();
		root.put("roleList", roleList);
		FreemarkerUtils.freemarker(this.request, this.response, "roleList.ftl", this.cfg, root);
	}

	public void add() {
		this.cfg = new Configuration();
		this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/admin");
		Map root = new HashMap();
		FreemarkerUtils.freemarker(this.request, this.response, "roleAdd.ftl", this.cfg, root);
	}

	public void save() {
		PrintWriter out = null;
		try {
			out = this.response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String callbackData = "";
		try {
			HttpSession session = this.request.getSession();
			Admin loginAdmin = (Admin) session.getAttribute("loginAdmin");
			if (loginAdmin.getJuri() == 0) {
				callbackData = BjuiJson.json("300", "权限不足", "", "", "", "", "", "");
			} else if (this.role == null) {
				callbackData = BjuiJson.json("300", "参数错误", "", "", "", "", "", "");
			} else {
				this.role.setDeleted(false);
				this.role.setCreateDate(new Date());
				boolean result = this.roleService.saveOrUpdate(this.role);
				if (result)
					callbackData = BjuiJson.json("200", "添加成功", "", "", "", "true", "", "");
				else
					callbackData = BjuiJson.json("300", "添加失败", "", "", "", "", "", "");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		out.print(callbackData);
		out.flush();
		out.close();
	}
	
	public void info() {
		String idStr = this.request.getParameter("id");
		HttpSession session = this.request.getSession();
		Admin loginAdmin = (Admin) session.getAttribute("loginAdmin");
		String callbackData = "";
		PrintWriter out = null;
		try {
			out = this.response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if (loginAdmin.getJuri() == 0) {
				callbackData = BjuiJson.json("300", "权限不足", "", "", "", "", "", "");
			} else if ((idStr == null) || ("".equals(idStr))) {
				callbackData = BjuiJson.json("300", "参数不能为空", "", "", "", "", "", "");
			} else {
				int id = 0;
				try {
					id = Integer.parseInt(idStr);
				} catch (Exception e) {
					callbackData = BjuiJson.json("300", "参数错误", "", "", "", "", "", "");
				}
				Role findRole = (Role) this.roleService.findById(Role.class, id);
				if (findRole == null) {
					callbackData = BjuiJson.json("300", "菜单不存在", "", "", "", "", "", "");
				} else {
					this.cfg = new Configuration();
					this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(),
							"WEB-INF/templates/admin");
					Map root = new HashMap();
					root.put("role", findRole);
					FreemarkerUtils.freemarker(this.request, this.response, "roleEdit.ftl", this.cfg, root);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		out.print(callbackData);
		out.flush();
		out.close();
	}

	public void update() {
		PrintWriter out = null;
		try {
			out = this.response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String callbackData = "";
		try {
			HttpSession session = this.request.getSession();
			Admin loginAdmin = (Admin) session.getAttribute("loginAdmin");
			if (loginAdmin.getJuri() == 0) {
				callbackData = BjuiJson.json("300", "权限不足", "", "", "", "", "", "");
			} else if (this.role == null) {
				callbackData = BjuiJson.json("300", "参数错误", "", "", "", "", "", "");
			} else {
//				Role findRole = (Role) this.roleService.findById(Role.class, this.role.getId().intValue());
				boolean result = this.roleService.saveOrUpdate(role);
				if (result) {
					callbackData = BjuiJson.json("200", "修改成功", "", "", "", "true", "", "");
				} else {
					callbackData = BjuiJson.json("300", "修改失败", "", "", "", "", "", "");
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		out.print(callbackData);
		out.flush();
		out.close();
	}

	public void delete() {
		PrintWriter out = null;
		try {
			out = this.response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String callbackData = "";
		try {
			HttpSession session = this.request.getSession();
			Admin loginAdmin = (Admin) session.getAttribute("loginAdmin");
			if (loginAdmin.getJuri() == 0) {
				callbackData = BjuiJson.json("300", "权限不足", "", "", "", "", "", "");
			} else {
				String idStr = this.request.getParameter("id");

				if ((idStr == null) || ("".equals(idStr))) {
					callbackData = BjuiJson.json("300", "参数错误", "", "", "", "", "", "");
				} else {
					int id = 0;
					try {
						id = Integer.parseInt(idStr);
					} catch (Exception e) {
						callbackData = BjuiJson.json("300", "参数错误", "", "", "", "", "", "");
					}
					Role findRole = (Role) this.roleService.findById(Role.class, id);
					if (findRole == null) {
						callbackData = BjuiJson.json("300", "角色不存在", "", "", "", "", "", "");
					} else {
						boolean result = this.roleService.delete(findRole);
						if (result) {
							callbackData = BjuiJson.json("200", "删除成功", "", "", "", "", "", "");
						} else {
							callbackData = BjuiJson.json("300", "删除失败", "", "", "", "", "", "");
						}
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		out.print(callbackData);
		out.flush();
		out.close();
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
