function checkType() {
	1 == reportType ? ($("#j-row-img, #j-upcontain .xcy-cutimg").removeClass("fn-hide"), $("#j-row-video").addClass("fn-hide")) : 4 == reportType && ($("#j-row-img, #j-upcontain .xcy-cutimg").addClass("fn-hide"), $("#j-row-video").removeClass("fn-hide"))
}
function createUpImg() {
	var e, t = {
		mime_types: [{
			title: "Image files",
			extensions: "jpg,jpeg,gif,png,bmp"
		}],
		max_file_size: "20480kb",
		prevent_duplicates: !1
	},
	r = "",
	i = "";
	parent.getPolicy(2,
	function(t, o) {
		t && ("0" == o.status ? (r = o.data.host, i = o.data.dir, e = {
			OSSAccessKeyId: o.data.accessId,
			policy: o.data.policy,
			expire: o.data.expire,
			signature: o.data.signature,
			success_action_status: "200"
		},
		a.init(), createUpVideo()) : alert(o.errMsg))
	});
	var a = new plupload.Uploader({
		runtimes: "html5,flash,silverlight,html4",
		browse_button: "j-uploader-selectimg",
		multi_selection: !1,
		container: document.getElementById("j-row-img"),
		flash_swf_url: "/static/lib/plupload-2.1.2/Moxie.swf",
		silverlight_xap_url: "/static/lib/plupload-2.1.2/Moxie.xap",
		url: location.protocol + "//xinhua.oss-cn-hangzhou.aliyuncs.com",
		filters: t,
		init: {
			PostInit: function() {},
			FilesAdded: function(t, a) {
				var o = a[0],
				l = o.name,
				n = l.substr(l.lastIndexOf(".")),
				d = (1e4 * (new Date).getTime() + Math.floor(1e4 * Math.random())).toString(32),
				s = d + n;
				Math.ceil(o.size / 1024 / 1024 * 100) / 100;
				$("#j-uploader-selectimg").addClass("fn-hide"),
				$("#j-row-img .j-uploader-tip").removeClass("fn-hide"),
				$("#j-row-img .j-uploader-tip p").html("开始上传<em>...</em>"),
				e.key = i + "/" + s,
				t.setOption({
					url: r,
					multipart_params: e
				}),
				t.start()
			},
			UploadProgress: function(e, t) {
				var r = t.percent;
				100 == r && (r = 99),
				$("#j-row-img .j-uploader-tip p em").html("（" + r + "%）")
			},
			FileUploaded: function(t, i, a) {
				if (200 == a.status) {
					var o = r + "/" + e.key,
					l = new Image;
					l.src = o,
					l.onload = l.onerror = function() {
						var e = l.width,
						t = l.height;
						$("#j-row-img .j-uploader-tip p em").html("（100%）");
						var r = o;
						r && r.indexOf(".gif") == -1 && (r += "?x-oss-process=image/resize,w_240");
						var i = $('<div class="xcy-cutimg fn-left fn-mr10 fn-mb10"><div class="imgbar"><span class="close"><i class="pz-icon icon-close"></i></span><img src="' + r + '"></div></div>');
						i.data({
							fileurl: o,
							width: e,
							height: t
						}),
						i.find(".close").click(function() {
							i.remove(),
							$("#j-row-img").removeClass("fn-hide")
						}),
						$("#j-row-img").before(i),
						$("#j-row-img .j-uploader-tip p").html(""),
						$("#j-row-img .j-uploader-tip").addClass("fn-hide"),
						$("#j-uploader-selectimg").removeClass("fn-hide"),
						$("#j-upcontain .xcy-cutimg").length >= 4 && $("#j-row-img").addClass("fn-hide")
					}
				} else $("#j-row-img .j-uploader-tip p").html(a.response)
			},
			Error: function(e, t) {
				t.code == -600 ? alert("上传的图片太大，请压缩到20M内") : t.code == -601 ? alert("不支持该格式！") : t.code == -602 ? alert("文件已选择！") : $("#j-row-img .j-uploader-tip p").html("文件上传失败：" + t.message)
			}
		}
	})
}
function createUpVideo() {
	function e(e) {
		var t = {
			userType: 1,
			fileType: 4
		};
		parent.getUcInfo("/stspolicy/get.json", t,
		function(t, r) {
			t && ("0" == r.status ? r.data && r.data.length > 0 && (a = JSON.parse(r.data).accessKeyId, o = JSON.parse(r.data).accessKeySecret, l = JSON.parse(r.data).securityToken, n = JSON.parse(r.data).expiration, d = JSON.parse(r.data).bucket, s = JSON.parse(r.data).dir, e()) : alert(r.errMsg))
		})
	}
	if ($("#j-reportform input[name=type]").click(function() {
		var e = $(this).val();
		reportType != e && (reportType = e, checkType())
	}), urlVideo) {
		reportType = 4;
		var t = decodeURIComponent(urlVideo);
		$("#j-row-video").data("fileurl", t),
		$("#j-row-video .videobar, #j-videocover").removeClass("fn-hide"),
		$("#j-uploader-selectvideo").addClass("fn-hide"),
		$("#j-row-video .j-uploader-tip").addClass("fn-hide")
	} else $("#j-row-video .xcy-video .imgbar").hide(),
	reportType = 1;
	checkType();
	var r, i = "oss-cn-hangzhou",
	a = (OSS.STS, ""),
	o = "",
	l = "",
	n = "",
	d = "",
	s = "",
	c = function(t) {
		e(function() {
			var e = new OSS.Wrapper({
				region: i,
				accessKeyId: a,
				accessKeySecret: o,
				stsToken: l,
				bucket: d
			});
			return t(e)
		})
	},
	p = function(e) {
		return function(t) {
			$("#j-row-video .j-uploader-tip p em").html(Math.floor(100 * e) + "%"),
			t()
		}
	},
	u = function(e) {
		var t = document.getElementById("j-locallfile").files[0];
		Math.ceil(t.size / 1024 / 1024 * 100) / 100;
		$("#j-uploader-selectvideo").addClass("fn-hide"),
		$("#j-row-video .j-uploader-tip").removeClass("fn-hide"),
		$("#j-row-video .j-uploader-tip p").html("开始上传<em>...</em>");
		var r = t.name,
		i = r.substr(r.lastIndexOf(".")),
		a = (1e4 * (new Date).getTime() + Math.floor(1e4 * Math.random())).toString(32),
		o = a + i,
		l = s + "/" + o;
		return e.multipartUpload(l, t, {
			progress: p
		}).then(function(e) {
			$("#j-row-video .xcy-video .imgbar").show(),
			$("#j-row-video .videobar, #j-videocover").removeClass("fn-hide"),
			$("#j-uploader-selectvideo").addClass("fn-hide"),
			$("#j-row-video .j-uploader-tip").addClass("fn-hide");
			var t = "http://xinhua-zbcb.oss-cn-hangzhou.aliyuncs.com/" + l;
			$("#j-row-video").data("fileurl", t)
		})
	};
	document.getElementById("j-locallfile").onchange = function() {
		var e = document.getElementById("j-locallfile").files[0],
		t = Math.ceil(e.size / 1024 * 100) / 100,
		i = ".3gp,.mp4,.m3u8,.wmv,.webm,.mov,.avi,.mpg,.mpeg,.mpeg1,.mpeg4,.mkv,.flv",
		a = e.name.substr(e.name.lastIndexOf("."));
		document.getElementById("j-locallfile").value == r ? alert("文件已选择！") : t > 2097152 ? alert("不支持上传超过2G的视频.") : i.indexOf(a.toLowerCase()) == -1 ? alert("视频格式错误，请选择:3gp,mp4,m3u8,wmv,webm,mov,avi,mpg,mpeg,mpeg1,mpeg4,mkv,flv格式的视频上传.") : c(u)
	}
}
var urlParams = lvsCmd.urlParams,
liveId = +lvsCmd.urlParams.liveId; (isNaN(liveId) || liveId < 1) && (parent.pizzaCmd.history.clear(), parent.pizzaCmd.history.push(null, "/scene/showscenelist.html"));
var urlVideo = lvsCmd.urlParams.video;
"undefined" == urlVideo && (urlVideo = null),
$("#j-back").click(function() {
	parent.pizzaCmd.history.pop()
});
var formFieldDict = {
	type: {
		name: "type",
		type: "radio",
		option: [{
			text: "图文报道",
			value: "1"
		},
		{
			text: "视频报道",
			value: "4"
		}],
		value: 1
	},
	content: {
		class: "j-content fn-h180",
		name: "content",
		type: "textarea",
		maxlength: 300
	},
	scene: {
		name: "scene",
		type: "text",
		disabled: !0,
		class: "disabled",
		value: lvsCmd.urlParams.title
	}
};
urlVideo && (formFieldDict.type.value = 4);
var reportType = 0;
createUpImg(),
$("#j-row-video .videobar .icon-video").click(function() {
	var e = $("#j-row-video").data("fileurl");
	return e && new parent.pizzaCmd.overlay($("#j-overlay-view").html(),
	function(t) {
		t.show(),
		t.obj.find(".j-overlay-close").click(function() {
			t.remove()
		}),
		parent.videoPlayer("j-viewvideo", "", e)
	}),
	$(this).blur(),
	!1
}),
$("#j-row-video .videobar .close").click(function() {
	$("#j-row-video .videobar, #j-row-video .j-uploader-tip, #j-videocover").addClass("fn-hide"),
	$("#j-uploader-selectvideo").removeClass("fn-hide"),
	$("#j-row-video").data("fileurl", null),
	$("#j-row-video .xcy-video .imgbar").html("").data("fileurl", null).hide()
});
var newTplform = new cake.tplform("j-reportform", formFieldDict);
newTplform.render($("#j-reportform .row-content"),
function(e) {
	function t() {
		var e = $(".j-content").val().length;
		$(".j-remark-length").html(e + "/300"),
		e > 300 ? $(".j-remark-length").addClass("fn-color-red") : $(".j-remark-length").removeClass("fn-color-red")
	}
	new lvsCmd.cutimg($("#j-videocover"), {
		filetype: 8,
		width: 750,
		height: 422,
		scale: .4
	},
	function(e) {
		$("#j-row-video .xcy-video .imgbar").html('<img src="' + e + '">').data("fileurl", e)
	}),
	$(".j-content").on("input", t),
	t()
},
function(e) {
	var t = {
		source: "PC",
		liveId: liveId,
		type: reportType,
		content: e.data.content
	};
	if (1 == reportType) {
		if (t.pictures = "", t.imageList = [], $("#j-upcontain .xcy-cutimg").each(function() {
			var e = $(this).data("fileurl"),
			r = $(this).data("width"),
			i = $(this).data("height");
			if (e) {
				var a = e.replace("http://", "").replace("https://", "");
				a = a.substr(a.indexOf("/")),
				"" == t.pictures ? t.pictures = a: t.pictures += "," + a,
				t.imageList.push({
					url: a,
					w: r,
					h: i
				})
			}
		}), "" == t.content && "" == t.pictures) return alert("报道内容和报道图片不能全为空"),
		!1
	} else if (4 == reportType) {
		var r = $("#j-row-video").data("fileurl");
		if (!r) return alert("请上传报道视频"),
		!1;
		var i = r.replace("http://", "").replace("https://", "");
		i = i.substr(i.indexOf("/")),
		t.video = i;
		var a = $("#j-row-video .xcy-video .imgbar").data("fileurl");
		if (a) {
			var o = a.replace("http://", "").replace("https://", "");
			o = o.substr(o.indexOf("/")),
			t.thumbnail = o
		}
	}
	var l = apiServer + "/report/add.json";
	return lvsCmd.ajax(l, t,
	function(e, t) {
		e ? "0" == t.status ? (alert("数据保存成功！"), parent.pizzaCmd.history.pop()) : alert(t.errMsg) : alert("接口请求失败，请检查网络连接！")
	}),
	!1
}),
$("#j-uploader-selectvideo").click(function() {
	return document.getElementById("j-locallfile").value = "",
	document.getElementById("j-locallfile").click()
});