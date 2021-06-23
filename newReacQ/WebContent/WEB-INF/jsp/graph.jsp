<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/newReacQ/css/.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js" type="text/javascript"></script>
<title>リアクション</title>
</head>
<body>
<canvas id="graph-area" height="300" width="650"></canvas>
<script type="text/javascript">

   // ▼グラフの中身
   var barChartData = {
      labels : ["説明可","わかる","わかるかも","わからない"],
      datasets : [
         {
            fillColor : "rgba(240,128,128,0.6)",    // 塗りつぶし色
            strokeColor : "rgba(240,128,128,0.9)",  // 枠線の色
            highlightFill: "rgba(255,64,64,0.75)",  // マウスが載った際の塗りつぶし色
            highlightStroke: "rgba(255,64,64,1)",   // マウスが載った際の枠線の色
            data : [ ${rea3 } , ${rea2} , ${rea1} , ${rea0} ]     // 各棒の値(カンマ区切りで指定)

         },

      ]

   }

   // ▼上記のグラフを描画するための記述
   window.onload = function(){
      var ctx = document.getElementById("graph-area").getContext("2d");
      window.myBar = new Chart(ctx).Bar(barChartData);
   }
</script>
</body>
</html>
