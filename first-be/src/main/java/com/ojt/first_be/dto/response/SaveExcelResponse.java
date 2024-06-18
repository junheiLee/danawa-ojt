package com.ojt.first_be.dto.response;

import com.ojt.first_be.constant.ResultCode;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class SaveExcelResponse<E> extends BaseResponse {

    private final int successCount;
    private final int failedCount;
    private final List<E> falidList;

    @Builder
    public SaveExcelResponse(String code, String message,
                             int successCount, int failedCount,
                             List<E> falidList) {
        super(code, message);
        this.successCount = successCount;
        this.failedCount = failedCount;
        this.falidList = falidList;
    }

    public void setResultCode(ResultCode code) {
        this.setResultCode(code.name());
        this.setMessage(String.format(code.getMessage(), this.successCount, this.failedCount));
    }
}
