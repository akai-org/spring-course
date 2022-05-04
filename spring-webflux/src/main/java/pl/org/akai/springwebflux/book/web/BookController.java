package pl.org.akai.springwebflux.book.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pl.org.akai.springwebflux.book.dto.BookCreateDto;
import pl.org.akai.springwebflux.book.service.BookService;
import pl.org.akai.springwebflux.common.web.ServerResponseFactory;
import reactor.core.publisher.Mono;

import static pl.org.akai.springwebflux.common.web.CommonVariable.*;
import static pl.org.akai.springwebflux.common.web.ControllerUtils.*;


@Slf4j
@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    public Mono<ServerResponse> listBooks(ServerRequest serverRequest) {
        return Mono.deferContextual(
                ctx -> withQueryParam(serverRequest, CATEGORY_QUERY_PARAM,
                        category -> withPageParams(serverRequest,
                                pageRequest -> bookService.listBooks(category, pageRequest)
                                                          .flatMap(ServerResponseFactory::createHttpSuccessResponse)
                                                          .doOnError(e -> log.error(e.getMessage(), e))
                                                          .onErrorResume(e -> ServerResponseFactory.createHttpInternalServerErrorResponse())))
                        .doOnSuccess(r -> log.info(LOG_MESSAGE, ctx.getOrDefault(PATH, UNKNOWN), ctx.getOrDefault(USER, UNKNOWN))));
    }

    public Mono<ServerResponse> getBookById(ServerRequest serverRequest) {
        return Mono.deferContextual(
                ctx -> withPathParam(serverRequest, BOOK_ID_PATH_PARAM,
                        bookId -> bookService.getBookById(bookId)
                                             .flatMap(ServerResponseFactory::createHttpSuccessResponse)
                                             .switchIfEmpty(ServerResponseFactory.createHttpNotFoundResponse())
                                             .doOnError(e -> log.error(e.getMessage(), e))
                                             .onErrorResume(e -> ServerResponseFactory.createHttpInternalServerErrorResponse()))
                        .doOnSuccess(r -> log.info(LOG_MESSAGE, ctx.getOrDefault(PATH, UNKNOWN), ctx.getOrDefault(USER, UNKNOWN))));
    }

    public Mono<ServerResponse> addBook(ServerRequest serverRequest) {
        return Mono.deferContextual(
                ctx -> serverRequest.bodyToMono(BookCreateDto.class)
                                    .flatMap(bookDto -> bookService.addBook(bookDto)
                                                                   .flatMap(ServerResponseFactory::createHttpCreatedResponse)
                                                                   .doOnError(e -> log.error(e.getMessage(), e))
                                                                   .onErrorResume(e -> ServerResponseFactory.createHttpInternalServerErrorResponse()))
                                    .doOnSuccess(r -> log.info(LOG_MESSAGE, ctx.getOrDefault(PATH, UNKNOWN), ctx.getOrDefault(USER, UNKNOWN))));
    }

    public Mono<ServerResponse> updateBook(ServerRequest serverRequest) {
        return Mono.deferContextual(
                ctx -> withPathParam(serverRequest, BOOK_ID_PATH_PARAM,
                        bookId -> serverRequest.bodyToMono(BookCreateDto.class)
                                               .flatMap(bookDto -> bookService.updateBookById(bookId, bookDto))
                                               .flatMap(ServerResponseFactory::createHttpSuccessResponse)
                                               .switchIfEmpty(ServerResponseFactory.createHttpNotFoundResponse())
                                               .doOnError(e -> log.error(e.getMessage(), e))
                                               .onErrorResume(e -> ServerResponseFactory.createHttpInternalServerErrorResponse()))
                        .doOnSuccess(r -> log.info(LOG_MESSAGE, ctx.getOrDefault(PATH, UNKNOWN), ctx.getOrDefault(USER, UNKNOWN))));
    }

    public Mono<ServerResponse> getBookByIdUsingRequest(ServerRequest serverRequest) {
        return Mono.deferContextual(
                ctx -> withPathParam(serverRequest, BOOK_ID_PATH_PARAM,
                        bookId -> bookService.doRequest(bookId)
                                             .flatMap(ServerResponseFactory::createHttpSuccessResponse)
                                             .switchIfEmpty(ServerResponseFactory.createHttpNotFoundResponse())
                                             .doOnError(e -> log.error(e.getMessage(), e))
                                             .onErrorResume(e -> ServerResponseFactory.createHttpInternalServerErrorResponse()))
                        .doOnSuccess(r -> log.info(LOG_MESSAGE, ctx.getOrDefault(PATH, UNKNOWN), ctx.getOrDefault(USER, UNKNOWN))));
    }

}

