var loading = {};

function sortableCallblack() {
    console.log("sortableCallblack");
    getCategoryItemsList(pageId);
}

function App() {
    console.log("=============================App Start==============================");
    loader({
        path: contextPath,
        url: '/dynamic/service/pageEdit/49c7dce1984b4a4bb251d84642a0d4a8/css/style.css',
        type: 'css'
    });
    loader({
        path: contextPath,
        url: '/content/common/assets/global/plugins/sortable/Sortable.min.js',
        type: 'js',
        callback: sortableCallblack
    });
    loader({
        path: contextPath,
        url: '/content/common/js/jquery.form.js',
        type: 'js',
        callback: function () {
            initEvents();
        }
    });
    loader({
        path: contextPath,
        url: '/dynamic/service/pageEdit/49c7dce1984b4a4bb251d84642a0d4a8/js/upload.js',
        type: 'js',
        callback: function () {

        }
    });
    loader({
        path: contextPath,
        url: '/content/common/jcrop/jquery.Jcrop.css',
        type: 'css',
        callback: function () {

        }
    });
    loader({
        path: contextPath,
        url: '/content/common/jcrop/jquery.Jcrop.min.js',
        type: 'js',
        callback: function () {

        }
    });
    loader({
        path: contextPath,
        url: '/content/common/plupload/plupload.full.min.js',
        type: 'js',
        callback: function () {
            initUpload();
        }
    });

    getPageList(pageId);
    getCover(pageId);
}

function getCategoryItemsList(pageId) {
    $.ajax({
        url: contextPath + '/articleCategory/getList.do',
        type: 'POST',
        timeout: 30000,
        dataType: 'json',
        data: {
            pageId: pageId
        },
        beforeSend: function () {
            try {
                loading = startLoading();
            } catch (e) {};
            if (loading) {
                loading.settext("正在加载，请稍后......");
            }
        },
        success: function (data) {
            console.log(data);
            var categoryItems = document.getElementById('tpl-categoryItems').innerHTML;
            var html = juicer(categoryItems, {
                data: data
            });
            $("#categoryItems").html(html);
            var el = document.getElementById('bar');
            initTabs();
            var sortable = Sortable.create(el, {
                group: "words",
                animation: 150,
                onAdd: function (evt) {
                    console.log('onAdd.bar:', evt.item);
                },
                onUpdate: function (evt) {
                    console.log('onUpdate.bar:', evt.item);
                },
                onRemove: function (evt) {
                    console.log('onRemove.bar:', evt.item);
                },
                onStart: function (evt) {
                    console.log('onStart.foo:', evt.item);
                },
                onEnd: function (evt) {
                    console.log('onEnd.foo:', evt.item);
                    console.log(sortable.toArray());
                    var ids = sortable.toArray();
                    sort(ids.join(","));
                }
            });
            if (loading) {
                loading.remove();
            }
        }
    });

}

function sort(ids) {
    $.ajax({
        url: contextPath + '/articleCategory/updateSort.do',
        type: 'POST',
        timeout: 30000,
        dataType: 'json',
        data: {
            ids: ids
        },
        beforeSend: function () {
            try {
                loading = startLoading();
            } catch (e) {};
            if (loading) {
                loading.settext("请求中，请稍后......");
            }
        },
        success: function (data) {
            console.log(data);
            if (loading) {
                loading.remove();
            }
            if (data.status == 0) {
                getCategoryItemsList(pageId);
                ifr.window.location.reload()
            } else {
                alert(data.errorMessage);
            }
        }
    });
}

function initArticleClickEvent() {
    $(".list_item").on("click", function () {
        var id = $(this).data("id");
        $(this).addClass("active-bg").siblings().removeClass("active-bg");
    });
}

function initTabs() {
    var th_width = $(".news-module li").eq(0).width();
    var th_left = $(".news-module li").eq(0).offset().left;
    var slider_width = $(".news-slider").width();
    console.log(slider_width);
    console.log(th_left);
    var slider_left = th_left + (th_width / 2) - slider_width / 2;
    console.log(slider_left);
    $(".news-slider").css("left", slider_left);
    $(".news-module li").on("click", function () {
        var n = $(this).index();
        var id = $(this).data("id");
        console.log("click->" + id);
        var th_width = $(this).width();
        var th_left = $(this).offset().left;
        var slider_width = $(".news-slider").width();
        var slider_left = th_left + (th_width / 2) - slider_width / 2;
        $(".news-slider").css("left", slider_left);
        $(this).addClass("actives").siblings().removeClass("actives");
        $(".navitem").each(function (i, o) {

            if ($(o).data('id') == id) {
                $(o).css("display", 'block');
                console.log(o);
            } else {
                $(o).css("display", 'none');
            }
        });

    });
}

function initEvents() {
    $('#model1 .btn-primary').on('click', function () {
        var name = $("#model1").find('.modal-body input[name=name]').val();
        var id = $("#model1").find('.modal-body input[name=id]').val();
        var action = $("#model1").find('.modal-body input[name=action]').val();
        var url = contextPath + '/articleCategory/insertArticleCategory.do';
        if (action == "edit") {
            url = contextPath + '/articleCategory/updateArticleCategory.do';
        }
        $.ajax({
            url: url,
            type: 'POST',
            timeout: 30000,
            dataType: 'json',
            data: {
                jsons: JSON.stringify({
                    id: id,
                    name: name,
                    tplPageId: pageId
                })
            },
            beforeSend: function () {
                try {
                    loading = startLoading();
                } catch (e) {};
                if (loading) {
                    loading.settext("请求中，请稍后......");
                }
            },
            success: function (data) {
                console.log(data);
                if (loading) {
                    loading.remove();
                }
                if (data.status == 0) {
                    $('#model1').modal('hide');
                    getCategoryItemsList(pageId);
                    ifr.window.location.reload()
                } else {
                    alert(data.errorMessage);
                }
            }
        });
    });
    $('#model1').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget)
        var action = button.data('action')
        var modal = $(this);
        var name = $("#bar").find(".actives").data("name");
        var id = $("#bar").find(".actives").data("id");
        modal.find('.modal-body input[name=action]').val(action);
        if (action == 'edit') {
            modal.find('.modal-body input[name=name]').val(name);
            modal.find('.modal-body input[name=id]').val(id);
        }
    })
    $('#model2 .btn-primary').on('click', function () {
        $('#model2 form').submit();
    });
    $('#model2').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget)
        var action = button.data('action')
        var modal = $(this);
        var name = $("#bar").find(".actives").data("name");
        var category = $("#bar").find(".actives").data("id");
        var o = $("#" + category).find(".active-bg");
        var id = $(o).data("id");
        $(".modal-title").html(name);
        console.log(id);
        console.log(action);
        modal.find('.modal-body input[name=action]').val(action);
        if (action == 'edit') {
            console.log($(o).find("img").attr("src"));
            var remark = $(o).find(".desc").html();
            modal.find('.modal-body input[name=id]').val(id);
            modal.find('.modal-body input[name=hrefUrl]').val($(o).data("hrefurl"));
            modal.find('.modal-body input[name=title]').val($(o).find(".title").html());
            modal.find('.modal-body textarea[name=remark]').val($(o).find(".desc").html());
            modal.find('.modal-body input[name=tags]').val($(o).data("tags"));
            $("#cover-img").attr("src", $(o).find("img").attr("src"));
            $("#cover-img").css("display", "block");
            $("#cover-img").css("max-width", 300);
            $("#cover-img").css("max-height", 300);
        }else{
						modal.find('.modal-body input[name=hrefUrl]').val("");
            modal.find('.modal-body input[name=title]').val("");
            modal.find('.modal-body textarea[name=remark]').val("");
            modal.find('.modal-body input[name=tags]').val("");
            $("#cover-img").css("display", "none");
				}

    });
    $('#model2 form').ajaxForm({
        beforeSubmit: function (formData, jqForm, options) {
            var data = {};
            $.each(formData, function (n, obj) {
                data[obj.name] = obj.value;
            });
            $.extend(data, {
                tplPageId: pageId,
                articleCategory: $("#bar").find(".actives").data("id"),
                cover: $("#cover-img0").attr("src"),
                mediType: '1',
                likeNum: 0,
                hitNum: 0
            });
            $.extend(data, {
                time: new Date()
            });
            console.log(data);
            var id = $("#model2").find('.modal-body input[name=id]').val();
            var action = $("#model2").find('.modal-body input[name=action]').val();
            var url = contextPath + '/article/insertArticle.do';
            if (action == "edit") {
                url = contextPath + '/article/updateArticle.do';
            }
            $.ajax({
                url: url,
                type: 'POST',
                timeout: 30000,
                dataType: 'json',
                data: {
                    jsons: JSON.stringify(data)
                },
                beforeSend: function () {
                    try {
                        loading = startLoading();
                    } catch (e) {};
                    if (loading) {
                        loading.settext("请求中，请稍后......");
                    }
                },
                success: function (data) {
                    console.log(data);
                    if (loading) {
                        loading.remove();
                    }
                    if (data.status == 0) {
                        $('#model2').modal('hide');
                        getPageList(pageId);
                        ifr.window.location.reload()
                    } else {
                        alert(data.errorMessage);
                    }
                }
            });
            return false;
        }
    });

    $("#js_preview_title").on("click", function () {
        $("#modify_title_box").css("display", 'block');
    });

    $('#img-uploader').on('show.bs.modal', function (event) {
        console.log("=============================================");
        var button = $(event.relatedTarget);
        xsize = button.data('xsize');
        ysize = button.data('ysize');
        cover= button.data('cover');
        console.log(xsize+"/"+ysize);
        console.log(cover);
        preImg('img/left_pic_two.jpg');

    });


}

function cancel_modify_title() {
    $("#modify_title_box").css("display", 'none');
}

function do_modify_title() {
    $.ajax({
        url: contextPath + '/tplPage/updateNameById.do',
        type: 'POST',
        timeout: 30000,
        dataType: 'json',
        data: {
            id: pageId,
            name: $("#js_hname_input").val()
        },
        beforeSend: function () {
            try {
                loading = startLoading();
            } catch (e) {};
            if (loading) {
                loading.settext("请求中，请稍后......");
            }
        },
        success: function (data) {
            console.log(data);
            if (loading) {
                loading.remove();
            }
            if (data.status == 0) {
                $("#modify_title_box").css("display", 'none');
                getPageList(pageId);
                ifr.window.location.reload()
            } else {
                alert(data.errorMessage);
            }
        }
    });
}

function getPageList(pageId) {
    $.ajax({
        url: contextPath + '/www/getTplPageById.do',
        type: 'POST',
        timeout: 30000,
        data: {
            id: pageId
        },
        dataType: 'json',
        beforeSend: function () {
            try {
                loading = startLoading();
            } catch (e) {};
            if (loading) {
                loading.settext("正在加载，请稍后......");
            }
        },
        success: function (data) {
            console.log(data);
            var navitem = document.getElementById('tpl-navitem').innerHTML;
            var html = juicer(navitem, {
                data: data
            });
            $("#navitem").html(html);
            $("#js_preview_title").html(data.data.page.name);
            $("#js_hname_input").val(data.data.page.name)
            initArticleClickEvent();
            if (loading) {
                loading.remove();
            }
        }
    });
}

function delActile(){
    var url = contextPath + '/article/deleteArticleById.do';
    var category = $("#bar").find(".actives").data("id");
    var o = $("#" + category).find(".active-bg");
    var id = $(o).data("id");
    if(id == "" || id == undefined){
        alert("请选择要删除的记录！");
        return;
    }
    $.ajax({
        url: url,
        type: 'POST',
        timeout: 30000,
        dataType: 'json',
        data: {
                id: id
        },
        beforeSend: function () {
            try {
                loading = startLoading();
            } catch (e) {};
            if (loading) {
                loading.settext("请求中，请稍后......");
            }
        },
        success: function (data) {
            console.log(data);
            if (loading) {
                loading.remove();
            }
            if (data.status == 0) {
                $('#model1').modal('hide');
                getPageList(pageId);
                ifr.window.location.reload()
            } else {
                alert(data.errorMessage);
            }
        }
    });
}

function delCatadory(){
    var url = contextPath + '/articleCategory/deleteArticleCategoryByArticleCategoryId.do';
    var category = $("#bar").find(".actives").data("id");
    $.ajax({
        url: url,
        type: 'POST',
        timeout: 30000,
        dataType: 'json',
        data: {
            jsons: JSON.stringify({
                id: category
            })
        },
        beforeSend: function () {
            try {
                loading = startLoading();
            } catch (e) {};
            if (loading) {
                loading.settext("请求中，请稍后......");
            }
        },
        success: function (data) {
            console.log(data);
            if (loading) {
                loading.remove();
            }
            if (data.status == 0) {
                $('#model1').modal('hide');
                getCategoryItemsList(pageId);
                ifr.window.location.reload()
            } else {
                alert(data.errorMessage);
            }
        }
    });
}
function delCover(){
 var url = contextPath + '/tplPage/updateCoverById.do';
    $.ajax({
        url: url,
        type: 'POST',
        timeout: 30000,
        dataType: 'json',
        data: {
            id:pageId,
            cover:null
        },
        beforeSend: function () {
            try {
                loading = startLoading();
            } catch (e) {};
            if (loading) {
                loading.settext("请求中，请稍后......");
            }
        },
        success: function (data) {
            console.log(data);
            if (loading) {
                loading.remove();
            }
            if (data.status == 0) {
                getCover(pageId);
                ifr.window.location.reload()
            } else {
                alert(data.errorMessage);
            }
        }
    });
}
function updateCover(){
 var url = contextPath + '/tplPage/updateCoverById.do';
 var cover=$("#cover-img1").attr("src");
    $.ajax({
        url: url,
        type: 'POST',
        timeout: 30000,
        dataType: 'json',
        data: {
            id:pageId,
            cover:cover
        },
        beforeSend: function () {
            try {
                loading = startLoading();
            } catch (e) {};
            if (loading) {
                loading.settext("请求中，请稍后......");
            }
        },
        success: function (data) {
            console.log(data);
            if (loading) {
                loading.remove();
            }
            if (data.status == 0) {
                getCover(pageId);
                ifr.window.location.reload()
            } else {
                alert(data.errorMessage);
            }
        }
    });
}
function getCover(pageId){
 var url = contextPath + '/www/getById.do';
    $.ajax({
        url: url,
        type: 'POST',
        timeout: 30000,
        dataType: 'json',
        data: {
            id:pageId
        },
        beforeSend: function () {
        },
        success: function (data) {
            console.log(data);
            if (data.status == 0) {
              if(data.data.cover){
                            var img="#cover-img1";
                            $(img).attr("src",data.data.cover);
                            $(img).css("display","block");
                            $(img).css("max-width",300);
                            $(img).css("max-height",169);
              }

            } else {
                alert(data.errorMessage);
            }
        }
    });
}