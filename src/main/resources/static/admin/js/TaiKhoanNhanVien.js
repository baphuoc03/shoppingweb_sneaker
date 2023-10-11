var app = angular.module('nhanvien-admin', []);
app.controller('ctrl', function ($scope, $http) {

    $scope.nhanVien = [];
    $scope.nhanVienDetail = {};
    $scope.nhanVienAdd = {};

    $http.get("/admin/nhan-vien/get-all").then(r =>{
        $scope.nhanVien = r.data;
    }).catch(e => console.log(e))

    $scope.detail = function (id){
        $http.get("/admin/nhan-vien/detail/"+id).then(r => {
            $scope.nhanVienDetail = r.data;
            console.log($scope.nhanVienDetail)
        }).catch(e => console.log(e))
    }
    $scope.add = function (){
        console.log($scope.nhanVienAdd)
        $http.post("/admin/nhan-vien",$scope.nhanVienAdd).then(r => {
            console.log(r.data)
            $scope.nhanVien.push(r.data);
            $('#viewAdd').modal('hide');
        }).catch(e => {
            console.log(e)
        })
    }
})