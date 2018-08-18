<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="page-wrapper-row">
	<div class="page-wrapper-bottom">
		<div class="page-prefooter" style="border-radius: 0px!important;">
			<div class="container">
				<div class="row">
					<div class="col-md-6 col-sm-6 col-xs-12 footer-block">
						<img src="${cfg.qrcode}" alt="二维码" class="qrcode-default">
						<div style="padding-top:2px;padding-left:15px">微信公众号</div>
					</div>


					<div class="col-md-6 col-sm-6 col-xs-12 footer-block">
						<div style="font-size:14px">联系我们</div>
						<address class="margin-bottom-40">
							电话: ${cfg.tel}
							<br>
							邮箱:
							<a href="mailto:${cfg.email}"> ${cfg.email}</a>
							<br>
							地址：${cfg.addr}
						</address>
					</div>
				</div>
			</div>
		</div>
		<div class="page-footer" style="border-radius: 0px!important;">
			<div class="container"> 2018 &copy; 版权所有
				&nbsp;|&nbsp;
				湖南华彩伟业网络科技有限公司 &nbsp;|&nbsp;湖南·常德 湘ICP备16015679号
			</div>
		</div>
		<div class="scroll-to-top" style="display: block;">
			<i class="icon-arrow-up"></i>
		</div>
	</div>
</div>

