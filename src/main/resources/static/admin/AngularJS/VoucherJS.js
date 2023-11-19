var app = angular.module("voucher", [])
app.controller("voucher-ctrl", function ($scope, $http) {
    const url = "http://localhost:8080/admin/voucher"
    var getUrlWithId = function (id) {
        return url + "/" + id;
    }
    //    chi tiet
    $scope.findById = function (id) {
        var urlWithId = getUrlWithId(id);
        $http.get(urlWithId).then(function (res) {
            const voucher = res.data;
            $scope.ten = voucher.ten;
            $scope.ma = voucher.ma;
            $scope.maConfirm = voucher.ma;
            $scope.loai = voucher.loai;
            $scope.mucGiam = voucher.mucGiam;
            $scope.mucGiamToiDa = voucher.mucGiamToiDa;
            $scope.giaTriToiThieu = voucher.giaTriToiThieu;
            $scope.soLuong = voucher.soLuong;
            $scope.ngayBatDau = voucher.ngayBatDau;
            $scope.ngayKetThuc = voucher.ngayKetThuc;
        });
    }
    //delete
    $scope.delete = function (id) {
        var urlWithId = getUrlWithId(id)
        $http.delete(urlWithId).then(function (res) {
            alert("Delete success");
            location.reload();
        })
    }
    //add
    $scope.create = function () {
        var voucher = {
            ten: $scope.ten,
            loai: $scope.loai,
            mucGiam: $scope.mucGiam,
            mucGiamToiDa: $scope.mucGiamToiDa,
            giaTriToiThieu: $scope.giaTriToiThieu,
            ngayBatDau: $scope.ngayBatDau,
            ngayKetThuc: $scope.ngayKetThuc,
            soLuong: $scope.soLuong
        }
        $http.post(url, voucher).then(function (response) {
            location.reload();
            alert("Create success");

        }).catch(error => {
            console.log(error)
            $scope.erTen = error.data.ten
            $scope.erMucGiam = error.data.mucGiam
            $scope.erMucGiamToiDa = error.data.mucGiamToiDa
            $scope.erMucGiamToiThieu = error.data.mucGiamToiThieu
            $scope.erNgayBatDau = error.data.ngayBatDau
            $scope.erNgayKetThuc = error.data.ngayKetThuc
            $scope.erSoLuong = error.data.soLuong

        })
    }
    //update
    $scope.update = function (id) {
        var urlWithId = getUrlWithId(id)
        var voucher = {
            ten: $scope.ten,
            loai: $scope.loai,
            mucGiam: $scope.mucGiam,
            mucGiamToiDa: $scope.mucGiamToiDa,
            giaTriToiThieu: $scope.giaTriToiThieu,
            ngayBatDau: $scope.ngayBatDau,
            ngayKetThuc: $scope.ngayKetThuc,
            soLuong: $scope.soLuong
        }
        $http.put(urlWithId, voucher).then(function (resp) {
            location.reload();
            alert("Update success");
        })
    }
    const currencySelector = document.getElementById("loaiGiam");
    const input = document.getElementById("mucGiamToiDa");

    input.style.display = "none";

    currencySelector.addEventListener("change", function () {
        if (currencySelector.value === "TIEN") {
            input.style.display = "none";
        } else {
            input.style.display = "block";
        }
    });
});

