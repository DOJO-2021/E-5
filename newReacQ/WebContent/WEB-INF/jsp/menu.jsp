<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ReacQ</title>
<link rel="stylesheet" href="/newReacQ/css/menu.css">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
	<!-- リアクション -->
	<div class="contentsR">
		<p>リアクション</p>
		<form method="POST" action="/newReacQ/MenuReactionServlet">
			<!-- リアクショングラフ -->
			<table class="table">
				<tr>
					<td>${react_t}<input type ="hidden" name="react_title" value="${react_t}"></td><td><input type ="submit" name="mrea" value="更新"></td>
				</tr>
				<tr>
					<td>${rea3}</td>
					<td>${rea2}</td>
					<td>${rea1}</td>
					<td>${rea0}</td>
				</tr>
				<tr>
					<td><input type ="submit" name="mrea" value="説明可"><img src="/newReacQ/images/" alt="河童_説明可"></td>
					<td><input type ="submit" name="mrea" value="分かる"><img src="/newReacQ/images/" alt="河童_分かる"></td>
					<td><input type ="submit" name="mrea" value="分かるかも"><img src="/newReacQ/images/" alt="河童_分かるかも"></td>
					<td><input type ="submit" name="mrea" value="分からない"><img src="/newReacQ/images/" alt="河童_分からない"></td>
				</tr>
			</table>
		</form>
	</div>

			<!-- 掲示板 -->
	<div class="contentsB">
		<p>掲示板</p><a href="/newReacQ/BoardServlet">全部見る</a><br>
		<p>最新の投稿</p>

		<form method="GET" action="/newReacQ/MenuBoardServlet">
			<!-- 最新の投稿テーブル -->
		     <table class = scrollable>
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
		</form>

		<p>自分の投稿</p>
		<form method="GET" action="/newReacQ/MenuBoardServlet">
		    <!-- 自分の投稿テーブル -->
		    <table>
		    	<c:forEach var="b" items="${mynewlist}" >
		        <tr>
		            <th>email</th>
		            <td>${b.email}</td>
		        </tr>
		        <tr>
		        	<th>質問内容</th>
		        	<td>${b.question}</td>
		        </tr>
		        </c:forEach>
		    </table>
		</form>
		</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>