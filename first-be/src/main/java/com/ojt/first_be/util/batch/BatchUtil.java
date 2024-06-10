package com.ojt.first_be.util.batch;

import com.ojt.first_be.dto.response.SaveExcelResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static com.ojt.first_be.constant.BatchConstant.BASIC_BATCH_SIZE;

@Component
public class BatchUtil {

    /**
     * 객체 처리 시, 설정한 개수만큼 한 번에 INSERT 하고,
     * 실패 시 단 건으로 처리하며, 실패한 데이터 정보를 담기 위한 로직
     *
     * @param items              저장할 객체 리스트
     * @param batchSaveFunction  해당 객체 리스트 여러 건 저장 함수
     * @param singleSaveFunction 해당 객체  단 건 저장 함수
     * @param <T>                처리하는 객체 타입
     * @return 저장 결과 (성공, 실패 개수 등 로직 성공 여부)
     */
    public static <T> SaveExcelResponse<Object> process(List<T> items,
                                                 Function<List<T>, Integer> batchSaveFunction,
                                                 Function<T, Integer> singleSaveFunction) {

        int totalSize = items.size();
        int endIdx; // 배치 처리 시, 적절한 사이즈로 나눌 때, 끝 인덱스
        int successRow = 0;
        List<Object> failedItems = new ArrayList<>(); // 실패한 로직을 담기 위한 List

        for (int i = 0; i < totalSize; i += BASIC_BATCH_SIZE) {
            endIdx = Math.min(i + BASIC_BATCH_SIZE, totalSize);
            List<T> batchItems = items.subList(i, endIdx);

            try {
                successRow += batchSaveFunction.apply(batchItems);

            } catch (Exception e) { // 일괄 처리 실패 시, 단건으로 저장하며 실패한 객체 정보를 보관

                for (T item : batchItems) {

                    try {
                        successRow += singleSaveFunction.apply(item);

                    } catch (Exception exception) {
                        failedItems.add(item);
                    }
                }
            }
        }

        return SaveExcelResponse
                .builder()
                .successCount(successRow)
                .failedCount(failedItems.size())
                .falidList(failedItems)
                .build();
    }
}
