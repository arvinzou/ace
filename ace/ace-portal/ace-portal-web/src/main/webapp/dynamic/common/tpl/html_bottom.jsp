<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="page-wrapper-row">
	<div class="page-wrapper-bottom">
		<div class="page-footer">
			<div class="container"> 2018 &copy; 版权所有
				&nbsp;|&nbsp;
				湖南华彩伟业网络科技有限公司
			</div>
		</div>
	</div>
</div>

<div id="stack1" class="modal fade" tabindex="-1" data-width="300">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">密码修改</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<form id="fm-password">
							<fieldset>
								新设密码： <input id="password" type="password" style="width: 200px;"/>
							</fieldset>
							<fieldset>
								重复输入： <input id="repassword" type="password" style="width: 200px;"/>
							</fieldset>
						</form>
					</div>
				</div>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn blue" onclick="submitform()">提交</button>
				<button type="button" data-dismiss="modal" class="btn red">取消</button>
			</div>
		</div>
	</div>
</div>