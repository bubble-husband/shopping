app.controller('baseController',function ($scope) {

    //重新加载列表 数据
    $scope.reloadList = function(){
        //切换页码
        $scope.search($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage);
        // $scope.search();
    };
    //分页控件配置
    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 10,
        itemsPerPage: 10,
        perPageOptions: [10, 20, 30, 40, 50],
        onChange: function(){
            // 重新加载
            $scope.reloadList();
        }
    };
    $scope.ids=[];//选中的ID集合

    $scope.updateSelection = function($event,id){
        if ($event.target.checked){
            $scope.ids.push(id);
        }else {
            var index = $scope.ids.indexOf(id);
            $scope.ids.splice(index,1);
        }
    };
    //提取json字符串数据中某个属性，返回拼接字符串 逗号分隔
    $scope.jsonToString=function(jsonString,key){
        var json=JSON.parse(jsonString);//将json字符串转换为json对象
        var value="";
        for(var i=0;i<json.length;i++){
            if(i>0){
                value+=","
            }
            value+=json[i][key];
        }
        return value;
    };


})