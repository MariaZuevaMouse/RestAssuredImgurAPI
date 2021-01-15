package com.company.mz.dto;

import com.company.mz.accounttest.CommonResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AlbumInfoResponseUnAuth extends CommonResponse<AlbumInfoResponseUnAuth.Data> {

    @lombok.Data
    public class Data {

        @JsonProperty("error")
        public String error;
        @JsonProperty("request")
        public String request;
        @JsonProperty("method")
        public String method;

    }
}
