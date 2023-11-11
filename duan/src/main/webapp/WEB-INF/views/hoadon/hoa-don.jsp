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
</head>
<body>
<div>
    <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="hoa-don/hien-thi" role="tab"
               aria-controls="description" aria-selected="true">Thông tin hóa đơn</a>
        </li>

        <li class="nav-item">
            <a class="nav-link" href="/hoa-don/add" role="tab">Thêm hóa đơn chờ</a>
        </li>
    </ul>
</div>
<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="description" role="tabpanel"
         aria-labelledby="description-tab">
        <div class="col-lg-12 grid-margin stretch-card">
            <div class="card">
                <div class="card-body">
                    <div>
                        <h4 class="card-title" style="float: left">Danh sách hóa đơn
                        </h4>
                        <div class="basic-dropdown" style="float: right">
                            <div class="dropdown">
                                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                                    <i class="ti-export btn-icon-prepend"></i>
                                    Xuất Excel
                                </button>
                                <div class="dropdown-menu">
                                    <a href="/hoa-don/export-excel" class="dropdown-item" tabindex="-1">Theo ngày thanh
                                        toán</a>
                                    <a href="/hoa-don/export-excel-ngay-nhan" class="dropdown-item" tabindex="-1">Theo
                                        ngày nhận</a>
                                    <a href="/hoa-don/export-excel-ngay-ship" class="dropdown-item" tabindex="-1">Theo
                                        ngày ship</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br>
                    <br>
                    <div class="loc" style="color:black;">
                        <form:form action="/hoa-don/loc" method="post" modelAttribute="hoaDon">
                            <div class="row" style="margin-top: 10px">
                                <div class="col-md-2">
                                    <div class="form-group row">
                                        <div class="col-sm-12">
                                            <select id="selectKhachHang1" name="khachHang" class="form-control select2"
                                                    style="font-weight: bold; width: 100%">
                                                <option selected disabled>Khách hàng</option>
                                                <c:forEach items="${listKhachHang}" var="khachHang">
                                                    <option value="${khachHang.id}">${khachHang.hoTen}</option>
                                                </c:forEach>
                                            </select>

                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="form-group row">
                                        <div class="col-sm-12">
                                            <select id="selectNhanVien1" name="nhanVien" class="form-control select2"
                                                    style="font-weight: bold; width: 100%">
                                                <option selected disabled>Nhân viên</option>
                                                <c:forEach items="${listNhanVien}" var="nhanVien">
                                                    <option value="${nhanVien.id}">${nhanVien.hoTen}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group row">
                                        <div class="col-sm-12">
                                            <select id="selectDiaChi1" name="diaChi" class="form-control select2"
                                                    style="font-weight: bold; width: 100%">
                                                <option selected disabled>Địa chỉ</option>
                                                <c:forEach items="${listDiaChi}" var="diaChi">
                                                    <option value="${diaChi.id}">${diaChi.diaChi}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="form-group row">
                                        <div class="col-sm-12">
                                            <select id="selectTrangThai1" name="trangThai" class="form-control select2"
                                                    style="font-weight: bold; width: 100%">
                                                <option selected disabled>Trạng thái hóa đơn</option>
                                                <option value="0">Hóa đơn chờ</option>
                                                <option value="1">Hóa đơn đã xác nhận</option>
                                                <option value="3">Hóa đơn chờ thanh toán</option>
                                                <option value="2">Hóa đơn đã thanh toán</option>
                                                    <%--                                                <option value="4">Nhập địa chỉ hoặc chọn</option>--%>
                                                    <%--                                                <option value="5">Nhập địa chỉ hoặc chọn</option>--%>
                                                    <%--                                                <option value="6">Nhập địa chỉ hoặc chọn</option>--%>
                                                    <%--                                                <option value="7">Nhập địa chỉ hoặc chọn</option>--%>
                                                <option value="8">Hóa đơn đã hủy</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group row">
                                        <div class="col-sm-12">
                                            <select name="loaiHoaDon" class="form-control select2"
                                                    style="font-weight: bold; width: 100%" id="selectLoai1">
                                                <option selected disabled>Loại hóa đơn</option>
                                                <option value="0">Tại quầy</option>
                                                <option value="1">Online</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group row">
                                        <label class="col-sm-4 col-form-label">Từ ngày thanh toán:</label>
                                        <div class="col-sm-8">
                                            <input type="date" id="ngayTao" name="startDate" class="form-control"
                                                   placeholder="Từ ngày">
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-4">
                                    <div class="form-group row">
                                        <label class="col-sm-5 col-form-label">Từ ngày nhận:</label>
                                        <div class="col-sm-7">
                                            <input type="date" id="ngayNhan" name="receiveStartDate"
                                                   class="form-control"
                                                   placeholder="Từ ngày">
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-4">
                                    <div class="form-group row">
                                        <label class="col-sm-4 col-form-label">Từ ngày ship:</label>
                                        <div class="col-sm-8">
                                            <input type="date" id="ngayShip" name="shipStartDate" class="form-control"
                                                   placeholder="Từ ngày  ">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group row">
                                        <label class="col-sm-4 col-form-label">Đến ngày thanh toán:</label>
                                        <div class="col-sm-8">
                                            <input type="date" id="ngayTao1" name="endDate" class="form-control"
                                                   placeholder="Đến ngày">
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-4">
                                    <div class="form-group row">
                                        <label class="col-sm-5 col-form-label">Đến ngày nhận:</label>
                                        <div class="col-sm-7">
                                            <input type="date" id="ngayNhan1" name="receiveEndDate" class="form-control"
                                                   placeholder="Đến ngày">
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-4">
                                    <div class="form-group row">
                                        <label class="col-sm-4 col-form-label">Đến ngày ship:</label>
                                        <div class="col-sm-8">
                                            <input type="date" id="ngayShip1" name="shipEndDate" class="form-control"
                                                   placeholder="Đến ngày  ">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div align="center">
                                <BUTTON type="submit" class="btn btn-warning" style="" id="bt">
                                    Lọc hóa đơn
                                </BUTTON>
                            </div>
                        </form:form>
                    </div>
                    <br>
                    <div class="search">
                        <form action="/hoa-don/search" method="post">
                            <div class="input-group" style="width: 30%; float: right">
                                <input type="text" class="form-control" placeholder="Bạn tìm gì..."
                                       aria-label="Bạn tìm gì..." name="search">
                                <div class="input-group-append">
                                    <button class="btn btn-sm btn-primary" type="submit">Search</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <%--           kết thúc tìm kiếm         --%>
                    <div class="table-responsive">
                        <table id="example" class="display" style="color: black; width: 2000px">
                            <thead>
                            <tr>
                                <th>Mã hóa đơn</th>
                                <th>Ngày Tạo</th>
                                <th>Tên khách hàng</th>
                                <th>Tên nhân viên</th>
                                <th>Địa chỉ</th>
                                <%--                                <th>Điểm quy đổi</th>--%>
                                <th>SĐT</th>
                                <th>Tổng tiền</th>
                                <th>Trạng thái</th>
                                <th>Loại HĐ</th>
                                <th>Hình thức thanh toán</th>
                                <th>Ngày Thanh Toán</th>
                                <th>Ngày nhận</th>
                                <th>Ngày ship</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <i class="mdi mdi-border-color"></i>
                            <c:forEach items="${listHoaDon}" var="hoaDon">
                                <tr>
                                    <td>${hoaDon.ma}</td>
                                    <td>${hoaDon.ngayTao}</td>
                                    <td>${hoaDon.khachHang.hoTen}</td>
                                    <td>${hoaDon.nhanVien.hoTen}</td>
                                    <td>${hoaDon.diaChi.diaChi}</td>

                                        <%--                                    <td>${hoaDon.quyDoi.soTienQuyDoi}</td>--%>
                                    <td>${hoaDon.sdt}</td>
                                    <td>${hoaDon.tongTien}</td>
                                    <td>
                                        <c:if test="${hoaDon.tinhTrang == 0}">Đang chờ</c:if>
                                        <c:if test="${hoaDon.tinhTrang == 1}">Đã xác nhận</c:if>
                                        <c:if test="${hoaDon.tinhTrang == 2}">Đã thanh toán</c:if>
                                        <c:if test="${hoaDon.tinhTrang == 3}">Chờ thanh toán</c:if>
                                        <c:if test="${hoaDon.tinhTrang == 4}">Chờ vận chuyển</c:if>
                                        <c:if test="${hoaDon.tinhTrang == 5}">Đang vận chuyển</c:if>
                                        <c:if test="${hoaDon.tinhTrang == 6}">Vận chuyển hoàn tất</c:if>
                                        <c:if test="${hoaDon.tinhTrang == 7}">Giao trễ</c:if>
                                        <c:if test="${hoaDon.tinhTrang == 8}">Đã hủy</c:if>
                                    </td>
                                    <td><c:if test="${hoaDon.loai == 1}">HĐ online</c:if>
                                        <c:if test="${hoaDon.loai == 0}">HĐ quầy</c:if>
                                    </td>
                                    <td>
                                        <c:if test="${hoaDon.hinhThucThanhToan == 1}">Online</c:if>
                                        <c:if test="${hoaDon.hinhThucThanhToan == 0}">Tiền mặt</c:if>
                                    </td>
                                    <td>${hoaDon.ngayThanhToan}</td>
                                    <td>${hoaDon.ngayNhan}</td>
                                    <td>${hoaDon.ngayShip}</td>
                                    <td>
                                        <a href="/hoa-don/detail/${hoaDon.id}" class="btn btn-warning btn-icon-text"
                                           tabindex="-1"
                                           role="button"
                                           onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                            <i class="ti-file btn-icon-prepend"></i>
                                            Detail</a>
                                        <c:if test="${hoaDon.tinhTrang == 0}">
                                            <a href="/hoa-don/huy/${hoaDon.id}"
                                               class="btn btn-danger btn-icon-text"
                                               tabindex="-1"
                                               role="button"
                                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                <i class="fas fa-times-circle"></i>
                                                Hủy</a>
                                            <a href="/hoa-don/view-update/${hoaDon.id}"
                                               class="btn btn-info btn-icon-text"
                                               tabindex="-1"
                                               role="button"
                                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                <i class="fas fa-times-circle"></i>
                                                Update thông tin</a>
                                        </c:if>
                                        <c:if test="${hoaDon.tinhTrang == 1}">
                                            <a href="/hoa-don/huy/${hoaDon.id}"
                                               class="btn btn-danger btn-icon-text"
                                               tabindex="-1"
                                               role="button"
                                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                <i class="fas fa-times-circle"></i>
                                                Hủy</a>
                                            <a href="/hoa-don/view-update/${hoaDon.id}"
                                               class="btn btn-info btn-icon-text"
                                               tabindex="-1"
                                               role="button"
                                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                <i class="fas fa-times-circle"></i>
                                                Update thông tin</a>
                                        </c:if>
                                        <c:if test="${hoaDon.tinhTrang == 2}">
                                            <a href="/hoa-don/xuat-pdf/${hoaDon.id}"
                                               class="btn btn-outline-success btn-icon-text"
                                               tabindex="-1"
                                               role="button"
                                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                <i class="ti-file btn-icon-prepend"></i>
                                                Xuất PDF</a>
                                        </c:if>
                                        <c:if test="${hoaDon.tinhTrang == 3}">
                                            <a href="/hoa-don/huy/${hoaDon.id}"
                                               class="btn btn-danger btn-icon-text"
                                               tabindex="-1"
                                               role="button"
                                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                <i class="fas fa-times-circle"></i>
                                                Hủy</a>
                                            <a href="/hoa-don/view-update/${hoaDon.id}"
                                               class="btn btn-info btn-icon-text"
                                               tabindex="-1"
                                               role="button"
                                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                                <i class="fas fa-times-circle"></i>
                                                Update thông tin</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <br>
                    <br>
                    <%--phân trang--%>
                    <%--                    <c:if test="${dem==0}">--%>
                    <%--                        <div align="center">--%>
                    <%--                            <div class="btn-group" role="group" aria-label="Basic example">--%>
                    <%--                                <ul class="pagination justify-content-center pagination-lg">--%>
                    <%--                                    <li class="page-item"><a class="page-link" href="/hoa-don/hien-thi?pageNum=0"><</a>--%>
                    <%--                                    </li>--%>
                    <%--                                    <c:forEach begin="1" end="${total}" varStatus="status">--%>
                    <%--                                        <li class="page-item">--%>
                    <%--                                            <a href="${pageContext.request.contextPath}/hoa-don/hien-thi?pageNum=${status.index -1}"--%>
                    <%--                                               class="page-link">${status.index}</a>--%>
                    <%--                                        </li>--%>
                    <%--                                    </c:forEach>--%>
                    <%--                                    <li class="page-item"><a class="page-link"--%>
                    <%--                                                             href="/hoa-don/hien-thi?pageNum=${total-1}">></a>--%>
                    <%--                                    </li>--%>
                    <%--                                </ul>--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>
                    <%--                    </c:if>--%>
                    <%--                    <c:if test="${dem==1}">--%>
                    <%--                        <div align="center">--%>
                    <%--                            <div class="btn-group" role="group" aria-label="Basic example">--%>
                    <%--                                <ul class="pagination justify-content-center pagination-lg">--%>
                    <%--                                    <li class="page-item"><a class="page-link" href="/hoa-don/search?pageNum=0"><</a>--%>
                    <%--                                    </li>--%>
                    <%--                                    <c:forEach begin="1" end="${total}" varStatus="status">--%>
                    <%--                                        <li class="page-item">--%>
                    <%--                                            <a href="${pageContext.request.contextPath}/hoa-don/search?pageNum=${status.index -1}"--%>
                    <%--                                               class="page-link">${status.index}</a>--%>
                    <%--                                        </li>--%>
                    <%--                                    </c:forEach>--%>
                    <%--                                    <li class="page-item"><a class="page-link"--%>
                    <%--                                                             href="/hoa-don/search?pageNum=${total-1}">></a>--%>
                    <%--                                    </li>--%>
                    <%--                                </ul>--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>
                    <%--                    </c:if>--%>
                    <%--                    <c:if test="${dem==2}">--%>
                    <%--                        <div align="center">--%>
                    <%--                            <div class="btn-group" role="group" aria-label="Basic example">--%>
                    <%--                                <ul class="pagination justify-content-center pagination-lg">--%>
                    <%--                                    <li class="page-item"><a class="page-link" href="/hoa-don/loc?pageNum=0"><</a></li>--%>
                    <%--                                    <c:forEach begin="1" end="${total}" varStatus="status">--%>
                    <%--                                        <li class="page-item">--%>
                    <%--                                            <a href="${pageContext.request.contextPath}/hoa-don/loc?pageNum=${status.index -1}"--%>
                    <%--                                               class="page-link">${status.index}</a>--%>
                    <%--                                        </li>--%>
                    <%--                                    </c:forEach>--%>
                    <%--                                    <li class="page-item"><a class="page-link"--%>
                    <%--                                                             href="/hoa-don/loc?pageNum=${total-1}">></a>--%>
                    <%--                                    </li>--%>
                    <%--                                </ul>--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>
                    <%--                    </c:if>--%>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/select2.full.min.js"></script>
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
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</html>
