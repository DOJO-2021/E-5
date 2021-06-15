<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>reacQ|ログイン</title>
<link rel="stylesheet" href="/newReacQ/css/login.css">
</head>
<body>


<section class="forms-section">
  <h1 class="section-title">ReacQ</h1>
  <div class="forms">
    <div class="form-wrapper is-active">
      <!--ログイン切り替え-->
      <button type="button" class="switcher switcher-login">
        ログイン
        <span class="underline"></span>
      </button>
      <form class="form form-login" method="POST" name="form1"action="/newReacQ/LoginServlet">
        <!--フォームグループ化(fieldset)-->
        <fieldset>
          <legend>Please, enter your email and password for login.</legend>
          <div class="input-block">
            <label for="login-email">E-mail</label>
            <input id="login-email" type="email" name="EMAIL" required>
          </div>
          <div class="input-block">
            <label for="login-password">Password</label>
            <input id="login-password" type="password" name="PASSWORD" required>
          </div>
        </fieldset>
        <!--ログインボタン-->
        <button type="submit" class="btn-login" id=rogu>ログイン</button>
      </form>
    </div>
    <div class="form-wrapper">
      <!--新規登録切り替え-->
      <button type="button" class="switcher switcher-signup">
        新規登録
        <span class="underline"></span>
      </button>
      <form class="form form-signup" method="POST" name="form" action="/newReacQ/ResistServlet">
         <!--フォームグループ化(fieldset)-->
        <fieldset>
          <legend>Please, enter your email, password and password confirmation for sign up.</legend>
          <div class="input-block">
            <label for="signup-email">E-mail</label>
            <input id="signup-email" type="email"  name="EMAIL"required>
          </div>
          <div class="input-block">
            <label for="signup-password">Password</label>
            <input id="signup-password" type="password"  name="PASSWORD"required>
          </div>
          <div class="input-block">
            <label for="name">name</label>
            <input id="name"  type="name" name="NAME" required>
          </div>
        </fieldset>

        <!--ラジオボタン 初期状態は受講者選択-->
        <div class="input-blok">
         <input type="radio" id="inq1" name="POSITION" value="1" checked>
	     <label for="inq1">受講者</label>
	     <input type="radio" id="inq2" name="POSITION" value="0">
	     <label for="inq2">講師</label>
	     </div>
         <!--新規登録ボタン-->
        <button type="submit" class="btn-signup"id="touroku" >新規登録</button>
      </form>
    </div>
  </div>
</section>

<script>
'use strict'

const switchers = [...document.querySelectorAll('.switcher')]

switchers.forEach(item => {
  item.addEventListener('click', function() {
    switchers.forEach(item => item.parentElement.classList.remove('is-active'))
    this.parentElement.classList.add('is-active')
  })
})

//登録完了アラート
function butotnClick(){
	if(document.form.EMAIL.value != ""&& document.form.PASSWORD.value !=""&& document.form.NAME.value!=""){
    alert('登録完了');
    return true;
	}else{alert('項目を入力してください')
	return false;
	}
}
let button = document.getElementById('touroku');
button.onclick = butotnClick;

</script>

</body>
</html>
