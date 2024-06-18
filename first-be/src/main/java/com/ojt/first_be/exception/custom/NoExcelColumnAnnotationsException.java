package com.ojt.first_be.exception.custom;

import com.ojt.first_be.constant.ResultCode;
import com.ojt.first_be.exception.CustomException;

public class NoExcelColumnAnnotationsException extends CustomException {

    public NoExcelColumnAnnotationsException(ResultCode resultCode) {
        super(resultCode);
    }
}
