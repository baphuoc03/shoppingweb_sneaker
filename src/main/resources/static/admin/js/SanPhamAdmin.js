var app = angular.module('product-admin', []);
app.controller('ctrl', function ($scope, $http) {


    $scope.items =[];
    $scope.form ={};
    $scope.filterData = {};


    $scope.getAll = function (){
        $http.get("/admin/san-pham/get-all").then(r => {
            console.log(r.data)
            $scope.items = r.data;
            $scope.filterData = {}
        }).catch(e => console.log(e))
    }
    $scope.getAll()

    $scope.delete = function (ma){
        if(confirm("Xóa sản phẩm? ")){
            $http.delete("/admin/san-pham/delete/"+ma).then(r => {
                var index = $scope.items.findIndex(i => i.ma == ma);
                console.log(index)
                $scope.items.splice(index,1);
                // $scope.getAll();
                alert("Xóa thành công")
            }).catch(e => {
                alert("Lỗi!!!")
                console.log(e)
            });
        }
    }

    $scope.getChiTietSP = function (ma){
        location.href = "/admin/san-pham/"+ma;
    }

    $scope.updateTrangThaiHienThi = function (switchId,maSP){
        let trangThai = document.getElementById(switchId).checked
        console.log(trangThai)
        $http.put("/admin/san-pham/update-TrangThai-HienThi/"+maSP,trangThai).then(r => {
            console.log("update hiển thị",r.data)
        }).catch(e => {
            alert("Lỗi!!!")
            document.getElementById(switchId).checked = trangThai == true ? false : true
        });
    }

    $scope.getPropertiesInFilter = function (){
        $http.get("/admin/mau-sac/find-all").then(r =>{
            $scope.mauSac = r.data;
        }).catch( e => console.log(e))

        $http.get("/admin/chat-lieu/find-all").then(r =>{
            $scope.chatLieu = r.data;
        }).catch( e => console.log(e))

        $http.get("/admin/dong-san-pham/find-all").then(r =>{
            $scope.dongSP = r.data;
        }).catch( e => console.log(e))

        $http.get("/kieu-dang/find-all").then(r =>{
            $scope.kieuDang = r.data;
        }).catch( e => console.log(e))
    }
    $scope.getPropertiesInFilter();

    $scope.filter = function (){
        console.log($scope.filterData)
        $http.post("/admin/san-pham/filter",$scope.filterData).then(r => {
            $scope.items = r.data;
        }).catch(e => console.log(e))
    }
    $scope.clearFilter = function (){
        $scope.getAll()
    }
});
//
// var appTB = angular.module('app', []);
// appTB.controller('thongBao-ctrl', function ($scope, $http) {
//
//     $scope.thongBao = [];
//
//     $http.get("/admin/thong-bao/get-all/afc0b6cc-4c66-11ee-b10b-d69e940a783b").then(r => $scope.thongBao = r.data)
//         .catch(e => console.log(e));
//
//     //////////////////
//     var socket = new SockJS('/connectNotification');
//     stompClient = Stomp.over(socket);
//     stompClient.connect({}, function(frame) {
//         // console.log('Connected: ' + frame);
//         stompClient.subscribe('/afc0b6cc-4c66-11ee-b10b-d69e940a783b', function(r) {
//             // $scope.thongBao.unshift()
//             console.log(r)
//         });
//     });
//     /////////////////
// })





