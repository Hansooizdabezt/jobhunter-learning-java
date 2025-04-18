package vn.hoidanit.jobhunter.utils.format;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;

import vn.hoidanit.jobhunter.domain.RestResponse;
import vn.hoidanit.jobhunter.utils.errors.GlobalException;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class FormatRestResponse implements ResponseBodyAdvice<Object> {

    private final GlobalException globalException;

    FormatRestResponse(GlobalException globalException) {
        this.globalException = globalException;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    @Nullable
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType,
            Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        HttpServletResponse servletResponse = ((ServletServerHttpResponse) response).getServletResponse();
        int status = servletResponse.getStatus();

        // case lỗi thì để nguyên body
        if (status >= 400) {
            return body;
        }

        RestResponse<Object> res = new RestResponse<>();
        res.setStatusCode(status);
        res.setMessage("call api success");
        res.setData(body);

        // Nếu converter là StringHttpMessageConverter => convert res thành String JSON
        if (StringHttpMessageConverter.class.isAssignableFrom(selectedConverterType)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.writeValueAsString(res);
            } catch (Exception e) {
                throw new RuntimeException("Failed to convert response to JSON string", e);
            }
        }

        return res;
    }

}
