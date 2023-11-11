package com.example.demo.config;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;


import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.HoaDon;
import com.example.demo.models.HoaDonChiTiet;
import com.example.demo.models.IMEI;
import com.example.demo.repositories.KhachHangRepository;
import com.example.demo.services.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class PaymentController {


    @Autowired
    private HoaDonService hoaDonService;
    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private KhachHangService khachHangService;
    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;
    @Autowired
    private DiaChiService diaChiService;
    @Autowired
    private IMEIService imeiService;
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private MauSacService mauSacService;
    @Autowired
    private ChipService chipService;
    @Autowired
    private RamService ramService;
    @Autowired
    private RomService romService;
    @Autowired
    private PinService pinService;
    @Autowired
    private HangSanPhamService hangSanPhamService;
    @Autowired
    private HangKhachHangService hangKhachHangService;
    @Autowired
    private DungLuongPinService dungLuongPinService;
    @Autowired
    private ManHinhService manHinhService;
    @Autowired
    private CameraService cameraService;
    @Autowired
    private BanHangOnlineService banHangOnlineService;
    @Autowired
    private GioHangChiTietService gioHangChiTietService ;
    @Autowired
    private GioHangService gioHangService ;
    @Autowired
    private DataIntermediateService dataService;
    @Autowired
    private KhachHangRepository khachHangRepository;

    String idhoadon="oo";
    @GetMapping("/pay/{tienthanhtoan}/{idhd}")
    public  String getPay(Model model,
                          @PathVariable("tienthanhtoan") Long tienthanhtoan,
                          @PathVariable("idhd") UUID idhd

    ) throws UnsupportedEncodingException {
        idhoadon=String.valueOf(idhd);
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
        long amount = tienthanhtoan*100;
        String bankCode = "NCB";

        String vnp_TxnRef = Config.getRandomNumber(8);
        String vnp_IpAddr = "127.0.0.1";

        String vnp_TmnCode = Config.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");

        vnp_Params.put("vnp_BankCode", bankCode);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);

        vnp_Params.put("vnp_Locale", "vn");
//        vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl+"?contractId="+contractId);
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl+"?contractId=");
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;

        return "redirect:"+paymentUrl;

    }


    @GetMapping("ketquathanhtoan")
    public String paymentCallback(
            Model model,
            @RequestParam Map<String, String> queryParams,HttpServletResponse response) throws IOException {
        String vnp_ResponseCode = queryParams.get("vnp_ResponseCode");
        String contractId = queryParams.get("contractId");
        String registerServiceId = queryParams.get("registerServiceId");
        System.out.println("vnp_ResponseCode----"+vnp_ResponseCode);
        System.out.println("contractId----"+contractId);
        System.out.println("registerServiceId----"+registerServiceId);

//        giao dien

        double tong=0;
        Integer lamchon=0;
        for (ChiTietSanPham ct:banHangOnlineService.ctspbanhang()) {
            if(banHangOnlineService.soluongcon(String.valueOf(ct.getId()))>0){
                tong=tong+1;
                lamchon=lamchon+1;
            }
        }
        double tb=tong/3;
        lamchon=lamchon/3;
        if(tb % 1 >0){
            lamchon=lamchon+1;
        }

        model.addAttribute("lamchon",lamchon);
        model.addAttribute("giamgia",banHangOnlineService);
        model.addAttribute("banhangonline",banHangOnlineService);

        model.addAttribute("khachhangdangnhap",hoaDonService.findById(UUID.fromString(idhoadon)).getKhachHang());
        model.addAttribute("listsp",banHangOnlineService.ctspbanhang());
        model.addAttribute("idkhachhang",hoaDonService.findById(UUID.fromString(idhoadon)).getKhachHang().getId());
        model.addAttribute("kh",hoaDonService.findById(UUID.fromString(idhoadon)).getKhachHang());
        model.addAttribute("hkh", hangKhachHangService.getALL0());
//        giohan
        model.addAttribute("listghct",banHangOnlineService.ListghctTheoidgh(banHangOnlineService.ListghTheoidkh(String.valueOf(hoaDonService.findById(UUID.fromString(idhoadon)).getKhachHang().getId())).get(0).getId()));
        model.addAttribute("tttong",1);

//        het giao dien
       // that bai: 24,rỗng, null
        // thành công:00,rỗng,null
        //hết thời gian :15,rỗng,null

            if ("00".equals(vnp_ResponseCode)) {
                // Giao dịch thành công
                // Thực hiện các xử lý cần thiết, ví dụ: cập nhật CSDL
//hoadon
                long millis = System.currentTimeMillis();
                Date date = new Date(millis);
                HoaDon hd=hoaDonService.findById(UUID.fromString(idhoadon));
                hd.setNgayThanhToan(date);
                hd.setNgayCapNhat(date);
                hd.setTinhTrang(2);
                hd.setLoai(1);
                hd.setHinhThucThanhToan(1);
                hd.setHinhThucThanhToan(1);
                hd.setTinhTrangGiaoHang(0);
                hoaDonService.add(hd);
//hoadonchitiet
               List<HoaDonChiTiet> hdct=banHangOnlineService.timhoadonchitiettheoidhd(UUID.fromString(idhoadon));
                for (HoaDonChiTiet hdct1:hdct
                     ) {
                    hdct1.setTinhTrang(1);
                    hoaDonChiTietService.add(hdct1);
                }
//imei
                List<HoaDonChiTiet> hdctchoimei=banHangOnlineService.timhoadonchitiettheoidhd(UUID.fromString(idhoadon));

                for (HoaDonChiTiet hdct2:hdctchoimei
                ) {
                 IMEI imei1=hdct2.getImei();
                 imei1.setTinhTrang(1);
                    imeiService.add(imei1);
                }
                idhoadon="oo";

                model.addAttribute("ketquathanhtoan", 1);
                return "ban-hang-online/ket_qua_thanh_toan";
            } else {
                // Giao dịch thất bại
                // Thực hiện các xử lý cần thiết, ví dụ: không cập nhật CSDL\
                idhoadon="oo";
                model.addAttribute("ketquathanhtoan", 0);
                return "ban-hang-online/ket_qua_thanh_toan";

            }





    }




}
