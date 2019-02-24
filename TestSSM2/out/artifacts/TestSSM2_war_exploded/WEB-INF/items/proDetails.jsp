<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品信息</title>

</head>
<body> 
<form id="itemForm" action="${pageContext.request.contextPath }/printInfo.action" method="post" enctype="multipart/form-data">
<input type="hidden" name="product.pid" value="${product.pid }"/>
商品信息：
<table width="100%" border=1>
<tr>
	<td>商品名称</td>
	<td><input type="text" name="product.pname" value="${productExtend.pname }"/></td>
</tr>
<tr>
	<td>商品价格</td>
	<td><input type="text" name="product.shop_price" value="${productExtend.shop_price }"/></td>
</tr>
<%--<tr>
	<td>商品图片</td>
	<td>
		<c:if test="${item.pic !=null}">
			<img src="/pic/${item.pic}" width=100 height=100/>
			<br/>
		</c:if>
		<input type="file"  name="pictureFile"/> 
	</td>
</tr>--%>
<tr>
	<td>商品简介</td>
	<td>
	<textarea rows="3" cols="30" name="productExtend.desc">${productExtend.desc }</textarea>
	</td>
</tr>
<tr>
<td colspan="2" align="center"><input type="submit" value="提交"/>
</td>
</tr>
</table>

</form>
</body>

</html>