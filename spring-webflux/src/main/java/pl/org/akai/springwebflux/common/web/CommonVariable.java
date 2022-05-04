package pl.org.akai.springwebflux.common.web;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonVariable {
    public static final int EMPTY_QUERY_PARAM = -1;
    public static final String BOOK_ID_PATH_PARAM = "bookId";
    public static final String CATEGORY_QUERY_PARAM = "category";
    public static final String PAGE_QUERY_PARAM = "page";
    public static final String PAGE_SIZE_QUERY_PARAM = "pageSize";
    public static final String LOG_MESSAGE = "Send response to request at endpoint {} from user with IP {}";
    public static final String USER = "user";
    public static final String PATH = "path";
    public static final String UNKNOWN = "unknown";

}