
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<body>
<c:if test="${listghct.size()>0}">
    <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
        <i class="fa fa-shopping-cart"></i>
        <span>Giỏ hàng</span>
        <div class="qty">${banhangonline.ListghctTheoidgh(banhangonline.ListghTheoidkh(idkhachhang).get(0).getId()).size()}</div>
    </a>
    <div class="cart-dropdown" style="width:  13cm" >
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
                        <input type="checkbox" name="checkidghTT" value="${ht.id}" onclick="chonsanphamgiohangTT('${stt.index}','${ht.id}','${ht.gioHang.id}');"  ${ht.tinhTrang==0 ?"checked":""}>


                        <img src="../../../uploads/${ht.chiTietSanPham.urlAnh}" width="50" height="50" style="border-radius:50% 50% 50% 50%;border: 1px solid black">
                    </div>

                </div>
            </c:forEach>
        </div>
        <div class="cart-summary">
            <small> ${banhangonline.TongtienvsTongspchon(listghct.get(0).gioHang.id).gettongsanphamchon()} Sản phẩm được chọn</small>
            <h5>Tổng:${banhangonline.TongtienvsTongspchon(listghct.get(0).gioHang.id).gettongtien()} đ</h5>
        </div>
        <div class="cart-btns">
            <a href="/ban-hang-online/xem-gio-hang">Xem giỏ hàng</a>
            <a href="#">Chọn hết
                <input type="checkbox" name="checktongTT" onclick="chonhetgiohangtongTRANGCHU('${listghct.get(0).gioHang.id}');"  ${tttong==0 ?"checked":""}>
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

</body>
</html>
