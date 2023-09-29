var app = angular.module("formSP-app", [])
app.controller("ctrl", function ($scope, $http) {

    var httpThuocTinh = "";
    var thuocTinhSL = undefined;

    $scope.viewAddMauSac = function (){
        httpThuocTinh = "/admin/mau-sac/add"
        thuocTinhSL = document.getElementById("mauSac");

        if(thuocTinhSL.selectedIndex == thuocTinhSL.length-1){
            $('#viewAdd').modal('show');
        }
    }

    $scope.viewAddDongSP = function (){
        httpThuocTinh = "/admin/dong-san-pham/add"
        thuocTinhSL = document.getElementById("dongSP");

        if(thuocTinhSL.selectedIndex == thuocTinhSL.length-1){
            $('#viewAdd').modal('show');
        }
    }

    $scope.viewAddKieuDang = function (){
        httpThuocTinh = "/kieu-dang/add"
        thuocTinhSL = document.getElementById("kieuDang");

        if(thuocTinhSL.selectedIndex == thuocTinhSL.length-1){
            $('#viewAdd').modal('show');
        }
    }

    $scope.viewAddChatLieu = function (){
        httpThuocTinh = "/admin/chat-lieu"
        thuocTinhSL = document.getElementById("chatLieu");

        if(thuocTinhSL.selectedIndex == thuocTinhSL.length-1){
            $('#viewAdd').modal('show');
        }
    }
    $scope.addThuocTinh = function (){
        $http.post(httpThuocTinh,{ten : $scope.tenThuocTinh}).then(r => {
            var option = document.createElement("option");
            option.text = r.data.ten;
            option.value = r.data.id == undefined ? r.data.ma : r.data.id
            thuocTinhSL.add(option,thuocTinhSL[thuocTinhSL.length - 1]);
            thuocTinhSL.selectedIndex = thuocTinhSL.length - 2;
            $scope.tenThuocTinh = "";
            $('#viewAdd').modal('hide');
        }).catch(e => console.log(e))
    }

    $scope.removeER = function (id){
        document.getElementById(id).innerText = "";
    }
    $scope.closeModal = function (){
        $('#viewAdd').modal('hide');
    }

})