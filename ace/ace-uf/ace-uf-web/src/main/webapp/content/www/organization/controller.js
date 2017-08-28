var start = 0;
var limit = 10;
$(function() {
	FastClick.attach(document.body);
});
$(document.body).pullToRefresh().on("pull-to-refresh", function() {
	setTimeout(function() {
		start = start + 10;
		var $scope = scope('myCtrl');
		$scope.query();
	}, 100);
});
function t_query() {
	start = 0;
	var $scope = scope('myCtrl');
	$scope.query();
	return false;
}
var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
    $scope.clear=function(){
       $scope.name="";
       $scope.query();
    }
	$scope.query = function() {
		$.showLoading();
		$http({
			method : 'GET',
			url : contextPath + "/www/query.do",
			params : {
				category : category,
				name : $scope.name,
				start : start,
				limit : limit,
				reportId : 'organizationPageList'
			}
		}).then(function successCallback(res) {
			$.hideLoading();
			$scope.list = res.data.value;
			$("body").removeClass("refreshing");

		}, function errorCallback(response) {
			$.hideLoading();
		});
	};
	$scope.query();
});

function scope(ctrl) {
	var appElement = document.querySelector('[ng-controller=' + ctrl + ']');
	var $scope = angular.element(appElement).scope();
	return $scope;
}

