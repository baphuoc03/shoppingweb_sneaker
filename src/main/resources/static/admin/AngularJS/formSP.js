var app = angular.module("formSP-app", [])
app.controller("ctrl", function ($scope, $http) {

    var httpThuocTinh = "";
    var thuocTinhSL = undefined;
    var filesTransfer = new DataTransfer();
    var checkViewModal = false;
    document.getElementById("pro-image").files = filesTransfer.files

    $("#viewAdd").on('hide.bs.modal', function () {
        if (checkViewModal == false) thuocTinhSL.selectedIndex = 0;
        $scope.tenThuocTinh = "";
        document.getElementById("viewAddThuongHieu").style.display = "none"
    });

    $scope.viewAddMauSac = function () {
        httpThuocTinh = "/admin/mau-sac/add"
        thuocTinhSL = document.getElementById("mauSac");

        if (thuocTinhSL.selectedIndex == thuocTinhSL.length - 1) {
            $('#viewAdd').modal('show');
            checkViewModal = false
        }
    }
    $scope.viewAddXuatXu = function () {
        httpThuocTinh = "/admin/xuat-xu/add"
        thuocTinhSL = document.getElementById("xuatXu");

        if (thuocTinhSL.selectedIndex == thuocTinhSL.length - 1) {
            $('#viewAdd').modal('show');
            checkViewModal = false
        }
    }
    $scope.viewAddDongSP = function () {
        httpThuocTinh = "/admin/dong-san-pham/add"
        thuocTinhSL = document.getElementById("dongSP");

        if (thuocTinhSL.selectedIndex == thuocTinhSL.length - 1) {
            document.getElementById("viewAddThuongHieu").style.display = "inline-block"
            $('#viewAdd').modal('show');
            checkViewModal = false
        }
    }

    $scope.viewAddKieuDang = function () {
        httpThuocTinh = "/admin/kieu-dang"
        thuocTinhSL = document.getElementById("kieuDang");

        if (thuocTinhSL.selectedIndex == thuocTinhSL.length - 1) {
            $('#viewAdd').modal('show');
            checkViewModal = false
        }
    }

    $scope.viewAddChatLieu = function () {
        httpThuocTinh = "/admin/chat-lieu/add"
        thuocTinhSL = document.getElementById("chatLieu");

        if (thuocTinhSL.selectedIndex == thuocTinhSL.length - 1) {
            $('#viewAdd').modal('show');
            checkViewModal = false
        }
    }
    $scope.addThuocTinh = function () {
        $http.post(httpThuocTinh, {thuongHieu: $scope.thuongHieu, ten: $scope.tenThuocTinh}).then(r => {
            // document.getElementById("viewAddThuongHieu").style.display = "none"
            if (thuocTinhSL.id == "dongSP") $scope.addOtpInDongSP(r.data)
            else {
                var option = document.createElement("option");
                option.text = r.data.ten;
                option.value = r.data.id == undefined ? r.data.ma : r.data.id
                thuocTinhSL.add(option, thuocTinhSL[thuocTinhSL.length - 1]);
                thuocTinhSL.value = option.value;
            }

            $scope.tenThuocTinh = "";
            checkViewModal = true
            $('#viewAdd').modal('hide');
        }).catch(e => console.log(e))
    }
    $scope.addOtpInDongSP = function (data) {
        let otpGroup = document.getElementById($scope.thuongHieu+"");
        console.log(otpGroup)
        console.log(data)
        let option = document.createElement("option");
        option.setAttribute("value",data.id);
        option.innerHTML=data.ten;
        // option.text = data.ten;
        // option.value = data.id;
        otpGroup.append(option)
        thuocTinhSL.value = option.value;

    }


    $scope.removeER = function (id) {
        document.getElementById(id).innerText = "";
    }
    $scope.closeModal = function () {
        $('#viewAdd').modal('hide');
    }

    $scope.appendFile = function () {
        let files = document.getElementById("pro-image").files
        files.forEach(f => filesTransfer.items.add(f))
        // document.getElementById("pro-image").files = filesTransfer.files
    }
    $scope.removeFile = function (key) {
        let index;
        let files1 = new DataTransfer();
        filesTransfer.files.forEach(f => {
            if (f.lastModified != key) {
                files1.items.add(f);
            }
        })

        filesTransfer = files1
        // document.getElementById("pro-image").files = filesTransfer.files
    }
    $scope.loadImgProduct = function (fileName) {
        const image = new File([fileName], fileName, {
            lastModified: new Date(),
        });
        let buttonCancel = document.getElementById(fileName).getElementsByClassName('image-cancel')
        buttonCancel[0].setAttribute("id", image.lastModified);
        console.log(buttonCancel)

        filesTransfer.items.add(image);
        document.getElementById("pro-image").files = filesTransfer.files
    }
    $scope.sortFiles = function () {
        console.log(document.getElementsByClassName("image-cancel"))
        let files1 = new DataTransfer();
        document.getElementsByClassName("image-cancel").forEach(i => {
            console.log(i)
            filesTransfer.files.forEach(f => {
                if (f.lastModified == i.id) {
                    files1.items.add(f);
                }
            })
        })
        filesTransfer = files1
        document.getElementById("pro-image").files = filesTransfer.files

        // filesTransfer = new DataTransfer();
        // document.getElementById("pro-image").files = filesTransfer.files

    }
    $scope.check = function (){
        console.log(filesTransfer.files, document.getElementById("pro-image").files)
    }

})

