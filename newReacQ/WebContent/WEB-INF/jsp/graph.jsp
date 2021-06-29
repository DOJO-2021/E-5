 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div class="canvas-container">
		<canvas id="graph-area"></canvas>
			<script type="text/javascript">
				   // ▼グラフの中身
				   var barChartData = {
				      labels : ["説明できる","わかる","わかるかも","わからない"],
				      datasets : [
				         {
				        	 fillColor : "rgba(255,225,109,0.6)",    // 塗りつぶし色
				             strokeColor : "rgba(255,225,109,0.9)",  // 枠線の色
				             highlightFill: "rgba(255,209,109,0.8)",  // マウスが載った際の塗りつぶし色
				             highlightStroke: "rgba(255,209,109,1)",   // マウスが載った際の枠線の色
				            data : [ ${rea3} , ${rea2} , ${rea1} , ${rea0} ]     // 各棒の値(カンマ区切りで指定)
				         },
				      ]
				   }
				   // ▼上記のグラフを描画するための記述
				   window.onload = function(){
				      var ctx = document.getElementById("graph-area").getContext("2d");
				      window.myBar = new Chart(ctx).Bar(barChartData);
				   }
			</script>
	</div>