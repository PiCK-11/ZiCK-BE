package com.pick.zick.domain.student.controller;

import com.pick.zick.domain.student.dto.request.CheckCanEnterRequest;
import com.pick.zick.domain.student.dto.response.CheckCanEnterResponse;
import com.pick.zick.domain.student.dto.response.ExcelResponse;
import com.pick.zick.domain.student.service.AttendanceExcelService;
import com.pick.zick.domain.student.service.CheckCanEnterService;
import com.pick.zick.domain.student.service.GetRandomHash;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final GetRandomHash getRandomHash;
    private final CheckCanEnterService checkCanEnterService;
    private final AttendanceExcelService attendanceExcelService;

    @GetMapping("/qr")
    public String getRandomHash() throws NoSuchAlgorithmException {
        return getRandomHash.makeHash();
    }

    @PostMapping("/attendances")
    public CheckCanEnterResponse checkCanEnterService(@RequestBody CheckCanEnterRequest checkCanEnterRequest) {
        return checkCanEnterService.execute(checkCanEnterRequest.getKey());
    }

    @GetMapping("/attendance/excel")
    public ExcelResponse getAttendanceExcel() throws IOException {
        return attendanceExcelService.generateExcel();
    }
}
