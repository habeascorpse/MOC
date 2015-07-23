/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
MocApp.controller('MessageController', function ($scope, $http, $location, State) {

    $scope.voucher = State.formData['voucher'];
    $scope.textMessage = {text: ""};
    $scope.selectedGroup = {name: null};

    $scope.getGroups = function () {


        $http.get(State.formData['url'] + 'group/all/' + State.formData['voucher']).
                success(function (data, status, headers, config) {
                    $scope.groups = data;


                })
                .error(function () {
                    $scope.msg = "Unable to load group list";
                });


    };

    $scope.getMessagesFromGroup = function (group) {

        $scope.selectedGroup = group;
        if ((group != null) && (group.name != null)) {


            $http.get(State.formData['url'] + 'message/get/' + group.name + '/' + State.formData['voucher']).
                    success(function (data, status, headers, config) {
                        $scope.messages = data;

                    })
                    .error(function () {
                        $scope.msg = "Unable to load messages from this group";
                    });
        }


    };

    $scope.sendMessage = function (group) {
        if ((group != null) && (group.name != null)) {

            $http.post(State.formData['url'] + 'message/send/' + group.name + '/' + State.formData['voucher'], $scope.textMessage).
                    success(function (data, status, headers, config) {
                        $scope.textMessage.text = "";
                        //$scope.getMessagesGromGroup(group);
                    })
                    .error(function () {
                        $scope.msg = "Unable to send message from this group";
                    });
        }


    };

    $scope.main = function () {

        $scope.getMessagesFromGroup($scope.selectedGroup);

        setTimeout($scope.main, 2000);


    };


});

