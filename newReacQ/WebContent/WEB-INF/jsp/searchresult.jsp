<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索結果</title>
  <link rel="stylesheet" href="css/board.css">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
  <!-- memo 質問の状態ソート reply_status -->
  <form action="/newReacQ/BoardServlet" method="POST">
  <input type="radio" name="qsort" value="すべて" checked>すべて
  <input type="radio" name="qsort" value="回答受付中">回答受付中
  <input type="radio" name="qsort" value="解決済み">解決済み
  <input type="radio" name="qsort" value="気になる">「気になる」した質問
  <input type ="text" name="QUESTION" placeholder="検索"><input type ="submit" name="" value="検索">
  </form>
  <div class="wrapper">
  <!-- 投稿された質問の折りたたみメニュー -->
  <section class="">
  <c:forEach var="b" items="${Alllist}">
  <form action="/newReacQ/BoardReplyServlet" method="POST">
  <div class="accordion">
      <p class="question q-title">▼質問 <input type ="submit" name="LIKE" value="気になる" id="">${b.count}</p>
      <div class="content">
  		  <p>${b.question}</p>
  		  <input type="hidden" name="QUESTION" value="${b.question}">
  		  <p>${b.question_reply}</p>
          <input type = "text" name="QUESTION_REPLY" placeholder="回答"><input type ="submit" value="送信">
      </div>
  </div>
  </form>
  </c:forEach>
  </section>
  <!-- 折りたたみメニューここまで -->
</div>

<div class="postform">
<div class="button">
  <!-- 投稿フォームのボタン -->
  		<input type ="button" value="投稿フォーム" onclick = "showpost()">
  <!-- 投稿フォームのボタン ここまで -->
  </div>

   <!-- 投稿フォーム -->
  <form action="/newReacQ/BoardPostServlet" method="POST">
  	<div id = "post">
  		<div class="post1">
  		<input type="text" name="QUESTION" placeholder="質問内容" style="width:430px;">
  		<input type="submit" name="SUBMIT" value="投稿"><br>
  		<input type="button" value ="閉じる" onclick = "hidepost()">
  		</div>
  	</div>
  </form>
  </div>
  <!-- 投稿フォームここまで -->

<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/board.js"></script>

</body>
</html>