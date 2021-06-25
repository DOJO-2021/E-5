<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ReacQ|mypage</title>
<link rel="stylesheet" href="/newReacQ/css/mypage.css">
<link rel="stylesheet" href="/newReacQ/css/common.css">
</head>
<body>
<header>
<jsp:include page="header.jsp" flush="true" />
</header>
<section>
<div id="main">
	<!-- リアクション集計の表示 -->
	<div class="box04">
	<h2>リアクション履歴</h2>
	<!-- デートピッカー-->
	<form method="POST" action="/newReacQ/MyReactionServlet">
		<table class="table">
			<tr>
			<td>日付</td><td><input type="date" name="REPLY_DATE_R" id="date_r" value="${date_r}">
			<input type="submit" name="SUBMIT" value="履歴更新" onclick="send_r()" class="button"><br></td>
			</tr>
		</table>
	</form>
	<!-- リアクション数-->
	<table class="size">
		<tr>
		<td>${myrea3}</td><td>${myrea2}</td><td>${myrea1}</td><td>${myrea0}</td>
		</tr>
		<tr>
				<td><img src="/newReacQ/images/河童説明可_ホバー前.png" onmouseover="this.src='/newReacQ/images/河童説明可_ホバー後.png'" onmouseout="this.src='/newReacQ/images/河童説明可_ホバー前.png'" width="100px" alt="河童_説明可"></td>
				<td><img src="/newReacQ/images/河童分かる_ホバー前.png" onmouseover="this.src='/newReacQ/images/河童分かる_ホバー後.png'" onmouseout="this.src='/newReacQ/images/河童分かる_ホバー前.png'" width="100px" alt="河童_分かる"></td>
				<td><img src="/newReacQ/images/河童分かるかも_ホバー前.png" onmouseover="this.src='/newReacQ/images/河童分かるかも_ホバー後.png'" onmouseout="this.src='/newReacQ/images/河童分かるかも_ホバー前.png'" width="100px" alt="河童_分かるかも"></td>
				<td><img src="/newReacQ/images/河童分からない_ホバー前.png" onmouseover="this.src='/newReacQ/images/河童分からない_ホバー後.png'" onmouseout="this.src='/newReacQ/images/河童分からない_ホバー前.png'" width="100px" alt="河童_分からない"></td>
		</tr>
		<tr class="ria">
		<td><p>説明可</p></td>
		<td><p>分かる</p></td>
		<td><p>分かるかも</p></td>
		<td><p>分からない</p></td>
		</tr>
	</table>


	<!-- 掲示板履歴の表示-->
    <div class="contentView">
	<h2>掲示板履歴</h2>
	<!-- デートピッカー-->
	<form method="POST" action="/newReacQ/MyBoardServlet">
		<table class="table">
			<tr>
			<td>日付</td><td><input type="date" name="REPLY_DATE_B" id="date_b" value="${date_b}">
			<input type="submit" name="SUBMIT" value="履歴更新" onclick="send_b()" class="button"><br></td>

			</tr>
		</table>
	</form>
	<!-- 質問履歴の表示-->

	<c:forEach var="b" items="${myboardList}" >
	<form method="POST" action="/newReacQ/DeleteServlet">
		<table class="table">
			<tr>
			<input type="hidden" name="CODE" value="${b.question_code}">
			<th>質問内容</th>
			<td>${b.question}</td>
			</tr>
			<hr>
			<tr>
			<td class="reply">${b.reply_date}</td>
			<td><button type ="submit" name="SUBMIT" value="削除"><img src="/newReacQ/images/gomi.png"width="20px" height="20px"></button></td>
			</tr>
		</table>
	</form>
	</c:forEach>
	</div>
	</div>

	<!-- アカウント管理-->
	<div class="box05">
		<div class="conA">
		<h2>アカウント管理</h2>
		<c:forEach var="u" items="${userList}">
		<form method="POST" action="/newReacQ/MyUserDataServlet" id="form">
			<!-- ポジション以外のアカウント管理テーブルの表示 -->
			<table class="table_a">
				<tr>
				<input type="hidden" name="ID" value="${u.id}">
				<th class="name">メールアドレス</th>
				</tr>
				<tr>
				<td><input type="text" name="EMAIL" value="${u.email}"></td>
				</tr>
				<tr>
				<th class="name">パスワード</th>
				</tr>
				<tr>
				<td><input type="password" name="PASSWORD" id="password" value="${u.password}"></td>
				</tr>
				<tr>
				<th class="name">パスワード再入力*更新時必須</th>
				</tr>
				<tr>
				<td><input type="password" name="passwordConfirm" id="passwordConfirm" required><button id="btn_passview">表示</button></td>
				</tr>
				<tr>
				<th class="name">氏名</th>
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
	</div>
  </div><!--  main div -->
  </section>

  <footer>
  <jsp:include page="footer.jsp" flush="true" />
  </footer>


<script type='text/javascript' src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src='js/today.js' type='text/javascript'></script>

</body>
</html>