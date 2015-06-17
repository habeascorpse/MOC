
app.controller('UserController', function ($scope, $http) {

    $scope.user = {
        name: '',
        email: '',
        login: '',
        password: '',
        country: {
            id: 0
        }
    };

    $scope.createUser = function () {

        $scope.user.country["id"] = $scope.selectedCountry;
        var req = {
            method: 'POST',
            url: 'http://localhost:8080/moc/rs/users/create',
            headers: {
                'Content-Type': 'application/json'
            },
            data: $scope.user
        };

        $http(req)
                .success(function (data) {
                    $scope.user = {
                        name: '',
                        email: '',
                        login: '',
                        password: '',
                        country: {
                            id: 0
                        }
                    };
                    $scope.msg = 'Created, please check your mail!';
                })
                .error(function (data, status, headers, config) {
                    if (status == 409) {
                        $scope.user.login = '';
                        $scope.msg = "Login already exist, please change it!";
                    }
                });

    };

    $scope.getCountries = function () {
        $http.get('http://localhost:8080/moc/rs/users/countries').success(function (data) {
            $scope.countries = data;
        });
    };
    
    $scope.authenticate = function() {
        
        
        $http.post('http://localhost:8080/moc/rs/users/authenticate',$scope.user ).
        success(function(data, status, headers, config) {
    // this callback will be called asynchronously
    // when the response is available
        });
    }
});
