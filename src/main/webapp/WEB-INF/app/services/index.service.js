(function() {

    angular
        .module('flickerApp')
        .factory('IndexService', IndexService);

    IndexService.$inject = ['$http', '$q'];

    function IndexService($http, $q) {

        return {
            getMovies: _getMovies
        };

        // functions
        function _getMovies($query) {
        	
            var xmlhttp = new XMLHttpRequest();
            var url = "http://localhost:8080/FlickHunter/controller/search/"+$query;
            var movies;
            xmlhttp.open("GET", url, false);
            xmlhttp.send();
            movies = JSON.parse(xmlhttp.responseText);
        	return movies;
        }
    }
})();
