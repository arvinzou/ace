window.onload = function () {
    initpage();
    $('#add-caseSub').click(addCaseSub);
    $('.case-list').on('click', '.delectBtn', delectTr)
    $('.textareaHeight').on('input', function () {
        this.style.height = 'auto';
        this.style.height = this.scrollHeight + "px";
    });
};


function deleteTestCase() {
    var input = $('#evaluatCaseList tr input:checked');
    var size = input.length;
    if (size == 0) {
        return;
    } else if (size > 1) {
        var se = confirm("是否删除" + size + '个题目');
        if (se != true) {
            return;
        }
    }
    for (var i = 0; i < size; i++) {
        var id = input.eq(i).data('id');
        activeDelect(id);
    }
}

function activeDelect(id) {
    var url = portalPath + '/evaluatCase/deleteEvaluatCaseByEvaluatCaseId.do';
    var data = {
        jsons: JSON.stringify({
            id: id
        })
    }
    $.getJSON(url, data, function (result) {
        getEvaluatCaseList($('#pagination1 .active').text());
    })
}


function active(id) {
    if (!id) {
        return;
    }
    ecId = id;
    var url = portalPath + '/evaluatCase/selectEvaluatCaseByPrimaryKey.do';
    var data = {
        id: id
    }
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            $('.submit_btn').off("click");
            $('.submit_btn').click(updataEvaluatCase);
            clearForm();
            fillForm(result.value);
            $('#createTest').modal('show');
        }
    })
}

function updataEvaluatCase() {
    var url = portalPath + '/evaluatCase/updateEvaluatCaseVo.do';
    var data = getFormData();
    if (!data) {
        return;
    }
    $.post(url, data, function (result) {
        if (result.status == 0) {
            alert('修改成功');
            getEvaluatCaseList($('#pagination1 .active').text());
            return;
        }
        alert('修改失败', '确认是否重复添加？');
    })
}


function fillForm(data) {
    $('.form_title').val(data.title);
    var list = data.caseSubData;
    var size = list.length;
    for (var i = 0; i < size - 2; i++) {
        addCaseSub();
    }

    var tr = $('.case-list tr');
    for (var i = 0; i < size; i++) {
        tr.eq(i).find('.form_optionScore').val(list['' + i].optionScore);
        tr.eq(i).find('.form_name').val(list['' + i].name);
    }
}


/*添加一行量表*/
function addCaseSub() {
    $('.case-list').append($('<tr>\n' +
        '                                        <th width="50%"><textarea rows="1" class="textareaHeight form_name"></textarea>  </th>\n' +
        '                                        <th width="30%"><input class="form_optionScore" type="text">分 </th>\n' +
        '                                        <th class="delectBtn primary-link" width="20%"> 删除 </th>\n' +
        '                                    </tr>'))
}


var eid, ecId;

/*初始化分页器*/
function initpage() {
    var url = window.location.href;
    var obj = parseQueryString(url);
    eid = obj.id;
    if (!eid) {
        window.location.href = '../test/index.jsp';
    }
    $.jqPaginator('#pagination1', {
        totalCounts: 1,
        pageSize: 20,
        visiblePages: 10,
        currentPage: 1,
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            getEvaluatCaseList(num, type);
        }
    });
}

function clearForm() {
    $('.form_title').val('');
    var tr = $('.case-list tr');
    for (var i = 0; i < tr.length; i++) {
        if (i < 2) {
            tr.eq(i).find('.form_optionScore').val('');
            tr.eq(i).find('.form_name').val('');
            continue;
        }
        tr.eq(i).remove();
    }
}


function parseQueryString(url) {
    var obj = {};
    var start = url.indexOf("?") + 1;
    var str = url.substr(start);
    var arr = str.split("&");
    for (var i = 0; i < arr.length; i++) {
        var arr2 = arr[i].split("=");
        obj[arr2[0]] = arr2[1];
    }
    return obj;
}


function getEvaluatCaseList(num, type) {
    var url = portalPath + '/evaluatCase/findEvaluatCaseListSecond.do';
    var data = {
        page: num,
        limit: 20,
        evaluatTplId: eid,
    };
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            if (type == "init") {
                $('#pagination1').jqPaginator('option', {
                    totalCounts: result.total,
                });
            }
            var navitem = document.getElementById('temp_evaluatCaseList').innerHTML;
            var html = juicer(navitem, {
                data: result.rows,
            });
            $("#evaluatCaseList").html(html);
        }
    })
}



/*删除一行量表*/
function delectTr() {
    $(this).parent().remove();
}

function insertEvaluatCase() {
    var url = portalPath + '/evaluatCase/insertEvaluatCaseVo.do';
    var data = getFormData();
    if (!data) {
        return;
    }
    $.post(url, data, function (result) {
        if (result.status == 0) {
            clearForm();
            alert('添加成功');
            getEvaluatCaseList($('#pagination1 .active').text());
            return;
        }
        alert('添加失败', '确认是否重复添加？');
    })
}


function getFormData() {
    var evaluatCase = {};
    var title = $('.form_title').val();
    if (!title) {
        alert('每项必填。');
        return null;
    }
    evaluatCase.title = title;
    evaluatCase.id = ecId;
    evaluatCase.evaluatTplId = eid;
    evaluatCase.type = 1;
    evaluatCase.score = 0;
    var temp = new Array();
    var tr = $('.case-list tr');
    for (var i = 0; i < tr.length; i++) {
        var evaluatCaseSub = {};
        var optionScore = tr.eq(i).find('.form_optionScore').val();
        var name = tr.eq(i).find('.form_name').val();
        if (!(optionScore && name)) {
            alert('每项必填。');
            return null;
        }
        evaluatCaseSub.optionScore = optionScore;
        evaluatCaseSub.name = name;
        temp[i] = evaluatCaseSub;
    }
    return {
        jsons: JSON.stringify({
            evaluatCase: evaluatCase,
            evaluatCaseSub: temp
        })
    }

}


/*创建试题*/
function createTestCase() {
    clearForm();
    $('.submit_btn').off("click");
    $('.submit_btn').click(insertEvaluatCase);
    $('#createTest').modal('show');
}