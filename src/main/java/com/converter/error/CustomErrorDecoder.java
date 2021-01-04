package com.converter.error;

import com.converter.error.exception.FeignClientException;
import com.converter.error.exception.FeignServerException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import static com.converter.error.exception.ErrorCode.Group.classify;
import static java.lang.String.format;

@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        log.info(format("%s 요청이 성공하지 못했습니다. status : %s, body : %s", methodKey, response.status(), response.body()));

        switch (classify(response.status())){
            case CLIENT_ERROR:
                return new FeignClientException();
            case SERVER_ERROR:
                return new FeignServerException();
            default:
                return new Exception("Generic error");
        }
    }
}
