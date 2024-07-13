package com.jrp.model.payload;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

import java.io.Serializable;

@Data
@Builder
public class ResponseMessage implements Serializable {

    private String message;
    private Object object;

}
