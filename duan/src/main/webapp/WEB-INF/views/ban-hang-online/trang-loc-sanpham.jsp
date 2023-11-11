<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>

    <%--phan trang--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>Hóa đơn</title>
    <%--căn đều--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"/>


    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

    <!-- Bootstrap -->
    <link type="text/css" rel="stylesheet" href="/cssbanhang/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

    <!-- Slick -->
    <link type="text/css" rel="stylesheet" href="/cssbanhang/slick.css"/>
    <link type="text/css" rel="stylesheet" href="/cssbanhang/slick-theme.css"/>

    <!-- nouislider -->
    <link type="text/css" rel="stylesheet" href="/cssbanhang/nouislider.min.css"/>

    <!-- Font Awesome Icon -->

    <!-- Custom stlylesheet -->
    <link type="text/css" rel="stylesheet" href="/cssbanhang/style.css"/>

    <%--    select 2--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/css/select2.min.css">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.min.css">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/select2-bootstrap-5-theme@1.3.0/dist/select2-bootstrap-5-theme.rtl.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/select2.full.min.js"></script>
    <%--loc theo giá--%>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/noUiSlider/14.6.1/nouislider.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/noUiSlider/14.6.1/nouislider.min.js"></script>
    <style>
        /* CSS cho modal */
        #myModal {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            display: flex;
            align-items: center;
            justify-content: center;
            z-index: 1000; /* Đặt giá trị z-index lớn */
        }

        .modal-content {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
        }

        /*div{*/
        /*    border: 1px solid red;*/
        /*}*/
        .input-with-button {
            display: flex; /* Sử dụng flexbox để căn chỉnh nút bên trong input */
            border: 1px solid #ccc; /* Tạo đường viền xung quanh hộp tìm kiếm */
            border-radius: 25px; /* Đặt bán kính tròn cho hộp tìm kiếm */
            overflow: hidden; /* Loại bỏ nút nếu nó bị tràn ra ngoài hộp */
        }

        .input-with-button input {
            flex: 1; /* Làm cho input mở rộng để lấp đầy hộp */
            border: none; /* Loại bỏ đường viền của input */
            padding: 10px; /* Đặt khoảng cách nội dung bên trong input */
            outline: none; /* Loại bỏ đường viền khi focus vào input */
        }

        .input-with-button button {
            background: #007bff; /* Màu nền của nút */
            color: #fff; /* Màu chữ trắng */
            border: none; /* Loại bỏ đường viền của nút */
            padding: 10px 20px; /* Đặt khoảng cách nội dung bên trong nút */
            cursor: pointer; /* Biến con trỏ thành bàn tay khi trỏ vào nút */
        }

        .carousel-prev {
            background-color: white;
            color: black;
            font-size: 16px;
            border: none;
            transition: background-color 0.3s, color 0.3s;
        }

        .carousel-prev:hover {
            background-color: #0056b3; /* Màu nền khi di chuột qua */
            color: #fff; /* Màu chữ khi di chuột qua */
        }

        .carousel-next {
            background-color: white; /* Màu nền nút */
            color: black; /* Màu chữ trên nút */
            font-size: 16px; /* Kích cỡ chữ */
            border: none; /* Loại bỏ viền nút */
            transition: background-color 0.3s, color 0.3s;
        }

        .carousel-next:hover {
            background-color: #0056b3; /* Màu nền khi di chuột qua */
            color: #fff; /* Màu chữ khi di chuột qua */
        }

        .form-control {
            background-color: #f5f5f5; /* Màu nền combobox */
            color: #333; /* Màu chữ */
            border: 1px solid #ccc; /* Viền */
            border-radius: 5px; /* Góc bo tròn */
            width: 200px; /* Chiều rộng */
            height: 40px;
        }

        .form-control:hover {
            background-color: #007bff; /* Màu nền khi di chuột vào */
            border-color: #007bff; /* Màu viền khi di chuột vào */
            color: #fff; /* Màu chữ khi di chuột vào */
        }
        .cart-dropdown {
            border-radius: 10px;
            width: 180px;
            background-color: #fff;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
            padding: 10px;
            margin-top: 10px;
        }

        .cart-dropdown a {
            display: block;
            width: 100%;
            padding: 10px;
            text-decoration: none;
            text-align: center;
            color: #fff;
            background-color: #007bff;
            margin-bottom: 10px;
        }

        .cart-dropdown a:hover {
            background-color: #0056b3;
        }
    </style>

</head>
<body>


<!-- HEADER -->
<header>
    <!-- TOP HEADER -->
    <div id="top-header">
        <div class="container">
            <ul class="header-links pull-left">
                <li><a href="#"><i class="fa fa-envelope-o"></i> gphones@gmail.com</a></li>
            </ul>
            <ul class="header-links pull-right">
                <%--                <li><a href="#"><i class="fa fa-dollar"></i> USD</a></li>--%>
                <c:if test="${idkhachhang=='1'}">
                    <li><a href="/login"><i class="fa fa-user-o"></i> Chưa đăng nhập:<input id="tkmkidkhachhang"
                                                                                            type="text"
                                                                                            style="display: none"
                                                                                            value="${idkhachhang}"></a>
                    </li>

                </c:if>
                <c:if test="${idkhachhang !='1'}">
                    <!-- Cart -->
                    <li>
                        <div class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown">
                                    <span>
                                    <i class="fa fa-user-o"></i>
                                            ${khachhangdangnhap.hoTen}
                                        <input id="tkmkidkhachhang" type="text" style="display: none"
                                               value="${idkhachhang}">
                                    </span>
                            </a>
                            <div class="cart-dropdown"
                                 style="border-radius: 10px;width: 3.5cm;margin-top: 10px;width: 180px">
                                <div>
                                    <div>
                                        <a href="#" class="btn btn-primary">Tài khoản của tôi</a>
                                    </div>
                                    <div>
                                        <a href="/ban-hang-online/hoa-don-online/${idkhachhang}" class="btn btn-primary">Đơn hàng</a>
                                    </div>
                                    <div>
                                        <a href="/logout" class="btn btn-primary" style="" onclick="">Đăng xuất</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>
                    <!-- /Cart -->
                </c:if>
            </ul>
        </div>
    </div>
    <!-- /TOP HEADER -->

    <!-- MAIN HEADER -->
    <div id="header">
        <!-- container -->
        <div class="container">
            <!-- row -->
            <div class="row">
                <!-- LOGO -->
                <div class="col-md-3">
                    <div class="header-logo">
                        <h2 class="logo" style="margin: 20px;color: white;font-family: 'Times New Roman'">GPhoneS Store</h2>
                    </div>
                </div>
                <!-- /LOGO -->

                <!-- SEARCH BAR -->
                <div class="col-md-6">
                    <div class="header-search">
                        <form>
                            <div class="input-with-button">
                                <input class="input" placeholder="Tìm kiếm sản phẩm...">
                                <button class="search-btn">Search</button>
                            </div>
                        </form>
                    </div>
                </div>
                <!-- /SEARCH BAR -->

                <!-- ACCOUNT -->
                <div class="col-md-3 clearfix">
                    <div class="header-ctn">
                        <!-- Wishlist -->
                        <%--                        <div>--%>
                        <%--                            <a href="#">--%>
                        <%--                                <i class="fa fa-heart-o"></i>--%>
                        <%--                                <span>Your Wishlist</span>--%>
                        <%--                                <div class="qty">2</div>--%>
                        <%--                            </a>--%>
                        <%--                        </div>--%>
                        <!-- /Wishlist -->

                        <!-- Cart -->


                        <div class="dropdown" id="giohangtrangchu">
                            <c:if test="${idkhachhang!='1'}">
                                <c:if test="${listghct.size()>0}">
                                    <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                                        <i class="fa fa-shopping-cart"></i>
                                        <span>Giỏ hàng</span>
                                        <div class="qty">${banhangonline.ListghctTheoidgh(banhangonline.ListghTheoidkh(idkhachhang).get(0).getId()).size()}</div>
                                    </a>
                                    <div class="cart-dropdown" style="width:  13cm">
                                        <div class="cart-list">

                                            <c:forEach items="${listghct}" var="ht" varStatus="stt">
                                                <br>
                                                <div style="border: 1px solid;height: 2cm">
                                                    <div style="width: 80%;float: right">
                                                        <label style="font-weight: bold">Sản
                                                            phẩm:</label>${ht.chiTietSanPham.sanPham.ten}-
                                                            ${ht.chiTietSanPham.rom.dungLuong}-${ht.chiTietSanPham.mauSac.ten}.

                                                        <br>
                                                        <label style="font-weight: bold">Số lượng:</label> ${ht.soLuong}<br>
                                                        <label style="tbackground-color: white;border: 1px solid white">${ht.donGiaKhiGiam}đ</label>
                                                    </div>
                                                    <div style="width: 18%;">
                                                        <input type="checkbox" name="checkidghTT" value="${ht.id}"
                                                               onclick="chonsanphamgiohangTT('${stt.index}','${ht.id}','${ht.gioHang.id}');"  ${ht.tinhTrang==0 ?"checked":""}>


                                                        <img src="../../../uploads/${ht.chiTietSanPham.urlAnh}"
                                                             width="50" height="50"
                                                             style="border-radius:50% 50% 50% 50%;border: 1px solid black">
                                                    </div>

                                                </div>
                                            </c:forEach>
                                        </div>
                                        <div class="cart-summary">
                                            <small> ${banhangonline.TongtienvsTongspchon(listghct.get(0).gioHang.id).gettongsanphamchon()}
                                                Sản phẩm được chọn</small>
                                            <h5>
                                                Tổng:${banhangonline.TongtienvsTongspchon(listghct.get(0).gioHang.id).gettongtien()}
                                                đ</h5>
                                        </div>
                                        <div class="cart-btns">
                                            <a href="/ban-hang-online/xem-gio-hang">Xem giỏ hàng</a>
                                            <a href="#">Chọn hết
                                                <input type="checkbox" name="checktongTT"
                                                       onclick="chonhetgiohangtongTRANGCHU('${listghct.get(0).gioHang.id}');"  ${tttong==0 ?"checked":""}>
                                            </a>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${listghct.size()<=0}">
                                    <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                                        <i class="fa fa-shopping-cart"></i>
                                        <span>Giỏ hàng</span>
                                        <div class="qty">0</div>
                                    </a>
                                    <div class="cart-dropdown" style="width: 500px;">
                                        <div class="cart-summary">
                                            <small> 0 Sản phẩm được chọn</small>
                                            <h5>Tổng:0 đ</h5>
                                        </div>
                                        <div class="cart-btns">
                                            <a href="/ban-hang-online/xem-gio-hang">Xem giỏ hàng</a>
                                            <a href="#">Chọn hết
                                            </a>
                                        </div>
                                    </div>
                                </c:if>
                            </c:if>
                            <c:if test="${idkhachhang=='1'}">

                                <a class="dropdown-toggle" aria-expanded="true" href="/login">
                                    <i class="fa fa-shopping-cart"></i>
                                    <span>Giỏ hàng</span>
                                    <div class="qty">0</div>
                                </a>


                            </c:if>

                        </div>

                        <!-- /Cart -->


                        <!-- Menu Toogle -->
                        <div class="menu-toggle">
                            <a href="#">
                                <i class="fa fa-bars"></i>
                                <span>Menu</span>
                            </a>
                        </div>
                        <!-- /Menu Toogle -->
                    </div>
                </div>
                <!-- /ACCOUNT -->
            </div>
            <!-- row -->
        </div>
        <!-- container -->
    </div>
    <!-- /MAIN HEADER -->
</header>
<!-- /HEADER -->

<!-- NAVIGATION -->
<nav id="navigation">
    <!-- container -->
    <div class="container">
        <!-- responsive-nav -->
        <div id="responsive-nav">
            <!-- NAV -->
            <%--                main-nav nav  navbar-nav--%>
            <ul class=" main-nav nav ">

                <c:if test="${idkhachhang=='1'}">
                    <li><a href="/ban-hang-online/hien-thi">TRANG CHỦ</a></li>
                </c:if>
                <c:if test="${idkhachhang !='1'}">
                    <li><a href="/ban-hang-online/home">TRANG CHỦ</a></li>
                </c:if>
                <li><a href="#">ƯU ĐÃI HẤP DẪN</a></li>
                <li><a href="#">LOẠI</a></li>
                <li><a href="/ban-hang-online/dien-thoai-thong-minh">ĐIỆN THOẠI THÔNG MINH</a></li>
            </ul>
            <!-- /NAV -->
        </div>
        <!-- /responsive-nav -->
    </div>
    <!-- /container -->
</nav>
<!-- /NAVIGATION -->


<%--code--%>
<div style="width: 75%;margin-left: 12.5%;">
    <P style="font-size: 16px;font-weight: 700">Bộ lọc tìm kiếm</P>
    <%--loc--%>
    <div>
        <div class="container px-0 px-lg-5 mt-0">
            <div class="row gx-0 gx-lg-5 row-cols-0 row-cols-md-0 row-cols-xl-5 justify-content-center"
                 style="width: 100%">
                <div style="height: 1.5cm">
                    <select class="form-control" id="hangds1" onchange="clickcombobox()">
                        <option selected value="null">Hãng sản phẩm</option>
                        <c:forEach items="${hangds}" var="ht">
                            <option value="${ht.ten}">${ht.ten}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <select class="form-control" id="camds1" onchange="clickcombobox()">
                        <option selected value="null">Camera</option>
                        <c:forEach items="${camds}" var="ht">
                            <option value="${ht.thongSo}">${ht.thongSo}</option>
                        </c:forEach>
                    </select>
                </div>

                <div>
                    <select class="form-control" id="mands1" onchange="clickcombobox()">
                        <option selected value="null">Màn hình</option>
                        <c:forEach items="${mands}" var="ht">
                            <option value="${ht.thongSo}">${ht.thongSo}</option>
                        </c:forEach>
                    </select>
                </div>
                <div style="height: 1.5cm">
                    <select class="form-control" id="mauds1" onchange="clickcombobox()">
                        <option selected value="null">Màu sắc</option>
                        <c:forEach items="${mauds}" var="ht">
                            <option value="${ht.ten}">${ht.ten}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <select class="form-control" id="ramds1" onchange="clickcombobox()">
                        <option selected value="null">Ram</option>
                        <c:forEach items="${ramds}" var="ht">
                            <option value="${ht.dungLuong}">${ht.dungLuong}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <select class="form-control" id="romds1" onchange="clickcombobox()">
                        <option selected value="null">Rom</option>
                        <c:forEach items="${romds}" var="ht">
                            <option value="${ht.dungLuong}">${ht.dungLuong}</option>
                        </c:forEach>
                    </select>
                </div>
                <div style="height: 1.5cm">
                    <select class="form-control" id="pinds1" onchange="clickcombobox()">
                        <option selected value="null">Pin</option>
                        <c:forEach items="${pinds}" var="ht">
                            <option value="${ht.loaiPin}">${ht.loaiPin}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <select class="form-control" id="dungds1" onchange="clickcombobox()">
                        <option selected value="null">Dung lượng pin</option>
                        <c:forEach items="${dungds}" var="ht">
                            <option value="${ht.thongSo}">${ht.thongSo}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <select class="form-control" id="chipds1" onchange="clickcombobox()">
                        <option selected value="null">Chíp</option>
                        <c:forEach items="${chipds}" var="ht">
                            <option value="${ht.ten}">${ht.ten}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <select class="form-control" id="sands1" onchange="clickcombobox()">
                        <option selected value="null">Sản phẩm</option>
                        <c:forEach items="${sands}" var="ht">
                            <option value="${ht.ten}">${ht.ten}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>

        <div id="vt"></div>

    </div>
    <div style="width: 6cm;float: right">
        <label class="range-label">Khoảng tiền:</label>
        <div id="slider" class="slider"></div>
        <br>
        <label id="thongbaokhoang" style="color: red"></label>
        <div>
            Từ: <input id="value1" value="0" style="width: 2cm">
            đến <input id="value2" value="${max}" style="width: 2cm">
        </div>
        <div id="max" style="display: none">${max}</div>
    </div>
    <%--loc kết thúc--%>
    <br><br>
    <div style="margin-left: -1%;margin-top: 2cm" id="dssanphamloc">
        <!-- Carousel -->
        <div id="demo11" class="carousel slide" data-bs-ride="false">
            <!-- Indicators/dots -->

            </button>

            <!-- The slideshow/carousel -->
            <div class="carousel-inner">
                <c:set var="vitri" scope="session" value="${-1}"/>
                <c:forEach begin="1" end="${lamchon1}" varStatus="trang">
                <c:set var="salary" scope="session" value="${1}"/>
                <c:if test="${trang.index <2}">
                <div class="carousel-item active">
                    </c:if>
                    <c:if test="${trang.index >=2}">
                    <div class="carousel-item ">
                        </c:if>


                        <div class="container px-0 px-lg-4 mt-0">
                            <div class="row gx-0 gx-lg-0 row-cols-0 row-cols-md-0 row-cols-xl-4 justify-content-center"
                                 style="width: 100%">
                                <c:forEach items="${listsp}" var="ht" varStatus="stt">
                                    <c:if test="${banhangonline.soluongcon(ht.id)>0}">
                                        <c:if test="${stt.index > vitri }">
                                            <%--                            phân trang số 9 là 8 dữ liệu--%>
                                            <c:if test="${salary <9}">
                                                <!-- product -->

                                                <div class="product" style="margin-left: 1%;width: 24%;">
                                                    <div class="product-img">
                                                        <img src="../../../uploads/${ht.urlAnh}"
                                                             style="width: 90%;height: 6cm;margin-left: 5%" alt="">
                                                        <div class="product-label">
                                                            <span class="sale">-${giamgia.tonggiamgia(ht.id)}%</span>
                                                            <span class="new">Giảm giá</span>
                                                        </div>
                                                    </div>
                                                    <div class="product-body"
                                                         style="text-align: left;word-wrap: break-word;z-index: 2">
                                                            <%--                    <p class="product-category">Điện thoại</p>--%>
                                                        <h3 class="product-name"><a href="#">${ht.sanPham.ten}</a></h3>
                                                        <h4 class="product-price"><span
                                                                style="font-size:15px">₫</span>${ht.giaBan-ht.giaBan/100*giamgia.tonggiamgia(ht.id)}-
                                                            <del class="product-old-price">${ht.giaBan}<span
                                                                    style="font-size:15px">₫</span></del>
                                                        </h4>
                                                            <%--                                            ${ht.tinhTrang} +  ${ht.sanPham.tinhTrang}--%>

                                                        <div class="product-rating">
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                        </div>
                                                        <div class="product-btns">
                                                            <button class="add-to-wishlist"><i
                                                                    class="fa fa-heart-o"></i><span class="tooltipp">Thêm vào danh sách yêu thích</span>
                                                            </button>
                                                            <button class="add-to-compare"><i
                                                                    class="fa fa-exchange"></i><span class="tooltipp">Thêm để so sánh</span>
                                                            </button>
                                                            <button class="quick-view"><a
                                                                    href="/ban-hang-online/chi-tiet-san-pham/${ht.id}"><i
                                                                    class="fa fa-exchange"></i></a><span
                                                                    class="tooltipp">Xem chi tiết</span></button>
                                                        </div>
                                                    </div>
                                                    <div class="add-to-cart" style="z-index: 1">
                                                        <c:if test="${idkhachhang!='1'}">
                                                            <button class="add-to-cart-btn"
                                                                    onclick="thongbaothemvaogiohang('${ht.id}');"><i
                                                                    class="fa fa-shopping-cart"></i> Thêm vào giỏ hàng
                                                            </button>
                                                        </c:if>
                                                        <c:if test="${idkhachhang=='1'}">
                                                            <a href="/login">
                                                                <button class="add-to-cart-btn"><i
                                                                        class="fa fa-shopping-cart"></i> Thêm vào giỏ
                                                                    hàng
                                                                </button>
                                                            </a>
                                                        </c:if></div>
                                                </div>
                                                <!-- /product -->
                                                <c:set var="vitri" scope="session" value="${stt.index}"/>
                                                <c:set var="salary" scope="session" value="${salary+1}"/>
                                            </c:if>
                                        </c:if>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                        <br><br><br><br>
                    </div>
                    </c:forEach>
                </div>
                <!-- Indicators/dots -->

                <div class="carousel text-center">
                    <button class="carousel-prev" type="button" data-bs-target="#demo11" data-bs-slide="prev">
                        <<
                    </button>
                    <c:forEach begin="1" end="${lamchon1}" varStatus="trang">
                        <button class="carousel-prev" type="button" data-bs-target="#demo11"
                                data-bs-slide-to="${trang.index-1}">${trang.index}</button>
                    </c:forEach>
                    <button class="carousel-next" type="button" data-bs-target="#demo11" data-bs-slide="next">
                        >>
                    </button>
                </div>
            </div>
        </div>

    </div>
</div>

<%--het code--%>

<!-- FOOTER -->
<footer id="footer">
    <!-- top footer -->
    <div class="section">
        <!-- container -->
        <div class="container">
            <!-- row -->
            <div class="row">
                <div class="col-md-3 col-xs-6">
                    <div class="footer">
                        <h3 class="footer-title">About Us</h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt
                            ut.</p>
                        <ul class="footer-links">
                            <li><a href="#"><i class="fa fa-map-marker"></i>1734 Stonecoal Road</a></li>
                            <li><a href="#"><i class="fa fa-phone"></i>+021-95-51-84</a></li>
                            <li><a href="#"><i class="fa fa-envelope-o"></i>email@email.com</a></li>
                        </ul>
                    </div>
                </div>

                <div class="col-md-3 col-xs-6">
                    <div class="footer">
                        <h3 class="footer-title">Categories</h3>
                        <ul class="footer-links">
                            <li><a href="#">Hot deals</a></li>
                            <li><a href="#">Laptops</a></li>
                            <li><a href="#">Smartphones</a></li>
                            <li><a href="#">Cameras</a></li>
                            <li><a href="#">Accessories</a></li>
                        </ul>
                    </div>
                </div>

                <div class="clearfix visible-xs"></div>

                <div class="col-md-3 col-xs-6">
                    <div class="footer">
                        <h3 class="footer-title">Information</h3>
                        <ul class="footer-links">
                            <li><a href="#">About Us</a></li>
                            <li><a href="#">Contact Us</a></li>
                            <li><a href="#">Privacy Policy</a></li>
                            <li><a href="#">Orders and Returns</a></li>
                            <li><a href="#">Terms & Conditions</a></li>
                        </ul>
                    </div>
                </div>

                <div class="col-md-3 col-xs-6">
                    <div class="footer">
                        <h3 class="footer-title">Service</h3>
                        <ul class="footer-links">
                            <li><a href="#">My Account</a></li>
                            <li><a href="#">View Cart</a></li>
                            <li><a href="#">Wishlist</a></li>
                            <li><a href="#">Track My Order</a></li>
                            <li><a href="#">Help</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- /row -->
        </div>
        <!-- /container -->
    </div>
    <!-- /top footer -->

    <!-- bottom footer -->
    <div id="bottom-footer" class="section">
        <div class="container">
            <!-- row -->
            <div class="row">
                <div class="col-md-12 text-center">
                    <ul class="footer-payments">
                        <li><a href="#"><i class="fa fa-cc-visa"></i></a></li>
                        <li><a href="#"><i class="fa fa-credit-card"></i></a></li>
                        <li><a href="#"><i class="fa fa-cc-paypal"></i></a></li>
                        <li><a href="#"><i class="fa fa-cc-mastercard"></i></a></li>
                        <li><a href="#"><i class="fa fa-cc-discover"></i></a></li>
                        <li><a href="#"><i class="fa fa-cc-amex"></i></a></li>
                    </ul>
                    <span class="copyright">
								<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
								Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i
                            class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com"
                                                                                target="_blank">Colorlib</a>
                        <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							</span>
                </div>
            </div>
            <!-- /row -->
        </div>
        <!-- /container -->
    </div>
    <!-- /bottom footer -->
</footer>
<!-- /FOOTER -->

<div style="position: fixed;top: 50%;left: 50%;transform: translate(-50%,-50%);display:none;z-index: 3;width: 10cm;height: 1cm;background-color: #00FF00;text-align: center"
     id="thongbaothemgiohang">
    <h2>Đã Thêm vào Giỏ hàng</h2>
</div>

<script>
    function chonhetgiohangtongTRANGCHU(idgh) {
        // var  idgh1=encodeURIComponent(idgh)
        if (document.getElementsByName('checktongTT')[0].checked == true) {
            var link = '/ban-hang-online/trang-chu/chon-san-pham-gio-hang-trang-chu/full/0/' + idgh;
            // document.getElementById("ktlink").innerHTML=link
            loadgiaodienghctbanhangTrangChu(link);
            loadgiaodienghctbanhangTrangChu(link);
        } else {
            var link = '/ban-hang-online/trang-chu/chon-san-pham-gio-hang-trang-chu/full/1/' + idgh;
            // document.getElementById("ktlink").innerHTML=link
            loadgiaodienghctbanhangTrangChu(link);
            loadgiaodienghctbanhangTrangChu(link);
        }
    }

    function chonsanphamgiohangTT(vt, idctgh, idgh) {
        // var  idgh1=encodeURIComponent(idgh)
        var vt1 = parseInt(vt);
        // var id1=encodeURIComponent(id);
        if (document.getElementsByName('checkidghTT')[vt1].checked == true) {
            var link = '/ban-hang-online/trang-chu/chon-san-pham-gio-hang-trang-chu/' + idctgh + '/0/' + idgh;
            // document.getElementById("ktlink").innerHTML=link
            loadgiaodienghctbanhangTrangChu(link);
            loadgiaodienghctbanhangTrangChu(link);
        } else {
            var link = '/ban-hang-online/trang-chu/chon-san-pham-gio-hang-trang-chu/' + idctgh + '/1/' + idgh;
            // document.getElementById("ktlink").innerHTML=link
            loadgiaodienghctbanhangTrangChu(link);
            loadgiaodienghctbanhangTrangChu(link);
        }
    }

    function thongbaothemvaogiohang(idctsp) {
        //chạy 2 lần mới ấn dc
        for (var stt = 0; stt < 2; stt = stt + 1) {
            loadgiaodienghctbanhangTrangChu('/ban-hang-online/them-san-pham-vao-gio-hang/' + idctsp);
        }

        // loadgiaodienghctbanhangTrangChu('/ban-hang-online/them-san-pham-vao-gio-hang/'+idctsp);
        // alert("123")
        document.getElementById('thongbaothemgiohang').style.display = '';
        setTimeout(function () {
            document.getElementById('thongbaothemgiohang').style.display = 'none';
        }, 2000); // 2000 milliseconds tương đương với 2 giây

    }


    function loadgiaodienghctbanhangTrangChu(interfaceUrl) {
        fetch(interfaceUrl)
            .then(response => response.text())
            .then(data => {
                const content = document.getElementById('giohangtrangchu');
                content.innerHTML = data;
                // thanhtienbenghct();
                loadScripts();


            })
            .catch(error => {
                console.error('Error loading interface:', error);
            });

        // document.getElementById('thanhlocctsp').style.display='none';

    }

    function loadbenloc(interfaceUrl) {
        fetch(interfaceUrl)
            .then(response => response.text())
            .then(data => {
                const content = document.getElementById('dssanphamloc');
                content.innerHTML = data;
                loadScripts();
            })
            .catch(error => {
                console.error('Error loading interface:', error);
            });
        // document.getElementById('thanhlocctsp').style.display='none';
    }

    function loadScripts() {
        const scriptsToLoad = [
            // 'https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js',


        ];
        const head = document.head || document.getElementsByTagName('head')[0];

        function loadScript(index) {
            if (index < scriptsToLoad.length) {
                const script = document.createElement('script');
                script.src = scriptsToLoad[index];
                script.onload = function () {
                    loadScript(index + 1);
                };
                head.appendChild(script);

            }
        }

        // Bắt đầu quá trình tải script
        loadScript(0);
    }

    loadSelect2();

    function loadSelect2() {
        // Gọi .select2() cho các phần tử sau khi tất cả các tệp script đã được nạp
        $('#hangds1').select2({
            theme: 'bootstrap-5'
        });
        $('#camds1').select2({
            theme: 'bootstrap-5'
        });
        $('#mands1').select2({
            theme: 'bootstrap-5'
        });
        $('#mauds1').select2({
            theme: 'bootstrap-5'
        });
        $('#ramds1').select2({
            theme: 'bootstrap-5'
        });
        $('#romds1').select2({
            theme: 'bootstrap-5'
        });

        $('#pinds1').select2({
            theme: 'bootstrap-5'
        });
        $('#dungds1').select2({
            theme: 'bootstrap-5'
        });

        $('#chipds1').select2({
            theme: 'bootstrap-5'
        });

        $('#sands1').select2({
            theme: 'bootstrap-5'
        });

        $('#diachids1').select2({
            theme: 'bootstrap-5'
        });

        // Gọi .select2() cho các phần tử khác ở đây (tương tự)
    }
</script>
<script>
    var slider = document.getElementById('slider');
    var value1 = document.getElementById('value1');
    var value2 = document.getElementById('value2');
    var max = parseFloat(document.getElementById('max').innerHTML);
    var isSliding = false;

    noUiSlider.create(slider, {
        start: [0, max], // Giá trị mặc định cho hai chấm chòn
        connect: true,    // Kết nối giữa hai chấm chòn
        range: {
            'min': 0,
            'max': max
        }
    });


    value1.addEventListener('input', function () {

        this.value = this.value.replace(/[^0-9]/g, '');
        isSliding = true;
        slider.noUiSlider.set([parseInt(value1.value), null]);
        isSliding = false;
        if (
            this.value.trim() === '' || parseFloat(this.value) > parseFloat(value2.value)
        ) {

            document.getElementById("thongbaokhoang").innerHTML = "Không hợp lệ ";

        } else {
            document.getElementById("thongbaokhoang").innerHTML = "";
            clickcombobox();
        }
    });

    value2.addEventListener('input', function () {
        this.value = this.value.replace(/[^0-9]/g, '');
        isSliding = true;
        slider.noUiSlider.set([null, parseInt(value2.value)]);
        isSliding = false;
        if (
            this.value.trim() === '' || parseFloat(this.value) < parseFloat(value1.value)
        ) {

            document.getElementById("thongbaokhoang").innerHTML = "Không hợp lệ ";

        } else {
            document.getElementById("thongbaokhoang").innerHTML = " ";
            clickcombobox();
        }
    });


    slider.noUiSlider.on('update', function (values, handle) {
        if (!isSliding) {
            if (handle === 0) {
                value1.value = Math.round(values[0]); // Làm tròn giá trị xuống số nguyên
                // clickcombobox();
            } else {
                value2.value = Math.round(values[1]); // Làm tròn giá trị xuống số nguyên
                // clickcombobox();
            }
            clickcombobox();

        }
    });
</script>
<!-- jQuery Plugins -->
<script src="/jsbanhang/jquery.min.js"></script>
<script src="/jsbanhang/bootstrap.min.js"></script>
<script src="/jsbanhang/slick.min.js"></script>
<script src="/jsbanhang/nouislider.min.js"></script>
<script src="/jsbanhang/jquery.zoom.min.js"></script>
<script src="/jsbanhang/main.js"></script>
<%--    select 2--%>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
<%--loc khoang tien--%>

</body>

</html>




