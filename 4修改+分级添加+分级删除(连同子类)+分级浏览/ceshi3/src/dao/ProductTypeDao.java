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
	//4修改
	public boolean update(ProductTypeBean productTypeBean) {
		String sql = "update product_type set name='" +
		productTypeBean.getName() + "', sort='" + productTypeBean.getSort()
		+ "', intro='" + productTypeBean.getIntro() + "' where id='" + productTypeBean.getId() + "'";
		Connection conn = DBUtil.getConn();
		Statement state = null;
		boolean f = false;
		int a = 0;
		try {
		state = conn.createStatement();
		a = state.executeUpdate(sql);
		} catch (Exception e) {
		e.printStackTrace();
		} finally {
		DBUtil.close(state, conn);
		}
		if (a > 0) {
		f = true;
		}
		return f;
		}
	
	//3添加分类----获取分类填报信息进行新增---一级
	public boolean add(ProductTypeBean ptb){
		String sql="insert into " +
				"product_type(name,parent_id,sort,intro,create_date) values('"+
				ptb.getName()
		+"','"+ptb.getParentId()+"','"+
		ptb.getSort()+"','"
		+ptb.getIntro()+"',now())";
		Connection conn=DBUtil.getConn();
		Statement state =null;
		boolean f=false;
		int a=0;
		try{
			state = conn.createStatement();
			a = state.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			DBUtil.close(state, conn);
		}
		if(a>0){
			f=true;
		}
		return f;
	}
	
	//3添加分类---获取该id作为父类id的所有子类链表
	public List<ProductTypeBean> getTypeBeans(int parentId){
		String sql="select * from product_type where parent_id='"+parentId+"'";
		List<ProductTypeBean> typeBeans = new ArrayList<ProductTypeBean>();
		Connection conn = DBUtil.getConn();
		Statement state = null;
		ResultSet rs = null;
		
		try{
			state = conn.createStatement();
			rs=state.executeQuery(sql);
			ProductTypeBean productTypeBean = null;
			while(rs.next()){
				int id= rs.getInt("id");
				String name= rs.getString("name");
				productTypeBean =new ProductTypeBean(id,name); 
				typeBeans.add(productTypeBean);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs,state,conn);
		}
		return typeBeans;
	}

	
	
	
	//2删除
	public boolean delete(int id) {
		boolean f = true;
		List<ProductTypeBean> typeList = getTypeList(id);//通过这个id找到这个id的孩子
		//循环删除,一层一层的往下调用本方法删除
		for(ProductTypeBean typeBean : typeList){
			boolean f1 = delete(typeBean.getId()); 
				if(!f1){
					f = false;
				}
		}
			String sql = "delete from product_type where id='" + id + "'";
			Connection conn = DBUtil.getConn();
			Statement state = null;
			int a = 0;
		try {
			state = conn.createStatement();
			a = state.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(state, conn);
		}
		//判断删除的个数---是否被删除
			if (a == 0) {
				f = false;
			}
		return f;
		}
		
	
	//1上下级查看  
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
	
//通过这个id获取此id对象和此id的父类对象(1  4)
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
	ProductTypeBean parentBean =productTypeDao.getTypeById(parentId);//再次调用本方法获取父类对象
	productTypeBean = new ProductTypeBean(id, name, sort, intro, createDate,parentBean);
	}
	} catch (SQLException e) {
	e.printStackTrace();
	} finally {
	DBUtil.close(rs, state, conn);
	}
	return productTypeBean;
	}
	
//通过这个id找到此id的孩子  （1   3）
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

