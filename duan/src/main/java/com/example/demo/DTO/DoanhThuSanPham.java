package com.example.demo.DTO;

import java.math.BigDecimal;

public interface DoanhThuSanPham {

    String getTenSanPham();
    String getTenHang();
    Integer getSoLuongSP();
    Integer getSoLuongTon();
    BigDecimal getDoanhThu();
    BigDecimal getGiaMuaMin();
    BigDecimal getGiaMuaMax();
    BigDecimal getDoanhThuTrungBinh();

}

