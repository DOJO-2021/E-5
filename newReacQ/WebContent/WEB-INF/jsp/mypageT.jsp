<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ReacQ</title>
</head>
<body>
	<!-- リアクション集計の表示 -->
	<h2>リアクション履歴</h2>
	<!-- デートピッカー -->
	<form method="POST" action="/newReacQ/MyReactionServlet">
		<table class="table">
			<tr>
			<td>日付</td><td><input type="date" name="REPLY_DATE" value="${e.reply_date}"></td>
			</tr>
		</table>
	</form>
	<!-- リアクショングラフ -->
	<table class="table">
		<tr>
		<td><img src="/simpleBC/images/" alt="河童_説明可グラフ" width="30" height="80"></td><td><img src="/simpleBC/images/" alt="河童_分かるグラフ" width="30" height="50"></td><td><img src="/simpleBC/images/" alt="河童_分かるかもグラフ" width="30" height="100"></td><td><img src="/simpleBC/images/" alt="河童_分からないグラフ" width="30" height="80"></td>
		</tr>
		<tr>
		<td><img src="/newReacQ/images/" alt="河童_説明可"></td><td><img src="/newReacQ/images/" alt="河童_分かる"></td><td><img src="/newReacQ/images/" alt="河童_分かるかも"></td><td><img src="/newReacQ/images/" alt="河童_分からない"></td>
		</tr>
	</table>

	<!-- アカウント管理 -->
	<h2>アカウント管理</h2>
	<form method="POST" action="/newReacQ/MyUserDataServlet">
		<!-- ポジション以外のアカウント管理テーブルの表示 -->
		<table class="table">
			<tr>
			<input type="hidden" name="ID" value="${e.id}">
			<th>メールアドレス</th><td><input type="text" name="EMAIL" value="${e.email}"></td>
			</tr>
			<tr>
			<th>パスワード</th><td><input type="password" name="PASSWORD" value="${e.password}"></td>
			</tr>
			<tr>
			<th>氏名</th><td><input type="text" name="NAME" value="${e.name}"></td>
			</tr>
		</table>
		<input type="submit" name="SUBMIT" value="更新"><br>
	</form>
</body>
</html>