<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/newReacQ/css/graph.css">
<title>リアクション</title>
</head>
<body>
<div class="chart-wrap">
  <div class="chart-title">
    リアクション
  </div>
  <div id="dashboard-stats" class="chart bars-horizontal brand-primary">
    <div class="row">
      <span class="label">説明可</span>
      <div class="bar-wrap">
        <div class="bar" data-value="${rea3}"></div>
      </div>
      <span class="number">${rea3}</span>
    </div>
    <div class="row">
      <span class="label">わかる</span>
      <div class="bar-wrap">
        <div class="bar" data-value="${rea2}"></div>
      </div>
      <span class="number">${rea2}</span>
    </div>
    <div class="row">
      <span class="label">わかるかも</span>
      <div class="bar-wrap">
        <div class="bar" data-value="${rea1}"></div>
      </div>
      <span class="number">${rea1}</span>
    </div>
    <div class="row">
      <span class="label">わからない</span>
      <div class="bar-wrap">
        <div class="bar" data-value="${rea0}"></div>
      </div>
      <span class="number">${rea0}</span>
    </div>
    </div>
</div>
<script type='text/javascript' src='https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js' id='jquery-js'></script>
<script>
(function($) {
	  function generateBarGraph(wrapper) {
	    // 配列の用意
	    var values = [];

	    // 取得して配列に格納
	    $(wrapper + ' .bar').each(function(index, el) {
	      values.push($(this).data('value'));
	    });

	    // 配列から最大値を取得
	    var max_value = Math.max.apply(Math, values);

	    // バーの値
	    $(wrapper + ' .bar').each(function(index, el) {
	      var bar = $(this),
	          value = bar.data('value'),
	          percent = Math.ceil((value / max_value) * 100);


	      //幅とクラス
	      bar.width(percent + '%');
	      bar.addClass('in');
	    });
	  }

	  // Generate the bar graph on window load...
	  $(window).on('load', function(event) {
	    generateBarGraph('#dashboard-stats');
	  });
	})(jQuery); // Fully reference jQuery after this point.
</script>
</body>
</html>
