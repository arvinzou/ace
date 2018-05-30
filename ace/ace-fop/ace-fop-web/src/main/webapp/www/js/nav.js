window.onload = function(){
    $('.nav-list>li').hover(function(){
        var $ul=$(this).find('ul');
        var oW=$(this).width();//li
        var otrigW=$(this).find('.trig').width();
        var oNavListL=$('.nav-list').offset().left;
        var oTL=$(this).offset().left-oNavListL;//距离最左边的距离
        var oTR=$('.nav-list').width()-oTL-oW;//距离最右边的距离
        console.log(oNavListL+":"+oTL);

        if($ul.find('li').length>0){
            $('.second-bg').show();
            $(this).find('.trig').show();
            $ul.show();
            var sum=0;
            var oLeft=0;
            for(var i=0;i<$ul.find('li').length;i++){
                sum+=$ul.find('li').eq(i).width()+4;
            }
            $ul.width(sum);
            oLeft=(sum-oW)/2;
            if(oLeft>oTL){//到达左侧边界
                oLeft=oTL;
                $ul.css('left',-oLeft+'px');
                return ;
            }
            if(oLeft>oTR){
                $ul.css('right',-oTR+'px');
                return ;
            }
            $ul.css('left',-oLeft+'px');

        }
    },function(){
        $('.second-bg').hide();
        $(this).find('ul').hide();
        $(this).find('.trig').hide();
    });
}