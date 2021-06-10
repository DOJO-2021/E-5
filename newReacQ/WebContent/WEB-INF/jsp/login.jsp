<!--<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>reacQ|ログイン</title>
<link rel="stylesheet" href="/newReacQ/css/login.css">
</head>
<body>
​
<section class="forms-section">
  <h1 class="section-title">ReacQ</h1>
  <div class="forms">
    <div class="form-wrapper is-active">
      <!--ログイン切り替え-->
      <button type="button" class="switcher switcher-login">
        ログイン
        <span class="underline"></span>
      </button>
      <form class="form form-login">
        <!--フォームグループ化(fieldset)-->
        <fieldset>
          <legend>Please, enter your email and password for login.</legend>
          <div class="input-block">
            <label for="login-email">E-mail</label>
            <input id="login-email" type="email" required>
          </div>
          <div class="input-block">
            <label for="login-password">Password</label>
            <input id="login-password" type="password" required>
          </div>
        </fieldset>
        <!--ログインボタン-->
        <button type="submit" class="btn-login">ログイン</button>
      </form>
    </div>
    <div class="form-wrapper">
      <!--新規登録切り替え-->
      <button type="button" class="switcher switcher-signup">
        新規登録
        <span class="underline"></span>
      </button>
      <form class="form form-signup">
         <!--フォームグループ化(fieldset)-->
        <fieldset>
          <legend>Please, enter your email, password and password confirmation for sign up.</legend>
          <div class="input-block">
            <label for="signup-email">E-mail</label>
            <input id="signup-email" type="email" required>
          </div>
          <div class="input-block">
            <label for="signup-password">Password</label>
            <input id="signup-password" type="password" required>
          </div>
          <div class="input-block">
            <label for="name">name</label>
            <input id="name" type="password" required>
          </div>
        </fieldset>
         <!--ラジオボタン-->
         <input type="radio" id="inq1" name="num_of_inq" value="1">
	     <label for="inq1">受講者</label>
	     <input type="radio" id="inq2" name="num_of_inq" value="2">
	     <label for="inq2">講師</label>
         <!--新規登録ボタン-->
        <button type="submit" class="btn-signup">新規登録</button>
      </form>
    </div>
  </div>
</section>
​
​
<script>
'use strict'
​
const switchers = [...document.querySelectorAll('.switcher')]
​
switchers.forEach(item => {
  item.addEventListener('click', function() {
    switchers.forEach(item => item.parentElement.classList.remove('is-active'))
    this.parentElement.classList.add('is-active')
  })
})
​
</script>
​
</body>
</html>
