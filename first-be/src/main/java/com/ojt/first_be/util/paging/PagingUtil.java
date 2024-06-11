package com.ojt.first_be.util.paging;

import org.springframework.stereotype.Component;

import java.util.function.Supplier;

import static com.ojt.first_be.constant.Common.OUTPUT_LIST_LIMIT_SIZE;

@Component
public class PagingUtil {

    public static Integer getTotalPage(boolean isTotalPageRequired, Supplier<Integer> counter) {

        if (!isTotalPageRequired) {
            return null;
        }
        int totalCount = counter.get();
        int additionalPage = (totalCount % OUTPUT_LIST_LIMIT_SIZE == 0) ? 0 : 1;

        return totalCount / OUTPUT_LIST_LIMIT_SIZE + additionalPage;
    }

    public static int calOffset(int page) {

        return (Math.abs(page) - 1) * OUTPUT_LIST_LIMIT_SIZE;
    }
}
