<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ReacQ|mypage</title>
<link rel="stylesheet" href="/newReacQ/css/mypage.css">
</head>
<body>
<jsp:include page="header.jsp" flush="true" />
	<!-- リアクション集計の表示 -->
	<div class="contentsR">
	<h2>リアクション履歴</h2>
	<!-- デートピッカー -->
	<form method="POST" action="/newReacQ/MyReactionServlet">
		<table class="table">
			<tr>
			<td>日付</td><td><input type="date" name="REPLY_DATE"  id="date_r" value="${reply_date}"></td>
			<input type="submit" name="SUBMIT" value="履歴の内容を更新する"><br>
			</tr>
		</table>
	</form>
	<!-- リアクショングラフ -->
	<table class="table">
		<tr>
		<td>${myrea3}</td><td>${myrea2}</td><td>${myrea1}</td><td>${myrea0}</td>
		</tr>
		<tr>
		<td><img src="/newReacQ/images/" alt="河童_説明可"></td><td><img src="/newReacQ/images/" alt="河童_分かる"></td><td><img src="/newReacQ/images/" alt="河童_分かるかも"></td><td><img src="/newReacQ/images/" alt="河童_分からない"></td>
		</tr>
	</table>
	</div>

	<!-- アカウント管理 -->
	<div class="contentsA">
	<h2>アカウント管理</h2>
	<c:forEach var="u" items="${userList}">
	<form method="POST" action="/newReacQ/MyUserDataServlet">
		<!-- ポジション以外のアカウント管理テーブルの表示 -->
		<table class="table">
			<tr>
			<input type="hidden" name="ID" value="${u.id}">
			<th>メールアドレス</th>
			</tr>
			<tr>
			<td><input type="text" name="EMAIL" value="${u.email}"></td>
			</tr>
			<tr>
			<th>パスワード</th>
			</tr>
			<tr>
			<td><input type="password" name="PASSWORD" value="${u.password}"></td>
			</tr>
			<tr>
			<th>氏名</th>
			</tr>
			<tr>
			<td><input type="text" name="NAME" value="${u.name}"></td>
			<input type="hidden" name="POSITION" value="${u.position}">
			</tr>
		</table>
		<input type="submit" name="SUBMIT" value="更新"><br>
	</form>
	</c:forEach>
	</div>
<jsp:include page="footer.jsp" flush="true" />
</body>
</html>