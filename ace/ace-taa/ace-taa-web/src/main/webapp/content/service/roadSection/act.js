function initPreview(id) {

            $.ajax({
                url: contextPath + '/roadGps/getList',
                type: "post",
                async: false,
                data: {
                    id: id
                },
                success: function(result) {

                    if(result.status == 0) {
                        if(result.value){
                            var o=result.value[0];
                            var map = new qq.maps.Map(document.getElementById("container"), {
                                center: new qq.maps.LatLng(o.latitude,o.longitude),
                                zoom: 14
                            });
                            var path = [];
                            //new qq.maps.LatLng(39.930, 116.425)
                            for(var i in result.value){
                                var e=result.value[i];
                                path.push(new qq.maps.LatLng(e.latitude,e.longitude));
                            }
                            var polygon = new qq.maps.Polyline({
                                map: map,
                                path: path,
                                editable: true
                            });
                        }

                    } else {
                        alert(result.errorMessage);
                    }
                },
                error: function() {

                    alert("对不起出错了！");
                }
            });
}

jQuery(function($) {
    initPreview(id);
});