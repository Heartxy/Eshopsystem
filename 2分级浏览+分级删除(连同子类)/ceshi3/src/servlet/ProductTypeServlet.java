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
		} 
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
