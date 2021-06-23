<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js" type="text/javascript"></script>
<title>リアクション</title>
</head>
<body>
	<div class="canvas-container">
		<canvas id="graph-area" height="250" width="650" ></canvas>
			<script type="text/javascript">
				   // ▼グラフの中身
				   var barChartData = {
				      labels : ["説明可","わかる","わかるかも","わからない"],
				      datasets : [
				         {
				        	 fillColor : "rgba(255,225,109,0.6)",    // 塗りつぶし色
				             strokeColor : "rgba(255,225,109,0.9)",  // 枠線の色
				             highlightFill: "rgba(255,209,109,0.8)",  // マウスが載った際の塗りつぶし色
				             highlightStroke: "rgba(255,209,109,1)",   // マウスが載った際の枠線の色
				            data : [ ${rea3 } , ${rea2} , ${rea1} , ${rea0} ]     // 各棒の値(カンマ区切りで指定)
				         },
				      ]
				   }
				   // ▼上記のグラフを描画するための記述
				   window.onload = function(){
				      var ctx = document.getElementById("graph-area").getContext("2d");
				      window.myBar = new Chart(ctx).Bar(barChartData);
				   }
				$(function () {
				    var container = $('.canvas-container');
				    var ctx= $('#graph-area');
				    ctx.attr('width', container.width());
				    ctx.attr('height', 300);
				});
			</script>
	</div>
</body>
</html>