package com.ojt.first_be.excel;

import java.io.File;
import java.util.List;

public interface ExcelHandler {

    /**
     * 분해할 Excel 파일과 변환할 객체 타입을 입력받아 해당 객체 리스트로 반환
     *
     * @param <T>  다루게 되는 객체 타입
     * @param file 분해할 대상 파일
     * @return Excel 파일을 변환한 객체 리스트
     */
    <T> List<T> toObjectList(File file);

}
