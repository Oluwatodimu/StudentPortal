package io.todimu.practice.studentportal.web.BaseResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseResponse {

    private Object data;
    private String message;
    private boolean error = true;
}
