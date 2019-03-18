var loading = {};
var editor;
window.onload = function () {
    jQuery(function ($) {
        $(".breadcrumb").append("<li><span>创建试题管理</span></li>");
        initVue();
    });
}

var type1 = {
    type: '1',
    content: '',
    topicOptList: [{
        answer: '0',
        content: '',
    }, {
        answer: '0',
        content: '',
    }]
};
var type2 = {
    type: '2',
    content: '',
    topicOptList: [{
        answer: '0',
        content: '',
    }, {
        answer: '0',
        content: '',
    }]
};
var type3 = {
    type: '3',
    content: '',
    topicOptList: [{
        answer: '0',
        content: '正确',
    }, {
        answer: '0',
        content: '错误',
    }]
};
var type4 = {
    type: '4',
    content: '',
};
var type5 = {
    type: '5',
    content: '',
}

function initVue() {
    new Vue({
        el: "#app",
        data: {
            type1: {
                type: '1',
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
                type: '2',
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
                type: '3',
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
                type: '4',
                content: '',
            },
            type5: {
                type: '5',
                content: '',
            }
        },

        methods: {
            submit1: function (type) {
                const that = this;
                const data = that[type];
                var answer = $('.test input[name=' + type + ']:checked').val();
                if (answer) {
                    data.topicOptList[answer].answer = 1;
                }
                that.postData(data,type);
            },
            submit2: function (type) {
                const that = this;
                const data = that[type];
                var checkeds = $('.test input[name=' + type + ']:checked');
                checkeds.each(function () {
                    data.topicOptList[$(this).val()].answer = 1;
                });
                that.postData(data,type);
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
            postData: function (data,type) {
                const that = this;
                if (that.verify(data)) {
                    const url = contextPath + "/topic/insertTopic";
                    const datas = {
                        jsons: JSON.stringify(data)
                    }
                    $.post(url, datas, function (rst) {
                        if(rst.status==0){
                            that[type]=window[type];
                            alert("添加成功");
                        }
                    })
                } else {
                    alert("还有内容没有填写");
                }
            }
        }
    })
}





