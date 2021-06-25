<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.Board" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>掲示板</title>
  <link rel="stylesheet" href="css/board.css">
  <link rel="stylesheet" href="css/common.css">
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
  <form action="/newReacQ/BoardReplyServlet" name="post_form" method="POST">
  <div class="accordion">
      <p class="q-title"><span class="question">▼ ${b.question}</span> <span class="q_title_like">☆気になる:${b.count}</span></p>
      <div class="content">
     	  <h3>〇質問内容 <input type ="submit" name="SUBMIT" class="like_btn" value="気になる: ${b.count}"></h3>
     	  <input type="hidden" name="QUESTION_CODE" value="${b.question_code}">
  		  <p style="white-space:pre-wrap;">${b.question}</p>
  		  <p class="reply_date">${b.reply_date}</p>
  		  <h3>〇回答</h3>
   		  <input type="hidden" name="QUESTION" value="${b.question}">
  		  <p style="white-space:pre-wrap;">${b.question_reply}</p>
          <textarea name="QUESTION_REPLY" class="reply_form" placeholder="回答"></textarea><br><input type ="submit" name="SUBMIT" class="send_btn" value="送信">
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
  		<input type ="image" src="/newReacQ/images/河童投稿ホバー前.jpg" onmouseover="this.src='/newReacQ/images/河童投稿.jpg'" onmouseout="this.src='/newReacQ/images/河童投稿ホバー前.jpg'" alt="投稿フォーム"  class="p_form_btn" onclick = "showpost()">
  <!-- 投稿フォームのボタン ここまで -->
  </div>
  

   <!-- 投稿フォーム -->
  <form action="/newReacQ/BoardPostServlet" method="POST">
  	<div id = "post">
  		<div class="post1">
  		<h2 style="text-align:center">質問を入力してください。</h2>
  		<p style="color:#FF0000">※質問の重複を避けるため、投稿の前に自分と似たような質問をしている人が居ないか確認しましょう。</p>
  		<textarea name="QUESTION" placeholder="質問内容" class="p_form_q"></textarea><br>
  		<input type="submit" name="SUBMIT" class="p_btn" value="投稿"><input type="button" value ="閉じる" class="p_close_btn" onclick = "hidepost()">
  		</div>
  	</div>
  </form>
  </div>
  <!-- 投稿フォームここまで -->



<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/board.js"></script>
<script>  
let data = document.getElementsByClassName("question"); 
//console.log(data); 
for(let i=0; i < data.length; i++){  
	let short_str = data[i].textContent.substr(0,30);  
	data[i].textContent = short_str + "..."; 
	}  
</script>
</body>
</html>