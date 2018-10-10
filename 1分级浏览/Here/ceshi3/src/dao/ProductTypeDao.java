package dao;
//商品分类

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import bean.ProductTypeBean;
import util.DBUtil;
public class ProductTypeDao {
	
//通过这个id找到此id的孩子，并将孩子链表放在父类对象中
	public ProductTypeBean getType(int id){
		List<ProductTypeBean> list = getTypeList(id);//获取孩子，dao中的方法可以调用同一个dao的别的方法
		ProductTypeBean ptb;
		//如果是0级分类将父类id与自己的id设置为0
		if(id==0){
			ptb=new ProductTypeBean();
			ptb.setId(0);
		}
		//否则通过这个获取本身对象
		else{
			ptb=getTypeById(id);
		}
		ptb.setChildBeans(list);//跳转制定方法ctrl+鼠标 ，A因为bean中有设置链表的成员所以可以直接将list赋值给该对象~~~~~
		return ptb;
	}
//通过这个id获取此id的父类对象
	public ProductTypeBean getTypeById(int typeId) {
	String sql = "select * from product_type where id='" + typeId + "'";
	Connection conn = DBUtil.getConn();
	Statement state = null;
	ResultSet rs = null;
	ProductTypeBean productTypeBean = null;
	try {
	state = conn.createStatement();
	rs = state.executeQuery(sql);
	while (rs.next()) {
	int id = rs.getInt("id");
	int sort = rs.getInt("sort");
	int parentId = rs.getInt("parent_id");
	String name = rs.getString("name");
	String intro = rs.getString("intro");
	String createDate = rs.getString("create_date");
	ProductTypeDao productTypeDao = new ProductTypeDao();
	ProductTypeBean parentBean =productTypeDao.getTypeById(parentId);
	productTypeBean = new ProductTypeBean(id, name, sort, intro, createDate,parentBean);
	}
	} catch (SQLException e) {
	e.printStackTrace();
	} finally {
	DBUtil.close(rs, state, conn);
	}
	return productTypeBean;
	}
	
//通过这个id找到此id的孩子
	public List<ProductTypeBean> getTypeList(int parentId) {
	String sql = "select * from product_type where parent_id='"+parentId+"'";
	List<ProductTypeBean> list = new ArrayList<ProductTypeBean>();
	Connection conn = DBUtil.getConn();
	Statement state = null;
	ResultSet rs = null;
	try {
	state = conn.createStatement();
	rs = state.executeQuery(sql);
	ProductTypeBean productTypeBean = null;
	while (rs.next()) {
	int id = rs.getInt("id");
	String name = rs.getString("name");
	int sort = rs.getInt("sort");
	String intro = rs.getString("intro");
	String createDate = rs.getString("create_date");
	productTypeBean = new ProductTypeBean(id,name,sort,intro,createDate);
	list.add(productTypeBean);
	}
	} catch (SQLException e) {
	e.printStackTrace();
	} finally {
	DBUtil.close(rs, state, conn);
	}
	return list;
	}
	
}

