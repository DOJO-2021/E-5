<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/newReacQ/css/menu.css">
<link rel="stylesheet" href="/newReacQ/css/common.css">
<title>ReacQ</title>
</head>
<body>
<header>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
</header>
<section>
    <div id="main">
	<!-- リアクション -->

	<div class="box01">
		<p>リアクション</p>
		<form method="POST" action="/newReacQ/MenuReactionServlet">
			<!-- リアクショングラフ -->
			<table class="table">
				<tr>
				<td class= "content">項目</td>
				<td><input type ="text" name="react_title" value="${react_t}"></td>
				<td><button type ="submit" name="mrea" value="更新" class = "update_button">更新</button></td>
				<td><button type ="submit" name="mrea" value="リセット" class = "update_button">リセット</button></td>
				</tr>
				<jsp:include page="/WEB-INF/jsp/graph.jsp" />
			</table>
		</form>
	</div>


			<!-- 掲示板 -->

	<div class="box02">
		<p>掲示板</p><a href="/newReacQ/BoardServlet">全部見る</a><br>
		<p>最新の投稿</p>

		<form method="GET" action="/newReacQ/MenuBoardServlet">
			<!-- 最新の投稿テーブル -->
		    <div id ="table">
		    <table>
 			<c:forEach var="b" items="${newlist}" >
		        <tr>
		        	<th>回答日時</th>
		        	<td>${b.reply_date}</td>
		        </tr>
		        <tr>
		        	<th>質問内容</th>
		        	<td>${b.question}</td>
		        </tr>
			</c:forEach>
		    </table>
			</div>
		</form>
	</div>
  </div><!--  id="main" end -->
</section>

<footer>
	<div id="foot">
		<jsp:include page="/WEB-INF/jsp/footer.jsp" />
	</div>
</footer>
</body>
</html>