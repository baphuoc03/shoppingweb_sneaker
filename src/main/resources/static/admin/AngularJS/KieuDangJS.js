var app = angular.module("kieudang",[])
app.controller("kieudang-ctrl", function ($scope, $http) {
    const url = "http://localhost:8080/admin/kieu-dang"
    var getUrlWithId = function (id){
        return url + "/"+ id;
    }
    //Chi tiet
    $scope.findById = function (id) {
        var urlWithId = getUrlWithId(id);
        $http.get(urlWithId).then(function (res) {
            const kieudang = res.data;
            $scope.id = kieudang.id;
            $scope.ten = kieudang.ten;
            $scope.ngayTao = kieudang.ngayTao;
            $scope.ngayCapNhat = kieudang.ngayCapNhat;
        });
    }
    $scope.itemss = [];
    $scope.form = {

    };

    $scope.getAll = function () {
        $http.get("/admin/kieu-dang/find-all").then(r => {
            console.log(r.data)
            $scope.itemss = r.data;
        }).catch(e => console.log(e))
    }
    $scope.getAll()
// xóa
    $scope.delete = function (id) {
        if(confirm("Bạn muốn xóa sản phẩm này?")){
        var urlWithId = getUrlWithId(id)
        $http.delete(urlWithId).then(function (res) {
            location.reload();
            alert("Delete success");
        }).catch(error =>{
        alert("Lỗi Xóa Sản Phẩm !")
            console.log("error", error);
        })
    }
    }

    //add
    $scope.create = function () {
        var kieuDang = {
                ten: $scope.ten
            }
            $http.post(url, kieuDang).then(function (response) {
                // location.reload();
                $scope.itemss.push(response.data)
                alert("Create success");
            }).catch(error =>{
                console.log(error)
                $scope.erTen = error.data.ten
            });

    };
    //update
    $scope.update = function (id) {
        var urlWithId = getUrlWithId(id)
        var kieudang = {
            ten: $scope.ten,
        }
        $http.put(urlWithId, kieudang).then(function (resp) {
            location.reload();
            alert("Update success");
        }).catch(error =>{
            console.log(error)
            $scope.erTenUd = error.data.ten
        });
    };

})