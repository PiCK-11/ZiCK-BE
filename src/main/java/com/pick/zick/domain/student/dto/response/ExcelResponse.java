package com.pick.zick.domain.student.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExcelResponse {
    private String fileName;
    private String fileData;
}
