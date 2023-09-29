var app = angular.module('chiTietSP-app', []);
app.controller('chiTietSP-ctrl', function ($scope, $http) {

    const pathName = window.location.pathname.split('/');
    const idSP = pathName[pathName.length - 1]

    $scope.items =[];
    $scope.form ={
        sanPham : idSP
    };
    $scope.sizes = [];
    $scope.itemUpdate = {};

    $scope.getAll = function (){
        $http.get("/admin/san-pham/"+idSP+"/get-all").then(r => {
            $scope.items = r.data;
        }).catch(e => console.log(e))
    }
    $scope.getSizes = function (){
        $http.get("/admin/san-pham/"+idSP+"/test").then(r => {
            console.log(r.data)
            $scope.sizes = r.data;
        }).catch(e => console.log(e))
    }
    $scope.getAll();
    $scope.getSizes();


    // $scope.delete = function (ma){
    //     $http.delete("/admin/san-pham/delete/"+ma).then(r => {
    //         var index = $scope.items.findIndex(i => i.ma == ma);
    //         console.log(index)
    //         $scope.items.splice(index,1);
    //         // $scope.getAll();
    //     }).catch(e => console.log(e));
    // }

    $scope.getChiTietSP = function (ma){
        location.href = "/admin/san-pham/"+ma;
    }

    //Thêm
    $scope.add = function (){

        let data =[];
        let sizesInForm = $scope.form.sizes


        if(sizesInForm == undefined){
            document.getElementById("eSize").innerText = "Vui lòng chọn size"
            return
        }else if($scope.form.soLuong == undefined){
            document.getElementById("eSoLuong").innerText = "Vui lòng chọn điền số lượng"
            return
        }else if($scope.form.soLuong < 0){
            document.getElementById("eSoLuong").innerText = "Số lượng phải >= 0 "
            return
        }

        if(confirm("Thêm "+sizesInForm.length+" chi tiết sản phẩm ?")){
            for (let i = 0 ;i< sizesInForm.length;i++){
                $scope.form.size = sizesInForm[i]
                data.push({
                    sanPham : idSP,
                    size : sizesInForm[i],
                    soLuong : $scope.form.soLuong
                })
            }
            console.log(data)

            $http.post("/admin/san-pham/"+idSP+"/add?soLuong="+$scope.form.soLuong,data).then(r =>{
                $scope.removeSizeInForm(sizesInForm);
                $scope.items = $scope.items.concat(r.data);
                $scope.form.soLuong = ""
                alert("Thêm thành công "+sizesInForm.length+" chi tiết sản phẩm")
            }).catch(e => {
                alert("Thêm thất bại!!!!")
                console.log(e)
            })
        }
    }

    //Xóa
    $scope.delete = function (item){
        console.log(item)
        if(confirm("Xóa chi tiết sản phẩm size "+item.size)){
            $http.delete("/admin/san-pham/"+idSP+"/delete/"+item.id).then(r => {
                let index = $scope.items.findIndex(i => i.id == item.id);
                $scope.items.splice(index,1);
                alert("Xóa thành công chi tiết sản phẩm size "+item.size);
            }).catch(e => {
                alert("Xóa thất bại!!!");
                console.log(e);
            })
        }
    }

    //Cập nhật
    $scope.viewUpdate = function (item){
        $scope.itemUpdate = angular.copy(item)
        console.log($scope.itemUpdate)
    }
    // $('#myModal').modal({
    //     keyboard: false
    // })
    $scope.update = function (){
        // if($scope.itemUpdate.soLuong == undefined){
        //     document.getElementById("eSoLuongUpdate").innerText = "Vui lòng chọn điền số lượng"
        //     return
        // }else if($scope.itemUpdate.soLuong < 0){
        //     document.getElementById("eSoLuongUpdate").innerText = "Số lượng phải >= 0 "
        //     return
        // }
        if(confirm("Cập nhật chi tiết sản phẩm size "+$scope.itemUpdate.size+" ?")){
            // $scope.itemUpdate.sanPham = idSP
            $http.put("/admin/san-pham/"+idSP+"/update",$scope.itemUpdate).then(r =>{
                let index = $scope.items.findIndex(i => i.id == $scope.itemUpdate.id)
                $scope.items[index] = r.data
                console.log("cl", $("#cancelModal").click())
                alert("Cập nhật thành công chi tiết sản phẩm size "+$scope.itemUpdate.size);
            }).catch(e => {
                document.getElementById("eSoLuongUpdate").innerText = e.data.soLuong
                console.log(e)
            })
        }
    }
    $scope.updateSlInTable = function (soLuong,id){
        console.log(id)
        $scope.itemUpdate.id = id
        $scope.itemUpdate.soLuong = parseInt(soLuong);
        console.log($scope.itemUpdate)
        $http.put("/admin/san-pham/"+idSP+"/update",$scope.itemUpdate).then(r =>{
            console.log(r.data)
        }).catch(e => {
            alert(e.data.soLuong)
            console.log(e)
        })
    }

    $scope.removeSizeInForm = function (size){
        for (let i = 0 ;i< size.length;i++){
            let index = $scope.sizes.findIndex(s => s.ma == size[i])
            $scope.sizes.splice(index,1);
        }
    }
    $scope.removeER = function (id){
        document.getElementById(id).innerText = "";
    }
});
$( '#multiple-select-clear-field' ).select2( {
    theme: "bootstrap-5",
    width: $( this ).data( 'width' ) ? $( this ).data( 'width' ) : $( this ).hasClass( 'w-100' ) ? '100%' : 'style',
    placeholder: $( this ).data( 'placeholder' ),
    closeOnSelect: false,
    allowClear: true,
} );





