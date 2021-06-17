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
	$(".q-title").on("click", function () {
        	$(this).next().slideToggle();
        	});