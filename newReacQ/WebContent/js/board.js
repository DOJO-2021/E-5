'use strict';
​
//質問投稿フォームの開閉
function hideForm(){
	document.getElementById("formblock").style.display = "none";
}
function showForm() {
	document.getElementById("formblock").style.display = "block";
}
window.onload = function(){
hideForm();
};