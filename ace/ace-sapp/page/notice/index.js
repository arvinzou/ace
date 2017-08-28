var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({
  /**
   * 页面的初始数据
   */
  reg: function () {
    wx.navigateTo({
      url: '../reg/index?redirectTo=/page/notice/index'
    })
  },
  onPullDownRefresh:function () {
    console.log('--------下拉刷新-------')
    var that = this;
    that.setData({
      start: 0
    });
    that.initData(that.data.inputVal);
  },
  initData:function(q){
    var that = this;
    util.request(cfg.findTaskCmccList, {taskName:q, start: that.data.start, limit: 20 },
      function (data) {
        wx.stopPullDownRefresh();
        that.setData({
          listHis: data.rows
        });
      }
    );
  },
  showInput: function () {
    this.setData({
      inputShowed: true
    });
  },
  hideInput: function () {
    this.setData({
      inputVal: "",
      inputShowed: false
    });
  },
  clearInput: function () {
    var that = this;
    that.setData({
      inputVal: ""
    });
    if (that.data.tab3){
      this.initTree3('');
    }
    if (that.data.tabActiveRight) {
      this.initData('');
    }
    
  },
  inputTyping: function (e) {
    var that = this;
    this.setData({
      inputVal: e.detail.value
    });
    if (that.data.inputVal.length >= 1 || that.data.inputVal == '') {
      console.log(that.data.inputVal);
    
      if (that.data.tab3) {
        this.initTree3(that.data.inputVal);
      }
      if (that.data.tabActiveRight) {
        this.initData(that.data.inputVal);
      }
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that=this;
    console.log("getStorage -> userInfo start")
    var o = wx.getStorageSync('userInfo');
    that.setData({
      WXSESSIONID: wx.getStorageSync('WX-SESSION-ID'),
      checkImageUrl: cfg.checkImageUrl
    });
    console.log(o)
    if (!o) {
      that.setData({
        userLogin: false
      });
      that.reg();
    } else {
      
      if(o.role&&(o.role=='admin')){
        that.setData({
          userLogin: true
        });
        that.initData(that.data.inputVal);
       
      }else{
        that.reg();
      }
    }
  },
  initTree1:function(){
    var that = this;
    util.request(cfg.getPersonageTreeList, {
      q: ''
    },
      function (data) {
        that.setData({
          list: data
        });
      }
    );
  },
  initTree2: function () {
    var that = this;
    util.request(cfg.selectFreeGroupTree, {
      q: ''
    },
      function (data) {
        that.setData({
          list: data
        });
      }
    );
  },
  initTree3: function (q) {
    var that = this;
    util.request(cfg.selectPersonageTree, {
      q: q
    },
      function (data) {
        that.setData({
          list: data
        });
      }
    );
  },
  onShareAppMessage: function (res) {
    if (res.from === 'button') {
      // 来自页面内转发按钮
      console.log(res.target)
    }
    return {
      title: '我发现了掌上统战小程序，一起看看吧',
      path: '/page/dept/index',
      success: function (res) {
        // 转发成功
      },
      fail: function (res) {
        // 转发失败
      }
    }
  },
  data: {
    loading: false,
    disabled: false,
    tabActiveLeft: true,
    tabActiveRight: false,
    tabActiveThir:false,

    tab1: true,
    tab2: false,
    tab3: false,

    id: util.uuid(),
    formData: {},
    userList:[],
    listHis:[],
    max: 0,
    start:0,
    inputVal:'',
    list2:[]
  },
  //事件
  valueChange: function (e) {
    console.log(e);
    if (e.detail && e.detail.value.length > 0) {
      this.setData({
        max: e.detail.value.length
      });
    }
  },
  onReady: function (res) {
    wx.setNavigationBarColor({
      frontColor: cfg.frontColor,
      backgroundColor: cfg.backgroundColor,
      animation: {
        duration: 400,
        timingFunc: 'easeIn'
      }
    });
  },
  tableft: function (e) {
    this.setData({
      tabActiveLeft: true,
      tabActiveRight: false,
      tabActiveThir: false,
      tab1: false,
      tab2: false,
      tab3: false
    })
  },
  tabright: function (e) {
    this.setData({
      tabActiveLeft: false,
      tabActiveRight: true,
      tabActiveThir: false,
      tab1: false,
      tab2: false,
      tab3: false
    })
  },
  tab1: function (e) {
    var that = this;
    that.setData({
      tab1: true,
      tab2: false,
      tab3: false
    });
    that.initTree1();
  },
  tab2: function (e) {
    var that = this;
    that.setData({
      tab1: false,
      tab2: true,
      tab3: false
    });
    that.initTree2();
  },
  tab3: function (e) {
    var that = this;
    that.setData({
      tab1: false,
      tab2: false,
      tab3: true
    });
    that.initTree3('');
  },
  onSubmit:function(e){
    var that=this;
    that.setData({
      loading: true,
      disabled: true
    })
    var list = that.data.userList;
    var tel=[];
    for(var i in list){
      var o=list[i];
      tel.push(o.tel+","+o.name);
    }
    var data=e.detail.value;
    data.tel = tel.join(";");
    console.log(data);
    util.request(cfg.insertTaskCmcc, data,
      function (data) {
        that.setData({
          loading: false,
          disabled: false
        })
        wx.showModal({
          title: '提示',
          content: data.errorMessage
        })
      }
    );
  },
  onSave: function (e) {
    console.log(e);
    var that = this;
    that.setData({
      loading: true,
      disabled: true
    })
  },
  tabadd:function(e){
    console.log("tabadd");
    console.log(e);
    var that = this;
    that.setData({
      tabActiveThir: true,
      tabActiveLeft: false,
      tabActiveRight: false,
      tab1: true,
      tab2: false,
      tab3: false
    });
    that.initTree1();
  }
  ,
  tabdel: function (e) {
    console.log(e);
    var that = this;
    that.setData({
      userList: [],
    })
  },
  tabdelLast: function (e) {
    console.log(e);
    var that = this;
    that.data.userList.pop()
    that.setData({
      userList: that.data.userList
    })
  },
  searchNodeById: function (data, id) {
    var that = this;
    for (var i in data) {
      //console.log('i', i);
      //console.log('datai', data[i].children);
      if (data[i].id == id) {
        console.log(data[i]);
        if (data[i].state == 'closed') {
          data[i].state = 'open'
        } else {
          data[i].state = 'closed'
        }
        break;
      } else {
        if (data[i].children) {
          that.searchNodeById(data[i].children, id);
        }
      }
    }
  },
  kindToggle: function (e) {
    var that = this;
    var id = e.currentTarget.id;
    var list = that.data.list;
    that.searchNodeById(list, id);
    that.setData({
      list: list
    });
  },
  checkChild: function (data) {
    
    var that = this;
    
    if (data.checked){
      if (data instanceof Object) {
        data.checked = false;
      }
    }else{
      if (data instanceof Object){
        data.checked = true;
      }
    }
    if (data.children) {
      var list = data.children;
      for (var i in list) {
        that.checkChild(list[i]);
      }
      that.checkChild(data.children);
    } 
  },
  findNodeById: function (data, id) {
    var that = this;
    for (var i in data) {
      if (data[i].id == id) {
        that.checkChild(data[i]);
        var list = that.data.list;
        that.setData({
          list: list
        });
        break;
      } else {
        if (data[i].children) {
          that.findNodeById(data[i].children, id);
        }
      }
    }
  },
  checkboxChange: function (e) {
    console.log('checkbox发生change事件，携带value值为：', e.detail.value)
  },
  bindchange:function(e){
    var that = this;
    var list = that.data.list;
    that.findNodeById(list, e.target.id);
  },
  onSelect:function(e){
    console.log(e);
    var that = this;
    var list = that.data.list;
    that.getNodeData(list);
  },
  getNodeData: function (data) {
    var that = this;
    var list = data;
    for (var i = 0; i < list.length; i++) {
      var o = list[i];
      if (o.checked) {
        
        if(o.href&&o.iconCls){
          console.log(o.text + "," + o.iconCls);
          if (o.iconCls.length==11){
            that.data.userList.push({ name: o.text, tel: o.iconCls });
          }
          
        }
      }
      if (o.children){
        that.getNodeData(o.children);
      }
    }
    that.setData({
      userList: that.data.userList,
      tabActiveThir: false,
      tabActiveLeft: true,
      tabActiveRight: false
    })
  }
})