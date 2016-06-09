package putitout.zipjetclone.ui.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import putitout.zipjetclone.ui.util.ZUtil;


public class Parser {

    public static final int RESPONSE_TYPE_VIDEO = 1;
    public static final int RESPONSE_TYPE_TIMELINE = 2;
    public static final int RESPONSE_TYPE_POST = 3;
    public static final int RESPONSE_TYPE_NOTIFICATION = 4;
    public static final int RESPONSE_TYPE_MILESTONE = 5;
    public static final int RESPONSE_TYPE_REFRESH_TIMELINE = 6;

    private String response = "";
    private String status = "";
    private String message = "";
    private String data = "";
    private String id = "";
    private String token = "";
    private String firstName = "";
    private String lastName = "";
    private String middleName = "";
    private String dateModified = "";
    private String userStatus = "";
    private String registrationOrigin = "";
    private String type = "";
    private String gender = "";
    private String babyId = "";
    private String requestId;
    private String inviteSentOn;
    private String albumId = "", medicalId = "";
    private String image;
    private String emailAddress;
    private String inviteeDecidedOn;
    private JSONObject jsonObjectData;

    private ProfileModel profile;
//    private AlbumListModel albumModel;
//
//    private ArrayList<Connection> connectionsArrayList;
//    private ArrayList<BabyModel> babiesList = new ArrayList<BabyModel>();
//    private ArrayList<FriendModel> friendList = new ArrayList<FriendModel>();
//    private ArrayList<FriendRequestModel> friendRequestModels = new ArrayList<FriendRequestModel>();
//    private ArrayList<RequestModel> deleteFriendsModels = new ArrayList<RequestModel>();
//    private ArrayList<AlbumListModel> albumList = new ArrayList<AlbumListModel>();
//    private ArrayList<MedicalModel> medicalList = new ArrayList<MedicalModel>();
//    private ArrayList<AlbumMediaModel> albumMediaList = new ArrayList<AlbumMediaModel>();
//    private ArrayList<CommentListModel> commentList = new ArrayList<CommentListModel>();
//    private ArrayList<SmileListModel> smileList = new ArrayList<SmileListModel>();
//    private ArrayList<VideoModel> videoList = new ArrayList<VideoModel>();
//    private ArrayList<TimelineModel> timelineList = new ArrayList<TimelineModel>();
//    private ArrayList<TimelineModel> refreshTimelineList = new ArrayList<TimelineModel>();
//    private ArrayList<NotificationModel> notificationList = new ArrayList<NotificationModel>();
//    private ArrayList<MilestoneModel> milestoneList = new ArrayList<MilestoneModel>();

    int responseType;
    int totalPosts;
    int refreshTotalPosts;
    int totalMilestones;

    public Parser(String response) {
        this.responseType = 0;
        parseResponseData(response);
    }

    public Parser(String response, int responseType) {
        this.responseType = responseType;
        parseResponseData(response);
    }

    private void parseResponseData(String response) {
        try {
            JSONObject parseJsonObject = new JSONObject(response);
            if (parseJsonObject.has(ZUtil.KEY_SERVER_RESPONSE_STATUS)) {
                status = parseJsonObject
                        .getString(ZUtil.KEY_SERVER_RESPONSE_STATUS);
            }
            if (parseJsonObject.has(ZUtil.KEY_SERVER_RESPONSE_MESSAGE)) {
                message = parseJsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_MESSAGE);
            }
//            if(parseJsonObject.has(ZUtil.KEY_SERVER_RESPONSE_DATA)){
//                jsonObjectData = parseJsonObject.getJSONObject(ZUtil.KEY_SERVER_RESPONSE_DATA);
//            }
            if (parseJsonObject.has(ZUtil.KEY_SERVER_RESPONSE_DATA) && !parseJsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_DATA).equals("")) {



                JSONObject jsonData = new JSONObject(parseJsonObject.
                        getString(ZUtil.KEY_SERVER_RESPONSE_DATA));
                if (jsonData.has(ZUtil.KEY_SERVER_RESPONSE_TOKEN)) {
                    token = jsonData.getString(ZUtil.KEY_SERVER_RESPONSE_TOKEN);
                }

                if (jsonData.has(ZUtil.KEY_SERVER_RESPONSE_REQUEST_ID)) {
                    requestId = jsonData.getString(ZUtil.KEY_SERVER_RESPONSE_REQUEST_ID);
                }

                if (jsonData.has(ZUtil.KEY_SERVER_RESPONSE_DETAILS)) {
                    JSONObject userInfo = new JSONObject(jsonData.getString
                            (ZUtil.KEY_SERVER_RESPONSE_DETAILS));
                    parseUserInfo(userInfo);
                }



                if (jsonData.has((ZUtil.KEY_SERVER_RESPONSE_FRIEND_REQUESTS))) {
                    JSONArray parseFriendRequests = jsonData.getJSONArray(ZUtil.KEY_SERVER_RESPONSE_FRIEND_REQUESTS);
                    parseFriendRequestsData(parseFriendRequests);
                }

                if (jsonData.has((ZUtil.KEY_SERVER_RESPONSE_FRIEND_SUGGESTION))) {
                    JSONArray parseSearchedUsers = jsonData.getJSONArray(ZUtil.KEY_SERVER_RESPONSE_FRIEND_SUGGESTION);
                    parseSearchedUsers(parseSearchedUsers);
                }

                if (jsonData.has(ZUtil.KEY_SERVER_RESPONSE_ALBUM_ID)) {
                    albumId = jsonData.getString(ZUtil.KEY_SERVER_RESPONSE_ALBUM_ID);
                }

                if (jsonData.has(ZUtil.KEY_SERVER_RESPONSE_MEDICAL_ID)) {
                    medicalId = jsonData.getString(ZUtil.KEY_SERVER_RESPONSE_MEDICAL_ID);
                }

                if (jsonData.has(ZUtil.KEY_SERVER_RESPONSE_ID)) {
                    id = jsonData.getString(ZUtil.KEY_SERVER_RESPONSE_ID);
                }

                if (jsonData.has(ZUtil.KEY_SERVER_RESPONSE_BABY_ID)) {
                    babyId = jsonData.getString(ZUtil.KEY_SERVER_RESPONSE_BABY_ID);
                }

                if (jsonData.has(ZUtil.KEY_SERVER_RESPONSE_USER_ID)) {
                    id = jsonData.getString(ZUtil.KEY_SERVER_RESPONSE_USER_ID);
                }

                if (jsonData.has(ZUtil.KEY_SERVER_RESPONSE_TOKEN)) {
                    token = jsonData.getString(ZUtil.KEY_SERVER_RESPONSE_TOKEN);
                }

                if (jsonData.has(ZUtil.KEY_SERVER_RESPONSE_CHILDREN)) {
                    parseBabiesList(jsonData.getJSONArray(ZUtil.KEY_SERVER_RESPONSE_CHILDREN));
                }

                if (jsonData.has(ZUtil.KEY_SERVER_RESPONSE_FRIEND_SUGGESTION)) {
                    parseFriendList(jsonData.getJSONArray(ZUtil.KEY_SERVER_RESPONSE_FRIEND_SUGGESTION));
                }

                if (jsonData.has(ZUtil.KEY_SERVER_RESPONSE_USERS)) {
                    parseFriendList(jsonData.getJSONArray(ZUtil.KEY_SERVER_RESPONSE_USERS));
                }

                if(jsonData.has(ZUtil.KEY_SERVER_RESPONSE_FIRST_NAME)) {
                    firstName = jsonData.getString(ZUtil.KEY_SERVER_RESPONSE_FIRST_NAME);
                }

                if (jsonData.has(ZUtil.KEY_SERVER_RESPONSE_LAST_NAME)) {
                    lastName = jsonData.getString(ZUtil.KEY_SERVER_RESPONSE_LAST_NAME);
                }

                if (jsonData.has(ZUtil.KEY_SERVER_RESPONSE_MIDDLE_NAME)) {
                    middleName = jsonData.getString(ZUtil.KEY_SERVER_RESPONSE_MIDDLE_NAME);
                }

                if (jsonData.has(ZUtil.KEY_SERVER_RESPONSE_DATE_MODIFIED)) {
                    dateModified = jsonData.getString(ZUtil.KEY_SERVER_RESPONSE_DATE_MODIFIED);
                }

                if (jsonData.has(ZUtil.KEY_SERVER_RESPONSE_USER_STATUS)) {
                    userStatus = jsonData.getString(ZUtil.KEY_SERVER_RESPONSE_USER_STATUS);
                }

                if (jsonData.has(ZUtil.KEY_SERVER_RESPONSE_GENDER)) {
                    gender = jsonData.getString(ZUtil.KEY_SERVER_RESPONSE_GENDER);
                }

                if (jsonData.has(ZUtil.KEY_SERVER_RESPONSE_TYPE)) {
                    type = jsonData.getString(ZUtil.KEY_SERVER_RESPONSE_TYPE);
                }

                if(jsonData.has(ZUtil.KEY_SERVER_RESPONSE_IMAGE)) {
                    image = jsonData.getString(ZUtil.KEY_SERVER_RESPONSE_IMAGE);
                }



                if (jsonData.has(ZUtil.KEY_SERVER_RESPONSE_ALL_FRIENDS)) {
                    parseDeleteFriendsList(jsonData.getJSONArray(ZUtil.KEY_SERVER_RESPONSE_ALL_FRIENDS));
                }


            }

        } catch (JSONException je) {
            je.printStackTrace();
        }
    }

    private void parseFriendRequestsData(JSONArray parseFriendRequests) {
        try {
            for (int i = 0; i < parseFriendRequests.length(); i++) {
                JSONObject jsonObject = parseFriendRequests.getJSONObject(i);
                String requestId = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_REQUEST_ID);
                String image = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_IMAGE);
                String name = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_NAME);
                String userId = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_USER_ID);
                String relation = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_RELATION);
                String status = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_STATUS);
                String dateCreated = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_DATE_CREATED);
                String dateModified = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_DATE_MODIFIED);

//                FriendRequestModel friendRequestModel = new FriendRequestModel(requestId, userId, name, image, relation, status, dateCreated, dateModified);
//                friendRequestModels.add(friendRequestModel);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }





    private void parseNotifications(JSONObject dataJsonObject){
        try {
            JSONArray jsonArrayNotifications = dataJsonObject.getJSONArray("activities");
            for(int i = 0; i < jsonArrayNotifications.length(); i++) {
                Log.i("Timeline", "Notification length is " + jsonArrayNotifications.length());
//            for(int i = 0; i < 12; i++){
                JSONObject jsonObjectNotification = jsonArrayNotifications.getJSONObject(i);
                String id = jsonObjectNotification.getString(ZUtil.KEY_SERVER_RESPONSE_ID);
                String userId = jsonObjectNotification.getString(ZUtil.KEY_SERVER_RESPONSE_USER_ID);
                String notificationType = jsonObjectNotification.getString(ZUtil.KEY_SERVER_RESPONSE_NOTIFICATION_TYPE);
                String description = jsonObjectNotification.getString(ZUtil.KEY_SERVER_RESPONSE_DESCRIPTION);
                String readStatus = jsonObjectNotification.getString(ZUtil.KEY_SERVER_RESPONSE_READ_STATUS);
                String notifyUser = jsonObjectNotification.getString(ZUtil.KEY_SERVER_RESPONSE_NOTIFY_USER);
                String relatedTo = jsonObjectNotification.getString(ZUtil.KEY_SERVER_RESPONSE_RELATED_TO);
                String relatedId = jsonObjectNotification.getString(ZUtil.KEY_SERVER_RESPONSE_RELATED_ID);
                String additionalInfo = jsonObjectNotification.getString(ZUtil.KEY_SERVER_RESPONSE_ADDITIONAL_INFO);
                String dateCreated = jsonObjectNotification.getString(ZUtil.KEY_SERVER_RESPONSE_DATE_CREATED);
                String firstName = jsonObjectNotification.getString(ZUtil.KEY_SERVER_RESPONSE_FIRST_NAME);
                String lastName = jsonObjectNotification.getString(ZUtil.KEY_SERVER_RESPONSE_LAST_NAME);
                String middleName = jsonObjectNotification.getString(ZUtil.KEY_SERVER_RESPONSE_MIDDLE_NAME);
                String name = jsonObjectNotification.getString(ZUtil.KEY_SERVER_RESPONSE_NAME);
                String image = jsonObjectNotification.getString(ZUtil.KEY_SERVER_RESPONSE_IMAGE);


//                NotificationModel notificationModel = new NotificationModel(id,userId,notificationType,description,readStatus,notifyUser,
//                        relatedTo,relatedId,additionalInfo,dateCreated,firstName,lastName,middleName,name,image);
//                notificationList.add(notificationModel);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

//    private void parseUserProfile(JSONObject dataJsonObject) {
//        try {
//            if (dataJsonObject.has(ZUtil.KEY_SERVER_RESPONSE_USER_PROFILE)) {
//                JSONObject jsonObject = dataJsonObject.getJSONObject(ZUtil.KEY_SERVER_RESPONSE_USER_PROFILE);
//                profile = new ProfileModel();
//                profile.setId(jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_ID));
//                profile.setName(jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_NAME));
//                profile.setGender(jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_GENDER));
//                profile.setImage(jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_IMAGE));
//            }
//            if (dataJsonObject.has(ZUtil.KEY_SERVER_RESPONSE_CHILDREN_PROFILES)) {
//                JSONArray jsonArray = dataJsonObject.getJSONArray(ZUtil.KEY_SERVER_RESPONSE_CHILDREN_PROFILES);
//                ArrayList<ChildrenModel> childrenList = new ArrayList<ChildrenModel>();
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//                    String childId = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_CHILD_ID);
//                    String name = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_NAME);
//                    JSONObject ageJsonObject = jsonObject.getJSONObject(ZUtil.KEY_SERVER_RESPONSE_AGE);
//                    String ageYears = ageJsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_YEARS);
//                    String ageMonths = ageJsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_MONTHS);
//                    String ageDays = ageJsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_DAYS);
//                    String birthday = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_BIRTHDAY);
//                    String weight = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_WEIGHT);
//                    String height = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_HEIGHT);
//                    String gender = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_GENDER);
//                    String image = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_IMAGE);
//                    ChildrenModel children = new ChildrenModel(childId, name, ageYears, ageMonths, ageDays, birthday, weight, height, gender, image);
//                    childrenList.add(children);
//                }
//                profile.setChildrenList(childrenList);
//            }
//
//            if (dataJsonObject.has(ZUtil.KEY_SERVER_RESPONSE_FRIENDS)) {
//                String jsonObjectString = dataJsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_FRIENDS);
//                if (!jsonObjectString.equals("")) {
//                    JSONObject jsonObject = new JSONObject(jsonObjectString);
//                    String requestId = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_REQUEST_ID);
//                    String name = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_NAME);
//                    String image = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_IMAGE);
//                    String relation = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_RELATION);
//                    String senderId = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_SENDER_ID);
//                    String receiverId = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_RECEIVER_ID);
//                    RequestModel requestModel = new RequestModel(image, name, relation, senderId, receiverId, requestId);
//                    profile.setFriendRequestModel(requestModel);
//                }
//            }
//
//            if (dataJsonObject.has(ZUtil.KEY_SERVER_RESPONSE_REQUESTS)) {
//                String jsonObjectString = dataJsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_REQUESTS);
//                if (!jsonObjectString.equals("")) {
//                    JSONObject jsonObject = new JSONObject(jsonObjectString);
//                    String requestId = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_REQUEST_ID);
//                    String name = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_NAME);
//                    String image = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_IMAGE);
//                    String relation = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_RELATION);
//                    String senderId = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_SENDER_ID);
//                    String receiverId = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_RECEIVER_ID);
//                    RequestModel requestModel = new RequestModel(image, name, relation, senderId, receiverId, requestId);
//                    profile.setRequestRequestModel(requestModel);
//                }
//            }
//
//        } catch (JSONException je) {
//            je.printStackTrace();
//        }
//    }

    public void parseUserInfo(JSONObject userInfo) {
        try {
            if (userInfo.has(ZUtil.KEY_SERVER_RESPONSE_ID)) {
                id = userInfo.getString(ZUtil.KEY_SERVER_RESPONSE_ID);
            }
            if (userInfo.has(ZUtil.KEY_SERVER_RESPONSE_FIRST_NAME)) {
                firstName = userInfo.getString(ZUtil.KEY_SERVER_RESPONSE_FIRST_NAME);
            }
            if (userInfo.has(ZUtil.KEY_SERVER_RESPONSE_LAST_NAME)) {
                lastName = userInfo.getString(ZUtil.KEY_SERVER_RESPONSE_LAST_NAME);
            }
            if (userInfo.has(ZUtil.KEY_SERVER_RESPONSE_MIDDLE_NAME)) {
                middleName = userInfo.getString(ZUtil.KEY_SERVER_RESPONSE_MIDDLE_NAME);
            }
            if (userInfo.has(ZUtil.KEY_SERVER_RESPONSE_REGISTRATION_ORIGIN)) {
                registrationOrigin = userInfo.getString(ZUtil.KEY_SERVER_RESPONSE_REGISTRATION_ORIGIN);
            }
            if (userInfo.has(ZUtil.KEY_SERVER_RESPONSE_DATE_MODIFIED)) {
                dateModified = userInfo.getString(ZUtil.KEY_SERVER_RESPONSE_DATE_MODIFIED);
            }
            if (userInfo.has(ZUtil.KEY_SERVER_RESPONSE_USER_STATUS)) {
                userStatus = userInfo.getString(ZUtil.KEY_SERVER_RESPONSE_USER_STATUS);
            }
            if (userInfo.has(ZUtil.KEY_SERVER_RESPONSE_GENDER)) {
                gender = userInfo.getString(ZUtil.KEY_SERVER_RESPONSE_GENDER);
            }
            if (userInfo.has(ZUtil.KEY_SERVER_RESPONSE_TYPE)) {
                type = userInfo.getString(ZUtil.KEY_SERVER_RESPONSE_TYPE);
            }
            if(userInfo.has(ZUtil.KEY_SERVER_RESPONSE_EMAIL)) {
                emailAddress = userInfo.getString(ZUtil.KEY_SERVER_RESPONSE_EMAIL);
            }
            if(userInfo.has((ZUtil.KEY_SERVER_RESPONSE_IMAGE))) {
                image = userInfo.getString(ZUtil.KEY_SERVER_RESPONSE_IMAGE);
            }

        } catch (JSONException je) {
            je.printStackTrace();
        }
    }



    private void parseBabiesList(JSONArray jsonArray) {
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = null;
                if (jsonObject.has(ZUtil.KEY_SERVER_RESPONSE_CHILD_ID)) {
                    id = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_CHILD_ID);
                } else if (jsonObject.has(ZUtil.KEY_SERVER_RESPONSE_ID)) {
                    id = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_ID);
                }
                String firstName = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_FIRST_NAME);
                String lastName = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_LAST_NAME);
                String middleName = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_MIDDLE_NAME);
                String gender = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_GENDER);
                String birthday = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_BIRTHDAY);
                String date_created = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_DATE_CREATED);
                String date_modified = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_DATE_MODIFIED);
                String weight = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_WEIGHT);
                String height = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_HEIGHT);
                String user_id = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_USER_ID);
                String image = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_IMAGE);

//                BabyModel babyModel = new BabyModel(id, firstName, middleName, lastName, gender, birthday, date_created, date_modified, weight, height, user_id, image);
//
//                babiesList.add(babyModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseFriendList(JSONArray jsonArray) {
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_ID);
                String name = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_NAME);
                String image = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_IMAGE);
                String requestSender = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_REQUEST_SENDER);
                String requestReceiver = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_REQUEST_RECEIVER);
                String friendAsSender = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_FRIEND_AS_SENDER);
                String friendAsReceiver = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_FRIEND_AS_RECEIVER);
                String requestId = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_REQUEST_ID);

//                FriendModel friendModel = new FriendModel(id, name, image, requestSender, requestReceiver, friendAsSender, friendAsReceiver, requestId);
//
//                friendList.add(friendModel);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }

    private void parseSearchedUsers(JSONArray jsonArray) {
        try {
            for (int i = 0; i<jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_ID);
                String name = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_NAME);
                String image = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_IMAGE);
                String requestSender = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_REQUEST_SENDER);
                String requestReceiver = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_REQUEST_RECEIVER);
                String friendAsSender = "";
                String friendAsReceiver = "";
                String requestId = "";
//                FriendModel friends = new FriendModel(id, name, image, requestSender, requestReceiver, friendAsSender, friendAsReceiver, requestId);
//                friendList.add(friends);

            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }

    private void parseDeleteFriendsList(JSONArray jsonArray) {
        try {
            for (int i = 0; i<jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String requestId = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_REQUEST_ID);
                String name = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_NAME);
                String image = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_IMAGE);
                String relation = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_RELATION);
                String senderId = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_SENDER_ID);
                String receiverId = jsonObject.getString(ZUtil.KEY_SERVER_RESPONSE_RECEIVER_ID);

//                RequestModel requestModel = new RequestModel(image, name, relation, senderId, receiverId, requestId);
//
//                deleteFriendsModels.add(requestModel);
            }
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }



    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public String getData() {
        return data;
    }

    public String getId() {
        return id;
    }

    public String getResponse() {
        return response;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getDateModified() {
        return dateModified;
    }

    public String getRegistrationOrigin() {
        return registrationOrigin;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public String getType() {
        return type;
    }

    public String getGender() {
        return gender;
    }

    public String getBabyId() {
        return babyId;
    }

    public String getEmail() {
        return emailAddress;
    }

    public String getImage() {
        return image;
    }

//    public ArrayList<Connection> getConnections() {
//        return connectionsArrayList;
//    }
//
//    public ArrayList<BabyModel> getBabiesList() {
//        return babiesList;
//    }

    public ProfileModel getProfile() { return profile; }

//    public ArrayList<FriendModel> getFriendsSuggestion() {
//        return friendList;
//    }
//
//    public ArrayList<RequestModel> getDeleteFriendsModels() {
//        return deleteFriendsModels;
//    }

    public String getRequestId() {
        return requestId;
    }

    public String getAlbumId() {
        return albumId;
    }

    public String getMedicalId() {
        return medicalId;
    }



    public int getTotalPosts(){ return totalPosts;}

    public int getRefreshTotalPosts(){ return refreshTotalPosts;}

    public int getTotalMilestones(){ return totalMilestones;}

    public JSONObject getJsonObjectData(){ return jsonObjectData;}
}
