app.controller('appController', function($scope) {

	$scope.titulo = "PRUEBA TECNICA";
	
});

app.controller('loginController', function($scope, $location, $http, $cookies) {

	$scope.controlador = "loginController";
	
	$scope.login = function(){
		
		console.log("hola mundo");
			
		var datapost = {
			method: 'POST',
			data: {
				username : $scope.username,
				password: $scope.password
			},
			url: '/security/login',
			headers:{
				'Accept': 'application/json'
			}
		};
		
		$http(datapost).then(function successCallback(response){
			
			var token = response.data.token;
			
			$cookies.put('token', token);
			
			$location.path("/holamundo");
			
		}, function errorCallback(response){
			$scope.mensaje = response;
			
		});
		
		
	}
	
		
});

//hmController

app.controller('hmController', function($scope, $http ,$cookies, $location) {

	$scope.token = $cookies.get('token');

	$scope.solicitar_saludo = function(){
		
		var datapost = {
				method: 'GET',
				headers: {
					'Authorization': $cookies.get('token')
				},
				url: '/saludo/holamundo',
				
			};
			
			$http(datapost).then(function successCallback(response){
				
				$scope.mensaje = response;
				$scope.message = response.data.message;
				$scope.saludo = response.data.saludo;
				$scope.status = response.status;
				
			}, function errorCallback(response){
				$scope.mensaje = response;
				$scope.message = response.data.message+" a un saludo | prueba con admin";
				$scope.status = response.data.status;
				
				
				
				
			});
		
	}
	
	$scope.salir = function(){
		
		$cookies.remove('token');
		
		$location.path("/login");
		
	}
	
});

app.controller('appController', function($scope) {

	$scope.titulo = "LOGIN";
	$scope.autenticacion = false;
	
	$scope.menu = "/views/menu.html";
	
});