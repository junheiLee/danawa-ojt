package com.ojt.first_be.util.batch;

import com.ojt.first_be.dto.response.SaveExcelResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

import static com.ojt.first_be.constant.Common.BATCH_SIZE;

@Slf4j
@Component
public class BatchService {

    /**
     * 객체 처리 시, 설정한 개수만큼 한 번에 INSERT 하고,
     * 실패 시, 로그에 실패 항목을 저장
     *
     * @param items             저장할 객체 리스트
     * @param batchSaveFunction 해당 객체 리스트 여러 건 저장 함수 (파라미터: 저장할 객체 리스트, 리턴: 성공 개수)
     * @param <T>               처리하는 객체 타입
     * @return 저장 결과 (성공, 실패 개수 등 로직 성공 여부)
     */
    public <T> SaveExcelResponse<Object> process(List<T> items,
                                                 Function<List<T>, Integer> batchSaveFunction) {

        int totalSize = items.size();
        int endIdx; // 배치 처리 시, 적절한 사이즈로 나눌 때, 끝 인덱스
        int successRow = 0;
        int failedRow = 0;

        for (int i = 0; i < totalSize; i += BATCH_SIZE) {
            endIdx = Math.min(i + BATCH_SIZE, totalSize);
            List<T> batchItems = items.subList(i, endIdx);

            try {
                successRow += batchSaveFunction.apply(batchItems);

            } catch (DataAccessException e) { // 일괄 처리 실패 시, log에 보관
                log.warn("실패 항목={}", batchItems);
                failedRow += batchItems.size();
            }
        }

        return SaveExcelResponse
                .builder()
                .successCount(successRow)
                .failedCount(failedRow)
                .build();
    }
}
