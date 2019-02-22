var myChart = echarts.init(document.getElementById('main'));
var list1=[{"年龄":"25-34"},{"性别":"女"},{"家庭住址":"北京朝阳国贸"},{"单位地址":"北京海淀新中关"},{"用户活跃度":"核心用户"},{"用户影响力":"引领者"},{"年龄阶段":"90后"},{"文化属性":"传统"},{"消费能力等级":"强"},{"月收入":"5K-10K"},{"品牌偏好":"Dior Hermes"},{"支付方式":"信用卡"},{"美妆分类":"美发护发"},{"美妆常用关键词式":"美白"},{"经典品牌":"Dior Hermes"},{"一线品牌":"Ero"},{"终端类型":"Pad"},{"上网时段":"7-9/10-11/12-13"},{"日收藏次数":"5次以下"},{"日点赞次数":"10次以上"},{"订阅渠道":"微信订阅"},{"订阅产品":"两订"}];
var list2=[{"基础信息":"年龄，性别"},{"位置信息":"家庭住址，单位地址"},{"用户价值":"用户活跃度，用户影响力"},{"人群属性":"年龄阶段，文化属性"},{"经济实力":"消费能力等级，月收入"},{"购物习惯":"品牌偏好，支付方式"},{"美妆标签":"美妆分类，美妆常用关键词式"},{"品牌标签":"经典品牌，一线品牌"},{"上网习惯":"终端类型，上网时段"},{"互动行为":"日收藏次数，日点赞次数"},{"购买行为":"订阅渠道，订阅产品"}];
var list3=[{"人口属性":"基础信息，位置信息"},{"用户分类":"用户价值，人群属性"},{"商业属性":"经济实力，购物习惯"},{"内容标签":"美妆标签，品牌标签"},{"行为标签":"上网习惯，互动行为，购买行为"}];
presents=[];

jQuery(function($) {
    var html=[];
    for (var i = 0; i < list1.length; i++) {
        var obj=list1[i];
        html.push("<tr>");
        for(var o in obj) {
            html.push("<td class=\"bgleftc\">"+o+"</td>");
            html.push("<td class=\"bgrightc\">"+obj[o]+"</td>");
        }
        html.push("</tr>");
    }
    $(".table3").find("tbody").html(html.join(""));

     html=[];
     for (var i = 0; i < list2.length; i++) {
            var obj=list2[i];
            html.push("<tr>");
            for(var o in obj) {
                html.push("<td class=\"bglefta\">"+o+"</td>");
                html.push("<td class=\"bgrighta\">"+obj[o]+"</td>");
            }
            html.push("</tr>");
     }
      $(".table1").find("tbody").html(html.join(""));


       html=[];
       for (var i = 0; i < list3.length; i++) {
              var obj=list3[i];
              html.push("<tr>");
              for(var o in obj) {
                  html.push("<td class=\"bgleftb\">"+o+"</td>");
                  html.push("<td class=\"bgrightb\">"+obj[o]+"</td>");
              }
              html.push("</tr>");
       }
       $(".table2").find("tbody").html(html.join(""));
});
for (var i = 0; i < list1.length; i++) {
    var obj=list1[i];
    for(var o in obj) {
        //presents.push(o);
        presents.push(obj[o]);
    }

}
var data = [];

for (var i = 0; i < presents.length; ++i) {
	data.push({
		name : presents[i],
		value : (presents.length - i) * 20
	});
}
for (var i = 10; i < presents.length; ++i) {
	var cnt = Math.floor(Math.random() * 10);
	console.log(cnt)
	for (var j = 0; j < cnt; ++j) {
		data.push({
			name : presents[i],
			value : Math.random() * 10
		});
	}
}


var option = {
	tooltip : {},
	series : [{
		type : 'wordCloud',
		gridSize : 2,
		sizeRange : [10, 35],
		rotationRange : [0, 90],
		rotationStep : 90,
		textStyle : {
			normal : {
				color : function() {
					return 'blue';
				}
			},
			emphasis : {
				shadowBlur : 1,
				shadowColor : 'blue'
			}
		},
		width : 800,
		height : 800,
		data : data
	}]
};

myChart.setOption(option);
window.onresize = myChart.resize;
var maskImage = new Image();
maskImage.onload = function() {
	console.log(maskImage);
	myChart.setOption({
		series : [{
			maskImage : maskImage
		}]
	});
}
maskImage.src = "image/1.png";