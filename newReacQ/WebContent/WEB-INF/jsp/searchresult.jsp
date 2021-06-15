<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <input type="radio" name="" value="すべて">すべて <input type="radio" name="" value="回答受付中">回答受付中 <input type="radio" name="" value="解決済み">解決済み <input type="radio" name="example" value="気になる"> 「気になる」した質問 <form action="/newReacQ/BoardServlet" method="POST"><input type ="text" name="" placeholder="検索"><input type ="submit" name="" value="検索"></form>
  <p>検索結果</p>
  <!-- 投稿された質問の折りたたみメニュー -->
  <c:forEach var="e" items="${resultlist}" >
    <div class="wrap">
      <label for="">▼質問 <input type ="button" value="気になる" id=""></label>
      <input type="checkbox" id="label1" class="switch" />
      <div class="content">
        <p>質問内容</p>
        <p>質問詳細</p>
          <input type = "text" placeholder="回答"><input type ="submit" value="送信">
      </div>
    </div>
  </c:forEach>
  <!-- 折りたたみメニューここまで -->

  <form id="f1" action="#">
  <!-- 投稿フォームのボタン -->
  <input type ="button" value="投稿フォーム" onclick = "showpost()">
  <!-- 投稿フォームのボタン ここまで -->

   <!-- 投稿フォーム -->
  <form action="/newReacQ/BoardServlet" method="POST">
  <div id = "post">
  <input type = "text" ><input type = "submit" value="投稿">
  <input type ="button" value ="閉じる" onclick = "hidepost()">
  </div>
  </form>
  <!-- 投稿フォームここまで -->
  </form>

</body>
</html>