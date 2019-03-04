var loading = {};
var editor;
var abc = ['A', 'B', 'C', 'D', 'E', 'F', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']
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
                type: '1',
                content: '',
                options: [{
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
                options: [{
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
                options: [{
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
                var answer = $('.test input[name='+type+']:checked').val();
                if(answer){
                    data.options[answer].answer = 1;
                }
                that.postData(data);
            },
            submit2:function (type) {
                const that = this;
                const data = that[type];
                var checkeds = $('.test input[name='+type+']:checked');
                checkeds.each(function(){
                    data.options[$(this).val()].answer=1;
                });
                that.postData(data);
            },
            addOption: function (type) {
                const options = this[type].options
                if (options.length > 25) {
                    alert("最多只能添加26个选项");
                    return;
                }
                this[type].options.push({content: '', answer: '0'});
            },
            removeOption: function (type, index) {
                this[type].options.splice(index, 1);
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
            postData:function (data) {
                const  that=this;
                if (that.verify(data)) {
                    const url = contextPath + "/topic/insertTopic";
                    const datas = {
                        jsons: JSON.stringify(data)
                    }
                    $.post(url, datas, function () {

                    })
                } else {
                    alert("还有内容没有填写");
                }
            }
        },
        mounted: {
            initTestareaHeight: function () {
                $('textarea').each(function () {
                    this.setAttribute('style', 'height:' + (this.scrollHeight) + 'px;overflow-y:hidden;');
                })
            }
        }
    })
}





