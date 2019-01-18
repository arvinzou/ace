<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<title>班牌</title>
		<jsp:include page="../common/common.jsp"/>
		<link rel="stylesheet" type="text/css" href="css/index.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jquery-3.2.1.min.js"></script>
	</head>

	<body>
		<div class="content">
			<div class="head">
				<div class="class_name">一教室</div><img src="img/logo.png" />
				<div class="dateTime" id="clock">
					<p>1921年7月1日 </p>
					<p>12:00 星期六</p>
				</div>
			</div>
			<div class="body">
				<div class="info_box">
					<table id="" class="tftable" border="1">
						<tr>
							<th class="style1">班次</th>
							<th class="style2">第78期县处级干部进修班</th>
							<th class="style1">应道人数</th>
							<th class="style2">48人</th>
							<th class="style4"><span class="active_classInfo">详情</span></th>
						</tr>
						<tr>
							<td class="style3">上午课程</td>
							<td class="amClass"> 自习</td>
							<td class="style3"> 主讲</td>
							<td class="amClassTeacher">- -</td>
							<td rowspan="2" class="style4"> <span class="active_course">课表</span></td>
						</tr>
						<tr>
							<td class="style3"> 下午课程</td>
							<td class="pmClass">自习</td>
							<td class="style3"> 主讲</td>
							<td class="pmClassTeacher">- -</td>
						</tr>
					</table>
					<div class="message">
						<div class="title">
							<span>
								通知公告
							</span>
						</div>
						<div class="fileMessage" id="fileMessage">

						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="hide modal">
			<div class="modal_content classInfo">
				<div class=" modal_head">
					<span>班级信息</span> <span>×</span>
				</div>
				<div class="modal_body">
					<div class="title">
						<img src="img/icon_base.png" alt="" /><span>基本信息</span>
					</div>
					<div class="table">
						<table id="tfhover" class="tftable" border="1">
							<tr>
								<td class="style5">班次</td>
								<td>H第78期县处级干部进修班</td>
								<td class="fontBlue">相册</td>
							</tr>
							<tr>
								<td class="style5">班级教室</td>
								<td colspan="2"> 301室</td>
							</tr>
							<tr>
								<td class="style5">具体位置 </td>
								<td colspan="2"> 常德市委党校行政办公楼东侧三楼</td>
							</tr>
						</table>
					</div>

					<div class="title titleMT">
						<img src="img/icon_member.png" alt="" /><span>班级成员</span>
					</div>
					<div class="table">
						<table class="tftable" border="1">
							<tr>
								<td class="style5">班主任</td>
								<td>于越晖 </td>
								<td>17688886666</td>
							</tr>
							<tr>
								<td class="style5">跟班老师</td>
								<td>王 旭 </td>
								<td>17688886666</td>
							</tr>
							<tr>
								<td class="style5">跟班干部</td>
								<td>唐海琼 </td>
								<td>17688886666</td>
							</tr>
							<tr>
								<td class="style5">班级学员 </td>
								<td class="fontBlue" colspan="2"> 查看班级通讯录(34人)</td>
							</tr>
						</table>
					</div>

				</div>
			</div>
		</div>

		<div class=" hide modal">
			<div class="modal_content weeekClass">
				<div class=" modal_head">
					<span>周课表</span> <span>×</span>
				</div>
				<div class="modal_body">
					<div class="dayClass">
						<div class="left">
							<p>11.26</p>
							<p>周一</p>
						</div>
						<div class="right">
							<table>
								<tr>
									<td class="style6">上午</td>
									<td>上午上午上午上午上午上午上午上午上午上午上午上午上午上午上午上午</td>
									<td class="style6">上午</td>
									<td class="style7">上午上午</td>
								</tr>
								<tr>
									<td class="style6">上午</td>
									<td>上午上午上午上午上午上午上午上午上午上午上午上午上午上午上午上午</td>
									<td class="style6">上午</td>
									<td class="style7">上午上午</td>
								</tr>
							</table>
						</div>
					</div>

					<div class="dayClass">
						<div class="left">
							<p>11.26</p>
							<p>周一</p>
						</div>
						<div class="right">
							<table>
								<tr>
									<td class="style6">上午</td>
									<td>上午上午上午上午上午上午上午上午上午上午上午上午上午上午上午上午</td>
									<td class="style6">上午</td>
									<td class="style7">上午上午</td>
								</tr>
								<tr>
									<td class="style6">上午</td>
									<td>上午上午上午上午上午上午上午上午上午上午上午上午上午上午上午上午</td>
									<td class="style6">上午</td>
									<td class="style7">上午上午</td>
								</tr>
							</table>
						</div>
					</div>
					<div class="dayClass">
						<div class="left">
							<p>11.26</p>
							<p>周一</p>
						</div>
						<div class="right">
							<table>
								<tr>
									<td class="style6">上午</td>
									<td>上午上午上午上午上午上午上午上午上午上午上午上午上午上午上午上午</td>
									<td class="style6">上午</td>
									<td class="style7">上午上午</td>
								</tr>
								<tr>
									<td class="style6">上午</td>
									<td>上午上午上午上午上午上午上午上午上午上午上午上午上午上午上午上午</td>
									<td class="style6">上午</td>
									<td class="style7">上午上午</td>
								</tr>
							</table>
						</div>
					</div>
					<div class="dayClass">
						<div class="left">
							<p>11.26</p>
							<p>周一</p>
						</div>
						<div class="right">
							<table>
								<tr>
									<td class="style6">上午</td>
									<td>上午上午上午上午上午上午上午上午上午上午上午上午上午上午上午上午</td>
									<td class="style6">上午</td>
									<td class="style7">上午上午</td>
								</tr>
								<tr>
									<td class="style6">上午</td>
									<td>上午上午上午上午上午上午上午上午上午上午上午上午上午上午上午上午</td>
									<td class="style6">上午</td>
									<td class="style7">上午上午</td>
								</tr>
							</table>
						</div>
					</div>
					<div class="dayClass">
						<div class="left">
							<p>11.26</p>
							<p>周一</p>
						</div>
						<div class="right">
							<table>
								<tr>
									<td class="style6">上午</td>
									<td>上午上午上午上午上午上午上午上午上午上午上午上午上午上午上午上午</td>
									<td class="style6">上午</td>
									<td class="style7">上午上午</td>
								</tr>
								<tr>
									<td class="style6">上午</td>
									<td>上午上午上午上午上午上午上午上午上午上午上午上午上午上午上午上午</td>
									<td class="style6">上午</td>
									<td class="style7">上午上午</td>
								</tr>
							</table>
						</div>
					</div>

				</div>
			</div>
		</div>

		<div class="hide modal">
			<div class="modal_content photoWall">
				<div class=" modal_head">
					<span>相册</span> <span>×</span>
				</div>
				<div class="modal_body">
					<div class="imgBox">
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>

						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
						<div class="img">
							<img src="http://e.hiphotos.baidu.com/image/pic/item/1c950a7b02087bf49661186dffd3572c10dfcfa1.jpg" alt="" />
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="hide modal">
			<div class="modal_content addBook">
				<div class=" modal_head">
					<span>通讯录</span> <span>×</span>
				</div>
				<div class="modal_body">
				</div>
			</div>
		</div>

	</body>
	<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
</html>