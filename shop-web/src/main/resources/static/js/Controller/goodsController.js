//控制层
app.controller('goodsController', function ($scope, $controller, goodsService, fastdfsService, itemCatService) {

    $controller('baseController', {$scope: $scope});//继承

    //读取列表数据绑定到表单中  
    $scope.findAll = function () {
        goodsService.findAll().success(
            function (response) {
                $scope.list = response;
            }
        );
    }

    //分页
    $scope.findPage = function (page, rows) {
        goodsService.findPage(page, rows).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    }


    $scope.tabId=0;
    $scope.choicetab=function(tabId){
        $scope.tabId=tabId;
    }

    //查询实体
    $scope.findOne=function(id){
        goodsService.findOne(id).success(
            function(response){
                $scope.entity= response;
                $scope.images=JSON.parse(response.goodsDesc.itemImages);
            }
        );
    }
    $scope.findOneById=function(id){
        location.href="./goods_edit.html?id="+id;
    }

    //保存
    $scope.save=function(){
        var serviceObject;//服务层对象
        if($scope.entity.goods.id!=null){//如果有ID
            console.log($scope.tabId)
            serviceObject=goodsService.update($scope.tabId, $scope.entity ); //修改
        }else{
            //entity中的一级，二级，三级、品牌
            $scope.entity.goods.category1Id=JSON.parse($scope.itemCat_0).id;
            $scope.entity.goods.category2Id=JSON.parse($scope.itemCat_1).id;
            $scope.entity.goods.category3Id=JSON.parse($scope.itemCat_2).id;
            $scope.entity.goods.brandId=$scope.entityBrand.id;
            $scope.entity.goodsDesc.introduction=editor.html();
            serviceObject=goodsService.add( $scope.entity  );//增加
        }
        serviceObject.success(
            function(response){
                if(response.success){
                    //重新查询
                    $scope.reloadList();//重新加载
                }else{
                    alert(response.message);
                }
            }
        );
    }


    //批量删除
    $scope.dele = function () {
        //获取选中的复选框
        goodsService.dele($scope.selectIds).success(
            function (response) {
                if (response.success) {
                    $scope.reloadList();//刷新列表
                    $scope.selectIds = [];
                }
            }
        );
    }

    $scope.searchEntity = {};//定义搜索对象

    //搜索
    $scope.search = function (page, rows) {
        goodsService.search(page, rows, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    }

    $scope.entityImage = {url:""};
    $scope.upload = function () {
        fastdfsService.upload().success(function (data) {
            if (data != null || data != undefined) {
                $scope.entityImage.url = data.server+"/"+data.path;

            } else {
                alert("上传失败....")
            }

        });

    }

    $scope.showImage=function () {
        var reader=new FileReader();
        reader.addEventListener("load",function (e) {
            $scope.$apply(function () {
                $scope.iconUrl=e.target.result;
            });
        },false);
        reader.readAsDataURL(file.files[0]._file)
    }

    $scope.initPage=function(){
        $scope.searchItemCat(0,0);
        $scope.findAllBrands();
        var id=$location.search().id;
        if(id!=undefined||id!=null){
            $scope.findOne(id);
        }
    }

    $scope.entityBrand={};
    $scope.findAllBrands=function(){
        brandService.findAll().success(function (data) {
            $scope.brands=data;
        })
    }

    $scope.itemCatGrade = 0;
    $scope.itemCatListGrade0 = [];
    $scope.itemCatListGrade1 = [];
    $scope.itemCatListGrade2 = [];
    $scope.itemCat_0 = {};
    $scope.itemCat_1 = {};
    $scope.itemCat_2 = {};
    $scope.itemCat_3 = {};
    $scope.searchItemCat = function (grade,id) {
        itemCatService.searchItemCatByParentId(grade).success(function (data) {
            if (grade == 0){
                $scope.itemCat_0 = data[0];
                $scope.itemCatListGrade1 = [];
                $scope.itemCatListGrade2 = [];
                $scope.itemCat_1 = {};
                $scope.itemCat_2 = {};
                $scope.itemCatListGrade0 = data;
            }else if (grade == 1){
                $scope.itemCatListGrade2 = [];
                $scope.itemCat_1 = data[0];
                $scope.itemCat_2 = {};
                $scope.itemCatListGrade1 = data;
            }else {
                $scope.itemCat_2 = data[0];
                $scope.itemCatListGrade2 = data;
            }

        })

    }


    $scope.itemCatChange = function (grade) {

        if (grade == 0){
            $scope.searchItemCat(1,JSON.parse($scope.itemCat_0).id);
        }else if (grade == 1){
            $scope.searchItemCat(2,JSON.parse($scope.itemCat_1).id);
        }else {
            $scope.itemCat_3 = JSON.parse($scope.itemCat_2);
        }

    }

});	
