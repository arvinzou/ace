var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
	$.showLoading();
	$http({
		method : 'GET',
		url : contextPath + "/www/selectOrganization.do",
		params : {
			id : id
		}
	}).then(function successCallback(res) {
		$.hideLoading();
		$scope.o = res.data;
		$scope.fastdfs_server = fastdfs_server;
		$.each($scope.o, function(key, value) {
		});

	}, function errorCallback(response) {
		$.hideLoading();
	});
    $scope.$on('ngRepeatFinished', function (ngRepeatFinishedEvent) {
            $(".swiper-container").swiper();
    });
});

app.directive('onFinishRenderFilters', function ($timeout) {
    return {
        restrict: 'A',
        link: function(scope, element, attr) {
            if (scope.$last === true) {
                $timeout(function() {
                    scope.$emit('ngRepeatFinished');
                });
            }
        }
    };
});