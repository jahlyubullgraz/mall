package com.newwing.fenxiao.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.newwing.fenxiao.entities.Config;
import com.newwing.fenxiao.entities.Message;
import com.newwing.fenxiao.service.IConfigService;
import com.newwing.fenxiao.service.IMessageService;
import com.newwing.fenxiao.utils.BjuiJson;
import com.newwing.fenxiao.utils.BjuiPage;
import com.newwing.fenxiao.utils.FreemarkerUtils;
import com.newwing.fenxiao.utils.PageModel;

import freemarker.template.Configuration;

@Controller("messageAction")
@Scope("prototype")
public class MessageAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Resource(name = "messageService")
	private IMessageService<Message> messageService;
	private Message message;

	@Resource(name = "configService")
	private IConfigService<Config> configService;

	public void list() {
		int count = this.messageService.getTotalCount("select count(*) from Message where deleted=0", new Object[0]);
		this.page = new BjuiPage(this.pageCurrent, this.pageSize);
		this.page.setTotalCount(count);
		List list = this.messageService.list("from Message where deleted=0 order by id desc", this.page.getStart(),
				this.page.getPageSize(), new Object[0]);
		this.cfg = new Configuration();

		this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/admin");
		Map root = new HashMap();
		root.put("list", list);
		root.put("page", this.page);
		FreemarkerUtils.freemarker(this.request, this.response, "messageList.ftl", this.cfg, root);
	}

	public void indexList() {
		String pStr = this.request.getParameter("p");
		int p = 1;
		if ((pStr != null) && (!"".equals(pStr))) {
			p = Integer.parseInt(pStr);
		}

		int count = this.messageService.getTotalCount("select count(*) from Message where deleted=0", new Object[0]);
		PageModel pageModel = new PageModel();
		pageModel.setPageSize(16);
		pageModel.setAllCount(count);
		pageModel.setCurrentPage(p);
		List list = this.messageService.list("from Message where deleted=0 order by id desc", pageModel.getStart(),
				pageModel.getPageSize(), new Object[0]);
		this.cfg = new Configuration();

		this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/index");
		Map root = new HashMap();
		root.put("messageList", list);
		root.put("page", pageModel.getPageStr("messageList.do?p="));
		Config config = (Config) this.configService.findById(Config.class, 1);
		root.put("config", config);
		FreemarkerUtils.freemarker(this.request, this.response, "messageList.ftl", this.cfg, root);
	}

	public void add() {
		this.cfg = new Configuration();

		this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/index");
		Map root = new HashMap();
		Config config = (Config) this.configService.findById(Config.class, 1);
		root.put("config", config);
		FreemarkerUtils.freemarker(this.request, this.response, "messageAdd.ftl", this.cfg, root);
	}

	public void save() {
		String callbackData = "";

		this.message.setCreateDate(new Date());
		this.message.setDeleted(false);
		boolean result = this.messageService.saveOrUpdate(this.message);
		if (result)
			callbackData = "<script>alert('????????????');window.location.href='messageList.do';</script>";
		else {
			callbackData = "<script>alert('????????????????????????');window.location.href='javascript:history.go(-1)';</script>";
		}
		PrintWriter out = null;
		try {
			out = this.response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(callbackData);
		out.flush();
		out.close();
	}

	public void info() {
		String idStr = this.request.getParameter("id");
		String callbackData = "";
		PrintWriter out = null;
		try {
			out = this.response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if ((idStr == null) || ("".equals(idStr))) {
				callbackData = BjuiJson.json("300", "????????????", "", "", "", "", "", "");
				out.print(callbackData);
				out.flush();
				out.close();
			} else {
				int id = 0;
				try {
					id = Integer.parseInt(idStr);
				} catch (Exception e) {
					callbackData = BjuiJson.json("300", "????????????", "", "", "", "", "", "");
					out.print(callbackData);
					out.flush();
					out.close();
				}
				Message findMessage = (Message) this.messageService.findById(Message.class, id);
				if (findMessage == null) {
					callbackData = BjuiJson.json("300", "???????????????", "", "", "", "", "", "");
					out.print(callbackData);
					out.flush();
					out.close();
				} else {
					this.cfg = new Configuration();

					this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(),
							"WEB-INF/templates/admin");
					Map root = new HashMap();
					root.put("message", findMessage);
					FreemarkerUtils.freemarker(this.request, this.response, "messageReply.ftl", this.cfg, root);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void indexInfo() {
		String idStr = this.request.getParameter("id");
		String callbackData = "";
		PrintWriter out = null;
		try {
			out = this.response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if ((idStr == null) || ("".equals(idStr))) {
			callbackData = "????????????";
			out.print(callbackData);
			out.flush();
			out.close();
		} else {
			int id = 0;
			try {
				id = Integer.parseInt(idStr);
			} catch (Exception e) {
				callbackData = "????????????";
				out.print(callbackData);
				out.flush();
				out.close();
			}
			Message findMessage = (Message) this.messageService.findById(Message.class, id);
			if (findMessage == null) {
				callbackData = "???????????????";
				out.print(callbackData);
				out.flush();
				out.close();
			} else {
				this.cfg = new Configuration();

				this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(),
						"WEB-INF/templates/index");
				Map root = new HashMap();
				root.put("message", findMessage);
				Config config = (Config) this.configService.findById(Config.class, 1);
				root.put("config", config);
				FreemarkerUtils.freemarker(this.request, this.response, "message.ftl", this.cfg, root);
			}
		}
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
			if (this.message == null) {
				callbackData = BjuiJson.json("300", "????????????", "", "", "", "", "", "");
			} else {
				Message findMessage = (Message) this.messageService.findById(Message.class,
						this.message.getId().intValue());
				this.message.setDeleted(findMessage.isDeleted());
				this.message.setCreateDate(findMessage.getCreateDate());
				this.message.setVersion(findMessage.getVersion());
				boolean result = this.messageService.saveOrUpdate(this.message);

				if (result) {
					callbackData = BjuiJson.json("200", "????????????", "", "", "", "true", "", "");
				} else
					callbackData = BjuiJson.json("300", "????????????", "", "", "", "", "", "");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		out.print(callbackData);
		out.flush();
		out.close();
	}

	public void delete() {
		String idStr = this.request.getParameter("id");
		String callbackData = "";
		PrintWriter out = null;
		try {
			out = this.response.getWriter();

			if ((idStr == null) || ("".equals(idStr))) {
				callbackData = BjuiJson.json("300", "????????????", "", "", "", "", "", "");
			} else {
				int id = 0;
				try {
					id = Integer.parseInt(idStr);
				} catch (Exception e) {
					callbackData = BjuiJson.json("300", "????????????", "", "", "", "", "", "");
				}
				Message findMessage = (Message) this.messageService.findById(Message.class, id);
				if (findMessage == null) {
					callbackData = BjuiJson.json("300", "?????????????????????", "", "", "", "", "", "");
				} else {
					boolean result = this.messageService.delete(findMessage);
					if (result)
						callbackData = BjuiJson.json("200", "????????????", "", "", "", "", "", "");
					else
						callbackData = BjuiJson.json("300", "????????????", "", "", "", "", "", "");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		out.print(callbackData);
		out.flush();
		out.close();
	}

	public Message getMessage() {
		return this.message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
	
}