package com.newwing.fenxiao.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.newwing.fenxiao.entities.Config;
import com.newwing.fenxiao.entities.Kami;
import com.newwing.fenxiao.entities.Product;
import com.newwing.fenxiao.service.IConfigService;
import com.newwing.fenxiao.service.IKamiService;
import com.newwing.fenxiao.service.IProductService;
import com.newwing.fenxiao.utils.BjuiJson;
import com.newwing.fenxiao.utils.BjuiPage;
import com.newwing.fenxiao.utils.FreemarkerUtils;
import com.opensymphony.xwork2.ActionContext;

import freemarker.template.Configuration;

@Controller("kamiAction")
@Scope("prototype")
public class KamiAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Resource(name = "kamiService")
	private IKamiService<Kami> kamiService;

	@Resource(name = "configService")
	private IConfigService<Config> configService;

	@Resource(name = "productService")
	private IProductService<Product> productService;
	private Kami Kami;
	private List<File> Filedata;
	private List<String> FiledataFileName;
	private List<String> FiledataContentType;
	private String name;

	public void list() {
		String key = this.request.getParameter("key");

		String pid = this.request.getParameter("pid");

		int count = 0;
		String countHql = "select count(*) from Kami where deleted=0";
		String hql = "from Kami where deleted=0";
		if (StringUtils.isNotEmpty(key)) {
			countHql = countHql + " and no like '" + key + "'";
			hql = hql + " and no like '" + key + "'";
		}
		if (StringUtils.isNotEmpty(pid)) {
			countHql = countHql + " and product.id=" + pid;
			hql = hql + " and product.id=" + pid;
		}
		hql = hql + " order by id desc";
		count = this.kamiService.getTotalCount(countHql, new Object[0]);
		this.page = new BjuiPage(this.pageCurrent, this.pageSize);
		this.page.setTotalCount(count);
		List list = this.kamiService.list(hql, this.page.getStart(), this.page.getPageSize(), new Object[0]);
		this.cfg = new Configuration();

		this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(), "WEB-INF/templates/admin");
		Map root = new HashMap();
		root.put("list", list);
		root.put("page", this.page);
		FreemarkerUtils.freemarker(this.request, this.response, "kamiList.ftl", this.cfg, root);
	}

	public void add() {
		String pidStr = this.request.getParameter("pid");
		try {
			if (StringUtils.isNotEmpty(pidStr)) {
				Product findProduct = (Product) this.productService.findById(Product.class, Integer.parseInt(pidStr));
				if (findProduct != null) {
					this.cfg = new Configuration();

					this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(),
							"WEB-INF/templates/admin");
					Map root = new HashMap();
					root.put("product", findProduct);
					FreemarkerUtils.freemarker(this.request, this.response, "kamiAdd.ftl", this.cfg, root);
				} else {
					PrintWriter out = null;
					try {
						out = this.response.getWriter();
					} catch (IOException e) {
						e.printStackTrace();
					}
					String callbackData = BjuiJson.json("300", "?????????????????????", "", "", "", "", "", "");
					out.print(callbackData);
					out.flush();
					out.close();
				}
			} else {
				PrintWriter out = null;
				try {
					out = this.response.getWriter();
				} catch (IOException e) {
					e.printStackTrace();
				}
				String callbackData = BjuiJson.json("300", "?????????????????????", "", "", "", "", "", "");
				out.print(callbackData);
				out.flush();
				out.close();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void save() {
		String infos = this.request.getParameter("infos");
		String pidStr = this.request.getParameter("pid");
		String callbackData = "";
		try {
			if (StringUtils.isNotEmpty(pidStr)) {
				Product findProduct = (Product) this.productService.findById(Product.class, Integer.parseInt(pidStr));
				if (findProduct != null) {
					if (StringUtils.isNotEmpty(infos)) {
						String[] infosArr = infos.split("\r\n");

						String no = "";

						String password = "";

						int successNum = 0;

						int errorNum = 0;
						for (String info : infosArr) {
							info = info.trim();
							if (StringUtils.isNotEmpty(info)) {
								String[] infoArr = info.split(":");
								no = infoArr[0];

								if (infoArr.length > 1) {
									password = infoArr[1];
								}
								Kami newKami = new Kami();
								newKami.setCreateDate(new Date());
								newKami.setDeleted(false);
								newKami.setStatus(Integer.valueOf(0));
								newKami.setNo(no);
								newKami.setPassword(password);
								newKami.setProduct(findProduct);
								boolean res = this.kamiService.saveOrUpdate(newKami);
								if (!res)
									continue;
								successNum++;
							}
						}

						errorNum = infosArr.length - successNum;
						callbackData = BjuiJson.json("200", "????????????????????????:" + successNum + ",????????????????????????:" + errorNum, "", "",
								"", "true", "", "");
					} else {
						callbackData = BjuiJson.json("300", "????????????????????????", "", "", "", "", "", "");
					}
				} else
					callbackData = BjuiJson.json("300", "?????????????????????", "", "", "", "", "", "");
			} else {
				callbackData = BjuiJson.json("300", "?????????????????????", "", "", "", "", "", "");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
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

	public void uploadKamiPage() {
		String pidStr = this.request.getParameter("pid");
		try {
			if (StringUtils.isNotEmpty(pidStr)) {
				Product findProduct = (Product) this.productService.findById(Product.class, Integer.parseInt(pidStr));
				if (findProduct != null) {
					this.cfg = new Configuration();

					this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(),
							"WEB-INF/templates/admin");
					Map root = new HashMap();
					root.put("product", findProduct);
					FreemarkerUtils.freemarker(this.request, this.response, "uploadKami.ftl", this.cfg, root);
				} else {
					PrintWriter out = null;
					try {
						out = this.response.getWriter();
					} catch (IOException e) {
						e.printStackTrace();
					}
					String callbackData = BjuiJson.json("300", "?????????????????????", "", "", "", "", "", "");
					out.print(callbackData);
					out.flush();
					out.close();
				}
			} else {
				PrintWriter out = null;
				try {
					out = this.response.getWriter();
				} catch (IOException e) {
					e.printStackTrace();
				}
				String callbackData = BjuiJson.json("300", "?????????????????????", "", "", "", "", "", "");
				out.print(callbackData);
				out.flush();
				out.close();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void uploadKami() {
		String pidStr = this.request.getParameter("pid");
		String filePath = this.request.getParameter("filePath");

		Product findProduct = (Product) this.productService.findById(Product.class, Integer.parseInt(pidStr));
		ActionContext ac = ActionContext.getContext();
		ServletContext sc = (ServletContext) ac.get("com.opensymphony.xwork2.dispatcher.ServletContext");
		String savePath = sc.getRealPath("/");
		String callbackData = "";
		File file = new File(savePath + filePath);
		try {
			String encoding = "GBK";
			if ((file.isFile()) && (file.exists())) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;

				String no = "";

				String password = "";

				int successNum = 0;

				int errorNum = 0;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					String[] infoArr = lineTxt.split(",");
					no = infoArr[0];

					if (infoArr.length > 1) {
						password = infoArr[1];
					}
					Kami newKami = new Kami();
					newKami.setCreateDate(new Date());
					newKami.setDeleted(false);
					newKami.setStatus(Integer.valueOf(0));
					newKami.setNo(no);
					newKami.setPassword(password);
					newKami.setProduct(findProduct);
					boolean res = this.kamiService.saveOrUpdate(newKami);
					if (res) {
						successNum++;
					} else
						errorNum++;
				}

				read.close();
				callbackData = BjuiJson.json("200", "????????????????????????:" + successNum + ",????????????????????????:" + errorNum, "", "", "",
						"true", "", "");
			} else {
				callbackData = BjuiJson.json("300", "????????????????????????", "", "", "", "", "", "");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
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
		String callbackData = "";
		String idStr = this.request.getParameter("id");
		try {
			PrintWriter out = this.response.getWriter();

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
				Kami findKami = (Kami) this.kamiService.findById(Kami.class, id);
				if (findKami == null) {
					callbackData = BjuiJson.json("300", "???????????????", "", "", "", "", "", "");
					out.print(callbackData);
					out.flush();
					out.close();
				} else {
					this.cfg = new Configuration();

					this.cfg.setServletContextForTemplateLoading(this.request.getServletContext(),
							"WEB-INF/templates/admin");
					Map root = new HashMap();
					root.put("Kami", findKami);
					FreemarkerUtils.freemarker(this.request, this.response, "KamiEdit.ftl", this.cfg, root);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		String callbackData = "";
		try {
			PrintWriter out = this.response.getWriter();
			if (this.Kami == null) {
				callbackData = BjuiJson.json("300", "????????????", "", "", "", "", "", "");
			} else {
				Kami findKami = (Kami) this.kamiService.findById(Kami.class, this.Kami.getId().intValue());
				boolean result = this.kamiService.saveOrUpdate(findKami);

				if (result) {
					callbackData = BjuiJson.json("200", "????????????", "", "", "", "true", "", "");
				} else {
					callbackData = BjuiJson.json("300", "????????????", "", "", "", "", "", "");
				}
			}
			out.print(callbackData);
			out.flush();
			out.close();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void delete() {
		String callbackData = "";
		String idStr = this.request.getParameter("id");
		try {
			PrintWriter out = this.response.getWriter();

			if ((idStr == null) || ("".equals(idStr))) {
				callbackData = BjuiJson.json("300", "????????????", "", "", "", "", "", "");
			} else {
				int id = 0;
				try {
					id = Integer.parseInt(idStr);
				} catch (Exception e) {
					callbackData = BjuiJson.json("300", "????????????", "", "", "", "", "", "");
				}
				Kami findKami = (Kami) this.kamiService.findById(Kami.class, id);
				if (findKami == null) {
					callbackData = BjuiJson.json("300", "???????????????", "", "", "", "", "", "");
				} else
					try {
						boolean result = this.kamiService.delete(findKami);
						if (result)
							callbackData = BjuiJson.json("200", "????????????", "", "", "", "", "", "");
						else
							callbackData = BjuiJson.json("300", "????????????", "", "", "", "", "", "");
					} catch (JSONException e) {
						e.printStackTrace();
					}
			}

			out.print(callbackData);
			out.flush();
			out.close();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Kami getKami() {
		return this.Kami;
	}

	public void setKami(Kami Kami) {
		this.Kami = Kami;
	}

	public List<File> getFiledata() {
		return this.Filedata;
	}

	public void setFiledata(List<File> filedata) {
		this.Filedata = filedata;
	}

	public List<String> getFiledataFileName() {
		return this.FiledataFileName;
	}

	public void setFiledataFileName(List<String> filedataFileName) {
		this.FiledataFileName = filedataFileName;
	}

	public List<String> getFiledataContentType() {
		return this.FiledataContentType;
	}

	public void setFiledataContentType(List<String> filedataContentType) {
		this.FiledataContentType = filedataContentType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
