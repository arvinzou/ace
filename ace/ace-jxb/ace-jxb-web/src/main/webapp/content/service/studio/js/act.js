var loading = {};

function loadlocal() {
    var urls = [];

    urls.push({path: portalPath, url: '', type: 'css'});
    urls.push({path: portalPath, url: '', type: 'css'});

    urls.push({path: portalPath, url: '/content/common/jcrop/jquery.Jcrop.min.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/assets/global/plugins/jquery.blockui.min.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/assets/global/scripts/app.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/assets/global/plugins/jquery.blockui.min.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/assets/layouts/layout3/scripts/layout.min.js', type: 'js'});
    urls.push({path: contextPath, url: '/content/service/studio/js/studio.js', type: 'js'});
    for (var i = 0; i < urls.length; i++) {
        loader(urls[i]);
    }
}

function App() {
    console.log("=============================App Start==============================");
    loadlocal();
    loader({
        path: contextPath,
        url: '/content/common/js/validateform.js',
        type: 'js',
        callback: function () {
            initvalidateform()
        }
    });
    loader({
        path: portalPath,
        url: '/content/common/plupload/plupload.full.min.js',
        type: 'js',
        callback: function () {
            loader({
                path: contextPath,
                url: '/content/service/studio/js/upload.js',
                type: 'js',
                callback: function () {
                    initUpload();
                }
            });
            // loader({
            //     path: contextPath,
            //     url: '/content/common/js/upload.js',
            //     type: 'js',
            //     callback: function () {
            //         initUpload();
            //     }
            // });
        }
    });
}


function initvalidateform() {
    $('.form-body').on('blur', 'input', validateInput);
    $('.form-body').on('focus', 'input', 'textarea', hideHint);
}


function hideHint() {
    var $that = $(this);
    $that.next().text("");
}

function validateInput() {
    var $that = $(this);
    if (!$that.val().trim()) {
        return;
    }
    $that.next().text("");
    var idName = $that.context.id;
    if (!idName) {
        return;
    }
    var result = validateform(idName);
    console.log(result);
    if (result.status == 0) {
        $that.next().text("");
    } else {
        $that.next().text(result.message);
    }
}

