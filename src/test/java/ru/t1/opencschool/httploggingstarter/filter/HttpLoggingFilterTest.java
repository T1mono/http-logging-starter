package ru.t1.opencschool.httploggingstarter.filter;


import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Тестирование класса HttpLoggingFilter.
 */
public class HttpLoggingFilterTest {

    private HttpLoggingFilter filter;
    private MockHttpServletRequest mockRequest;
    private MockHttpServletResponse mockResponse;
    private FilterChain mockFilterChain;

    private InMemoryAppender inMemoryAppender;

    @BeforeEach
    public void setUp() {
        filter = new HttpLoggingFilter();
        mockRequest = new MockHttpServletRequest();
        mockResponse = new MockHttpServletResponse();
        mockFilterChain = mock(FilterChain.class);

        // Настройка InMemoryAppender для логгера
        Logger logger = (Logger) LoggerFactory.getLogger(HttpLoggingFilter.class);
        inMemoryAppender = new InMemoryAppender();
        inMemoryAppender.start();
        logger.addAppender(inMemoryAppender);
    }

    @AfterEach
    public void tearDown() {
        // Очистка InMemoryAppender после каждого теста
        inMemoryAppender.stop();
    }

    @Test
    public void testFilterWithExistingRequestId() throws ServletException, IOException {
        // Установка заголовка X-Request-ID
        String requestId = "existing-request-id";
        mockRequest.addHeader("X-Request-ID", requestId);

        // Запуск фильтра
        filter.doFilter(mockRequest, mockResponse, mockFilterChain);

        // Проверка, что фильтр вызвал метод doFilter
        verify(mockFilterChain).doFilter(mockRequest, mockResponse);

        // Проверка логирования запроса
        assertTrue(inMemoryAppender.contains("Request ID: " + requestId, Level.INFO));
        assertTrue(inMemoryAppender.contains("Method: " + mockRequest.getMethod(), Level.INFO));
        assertTrue(inMemoryAppender.contains("URL: " + mockRequest.getRequestURI(), Level.INFO));
    }

    @Test
    public void testFilterWithoutRequestId() throws ServletException, IOException {
        // Запуск фильтра без заголовка X-Request-ID
        filter.doFilter(mockRequest, mockResponse, mockFilterChain);

        // Проверка, что фильтр вызвал метод doFilter
        verify(mockFilterChain).doFilter(mockRequest, mockResponse);

        // Проверка логирования запроса
        assertTrue(inMemoryAppender.contains("Method: " + mockRequest.getMethod(), Level.INFO));
        assertTrue(inMemoryAppender.contains("URL: " + mockRequest.getRequestURI(), Level.INFO));
    }

    @Test
    public void testFilterWithEmptyRequestId() throws ServletException, IOException {
        // Установка пустого заголовка X-Request-ID
        mockRequest.addHeader("X-Request-ID", "");

        // Запуск фильтра
        filter.doFilter(mockRequest, mockResponse, mockFilterChain);

        // Проверка, что фильтр вызвал метод doFilter
        verify(mockFilterChain).doFilter(mockRequest, mockResponse);

        // Проверка логирования запроса
        assertTrue(inMemoryAppender.contains("Method: " + mockRequest.getMethod(), Level.INFO));
        assertTrue(inMemoryAppender.contains("URL: " + mockRequest.getRequestURI(), Level.INFO));
    }

    @Test
    public void testFilterWithResponseLogging() throws ServletException, IOException {
        // Установка статуса ответа
        mockResponse.setStatus(200);

        // Запуск фильтра
        filter.doFilter(mockRequest, mockResponse, mockFilterChain);

        // Проверка, что фильтр вызвал метод doFilter
        verify(mockFilterChain).doFilter(mockRequest, mockResponse);

        // Проверка логирования ответа
        assertTrue(inMemoryAppender.contains("Response Code: " + mockResponse.getStatus(), Level.INFO));
    }

    private static class InMemoryAppender extends AppenderBase<ILoggingEvent> {
        private final List<ILoggingEvent> log = new ArrayList<>();

        @Override
        protected void append(ILoggingEvent eventObject) {
            log.add(eventObject);
        }

        public boolean contains(String string, Level level) {
            return log.stream()
                    .filter(event -> event.getLevel().equals(level))
                    .anyMatch(event -> event.getFormattedMessage().contains(string));
        }
    }
}