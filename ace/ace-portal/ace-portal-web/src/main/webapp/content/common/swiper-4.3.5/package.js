var version = '4.2.0';

Package.describe({
  name: 'nolimits4web:swiper',
  summary: 'iDangero.us Swiper - mobile touch slider with hardware accelerated transitions and native behavior',
  version: version,
  git: 'https://github.com/nolimits4web/Swiper'
});

Package.onUse(function (api) {
  api.versionsFrom('1.1.0.2');

  api.addFiles([
      'dist/css/swiper.min.css',
      'dist/js/swiper.js'
    ], ['client']
  );
});

Package.onTest(function (api) {
});
