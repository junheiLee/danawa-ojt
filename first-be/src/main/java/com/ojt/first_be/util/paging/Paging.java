package com.ojt.first_be.util.paging;

import com.ojt.first_be.dto.response.PageCount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

import static com.ojt.first_be.constant.Common.OUTPUT_LIST_LIMIT_SIZE;

@Slf4j
@Component
public class Paging {

    public static PageCount getTotalPage(Supplier<Integer> counter) {

        int totalCount = counter.get();
        int additionalPage = (totalCount % OUTPUT_LIST_LIMIT_SIZE == 0) ? 0 : 1;
        int totalPage = totalCount / OUTPUT_LIST_LIMIT_SIZE + additionalPage;

        return PageCount.builder()
                .totalPage(totalPage)
                .totalItem(totalCount)
                .build();

    }

    public static int calOffset(int page) {
        return (Math.abs(page) - 1) * OUTPUT_LIST_LIMIT_SIZE;
    }
}
