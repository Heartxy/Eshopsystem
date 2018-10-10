package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ProductTypeBean;
import dao.ProductTypeDao;
import util.StringUtil;
import com.alibaba.fastjson.JSON;

/**
 * Servlet implementation class ProductTypeServlet
 */
@WebServlet("/servlet/ProductTypeServlet")
public class ProductTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("Utf-8");
		String method =req.getParameter("method");
		if(method.equals("list")){
			list(req,resp);
		} else if ("delete".equals(method)) {
			delete(req, resp);
		} else if ("toAdd".equals(method)) {
			toAdd(req, resp);//获取一级
		} else if("getType".equals(method)){
			getType(req,resp);//获取接下来的分级
		}//一级和其他级分开的原因是---一级之上没有别级，所以不能通过parent_id=id来查找，只能通过parent_id=0来看
		
		else if("add".equals(method)){
				add(req,resp);//获取接下来的分级
			}
	}
	
	//5添加分类
private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String name = req.getParameter("name");//分类名
		int sort = StringUtil.StringToInt(req.getParameter("sort"));//序号
		String intro = req.getParameter("desc");//描述
		String[] parIds = req.getParameterValues("parentId");//所有的父id
		int parentId = 0;//只拿一个最近的parent_id
		
		for(String parId:parIds){
			//比较大小，只要最大的那一个作为父类id。id是数据库自增，最大的就是最后的。
			parentId = Math.max(parentId,StringUtil.StringToInt(parId));
		}
		
		ProductTypeDao productTypeDao = new ProductTypeDao();
		boolean f;
		ProductTypeBean productTypeBean=null;
		
			productTypeBean = new ProductTypeBean(name,parentId,sort,intro);
			f = productTypeDao.add(productTypeBean);
		
		if (f) {
			resp.sendRedirect("ProductTypeServlet?method=toAdd&status=true");
		} else {
			resp.sendRedirect("ProductTypeServlet?method=toAdd&status=false");
		}
	}
	
	//4获取下一级分类的json字段
	private void getType(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int parentId = StringUtil.StringToInt(req.getParameter("id"));
		ProductTypeDao productTypeDao = new ProductTypeDao();
		
		List<ProductTypeBean> typeList = productTypeDao.getTypeList(parentId);//getTypeList与getTypeBeans效果一样，唯一区别就是获取字段不一样
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSONString(typeList));
		out.flush();
		out.close();
		}
	
	//3添加分类----获取一级分类 parent-id为0的~
	private  void toAdd(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		ProductTypeDao productTypeDao = new ProductTypeDao();
		
		List<ProductTypeBean> typeList = productTypeDao.getTypeBeans(0);
		req.setAttribute("productTypeList", typeList);
		req.getRequestDispatcher("/admin/product/type/add.jsp").forward(req, resp);
	}
	
	//2删除本商品及下一级
	private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	int id = StringUtil.StringToInt(req.getParameter("id"));
	ProductTypeDao productTypeDao = new ProductTypeDao();
	productTypeDao.delete(id);
	resp.sendRedirect("ProductTypeServlet?method=list");
	}
	

	//1上一级下一级 获取同级对象
	public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			int parentId = StringUtil.StringToInt(req.getParameter("id"));
			//需要获取下一级的对象链表所以自己的id做为下一级的父类id
			ProductTypeDao productTypeDao = new ProductTypeDao();
			ProductTypeBean productTypeBean = productTypeDao.getType(parentId);
			req.setAttribute("productTypeBean", productTypeBean);
			req.getRequestDispatcher("/admin/product/type/list.jsp").forward(req, resp);
	}
	
}
