var app = angular.module("donhang-app", [])
app.controller("donhang-ctrl", function ($scope, $http) {
    $scope.donHang = {}
    $scope.chiTietDonHang = []
    $scope.sanPham = [];
    const limit = 10;
    $scope.er = {}


    $scope.closeModal = function (id) {
        $(id).modal('hide')
        $scope.donHang = {}
    }
    $("#chuaXacNhanDetail").on('hide.bs.modal', function () {
        $scope.donHangChuaXacNhan = {}
        $scope.chiTietDonHang.length = 0
    });
    $("#donHangDetail").on('hide.bs.modal', function () {
        $scope.donHang = {}
        $scope.chiTietDonHang.length = 0
    });

    $scope.getTotalPrice = function () {
        let total = 0;
        $scope.chiTietDonHang.forEach(c => total += (c.donGiaSauGiam * c.soLuong))
        return total
    }
    ///////////////////////
    $scope.getSanPham = function () {
        $http.get("/admin/san-pham/1/get-all-ctsp").then(r => {
            $scope.sanPham = r.data
        }).catch(e => console.log(e))
    }
    $scope.getSanPham()
    $scope.addChiTietDonHang = function (item) {
        $scope.chiTietDonHang.push({
            sanPham: item.sanPham,
            anh: item.sanPhamDTO.anh.length == 0 ? "default.png" : item.sanPhamDTO.anh[0],
            size: item.size,
            soLuong: 1,
            donGia: item.sanPhamDTO.giaBan,
            donGiaSauGiam: item.sanPhamDTO.giaNiemYet,
            idChiTietSanPham: item.id
        })
        $scope.er.soLuongSP = ""
    }
    $scope.searchSanPham = function () {
        $http.get("/admin/san-pham/1/get-all-ctsp?keyWord=" + $scope.inputProduct).then(r => {
            $scope.sanPham = r.data
        }).catch(e => console.log(e))
    }
    $scope.checkSanPhamInDonHang = function (idCTSP) {
        let result = false;
        $scope.chiTietDonHang.forEach(d => {
            if (d.idChiTietSanPham == idCTSP) {
                result = true;
            }
        })
        return result;
    }

    ///////////////////////////////////////
    ///////Hàm dùng chung
    $scope.id = []
    $scope.trangThaiDonHang = 2
    $scope.huyDH = function () {
        console.log($scope.trangThaiDonHang)
        if ($scope.trangThaiDonHang == 1) {
            $scope.daXacNhan.huyDH();
        } else if ($scope.trangThaiDonHang == 2) {
            $scope.chuaXacNhan.huyDH();
        } else if ($scope.trangThaiDonHang == 3) {
            $scope.dangGiao.huyDH();
        }else if ($scope.trangThaiDonHang == 5) {
            $scope.chuaThanhToan.huyDH();
        }

    }
    /////////////////////Check Box
    $scope.setCheckAll = function (id, name) {
        let setCheckbox = document.getElementById(id)

        let checkBox = document.getElementsByName(name)
        if (setCheckbox.checked == true) {
            checkBox.forEach(c => {
                c.checked = true
            })
        } else {
            checkBox.forEach(c => {
                c.checked = false
            })
        }
        if ($scope.trangThaiDonHang == 1) {
            $scope.daXacNhan.checkButton();
        } else if ($scope.trangThaiDonHang == 2) {
            $scope.chuaXacNhan.checkButton()
        } else if ($scope.trangThaiDonHang == 3) {
            $scope.dangGiao.checkButton();
        }else if ($scope.trangThaiDonHang == 5) {
            $scope.chuaThanhToan.checkButton();
        }
    }
    $scope.checkAllChecked = function (name, idCheckBoxSetAll) {
        let checkBox = document.getElementsByName(name)
        let check = true;
        checkBox.forEach(c => {
            if (c.checked == false) {
                check = false
            }
        })
        document.getElementById(idCheckBoxSetAll).checked = check
        if ($scope.trangThaiDonHang == 1) {
            $scope.daXacNhan.checkButton();
        } else if ($scope.trangThaiDonHang == 2) {
            $scope.chuaXacNhan.checkButton()
        } else if ($scope.trangThaiDonHang == 3) {
            $scope.dangGiao.checkButton();
        }else if ($scope.trangThaiDonHang == 5) {
            $scope.chuaThanhToan.checkButton();
        }
    }

    /////////////////////////////////////////
    $scope.updateTrangThaiDonHang = function (ma, trangThai) {
        let success = true;
        $http.get("/admin/don-hang/update-trang-thai/" + ma + "?trangThai=" + trangThai).then(r => {
            success = true;
        }).catch(e => {
            console.log(e)
            success = false
        })
        return success
    }
    ////////////////////////////////////////////
    $scope.giaoHangNhanh = {
        tokenShop: "954c787d-2876-11ee-96dc-de6f804954c9",
        headers: {headers: {token: "954c787d-2876-11ee-96dc-de6f804954c9"}},
        headersShop: {headers: {token: "954c787d-2876-11ee-96dc-de6f804954c9", ShopId: 4379549}},
        districts: [],
        wards: [],
        getCitys() {
            $http.get("https://online-gateway.ghn.vn/shiip/public-api/master-data/province", this.headers).then(res => {
                this.citys = res.data.data
            }).catch(err => console.log(err))
        },
        getDistricts(idCity) {
            $http.get("https://online-gateway.ghn.vn/shiip/public-api/master-data/district?province_id=" + idCity, this.headers).then(res => {
                this.districts = res.data.data;
            }).catch(err => console.log(err))
        },
        getWards(idDistrict) {
            $http.get("https://online-gateway.ghn.vn/shiip/public-api/master-data/ward?district_id=" + idDistrict, this.headers).then(res => {
                this.wards = res.data.data;
            }).catch(err => console.log(err))
        },
        cityChange(idCity) {
            $http.get("https://online-gateway.ghn.vn/shiip/public-api/master-data/district?province_id=" + idCity, this.headers).then(res => {
                this.districts = res.data.data;
                this.wards.length = 0
            }).catch(err => console.log(err))
            // $scope.chuaXacNhan.detail.quanHuyenCode = this.districts[0].DistrictID +""
        },
        districtChange(idDistrict) {
            $http.get("https://online-gateway.ghn.vn/shiip/public-api/master-data/ward?district_id=" + idDistrict, this.headers).then(res => {
                this.wards = res.data.data;
            }).catch(err => console.log(err))
        },
        getFeeShipped() {
            let data = {
                "service_type_id": 2,
                "to_district_id": parseInt($scope.chuaXacNhan.detail.quanHuyenCode),
                "to_ward_code": $scope.chuaXacNhan.detail.xaPhuongCode,
                "height": 10,
                "length": 10,
                "weight": 200,
                "width": 10
            }
            $http.post("https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/fee", data, this.headersShop).then(res => {
                $scope.chuaXacNhan.detail.phiGiaoHang = res.data.data.total;
            }).catch(err => console.log(err));
        }
    }
    $scope.giaoHangNhanh.getCitys()
    ////////////////////////////////////////////
    $scope.chuaXacNhan = {
        list: [],
        detail: {},
        totalElement: 0,
        totalPage: 0,
        page: 0,
        id: [],
        pages: [],
        init(pageNumber) {
            $http.get("/admin/don-hang/get-by-trangthai?trangThai=2&pageNumber=" + pageNumber).then(r => {
                this.list = r.data.content;
                this.totalElement = r.data.totalElements;
                this.totalPage = r.data.totalPages;
                this.setPageNumbers()
            })
        },
        getList(pageNumber) {
            $scope.trangThaiDonHang = 2
            this.page = pageNumber;
            $http.get("/admin/don-hang/get-by-trangthai?trangThai=2&pageNumber=" + pageNumber).then(r => {
                this.list = r.data.content;
            })
        },
        xacNhanDH(ma) {
            if (!confirm("Xác nhận đơn hàng?")) return;

            $http.get("/admin/don-hang/update-trang-thai/" + ma + "?trangThai=1").then(r => {
                if (this.page == this.totalPage - 1) {
                    if (this.list.length == 1 && this.page > 0) {
                        this.page--;
                    }
                }
                this.init(this.page)
                document.getElementById('checkAllChuaXacNhan').checked = false
            }).catch(e => {
                console.log(e)
            })
        },
        xacNhanDHAll() {
            if (!confirm("Xác nhận đơn hàng?")) return;
            let checkBox = document.getElementsByName('checkChuaXacNhan')
            checkBox.forEach(c => {
                if (c.checked == true) {
                    this.id.push(c.value)
                }
            })

            $http.put("/admin/don-hang/update-trang-thai?trangThai=1", this.id).then(r => {
                if (this.page == this.totalPage - 1) {
                    if (this.list.length == 1 && this.page > 0) {
                        this.page--;
                    }
                }
                this.init(this.page)
                this.id = []
                document.getElementById('checkAllChuaXacNhan').checked = false
            }).catch(e => {
                console.log(e)
            })
        },
        setIdDonHang(id) {
            this.id = []
            this.id.push(id)
        },
        setAllIdDonHang() {
            this.id = []
            let checkBox = document.getElementsByName('checkChuaXacNhan')
            checkBox.forEach(c => {
                if (c.checked == true) {
                    this.id.push(c.value)
                }
            })
        },
        huyDH() {

            if ($scope.lyDo == null || $scope.length == 0 || $scope.lyDo == undefined) {
                $scope.messLyDo = "Không để trống lý do hủy"
                return
            } else if ($scope.lyDo.length == 200) {
                $scope.messLyDo = "Lý do hủy chỉ tối đa 200 ký tự"
                return;
            }

            $http.put("/admin/don-hang/huy-don-hang?lyDo=" + $scope.lyDo, this.id).then(r => {
                if (this.page == this.totalPage - 1) {
                    if (this.list.length == 1 && this.page > 0) {
                        this.page--;
                    }
                }
                this.init(this.page)
                $scope.lyDo = null;
                $scope.messLyDo = "";
                this.id = []
                $('#closeHuy').click()
                document.getElementById('checkAllChuaXacNhan').checked = false
            }).catch(e => {
                console.log(e)
            })
        },
        getDetail(ma) {
            $http.get("/admin/don-hang/" + ma).then(r => {
                this.detail = r.data;
                this.detail.thanhPhoCode = this.detail.thanhPhoCode + ""

                //Lấy quận huyện
                $scope.giaoHangNhanh.getDistricts(this.detail.thanhPhoCode)//hàm lấy quận huyện truyền vào thành phố
                this.detail.quanHuyenCode = this.detail.quanHuyenCode + "" // set selected quận huyện

                $scope.giaoHangNhanh.getWards(this.detail.quanHuyenCode)//hàm lấy xã truyền vào quận huyện
                this.detail.xaPhuongCode = this.detail.xaPhuongCode + "" //set selected xã

                $('#chuaXacNhanDetail').modal('show')
            }).catch(e => console.log(e))

            $http.get("/admin/chi-tiet-don-hang/" + ma).then(r => {
                $scope.chiTietDonHang = r.data;
            }).catch(e => console.log(e))
        },
        capNhat() {
            if (!confirm("Cập nhật thông tin đơn hàng " + this.detail.ma + "?")) {
                return
            }
            let indexCity = $scope.giaoHangNhanh.citys.findIndex(c => c.ProvinceID == this.detail.thanhPhoCode)
            let indexDistrict = $scope.giaoHangNhanh.districts.findIndex(d => d.DistrictID == this.detail.quanHuyenCode)
            let indexWard = $scope.giaoHangNhanh.wards.findIndex(w => w.WardCode == this.detail.xaPhuongCode)

            this.detail.thanhPhoName = $scope.giaoHangNhanh.citys[indexCity] == undefined ? "" : $scope.giaoHangNhanh.citys[indexCity].ProvinceName;
            this.detail.quanHuyenName = $scope.giaoHangNhanh.districts[indexDistrict] == undefined ? "" : $scope.giaoHangNhanh.districts[indexDistrict].DistrictName;
            this.detail.xaPhuongName = $scope.giaoHangNhanh.wards[indexWard] == undefined ? "" : $scope.giaoHangNhanh.wards[indexWard].WardName == undefined
            let data = {
                ma: this.detail.ma,
                nguoiSoHuu: {username: this.detail.nguoiSoHuu},
                tenNguoiNhan: this.detail.tenNguoiNhan,
                soDienThoai: this.detail.soDienThoai,
                email: this.detail.email,
                thanhPhoName: this.detail.thanhPhoName,
                thanhPhoCode: this.detail.thanhPhoCode,
                quanHuyenName: this.detail.quanHuyenName,
                quanHuyenCode: this.detail.quanHuyenCode,
                xaPhuongName: this.detail.xaPhuongName,
                xaPhuongCode: this.detail.xaPhuongCode,
                diaChiChiTiet: this.detail.diaChiChiTiet,
                ngayDatHang: this.detail.ngayDatHang,
                trangThai: this.detail.trangThai,
                ghiChu: this.detail.ghiChu,
                tienGiam: this.detail.tienGiam,
                phiGiaoHang: this.detail.phiGiaoHang,
                trangThaiDetail: this.detail.trangThai
            }
            let chiTietDonHang = [];
            $scope.chiTietDonHang.forEach(c => {
                chiTietDonHang.push({
                    id: c.id,
                    donHangID: this.detail.ma,
                    sanPhamCT: c.idChiTietSanPham,
                    soLuong: c.soLuong,
                    donGia: c.donGia,
                    donGiaSauGiam: c.donGiaSauGiam
                })
            })
            let formData = new FormData();
            formData.append("donHang", new Blob([JSON.stringify(data)], {
                type: 'application/json'
            }))
            formData.append("chiTietDonHang", new Blob([JSON.stringify(chiTietDonHang)], {
                type: 'application/json'
            }))
            $http.put("/admin/don-hang", formData, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            }).then(r => {
                let index = this.list.findIndex(d => d.ma == this.detail.ma)
                this.list[index] = this.detail
            }).catch(e => {
                $scope.er = e.data
                console.log($scope.er)
            })

        },
        setPageNumbers() {
            let numbers = [];
            for (let i = 0; i < this.totalPage; i++) {
                numbers.push(i)
            }
            this.pages = numbers;
        },
        updateSoLuong(idCTSP, soLuong) {
            let index = $scope.chiTietDonHang.findIndex(c => c.idChiTietSanPham == idCTSP)
            let chiTietDonHang = $scope.chiTietDonHang[index]
            $http.get("/admin/san-pham/1/kiem-tra-so-luong/" + idCTSP + "?soLuong=" + soLuong + "&idCTDH=" + (chiTietDonHang.id == undefined ? "" : chiTietDonHang.id)).then(r => {
                $scope.chiTietDonHang[index].soLuong = soLuong
                $scope.getTotalPrice()
            }).catch(e => {
                if (chiTietDonHang.id == undefined) {
                    chiTietDonHang.soLuong = 1
                } else {
                    $http.get("/admin/chi-tiet-don-hang/detail/" + chiTietDonHang.id).then(r => {
                        $scope.chiTietDonHang[index].soLuong = r.data.soLuong
                    }).catch(e => console.log(e))
                }
                alert("số lượng đã vượt quá số lượng sản phẩm!")
            })


        },
        subtractSoLuong(idCTSP) {
            let index = $scope.chiTietDonHang.findIndex(c => c.idChiTietSanPham == idCTSP)
            let chiTietDonHang = $scope.chiTietDonHang[index]
            let soLuong = chiTietDonHang.soLuong - 1


            $http.get("/admin/san-pham/1/kiem-tra-so-luong/" + chiTietDonHang.idChiTietSanPham + "?soLuong=" + soLuong + "&idCTDH=" + "").then(r => {
                $scope.chiTietDonHang[index].soLuong = soLuong
            }).catch(e => {
                alert("số lượng sản phẩm đã đạt giá trị tối thiểu")
            })


        },
        addSoLuong(idCTSP) {
            let index = $scope.chiTietDonHang.findIndex(c => c.idChiTietSanPham == idCTSP)
            let chiTietDonHang = $scope.chiTietDonHang[index]
            let soLuong = chiTietDonHang.soLuong + 1

            $http.get("/admin/san-pham/1/kiem-tra-so-luong/" + chiTietDonHang.idChiTietSanPham + "?soLuong=" + soLuong + "&idCTDH=" + (chiTietDonHang.id == undefined ? "" : chiTietDonHang.id)).then(r => {
                $scope.chiTietDonHang[index].soLuong = soLuong
            }).catch(e => {
                alert("số lượng sản phẩm đã đạt giá trị tối ta")
            })

        },
        removeSanPham(idCTDH) {
            let index = $scope.chiTietDonHang.findIndex(c => c.id == idCTDH)
            $scope.chiTietDonHang.splice(index, 1)
        },
        checkButton() {
            let checkboxs = document.getElementsByName('checkChuaXacNhan')
            let check = true;
            checkboxs.forEach(c => {
                if (c.checked == true) {
                    check = false;
                }
            })
            document.getElementById("huyAll1").disabled = check;
            document.getElementById("xacNhanAll1").disabled = check;
        }
    }
    $scope.chuaXacNhan.init(0)

    $scope.chuaThanhToan = {
        list: [],
        detail: {},
        totalElement: 0,
        totalPage: 0,
        page: 0,
        id: [],
        pages: [],
        init(pageNumber) {
            $http.get("/admin/don-hang/get-by-trangthai?trangThai=5&pageNumber=" + pageNumber).then(r => {
                this.list = r.data.content;
                this.totalElement = r.data.totalElements;
                this.totalPage = r.data.totalPages;
                this.setPageNumbers()
            })
        },
        getList(pageNumber) {
            $scope.trangThaiDonHang = 5
            this.page = pageNumber;
            $http.get("/admin/don-hang/get-by-trangthai?trangThai=5&pageNumber=" + pageNumber).then(r => {
                this.list = r.data.content;
                this.totalElement = r.data.totalElements;
                this.totalPage = r.data.totalPages;
                this.setPageNumbers()
            })
        },
        xacNhanDH(ma) {
            if (!confirm("Xác nhận đơn hàng?")) return;

            $http.get("/admin/don-hang/update-trang-thai/" + ma + "?trangThai=2").then(r => {
                if (this.page == this.totalPage - 1) {
                    if (this.list.length == 1 && this.page > 0) {
                        this.page--;
                    }
                }
                this.init(this.page)
                document.getElementById('checkAllChuaTT').checked = false
            }).catch(e => {
                console.log(e)
            })
        },
        xacNhanDHAll() {
            if (!confirm("Xác nhận đơn hàng?")) return;
            let checkBox = document.getElementsByName('checkChuaTT')
            checkBox.forEach(c => {
                if (c.checked == true) {
                    this.id.push(c.value)
                }
            })

            $http.put("/admin/don-hang/update-trang-thai?trangThai=2", this.id).then(r => {
                if (this.page == this.totalPage - 1) {
                    if (this.list.length == 1 && this.page > 0) {
                        this.page--;
                    }
                }
                this.init(this.page)
                this.id = []
                document.getElementById('checkAllChuaTT').checked = false
            }).catch(e => {
                console.log(e)
            })
        },
        setIdDonHang(id) {
            this.id = []
            this.id.push(id)
        },
        setAllIdDonHang() {
            this.id = []
            let checkBox = document.getElementsByName('checkChuaTT')
            checkBox.forEach(c => {
                if (c.checked == true) {
                    this.id.push(c.value)
                }
            })
        },
        huyDH() {

            if ($scope.lyDo == null || $scope.length == 0 || $scope.lyDo == undefined) {
                $scope.messLyDo = "Không để trống lý do hủy"
                return
            } else if ($scope.lyDo.length == 200) {
                $scope.messLyDo = "Lý do hủy chỉ tối đa 200 ký tự"
                return;
            }

            $http.put("/admin/don-hang/huy-don-hang?lyDo=" + $scope.lyDo, this.id).then(r => {
                if (this.page == this.totalPage - 1) {
                    if (this.list.length == 1 && this.page > 0) {
                        this.page--;
                    }
                }
                this.init(this.page)
                $scope.lyDo = null;
                $scope.messLyDo = "";
                this.id = []
                $('#closeHuy').click()
                document.getElementById('checkAllChuaTT').checked = false
            }).catch(e => {
                console.log(e)
            })
        },
        getDetail(ma) {
            $http.get("/admin/don-hang/" + ma).then(r => {
                this.detail = r.data;
                $scope.chuaXacNhan.detail = r.data
                this.detail.thanhPhoCode = this.detail.thanhPhoCode + ""

                //Lấy quận huyện
                $scope.giaoHangNhanh.getDistricts(this.detail.thanhPhoCode)//hàm lấy quận huyện truyền vào thành phố
                this.detail.quanHuyenCode = this.detail.quanHuyenCode + "" // set selected quận huyện

                $scope.giaoHangNhanh.getWards(this.detail.quanHuyenCode)//hàm lấy xã truyền vào quận huyện
                this.detail.xaPhuongCode = this.detail.xaPhuongCode + "" //set selected xã

                $('#chuaXacNhanDetail').modal('show')
            }).catch(e => console.log(e))

            $http.get("/admin/chi-tiet-don-hang/" + ma).then(r => {
                $scope.chiTietDonHang = r.data;
            }).catch(e => console.log(e))
        },
        setPageNumbers() {
            let numbers = [];
            for (let i = 0; i < this.totalPage; i++) {
                numbers.push(i)
            }
            this.pages = numbers;
        },
        checkButton() {
            let checkboxs = document.getElementsByName('checkChuaTT')
            let check = true;
            checkboxs.forEach(c => {
                if (c.checked == true) {
                    check = false;
                }
            })
            document.getElementById("huyAll5").disabled = check;
            document.getElementById("xacNhanAll5").disabled = check;
        }
    }
    $scope.daXacNhan = {
        list: [],
        detail: {},
        totalElement: 0,
        totalPage: 0,
        page: 0,
        pages: [],
        id: [],
        init() {
            $http.get("/admin/don-hang/get-by-trangthai?trangThai=1").then(r => {
                this.list = r.data.content;
                this.totalElement = r.data.totalElements;
                this.totalPage = r.data.totalPages;
                this.setPageNumbers()
            })
        },
        getList(pageNumber) {
            $scope.trangThaiDonHang = 1
            $scope.daXacNhan.page = pageNumber;
            $http.get("/admin/don-hang/get-by-trangthai?trangThai=1&pageNumber=" + pageNumber).then(r => {
                this.list = r.data.content;
                this.totalElement = r.data.totalElements;
                this.totalPage = r.data.totalPages;
                this.setPageNumbers()
            })
        },
        getDetail(ma) {
            $http.get("/admin/don-hang/" + ma).then(r => {
                $scope.donHang = r.data;
                $('#donHangDetail').modal('show')
            }).catch(e => console.log(e))

            $http.get("/admin/chi-tiet-don-hang/" + ma).then(r => {
                $scope.chiTietDonHang = r.data;
            }).catch(e => console.log(e))
        },
        setPageNumbers() {
            let numbers = [];
            for (let i = 0; i < this.totalPage; i++) {
                numbers.push(i)
            }
            this.pages = numbers;
        },
        chuyenGiao(ma) {
            if (!confirm("Chuyển giao đơn hàng?")) return;

            $http.get("/admin/don-hang/update-trang-thai/" + ma + "?trangThai=3").then(r => {
                if (this.page == this.totalPage - 1) {
                    if (this.list.length == 1 && this.page > 0) {
                        this.page--;
                    }
                }
                this.init(this.page)
                // document.getElementById('checkAllChuaXacNhan').checked = false
            }).catch(e => {
                console.log(e)
            })
        },
        setIdDonHang(id) {
            this.id = []
            this.id.push(id)
        },
        setAllIdDonHang() {
            this.id = []
            let checkBox = document.getElementsByName('checkDaXacNhan')
            checkBox.forEach(c => {
                if (c.checked == true) {
                    this.id.push(c.value)
                }
            })
        },
        huyDH() {
            if ($scope.lyDo == null || $scope.length == 0 || $scope.lyDo == undefined) {
                $scope.messLyDo = "Không để trống lý do hủy"
                return
            } else if ($scope.lyDo.length == 200) {
                $scope.messLyDo = "Lý do hủy chỉ tối đa 200 ký tự"
                return;
            }

            $http.put("/admin/don-hang/huy-don-hang?lyDo=" + $scope.lyDo, this.id).then(r => {
                if (this.page == this.totalPage - 1) {
                    if (this.list.length == 1 && this.page > 0) {
                        this.page--;
                    }
                }
                this.getList(this.page)
                $scope.lyDo = null;
                $scope.messLyDo = "";
                this.id = []
                $('#closeHuy').click()
                document.getElementById('checkAlldaXacNhan').checked = false
            }).catch(e => {
                console.log(e)
            })
        },
        chuyenGiaoDHAll() {
            if (!confirm("Xác nhận đơn hàng?")) return;
            let checkBox = document.getElementsByName('checkDaXacNhan')
            checkBox.forEach(c => {
                if (c.checked == true) {
                    this.id.push(c.value)
                }
            })

            $http.put("/admin/don-hang/update-trang-thai?trangThai=3", this.id).then(r => {
                if (this.page == this.totalPage - 1) {
                    if (this.list.length == 1 && this.page > 0) {
                        this.page--;
                    }
                }
                this.init(this.page)
                this.id = []
                document.getElementById('checkAlldaXacNhan').checked = false
            }).catch(e => {
                console.log(e)
            })
        },
        checkButton() {
            let checkboxs = document.getElementsByName('checkDaXacNhan')
            let check = true;
            checkboxs.forEach(c => {
                if (c.checked == true) {
                    check = false;
                }
            })
            document.getElementById("huyAll2").disabled = check;
            document.getElementById("xacNhanAll2").disabled = check;
        }
    }

    $scope.dangGiao = {
        list: [],
        detail: {},
        totalElement: 0,
        totalPage: 0,
        page: 0,
        pages: [],
        id: [],
        init() {
            $http.get("/admin/don-hang/get-by-trangthai?trangThai=3").then(r => {
                this.list = r.data.content;
                this.totalElement = r.data.totalElements;
                this.totalPage = r.data.totalPages;
                this.setPageNumbers()
            })
        },
        getList(pageNumber) {
            $scope.trangThaiDonHang = 3
            this.page = pageNumber;
            $http.get("/admin/don-hang/get-by-trangthai?trangThai=3&pageNumber=" + pageNumber).then(r => {
                this.list = r.data.content;
                this.totalElement = r.data.totalElements;
                this.totalPage = r.data.totalPages;
                this.setPageNumbers()
            })
        },
        setPageNumbers() {
            let numbers = [];
            for (let i = 0; i < this.totalPage; i++) {
                numbers.push(i)
            }
            this.pages = numbers;
        },
        hoanThanh(ma) {
            if (!confirm("Xác nhận hoàn thành đơn hàng?")) return;

            $http.get("/admin/don-hang/update-trang-thai/" + ma + "?trangThai=4").then(r => {
                if (this.page == this.totalPage - 1) {
                    if (this.list.length == 1 && this.page > 0) {
                        this.page--;
                    }
                }
                this.getList(this.page)
                // document.getElementById('checkAllChuaXacNhan').checked = false
            }).catch(e => {
                console.log(e)
            })
        },
        setIdDonHang(id) {
            this.id = []
            this.id.push(id)
        },
        setAllIdDonHang() {
            let checkBox = document.getElementsByName('checkDangGiao')
            this.id = []
            checkBox.forEach(c => {
                if (c.checked == true) {
                    this.id.push(c.value)
                }
            })
        },
        huyDH() {
            if ($scope.lyDo == null || $scope.length == 0 || $scope.lyDo == undefined) {
                $scope.messLyDo = "Không để trống lý do hủy"
                return
            } else if ($scope.lyDo.length == 200) {
                $scope.messLyDo = "Lý do hủy chỉ tối đa 200 ký tự"
                return;
            }

            $http.put("/admin/don-hang/huy-don-hang?lyDo=" + $scope.lyDo, this.id).then(r => {
                if (this.page == this.totalPage - 1) {
                    if (this.list.length == 1 && this.page > 0) {
                        this.page--;
                    }
                }
                this.getList(this.page)
                $scope.lyDo = null;
                $scope.messLyDo = "";
                this.id = []
                $('#closeHuy').click()
                document.getElementById('checkAllDangGiao').checked = false
            }).catch(e => {
                console.log(e)
            })
        },
        hoanThanhDHAll() {
            if (!confirm("Xác nhận đơn hàng hoàn thành?")) return;
            let checkBox = document.getElementsByName('checkDangGiao')
            checkBox.forEach(c => {
                if (c.checked == true) {
                    this.id.push(c.value)
                }
            })

            $http.put("/admin/don-hang/update-trang-thai?trangThai=4", this.id).then(r => {
                if (this.page == this.totalPage - 1) {
                    if (this.list.length == 1 && this.page > 0) {
                        this.page--;
                    }
                }
                this.getList(this.page)
                this.id = []
                document.getElementById('checkAllDangGiao').checked = false
            }).catch(e => {
                console.log(e)
            })
        },
        checkButton() {
            let checkboxs = document.getElementsByName('checkDangGiao')
            let check = true;
            checkboxs.forEach(c => {
                if (c.checked == true) {
                    check = false;
                }
            })
            document.getElementById("huyAll3").disabled = check;
            document.getElementById("xacNhanAll3").disabled = check;
        }
    }

    $scope.hoanThanh = {
        list: [],
        detail: {},
        totalElement: 0,
        totalPage: 0,
        page: 0,
        pages: [],
        init() {
            $http.get("/admin/don-hang/get-by-trangthai?trangThai=4").then(r => {
                this.list = r.data.content;
                this.totalElement = r.data.totalElements;
                this.totalPage = r.data.totalPages;
                this.setPageNumbers()
            })
        },
        getList(pageNumber) {
            $scope.trangThaiDonHang = 0
            this.page = pageNumber;
            $http.get("/admin/don-hang/get-by-trangthai?trangThai=4&pageNumber=" + pageNumber).then(r => {
                this.list = r.data.content;
                this.totalElement = r.data.totalElements;
                this.totalPage = r.data.totalPages;
                this.setPageNumbers()
            })
        },
        getDetail(ma) {
            $http.get("/admin/don-hang/" + ma).then(r => {
                $scope.donHang = r.data;
                $('#donHangDetail').modal('show')
            }).catch(e => console.log(e))

            $http.get("/admin/chi-tiet-don-hang/" + ma).then(r => {
                $scope.chiTietDonHang = r.data;
            }).catch(e => console.log(e))
        },
        setPageNumbers() {
            let numbers = [];
            for (let i = 0; i < this.totalPage; i++) {
                numbers.push(i)
            }
            this.pages = numbers;
        }
    }

    $scope.huy = {
        list: [],
        detail: {},
        totalElement: 0,
        totalPage: 0,
        page: 0,
        pages: [],
        init() {
            $http.get("/admin/don-hang/get-by-trangthai?trangThai=0").then(r => {
                this.list = r.data.content;
                this.totalElement = r.data.totalElements;
                this.totalPage = r.data.totalPages;
                this.setPageNumbers()
            })
        },
        getList(pageNumber) {
            $scope.trangThaiDonHang = 0
            $scope.daXacNhan.page = pageNumber;
            $http.get("/admin/don-hang/get-by-trangthai?trangThai=0&pageNumber=" + pageNumber).then(r => {
                this.list = r.data.content;
                this.totalElement = r.data.totalElements;
                this.totalPage = r.data.totalPages;
                this.setPageNumbers()
            })
        },
        getDetail(ma) {
            $http.get("/admin/don-hang/" + ma).then(r => {
                $scope.donHang = r.data;
                $('#donHangDetail').modal('show')
            }).catch(e => console.log(e))

            $http.get("/admin/chi-tiet-don-hang/" + ma).then(r => {
                $scope.chiTietDonHang = r.data;
            }).catch(e => console.log(e))
        },
        setPageNumbers() {
            let numbers = [];
            for (let i = 0; i < this.totalPage; i++) {
                numbers.push(i)
            }
            this.pages = numbers;
        }
    }
    //don hang usser
    $scope.donHangChuaXacNhanKh = []
    $scope.donHangUser = function (trangThai) {
        $http.get("/don-hang/get-by-trangThai-khachHang?trangThai=" + trangThai).then(resp => {
            $scope.donHangChuaXacNhanKh = resp.data;
            console.log(resp.data)
        }).catch(err => {
            console.log(err);
        })
    }
    $scope.donHangUser(2)

    $scope.findByMaDonHangUser = function (ma) {
        $http.get("/don-hang/" + ma).then(function (res) {
            const donHang = res.data;
            $scope.maDonHang = donHang.ma
            $scope.nguoiNhan = donHang.tenNguoiNhan
            $scope.soDT = donHang.soDienThoai
            $scope.email = donHang.email
            $scope.ghiChu = donHang.ghiChu
            $scope.diaChiCT = donHang.diaChiChiTiet
            $scope.tienGiam = donHang.tienGiam
            $scope.phiGiaoHang = donHang.phiGiaoHang
            $scope.lyDoHuy = donHang.lyDoHuy
            $scope.chiTietDon = donHang.chiTietDonHang
        })
    }
    $scope.huyDonUser = function (ma) {
        lyDoHuy = $scope.lyDoHuy
        $http.put("/don-hang/huy-don-hang-user?ma=" + ma, lyDoHuy).then(function (res) {
            // location.reload()
            let index = $scope.donHangChuaXacNhanKh.findIndex(d => d.ma == ma)
            $scope.donHangChuaXacNhanKh.splice(index,1);
            alert("Hủy thành công");
        })
    }
    $scope.setMaSanPham = function (maSP){
        console.log(maSP);
        $scope.danhGia.sanPham = maSP
    }
    $scope.danhGia = {}
    $scope.erDanhGia = {}
    $scope.addDanhGia = function (){
        $http.post("/nhan-xet",$scope.danhGia).then(r => {
            $('#danhGia').modal('hide')
            alert("Thành công")
            $scope.danhGia = {}
        }).catch(e => $scope.erDanhGia = e.data)
    }

})