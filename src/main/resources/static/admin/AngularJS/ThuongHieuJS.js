var app = angular.module("thuonghieu",[])
app.controller("thuonghieu-ctrl", function ($scope, $http) {
    const url = "http://localhost:8080/admin/thuong-hieu"
    var getUrlWithId = function (id) {
        return url + "/" + id;
    }
    $scope.itemss = [];
    $scope.form = {};
    // get all
    $scope.getAll = function () {
        $http.get("/admin/thuong-hieu/find-all").then(r => {
            console.log(r.data)
            $scope.itemss = r.data;
        }).catch(e => console.log(e))
    }
    $scope.getAll()
    //delete
    $scope.delete = function (id) {
        var urlWithId = getUrlWithId(id)
        $http.delete(urlWithId).then(function (res) {
            location.reload();
            alert("Xóa Thành Công !");
        })
    }
    // chi tiết
    $scope.findById = function (id){
        var urlWithId = getUrlWithId(id);
        $http.get(urlWithId).then(function (res){
            const thuonghieu = res.data;
            $scope.id = thuonghieu.id;
            $scope.ten = thuonghieu.ten;
            $scope.ngayTao = thuonghieu.ngayTao;
            $scope.ngayCapNhat = thuonghieu.ngayCapNhat;
        });
    }
    // add
    $scope.create = function (){

        console.log("add")
        var thuonghieu = {
            ten:$scope.ten
        }
        $http.post(url, thuonghieu).then(function (response){
            // location.reload();
            $scope.itemss.push(r, data)
            alert("Thêm Thành Công !");
        })
    }
    //update
    $scope.update = function (id){
        var urlWithId = getUrlWithId(id)
        var thuonghieu = {
            ten: $scope.ten
        }
        $http.put(urlWithId, thuonghieu).then(function (resp){
            location.reload();
            // $scope.itemss.merge(r, data)
            alert("Cập Nhật Thành Công")
        })
    }
})
