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

    <title>Thanh toán</title>
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
    <link rel="stylesheet" href="/cssbanhang/font-awesome.min.css">

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
            /* CSS để thiết lập chiều dài và chiều cao của dropdown select2 */
            .select2-container--bootstrap-5 .select2-selection--single {
                /*width: 5cm !important; !* Thiết lập chiều dài là 5cm *!*/
                height: 1cm !important;
                font-size: 14px;
              padding-top: 7px;
            }
        </style>
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

<body>
<!-- HEADER -->
<header>
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
                            <div class="cart-dropdown"
                                 style="border-radius: 10px;width: 3.5cm;margin-top: 10px;width: 180px">
                                <div>
                                    <div>
                                        <form action="/thong-tin-ca-nhan-khach-hang" method="post"
                                              style="display: none">
                                            <input value="${idkhachhang}" name="idKhachHang" style="display: none">
                                            <button style="" class="btn btn-primary" type="submit" id="taikhoancuatoi">
                                                Tài khoản của tôi
                                            </button>
                                        </form>
                                        <a class="btn btn-primary" type="submit" onclick="anbt()">Tài khoản của tôi</a>

                                    </div>
                                    <div>
                                        <a href="/ban-hang-online/hoa-don-online/${idkhachhang}"
                                           class="btn btn-primary">Đơn hàng</a>
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
                        <h2 class="logo" style="margin: 20px;color: white;font-family: 'Times New Roman'">GPhoneS
                            Store</h2>
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
                                <c:if test="${listghctTT.size()>0}">
                                    <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                                        <i class="fa fa-shopping-cart"></i>
                                        <span>Giỏ hàng</span>
                                        <div class="qty">${banhangonline.ListghctTheoidgh(banhangonline.ListghTheoidkh(idkhachhang).get(0).getId()).size()}</div>
                                    </a>
                                    <div class="cart-dropdown" style="width:  13cm">
                                        <div class="cart-list">

                                            <c:forEach items="${listghctTT}" var="ht" varStatus="stt">
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
                                                            <%--                                                        <input type="checkbox" name="checkidghTT" value="${ht.id}" onclick="chonsanphamgiohangTT('${stt.index}','${ht.id}','${ht.gioHang.id}');"  ${ht.tinhTrang==0 ?"checked":""}>--%>


                                                        <img src="../../../uploads/${ht.chiTietSanPham.urlAnh}"
                                                             width="50" height="50"
                                                             style="border-radius:50% 50% 50% 50%;border: 1px solid black">
                                                    </div>

                                                </div>
                                            </c:forEach>
                                        </div>
                                        <div class="cart-summary">
                                            <small> ${banhangonline.TongtienvsTongspchon(listghctTT.get(0).gioHang.id).gettongsanphamchon()}
                                                Sản phẩm được chọn</small>
                                            <h5>
                                                Tổng:${banhangonline.TongtienvsTongspchon(listghctTT.get(0).gioHang.id).gettongtien()}
                                                đ</h5>
                                        </div>
                                        <div class="cart-btns">
                                            <a href="/ban-hang-online/xem-gio-hang" style="width: 100%">Xem giỏ hàng</a>
                                                <%--                                            <a href="#">Chọn hết--%>
                                                <%--                                                <input type="checkbox" name="checktongTT" onclick="chonhetgiohangtongTRANGCHU('${listghctTT.get(0).gioHang.id}');"  ${tttong==0 ?"checked":""}>--%>
                                                <%--                                            </a>--%>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${listghctTT.size()<=0}">
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

<main id="content">

    <div class="section">
        <!-- container -->
        <div class="shadow p-3 mb-5 bg-body-tertiary rounded" style="width: 85%;margin-left: 7.5%;">
            <!-- row -->
            <div class="row">

                <div class="col-md-6">
                    <!-- Billing Details -->
                    <div class="billing-details">
                        <div class="section-title">
                            <h3 class="title">Thanh toán</h3>
                        </div>
                        <div class="form-group">
                            <input class="input" type="text" name="first-name"
                                   value="${listghct.get(0).gioHang.khachHang.hoTen}" placeholder="First Name" disabled>
                        </div>

                        <div class="form-group">
                            <input class="input" type="email" value="${listghct.get(0).gioHang.khachHang.email}"
                                   name="email" placeholder="Email" disabled>
                        </div>
                        <div class="form-group">
                            <input class="input" id="sodienthoai1" type="tel" name="tel"
                                   value="${listghct.get(0).gioHang.khachHang.sdt}"
                                   placeholder="Telephone">
                        </div>
                        <button style="float: right" class="btn btn-primary" data-bs-toggle="modal"
                                data-bs-target="#modalthemdiachidathang">Thêm địa chỉ
                        </button>

                        <select class="form-control" id="diachids1" style="width: 80%; height: 30cm;">
                            <c:forEach
                                    items="${banhangonline.Listdiachimotkhachang(listghct.get(0).gioHang.khachHang.id)}"
                                    var="ht">
                                <option value="${ht.id}">${ht.diaChi} - ${ht.quan} - ${ht.huyen}
                                    - ${ht.thanhPho}</option>
                            </c:forEach>
                        </select>


                    </div>

                </div>

                <!-- Order Details -->
                <div class="col-md-6 order-details">
                    <div class="section-title text-center">
                        <h3 class="title">Sản Phẩm</h3>
                    </div>
                    <div class="order-summary">
                        <div class="order-col">
                            <div><strong>Sản phẩm</strong></div>

                            <div><strong>Thành tiền</strong></div>
                        </div>
                        <c:forEach items="${listghct}" var="ht" varStatus="stt">
                            <div class="order-products">
                                <div class="order-col">
                                    <div><img src="/uploads/${ht.chiTietSanPham.urlAnh}" height="50px" width="50px">
                                    </div>
                                    <div> ${ht.chiTietSanPham.sanPham.ten}
                                        - ${ht.chiTietSanPham.mauSac.ten} - ${ht.chiTietSanPham.ram.dungLuong}
                                        - ${ht.chiTietSanPham.rom.dungLuong}</div>
                                    <div>${ht.donGiaKhiGiam*ht.soLuong}</div>
                                </div>

                            </div>
                        </c:forEach>
                        <div class="order-col">
                            <div><strong>Số lượng sản phẩm chọn</strong></div>
                            <div id="tongsanphamchon">
                                <strong
                                        class="order-total" style="color: black">
                                    ${banhangonline.TongtienvsTongspchon(listghct.get(0).gioHang.id).gettongsanphamchon()}

                                </strong>
                            </div>

                        </div>
                        <div class="order-col">
                            <div><strong>Tổng tiền</strong></div>
                            <div id="tongthanhtien">
                                <strong
                                        class="order-total"> ${banhangonline.TongtienvsTongspchon(listghct.get(0).gioHang.id).gettongtien()}
                                </strong>
                            </div>

                        </div>
                    </div>


                    <%--                    <button type="button" style="width: 100%" class="primary-btn order-submit"--%>
                    <%--                            onclick="nutdathang('${listghct.get(0).gioHang.id}')">Đặt hàng--%>
                    <%--                    </button>--%>
                    <form action="/ban-hang-online/san-pham-duoc-chon-thanh-toan/nut-dat-hang" method="post">
                        <div style="display: none">
                            <input id="idgh1" name="idgh1">
                            <input id="tongtien1" name="tongtien1">
                            <input id="iddc1" name="iddc1">
                            <input id="sdt1" name="sdt1">
                        </div>
                        <input type="checkbox" id="checkBox"> Tôi đã đọc và đồng ý với <a style="text-decoration-line: underline"
                                                                            data-bs-toggle="modal"
                                                                            data-bs-target="#modalChinhSach">chính sách
                        của cửa hàng</a>
                        <%--                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalChinhSach">--%>
                        <%--                            Launch demo modal--%>
                        <%--                        </button>--%>
                        <%--                        <a data-bs-toggle="modal"--%>
                        <%--                           data-bs-target="#modalthemdiachidathang">a</a>--%>


                        <button class="primary-btn order-submit" style="float: right ; display: none" type="button"
                                id="nutdathangthanhtoan" onclick="nutdathang('${listghct.get(0).gioHang.id}')">Đặt Hàng
                        </button>
                    </form>
                </div>

                <!-- /Order Details -->
            </div>
            <!-- /row -->
        </div>
        <!-- /container -->
    </div>

    <%--modal them dia chi dat hang--%>
    <!-- The Modal -->
    <div class="modal" id="modalthemdiachidathang">
        <div class="modal-dialog">
            <div class="modal-content" style="">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Thêm địa chỉ</h4>
                </div>
                <form action="/ban-hang-online/san-pham-duoc-chon-thanh-toan/nut-them-dia-chi" method="post">
                    <!-- Modal body -->
                    <div class="modal-body">
                        <div style="margin-left:2.5cm ">
                            <div>Địa chỉ:<label style="background: white;color: red;border: 1px solid white"
                                                id="tb1"></label></div>
                            <input type="text" id="themdiachidathangdiachi" name="diachi"><br>
                            <div>Quận:<label style="background: white;color: red;border: 1px solid white"
                                             id="tb2"></label></div>
                            <input type="text" id="themdiachidathangquan" name="quan"><br>
                            <div>Huyện:<label style="background: white;color: red;border: 1px solid white"
                                              id="tb3"></label></div>
                            <input type="text" id="themdiachidathanghuyen" name="huyen"><br>
                            <div>Thành phố:<label style="background: white;color: red;border: 1px solid white"
                                                  id="tb4"></label></div>
                            <input type="text" id="themdiachidathangthanhpho" name="thanhpho"><br>
                        </div>
                        <input type="text" value="${listghct.get(0).gioHang.id}" name="idgh" style="display: none">
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-danger" onclick=" return themdiachikhachhang();">Thêm
                        </button>
                        <%--                <button type="button" style="display: none" id="btthemdiachidathang" data-bs-dismiss="modal"></button>--%>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="modal" id="modalChinhSach">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Chính sách</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    Để mang đến sự thuận tiện và trải nghiệm tốt nhất cho khách hàng, GphoneS Store có những chính sách
                    phù hợp khi khách hàng có nhu cầu đổi trả sản phẩm. Chúng tôi luôn mong muốn mang lại cho khách hàng
                    những sản phẩm và chất lượng phục vụ tốt nhất.
                    <br>

                    Việc đổi trả sản phẩm đi kèm một số điều kiện cụ thể, Quý khách vui lòng tham khảo thông tin chi
                    tiết bên dưới :
                    <br>

                    <b> 1. Điều kiện đổi sản phẩm:</b>
                    <br>

                    Sản phẩm không trùng khớp về màu sắc, mẫu mã, size theo đơn hàng.
                    <br>
                    Lỗi kỹ thuật do nhà sản xuất.
                    <br>
                    Sản phẩm đổi hàng phải còn mới, nguyên vẹn, không bị dính nước, không có dấu hiệu rơi vỡ
                    <br>
                    Sản phẩm phải còn đầy đủ tem, nhãn mác, thẻ bảo hành và hoá đơn mua hàng.
                    <br>
                    Sản phẩm đổi hàng còn trong thời hạn cho phép đổi trả.
                    <br>
                    Áp dụng đối với sản phẩm mua hàng online qua website và trực tiếp tại GPhoneS Store,
                    chúng tôi không hỗ trợ cho những đơn hàng mua tại địa điểm khác.

                    <br>
                    Khách hàng chỉ có thể trả sản phẩm với điều kiện GPhoneS Store không có sản phẩm cùng loại khác (màu sắc,
                    mẫu mã, kích thước) thay thế.
                    <br>
                    <b>2. Thời hạn đổi sản phẩm:</b>
                    <br>
                    Thời hạn đổi sản phẩm: 7 ngày đối với khách hàng mua online và 7 ngày đối với khách hàng mua trực
                    tiếp tại cửa hàng kể từ ngày khách nhận được hàng.
                    <br>
                    Nếu vượt quá thời gian quy định trên thì chúng tôi sẽ không nhận đổi sản phẩm với bất kì lý do
                    nào.
                    <br>
                    <b>3. Thời hạn thực hiện đổi lại sản phẩm cho khách hàng</b>
                    <br>
                    Đối với khách hàng mua online:
                    <br>
                    Ngay sau khi nhận được sản phẩm đổi của Quý khách hàng, chúng tôi sẽ tiến hành đánh giá sản phẩm và
                    thông báo kết quả và phương án giải quyết đổi đến khách hàng trong vòng 3 đến 5 ngày
                    <br>
                    Đối với khách hàng mua trực tiếp tại cửa hàng:
                    <br>
                    Khách hàng đem sản phẩm cần đổi đến trực tiếp tại cửa hàng trên hóa đơn, nhân viên cửa hàng sẽ kiểm
                    tra tình trạng sản phẩm và đưa ra hướng giải quyết ngay tại của hàng. Nhân viên tại cửa hàng sẽ tư
                    vấn cụ thể để khách hàng chọn lựa phương án tốt nhất.
                    <br>
                    <b> 4. Hình thức đổi sản phẩm:</b>

                    <br>
                    Sản phẩm chỉ được đổi một lần duy nhất
                    <br>
                    Quý khách vui lòng đến trực tiếp cửa hàng GPhoneS Store ghi trên hoá đơn mua hàng (đối với khách hàng ở Hồ
                    Chí Minh)
                    <br>
                    Đối với khách hàng ở khu vực khác, vui lòng gọi điện trực tiếp đến số điện thoại 098123456 hoặc gửi yêu cầu qua  mail gphonespluss@gmail.com Sau khi nhận được mail phản hồi xác nhận đồng ý từ
                    GPhoneS, Quý khách đóng gói sản phẩm kèm đầy đủ phụ kiện, hoá đơn, phiếu bảo hành, …và gửi về địa chỉ
                    số 3 phố Trịnh Văn Bô, Phương Canh, Nam Từ Liêm, Hà Nội.
                    <br>

                    <b> Lưu ý: GPhoneS Store khuyến khích Quý khách hàng nên chụp ảnh sản phẩm trước khi đổi hàng hóa, việc lưu
                        giữ sản phẩm sẽ giúp khách hàng làm bằng chứng nếu có những vấn đề phát sinh trong quá trình đổi
                        trả.
                    </b>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" id="btnAgree" onclick="onCheck()" data-bs-dismiss="modal">Tôi đồng ý</button>
                </div>
            </div>
        </div>
    </div>
</main>


<div style="color: #0C9A9A;display: none"></div>

<script>

    var checkBox = document.getElementById("checkBox");
    var button = document.getElementById("nutdathangthanhtoan");
    checkBox.addEventListener("change",function (){
        if (this.checked) {

            button.style.display =''; // Enable nút khi checkbox được chọn

        } else {
            button.style.display ='none';
        }
    })
    var btnAgree = document.getElementById("btnAgree");
    btnAgree.addEventListener("click",function (){
        checkBox.checked=true;
        button.style.display='';


    })

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


    function loadgiaodienghctbanhang(interfaceUrl) {
        fetch(interfaceUrl)
            .then(response => response.text())
            .then(data => {
                const content = document.getElementById('content');
                content.innerHTML = data;
                thanhtienbenghct();
                loadScripts();
                loadSelect2diachi();


            })
            .catch(error => {
                console.error('Error loading interface:', error);
            });


    }


    function loadScripts() {
        const scriptsToLoad = [];

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





    function loadSelect2diachi() {
        $('#diachids1').select2({
            theme: 'bootstrap-5',
            height: '5cm'
        });
    }

    loadSelect2diachi();
</script>
<script>
    function anbt() {
        document.getElementById('taikhoancuatoi').click();
    }
</script>
<!-- jQuery Plugins -->
<script src="/jsbanhang/jquery.min.js"></script>
<script src="/jsbanhang/bootstrap.min.js"></script>
<script src="/jsbanhang/slick.min.js"></script>
<script src="/jsbanhang/nouislider.min.js"></script>
<script src="/jsbanhang/jquery.zoom.min.js"></script>
<script src="/jsbanhang/main.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>


</body>
</html>
