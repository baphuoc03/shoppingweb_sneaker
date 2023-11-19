var app = angular.module("trangChu-app", [])
app.controller("ctrl", function ($scope, $http) {
    $scope.products = [];
    $scope.maSpInDSYT = []
    $scope.getSanPham = function (number) {
        let path = ""
        if (number == 0) {
            path = "ban-chay"
        } else if (number == 1) {
            path = "khuyen-mai"
        } else {
            path = "san-pham-moi"
        }
        $http.get("/san-pham/" + path).then(r => {
            $scope.products = r.data
            console.log($scope.products)
        }).catch(e => console.log(e))
    }
    $scope.getSanPham(0)

    $scope.getMaSanPhamInDSTY = function () {
        $http.get("/danh-sach-yeu-thich/get-ma-san-pham-in-dsyt").then(r => {
            $scope.maSpInDSYT = r.data
            console.log($scope.maSpInDSYT.length)
            // document.getElementById("buttonHeart").setAttribute("data-notify", ""+$scope.maSpInDSYT.length)
        }).catch(e => console.log(e))
    }
    $scope.getMaSanPhamInDSTY()

    $scope.addDSYT = function (id) {
        let heartButton = document.getElementById("" + id)
        // let className = heartButton.className;
        let data = {
            "sanPham": id
        }
        if (heartButton.className == "far fa-heart" || heartButton.className == "far fa-heart ng-scope") {// thêm vào danh sách yêu thích
            $http.post("/danh-sach-yeu-thich/add", data).then(r => {
                heartButton.className = "fas fa-heart"
                alert("Đã thêm vào danh sách yêu thích!")
                $scope.getMaSanPhamInDSTY()
            }).catch(e => {
                heartButton.className = "far fa-heart"

                if (e.status == "401") {//Bắt lỗi chưa đăng nhập
                    $('#dangNhap').modal('show') // hiển thị modal
                }
            })
        } else { // xóa khỏi danh sách yêu thích
            $http.delete("/danh-sach-yeu-thich/delete/" + id).then(r => {
                heartButton.className = "far fa-heart"

                let index = $scope.maSpInDSYT.findIndex(m => m == id + "");
                $scope.maSpInDSYT.splice(index, 1)

                alert("Đã xóa danh sách yêu thích!")
            }).catch(e => {
                heartButton.className = "fas fa-heart"

                if (e.status == "401") {//Bắt lỗi chưa đăng nhập
                    $('#dangNhap').modal('show') // hiển thị modal
                }
            })
        }

    }

//    show giỏ hàng
    $scope.cart = []
    $scope.getTotal = function () {
        var totalPrice = 0;
        for (let i = 0; i < $scope.cart.length; i++) {
            totalPrice += $scope.cart[i].soLuong * $scope.cart[i].donGiaSauGiam
        }
        return totalPrice;
    }
    $http.get("/cart/find-all").then(r => {
        console.log(r.data)
        $scope.cart = r.data;
        console.log("soLuong:")
    }).catch(e => console.log(e))

    $scope.login = function (){
        var expires = (new Date(Date.now()+ 60*1000)).toUTCString();
        document.cookie = "url="+window.location.href+"; expires="+expires;
        location.href = "/dang-nhap";
    }

})