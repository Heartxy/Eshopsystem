<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type"content="text/html; charset=UTF-8">
<title>商品类型查询</title>
<script type="text/javascript"src="${pageContext.request.contextPath}/
static/js/jquery-1.12.1.js"></script>
<script  type="text/javascript"src="${pageContext.request.contextPath}/
static/bootstrap-3.3.5-dist/js/bootstrap.js"></script>
<link rel="stylesheet"href="${pageContext.request.contextPath}/static/
bootstrap-3.3.5-dist/css/bootstrap.css"/>
</head>
<script type="text/javascript">
function showIndex(){
<c:set var="item0" scope="session"
value="${productTypeBean}"></c:set>
var content = "<li><a href='${pageContext.request.contextPath}/ProductTypeServlet?method=list&id=0'>Home</a></li>";
var contentM = "";
if ("${item0!=null}") {
contentM = "<li><a href='${pageContext.request.contextPath}/ProductTypeServlet?method=list&id="+"${item0.id}"+"'>"+"${item0.name}"+"</a></li>"+contentM;
<c:set var="item0" scope="session" value="${item0.parentBean}"></c:set>
}
content += contentM;
content += "</select></div>";
$("#index0").append(content);
}
</script>
<body>
<div class="container">
<h1>商品类型查询</h1>
<table class="table table-striped">
<ol class="breadcrumb">
<li><a href="${pageContext.request.contextPath}/servlet/ProductTypeServlet?method=list&id=0">Home</a></li>
<li><a href="${pageContext.request.contextPath}/servlet/ProductTypeServlet?method=list&id=${productTypeBean.parentBean.id}">上一级</a></li>
<li class="active">${productTypeBean.name}</li>
</ol>
<!-- 显示的是孩子对象 -->
<c:forEach items="${productTypeBean.childBeans }" var="item" varStatus="status">
<c:if test="${status.index==0 }">
<tr>
<td>id</td>
<td>分类名</td>
<td>序号</td>
<td>描述</td>
<td>创建时间</td>
</tr>
</c:if>
<tr>
<td>${item.id }</td>
<td><a href="${pageContext.request.contextPath}/servlet/ProductTypeServlet?method=list&id=${item.id}">${item.name}</a></td>
<!-- 点击触发下一级  -->
<td>${item.sort }</td>
<td>${item.intro }</td>
<td>${item.createDate }</td>
</tr>
</c:forEach>
</table>
</div>
</body>
</html>