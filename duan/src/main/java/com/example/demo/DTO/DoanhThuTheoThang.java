package com.example.demo.DTO;

import java.math.BigDecimal;

public interface DoanhThuTheoThang {

    Integer getThang();
    Integer getNam();
    Integer getSoLuongSP();
    BigDecimal getDoanhThu();
    BigDecimal getGiaMuaMin();
    BigDecimal getGiaMuaMax();
    BigDecimal getDoanhThuTrungBinh();
    String getTen();

}

