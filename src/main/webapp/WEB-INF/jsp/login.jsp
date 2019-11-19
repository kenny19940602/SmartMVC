<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
</head>
<body>
	<h1>登录</h1>
	<form action="login.do" method="post">
		<div>
			<label>用户名：</label>
			<input type="text" name="uname" 
			value="${uname}"> 
			<span>${unameError}</span>
		</div>
		<div>
			<label>密码：</label>
			<input type="password" name="pwd"> 
			<span>${pwdError}</span>
		</div>
		<div>
			<input type="submit" value="登录">  
		</div>
	</form>
</body>
</html>





