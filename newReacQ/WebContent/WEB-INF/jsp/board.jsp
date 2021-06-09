<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>掲示板</title>
  <link rel="stylesheet" href="">
</head>
<body>
  <!-- memo 質問の状態ソート reply_status -->
  <input type="radio" name="qsort" value="すべて">すべて <input type="radio" name="qsort" value="回答受付中">回答受付中 <input type="radio" name="qsort" value="解決済み">解決済み <input type="radio" name="qsort" value="気になる"> 「気になる」した質問 <input type ="text" name="" placeholder="検索"><input type ="submit" name="" value="検索">
  <!-- 投稿された質問の折りたたみメニュー -->
  <!--<c:forEach var="e" items="" >-->
    <div class="wrap">
      <label for="">▼質問 <input type ="button" value="気になる" id=""></label><!-- memo気になるボタンのカウントアップと取り消し -->
      <input type="checkbox" id="label1" class="switch" />
      <div class="content">
        <p>質問内容</p>
        <p>質問詳細</p>
          <input type = "text" placeholder="回答"><input type ="submit" value="送信">
      </div>
    </div>
  <!--</c:forEach>-->
  <!-- 折りたたみメニューここまで -->

  <!-- 投稿フォームのボタン -->
  <!-- memoクリック時の処理をjsで追加予定 -->
  <input type ="submit" value="投稿フォーム">
  <!-- 投稿フォームのボタン ここまで -->

</body>
</html>