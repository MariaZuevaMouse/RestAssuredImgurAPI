package com.company.mz.endpoints;

public final class Endpoints {
    public static final String ACCOUNT_INFO = "/account/{user}";
    public static final String ALBUM_COUNT ="account/{username}/albums/count";
    public static final String ALBUM_CREATE ="album";
    public static final String ALBUM_AUTH_DELETE ="album/{albumHash}";
    public static final String ALBUM_UNAUTH_DELETE ="album/{albumDeleteHash}";
    public static final String ALBUM_UPDATE ="album/{albumHash}";
    public static final String ALBUM_IDS_INFO ="account/{username}/albums/ids";
    public static final String ALBUM_INFO ="account/{username}/albums/";

    public static final String IMAGE_AUTH_DELETE ="image/{imageHash}";
    public static final String IMAGE_UNAUTH_DELETE ="/image/{imageDeleteHash}";
    public static final String IMAGE_FAVORITE ="/image/{imageHash}/favorite";
    public static final String IMAGE_UPDATE ="/image/{imageHash}";
    public static final String IMAGE_INFO = "image/{imgehash}";
    public static final String IMAGE_POST = "/image";

}
