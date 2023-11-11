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
<div class="row">
    <form action="/thong-ke/loc-thoi-gian-kh" method="post" style="float: right">
        <div style="display: flex; justify-content: center; align-items: center;">
            <div>
                <label>Ngày bắt đầu
                    <input type="date" id="ngayTao" name="startDate" class="form-control"
                           placeholder="Từ ngày">
                </label>
            </div>
            <div>
                <label>Ngày kết thúc
                    <input type="date" id="ngayTao1" name="endDate" class="form-control"
                           placeholder="Kết thúc">
                </label>
            </div>
            <div>
                <button type="submit" class="btn btn-primary mr-2" style="width: 100px; height: 38px;"
                        onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                    Lọc
                </button>
            </div>
        </div>
    </form>
</div>
<div class="row">
<div class="col-6"><h3>Biểu đồ thống kê doanh thu theo độ tuổi</h3>
<div class="card">
    <div class="card-body">
       <canvas id="myChart" ></canvas>
    </div>
</div>
</div>
<div class="col-6"><h3>Biểu đồ thống kê doanh thu theo giới tinh</h3>
    <div class="card">
        <div class="card-body">
            <canvas id="myChart2" ></canvas>
        </div>
    </div>
</div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.0/dist/chart.umd.min.js"></script>
<script>
    // Biểu đồ doanh thu theo độ tuổi
    // Biểu đồ doanh thu theo độ tuổi
    const ctx1 = document.getElementById('myChart');
    const data1 = [];

    <c:forEach items="${listDoanhThuKhachHang}" var="DT" >
    data1.push({
        tuoi: "${DT.getTuoi()}",
        doanhThu: parseFloat("${DT.getDoanhThu()}"), // Sử dụng hàm parseFloat() để chuyển đổi giá trị chuỗi thành số
    });
    </c:forEach>

    new Chart(ctx1, {
        type: 'pie',
        data: {
            labels: data1.map(item => item.tuoi),
            datasets: [{
                label: 'Doanh Thu',
                data: data1.map(item => item.doanhThu),
                borderWidth: 1,
                percentage: true, // Thêm thuộc tính này để hiển thị phần trăm
            }],
        },
    });


    // Biểu đồ doanh thu theo giới tính
    const ctx2 = document.getElementById('myChart2');
    const data2 = [];

    <c:forEach items="${listDoanhThuKhachHangGioiTinh}" var="DT" >
    data2.push({
        gioiTinh: "${DT.getGioiTinh() ?"Nam":"Nữ"}",
        doanhThu: ${DT.getDoanhThu()},
    });
    </c:forEach>

    new Chart(ctx2, {
        type: 'pie',
        data: {
            labels: data2.map(item => item.gioiTinh),
            datasets: [{
                label: 'Doanh Thu',
                data: data2.map(item => item.doanhThu),
                borderWidth: 1,
                percentage: true, // Thêm thuộc tính này để hiển thị phần trăm
            }],
        },
    });
</script>
</html>
