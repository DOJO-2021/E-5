<!--<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ReacQ</title>
<link rel="stylesheet" href="/newReacQ/css/menu.css">
<link rel="stylesheet" href="/newReacQ/css/common.css">
</head>
<body>
<!-- ヘッダー -->
<header>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
</header>
<section>
	<div id = "main">

		<!-- リアクション -->
		<div class="box01">
			<p>リアクション</p>
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
						<td><button type="submit" name="mrea" value="説明可"><img src="/newReacQ/images/河童説明可_ホバー前.png" onmouseover="this.src='/newReacQ/images/河童説明可_ホバー後.png'" onmouseout="this.src='/newReacQ/images/河童説明可_ホバー前.png'" width="150px" alt="河童_説明可"></button></td>
						<td><button type ="submit" name="mrea" value="分かる"><img src="/newReacQ/images/河童分かる_ホバー前.png" onmouseover="this.src='/newReacQ/images/河童分かる_ホバー後.png'" onmouseout="this.src='/newReacQ/images/河童分かる_ホバー前.png'" width="150px" alt="河童_分かる"></button></td>
						<td><button type ="submit" name="mrea" value="分かるかも"><img src="/newReacQ/images/河童分かるかも_ホバー前.png" onmouseover="this.src='/newReacQ/images/河童分かるかも_ホバー後.png'" onmouseout="this.src='/newReacQ/images/河童分かるかも_ホバー前.png'" width="150px" alt="河童_分かるかも"></button></td>
						<td><button type ="submit" name="mrea" value="分からない"><img src="/newReacQ/images/河童分からない_ホバー前.png" onmouseover="this.src='/newReacQ/images/河童分からない_ホバー後.png'" onmouseout="this.src='/newReacQ/images/河童分からない_ホバー前.png'" width="150px" alt="河童_分からない"></button></td>
					</tr>
				</table>
			</form>
		</div>

			<!-- 掲示板 -->

		<div class="box02">
		<p>掲示板</p><a href="/newReacQ/BoardServlet">全部見る</a><br>
		<p>最新の投稿</p>

		<form method="GET" action="/newReacQ/MenuBoardServlet">
			<!-- 最新の投稿テーブル -->
			<!-- 固定コンテンツ -->
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
		</form>
		</div>

		<div class="box03">
		<p>自分の投稿</p>
		<form method="GET" action="/newReacQ/MenuBoardServlet">
		    <!-- 自分の投稿テーブル -->
		    <table>
		    	<c:forEach var="b" items="${mynewlist}" >
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
		</div>
	</div> <!-- id="main" end -->
</section>

<footer>
	<div id="foot">
		<jsp:include page="/WEB-INF/jsp/footer.jsp" />
	</div>
</footer>
</body>
</html>