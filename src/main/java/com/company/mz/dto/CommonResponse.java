package com.company.mz.accounttest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class CommonResponse<AnyData> {

    @JsonProperty("data")
    public AnyData data;
    @JsonProperty("success")
    public Boolean success;
    @JsonProperty("status")
    public Integer status;

}
