package putitout.zipjetclone.ui.util;

public class URLManager {
    public static final String SERVER_ULR = "http://kidlr.lotiv.com";
    public static final String REGISTER_USER = SERVER_ULR + "/api/user/register";
    public static final String SOCIAL_LOGIN = SERVER_ULR + "/api/user/social-login";
    public static final String VERIFY_SOCIAL_LOGIN = SERVER_ULR + "/api/user/verify-social-id";
    public static final String LOGIN_USER = SERVER_ULR + "/api/user/login";
    public static final String ADD_NEW_BABY = SERVER_ULR + "/api/user/baby/add";
    public static final String UPDATE_BABY = SERVER_ULR + "/api/user/baby/update";
    public static final String UPLOAD_IMAGE = SERVER_ULR + "/api/image/upload";
    public static final String INVITE_USER = SERVER_ULR + "/api/user/invite";
    public static final String LIST_BABIES = SERVER_ULR + "/api/user/baby/list";
    public static final String LIST_TAGABLE_BABIES = SERVER_ULR + "/api/user/friends/children";
    public static final String UPDATE_FRIEND_RELATION = SERVER_ULR + "/api/user/friends/relation/update";
    public static final String LIST_SENT_REQUEST = SERVER_ULR + "/api/user/friends/sent";
    public static final String USER_PROFILE = SERVER_ULR + "/api/user/profile";
    public static final String SUGGEST_FRIEND_LIST = SERVER_ULR + "/api/user/friends/suggested";
    public static final String SEARCH_FRIENDS_FROM_KIDLR = SERVER_ULR + "/api/user/friends/search";
    public static final String UPDATE_USER_PROFILE = SERVER_ULR + "/api/user/update";
    public static final String ADD_FRIEND_REQUEST = SERVER_ULR + "/api/user/friends/request/send";
    public static final String DELETE_BABY = SERVER_ULR + "/api/user/baby/delete";
    public static final String FRIEND_REQUEST_LIST = SERVER_ULR + "/api/user/friends/requests";
    public static final String UPDATE_FRIEND_STATUS = SERVER_ULR + "/api/user/friends/request/update";
    public static final String CANCEL_FRIEND_REQUEST = SERVER_ULR + "/api/user/friends/request/cancel";
    public static final String DELETE_FRIEND_REQUEST = SERVER_ULR + "/api/user/friends/request/delete";
    public static final String DELETE_FRIEND_LIST = SERVER_ULR + "/api/user/friends/all";
    public static final String CREATE_ALBUM = SERVER_ULR + "/api/album/create";
    public static final String UPDATE_ALBUM = SERVER_ULR + "/api/album/update";
    public static final String SHOW_ALBUM_REFRESH = SERVER_ULR + "/api/album/show";
    public static final String LIST_ALBUM = SERVER_ULR + "/api/album/list";
    public static final String UPLOAD_ALBUM = SERVER_ULR + "/api/album/media/upload";
    public static final String SHOW_MEDIA = SERVER_ULR + "/api/album/media/show";
    public static final String ALBUM_MEDIA_LIST = SERVER_ULR + "/api/album/media/list";
    public static final String REMOVE_MEDIA = SERVER_ULR + "/api/album/media/remove";
    public static final String DELETE_ALBUM = SERVER_ULR + "/api/album/delete";
    public static final String CREATE_COMMENT = SERVER_ULR + "/api/comment/create";
    public static final String LIST_COMMENT = SERVER_ULR + "/api/comment/list";
    public static final String CREATE_SMILE = SERVER_ULR + "/api/smile/create";
    public static final String LIST_SMILE = SERVER_ULR + "/api/smile/list";
    public static final String LIST_COMMENT_AND_SMILE = SERVER_ULR + "/api/album/media/socials";
    public static final String DELETE_COMMENT = SERVER_ULR + "/api/comment/delete";
    public static final String UPDATE_COMMENT = SERVER_ULR + "/api/comment/update";
    public static final String CREATE_MEDICAL = SERVER_ULR + "/api/medical/create";
    public static final String LIST_MEDICAL = SERVER_ULR + "/api/medical/list";
    public static final String DELETE_MEDICAL = SERVER_ULR + "/api/medical/delete";
    public static final String UPDATE_MEDICAL = SERVER_ULR + "/api/medical/update";
    public static final String SHOW_MEDICAL = SERVER_ULR + "/api/medical/show";
    public static final String LIST_VIDEO = SERVER_ULR + "/api/video/list";
    public static final String DELETE_VIDEO = SERVER_ULR + "/api/video/delete";
    public static final String UPLOAD_VIDEO = SERVER_ULR + "/api/video/upload";
    public static final String UPDATE_VIDEO = SERVER_ULR + "/api/video/update";
    public static final String RESET_PASSWORD = SERVER_ULR + "/api/user/reset-password";
    public static final String FORGET_PASSWORD = SERVER_ULR + "/api/user/forget-password";
    public static final String USER_TIMELINE = SERVER_ULR + "/api/user/timeline";
    public static final String CREATE_POST = SERVER_ULR + "/api/post/create";
    public static final String UPDATE_POST = SERVER_ULR + "/api/post/update";
    public static final String DELETE_POST = SERVER_ULR + "/api/post/delete";
    public static final String LIST_NOTIFICATION = SERVER_ULR + "/api/user/activities/list";
    public static final String LIST_MILESTONES = SERVER_ULR + "/api/user/milestones/list";



    public static String getImageURL(String accessToken, String relatedTo, String relatedId, String category) {
        //http://kidlr-web.localhost/api/image/show?access_token=1440056934216CE697-88CA-409B-B4F6-946F87F5ECE6&related_to=users&related_id=6&category=main_image
        StringBuilder stringBuilder = new StringBuilder("http://kidlr-web.localhost/api/image/show?");
        stringBuilder.append("access_token=" + accessToken);
        stringBuilder.append("&rt=" + relatedTo);
        stringBuilder.append("&ri=" + relatedId);
        stringBuilder.append("&ca=" + category);
        //KLog.info("Image URL : "+stringBuilder.toString());
        return stringBuilder.toString();
    }

}
