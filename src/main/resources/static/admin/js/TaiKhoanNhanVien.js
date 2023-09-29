var app = angular.module('nhanvien-admin', []);
app.controller('ctrl', function ($scope, $http) {

    $scope.nhanVien = [];
    $scope.nhanVienDetail = {};

    $http.get("/admin/nhan-vien/get-all").then(r =>{
        $scope.nhanVien = r.data;
    }).catch(e => console.log(e))

    $scope.detail = function (id){
        $http.get("/admin/nhan-vien/detail/"+id).then(r => {
            $scope.nhanVienDetail = r.data;
            console.log($scope.nhanVienDetail)
        }).catch(e => console.log(e))
    }
})