<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>送信結果</title>
</head>
<body>
	<h1><c:out value="${result.title}" /></h1>
	<a href="${result.backTo}">戻る</a>
</body>
</html>