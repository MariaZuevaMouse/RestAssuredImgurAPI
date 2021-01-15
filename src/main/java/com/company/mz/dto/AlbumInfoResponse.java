package com.company.mz.dto;

import com.company.mz.accounttest.CommonResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlbumInfoResponse extends CommonResponse<List<AlbumInfoResponse.Data>> {

    @JsonProperty("data")
    public List<Data> data = new ArrayList<Data>();

    @lombok.Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class Data {
        @JsonProperty("id")
        public String id;
        @JsonProperty("title")
        public String title;
        @JsonProperty("description")
        public String description;
        @JsonProperty("datetime")
        public Integer datetime;
        @JsonProperty("cover")
        public String cover;
        @JsonProperty("cover_edited")
        public Integer coverEdited;
        @JsonProperty("cover_width")
        public Integer coverWidth;
        @JsonProperty("cover_height")
        public Integer coverHeight;
        @JsonProperty("account_url")
        public String accountUrl;
        @JsonProperty("account_id")
        public Integer accountId;
        @JsonProperty("privacy")
        public String privacy;
        @JsonProperty("layout")
        public String layout;
        @JsonProperty("views")
        public Integer views;
        @JsonProperty("link")
        public String link;
        @JsonProperty("favorite")
        public Boolean favorite;
        @JsonProperty("nsfw")
        public Object nsfw;
        @JsonProperty("section")
        public Object section;
        @JsonProperty("images_count")
        public Integer imagesCount;
        @JsonProperty("in_gallery")
        public Boolean inGallery;
        @JsonProperty("is_ad")
        public Boolean isAd;
        @JsonProperty("include_album_ads")
        public Boolean includeAlbumAds;
        @JsonProperty("is_album")
        public Boolean isAlbum;
        @JsonProperty("deletehash")
        public String deletehash;
        @JsonProperty("order")
        public Integer order;

        public Data() {
        }
    }
}
