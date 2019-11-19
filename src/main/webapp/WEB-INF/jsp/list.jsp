<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户列表</title>
</head>
<body>
	<h1>用户列表</h1>
	<a href="add.do">添加</a> 
	<a href="logout.do">登出</a>
	<table>
		<tr>
			<td>ID</td>
			<td>用户名</td>
			<td>密码</td>
			<td>邮箱</td>
			<td>操作</td>
		</tr>			
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.id}</td> 
				<td>${user.uname}</td> 
				<td>${user.pwd}</td> 
				<td>${user.email}</td> 
				<td><a href="delete.do?id=${user.id}">删除</a></td> 
			</tr>
		</c:forEach>
	</table>
</body>
</html>





