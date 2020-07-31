app.service('fastdfsService', function ($http) {

    //上传图片
    this.upload = function () {
        var formData = new FormData();
        formData.append("file", file.files[0]);
        return $http({
            method: 'POST',
            url: "/fastdfs/upload",
            headers: {'Content-Type': undefined},
            data: formData,
            transformRequest: angular.identity
        });
    }

    // //下载图片
    // this.download = function () {
    //     return $http.get("/fastdfs/upload/id=")
    //
    // }

})