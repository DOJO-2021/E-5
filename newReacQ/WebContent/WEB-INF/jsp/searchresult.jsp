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
  <input type="radio" name="qsort" value="すべて" checked>すべて <input type="radio" name="qsort" value="回答受付中">回答受付中 <input type="radio" name="qsort" value="解決済み">解決済み <input type="radio" name="qsort" value="気になる"> 「気になる」した質問 <form action="/newReacQ/BoardServlet" method="POST"><input type ="text" name="QUESTION" placeholder="検索"><input type ="submit" name="" value="検索"></form>
  <!-- 投稿された質問の折りたたみメニュー -->
  <h2>検索結果</h2>
  <section class="">
  <c:forEach var="c" items="${resultlist}">
  <div class="accordion">
      <p class="question q-title">▼質問 <input type ="button" value="気になる ${Likelist.size }" id=""></p>
      <div class="content">
  		  <p>${c.question}</p>
          <form action="/newReacQ/BoardReplyServlet" method="POST"><input type = "text" placeholder="回答"><input type ="submit" value="送信"></form>
      </div>
  </div>
  </c:forEach>
  </section>
  <!-- 折りたたみメニューここまで -->


  <form id="f1" action="#">
  <!-- 投稿フォームのボタン -->
  <input type ="button" value="投稿フォーム" onclick = "showpost()">
  <!-- 投稿フォームのボタン ここまで -->

   <!-- 投稿フォーム -->
  <form action="/newReacQ/BoardPostServlet" method="POST">
  <div id = "post">
  <input type = "text"  name="QUESTION"><input type = "submit" value="投稿">
  <input type ="button" value ="閉じる" onclick = "hidepost()">
  </div>
  </form>
  <!-- 投稿フォームここまで -->
  </form>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/board.js"></script>

</body>
</html>