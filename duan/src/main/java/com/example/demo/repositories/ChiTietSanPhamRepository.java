package com.example.demo.repositories;

import com.example.demo.models.ChiTietSanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface ChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham, UUID> {
    @Query("select ct from ChiTietSanPham ct left join SanPham sp on ct.sanPham.id=sp.id " +
            "left join HangSanPham hang on sp.hangSanPham.id=hang.id left join Ram ram on ct.ram.id=ram.id" +
            " left join Rom rom on ct.rom.id=rom.id left join Pin pin on ct.pin.id=pin.id left join MauSac ms on ct.mauSac.id=ms.id " +
            "left join Chip chip on ct.chip.id=chip.id where ct.tinhTrang=0 and" +
            "( sp.ten like %:ten% or hang.ten like %:ten% or ram.dungLuong like %:ten% or rom.dungLuong like %:ten% " +
            "or pin.loaiPin like %:ten% or ms.ten like %:ten% or chip.ten like %:ten%)")
    List<ChiTietSanPham> search(String ten);

    @Query("select ct from ChiTietSanPham ct left join SanPham sp on ct.sanPham.id=sp.id " +
            "left join HangSanPham hang on sp.hangSanPham.id=hang.id left join Ram ram on ct.ram.id=ram.id" +
            " left join Rom rom on ct.rom.id=rom.id left join Pin pin on ct.pin.id=pin.id left join MauSac ms on ct.mauSac.id=ms.id " +
            "left join Chip chip on ct.chip.id=chip.id where ct.tinhTrang=1 and" +
            " (sp.ten like %:ten% or hang.ten like %:ten% or ram.dungLuong like %:ten% or rom.dungLuong like %:ten% " +
            "or pin.loaiPin like %:ten% or ms.ten like %:ten% or chip.ten like %:ten%)")
    List<ChiTietSanPham> search1(String ten);

    @Query("select ct from ChiTietSanPham ct left join SanPham sp on ct.sanPham.id=sp.id " +
            "left join HangSanPham hang on sp.hangSanPham.id=hang.id left join Ram ram on ct.ram.id=ram.id " +
            "left join Rom rom on ct.rom.id=rom.id left join Pin pin on ct.pin.id=pin.id " +
            "left join MauSac ms on ct.mauSac.id=ms.id left join Chip chip on ct.chip.id=chip.id " +
            "left join DungLuongPin dungLuong on pin.dungLuongPin.id=dungLuong.id " +
            "left  join ManHinh manHinh on sp.manHinh.id=manHinh.id " +
            "left join Camera  cam on sp.camera.id=cam.id " +
            "where ct.tinhTrang=0 and" +
            " ((:idHang is null or hang.id =:idHang) " +
            "and (:idRam is null or ram.id=: idRam) " +
            "and (:idRom is null or rom.id=: idRom) " +
            "and (:idDLPin is null or dungLuong.id=: idDLPin) " +
            "and (:idChip is null or chip.id=: idChip) " +
            "and (:moTaMan is null or manHinh.id =:moTaMan) " +
            "and (:moTaCam is null or cam.id =:moTaCam) )"

//            +"and (:giaBanMin is null and :giaBanMax is null or ct.giaBan between :giaBanMin and :giaBanMax)"
    )
    List<ChiTietSanPham> loc(UUID idHang, UUID idRam, UUID idRom, UUID idDLPin, UUID idChip, UUID moTaMan, UUID moTaCam);

    @Query("select ct from ChiTietSanPham ct left join SanPham sp on ct.sanPham.id=sp.id " +
            "left join HangSanPham hang on sp.hangSanPham.id=hang.id left join Ram ram on ct.ram.id=ram.id " +
            "left join Rom rom on ct.rom.id=rom.id left join Pin pin on ct.pin.id=pin.id " +
            "left join MauSac ms on ct.mauSac.id=ms.id left join Chip chip on ct.chip.id=chip.id " +
            "left join DungLuongPin dungLuong on pin.dungLuongPin.id=dungLuong.id " +
            "left  join ManHinh manHinh on sp.manHinh.id=manHinh.id " +
            "left join Camera  cam on sp.camera.id=cam.id " +
            "where ct.tinhTrang=0 and" +
            " ((:idHang is null or hang.id =:idHang) " +
            "and (:idRam is null or ram.id=: idRam) " +
            "and (:idRom is null or rom.id=: idRom) " +
            "and (:idDLPin is null or dungLuong.id=: idDLPin) " +
            "and (:idChip is null or chip.id=: idChip) " +
            "and (:moTaMan is null or manHinh.id =:moTaMan) " +
            "and (:moTaCam is null or cam.id =:moTaCam) " +
            "and (:giaBanMin is null and :giaBanMax is null " +
            "or ct.giaBan between :giaBanMin and ct.giaBan or ct.giaBan between 0 and :giaBanMax or ct.giaBan between :giaBanMin and :giaBanMax))"
    )
    List<ChiTietSanPham> locBanHang(UUID idHang, UUID idRam, UUID idRom, UUID idDLPin, UUID idChip, UUID moTaMan, UUID moTaCam, BigDecimal giaBanMin, BigDecimal giaBanMax);

    @Query("select ct from ChiTietSanPham ct left join IMEI  i on ct.id=i.chiTietSanPham.id where i.id=:id")
    ChiTietSanPham getChiTiet(UUID id);

    @Query("select ct from ChiTietSanPham ct left join IMEI  i on ct.id=i.chiTietSanPham.id left join HoaDonChiTiet hd on i.id=hd.imei.id where i.id=:id")
    ChiTietSanPham getChiTiet2(UUID id);

    @Query("select ctsp from  ChiTietSanPham ctsp where ctsp.tinhTrang=0 ")
    List<ChiTietSanPham> getChiTietOn();

    @Query("select ctsp from  ChiTietSanPham ctsp where ctsp.tinhTrang=1 ")
    List<ChiTietSanPham> getChiTietOff();

    @Query("select ctsp from  ChiTietSanPham ctsp where ctsp.tinhTrang=1 ")
    List<ChiTietSanPham> getChiTietSanPhamOff();

    @Query("select ctsp from  ChiTietSanPham ctsp where ctsp.tinhTrang=0 ")
    List<ChiTietSanPham> findAll0();

    @Query("select ctsp from  ChiTietSanPham ctsp where ctsp.tinhTrang=0 and ctsp.sanPham.hangSanPham.id=:id")
    List<ChiTietSanPham> findAllHang(UUID id);

    @Query("select ctsp from  ChiTietSanPham ctsp where ctsp.tinhTrang=0 and ctsp.sanPham.manHinh.id=:id")
    List<ChiTietSanPham> findAllMan(UUID id);

    @Query("select ctsp from  ChiTietSanPham ctsp where ctsp.tinhTrang=0 and ctsp.chip.id=:id")
    List<ChiTietSanPham> findAllChip(UUID id);

    @Query("select ctsp from  ChiTietSanPham ctsp where ctsp.tinhTrang=0 and ctsp.pin.dungLuongPin.id=:id")
    List<ChiTietSanPham> findAllPin(UUID id);

    @Query("select ctsp from  ChiTietSanPham ctsp where ctsp.tinhTrang=0 and ctsp.rom.id=:id")
    List<ChiTietSanPham> findAllRom(UUID id);

    @Query("select ctsp from  ChiTietSanPham ctsp where ctsp.tinhTrang=0 and ctsp.ram.id=:id")
    List<ChiTietSanPham> findAllRam(UUID id);

    @Query("select ctsp from  ChiTietSanPham ctsp where ctsp.tinhTrang=0 and ctsp.sanPham.camera.id=:id")
    List<ChiTietSanPham> findAllCam(UUID id);
}
