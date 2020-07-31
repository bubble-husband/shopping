app.controller("brandController",function ($scope,brandService,$controller){

    $controller('baseController',{$scope:$scope});

    $scope.entity={};
    $scope.searchEntity={};

    $scope.findAll = function () {
        brandService.findAll().success(function (data) {
            $scope.list=data;
        })
    };

    $scope.findPage = function (pageNo,pageSize) {
        brandService.findPage(pageNo,pageSize).success(function (data) {
            $scope.list = data.rows;
            $scope.paginationConf.totalItems = data.total;

        })
    };

    var method = {};
    $scope.save = function(){
        if ($scope.entity.id == undefined||$scope.entity.id == ''|| $scope.entity.id == null){
            method = brandService.add;
        }else {
            method = brandService.update;
        }
        method($scope.entity).success(function (data) {
            if(data.success){
                console.log(data.message);
                //清空实体类
                $scope.entity = {};
                //重新加载页面
                $scope.reloadList();
            }else {
                console.log(data.message)
            }

        })
    };

    $scope.findOne = function (id) {
        brandService.findOne(id).success(function (data) {

            if (data == undefined||data == ""||data == null){
                console.log("查找失败");
            }else {
                $scope.entity = data;
            }
        })
    };

    $scope.search = function (pageNo,pageSize) {
        brandService.search(pageNo,pageSize,$scope.searchEntity).success(function (data) {
            $scope.list = data.rows;
            $scope.paginationConf.totalItems = data.total;
        })
    };

    $scope.delete = function () {
       brandService.delete($scope.ids).success(function (data) {
            if (data.success){
                $scope.reloadList();
            }else {
                console.log(data.message);
            }
        })
    };

})