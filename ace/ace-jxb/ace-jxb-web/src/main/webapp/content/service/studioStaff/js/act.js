var loading = {};

function loadlocal() {
    var urls = [];
    // urls.push({
    //     path: portalPath,
    //     url: '/content/common/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css',
    //     type: 'css'
    // });
    urls.push({path: portalPath, url: '/content/common/assets/global/plugins/jquery.blockui.min.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/assets/global/scripts/app.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/assets/global/plugins/jquery.blockui.min.js', type: 'js'});
    urls.push({path: portalPath, url: '/content/common/assets/layouts/layout3/scripts/layout.min.js', type: 'js'});
    urls.push({path: contextPath, url: '/content/common/js/jqPaginator.js', type: 'js'});
    // urls.push({path: "", url: 'https://cdn.bootcss.com/limonte-sweetalert2/7.21.0/sweetalert2.min.js', type: 'js'});
    urls.push({path: contextPath, url: '/content/service/studioStaff/js/studioStaff.js', type: 'js'});
    for (var i = 0; i < urls.length; i++) {
        loader(urls[i]);
    }
}

function App() {
    console.log("=============================App Start==============================");
    loadlocal();
}

// function logStr( text) {
//     console.log(text);
// }