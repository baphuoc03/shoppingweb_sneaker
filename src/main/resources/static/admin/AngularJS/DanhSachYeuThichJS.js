var app = angular.module("danh-sach-yeu-thich",[])
app.controller("danh-sach-yeu-thich-ctrl", function($scope, $http){
    $scope.form = {}
    $scope.items = []
    $scope.productDetails = [];

    $scope.findAll = function () {
        $http.get("/danh-sach-yeu-thich/find-all").then(resp => {
            $scope.items = resp.data;
            $scope.items.forEach(item => {
                $http.get("/chi-tiet-san-pham/" + item.sanPhamm.ma + "/get-all").then(r => {
                    item.chietTietSanPham = r.data;
                }).catch(e => console.log(e))
            })
        }).catch(error =>{
            console.log(error)
        })
    }
    $scope.findAll();

    $scope.delete = function (ma){
        var url = "/danh-sach-yeu-thich/delete" +"/"+ ma;
        $http.delete(url).then(function (r){
            alert("Dlete thành công!!!")
            location.reload();
        })
    }

    $scope.getDetail =function (maSP){
        var productDetails = []
        $http.get("/chi-tiet-san-pham/" + maSP + "/get-all").then(r => {
            productDetails = r.data;
        }).catch(e => console.log(e))
        return productDetails
    }
    $scope.getSoLuong = function (idCTSP,idSpan) {
        $http.get("/chi-tiet-san-pham/1/" + idCTSP).then(r => {
            document.getElementById('soLuong'+idSpan).innerText = "Còn lại " + r.data
        }).catch(e => console.log(e))
    }

    $scope.addToCart = function (idSlCTSP, idInputSoLuong){
        var soLuong = document.getElementById(idInputSoLuong).value
        var ctsp = document.getElementById(idSlCTSP).value
        console.log(soLuong,ctsp)
        if (confirm("Thêm sản phẩm vào giỏ hàng?")) {
            $http.post("/cart/add-to-cart?idCTSP=" + ctsp + "&sl=" + soLuong).then(function (response) {
                console.log(response.data)
                if (response.data == null || response.data.length == 0) {
                    alert("Phân loại của sản phẩm không đủ số lượng!!!")
                } else {
                    alert("Success")

                    $scope.cartShow()
                }
            }).catch(e => {
                document.getElementById("eSize").innerText = e.data.eSize = undefined ? "" : e.data.eSize
                console.log(e)
            })
        }
    }


})