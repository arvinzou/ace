var data = [{
		message: "Je t`aime,Je t`adore",
		headimg: "http://www.w3school.com.cn/i/eg_tulip.jpg"
	},
	{
		message: "Ich liebe Dich",
		headimg: "http://www.w3school.com.cn/i/eg_tulip.jpg"
	},
	{
		message: "Minarakastan sinua",
		headimg: "http://www.w3school.com.cn/i/eg_tulip.jpg"
	},
	{
		message: " IK hou van jou",
		headimg: "http://www.w3school.com.cn/i/eg_tulip.jpg"
	},
	{
		message: "Miluji te",
		headimg: "http://www.w3school.com.cn/i/eg_tulip.jpg"
	},
	{
		message: "e`g elska tig",
		headimg: "http://www.w3school.com.cn/i/eg_tulip.jpg"
	},
	{
		message: " Tave Myliu",
		headimg: "http://www.w3school.com.cn/i/eg_tulip.jpg"
	},
	{
		message: "Mahal Kit",
		headimg: "http://www.w3school.com.cn/i/eg_tulip.jpg"
	},
	{
		message: "Ya vas lyublyu,Ya tibia Lyublyu",
		headimg: "http://www.w3school.com.cn/i/eg_tulip.jpg"
	},
	{
		message: "爱している",
		headimg: "http://www.w3school.com.cn/i/eg_tulip.jpg"
	}
]

var item = {
	img: '', //图片 
	info: '', //文字 
	href: 'javascript:;', //链接 
	close: true, //显示关闭按钮 
	speed: parseInt(Math.random() * 6 + 5, 10), //延迟,单位秒,默认6 
	color: '#ffffff', //颜色,默认白色 
	old_ie_color: '#ffffff', //ie低版兼容色,不能与网页背景相同,默认黑色 
}
$(function() {
	openBarrager();
})

function openBarrager() {
	var i = 0;
	setInterval(function() {
		if(data.length == i) {
			i = 0;
		}
		var s = data[i];
		item.info = s.message;
		item.img = s.headimg;
		$('#message').barrager(item);
		i++;
	}, 2000)
}