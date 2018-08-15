<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta name="format-detection" content="telephone=no" />
		<title>如何更好的了解孩子</title>
		<link rel="stylesheet" type="text/css" href="../common/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="../common/css/iconfont.css" />
		<link rel="stylesheet" type="text/css" href="../common/css/font-awesome.min.css" />
		<link rel="stylesheet" type="text/css" href="../common/css/star-rating.css" />
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<script type="text/javascript" src="../common/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="../common/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="../common/js/init-rem.js"></script>
		<script type="text/javascript" src="../common/js/star-rating.js"></script>
		<script type="text/javascript">
			jQuery(document).ready(function() {
				var $inp = $('#rating-input');
				$inp.rating({
					min: 0,
					max: 5,
					step: 1,
					size: 'xs',
					showClear: false
				});
			});
		</script>
	</head>

	<body>
		<div class="container">
			<div class="course_basic">
				<div class="row">
					<div class="col-xs-12 col-md-12">
						<div class="banner">
							<img src="img/banner.png" />
						</div>
					</div>
				</div>
				<div class="row course_title">
					<div class="col-xs-12 col-md-12">
						<h3>01 如何更好地了解孩子？</h3>
					</div>
				</div>
				<div class="row teacher_name">
					<div class="col-xs-12 col-md-12"><p>肖海平</p></div>
				</div>
				<div class="row course_audio">
					
				</div>
			</div>
			<div class="comment_list">
				<div class="row statics">
					<div class="col-xs-6 col-md-6">
						<span class="evaluate">评价（682个评价）</span>
					</div>
					<div class="col-xs-6 col-md-6" style="text-align: right;">
						<a href="#" data-toggle="modal" data-target="#myModal">
							<img src="img/icon_comment.png" class="comments_icon"/>
							<span class="comment">发表评论</span>
						</a>
					</div>
				</div>
				<div class="row list">
					<div class="row list_item">
						<div class="row">
							<div class="col-xs-3 col-md-3">
								<img src="img/headImg.png" class="headImg" />
							</div>
							<div class="col-xs-5 col-md-5">
								<p class="commonts_name">半分</p>
								<p class="commonts_time">2018-04-03</p>
							</div>
							<div class="col-xs-4 col-md-4" style="text-align: right;">
								<img src="img/comment_level.png" class="comment_level" />
								<img src="img/comment_level.png" class="comment_level" />
								<img src="img/comment_level.png" class="comment_level" />
							</div>
						</div>
						<div class="row">
							<div class="col-xs-3 col-md-3"></div>
							<div class="col-xs-9 col-md-9">
								<p class="comment_content">
									刚刚看完的这个视频，多少了解了自闭症的信息，比如自闭症和疫苗没有关系，自闭症和遗传基因有关系，而且还有别的原因，但是现在没有太好的方法等等。
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<!--评论模态框-->
		<!--评价框模态窗-->
		<div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<div class="container star">
							<form>
								<input value="5" id="rating-input" type="text" title="" />
								<span class="demo">推荐</span><br />
								<textarea class="point" placeholder="鼓励下老师吧~" onfocus="this.placeholder=''" onblur="this.placeholder='鼓励下老师吧~'"></textarea><br />
								<a href="#" class="commit">提&nbsp;交</a>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>