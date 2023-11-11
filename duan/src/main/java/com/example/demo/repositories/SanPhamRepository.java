package com.example.demo.repositories;

import com.example.demo.models.Rom;
import com.example.demo.models.SanPham;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, UUID> {
    @Query("SELECT r FROM SanPham r WHERE r.tinhTrang=0")
    List<SanPham> findAll0();

    @Query("SELECT r FROM SanPham r WHERE r.tinhTrang=1")
    List<SanPham> findAll1();

    @Query("SELECT r FROM SanPham r WHERE r.ten LIKE %:dungluong% or r.heDieuHanh LIKE %:dungluong% and r.tinhTrang=0")
    List<SanPham> search(String dungluong);

    @Query("SELECT r FROM SanPham r WHERE r.ten LIKE %:dungluong% or r.heDieuHanh LIKE %:dungluong% and r.tinhTrang=1")
    List<SanPham> search2(String dungluong);

    @Query("select r from SanPham r  where r.tinhTrang=0")
    List<SanPham> getall0();

    @Query("select r from SanPham r  where r.tinhTrang=1")
    List<SanPham> getall1();

    @Transactional
    @Modifying
    @Query(value = "update san_pham set tinh_trang=0, ngay_cap_nhat=convert(date,getdate(),105)", nativeQuery = true)
    void update0();

    @Query("select sp from SanPham sp left join ManHinh m on sp.manHinh.id=m.id " +
            "left join HangSanPham hang on sp.hangSanPham.id=hang.id" +
            " left join Camera c on sp.camera.id=c.id " +

            "where sp.tinhTrang=0 and" +
            " (:idHang is null or hang.id =:idHang) " +
            "and (:idMan is null or m.id=: idMan) " +
            "and (:idCamera is null or c.id=: idCamera) "
//            +"and (:giaBanMin is null and :giaBanMax is null or ct.giaBan between :giaBanMin and :giaBanMax)"
    )
    List<SanPham> loc(UUID idHang, UUID idMan, UUID idCamera);
}
