var app = angular.module('app', ['ngRoute','ngResource', 'ngCookies', function($httpProvider){
	

}]);

app.config(function($routeProvider){
    $routeProvider
        .when('/',{
            templateUrl: '/parciales/login.html',
            controller: 'loginController'
        }).when('/holamundo',{
            templateUrl: '/parciales/holamundo.html',
            controller: 'hmController'
        })
        .otherwise(
            { redirectTo: '/'}
        );
});
