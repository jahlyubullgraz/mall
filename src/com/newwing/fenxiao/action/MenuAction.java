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
import com.newwing.fenxiao.entities.Menu;
import com.newwing.fenxiao.service.IMenuService;
import com.newwing.fenxiao.utils.BjuiJson;
import com.newwing.fenxiao.utils.FreemarkerUtils;

import freemarker.template.Configuration;

@Controller("menuAction")
@Scope("prototype")
public class MenuAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Resource(name = "menuService")
	private IMenuService<Menu> menuService;
	
	private Menu menu;

	public void list() {
		this.cfg = new Configuration();
		this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/admin");
		List menuList = this.menuService.list("from Menu where deleted = 0 order by id desc");
		Map root = new HashMap();
		root.put("menuList", menuList);
		FreemarkerUtils.freemarker(this.request, this.response, "menuList.ftl", this.cfg, root);
	}

	public void add() {
		this.cfg = new Configuration();
		this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/admin");
		Map root = new HashMap();
		FreemarkerUtils.freemarker(this.request, this.response, "menuAdd.ftl", this.cfg, root);
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
			} else if (this.menu == null) {
				callbackData = BjuiJson.json("300", "参数错误", "", "", "", "", "", "");
			} else {
				this.menu.setDeleted(false);
				this.menu.setCreateDate(new Date());
				boolean result = this.menuService.saveOrUpdate(this.menu);
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
				Menu findMenu = (Menu) this.menuService.findById(Menu.class, id);
				if (findMenu == null) {
					callbackData = BjuiJson.json("300", "菜单不存在", "", "", "", "", "", "");
				} else {
					this.cfg = new Configuration();
					this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(),
							"WEB-INF/templates/admin");
					Map root = new HashMap();
					root.put("menu", findMenu);
					FreemarkerUtils.freemarker(this.request, this.response, "menuEdit.ftl", this.cfg, root);
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
			} else if (this.menu == null) {
				callbackData = BjuiJson.json("300", "参数错误", "", "", "", "", "", "");
			} else {
//				Menu findMenu = (Menu) this.menuService.findById(Menu.class, this.menu.getId().intValue());
				boolean result = this.menuService.saveOrUpdate(menu);
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
					Menu findMenu = (Menu) this.menuService.findById(Menu.class, id);
					if (findMenu == null) {
						callbackData = BjuiJson.json("300", "菜单不存在", "", "", "", "", "", "");
					} else {
						boolean result = this.menuService.delete(findMenu);
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

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

}
