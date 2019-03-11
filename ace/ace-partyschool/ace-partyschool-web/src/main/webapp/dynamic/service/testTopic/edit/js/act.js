var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {
        $(".breadcrumb").append("<li><span>创建试题管理</span></li>");
        initVue();
    });
}

function initVue() {
    new Vue({
        el: "#app",
        data: {
            type1: {
                type: '',
                content: '',
                topicOptList: [{
                    answer: '0',
                    content: '',
                }, {
                    answer: '0',
                    content: '',
                }]
            },
            type2: {
                type: '',
                content: '',
                topicOptList: [{
                    answer: '0',
                    content: '',
                }, {
                    answer: '0',
                    content: '',
                }]
            },
            type3: {
                type: '',
                content: '',
                topicOptList: [{
                    answer: '0',
                    content: '正确',
                }, {
                    answer: '0',
                    content: '错误',
                }]
            },
            type4: {
                type: '',
                content: '',
            },
            type5: {
                type: '',
                content: '',
            }
        },
        created:function () {
                const url = contextPath + "/topic/selectTopicByPrimaryKey";
                const data = {
                    id:urlParams.did
                }
                var that=this;
            $.ajaxSettings.async = false;
                $.getJSON(url, data, function (rst) {
                    if(rst.status==0){
                        var item=rst.value;
                        that['type'+item.type]=item;
                    }
                    else {
                        alert("没有获取到数据,刷新重试");
                    }
                })
            $.ajaxSettings.async = true;
        },
        methods: {
            submit1: function (type) {
                const that = this;
                const data = that[type];
                var answer = $('.test input[name=' + type + ']:checked').val();
                that.initAnswer(data.topicOptList);
                if (answer) {
                    data.topicOptList[answer].answer = 1;
                }
                that.postData(data);
            },
            submit2: function (type) {
                const that = this;
                const data = that[type];
                var checkeds = $('.test input[name=' + type + ']:checked');
                checkeds.each(function () {
                    data.topicOptList[$(this).val()].answer = 1;
                });
                that.postData(data);
            },
            initAnswer:function (data) {
                for(item in data){
                    data[item].answer=0;
                }
            },
            addOption: function (type) {
                const topicOptList = this[type].topicOptList
                if (topicOptList.length > 25) {
                    alert("最多只能添加26个选项");
                    return;
                }
                this[type].topicOptList.push({content: '', answer: '0'});
            },
            removeOption: function (type, index) {
                this[type].topicOptList.splice(index, 1);
            },
            autoHeight: function (e) {
                var thisDom = e.currentTarget;
                thisDom.style.height = (thisDom.scrollHeight) + 'px';
            },
            verify: function (data) {
                for (const item in data) {
                    if (typeof(data[item]) == 'object') {
                        var op = data[item];
                        for (const i in op) {
                            if (!op[i].content) {
                                return false;
                            }
                        }
                    } else {
                        if (!data[item]) {
                            return false;
                        }
                    }

                }
                return true;
            },
            postData: function (data) {
                const that = this;
                if (that.verify(data)) {
                    const url = contextPath + "/topic/updateTopic";
                    const datas = {
                        jsons: JSON.stringify(data)
                    }
                    $.post(url, datas, function (rst) {
                        if(rst.status==1){
                            window.history.back();
                        }
                    })
                } else {
                    alert("还有内容没有填写");
                }
            },
        },
        mounted: function(){
            $('textarea').each(function () {
                this.setAttribute('style', 'height:' + (this.scrollHeight) + 'px;overflow-y:hidden;');
            })
        }
    })
}





