package com.example.demo.DTO;

import java.math.BigDecimal;

public interface DoanhThuHang {

    String getTenSanPham();
    String getTenHang();
    Integer getSoLuongSP();
    BigDecimal getDoanhThu();
    BigDecimal getGiaMuaMin();
    BigDecimal getGiaMuaMax();
    BigDecimal getDoanhThuTrungBinh();

}

