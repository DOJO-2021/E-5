<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ReacQ</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="/newReacQ/css/menu.css">
<link rel="stylesheet" href="/newReacQ/css/common.css">
</head>
<body>
<!-- ヘッダー -->
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<section>
    <div id="main">

		<!-- リアクション -->
		<div class="box01">
			<h2>リアクション</h2>
			<form method="POST" action="/newReacQ/MenuReactionServlet">
				<!-- リアクショングラフ -->
				<table class="table">
					<tr>
					<td class="content">項目</td>
					<td>${react_t}<input type ="hidden" name="react_title" value="${react_t}"></td>
					<td><button type ="submit" name="mrea" value="更新" class = "update_button">更新</button></td>
					</tr>
					<tr>
						<jsp:include page="/WEB-INF/jsp/graph.jsp" />
					</tr>
					<tr>
						<td><button type="submit" name="mrea" value="説明可"><img src="/newReacQ/images/河童説明可_ホバー前.png" onmouseover="this.src='/newReacQ/images/河童説明可_ホバー後.png'" onmouseout="this.src='/newReacQ/images/河童説明可_ホバー前.png'"  alt="河童_説明可" ></button></td>
						<td><button type ="submit" name="mrea" value="分かる"><img src="/newReacQ/images/河童分かる_ホバー前.png" onmouseover="this.src='/newReacQ/images/河童分かる_ホバー後.png'" onmouseout="this.src='/newReacQ/images/河童分かる_ホバー前.png'"  alt="河童_分かる"></button></td>
						<td><button type ="submit" name="mrea" value="分かるかも"><img src="/newReacQ/images/河童分かるかも_ホバー前.png" onmouseover="this.src='/newReacQ/images/河童分かるかも_ホバー後.png'" onmouseout="this.src='/newReacQ/images/河童分かるかも_ホバー前.png'"  alt="河童_分かるかも" ></button></td>
						<td><button type ="submit" name="mrea" value="分からない"><img src="/newReacQ/images/河童分からない_ホバー前.png" onmouseover="this.src='/newReacQ/images/河童分からない_ホバー後.png'" onmouseout="this.src='/newReacQ/images/河童分からない_ホバー前.png'"  alt="河童_分からない" ></button></td>
					</tr>
				</table>
			</form>
		</div>

			<!-- 掲示板 -->

		<div class="box02">
<<<<<<< HEAD
		<h2>最新の投稿<a href="/newReacQ/BoardServlet"><span class="mgl-130">全部の投稿を見る</span></a></h2>
=======
		<h2>最新の投稿</h2>
		<p><a href="/newReacQ/BoardServlet">全部の投稿を見る</a><br>
>>>>>>> 01bd15fbe959540979aa17e1ca8d6e010d61edc5
		<form method="GET" action="/newReacQ/MenuBoardServlet">
			<!-- 最新の投稿テーブル -->
			<!-- 固定コンテンツ -->
		    <table >
 			<c:forEach var="b" items="${newlist}" >
<<<<<<< HEAD
		        <tr>
		        	<th>質問内容</th>
		        	<td>${b.question}</td>
		        </tr>
		        <tr>
		        	<td class = "reply_date">${b.reply_date}</td>
		        </tr>
=======
 				<tr>
 				<th>質問内容</th>
		        <td>${b.question}</td>
		        </tr>
		        <tr>
				<td class="reply">${b.reply_date}</td>
				<td><button type ="submit" name="SUBMIT" value="削除"><img src="/newReacQ/images/gomi.png"width="20px" height="20px"></button></td>
				</tr>
>>>>>>> 01bd15fbe959540979aa17e1ca8d6e010d61edc5
			</c:forEach>
		    </table>
		</form>
		</div>
		<div class ="box03">
		<h2>自分の投稿</h2>
		<form method="GET" action="/newReacQ/MenuBoardServlet">
		    <!-- 自分の投稿テーブル -->
		    <table>
<<<<<<< HEAD
		    	<c:forEach var="b" items="${mynewlist}" >
		        <tr>
		        	<th>質問内容</th>
		        	<td>${b.question}</td>
		        </tr>
		        <tr>
		        	<td class = "reply_date">${b.reply_date}</td>
		        </tr>
=======
		    <c:forEach var="b" items="${mynewlist}" >
		    	<tr>
 				<th>質問内容</th>
		        <td>${b.question}</td>
		        </tr>
		        <tr>
				<td class="reply">${b.reply_date}</td>
				<td><button type ="submit" name="SUBMIT" value="削除"><img src="/newReacQ/images/gomi.png"width="20px" height="20px"></button></td>
				</tr>
>>>>>>> 01bd15fbe959540979aa17e1ca8d6e010d61edc5
		        </c:forEach>
		    </table>
		</form>
		</div>

	</div> <!-- id="main" end -->
</section>

	<jsp:include page="/WEB-INF/jsp/footer.jsp" />

</body>
</html>