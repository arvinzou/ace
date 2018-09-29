// page/index/index.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        textLists: [{
                id: 'ele-1',
                content: '1211122222222222222222222222222222222222222222222222222222222222222222',
                type: 1
            },
            {
                id: 'ele-2',
                content: 'http://t2.hddhhn.com/uploads/tu/201707/115/52.jpg',
                type: 2
            },
            {
                id: 'ele-3',
                content: '我是文字我是文字我是文字我是文字我是文字我是文字我是文字我是文字我是文字我是文字',
                type: 1
            },
            {
                id: 'ele-4',
                content: 'http://t2.hddhhn.com/uploads/tu/201707/115/52.jpg',
                type: 2
            },
        ],
        toolBar: {
            top: '0px',
            show: false,
            type:'1'
        },
        clickObject: '',
    },
    // 显示工具菜单
    showTool: function(e) {
        var that = this;
        console.log(e);
        var id = e.currentTarget.id;
        var type = e.currentTarget.dataset.type;
        var height = e.currentTarget.offsetTop + 5;
        that.setData({
                clickObject: id
            },
            (e) => {
                var query = wx.createSelectorQuery();
                query.select('#' + id).boundingClientRect();
                query.exec(function(res) {
                    that.setData({
                        toolBar: {
                            top: height + res[0].height + 'px',
                            show: true,
                            type:type
                        }
                    });
                })
            }
        );
    },
    // 开始修改
    startEdit: function(e) {
        var id = this.data.clickObject;
        var todo = this.getTodo(id);
        this.cencelClickStyle(false);
        todo.editing = true;
        this.updateData(true);
        this.updateStorage();
    },
    // 修改结束
    endEdit: function(e) {
        var todo = this.findEditing();
        var content = e.detail.value
        if (content.trim()){
            todo.editing = false;
            todo.content =content;
        }else{
            this.data.textLists.splice(this.getElementIndex(todo.id), 1);
        }
        this.updateData(true);
        this.updateStorage();
    },
    // 获取单个数据信息
    getTodo: function(id) {
        return this.data.textLists.filter(function(t) {
            return id == t.id;
        })[0];
    },
    // 查找正在修改的元素
    findEditing: function() {
        return this.data.textLists.filter(function(t) {
            return t.editing == true;
        })[0];
    },

    // 更新渲染
    updateData: function(resetTodos) {
        var data = {};
        if (resetTodos) {
            data.textLists = this.data.textLists;
        }
        this.setData(data);
    },
    // 保存在内存
    updateStorage: function() {
        var storage = [];
        this.data.textLists.forEach(function(t) {
            storage.push({
                id: t.id,
                text: t.text,
                complete: t.complete
            })
        });
        wx.setStorageSync('textLists', storage);
    },
    // 添加元素
    addElement: function(content, type, editing) {
        var that = this;
        var id = 'e' + new Date().getTime();
        console.log(that);
        console.log(that.data.clickObject);

        that.data.textLists.splice(that.getElementIndex(that.data.clickObject), 0, {
            id: id,
            content: content,
            type: type,
            editing: editing,
        });
        that.setData({
            clickObject:id,
        })
        that.updateData(true);
    },
    // 添加文本元素
    addTextElement: function() {
        var that = this;
        that.cencelClickStyle(false);
        that.addElement('', 1, true);
    },
    //获取点击元素所在序列
    getElementIndex: function(id) {
        console.log("id" + id);
        var len = this.data.textLists.length;
        var data = this.data.textLists;
        for (var i = 0; i < len; i++) {
            if (data[i].id == id) {
                return i;
            }
        }
    },
    // 删除元素
    delElement: function() {
        this.data.textLists.splice(this.getElementIndex(this.data.clickObject), 1);
        this.cencelClickStyle(true);
        this.updateData(true);
    },
    //添加图爿
    addImage() {
        var that = this;
        that.cencelClickStyle(false);
        wx.showActionSheet({
            itemList: ['打开照相', '选取现有的'],
            itemColor: '#007aff',
            success(res) {
                console.log(res.tapIndex);
                if (res.tapIndex === 0) {
                    wx.chooseImage({
                        sourceType: ['camera'],
                        success(res) {
                            that.uploadFileFun(res.tempFilePaths[0]);
                        }
                    })
                } else if (res.tapIndex === 1) {
                    wx.chooseImage({
                        count: 3, // 设置最多三张
                        sizeType: ['original', 'compressed'],
                        sourceType: ['album', 'camera'],
                        success(res) {
                            var tempFilePaths = res.tempFilePaths;
                            for (var i = 0; i < tempFilePaths.length; i++) {
                                that.uploadFileFun(tempFilePaths[i]);
                            }
                        }
                    })
                }
            }
        })
    },
    // 清除点击样式
    cencelClickStyle: function(isAll) {
        if (isAll) {
            this.setData({
                clickObject: '',
                toolBar: {
                    show: false,
                }
            });
        }
        else{
            this.setData({
                toolBar: {
                    show: false,
                }
            });
        }

    },
    // 上传文件方法
    uploadFileFun: function(tempFilePaths) {
        var that = this;
        wx.uploadFile({
            url: 'http://zx.huacainfo.com/portal/www/uploadFile.do',
            filePath: tempFilePaths,
            name: 'file',

            formData: {
                collectionName: 'ceshi',
                id: '111'
            },
            success: function(res) {
                console.log(1);
                var data = JSON.parse(res.data);
                var url = 'http://zx.huacainfo.com/' + data.value[0].fileUrl;
                that.addElement(url, 2, false)
            },
            fail: function(res) {
                return null
            },
        })
    }
})