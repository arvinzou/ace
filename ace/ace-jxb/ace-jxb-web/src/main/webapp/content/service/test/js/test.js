window.onload = function () {
    initDoc();
    $('#add-gauge').click(addGange);
    $('.gauge-list').on('click', '.delectBtn', delectTr)
    $('.textareaHeight').on('input', function () {
        this.style.height = 'auto';
        this.style.height = this.scrollHeight + "px";
    });
    $('.subtPage').click(subtPage);
    $('.addPage').click(addPage);
};


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

function addPage() {
    var index = $('.modal-body .step-row[flag="true"]').index();
    $('.modal-body .step-row').eq(index).css('display', 'none');
    $('.modal-body .step-row').eq(index).attr('flag', false);
    $('.modal-body .step-row').eq(index + 1).css('display', 'block');
    $('.modal-body .step-row').eq(index + 1).attr('flag', true);
    $('.subtPage').show();
    if (index == 2) {
        $('.addPage').hide();
        $('.submit_btn').show();
    }
}

function delectTr() {
    $(this).parent().remove();
}

function addGange() {
    $('.gauge-list').append($('<tr>\n' +
        '                                        <th width="30%"> 大于 <input type="text">分 </th>\n' +
        '                                        <th width="50%"><textarea rows="1" class="textareaHeight"></textarea>  </th>\n' +
        '                                        <th class="delectBtn" width="20%"> 删除 </th>\n' +
        '                                    </tr>'))
}


function createTest() {
    $('#createTest').modal('show');
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