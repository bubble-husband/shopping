app.service('brandService',function ($http) {

    //读取列表数据绑定到表单中

    this.selectOptionList=function(){
        return $http.get('/admin/brand/selectOptionList');
    }

    this.findAll = function(){
        return $http.get("/admin/brand/findAll");
    }

    this.findPage = function(pageNo,pageSize){
        return $http.get("/admin/brand/findPage?pageNo="+pageNo+"&pageSize="+pageSize);
    }

    this.update = function(entity){
        return $http.post("/admin/brand/update",entity);
    }

    this.add = function(entity) {
        return $http.post("/admin/brand/add",entity);
    }

    this.findOne = function(id){
        return $http.get("/admin/brand/findOne?id="+id);
    }

    this.search = function(pageNo,pageSize,entity){
        return $http.post("/admin/brand/search?pageNo="+pageNo+"&pageSize="+pageSize,entity);

    }

    this.delete = function(ids){
        return $http.get("/admin/brand/delete?ids="+ids);
    }


})