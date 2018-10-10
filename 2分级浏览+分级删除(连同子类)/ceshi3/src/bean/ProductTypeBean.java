package bean;

import java.util.List;

public class ProductTypeBean {
	private int id;//自己的id号
	private String name;
	private int parentId;//父类id号
	private int sort;
	private String intro;
	private String createDate;//创建日期
	private List<ProductTypeBean> childBeans;//子类对象链表
	private ProductTypeBean parentBean;//父类对象
	/*
	 bean的设置 和 数据表的信息不同，还多在List<ProductTypeBean>与ProductTypeBean
	 */
	
	public ProductTypeBean(int id, String name, int parentId, int sort,
			String intro, String createDate, List<ProductTypeBean> childBeans,
			ProductTypeBean parentBean) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.sort = sort;
		this.intro = intro;
		this.createDate = createDate;
		this.childBeans = childBeans;
		this.parentBean = parentBean;
	}

	public ProductTypeBean() {
	}

	public ProductTypeBean(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public ProductTypeBean(String name, int parentId, int sort, String intro) {
		super();
		this.name = name;
		this.parentId = parentId;
		this.sort = sort;
		this.intro = intro;
	}

	public ProductTypeBean(int id, String name, int parentId, int sort,
			String intro) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.sort = sort;
		this.intro = intro;
	}

	public ProductTypeBean(int id, String name, int sort, String intro,
			String createDate) {
		super();
		this.id = id;
		this.name = name;
		this.sort = sort;
		this.intro = intro;
		this.createDate = createDate;
	}

	public ProductTypeBean(int id, String name, int sort, String intro,
			String createDate, ProductTypeBean parentBean) {
		super();
		this.id = id;
		this.name = name;
		this.sort = sort;
		this.intro = intro;
		this.createDate = createDate;
		this.parentBean = parentBean;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public List<ProductTypeBean> getChildBeans() {
		return childBeans;
	}

	public void setChildBeans(List<ProductTypeBean> childBeans) {
		this.childBeans = childBeans;
	}

	public ProductTypeBean getParentBean() {
		return parentBean;
	}

	public void setParentBean(ProductTypeBean parentBean) {
		this.parentBean = parentBean;
	}
}
