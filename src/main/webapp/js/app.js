/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var MocApp = angular.module('MocApp', ['ngRoute']);

MocApp.config(function($routeProvider,$locationProvider)
{
   // remove o # da url
   $locationProvider.html5Mode(true);
 
   $routeProvider
 
   // para a rota '/', carregaremos o template home.html e o controller 'HomeCtrl'
   .when('/', {
      templateUrl : 'login_1.html',
      controller : 'UserController'
   })
   
   .when('/message', {
       templateUrl : 'home.html',
       controller : 'UserController'
   })
 
 
   // caso não seja nenhum desses, redirecione para a rota '/'
   .otherwise ({ redirectTo: '/' });
});
