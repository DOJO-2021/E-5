<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ReacQ | 送信結果</title>
<link rel="shortcut icon" href="images/favicon.ico">
<link rel="stylesheet" href="/newReacQ/css/common.css">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
	<h1><c:out value="${result.title}" /></h1>
	<a href="${result.backTo}">戻る</a>

<jsp:include page="footer.jsp" flush="true" />
</body>
</html>