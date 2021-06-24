<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ReacQ|掲示板</title>
<link rel="stylesheet" href="css/board.css">
<link rel="stylesheet" href="/newReacQ/css/common.css">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
  <!-- memo 質問の状態ソート reply_status -->
  <form action="/newReacQ/BoardServlet" method="POST">
  <input type="radio" name="qsort" value="すべて" checked>すべて
  <input type="radio" name="qsort" value="回答受付中">回答受付中
  <input type="radio" name="qsort" value="解決済み">解決済み
  <input type="radio" name="qsort" value="気になる">「気になる」した質問
  <input type ="text" name="QUESTION" placeholder="フリーワード"><input type ="submit" name="" value="検索">
  </form>
  <div class="wrapper">
  <!-- 投稿された質問の折りたたみメニュー -->
  <section class="">
  <c:forEach var="b" items="${Alllist}">
  <form action="/newReacQ/BoardReplyServlet" method="POST">
  <div class="accordion">
      <p class="question q-title"> ▼ ${b.question} <span class="q_title_like">気になる:${b.count}</span></p>
      <div class="content">
      <input type ="submit" name="SUBMIT" value="気になる" class="like_btn">
	      <h3>質問内容</h3>
	      <input type="hidden" name="QUESTION_CODE" value="${b.question_code}">
  		  <p style="white-space:pre-wrap;">${b.question}</p>
  		  <input type="hidden" name="QUESTION" value="${b.question}">
  		  <h3>回答</h3>
  		  <p style="white-space:pre-wrap;">${b.question_reply}</p>
          <textarea name="QUESTION_REPLY" class="reply_form" placeholder="回答"></textarea><input type ="submit" name="SUBMIT" class="send_btn" value="送信">
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
  		<input type ="button" value="投稿フォーム" class="p_form_btn" onclick = "showpost()">
  <!-- 投稿フォームのボタン ここまで -->
  </div>

   <!-- 投稿フォーム -->
  <form action="/newReacQ/BoardPostServlet" method="POST">
  	<div id = "post">
  		<div class="post1">
  		<h2 style="text-align:center">質問を入力してください。</h2>
  		<p style="color:#FF0000">※質問の重複を避けるため、投稿の前に自分と似たような質問をしている人が居ないか確認しましょう。</p>
  		<textarea name="QUESTION" placeholder="質問内容" class="p_form_q"></textarea><br><!--  <input type="text" name="QUESTION" placeholder="質問内容"> -->
  		<input type="submit" name="SUBMIT" class="p_btn" value="投稿"><input type="button" value ="閉じる" class="p_close_btn" onclick = "hidepost()">
  		</div>
  	</div>
  </form>
  </div>
  <!-- 投稿フォームここまで -->

<jsp:include page="footer.jsp" flush="true" />

<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/board.js"></script>

</body>
</html>