<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>运营数据</title>
</head>
<jsp:include page="../../common/common.jsp" />
<script type="text/javascript">


</script>
<body>
	<div class="page-content">
		<div class="widget-box" id="widget-box">
			<div class="widget-header">
				<h5 class="widget-title smaller">设置查询条件</h5>

				<div class="widget-toolbar"></div>
			</div>

			<div class="widget-body">
				<div class="widget-main padding-6">
					<form action="#" id="fm-search">
					
				类别：<input
							class="easyui-combobox" style="width: 200px" name="category"
							data-options="
                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=69&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'">

                     名称： <input name="name" type="text"
                                        							style="width: 200px;" />
						<button class="btn btn-info" id="btn-search"
							authority="${pageContext.request.contextPath}/normData/findNormDataList.do">
							 <i
								class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
						</button>

						
					</form>
					<div class="space10"></div>
					<div id="toolbar" class="toolbar">

						
						<button class="btn btn-info" id="btn-view-add"
							authority="${pageContext.request.contextPath}/normData/insertNormData.do">
							 <i
								class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-info" id="btn-view-edit"
							authority="${pageContext.request.contextPath}/normData/updateNormData.do">
							 <i
								class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-warning" id="btn-view-del"
							authority="${pageContext.request.contextPath}/normData/deleteNormDataByNormDataId.do">
							 <i
								class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
						</button>
						
					</div>
				</div>
			</div>
		</div>

		<table id="grid-table"></table>

		<div id="grid-pager"></div>
		
		
	</div>
	<div id="dialog-message" class="hide">
        		<div id="uploader">
        			<p>Your browser doesn't have Flash, Silverlight or HTML5 support.</p>
        		</div>
    </div>
    <div id="dialog-message-file" class="hide">
        <div id="load" class="loading"></div>
    </div>

    <div id="dialog-message-view" class="hide">
<h5 class="header-title">基本信息</h5><div  class="row"  style="padding:10px">
<div class="labelItem"><span class="labelItemHeader">
编码</span>
<br>
<span id="id">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
会议编码</span>
<br>
<span id="meetingId">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
议题编码</span>
<br>
<span id="topicId">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
指标编码</span>
<br>
<span id="normId">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
产品编号</span>
<br>
<span id="productId">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
年度</span>
<br>
<span id="year">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
上年度指标</span>
<br>
<span id="lastYear">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt1</span>
<br>
<span id="wkt1">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt2</span>
<br>
<span id="wkt2">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt3</span>
<br>
<span id="wkt3">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt4</span>
<br>
<span id="wkt4">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt5</span>
<br>
<span id="wkt5">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt6</span>
<br>
<span id="wkt6">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt7</span>
<br>
<span id="wkt7">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt8</span>
<br>
<span id="wkt8">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt9</span>
<br>
<span id="wkt9">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt10</span>
<br>
<span id="wkt10">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt11</span>
<br>
<span id="wkt11">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt12</span>
<br>
<span id="wkt12">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt13</span>
<br>
<span id="wkt13">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt14</span>
<br>
<span id="wkt14">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt15</span>
<br>
<span id="wkt15">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt16</span>
<br>
<span id="wkt16">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt17</span>
<br>
<span id="wkt17">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt18</span>
<br>
<span id="wkt18">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt19</span>
<br>
<span id="wkt19">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt20</span>
<br>
<span id="wkt20">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt21</span>
<br>
<span id="wkt21">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt22</span>
<br>
<span id="wkt22">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt23</span>
<br>
<span id="wkt23">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt24</span>
<br>
<span id="wkt24">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt25</span>
<br>
<span id="wkt25">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt26</span>
<br>
<span id="wkt26">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt27</span>
<br>
<span id="wkt27">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt28</span>
<br>
<span id="wkt28">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt29</span>
<br>
<span id="wkt29">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt30</span>
<br>
<span id="wkt30">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt31</span>
<br>
<span id="wkt31">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt32</span>
<br>
<span id="wkt32">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt33</span>
<br>
<span id="wkt33">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt34</span>
<br>
<span id="wkt34">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt35</span>
<br>
<span id="wkt35">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt36</span>
<br>
<span id="wkt36">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt37</span>
<br>
<span id="wkt37">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt38</span>
<br>
<span id="wkt38">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt39</span>
<br>
<span id="wkt39">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt40</span>
<br>
<span id="wkt40">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt41</span>
<br>
<span id="wkt41">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt42</span>
<br>
<span id="wkt42">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt43</span>
<br>
<span id="wkt43">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt44</span>
<br>
<span id="wkt44">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt45</span>
<br>
<span id="wkt45">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt46</span>
<br>
<span id="wkt46">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt47</span>
<br>
<span id="wkt47">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt48</span>
<br>
<span id="wkt48">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt49</span>
<br>
<span id="wkt49">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt50</span>
<br>
<span id="wkt50">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt51</span>
<br>
<span id="wkt51">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt52</span>
<br>
<span id="wkt52">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkt53</span>
<br>
<span id="wkt53">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc1</span>
<br>
<span id="wkc1">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc2</span>
<br>
<span id="wkc2">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc3</span>
<br>
<span id="wkc3">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc4</span>
<br>
<span id="wkc4">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc5</span>
<br>
<span id="wkc5">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc6</span>
<br>
<span id="wkc6">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc7</span>
<br>
<span id="wkc7">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc8</span>
<br>
<span id="wkc8">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc9</span>
<br>
<span id="wkc9">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc10</span>
<br>
<span id="wkc10">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc11</span>
<br>
<span id="wkc11">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc12</span>
<br>
<span id="wkc12">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc13</span>
<br>
<span id="wkc13">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc14</span>
<br>
<span id="wkc14">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc15</span>
<br>
<span id="wkc15">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc16</span>
<br>
<span id="wkc16">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc17</span>
<br>
<span id="wkc17">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc18</span>
<br>
<span id="wkc18">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc19</span>
<br>
<span id="wkc19">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc20</span>
<br>
<span id="wkc20">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc21</span>
<br>
<span id="wkc21">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc22</span>
<br>
<span id="wkc22">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc23</span>
<br>
<span id="wkc23">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc24</span>
<br>
<span id="wkc24">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc25</span>
<br>
<span id="wkc25">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc26</span>
<br>
<span id="wkc26">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc27</span>
<br>
<span id="wkc27">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc28</span>
<br>
<span id="wkc28">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc29</span>
<br>
<span id="wkc29">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc30</span>
<br>
<span id="wkc30">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc31</span>
<br>
<span id="wkc31">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc32</span>
<br>
<span id="wkc32">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc33</span>
<br>
<span id="wkc33">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc34</span>
<br>
<span id="wkc34">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc35</span>
<br>
<span id="wkc35">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc36</span>
<br>
<span id="wkc36">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc37</span>
<br>
<span id="wkc37">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc38</span>
<br>
<span id="wkc38">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc39</span>
<br>
<span id="wkc39">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc40</span>
<br>
<span id="wkc40">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc41</span>
<br>
<span id="wkc41">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc42</span>
<br>
<span id="wkc42">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc43</span>
<br>
<span id="wkc43">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc44</span>
<br>
<span id="wkc44">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc45</span>
<br>
<span id="wkc45">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc46</span>
<br>
<span id="wkc46">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc47</span>
<br>
<span id="wkc47">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc48</span>
<br>
<span id="wkc48">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc49</span>
<br>
<span id="wkc49">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc50</span>
<br>
<span id="wkc50">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc51</span>
<br>
<span id="wkc51">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc52</span>
<br>
<span id="wkc52">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
wkc53</span>
<br>
<span id="wkc53">
</span>
</div>
</div>
<h5 class="header-title">操作信息</h5><div  class="row"  style="padding:10px">
<div class="labelItem"><span class="labelItemHeader">
创建人编码</span>
<br>
<span id="createUserId">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
创建人姓名</span>
<br>
<span id="createUserName">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
入库日期</span>
<br>
<span id="createDate">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
最后更新人编码</span>
<br>
<span id="lastModifyUserId">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
最后更新人姓名</span>
<br>
<span id="lastModifyUserName">
</span>
</div>
<div class="labelItem"><span class="labelItemHeader">
最后更新时间</span>
<br>
<span id="lastModifyDate">
</span>
</div>
</div>

    </div>
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/service/normData/config.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/normData/model.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/normData/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/normData/view.js?version=${cfg.version}"></script>
	<jsp:include page="../../common/footer-2.jsp" />
	<script type="text/javascript">
window.onresize = function () {
	console.log('autoWidthJqgrid');
	$(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
	$(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight-layoutTopHeight);
	parent.autoWidth();
}
</script>
</body>
</html>