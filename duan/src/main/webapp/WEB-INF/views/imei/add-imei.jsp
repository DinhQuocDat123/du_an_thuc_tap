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
    <title>GPhoneS Store </title>
    <!-- Favicon icon -->
</head>
<body>
<div>
    <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
        <li class="nav-item">
            <a class="nav-link" href="/imei/hien-thi" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                Thông tin Imei</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
               aria-controls="description" aria-selected="true">Thêm thông tin Imei</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/imei/hien-thi-da-ban" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Imei đã bán</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/imei/hien-thi-da-xoa" role="tab"
               onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">Imei đã xoá</a>
        </li>
    </ul>
</div>
<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="description" role="tabpanel"
         aria-labelledby="description-tab">
        <form:form action="/imei/add" method="post" modelAttribute="imei">
            <div class="col-lg-12 grid-margin stretch-card">
                <div class="card">
                    <div class="card-body" style="color: black">
                        <form class="forms-sample">
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-11">
                                        <form:select path="chiTietSanPham" class="form-control"
                                                     cssStyle="font-weight: bold; width: 100%" id="selectSanPham">
                                            <option selected disabled>Sản phẩm</option>
                                            <c:forEach items="${listCTSP}" var="ctsp">
                                                <option value="${ctsp.id}">${ctsp.sanPham.ten} - ${ctsp.mauSac.ten}
                                                    - ${ctsp.ram.dungLuong}- ${ctsp.rom.dungLuong}</option>
                                            </c:forEach>
                                        </form:select>
                                    </div>
                                    <div class="col-1">
                                        <a type="button" href="/chi-tiet-san-pham/view-add">
                                            <img src="/uploads/plus.png">
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <form:label class="form-label" path="ma">Ma:</form:label>
                                <form:input class="form-control" placeholder="" path="ma" value="${ma}"
                                            readonly="true"/>
                                <form:errors path="ma" cssStyle="color: red"></form:errors>
                            </div>
                            <div class="form-group">
                                <form:label class="form-label" path="soImei">Imei:</form:label>
                                <form:input class="form-control" placeholder="Imei" path="soImei"/>
                                <form:errors path="soImei" cssStyle="color: red"></form:errors>
                                <label style="color: red">${thongBao}</label>
                            </div>
                            <div class="form-group">
                                <form:label path="moTa"><b>Mô Tả:</b></form:label>
                                <form:textarea path="moTa" class="form-control"></form:textarea>
                                <form:errors path="moTa" cssStyle="color: red"></form:errors>
                            </div>
                            <div style="text-align: center">
                                <button type="submit" class="btn btn-primary mr-2"
                                        onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                                    ADD
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
