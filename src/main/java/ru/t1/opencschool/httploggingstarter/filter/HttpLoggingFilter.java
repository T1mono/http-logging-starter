package ru.t1.opencschool.httploggingstarter.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class HttpLoggingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        String requestId = request.getHeader("X-Request-ID"); // You can use any unique identifier for each request

        log.info("Request ID: {}, Method: {}, URL: {}, Headers: {}", requestId, request.getMethod(), request.getRequestURI(), request.getHeaderNames());

        filterChain.doFilter(request, response);

        log.info("Request ID: {}, Response Code: {}, Headers: {}, Time Taken: {}ms", requestId, response.getStatus(), response.getHeaderNames(), System.currentTimeMillis() - startTime);
    }
}
