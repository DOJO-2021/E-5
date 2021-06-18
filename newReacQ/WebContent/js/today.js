
var date = new Date();

var yyyy = date.getFullYear();
var mm = ("0"+(date.getMonth()+1)).slice(-2);
var dd = ("0"+date.getDate()).slice(-2);

document.getElementById("date_r").value=yyyy+'-'+mm+'-'+dd;
document.getElementById("date_b").value=yyyy+'-'+mm+'-'+dd;

window.addEventListener('DOMContentLoaded', function(){
  // (1)パスワード入力欄とボタンのHTMLを取得
  let btn_passview = document.getElementById("btn_passview");
  let input_pass = document.getElementById("password");
  let input_pass2 = document.getElementById("passwordConfirm");

  // (2)ボタンのイベントリスナーを設定
  btn_passview.addEventListener("click", (e)=>{
    // (3)ボタンの通常の動作をキャンセル（フォーム送信をキャンセル）
    e.preventDefault();

    // (4)パスワード入力欄のtype属性を確認
    if( input_pass.type === 'password') {
      // (5)パスワードを表示する
      input_pass.type = 'text';
      input_pass2.type = 'text';
      btn_passview.textContent = '非表示';
    } else {
      // (6)パスワードを非表示にする
      input_pass.type = 'password';
      input_pass2.type = 'password';
      btn_passview.textContent = '表示';
    }
  });
});


// PWが二回入力されているかどうかの確認
document.getElementById('form').onsubmit = function(){

	// エラーメッセージをクリアする
    form.password.setCustomValidity("");

	// 入力値が同じかどうか
	if(form.password.value != form.passwordConfirm.value){
		// 一致していなかったら、エラーメッセージを表示する
      form.password.setCustomValidity("パスワードと確認用パスワードが一致しません");
    }
  };
  // 入力値チェックエラーが発生したときの処理
  form.addEventListener("invalid", function() {
    document.getElementById("errorMessage").innerHTML = "入力値にエラーがあります";
  }, false);
