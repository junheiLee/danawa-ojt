package com.ojt.first_be.domain;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

public interface Uploadable {

    void setValuesFormExcel(Row row);

    /**
     * 숫자 형태의 문자열 값에 CellType이 NUMERIC으로 설정 되어
     * 문자열로 가져오지 못하는 오류 해결 로직
     *
     * @param cell 문자열을 가지고 있어야 하는 CELL
     * @return cell이 담고 있는 값을 문자열로 반환
     */
    default String getStringCellValue(Cell cell) {

        if (cell == null) {
            return "";
        }

        CellType cellType = cell.getCellType();
        return switch (cellType) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.format("%.0f", cell.getNumericCellValue());
            default -> "";
        };
    }
}
