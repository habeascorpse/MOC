
app.controller('UserController', function($scope,$http) {
  
    $scope.user = {
        name : '',
        email : '',
        login : '',
        password : '',
        country : {
            id : 0
        }
    };
    
    $scope.createUser = function() {   
        
        $scope.user.country = $scope.selectedCountry;
        var req = {
                    method: 'POST',
                    url: 'http://localhost:8080/moc/rs/users/create',
                    headers: {
                      'Content-Type': 'application/json'
                    },
                    data: $scope.user
                   };
        
        $http(req).success(function(data) {
           $scope.response = 'Verifique sua caixa de email!'; 
        });
        
    };
    
    $scope.getCountries = function() {
        $http.get('http://localhost:8080/moc/rs/users/countries').success(function(data) {
           $scope.countries = data;
        });
    };
});
