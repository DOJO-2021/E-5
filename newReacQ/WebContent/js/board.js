'use strict';

//投稿フォームの開閉
	function hidepost(){
		document.getElementById("post").style.display = "none";
	}
	function showpost() {
		document.getElementById("post").style.display = "block";
	}
	window.onload = function(){
	hidepost();
	};

//投稿された質問の折りたたみメニュー
   const question = document.querySelectorAll(".q-title");

      function toggle() {
        const content = this.nextElementSibling;
        this.classList.toggle("is-active");
        content.classList.toggle("is-open");
      }

      for (let i = 0; i < question.length; i++) {
        question[i].addEventListener("click", toggle);
      }