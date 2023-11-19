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
        body {
            margin: 0;
        }
        .status-bar {
            display: flex;
            justify-content: center; /* Chuyển thanh trạng thái ra giữa */
            background-color:#DDDDDD;
            padding: 10px;
            border-radius: 50px 50px 50px 50px;
            margin: 0 auto; /* Đưa thanh trạng thái vào giữa trang */
            max-width: 900px; /* Đặt chiều rộng tối đa của thanh trạng thái */
            border: 1px solid black;
        }

        .status {
            flex: 1;
            text-align: center;
        }
        .pill {
            border: 2px solid blue;
            display: inline-block;
            padding: 8px 16px;
            border-radius: 20px;
            font-size: 14px;
            font-weight: bold;
            color: #000;
            margin-right: 1cm; /* Khoảng cách giữa các viên thuốc */
            background-color: white; /* Màu xám mặc định cho tất cả các trạng thái */
        }

        .status-bar .status:hover .pill.default {
            background-color: #00ff00;
        }

        .pending { color: black; background-color: #00ff00; } /* Màu xanh cho trạng thái Đang chờ */
        .confirmed { color: black; background-color: #00ff00; } /* Màu xanh cho trạng thái Đã xác nhận */
        .waiting { color: black; background-color: #00ff00; } /* Màu xanh cho trạng thái Chờ thanh toán */
        .paid {color: black; background-color: #00ff00; } /* Màu xanh cho trạng thái Đã thanh toán */
        .canceled { color: black; background-color: #00ff00; } /* Màu xanh cho trạng thái Đã hủy */
        .default { color: black; }
        .col-sm-3{
            color: black;
            font-size: 15px;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div>
    <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
        <li class="nav-item">
            <a href="/hoa-don/hien-thi" class="nav-link"
               tabindex="-1"
               role="button">
                Trang Hóa đơn</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
               aria-controls="description" aria-selected="true">Xem hóa đơn</a>
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
                            <div class="container mt-5">
                                <div class="status-bar">
                                    <div class="status">
                                        <div class="pill ${hoaDon.tinhTrang == 0 ? 'pending' : 'default'}">Đang chờ</div>
                                    </div>
                                    <div class="status">
                                        <div class="pill ${hoaDon.tinhTrang == 1 ? 'confirmed' : 'default'}">Đã xác nhận</div>
                                    </div>
                                    <div class="status">
                                        <div class="pill ${hoaDon.tinhTrang == 3 ? 'waiting' : 'default'}">Chờ thanh toán</div>
                                    </div>
                                    <div class="status">
                                        <div class="pill ${hoaDon.tinhTrang == 2 ? 'paid' : 'default'}">Đã thanh toán</div>
                                    </div>
                                    <div class="status">
                                        <div class="pill ${hoaDon.tinhTrang == 8 ? 'canceled' : 'default'}">Đã hủy</div>
                                    </div>
                                </div>

                                <br><br>
                                <form:form action="/hoa-don/update/${hoaDon.id}" method="post"
                                           modelAttribute="hoaDon">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <label class="col-sm-3 col-form-label">Mã:</label>
                                                <div class="col-sm-9">
                                                    <form:input class="form-control" id="form-control" readonly="true"
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
                                                    <form:select path="khachHang" items="${listKhachHang}"
                                                                 class="form-control"
                                                                 itemLabel="hoTen"
                                                                 itemValue="id"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <label class="col-sm-3 col-form-label">SĐT:
                                                </label>
                                                <div class="col-sm-9">
                                                    <form:input class="form-control" placeholder=""
                                                                path="sdt"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <label class="col-sm-3 col-form-label">Tổng tiền:
                                                </label>
                                                <div class="col-sm-9">
                                                    <form:input class="form-control" placeholder=""
                                                                path="tongTien" value="${tong}" readonly="true"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <label class="col-sm-3 col-form-label">Ngày tạo:
                                                    <div id="tb" style="color: crimson;float: right"></div>
                                                </label>
                                                <div class="col-sm-9">
                                                    <form:input class="form-control" type="date"
                                                                value="${hoaDon.ngayTao}"
                                                                placeholder=""
                                                                path="ngayTao"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <label class="col-sm-3 col-form-label">Ngày nhận:
                                                    <div style="color: crimson;float: right"></div>
                                                </label>
                                                <div class="col-sm-9">
                                                    <form:input class="form-control" type="date"
                                                                value="${hoaDon.ngayNhan}"
                                                                placeholder=""
                                                                path="ngayNhan"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <label class="col-sm-3 col-form-label">Ngày ship:
                                                    <div style="color: crimson;float: right"></div>
                                                </label>
                                                <div class="col-sm-9">
                                                    <form:input class="form-control" type="date"
                                                                value="${hoaDon.ngayShip}"
                                                                placeholder=""
                                                                path="ngayShip"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <label class="col-sm-3 col-form-label">Trạng thái:
                                                </label>
                                                <div class="col-sm-9">
                                                    <form:select path="tinhTrang" class="form-control">
                                                        <form:option value="0">Đang chờ</form:option>
                                                        <form:option value="1">Đã xác nhận</form:option>
                                                        <form:option value="3">Chờ thanh toán</form:option>
                                                        <form:option value="2">Đã thanh toán</form:option>
                                                        <form:option value="8">Đã hủy</form:option>
                                                    </form:select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div style="text-align: center">
                                        <button type="submit" class="btn btn-primary mr-2"
                                                onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                            SAVE
                                        </button>
                                    </div>

                                </form:form>

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
                                        <c:if test="${hoaDon.tinhTrang == 0 || hoaDon.tinhTrang==1}">
                                            <th></th>
                                        </c:if>

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
                                            <td>
                                                <button class="btn btn-warning btn-icon-text"><a
                                                        href="/hoa-don/delete-hoa-don-chi-tiet/${hdct.id}"
                                                        style="text-decoration: none">Xóa sản phẩm</a>
                                                </button>
                                            </td>
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
        const url = "http://localhost:8080/hoa-don/search-hdct-update?search=" + search;
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
                <td>
                    <button class="btn btn-warning btn-icon-text"><a
                    href="/hoa-don/delete-hoa-don-chi-tiet/"` + hdct.id + `
                    style="text-decoration: none">Xóa sản phẩm</a>
                    </button>
                </td>
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
<script>

    $('#selectKhachHang1').select2({
        theme: 'bootstrap-5'
    });
    $('#selectNhanVien1').select2({
        theme: 'bootstrap-5'
    });
    $('#selectDiaChi1').select2({
        theme: 'bootstrap-5'
    });

</script>
<%--<script>--%>
<%--   var selectElement = document.getElementById('tinhTrang');--%>
<%--   var statusBar = document.querySelector('.status-bar');--%>

<%--    selectElement.addEventListener('change', (event) => {--%>
<%--       var selectedValue = event.target.value;--%>

<%--        for (const statusElement of statusBar.querySelectorAll('.status')) {--%>
<%--            const pillElement = statusElement.querySelector('.pill');--%>
<%--            pillElement.classList.remove('pending', 'confirmed', 'waiting', 'paid', 'canceled');--%>

<%--            if (statusElement.dataset.status === selectedValue) {--%>
<%--                pillElement.classList.add(statusElement.dataset.status);--%>
<%--            }--%>
<%--        }--%>
<%--    });--%>
<%--</script>--%>
</html>
