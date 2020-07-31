 //控制层 
app.controller('typeTemplateController' ,function($scope,$controller,typeTemplateService,brandService,specificationService){
	
	$controller('baseController',{$scope:$scope});//继承



	$scope.brandList={data:[]};//品牌列表
	//读取品牌列表
	$scope.findBrandList=function(){
		brandService.selectOptionList().success(
			function(response){
				$scope.brandList={data:response};
			}
		);
	}

	$scope.specificationList={data:[]};//规格列表
	//读取规格列表
	$scope.findSpecificationList=function() {
		specificationService.selectOptionList().success(
			function (response) {
				$scope.specificationList = {data: response};
			}
		);
	}
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		typeTemplateService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}
	
	//分页
	$scope.findPage=function(page,rows){			
		typeTemplateService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(id){				
		typeTemplateService.findOne(id).success(
			function(response){
				$scope.entity= response;
				$scope.brandIds= JSON.parse($scope.entity.brandIds);//转换品牌列表
				$scope.specIds= JSON.parse($scope.entity.specIds);//转换规格列表
				if ($scope.entity.customAttributeItems!=null||$scope.entity.customAttributeItems!=''){
					$scope.customAttributeItems= JSON.parse($scope.entity.customAttributeItems);//转换扩展属性
				}

			}
		);				
	}

	var serviceObject = {};//服务层对象
	//保存 
	$scope.save=function(){
		$scope.entity.brandIds= JSON.stringify($scope.brandIds);//转换品牌列表
		$scope.entity.specIds= JSON.stringify($scope.specIds);//转换规格列表
		$scope.entity.customAttributeItems= JSON.stringify($scope.customAttributeItems);//转换扩展属性
		if($scope.entity.id == null || $scope.entity.id == ''|| $scope.entity.id == undefined){//如果有ID
			serviceObject=typeTemplateService.add($scope.entity);//增加
		}else{
			serviceObject=typeTemplateService.update($scope.entity); //修改
		}				
		serviceObject.success(
			function(response){
				if(response.success){
					//重新查询
		        	$scope.reloadList();//重新加载
				}else{
					console.log(response.message);
				}
			}		
		);				
	}

	$scope.clear = function() {
		$scope.entity = {};
		$scope.brandIds= [];
		$scope.specIds= [];
		$scope.customAttributeItems= [];
	}
	
	 
	//批量删除 
	$scope.delete = function() {
		//获取选中的复选框			
		typeTemplateService.delete($scope.ids).success(
			function(response){
				if(response.success){
					$scope.reloadList();//刷新列表
					$scope.ids=[];
				}						
			}		
		);				
	}
	
	$scope.searchEntity={};//定义搜索对象 
	
	//搜索
	$scope.search=function(page,rows){			
		typeTemplateService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}



	//新增扩展属性行
	$scope.addTableRow=function(){
		$scope.customAttributeItems.push({});
	};

	//新增扩展属性行
	$scope.deleteTableRow=function(index){
		$scope.customAttributeItems.splice(index,1);
	};
});	
