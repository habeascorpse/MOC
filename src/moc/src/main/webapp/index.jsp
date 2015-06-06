<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MOC on the cloud</title>
        <script src="js/angular/angular.js"></script>
        <script src="js/app.js"></script>
        <script src="js/userController.js"></script>
    </head>
    <body ng-app="MocApp">
        <h1>Auto Cadastro!</h1>
        <div ng-controller="UserController">
            <form  ng-submit="createUser()">
                <p>
                    Name:<input type="text" ng-model="user.name" placeholder="name" />
                </p>
                <p>
                    Login:<input type="text" ng-model="user.login" placeholder="login" /> 
                </p>
                <p>
                    Email:<input type="text" ng-model="user.email" placeholder="mail@provider.com" /> 
                </p>
                <p>
                    Password:<input type="password" ng-model="user.password" /> 
                </p>
                <p ng-init="getCountries()">
                    Country:
                    <select ng-model="selectedCountry" convert-to-number > 
                        <option ng-repeat="country in countries" value="{{country.id}}">{{country.name}}</option>
                    </select>
                    {{selectedCountry }}
                </p>

                <input type="submit"  value="Criar" />
            </form>
            
        </div>
    </body>
</html>
