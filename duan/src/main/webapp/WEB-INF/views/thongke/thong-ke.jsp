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

<%--<div class="row">--%>
<%--    <div class="col-lg-4 col-sm-6">--%>
<%--        <div class="card">--%>
<%--            <div class="stat-widget-two card-body">--%>
<%--                <div class="stat-content">--%>
<%--                    <div class="stat-text">Tổng đơn hàng đã bán </div>--%>
<%--                    <div class="stat-digit">${listCount}</div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="col-lg-4 col-sm-6">--%>
<%--        <div class="card">--%>
<%--            <div class="stat-widget-two card-body">--%>
<%--                <div class="stat-content">--%>
<%--                    <div class="stat-text">Tổng thu nhập</div>--%>
<%--                    <div class="stat-digit">${listAvg}<a>VNĐ</a></div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="col-lg-4 col-sm-6">--%>
<%--        <div class="card">--%>
<%--            <div class="stat-widget-two card-body">--%>
<%--                <div class="stat-content">--%>
<%--                    <div class="stat-text">Tổng(..)</div>--%>
<%--                    <div class="stat-digit">500</div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <!-- /# column -->--%>
<%--</div>--%>

<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">Doanh thu các năm
                <form action="/thong-ke/loc-nam" method="post" style="float: right">
                    <select name="namSelect">
                        <option value="" disabled >Chọn năm</option>
                        <c:forEach items="${listYear}" var="nam">
                            <option value="${nam.getNam()}">${nam.getNam()}</option>
                        </c:forEach>
                    </select>
                    <button type="submit" class="btn btn-primary mr-2"
                            onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
                        Lọc
                    </button>
                </form>
            </h4>

            <div class="table-responsive">
                <table class="table table-striped" style="color: black">
                    <thead>
                    <tr>
                        <th>Tháng</th>
                        <th>Số lượng sản phẩm đã bán</th>
                        <th>Doanh thu tháng</th>
                        <th>Giá mua thấp nhất</th>
                        <th>Giá mua cao nhất</th>
                        <th>Doanh thu trung bình</th>
                    </tr>
                    </thead>
                    <tbody>
                    <i class="mdi mdi-border-color"></i>
                    <c:forEach items="${listDoanhThu}" var="DT" varStatus="index">
                        <tr>
                            <td>${DT.getThang()}</td>
                            <td>${DT.getSoLuongSP()}</td>
                            <td>${DT.getDoanhThu()}</td>
                            <td>${DT.getGiaMuaMin()}</td>
                            <td>${DT.getGiaMuaMax()}</td>
                            <td>${DT.getDoanhThuTrungBinh()}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>


<div class="card">
    <div class="card-body">
        <canvas id="myChart" ></canvas>
    </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.0/dist/chart.umd.min.js"></script>
<script>
    const data = [];

    <c:forEach items="${listDoanhThu}" var="DT" varStatus="index">
    data.push({
        thang: ${DT.getThang()},
        doanhThu: ${DT.getDoanhThu()},
        soLuong: ${DT.getSoLuongSP()}
    });
    </c:forEach>

    const ctx = document.getElementById('myChart');

    new Chart(ctx, {
        type: 'line',
        data: {
            labels: data.map(item => item.thang),
            datasets: [{
                label: 'Doanh Thu',
                data: data.map(item => item.doanhThu),
                borderWidth: 1,
                yAxisID: 'y'
            },
                {
                    label: 'Số Lượng',
                    data: data.map(item => item.soLuong),
                    borderWidth: 1,
                    yAxisID: 'y1'
                }
            ]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true,
                    id: 'y'
                },
                y1: {
                    beginAtZero: true,
                    id: 'y1',
                    position: 'right',

                    // grid line settings
                    grid: {
                        drawOnChartArea: false, // only want the grid lines for one axis to show up
                    },
                },
            }
        }
    });
</script>
</html>
