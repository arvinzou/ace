function App() {
    console.log("=============================App Start==============================");
    loader({
        path: contextPath,
        url: '/www/adviceonline/css/history.css',
        type: 'css'
    });
    loader({
        path: contextPath,
        url: '/www/common/css/nav.css',
        type: 'css'
    });
    loader({
        path: contextPath,
        url: '/www/common/js/nav.js',
        type: 'js'
    });
}