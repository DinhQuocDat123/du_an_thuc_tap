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
    <!-- Favicon icon -->
</head>
<body>
<div>
    <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
        <li class="nav-item">
            <a class="nav-link" href="/chi-tiet-san-pham/hien-thi" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Thông tin chi tiết
                sản phẩm</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
               aria-controls="description" aria-selected="true" role="tab">Sản phẩm đã xóa</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/chi-tiet-san-pham/view-add" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Thêm mới chi tiết
                sản phẩm</a>
        </li>
    </ul>
</div>
<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="description" role="tabpanel"
         aria-labelledby="description-tab">

        <div class="col-lg-12 grid-margin stretch-card">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title" style="float: left">Danh sách Chi tiết sản phẩm đã xóa</h4>
                    <%--            Tìm kiếm               --%>
                    <form action="/chi-tiet-san-pham/search-da-xoa" method="post">
                        <div class="input-group" style="width: 30%; float: right">
                            <input type="text" class="form-control" placeholder="Bạn tìm gì..."
                                   aria-label="Bạn tìm gì..." name="search">
                            <div class="input-group-append">
                                <button class="btn btn-sm btn-primary" type="submit">Search</button>
                            </div>
                        </div>
                        <div style="float: right">
                            <a href="/chi-tiet-san-pham/khoi-phuc-tat-ca" class="btn btn-danger btn-icon-text"
                               tabindex="-1"
                               role="button"
                               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                <i class="ti-reload btn-icon-prepend"></i>
                                Khôi phục tất cả</a>
                        </div>


                    </form>
                    <%--           kết thúc tìm kiếm         --%>
                    <div class="table-responsive">
                        <table id="example" class="display" style="color: black;width: 2000px">
                            <thead>
                            <tr>
                                <th scope="col">Ảnh sản phẩm</th>
                                <th scope="col">Ngày tạo</th>
                                <th scope="col">Ngày cập nhật</th>
                                <th scope="col">Tên sản phẩm</th>
                                <th scope="col">Màu sắc</th>
                                <th scope="col">Chip</th>
                                <th scope="col">Ram</th>
                                <th scope="col">Rom</th>
                                <th scope="col">Pin</th>
                                <th scope="col">Giá bán</th>
                                <th scope="col">Tình trạng</th>
                                <th scope="col">Năm bảo hành</th>
                                <th scope="col">Số lượng tồn</th>
                                <th scope="col">Mô tả</th>
                                <th scope="col">Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <i class="mdi mdi-border-color"></i>
                            <c:forEach items="${listCTSP}" var="ctsp" varStatus="index">


                                <tr>
                                    <td align="center">
                                        <img src="/uploads/${ctsp.urlAnh}" width="40" height="40">
                                    </td>
                                    <td>${ctsp.ngayTao}</td>
                                    <td>${ctsp.ngayCapNhat}</td>
                                    <td>${ctsp.sanPham.ten}</td>
                                    <td>${ctsp.mauSac.ten}</td>
                                    <td>${ctsp.chip.ten}</td>
                                    <td>${ctsp.ram.dungLuong}</td>
                                    <td>${ctsp.rom.dungLuong}</td>
                                    <td>${ctsp.pin.dungLuongPin.thongSo}</td>
                                    <td>${ctsp.giaBan} VND</td>
                                    <td>${ctsp.tinhTrang==0?"Còn kinh doanh":"Ngừng kinh doanh"}</td>
                                    <td>${ctsp.namBaoHanh}</td>
                                    <td>${ctsp.soLuong}</td>
                                    <td>${ctsp.moTa}</td>
                                    <td>

                                        <a href="/chi-tiet-san-pham/khoi-phuc/${ctsp.id}"
                                           class="btn btn-danger btn-icon-text"
                                           tabindex="-1"
                                           role="button"
                                           onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                            <i class="ti-reload btn-icon-prepend"></i>
                                            Khôi phục</a>
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
</body>
</html>
