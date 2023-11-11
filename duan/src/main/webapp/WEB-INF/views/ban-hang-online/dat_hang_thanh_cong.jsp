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

    <title>Trang chủ</title>
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

        /*div{*/
        /*    border: 1px solid red;*/
        /*}*/
    </style>

</head>

<head>
    <style>
        #pis {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            background-color: #f9f9f9;
            padding: 20px;
            margin-top: 50px;
        }

        h1 {
            color: #338dbc;
            text-align: center;
            font-size: 24px;
            margin-bottom: 10px;
        }

        p {
            margin-bottom: 10px;
            color: #333;
        }

        .thongbao {
            margin: 0 auto;
            width: 500px;
            text-align: center;
        }

        .thongbao h1 {
            font-size: 30px;
            font-weight: bold;
        }

        .thongbao p {
            font-size: 16px;
        }

        .nut {
            width: 100px;
            height: 30px;
            border-radius: 5px;
            background-color: #338dbc;
            color: #fff;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
        }

        .header {
            text-align: center;
            margin-bottom: 30px;
        }

        .footer {
            text-align: center;
            margin-top: 50px;
            padding-top: 20px;
            border-top: 1px solid #ddd;
            color: #888;
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
                            <div class="cart-dropdown" style="border-radius: 10px;width: 3.5cm;margin-top: 10px">
                                <div>
                                    <div>
                                        Tài khoản của tôi
                                    </div>

                                    <form action="/ban-hang-online/hoa-don-online" method="post">
                                        <input name="idkh" value="${idkhachhang}" style="display: none">
                                        <button type="submit">Đơn hàng</button>
                                    </form>
                                    <div>
                                        Đăng xuất
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
                        <a href="#" class="logo">
                            <h2 class="logo" style="margin: 20px;color: white;font-family: 'Times New Roman'">GPhoneS
                                Store</h2>
                        </a>
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
                        <div>

                        </div>
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
                                    <div class="cart-dropdown">
                                        <div class="cart-list">


                                        </div>
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


<br>
<br>
<div class="container shadow p-3 mb-5 bg-body-tertiary rounded" id="pis">
    <div class="header">
        <h1>Đặt hàng thành công</h1>
    </div>
    <div class="thongbao">
        <h1>Chúc mừng bạn đã đặt hàng thành công!</h1>
        <p>Đơn hàng của bạn sẽ được nhân viên của của hàng xác nhận trong thời gian sớm nhất.</p>
    </div>
    <div class="footer">
        <a href="/ban-hang-online/home">
            <button class="nut" style="width: 170px">Trở về trang chủ</button>
        </a>
        <a href="/ban-hang-online/xem-gio-hang">
            <button class="nut" style="width: 170px">Tiếp tục mua hàng</button>
        </a>
    </div>
</div>





<!-- NEWSLETTER -->
<div id="newsletter" class="section">
    <!-- container -->
    <div class="container">
        <!-- row -->
        <div class="row">
            <div class="col-md-12">
                <div class="newsletter">
                    <p>Sign Up for the <strong>NEWSLETTER</strong></p>
                    <form>
                        <input class="input" type="email" placeholder="Enter Your Email">
                        <button class="newsletter-btn"><i class="fa fa-envelope"></i> Subscribe</button>
                    </form>
                    <ul class="newsletter-follow">
                        <li>
                            <a href="#"><i class="fa fa-facebook"></i></a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-twitter"></i></a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-instagram"></i></a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-pinterest"></i></a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</div>
<!-- /NEWSLETTER -->

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




<script>
    function chonhetgiohangtongTRANGCHU(idgh) {
        // var  idgh1=encodeURIComponent(idgh)
        if (document.getElementsByName('checktongTT')[0].checked == true) {
            var link = '/ban-hang-online/trang-chu/chon-san-pham-gio-hang-trang-chu/full/0/' + idgh;
            // document.getElementById("ktlink").innerHTML=link

            loadgiaodienghctbanhangTrangChu(link);


        } else {
            var link = '/ban-hang-online/trang-chu/chon-san-pham-gio-hang-trang-chu/full/1/' + idgh;
            // document.getElementById("ktlink").innerHTML=link

            loadgiaodienghctbanhangTrangChu(link);

        }
        loadgiaodienghctbanhang('/ban-hang-online/single_page_gio_hang_chi_tiet');
        loadgiaodienghctbanhang('/ban-hang-online/single_page_gio_hang_chi_tiet');

        // alert("vdvdvd")
    };

    function chonsanphamgiohangTT(vt, idctgh, idgh) {
        // var  idgh1=encodeURIComponent(idgh)
        var vt1 = parseInt(vt);
        // var id1=encodeURIComponent(id);
        if (document.getElementsByName('checkidghTT')[vt1].checked == true) {
            var link = '/ban-hang-online/trang-chu/chon-san-pham-gio-hang-trang-chu/' + idctgh + '/0/' + idgh;
            // document.getElementById("ktlink").innerHTML=link

            loadgiaodienghctbanhangTrangChu(link);
            // loadgiaodienghctbanhangTrangChu(link);
            // loadgiaodienghctbanhang('/ban-hang-online/single_page_gio_hang_chi_tiet');

        } else {
            var link = '/ban-hang-online/trang-chu/chon-san-pham-gio-hang-trang-chu/' + idctgh + '/1/' + idgh;
            // document.getElementById("ktlink").innerHTML=link

            loadgiaodienghctbanhangTrangChu(link);
            // loadgiaodienghctbanhangTrangChu(link);
            // loadgiaodienghctbanhang('/ban-hang-online/single_page_gio_hang_chi_tiet');

        }

        loadgiaodienghctbanhang('/ban-hang-online/single_page_gio_hang_chi_tiet');
        loadgiaodienghctbanhang('/ban-hang-online/single_page_gio_hang_chi_tiet');


    };

    function loadgiaodienghctbanhangTrangChu(interfaceUrl) {
        fetch(interfaceUrl)
            .then(response => response.text())
            .then(data => {
                const content = document.getElementById('giohangtrangchu');
                content.innerHTML = data;

                loadScripts();


            })
            .catch(error => {
                console.error('Error loading interface:', error);
            });


    }


    function thanhtienbenghct() {
        var checkdongiakhigiam = document.getElementsByName('checkdongiakhigiam');
        var checksoluong = document.getElementsByName('checksoluong');
        var checkthanhtien = document.getElementsByName('checkthanhtien');
        for (var i = 0; i < checkthanhtien.length; i++) {

            checkthanhtien[i].innerHTML = parseInt(checkdongiakhigiam[i].innerHTML) * parseInt(checksoluong[i].value);
        }
    };
    thanhtienbenghct();

    function loadgiaodienghctbanhang(interfaceUrl) {
        fetch(interfaceUrl)
            .then(response => response.text())
            .then(data => {
                const content = document.getElementById('content');
                content.innerHTML = data;
                thanhtienbenghct();
                loadScripts();
            })
            .catch(error => {
                console.error('Error loading interface:', error);
            });
    }

    function loadScripts() {
        const scriptsToLoad = [
            // '/jsbanhang/jquery.min.js',
            // '/jsbanhang/bootstrap.min.js',
            // '/jsbanhang/slick.min.js',
            // '/jsbanhang/nouislider.min.js',
            // '/jsbanhang/jquery.zoom.min.js',
            // '/jsbanhang/main.js'

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


</script>
<script src="/jsbanhang/jquery.min.js"></script>
<script src="/jsbanhang/bootstrap.min.js"></script>
<script src="/jsbanhang/slick.min.js"></script>
<script src="/jsbanhang/nouislider.min.js"></script>
<script src="/jsbanhang/jquery.zoom.min.js"></script>
<script src="/jsbanhang/main.js"></script>
</body>
</html>