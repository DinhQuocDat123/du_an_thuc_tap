<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Focus - Bootstrap Admin Dashboard </title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.min.css"/>
    <style>
        .stepwizard-step p {
            margin-top: 10px;

        }

        .stepwizard-row {
            display: table-row;

        }

        .stepwizard {
            display: table;
            width: 100%;
            position: relative;

        }

        .stepwizard-step button[disabled] {
            opacity: 1 !important;
            filter: alpha(opacity=100) !important;
        }

        .stepwizard-row:before {
            top: 14px;
            bottom: 0;
            position: absolute;
            content: " ";
            width: 100%;
            height: 7px;
            background-color: red;
            z-order: 0;


        }

        .stepwizard-step-linear a:active .btn-circle {
            background-color: red;
        }

        .stepwizard-step {
            display: table-cell;
            text-align: center;
            position: relative;
        }

        .btn-circle {
            width: 30px;
            height: 30px;
            text-align: center;
            padding: 6px 0;
            font-size: 12px;
            line-height: 1.428571429;
            border-radius: 15px;
            background-color: #999999;
        }

        .stepwizard-step-linear {

            font-size: 20px;
            color: black;
        }
    </style>
</head>
<body>
<div>
    <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
        <li class="nav-item">
            <a href="/don-hang/hien-thi" class="nav-link"
               tabindex="-1"
               role="button">
                Trang Hóa đơn</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
               aria-controls="description" aria-selected="true">Xem đơn hàng</a>
        </li>
    </ul>
</div>
<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="review" role="tabpanel" aria-labelledby="review-tab">
        <div class="col-12 grid-margin">
            <h3 style="text-align: center">Hóa đơn</h3>
            <div class="col-lg-12 grid-margin stretch-card">
                <div class="card">
                    <div>
                        <div class="card-body">
                            <div>
                                <div class="container mt-5">
                                    <div class="stepwizard stepwizard-linear col-12">
                                        <div class="stepwizard-row setup-panel">
                                            <c:if test="${donHang.tinhTrang == 0}">
                                                <div class="stepwizard-step">
                                                    <a href="#step-1" type="button"
                                                       class="stepwizard-step-linear btn btn-primary btn-circle">0</a>
                                                    <p>Đang chờ</p>
                                                </div>
                                            </c:if>
                                            <c:if test="${donHang.tinhTrang == 1}">
                                                <div class="stepwizard-step">
                                                    <a href="#step-2" type="button"
                                                       class="stepwizard-step-linear btn btn-default btn-circle"
                                                       disabled="disabled">1</a>
                                                    <p>Đã xác nhận</p>
                                                </div>
                                            </c:if>
                                            <c:if test="${donHang.tinhTrang == 2}">
                                                <div class="stepwizard-step">
                                                    <a href="#step-3" type="button"
                                                       class="stepwizard-step-linear btn btn-default btn-circle"
                                                       disabled="disabled">2</a>
                                                    <p>Đã thanh toán</p>
                                                </div>
                                            </c:if>
                                            <c:if test="${donHang.tinhTrang == 3}">
                                                <div class="stepwizard-step">
                                                    <a href="#step-4" type="button"
                                                       class="stepwizard-step-linear btn btn-default btn-circle"
                                                       disabled="disabled">3</a>
                                                    <p>Chờ thanh toán</p>
                                                </div>
                                            </c:if> <c:if test="${donHang.tinhTrang == 4}">
                                            <div class="stepwizard-step">
                                                <a href="#step-5" type="button"
                                                   class="stepwizard-step-linear btn btn-default btn-circle"
                                                   disabled="disabled">4</a>
                                                <p>Chờ vận chuyển</p>
                                            </div>
                                        </c:if>
                                            <c:if test="${donHang.tinhTrang == 5}">
                                                <div class="stepwizard-step">
                                                    <a href="#step-6" type="button"
                                                       class="stepwizard-step-linear btn btn-default btn-circle"
                                                       disabled="disabled">5</a>
                                                    <p>Đang vận chuyển</p>
                                                </div>
                                            </c:if>
                                            <c:if test="${donHang.tinhTrang == 6}">
                                                <div class="stepwizard-step">
                                                    <a href="#step-7" type="button"
                                                       class="stepwizard-step-linear btn btn-default btn-circle"
                                                       disabled="disabled">6</a>
                                                    <p>Vận chuyển hoàn tất</p>
                                                </div>
                                            </c:if>
                                            <c:if test="${donHang.tinhTrang == 7}">
                                                <div class="stepwizard-step">
                                                    <a href="#step-8" type="button"
                                                       class="stepwizard-step-linear btn btn-default btn-circle"
                                                       disabled="disabled">7</a>
                                                    <p>Giao trễ</p>
                                                </div>
                                            </c:if>
                                            <c:if test="${donHang.tinhTrang == 8}">
                                                <div class="stepwizard-step">
                                                    <a href="#step-9" type="button"
                                                       class="stepwizard-step-linear btn btn-default btn-circle"
                                                       disabled="disabled">8</a>
                                                    <p>Đã hủy</p>
                                                </div>
                                            </c:if>
                                        </div>
                                    </div>
                                    <br><br>
                                    <form:form action="/hoa-don/detail/${donHang.id}" method="get"
                                               modelAttribute="donHang">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-sm-3 col-form-label">Mã:</label>
                                                    <div class="col-sm-9">
                                                        <form:input class="form-control" id="form-control"
                                                                    readonly="true"
                                                                    placeholder=""
                                                                    path="ma"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-sm-3 col-form-label">Khách hàng:
                                                    </label>
                                                    <div class="col-sm-9">
                                                        <input type="text" class="form-control" id="khachHangInput"
                                                               name="khachHang"
                                                               value="${donHang.khachHang.hoTen}" readonly>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-sm-3 col-form-label">Nhân viên:
                                                    </label>
                                                    <div class="col-sm-9">
                                                        <input type="text" class="form-control" id="nhanVienInput"
                                                               name="nhanVien"
                                                               value="${donHang.nhanVien.hoTen}" readonly>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-sm-3 col-form-label">SĐT:
                                                    </label>
                                                    <div class="col-sm-9">
                                                        <form:input class="form-control" placeholder="" readonly="true"
                                                                    path="sdt"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-sm-3 col-form-label">Địa chỉ:</label>
                                                    <div class="col-sm-9">
                                                        <input type="text" class="form-control" id="diaChiInput"
                                                               name="diaChi"
                                                               value="${donHang.diaChi.diaChi}" readonly>
                                                    </div>

                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-sm-3 col-form-label">Hình thức TT:
                                                    </label>
                                                    <div class="col-sm-9">
                                                        <form:input class="form-control" placeholder="" readonly="true"
                                                                    path="hinhThucThanhToan"
                                                                    value="${donHang.hinhThucThanhToan == 0 ? 'Tiền mặt' : 'Chuyển khoản'}"
                                                        />
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-sm-3 col-form-label">Tổng tiền:
                                                    </label>
                                                    <div class="col-sm-9">
                                                        <form:input class="form-control" placeholder="" readonly="true"
                                                                    path="tongTien"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-sm-3 col-form-label">Ngày tạo:
                                                        <div id="tb" style="color: crimson;float: right"></div>
                                                    </label>
                                                    <div class="col-sm-9">
                                                        <form:input readonly="true" class="form-control" type="date"
                                                                    value="${donHang.ngayTao}"
                                                                    placeholder=""
                                                                    path="ngayTao"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-sm-3 col-form-label">Ngày nhận:
                                                        <div style="color: crimson;float: right"></div>
                                                    </label>
                                                    <div class="col-sm-9">
                                                        <form:input readonly="true" class="form-control" type="date"
                                                                    value="${donHang.ngayNhan}"
                                                                    placeholder=""
                                                                    path="ngayNhan"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group row">
                                                    <label class="col-sm-3 col-form-label">Ngày ship:
                                                        <div style="color: crimson;float: right"></div>
                                                    </label>
                                                    <div class="col-sm-9">
                                                        <form:input readonly="true" class="form-control" type="date"
                                                                    value="${donHang.ngayShip}"
                                                                    placeholder=""
                                                                    path="ngayShip"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        </form>
                                    </form:form>
                                </div>
                            </div>
                            <br>
                            <h3 style="text-align: center;">Hóa đơn chi tiết</h3>
                            <br>
                            <div class="search">
                                <div class="input-group" style="width: 30%; float: right">
                                    <input type="text" class="form-control" placeholder="Bạn tìm gì..."
                                           aria-label="Bạn tìm gì..." name="search" id="search">
                                    <div class="input-group-append">
                                        <button class="btn btn-sm btn-primary" type="button" id="button-search">Search
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="table-responsive">
                                <table id="example" class="display">
                                    <thead>
                                    <tr>
                                        <th>Tên Sản Phẩm</th>
                                        <th>Ảnh</th>
                                        <th>Hãng</th>
                                        <th>Màu Sắc</th>
                                        <th>Ram</th>
                                        <th>Dung Lượng Bộ Nhớ</th>
                                        <th>Số IMEI</th>
                                        <th>Đơn Giá</th>
                                        <th>Thành tiền</th>
                                    </tr>
                                    </thead>
                                    <tbody id="table-search">
                                    <i class="mdi mdi-border-color"></i>
                                    <c:forEach items="${listHoaDonChiTiet}" var="hdct">
                                        <tr>
                                            <td>${hdct.imei.chiTietSanPham.sanPham.ten}</td>
                                            <td align="center">
                                                <img src="/uploads/${hdct.imei.chiTietSanPham.urlAnh}" width="40"
                                                     height="40">
                                            </td>
                                            <td>${hdct.imei.chiTietSanPham.sanPham.hangSanPham.ten}</td>
                                            <td>${hdct.imei.chiTietSanPham.mauSac.ten}</td>
                                            <td>${hdct.imei.chiTietSanPham.ram.dungLuong}</td>
                                            <td>${hdct.imei.chiTietSanPham.rom.dungLuong}</td>
                                            <td>${hdct.imei.soImei}</td>
                                            <td>${hdct.donGia}</td>
                                            <td>${hdct.donGia * hdct.soLuong}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"
        integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
<script>
    $('button[id^="button-search"]').on('click', async function (e) {
        const btn = $(this);
        const search = $("#search").val();
        const url = "http://localhost:8080/don-hang/search-hdct?search=" + search;
        try {
            const resp = await fetch(url);
            const data = await resp.json();
            console.log(data)
            // Hiển thị dữ liệu tìm kiếm
            let html = ``;
            for (let i = 0; i < data.length; i++) {
                const hdct = data[i];
                const tr = `
            <tr>
                <td>` + hdct.imei.chiTietSanPham.sanPham.ten + `</td>
                <td align="center"><img src="/uploads/` + hdct.imei.chiTietSanPham.urlAnh + `" width="40" height="40"></td>
                <td>` + hdct.imei.chiTietSanPham.sanPham.hangSanPham.ten + `</td>
                <td>` + hdct.imei.chiTietSanPham.mauSac.ten + `</td>
                <td>` + hdct.imei.chiTietSanPham.ram.dungLuong + `</td>
                <td>` + hdct.imei.chiTietSanPham.rom.dungLuong + `</td>
                <td>` + hdct.imei.soImei + `</td>
                <td>` + hdct.donGia + `</td>
                <td>` + hdct.donGia * hdct.soLuong + `</td>
            </tr>
            `;
                html += tr;
            }
            $("#table-search").html(html);
        } catch (err) {
            console.error(err)
        }
    });
</script>
</html>
