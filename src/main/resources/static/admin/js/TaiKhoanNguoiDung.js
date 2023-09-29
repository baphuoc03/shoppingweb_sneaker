var app = angular.module('nguoidung-admin', []);
app.controller('ctrl', function ($scope, $http) {

    $scope.khachHang = [];
    $scope.khachHangDetail = {};

    $http.get("/admin/khach-hang/get-all-khach-hang").then(r =>{
        $scope.khachHang = r.data;
    }).catch(e => console.log(e))

    $scope.detail = function (id){
        $http.get("/admin/khach-hang/detail/"+id).then(r => {
            $scope.khachHangDetail = r.data;
            console.log($scope.khachHangDetail)
        }).catch(e => console.log(e))
    }
})