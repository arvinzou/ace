var init=false;
jQuery(function($) {
	$.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
		_title : function(title) {
			var $title = this.options.title || '&nbsp;'
			if (("title_html" in this.options)
					&& this.options.title_html == true)
				title.html($title);
			else
				title.text($title);
		}
	}));
    initData();
});
function initData(){
    loadSwiperData1("#swiper1");
    loadSwiperData2("#swiper2");
}
function loadSwiperData1(el){
	$.ajax({
		type : "get",
		url : contextPath+'/person/findPersonList.do',
		data : {
			limit : 10
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
            var html=[];
            $(rst.rows).each(function(i,o){
                if(o.photo.indexOf("?")>0){
                                o.photo=o.photo.split("?")[0];
                            }
                html.push('<div class="swiper-slide" data="'+fastdfs_server+o.photo+'" style="background-image:url('+fastdfs_server+o.photo+')"></div>');
            });
            $(el).html(html.join("\n\r"));
            initSwiper1();
		},
		error : function() {
			alert("HTTP错误！");
		}
	});
}
function loadSwiperData2(el){
	$.ajax({
		type : "get",
		url : contextPath+'/person/findPersonList.do',
		data : {
			limit : 10
		},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
            var html=[];
            $(rst.rows).each(function(i,o){
                if(o.photo.indexOf("?")>0){
                    o.photo=o.photo.split("?")[0];
                }
                html.push('<div class="swiper-slide" data="'+fastdfs_server+o.photo+'" style="background-image:url('+fastdfs_server+o.photo+')"></div>');
            });
            $(el).html(html.join("\n\r"));
            initSwiper2();
		},
		error : function() {
			alert("HTTP错误！");
		}
	});
}
function initSwiper1(){
    var swiper1 = new Swiper('.swiper-container1', {
      effect: 'coverflow',
      grabCursor: true,
      centeredSlides: true,
      slidesPerView: 'auto',
      coverflowEffect: {
        rotate: 50,
        stretch: 0,
        depth: 100,
        modifier: 1,
        slideShadows : true,
      },
      pagination: {
        el: '.swiper-pagination',
      },
      on: {
        transitionEnd: function () {
          var image_url=$(".swiper-container1").find(".swiper-slide-active").attr("data");
          $("#box1").html("<img src='"+image_url+"' class='photo'/>");
        }
      }
    });
}


function initSwiper2(){
    var swiper2 = new Swiper('.swiper-container2', {
      effect: 'coverflow',
      grabCursor: true,
      centeredSlides: true,
      slidesPerView: 'auto',
      coverflowEffect: {
        rotate: 50,
        stretch: 0,
        depth: 100,
        modifier: 1,
        slideShadows : true,
      },
      pagination: {
        el: '.swiper-pagination',
      },
      on: {
        transitionEnd: function () {
             var image_url=$(".swiper-container2").find(".swiper-slide-active").attr("data");
          $("#box2").html("<img src='"+image_url+"' class='photo'/>");
        }
      }
    });
}