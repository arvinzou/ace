var testName = '';

window.onload = function () {
    initDoc();
    initpage();
    $('#add-gauge').click(addGange);
    $('.gauge-list').on('click', '.delectBtn', delectTr)
    $('.textareaHeight').on('input', function () {
        this.style.height = 'auto';
        this.style.height = this.scrollHeight + "px";
    });
    $('.subtPage').click(subtPage);
    $('.addPage').click(addPage);
};


function evaluatCaseList(id) {
    if (id) {
        window.location.href = '../testCase/index.jsp?id=' + id;
    }
}


function deleteTestTpl() {
    var input = $('#evaluatTplList tr input:checked');
    var size = input.length;
    if (size == 0) {
        return;
    } else if (size > 1) {
        alert("一次只能删除一个");
        return;
    }
    var id = input.eq(0).data('id');
    activeDelect(id);
}

function activeDelect(id) {
    var url = portalPath + '/evaluatTpl/deleteEvaluatTpl.do';
    var data = {
        jsons: JSON.stringify({
            id: id
        })
    }
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            getEvaluatTplList($('#pagination1 .active').text());
            return
        }
        alert(result.errorMessage)

    })
}


function clearForm() {
    $('.modal-body .step-row').eq(2).css('display', 'none');
    $('.modal-body .step-row').eq(2).attr('flag', false);
    $('.modal-body .step-row').eq(1).css('display', 'none');
    $('.modal-body .step-row').eq(1).attr('flag', false);
    $('.modal-body .step-row').eq(0).css('display', 'block');
    $('.modal-body .step-row').eq(0).attr('flag', true);
    $('.subtPage').hide();
    $('.addPage').show();
    $('.submit_btn').hide();
    var input = $('.step-row input')
    for (var i = 0; i < input.length; i++) {
        input.eq(i).val('');
    }
    editor.setValue('');
    editor_two.setValue('');
    $('.form_cover').prop('src', 'addImg1.png');
    var tr = $('.gauge-list tr');
    for (var i = 0; i < tr.length; i++) {
        if (i < 2) {
            tr.eq(i).find('.form_score').val('');
            tr.eq(i).find('.form_content').val('');
            continue;
        }
        tr.eq(i).remove();
    }
}


function insertEvaluatTpl() {
    var url = portalPath + '/evaluatTpl/insertEvaluatTplVo.do';
    var data = submitForm();
    if (!data) {
        return;
    }
    $.post(url, data, function (result) {
        console.log(result);
        if (result.status == 0) {
            alert('添加成功');
            $('#createTest').modal('hide');
            getEvaluatTplList($('#pagination1 .active').text());
            return;
        }
        alert('添加失败', '确认是否重复添加？');
    })
}


function updataEvaluatTpl() {
    var url = portalPath + '/evaluatTpl/updateEvaluatTplVo.do';
    var data = submitForm();
    if (!data) {
        return;
    }
    $.post(url, data, function (result) {
        console.log(result);
        if (result.status == 0) {
            alert('修改成功');
            $('#createTest').modal('hide');
            getEvaluatTplList($('#pagination1 .active').text());
            return;
        }
        alert('修改失败', '确认是否重复添加？');
    })
}


/*初始化分页器*/
function initpage() {
    testSelectOptions();
    $.jqPaginator('#pagination1', {
        totalCounts: 20,
        pageSize: 20,
        visiblePages: 10,
        currentPage: 1,
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            getEvaluatTplList(num, type);
        }
    });
}


function getEvaluatTplList(num, type) {
    var url = portalPath + '/evaluatTpl/findEvaluatTplListVo.do';
    var data = {
        page: num,
        limit: 20,
        name: testName
    };
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            if (type == "init") {
                $('#pagination1').jqPaginator('option', {
                    totalCounts: result.total,
                });
            }
            var navitem = document.getElementById('temp_evaluatTplList').innerHTML;
            var html = juicer(navitem, {
                data: result.rows,
            });
            $("#evaluatTplList").html(html);

        }
    })
}

var ids = '';


function searchByName() {
    testName = $('input[name="testName"]').val();
    initpage();

}


function modify(id) {
    clearForm();
    var url = portalPath + '/evaluatTpl/selectEvaluatTplByPrimaryKey.do';
    var data = {
        id: id
    };
    ids = id;
    $.getJSON(url, data, function (result) {
        if (result.status == 0) {
            $('.submit_btn').off("click");
            $('.submit_btn').click(updataEvaluatTpl);
            fillForm(result.value);
            $('#createTest').modal('show');
        }
    })
}


function fillForm(data) {
    for (key in data) {
        $('.form_' + key).val(data[key]);
    }
    editor.setValue(data['introduce']);
    editor_two.setValue(data['notice']);
    $('.form_cover').prop('src', data.cover);
    var list = data.gaugelist;
    var size = list.length;
    for (var i = 0; i < size - 2; i++) {
        addGange();
    }

    var tr = $('.gauge-list tr');
    for (var i = 0; i < size; i++) {
        tr.eq(i).find('.form_score').val(list['' + i].scoreEnd);
        tr.eq(i).find('.form_content').val(list['' + i].content);
    }

}


function submitForm() {
    var evaluatTpl = {
        name: '',
        category: '',
        // cover:'',
        introduce: '',
        notice: '',
        originalCost: '',
        discountCost: ''
    };
    for (key in evaluatTpl) {
        var val = $('.form_' + key).val();
        if (!val) {
            alert('还有信息没有填写。');
            return null;
        }
        evaluatTpl[key] = val;
    }
    var imgUrl = $('.form_cover').prop('src');
    if (imgUrl.indexOf('addImg1') != -1) {
        alert('请上传封面。');
        return null;
    }
    evaluatTpl.cover = imgUrl;
    var temp = new Array();
    var tr = $('.gauge-list tr');
    var topScore = 10000;
    for (var i = 0; i < tr.length; i++) {
        var evaluatGauge = {};
        var scoreEnd = tr.eq(i).find('.form_score').val();
        var content = tr.eq(i).find('.form_content').val();
        if (!(scoreEnd && content)) {
            alert('量表每项必填。');
            return null;
        }
        if (topScore <= scoreEnd) {
            alert('分数从高到底。');
            return null;
        }
        topScore = scoreEnd;
        evaluatGauge.scoreEnd = scoreEnd;
        evaluatGauge.content = content;
        temp[i] = evaluatGauge;
    }
    evaluatTpl.id = ids;
    return {
        jsons: JSON.stringify({
            evaluatTpl: evaluatTpl,
            evaluatGauge: temp
        })
    }
}

/*添加新的测试模板*/
function createTestType() {
    var name = $('#notNull_type').val();
    if (!name.trim()) {
        return;
    }
    var url = portalPath + '/evaluatType/insertEvaluatType.do';
    var data = {
        jsons: JSON.stringify({
            name: name
        })
    }
    $.post(url, data, function (result) {
        if (result.status == 0) {
            testSelectOptions();
            $('#notNull_type').val('');
            return;
        }
        alert('添加失败', '确认是否重复添加？');
    })
}


/*上一步*/
function subtPage() {
    var index = $('.modal-body .step-row[flag="true"]').index();
    $('.modal-body .step-row').eq(index).css('display', 'none');
    $('.modal-body .step-row').eq(index).attr('flag', false);
    $('.modal-body .step-row').eq(index - 1).css('display', 'block');
    $('.modal-body .step-row').eq(index - 1).attr('flag', true);
    $('.addPage').show();
    $('.submit_btn').hide();
    if (index == 1) {
        $('.subtPage').hide();
    }
}


/*下一步*/
function addPage() {
    var index = $('.modal-body .step-row[flag="true"]').index();
    $('.modal-body .step-row').eq(index).css('display', 'none');
    $('.modal-body .step-row').eq(index).attr('flag', false);
    $('.modal-body .step-row').eq(index + 1).css('display', 'block');
    $('.modal-body .step-row').eq(index + 1).attr('flag', true);
    $('.subtPage').show();
    if (index == 1) {
        $('.addPage').hide();
        $('.submit_btn').show();
    }
}

/*删除一行量表*/
function delectTr() {
    $(this).parent().remove();
}


/*添加一行量表*/
function addGange() {
    $('.gauge-list').append($('<tr>\n' +
        '                                        <th width="30%"> 大于 <input class="form_score" type="text">分 </th>\n' +
        '                                        <th width="50%"><textarea rows="1" class="textareaHeight form_content"></textarea>  </th>\n' +
        '                                        <th class="delectBtn primary-link" width="20%"> 删除 </th>\n' +
        '                                    </tr>'))
}


/*创建测试*/
function createTest() {
    clearForm();
    $('.submit_btn').off("click");
    $('.submit_btn').click(insertEvaluatTpl);
    $('#createTest').modal('show');
}

function testSelectOptions() {
    var url = portalPath + '/evaluatType/selectType.do';
    $.getJSON(url, function (result) {
        var navitem = document.getElementById('temp_TestTypeList').innerHTML;
        var html = juicer(navitem, {
            data: result.rows,
        });
        $("#TestTypeList").html(html);
    });
}


var editor, editor_two;

function initDoc() {
    editor = new Simditor({
        textarea: $('#notNull_introduction'),
        toolbar: ['title', 'bold', 'italic', 'underline', 'strikethrough', 'fontScale', 'color', '|', 'ol', 'ul', 'blockquote', 'code', 'table', '|', 'link', 'image', 'hr', '|', 'indent', 'outdent'],
        upload: {
            url: portalPath + '/files/uploadImage.do', //文件上传的接口地址
            params: null, //键值对,指定文件上传接口的额外参数,上传的时候随文件一起提交
            fileKey: 'file', //服务器端获取文件数据的参数名
            connectionCount: 3,
            leaveConfirm: '正在上传文件'
        }
    });
    editor_two = new Simditor({
        textarea: $('#notNull_Notice'),
        toolbar: ['title', 'bold', 'italic', 'underline', 'strikethrough', 'fontScale', 'color', '|', 'ol', 'ul', 'blockquote', 'code', 'table', '|', 'link', 'image', 'hr', '|', 'indent', 'outdent'],
        upload: {
            url: portalPath + '/files/uploadImage.do', //文件上传的接口地址
            params: null, //键值对,指定文件上传接口的额外参数,上传的时候随文件一起提交
            fileKey: 'file', //服务器端获取文件数据的参数名
            connectionCount: 3,
            leaveConfirm: '正在上传文件'
        }
    });
}