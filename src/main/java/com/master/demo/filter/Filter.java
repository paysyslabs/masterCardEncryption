package com.master.demo.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class Filter  extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        log.info("<<====com.mastercard.encryption.demo.filter=====>>");
        log.info(httpServletRequest.toString());
        log.info(httpServletResponse.toString());
        log.info(filterChain.toString());
        log.info("<<====com.mastercard.encryption.demo.filter=====>>");
    }
}
