var loading = {};

function loadlocal() {
    var urls = [];
    urls.push({path: contextPath, url: '/content/common/js/jqPaginator.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/simditor/scripts/module.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/simditor/scripts/hotkeys.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/simditor/scripts/uploader.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/simditor/scripts/simditor.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/plupload/plupload.full.min.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/jcrop/jquery.Jcrop.min.js', type: 'js'});
    urls.push({path: contextPath, url: '/dynamic/service/course/js/course.js', type: 'js'});
    for (var i = 0; i < urls.length; i++) {
        loader(urls[i]);
    }
}

function App() {
    console.log("=============================App Start==============================");
    loadlocal();
    loader({
        path: portalPath,
        url: '/content/common/plupload/plupload.full.min.js',
        type: 'js',
        callback: function () {
            loader({
                path: contextPath,
                url: '/content/common/js/upload.js',
                type: 'js',
                callback: function () {
                    initUpload();
                }
            });
        }
    });


}


