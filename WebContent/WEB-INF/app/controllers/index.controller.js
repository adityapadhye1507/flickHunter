// index.controller.js
(function() {
	'use strict';
	angular.module('flickerApp').controller('IndexController', IndexController);

	IndexController.$inject = [ '$scope', 'IndexService' ];

	function IndexController($scope, IndexService) {

		// Public exposed members
		$scope.movies = [];
		$scope.phrase = '';
		$scope.details = null;

		$scope.isShowingDetails = false;
		$scope.isShowingResult = false;

		$scope.totalItems = 0;
		$scope.currentPage = 1;
		$scope.itemsPerPage = 10;
		$scope.maxSize = 5;

		$scope.onSearch = onSearch;
		$scope.displayDetails = displayDetails;
		$scope.onBackButton = onBackButton;
		$scope.paginate = paginate;
		$scope.getKeywordLabelColor = getKeywordLabelColor;
		$scope.getGenreLabelColor = getGenreLabelColor;

		$scope.pageChanged = function() {
			console.log('Page changed to: ' + $scope.currentPage);
		};

		// Functions
		function onSearch() {

			$scope.isExpandTable = true;
			var query = document.getElementById("searchQuery").value;
			console.log("Searching for query:" + query);
			$scope.movies = IndexService.getMovies(query);
			console.log($scope.movies);
			$scope.totalItems = $scope.movies.length;
			$scope.isShowingDetails = false;
			$scope.isShowingResult = true;

		}

		function displayDetails(movie) {
			$scope.details = movie;
			$scope.isShowingDetails = true;
			$scope.isShowingResult = false;
		}

		function onBackButton() {
			$scope.isShowingDetails = false;
			$scope.isShowingResult = true;
		}

		function showTablePanel() {
			console.log($scope.movies);
		}

		function paginate(value) {
			var begin, end, index;
			begin = ($scope.currentPage - 1) * 10;
			end = begin + 10;
			index = $scope.movies.indexOf(value);
			return (begin <= index && index < end);
		}

		function getGenreLabelColor() {
			var myArray = [ "label-info", "label-success", "label-primary",
					"label-warning", "label-default" ];

			return "label-info";
		}
		function getKeywordLabelColor() {

			return "label-primary";
		}
		$scope.mySplit = function(string) {
			var array = string.split(',');
			var name = array[1] + ", " + array[0];
			return name;
		}

	}

})();
