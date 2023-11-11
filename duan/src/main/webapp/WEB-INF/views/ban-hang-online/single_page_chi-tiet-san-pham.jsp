<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
</head>
<body>
<c:if test="${ktcokhong==1}">
    <div class="section">
        <!-- container -->
        <div class="container">
            <!-- row -->
            <div style="">
                    <%--            <!-- Product details -->--%>
                <div style="width: 42%;float: right">
                    <div class="product-details">
                        <h2 class="product-name">${motctsp.sanPham.ten}</h2>

                        <div>
                            <h4 class="product-price"><span
                                    style="font-size:15px">₫</span>${motctsp.giaBan-motctsp.giaBan/100*banhangonline.tonggiamgia(motctsp.id)}-
                                <del class="product-old-price">${motctsp.giaBan}<span style="font-size:15px">₫</span>
                                </del>
                            </h4>

                        </div>
                        <div class="add-to-cart" style="margin-top: 7cm">
                            Số lượng :<label style="background: white;color: red;border: 1px solid white"
                                             id="thongbaosoluong"></label>
                            <br>
                            <c:if test="${idkhachhang=='1'}">
                            <div class="qty-label">
                                <div class="" style="margin-left: 1cm">

                                    <c:if test="${idkhachhang=='1'}">
                                        <div class="qty-label">
                                            <div class="" style="margin-left: 1cm">
                                                <BUTTON class="qty-down">-</BUTTON>
                                                <input type="number" value="1" min="1"
                                                       max="${banhangonline.soluongcon(motctsp.id)}" id="input2"
                                                       style="width: 2cm" name="solg">
                                                <BUTTON class="qty-up">+</BUTTON>
                                            </div>
                                        </div>
                                        <label style="background: white;border: 1px solid white">Số lượng còn
                                            :${banhangonline.soluongcon(motctsp.id)}</label>
                                        <br><br>
                                        <a href="/login">
                                            <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> Thêm vào
                                                giỏ hàng
                                            </button>
                                            <a href="/ban-hang-online/chi-tiet-san-pham/${motctsp.id}"
                                               id="loadlaictsp"></a>
                                        </a>
                                        <a href="/login">
                                            <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> Mua ngay
                                            </button>
                                        </a>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${idkhachhang!='1'}">
                            <form action="" method="post" id="formctsp">

                                <div class="qty-label">
                                    <div class="" style="margin-left: 1cm">
                                        <BUTTON class="qty-down" type="button">-</BUTTON>
                                        <input type="number" value="0" min="0"
                                               max="${banhangonline.soluongcon(motctsp.id)-banhangonline.sl1ctsptronggh(banhangonline.ListghTheoidkh(idkhachhang).get(0).getId(),motctsp.id)}"
                                               id="input2" style="width: 2cm" name="solg">
                                        <BUTTON class="qty-up" type="button">+</BUTTON>
                                    </div>
                                </div>


                                <label style="background: white;border: 1px solid white">Số lượng còn
                                    :${banhangonline.soluongcon(motctsp.id)}</label>
                                <br><br>

                                    <%--                            <p>${idkhachhang}----${motctsp.id}</p>--%>
                                <button class="add-to-cart-btn" type="button"
                                        onclick="thongbaothemvaogiohang('${motctsp.id}');"><i
                                        class="fa fa-shopping-cart"></i> Thêm vào giỏ hàng
                                </button>
                                <button class="add-to-cart-btn" id="btthanhtoam" type="button"
                                        onclick="clickthanhtoanctsp()"><i class="fa fa-shopping-cart"></i> Mua ngay
                                </button>


                                <input style="display:none;" value="${idkhachhang}" name="idkh">
                                <input style="display:none;" value="${motctsp.id}" name="idctsp">

                            </form>
                        </c:if>


                    </div>
                    <br>
                    <p>
                    <div class="product-description">
                        <h3>Thông tin chi tiết sản phẩm:</h3>
                        <table class="product-info-table">
                            <tr>
                                <td class="info-label">Hãng sản phẩm:</td>
                                <td>${motctsp.sanPham.hangSanPham.ten}</td>
                            </tr>
                            <tr>
                                <td class="info-label">Camera:</td>
                                <td>${motctsp.sanPham.camera.thongSo}</td>
                            </tr>
                            <tr>
                                <td class="info-label">Màn:</td>
                                <td>${motctsp.sanPham.manHinh.thongSo}</td>
                            </tr>
                            <tr>
                                <td class="info-label">Màu:</td>
                                <td>${motctsp.mauSac.ten}</td>
                            </tr>
                            <tr>
                                <td class="info-label">Ram:</td>
                                <td>${motctsp.ram.dungLuong}</td>
                            </tr>
                            <tr>
                                <td class="info-label">Rom:</td>
                                <td>${motctsp.rom.dungLuong}</td>
                            </tr>
                            <tr>
                                <td class="info-label">Pin:</td>
                                <td>${motctsp.pin.loaiPin}</td>
                            </tr>
                            <tr>
                                <td class="info-label">Dung lượng pin:</td>
                                <td>${motctsp.pin.dungLuongPin.thongSo}</td>
                            </tr>
                            <tr>
                                <td class="info-label">Chip:</td>
                                <td>${motctsp.chip.ten}</td>
                            </tr>
                        </table>
                    </div>

                    <div class="row">
                        <button class=" btn btn-info">Xem thêm cấu hình chi tiết</button>
                    </div>
                    </p>

                </div>
            </div>
            <!-- /Product details -->
            <!-- Product main img -->
                <%--            class="col-md-5 col-md-push-2"--%>
            <div style="height: 48%;width: 50%;margin-left: 2%">
                <div id="product-main-img">
                    <div class="product-preview">
                        <img src="../../../uploads/${motctsp.urlAnh}" alt="" style="width: 100%;height: 12cm">
                    </div>

                    <div class="product-preview">
                        <img src="../../uploads/${motctsp.sanPham.anh.anh1}" alt="" style="width: 100%;height: 12cm; ">
                    </div>

                    <div class="product-preview">
                        <img src="../../uploads/${motctsp.sanPham.anh.anh2}" alt="" style="width: 100%;height: 12cm; ">
                    </div>

                    <div class="product-preview">
                        <img src="../../uploads/${motctsp.sanPham.anh.anh3}" alt="" style="width: 100%;height: 12cm; ">
                    </div>
                </div>
            </div>
            <!-- /Product main img -->


            <!-- Product thumb imgs -->
                <%--            class="col-md-2  col-md-pull-5"--%>
            <div style=" width: 17.2cm; margin-top: 10px; height: 5cm;" align="center">
                <div id="product-imgs" style=" width: 5cm;height: 16cm;margin-top: -5.5cm; transform: rotate(270deg);">

                    <div class="product-preview">
                        <img src="../../../uploads/${motctsp.urlAnh}" alt=""
                             style="height: 5cm;width:4.9cm;transform: rotate(90deg);">
                    </div>

                    <div class="product-preview">
                        <img src="../../uploads/${motctsp.sanPham.anh.anh1}" alt=""
                             style="height: 5cm;width:4.9cm;transform: rotate(90deg);">
                    </div>

                    <div class="product-preview">
                        <img src="../../uploads/${motctsp.sanPham.anh.anh2}" alt=""
                             style="height: 5cm;width:4.9cm;transform: rotate(90deg);">
                    </div>

                    <div class="product-preview">
                        <img src="../../uploads/${motctsp.sanPham.anh.anh3}" alt=""
                             style="height: 5cm;width:4.9cm;transform: rotate(90deg);">
                    </div>
                </div>
            </div>

                <%--            <!-- /Product thumb imgs -->--%>


            <!-- Product tab -->
            <div class="col-md-12">
                <div id="product-tab">
                    <!-- product tab nav -->
                    <ul class="tab-nav">
                        <li class="active"><a data-toggle="tab" href="#tab1">Description</a></li>
                        <li><a data-toggle="tab" href="#tab2">Details</a></li>
                        <li><a data-toggle="tab" href="#tab3">Reviews (3)</a></li>
                    </ul>
                    <!-- /product tab nav -->

                    <!-- product tab content -->
                    <div class="tab-content">
                        <!-- tab1  -->
                        <div id="tab1" class="tab-pane fade in active">
                            <div class="row">
                                <div class="col-md-12">
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor
                                        incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis
                                        nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                                        Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu
                                        fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in
                                        culpa qui officia deserunt mollit anim id est laborum.</p>
                                </div>
                            </div>
                        </div>
                        <!-- /tab1  -->

                        <!-- tab2  -->
                        <div id="tab2" class="tab-pane fade in">
                            <div class="row">
                                <div class="col-md-12">
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor
                                        incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis
                                        nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                                        Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu
                                        fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in
                                        culpa qui officia deserunt mollit anim id est laborum.</p>
                                </div>
                            </div>
                        </div>
                        <!-- /tab2  -->

                        <!-- tab3  -->
                        <div id="tab3" class="tab-pane fade in">
                            <div class="row">
                                <!-- Rating -->
                                <div class="col-md-3">
                                    <div id="rating">
                                        <div class="rating-avg">
                                            <span>4.5</span>
                                            <div class="rating-stars">
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star-o"></i>
                                            </div>
                                        </div>
                                        <ul class="rating">
                                            <li>
                                                <div class="rating-stars">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                </div>
                                                <div class="rating-progress">
                                                    <div style="width: 80%;"></div>
                                                </div>
                                                <span class="sum">3</span>
                                            </li>
                                            <li>
                                                <div class="rating-stars">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star-o"></i>
                                                </div>
                                                <div class="rating-progress">
                                                    <div style="width: 60%;"></div>
                                                </div>
                                                <span class="sum">2</span>
                                            </li>
                                            <li>
                                                <div class="rating-stars">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star-o"></i>
                                                    <i class="fa fa-star-o"></i>
                                                </div>
                                                <div class="rating-progress">
                                                    <div></div>
                                                </div>
                                                <span class="sum">0</span>
                                            </li>
                                            <li>
                                                <div class="rating-stars">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star-o"></i>
                                                    <i class="fa fa-star-o"></i>
                                                    <i class="fa fa-star-o"></i>
                                                </div>
                                                <div class="rating-progress">
                                                    <div></div>
                                                </div>
                                                <span class="sum">0</span>
                                            </li>
                                            <li>
                                                <div class="rating-stars">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star-o"></i>
                                                    <i class="fa fa-star-o"></i>
                                                    <i class="fa fa-star-o"></i>
                                                    <i class="fa fa-star-o"></i>
                                                </div>
                                                <div class="rating-progress">
                                                    <div></div>
                                                </div>
                                                <span class="sum">0</span>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <!-- /Rating -->

                                <!-- Reviews -->
                                <div class="col-md-6">
                                    <div id="reviews">
                                        <ul class="reviews">
                                            <li>
                                                <div class="review-heading">
                                                    <h5 class="name">John</h5>
                                                    <p class="date">27 DEC 2018, 8:0 PM</p>
                                                    <div class="review-rating">
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star-o empty"></i>
                                                    </div>
                                                </div>
                                                <div class="review-body">
                                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do
                                                        eiusmod tempor incididunt ut labore et dolore magna aliqua</p>
                                                </div>
                                            </li>
                                            <li>
                                                <div class="review-heading">
                                                    <h5 class="name">John</h5>
                                                    <p class="date">27 DEC 2018, 8:0 PM</p>
                                                    <div class="review-rating">
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star-o empty"></i>
                                                    </div>
                                                </div>
                                                <div class="review-body">
                                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do
                                                        eiusmod tempor incididunt ut labore et dolore magna aliqua</p>
                                                </div>
                                            </li>
                                            <li>
                                                <div class="review-heading">
                                                    <h5 class="name">John</h5>
                                                    <p class="date">27 DEC 2018, 8:0 PM</p>
                                                    <div class="review-rating">
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star-o empty"></i>
                                                    </div>
                                                </div>
                                                <div class="review-body">
                                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do
                                                        eiusmod tempor incididunt ut labore et dolore magna aliqua</p>
                                                </div>
                                            </li>
                                        </ul>
                                        <ul class="reviews-pagination">
                                            <li class="active">1</li>
                                            <li><a href="#">2</a></li>
                                            <li><a href="#">3</a></li>
                                            <li><a href="#">4</a></li>
                                            <li><a href="#"><i class="fa fa-angle-right"></i></a></li>
                                        </ul>
                                    </div>
                                </div>
                                <!-- /Reviews -->

                                <!-- Review Form -->
                                <div class="col-md-3">
                                    <div id="review-form">
                                        <form class="review-form">
                                            <input class="input" type="text" placeholder="Your Name">
                                            <input class="input" type="email" placeholder="Your Email">
                                            <textarea class="input" placeholder="Your Review"></textarea>
                                            <div class="input-rating">
                                                <span>Your Rating: </span>
                                                <div class="stars">
                                                    <input id="star5" name="rating" value="5" type="radio"><label
                                                        for="star5"></label>
                                                    <input id="star4" name="rating" value="4" type="radio"><label
                                                        for="star4"></label>
                                                    <input id="star3" name="rating" value="3" type="radio"><label
                                                        for="star3"></label>
                                                    <input id="star2" name="rating" value="2" type="radio"><label
                                                        for="star2"></label>
                                                    <input id="star1" name="rating" value="1" type="radio"><label
                                                        for="star1"></label>
                                                </div>
                                            </div>
                                            <button class="primary-btn">Submit</button>
                                        </form>
                                    </div>
                                </div>
                                <!-- /Review Form -->
                            </div>
                        </div>
                        <!-- /tab3  -->
                    </div>
                    <!-- /product tab content  -->
                </div>
            </div>
            <!-- /product tab -->

        </div>
        <!-- /row -->
    </div>
</c:if>

<c:if test="${ktcokhong==0}">
    <!-- SECTION -->
    <div class="section">
        <!-- container -->
        <div class="container">
            <!-- row -->
            <div>


                    <%--            <!-- Product details -->--%>
                <div style="width: 42%;float: right">
                    <div class="product-details">
                        <h2 class="product-name">${tensp}</h2>

                        <div>
                            <h4 class="product-price"><span style="font-size:15px">₫</span>0-
                                <del class="product-old-price">0<span style="font-size:15px">₫</span></del>
                            </h4>
                        </div>


                        <div class="add-to-cart" style="margin-top: 7cm">
                            Số lượng :<label style="background: white;color: red;border: 1px solid white"
                                             id="thongbaosoluong"></label>
                            <br>
                            <div class="qty-label">

                                <div class="" style="margin-left: 1cm">
                                    <BUTTON class="qty-down">-</BUTTON>
                                    <input type="number" value="1" min="1" max="0" id="input2" style="width: 2cm">
                                    <BUTTON class="qty-up">+</BUTTON>
                                </div>
                            </div>
                            <label style="background: white;border: 1px solid white">Số lượng còn :0</label>
                            <br><br>
                            <c:if test="${idkhachhang=='1'}">
                                <a href="/login">
                                    <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> Thêm vào giỏ
                                        hàng
                                    </button>
                                </a>
                                <a href="/login">
                                    <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> Mua ngay
                                    </button>

                                </a>
                            </c:if>
                            <c:if test="${idkhachhang!='1'}">

                                <button class="add-to-cart-btn" onclick="alert('Sản phẩm này đã ngừng bán')"><i
                                        class="fa fa-shopping-cart"></i> Thêm vào giỏ hàng
                                </button>


                                <button class="add-to-cart-btn" onclick="alert('Sản phẩm này đã ngừng bán')"><i
                                        class="fa fa-shopping-cart"></i> Mua ngay
                                </button>


                            </c:if>
                        </div>


                        <div class="product-description">
                            <h3>Thông tin chi tiết sản phẩm:</h3>
                            <table class="product-info-table">
                                <tr>
                                    <td class="info-label">Hãng sản phẩm:</td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td class="info-label">Camera:</td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td class="info-label">Màn:</td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td class="info-label">Màu:</td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td class="info-label">Ram:</td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td class="info-label">Rom:</td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td class="info-label">Pin:</td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td class="info-label">Dung lượng pin:</td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td class="info-label">Chip:</td>
                                    <td></td>
                                </tr>
                            </table>
                        </div>

                    </div>
                </div>
                <!-- /Product details -->
                <!-- Product main img -->
                    <%--            class="col-md-5 col-md-push-2"--%>
                <div style="height: 48%;width: 50%;margin-left: 2%">
                    <div id="product-main-img">
                        <div class="product-preview">
                            <img src="../../../uploads/${motctsp.urlAnh}" alt="" style="width: 100%;height: 12cm">
                        </div>

                        <div class="product-preview">
                            <img src="../../uploads/${motctsp.sanPham.anh.anh1}" alt=""
                                 style="width: 100%;height: 12cm; ">
                        </div>

                        <div class="product-preview">
                            <img src="../../uploads/${motctsp.sanPham.anh.anh2}" alt=""
                                 style="width: 100%;height: 12cm; ">
                        </div>

                        <div class="product-preview">
                            <img src="../../uploads/${motctsp.sanPham.anh.anh3}" alt=""
                                 style="width: 100%;height: 12cm; ">
                        </div>
                    </div>
                </div>
                <!-- /Product main img -->


                <!-- Product thumb imgs -->
                    <%--            class="col-md-2  col-md-pull-5"--%>
                <div style=" width: 17.2cm; margin-top: 10px; height: 5cm;" align="center">
                    <div id="product-imgs"
                         style=" width: 5cm;height: 16cm;margin-top: -5.5cm; transform: rotate(270deg);">

                        <div class="product-preview">
                            <img src="../../../uploads/${motctsp.urlAnh}" alt=""
                                 style="height: 5cm;width:4.9cm;transform: rotate(90deg);">
                        </div>

                        <div class="product-preview">
                            <img src="../../uploads/${motctsp.sanPham.anh.anh1}" alt=""
                                 style="height: 5cm;width:4.9cm;transform: rotate(90deg);">
                        </div>

                        <div class="product-preview">
                            <img src="../../uploads/${motctsp.sanPham.anh.anh2}" alt=""
                                 style="height: 5cm;width:4.9cm;transform: rotate(90deg);">
                        </div>

                        <div class="product-preview">
                            <img src="../../uploads/${motctsp.sanPham.anh.anh3}" alt=""
                                 style="height: 5cm;width:4.9cm;transform: rotate(90deg);">
                        </div>
                    </div>
                </div>

                    <%--            <!-- /Product thumb imgs -->--%>


                <!-- Product tab -->
                <div class="col-md-12">
                    <div id="product-tab">
                        <!-- product tab nav -->
                        <ul class="tab-nav">
                            <li class="active"><a data-toggle="tab" href="#tab1">Description</a></li>
                            <li><a data-toggle="tab" href="#tab2">Details</a></li>
                            <li><a data-toggle="tab" href="#tab3">Reviews (3)</a></li>
                        </ul>
                        <!-- /product tab nav -->

                        <!-- product tab content -->
                        <div class="tab-content">
                            <!-- tab1  -->
                            <div id="tab1" class="tab-pane fade in active">
                                <div class="row">
                                    <div class="col-md-12">
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                            tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                                            quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                                            consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                                            cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat
                                            non proident, sunt in culpa qui officia deserunt mollit anim id est
                                            laborum.</p>
                                    </div>
                                </div>
                            </div>
                            <!-- /tab1  -->

                            <!-- tab2  -->
                            <div id="tab2" class="tab-pane fade in">
                                <div class="row">
                                    <div class="col-md-12">
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                            tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                                            quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                                            consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                                            cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat
                                            non proident, sunt in culpa qui officia deserunt mollit anim id est
                                            laborum.</p>
                                    </div>
                                </div>
                            </div>
                            <!-- /tab2  -->

                            <!-- tab3  -->
                            <div id="tab3" class="tab-pane fade in">
                                <div class="row">
                                    <!-- Rating -->
                                    <div class="col-md-3">
                                        <div id="rating">
                                            <div class="rating-avg">
                                                <span>4.5</span>
                                                <div class="rating-stars">
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star-o"></i>
                                                </div>
                                            </div>
                                            <ul class="rating">
                                                <li>
                                                    <div class="rating-stars">
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                    </div>
                                                    <div class="rating-progress">
                                                        <div style="width: 80%;"></div>
                                                    </div>
                                                    <span class="sum">3</span>
                                                </li>
                                                <li>
                                                    <div class="rating-stars">
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star-o"></i>
                                                    </div>
                                                    <div class="rating-progress">
                                                        <div style="width: 60%;"></div>
                                                    </div>
                                                    <span class="sum">2</span>
                                                </li>
                                                <li>
                                                    <div class="rating-stars">
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star-o"></i>
                                                        <i class="fa fa-star-o"></i>
                                                    </div>
                                                    <div class="rating-progress">
                                                        <div></div>
                                                    </div>
                                                    <span class="sum">0</span>
                                                </li>
                                                <li>
                                                    <div class="rating-stars">
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star-o"></i>
                                                        <i class="fa fa-star-o"></i>
                                                        <i class="fa fa-star-o"></i>
                                                    </div>
                                                    <div class="rating-progress">
                                                        <div></div>
                                                    </div>
                                                    <span class="sum">0</span>
                                                </li>
                                                <li>
                                                    <div class="rating-stars">
                                                        <i class="fa fa-star"></i>
                                                        <i class="fa fa-star-o"></i>
                                                        <i class="fa fa-star-o"></i>
                                                        <i class="fa fa-star-o"></i>
                                                        <i class="fa fa-star-o"></i>
                                                    </div>
                                                    <div class="rating-progress">
                                                        <div></div>
                                                    </div>
                                                    <span class="sum">0</span>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                    <!-- /Rating -->

                                    <!-- Reviews -->
                                    <div class="col-md-6">
                                        <div id="reviews">
                                            <ul class="reviews">
                                                <li>
                                                    <div class="review-heading">
                                                        <h5 class="name">John</h5>
                                                        <p class="date">27 DEC 2018, 8:0 PM</p>
                                                        <div class="review-rating">
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star-o empty"></i>
                                                        </div>
                                                    </div>
                                                    <div class="review-body">
                                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed
                                                            do eiusmod tempor incididunt ut labore et dolore magna
                                                            aliqua</p>
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="review-heading">
                                                        <h5 class="name">John</h5>
                                                        <p class="date">27 DEC 2018, 8:0 PM</p>
                                                        <div class="review-rating">
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star-o empty"></i>
                                                        </div>
                                                    </div>
                                                    <div class="review-body">
                                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed
                                                            do eiusmod tempor incididunt ut labore et dolore magna
                                                            aliqua</p>
                                                    </div>
                                                </li>
                                                <li>
                                                    <div class="review-heading">
                                                        <h5 class="name">John</h5>
                                                        <p class="date">27 DEC 2018, 8:0 PM</p>
                                                        <div class="review-rating">
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star-o empty"></i>
                                                        </div>
                                                    </div>
                                                    <div class="review-body">
                                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed
                                                            do eiusmod tempor incididunt ut labore et dolore magna
                                                            aliqua</p>
                                                    </div>
                                                </li>
                                            </ul>
                                            <ul class="reviews-pagination">
                                                <li class="active">1</li>
                                                <li><a href="#">2</a></li>
                                                <li><a href="#">3</a></li>
                                                <li><a href="#">4</a></li>
                                                <li><a href="#"><i class="fa fa-angle-right"></i></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <!-- /Reviews -->

                                    <!-- Review Form -->
                                    <div class="col-md-3">
                                        <div id="review-form">
                                            <form class="review-form">
                                                <input class="input" type="text" placeholder="Your Name">
                                                <input class="input" type="email" placeholder="Your Email">
                                                <textarea class="input" placeholder="Your Review"></textarea>
                                                <div class="input-rating">
                                                    <span>Your Rating: </span>
                                                    <div class="stars">
                                                        <input id="star5" name="rating" value="5" type="radio"><label
                                                            for="star5"></label>
                                                        <input id="star4" name="rating" value="4" type="radio"><label
                                                            for="star4"></label>
                                                        <input id="star3" name="rating" value="3" type="radio"><label
                                                            for="star3"></label>
                                                        <input id="star2" name="rating" value="2" type="radio"><label
                                                            for="star2"></label>
                                                        <input id="star1" name="rating" value="1" type="radio"><label
                                                            for="star1"></label>
                                                    </div>
                                                </div>
                                                <button class="primary-btn">Submit</button>
                                            </form>
                                        </div>
                                    </div>
                                    <!-- /Review Form -->
                                </div>
                            </div>
                            <!-- /tab3  -->
                        </div>
                        <!-- /product tab content  -->
                    </div>
                </div>
                <!-- /product tab -->

            </div>
            <!-- /row -->
        </div>
        <!-- /container -->
    </div>
    <!-- /SECTION -->


</c:if>


<%--    <div style="position: fixed;top: 50%;left: 50%;transform: translate(-50%,-50%);z-index: 2;width: 10cm;height: 1cm;background-color: #00FF00;text-align: center;display: none;border: 5px solid black " id="thongbaothemgiohang">--%>
<%--        <h2>Đã Thêm vào Giỏ hàng</h2>--%>
<%--    </div>--%>

</body>
</html>
