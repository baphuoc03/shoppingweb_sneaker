var app = angular.module("cart-app", [])
app.controller("cart-ctrl", function ($scope, $http) {
    $scope.cart = [];
    $scope.vouchers = [];

    $http.get("/cart/find-all").then(r => {
        console.log(r.data)
        $scope.cart = r.data;
        console.log("soLuong:")
    }).catch(e => console.log(e))

    $scope.updateSl = function (id, soLuong) {
        if (soLuong <= 0 || soLuong.length > 1 || soLuong.length > 1) {
            alert("Số lượng phải là số nguyên > 0!!!")
            return
        }
        if (!parseInt(soLuong)) {
            alert("Số lượng phải là số nguyên > 0!!")
            return
        }
        $http.put("/cart/update-sl/" + id + "/" + soLuong).then(r => {
            console.log(r.data)
            $scope.cart = r.data;
        })
    }

    $scope.setUpdateSL = function () {
        console.log(document.getElementById("slUpdate").getAttribute("name"))
        var id = document.getElementById("slUpdate").getAttribute("name")
        var soLuong = document.getElementById("slUpdate").value
        console.log(soLuong)
        if (soLuong <= 0 || soLuong.split('.').length > 1 || soLuong.split(',').length > 1) {
            alert("Số lượng phải là số nguyên > 0!!!")
            return
        }
        if (!parseInt(soLuong)) {
            alert("Số lượng phải là số nguyên > 0!!!")
            return
        }

        if (confirm("Cập nhật số lượng sản phẩm trong giỏ?")) {


            $http.put("/cart/update-sl?idCTSP=" + id + "&soLuong=" + soLuong).then(function (response) {
                console.log(response.data)
                if (response.data == null || response.data.length == 0) {
                    alert("Phân loại của sản phẩm không đủ số lượng!!!")
                } else {
                    alert("Success")
                    location.reload()
                }
            })
        }

    }
    $scope.removeProductIncart = function (idCTSP) {
        if (confirm("Xóa sản phẩm khỏi giỏ hàng?")) {
            $http.delete("/cart/remove/" + idCTSP).then(function (response) {
                // alert("Success")
                $scope.cart = response.data;
                $scope.getTotal();
                // let index = $scope.cart.findIndex(c => c.id == idCTSP);
                // $scope.cart.slice(index,1)
            })
        }
    }
    $scope.getTotal = function () {
        var totalPrice = 0;
        for (let i = 0; i < $scope.cart.length; i++) {
            totalPrice += $scope.cart[i].soLuong * $scope.cart[i].donGiaSauGiam
        }
        return totalPrice;
    }

//    show voucher
    $http.get("/check-out/voucher").then(resp => {
        console.log(resp.data)
        $scope.vouchers = resp.data;
    }).catch(error => {
        console.log(error)
    })

    $scope.showDetails = function (index) {
        $scope.selectedVoucher = $scope.vouchers[index];
    };

    $scope.hideDetails = function () {
        $scope.selectedVoucher = null;
    };

})



