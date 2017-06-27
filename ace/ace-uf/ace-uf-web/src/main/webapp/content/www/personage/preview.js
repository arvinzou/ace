var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
	$.showLoading();
	$http({
		method : 'GET',
		url : contextPath + "/www/query.do",
		params : {
			id : id,
			start : 0,
			limit : 0,
			reportId : 'personAgeOne'
		}
	}).then(function successCallback(res) {
		$.hideLoading();
		$scope.o = res.data.value[0];
	}, function errorCallback(response) {
		$.hideLoading();
	});
});