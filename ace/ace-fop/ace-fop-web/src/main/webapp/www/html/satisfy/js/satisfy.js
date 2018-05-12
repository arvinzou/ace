 var drawCircle = function(canvasId, data_arr, color_arr, text_arr){
            var drawing = document.getElementById(canvasId);
            if(drawing.getContext) {
                var context = drawing.getContext('2d');
                var radius = drawing.height/2 -20,//半径
                    ox = radius +20, oy = radius +20;//圆心
                var width = 30, height = 10, //图例宽高
                    posX = ox * 2 +20, posY = 30;//图例位置
                var textX = posX + width + 5, textY = posY + 10;//文本位置
                var startAngle = 0, endAngle = 0;//起始、结束弧度
               /* context.strokeStyle = 'Purple';
                context.lineWidth = 3*/;
//              context.strokeRect(0, 0, drawing.width, drawing.height);
                for(var i=0, len=data_arr.length; i<len; i++) {
                    //绘制饼图
                    endAngle += data_arr[i] * 2*Math.PI;
                    context.fillStyle = color_arr[i];
                    context.beginPath();
                    context.moveTo(ox, oy);
                    context.arc(ox, oy, radius, startAngle, endAngle, false);
                    context.closePath();
                    context.fill();
                    startAngle = endAngle;
                    //绘制图例
                    context.fillRect(posX, posY + 20 * i, width, height);
                    context.moveTo(posX, posY + 20 * i);
                    context.font = 'bold 12px Arial';
                    var percent = text_arr[i] + ' : ' + data_arr[i]*100 + '%';
                    context.fillText(percent, textX, textY + 20 * i);
                }

            }
    };
   var init_appeal = function(){
            var data_arr = [0.42, 0.26, 0.32],
                color_arr = ['#4167E2', '#1D60BC', '#5C7DEE'],
                text_arr =['一般', '很满意', '不满意'];
            drawCircle('appeal', data_arr, color_arr, text_arr);
        };
    var init_react = function(){
            var data_arr = [0.42, 0.26, 0.32],
                color_arr = ['#FF9F00', '#FA8C35', '#FFBC4C'],
                text_arr =['很满意', '一般', '不满意'];
            drawCircle('react', data_arr, color_arr, text_arr);
        };
$(function(){
	init_appeal();
	init_react();
});
