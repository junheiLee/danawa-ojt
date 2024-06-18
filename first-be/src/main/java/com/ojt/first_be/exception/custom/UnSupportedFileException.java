package com.ojt.first_be.exception.custom;

import com.ojt.first_be.constant.ResultCode;
import com.ojt.first_be.exception.CustomException;

public class UnSupportedFileException extends CustomException {

    public UnSupportedFileException(ResultCode resultCode) {
        super(resultCode);
    }
}
