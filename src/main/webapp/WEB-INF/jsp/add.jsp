<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加用户</title>
</head>
<body>
	<h1>添加用户</h1>
	<form action="save.do" method="post">
		<div>
			<label>用户名：</label>
			<input type="text" name="uname" 
			value="${uname}"> 
			<span>${unameError}</span>
		</div>
		<div>
			<label>密码：</label>
			<input type="password" name="pwd"> 
		</div>
		<div>
			<label>e-mail：</label>
			<input type="text" name="email"
				value="${email}">
		</div>
		<div>
			<input type="submit" value="保存">  
		</div>
	</form>
</body>
</html>





