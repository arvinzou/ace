/**
 * @license Copyright (c) 2003-2017, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function(config) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	// 在 CKEditor 中集成 CKFinder注意 ckfinder 的路径选择要正确。
	config.language = 'zh-cn'; // 配置语言
	config.toolbar = "Full";
	config.skin='bootstrapck';
	config.font_names = "宋体/宋体;黑体/黑体;仿宋/仿宋_GB2312;楷体/楷体_GB2312;隶书/隶书;幼圆/幼圆;微软雅黑/微软雅黑;"
			+ config.font_names;
	// config.filebrowserBrowseUrl = '../ckfinder/ckfinder.html';
	// config.filebrowserImageBrowseUrl =
	// '../ckfinder/ckfinder.html?type=Images';
	// config.filebrowserFlashBrowseUrl =
	// '../ckfinder/ckfinder.html?type=Flash';
	config.filebrowserUploadUrl = portalPath+'/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files';
	config.filebrowserImageUploadUrl = portalPath+'/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images';
	config.filebrowserFlashUploadUrl = portalPath+'/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash';
	config.filebrowserWindowWidth = '800';
	config.filebrowserWindowHeight = '500';

};
