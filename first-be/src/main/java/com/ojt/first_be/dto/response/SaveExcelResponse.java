package com.ojt.first_be.dto.response;

import com.ojt.first_be.constant.ResultCode;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SaveExcelResponse<E> extends BaseResponse {

    private final int successCount;
    private final int failedCount;

    @Builder
    public SaveExcelResponse(String code, String message,
                             int successCount, int failedCount) {
        super(code, message);
        this.successCount = successCount;
        this.failedCount = failedCount;
    }

    public void setResultCode(ResultCode code) {
        this.setResultCode(code.name());
        this.setMessage(String.format(code.getMessage(), this.successCount, this.failedCount));
    }
}
