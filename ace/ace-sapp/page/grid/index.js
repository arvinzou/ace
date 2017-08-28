var util = require("../../util/util.js");
var cfg = require("../../config.js");
Page({
  data: {
    list: []
  },
  onLoad: function () {
    var that = this;
    util.request(cfg.selectOrganizationCategoryList, {  },
      function (data) {
        that.setData({
          list: data
        });
      }
    );
  }
})