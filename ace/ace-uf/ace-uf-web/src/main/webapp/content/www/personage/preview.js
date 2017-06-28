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
		$.each($scope.o, function(key, value) {
			if (key == 'sex') {
				$scope.o[key] = rsd(value, '01');
			}
			if (key == 'category') {
				$scope.o[key] = rsd(value, '98');
			}
			if (key == 'nation') {
				$scope.o[key] = rsd(value, '09');
			}
			if (key == 'degreee') {
				$scope.o[key] = rsd(value, '10');
			}
			if (key == 'academicTitle') {
				$scope.o[key] = rsd(value, '99');
			}
			if (key == 'rank') {
				$scope.o[key] = rsd(value, '100');
			}

			if (key == 'party') {
				$scope.o[key] = rsd(value, '101');
			}
			if (key == 'academicTitle') {
				$scope.o[key] = rsd(value, '99');
			}
			if (key == 'birthday') {
				$scope.o[key] = value.substring(0, 10);
			}
			if (key == 'currentPostDate' || key == 'currentRankDate') {
				$scope.o[key] = new Date(value).pattern("yyyy-MM-dd");
			}
		});
	}, function errorCallback(response) {
		$.hideLoading();
	});
	$scope.$on('load', function(e) {
		console.log("load");
		photoview();
	});
});
app.directive('onFinishRenderFilters', function($timeout) {
	return {
		restrict : 'A',
		link : function(scope, element, attr) {
			$timeout(function() {
				scope.$emit('load');
			});
		}
	};
});
function photoview() {
	$("img").each(
			function() {
				$(this).removeAttr("width");
				$(this).removeAttr("height");
				$(this).css("max-width", "150px");
				$(this).css("max-height", "150px");
				$(this).wrap(
						"<a href='" + $(this).attr("src")
								+ "'  data-size='600x600'/>");
				$(this).parent().wrap("<figure/>");
				DrawImage(this)
			});
	initPhotoSwipeFromDOM('figure');

}
function DrawImage(ImgD) {
	var image = new Image();
	image.src = ImgD.src;
	image.onload = function() {

		$(ImgD).parent().attr("data-size",
				(ImgD.width * 5) + "x" + (ImgD.height * 5));
	}
}
