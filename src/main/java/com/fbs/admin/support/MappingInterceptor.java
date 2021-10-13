package com.fbs.admin.support;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Type;

@RestControllerAdvice
@Component
public class MappingInterceptor implements RequestBodyAdvice {

    private final HttpServletRequest request;

    public MappingInterceptor(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
       /* String uri = request.getRequestURI();
        Object obj = new Object();
        Map<String, Object> map = new HashMap<>();
        if (body != null) {
            map = (Map<String, Object>) body;
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Object value = entry.getValue();
                if (value instanceof LocalDate) {
                    LocalDate localDate = (LocalDate) value;
                    DateTimeFormatter simpleDateFormat = DateTimeFormatter.ofPattern(fbsDateFormat);
                    String dateString = simpleDateFormat.format(localDate);
                    entry.setValue(dateString);
                } else if (value instanceof LocalDateTime) {
                    LocalDateTime localDateTime = (LocalDateTime) value;
                    DateTimeFormatter simpleDateFormat = DateTimeFormatter.ofPattern(fbsDateFormat);
                    String dateString = simpleDateFormat.format(localDateTime);
                    entry.setValue(dateString);
                } else if (value instanceof Timestamp) {
                    LocalDateTime localDateTime = ((Timestamp) value).toLocalDateTime();
                    DateTimeFormatter simpleDateFormat = DateTimeFormatter.ofPattern(fbsDateFormat);
                    String dateString = simpleDateFormat.format(localDateTime);
                    entry.setValue(dateString);
                }
            }
            obj = map;
        }*/
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }


}
