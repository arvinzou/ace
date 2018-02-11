/*取消提示*/
var imgHost='http://zx.huacainfo.com/';
var limit = 1000;
var userProp;
var start=0;
/*直播模板*/
var liveTemplate = ' <li>' +
    '             <div class="picbar">' +
    '                <div class="pic">' +
    '                    <img src="[imageSrc]">' +
    '                </div>' +
    '                <span class="reportNum">[reportNum]条报道' +
    '                </span>' +
    '                <div class="livetitle omission">[name]</div>' +
    '            </div>' +
    '            <div class="msgbar fn-clear"> ' +
    '            	<span class="omission msgbar-common creater">' +
    '            		<i class="iconfont">&#xe61a;</i>[createUserName]' +
    '            	</span>' +
    '                <span class="msgbar-common msgbar-time">' +
    '            		<i class="iconfont">&#xe651;</i>[startTime]' +
    '            	</span>' +
    '                <a class="changeLiveStatus">[status]</a>' +
    '            </div>' +
    '        </li>';

function promptDo(text) {
    $('.prompt').text(text);
}

/**/
$(function () {
    $('#htmlLoad').on('click', '.html-blackCancel',hideTableDo);
})


/*隐藏修改页面*/
function hideTableDo() {
    $('#htmlLoad').empty();
}