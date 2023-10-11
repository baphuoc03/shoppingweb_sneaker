package com.example.shoppingweb.restcontroller.admin;

import com.example.shoppingweb.ResponseEntity.ResponseObject;
import com.example.shoppingweb.dto.reponse.VoucherReponse;
import com.example.shoppingweb.dto.request.VoucherRequest;
import com.example.shoppingweb.service.impl.VoucherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("${admin.domain}/voucher")
@CrossOrigin(origins = "*")
public class VoucherRestController {
    @Autowired
    VoucherServiceImpl service;

    @GetMapping("/a")
    public List<VoucherReponse> findAll(
            @RequestParam(name = "pageNumber", required = false, defaultValue = "1") int pageNumber) {
        Page<VoucherReponse> page = service.findAll(pageNumber-1, 1);
        List<VoucherReponse> listVC = page.getContent();
        return listVC;
    }

    @PostMapping("")
    public ResponseEntity<ResponseObject> save(@RequestBody VoucherRequest voucherRequest) {
        voucherRequest.setMa(codeVoucher());
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Oke", "Thêm thành công", service.addVoucher(voucherRequest))
        );
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<ResponseObject> findById(@PathVariable("id") String id) {
//        boolean exitst = service.exitst(id);
//        return exitst ? ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject("Oke", "Tìm thấy", service.findById(id))
//        ) : ResponseEntity.status(HttpStatus.FOUND).body(
//                new ResponseObject("Found", "Không tìm thấy", "")
//        );
//    }

    @GetMapping("/{id}")
    public VoucherReponse findById(@PathVariable("id") String id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> delete(@PathVariable("id") String id) {
        boolean exitst = service.exitst(id);
        if (exitst) {
            service.deleteVoucher(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Oke", "Xóa thành công", "")
            );
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(
                new ResponseObject("found", "Xóa thất bài", "")
        );
    }

    //
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> update(@PathVariable("id") String id,
                                                 @RequestBody VoucherRequest voucherRequest) {
        boolean exitst = service.exitst(id);
        if (exitst) {
            voucherRequest.setMa(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Oke", "Sửa thành công", service.addVoucher(voucherRequest))
            );
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(
                new ResponseObject("Found", "Không tìm thấy", "")
        );
    }

    private static String codeVoucher() {
        final String ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        final int CODE_LENGTH = 8;

        StringBuilder code = new StringBuilder();

        Random random = new Random();
        int maxIndex = ALLOWED_CHARACTERS.length();

        // Sinh ngẫu nhiên các ký tự cho mã
        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(maxIndex);
            char randomChar = ALLOWED_CHARACTERS.charAt(randomIndex);
            code.append(randomChar);
        }

        return code.toString();
    }


}

