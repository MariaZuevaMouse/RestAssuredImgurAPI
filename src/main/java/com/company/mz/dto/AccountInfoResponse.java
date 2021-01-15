package com.company.mz.dto;

import com.company.mz.accounttest.CommonResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class AccountInfoResponse extends CommonResponse<AccountInfoResponse.Data> {
//    @JsonProperty("data")
//    public Data data;
//    @JsonProperty("success")
//    public Boolean success;
//    @JsonProperty("status")
//    public Integer status;

    @lombok.Data
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class Data {

        @JsonProperty("id")
        public Integer id;
        @JsonProperty("url")
        public String url;
        @JsonProperty("bio")
        public Object bio;
        @JsonProperty("avatar")
        public String avatar;
        @JsonProperty("avatar_name")
        public String avatarName;
        @JsonProperty("cover")
        public String cover;
        @JsonProperty("cover_name")
        public String coverName;
        @JsonProperty("reputation")
        public Integer reputation;
        @JsonProperty("reputation_name")
        public String reputationName;
        @JsonProperty("created")
        public Integer created;
        @JsonProperty("pro_expiration")
        public Boolean proExpiration;
        @JsonProperty("user_follow")
        public UserFollow userFollow;
        @JsonProperty("is_blocked")
        public Boolean isBlocked;


        @lombok.Data
        @NoArgsConstructor
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public class UserFollow {

            @JsonProperty("status")
            public Boolean status;

        }

    }


}
