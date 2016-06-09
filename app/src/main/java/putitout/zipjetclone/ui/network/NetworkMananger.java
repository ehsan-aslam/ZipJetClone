package putitout.zipjetclone.ui.network;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;

import putitout.zipjetclone.ui.interfaces.OnWebServiceResponse;
import putitout.zipjetclone.ui.model.UserModel;
import putitout.zipjetclone.ui.util.URLManager;
import putitout.zipjetclone.ui.util.ZPrefs;
import putitout.zipjetclone.ui.util.ZUtil;
import putitout.zipjetclone.ui.webservices.APIWebService;


public class NetworkMananger {
    public static final int HTTP_GET_REQUEST_TYPE = 0;
    public static final int HTTP_POST_REQUEST_TYPE = 1;
    public static final int HTTP_PUT_REQUEST_TYPE = 2;
    public static final int HTTP_DELETE_REQUEST_TYPE = 3;

    public static String getApiResponse(String url, ArrayList<NameValuePair> parameters,
                                        int requestType) throws Exception {
        BufferedReader bufferReader = null;
        String result = ZUtil.KEY_SERVER_NO_RESPONSE;
        try {
            HttpResponse response = getHttpResponse(url, parameters, requestType);
            bufferReader = new BufferedReader(new InputStreamReader(response
                    .getEntity().getContent()));
            StringBuffer stringBuffer = new StringBuffer("");
            String line = "";
            String lineSeparator = System.getProperty("line.separator");
            while ((line = bufferReader.readLine()) != null) {
                stringBuffer.append(line + lineSeparator);
            }
            bufferReader.close();
            result = stringBuffer.toString();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            result = ZUtil.KEY_SERVER_NO_RESPONSE;
        } catch (IOException io) {
            if (bufferReader != null) {
                try {
                    bufferReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            io.printStackTrace();
            result = ZUtil.KEY_SERVER_NO_RESPONSE;
        } catch (Exception e) {
            e.printStackTrace();
            result = ZUtil.KEY_SERVER_NO_RESPONSE;
        }
        return result;
    }




    public static HttpResponse getHttpResponse(String webUrl, ArrayList<NameValuePair> parameters,
                                               int requestCode) throws Exception {

        HttpClient httpClient = new DefaultHttpClient();

        HttpResponse response = null;
        HttpGet httpGet = null;
        HttpEntityEnclosingRequestBase request = null;
        switch (requestCode) {
            case HTTP_GET_REQUEST_TYPE:
                httpGet = new HttpGet();
                break;
            case HTTP_POST_REQUEST_TYPE:
                request = new HttpPost(webUrl);
                break;
            case HTTP_PUT_REQUEST_TYPE:
                request = new HttpPut(webUrl);
                break;
            case HTTP_DELETE_REQUEST_TYPE:
                request = new HttpDeleteWithBody(
                        webUrl);
                break;
        }
        if (requestCode != HTTP_GET_REQUEST_TYPE) {
            request.addHeader("auth-token",
                    ZUtil.AUTH_TOKEN_KEY);
            UrlEncodedFormEntity urlEncodedFormEntitiy = new UrlEncodedFormEntity(parameters,
                    HTTP.UTF_8);
            request.setEntity(urlEncodedFormEntitiy);
            response = httpClient.execute(request);
        } else {
            String parametsString = "?";
            for(NameValuePair nameValuePair : parameters) {
                parametsString += (nameValuePair.getName() +"="+nameValuePair.getValue() + "&");
            }
            parametsString = parametsString.substring(0, parametsString.length()-1);
            URI website = new URI(webUrl + parametsString);
            httpGet.setURI(website);
            response = httpClient.execute(httpGet);
        }
        return response;
    }



    private static void sendApiRequest(Context context, int requestCode, ArrayList<NameValuePair>
            params, OnWebServiceResponse listener, int requestType, String url) {
        APIWebService apiWebService = new APIWebService(
                context, requestCode, params, requestType, url);
        apiWebService.setOnWebServiceResponseListener(listener);
        startAsyncTaskInParallel(apiWebService);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static void startAsyncTaskInParallel(APIWebService task) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
        else {
            task.execute();
        }
    }

    public static class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {
        public static final String METHOD_NAME = "DELETE";

        public String getMethod() {
            return METHOD_NAME;
        }

        public HttpDeleteWithBody(final String uri) {
            super();
            setURI(URI.create(uri));
        }

        public HttpDeleteWithBody(final URI uri) {
            super();
            setURI(uri);
        }

        public HttpDeleteWithBody() {
            super();
        }
    }

    public static final void createNewUserApi(Context context, OnWebServiceResponse webserviceResponseListener,
                                              int requestCode, UserModel model) {

        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(ZUtil.KEY_USERNAME,
                model.getUserName()));
        params.add(new BasicNameValuePair(ZUtil.KEY_FIRST_NAME,
                model.getFirstName()));
        params.add(new BasicNameValuePair(ZUtil.KEY_MIDDLE_NAME,
                ""));
        params.add(new BasicNameValuePair(ZUtil.KEY_LAST_NAME,
                model.getLastName()));
        params.add(new BasicNameValuePair(ZUtil.KEY_EMAIL,
                model.getEmailAddress()));
        params.add(new BasicNameValuePair(ZUtil.KEY_GENDER,
                model.getGender()));
        params.add(new BasicNameValuePair(ZUtil.KEY_PASSWORD,
                ZUtil.convertStringToSHA256(model.getPassword())));
        params.add(new BasicNameValuePair(ZUtil.KEY_REGISTRATION_TYPE,
                model.getRegistrationType()));
        if (!model.getRegistrationType().equals(ZUtil.KEY_STANDARD)) {
            params.add(new BasicNameValuePair(ZUtil.KEY_SOCIAL_ID,
                    model.getSocialId()));
        }
        params.add(new BasicNameValuePair(ZUtil.KEY_STATUS,
                model.getStatus() + ""));
        params.add(new BasicNameValuePair(ZUtil.KEY_TYPE,
                model.getType()));
        sendApiRequest(context, requestCode, params, webserviceResponseListener,
                NetworkMananger.HTTP_POST_REQUEST_TYPE, URLManager.REGISTER_USER);

    }

    public static void loginUserApi(Context context, String email, String password, OnWebServiceResponse
            webServiceResponse, int requestCode) {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(ZUtil.KEY_EMAIL, email));
        params.add(new BasicNameValuePair(ZUtil.KEY_PASSWORD, ZUtil.convertStringToSHA256(password)));
        sendApiRequest(context, requestCode, params, webServiceResponse,
                NetworkMananger.HTTP_POST_REQUEST_TYPE, URLManager.LOGIN_USER);

    }

    public static final void invokeSocialLoginApi(Context context, OnWebServiceResponse webserviceResponseListener,
                                                  int requestCode, String socialId, String registrationOrigin,String socialEmail) {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(ZUtil.KEY_SOCIAL_ID,
                socialId));
        params.add(new BasicNameValuePair(ZUtil.KEY_SOCIAL_REGISTRATION_ORIGIN,
                registrationOrigin));
        params.add(new BasicNameValuePair(ZUtil.KEY_SOCIAL_EMAIL,
                socialEmail));
        sendApiRequest(context, requestCode, params, webserviceResponseListener, NetworkMananger.HTTP_POST_REQUEST_TYPE, URLManager.SOCIAL_LOGIN);
    }

    public static final void invokeVerifySocialLoginApi(Context context, OnWebServiceResponse webserviceResponseListener, int requestCode, String socialId, String registrationOrigin) {

        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(ZUtil.KEY_SOCIAL_ID,
                socialId));
        params.add(new BasicNameValuePair(ZUtil.KEY_SOCIAL_REGISTRATION_ORIGIN,
                registrationOrigin));
        sendApiRequest(context, requestCode, params, webserviceResponseListener, NetworkMananger.HTTP_POST_REQUEST_TYPE, URLManager.VERIFY_SOCIAL_LOGIN);
    }

    public static void addNewBabyApi(Context context, OnWebServiceResponse onWebServiceResponse,
                                     int requestCode, String authToken, String babyFirstName, String babyMiddleName,
                                     String babyLastName, String babyDOB,
                                     String babyHeight, String babyWeight, String gender) {
//        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair(ZUtil.KEY_FIRST_NAME, babyFirstName));
//        params.add(new BasicNameValuePair(ZUtil.KEY_MIDDLE_NAME, babyMiddleName));
//        params.add(new BasicNameValuePair(ZUtil.KEY_LAST_NAME, babyLastName));
//        params.add(new BasicNameValuePair(ZUtil.KEY_BABY_DOB, babyDOB));
//        params.add(new BasicNameValuePair(ZUtil.KEY_GENDER, gender));
//        params.add(new BasicNameValuePair(ZUtil.KEY_BABY_HEiGHT, babyHeight));
//        params.add(new BasicNameValuePair(ZUtil.KEY_BABY_WEIGHT, babyWeight));
//        params.add(new BasicNameValuePair(ZUtil.KEY_SERVER_RESPONSE_TOKEN, authToken));
//        sendApiRequest(context, requestCode, params, onWebServiceResponse,
//                NetworkMananger.HTTP_POST_REQUEST_TYPE, URLManager.ADD_NEW_BABY);

    }

    public static void updateBabyApi(Context context, OnWebServiceResponse onWebServiceResponse,
                                     int requestCode, String authToken, String babyId, String babyFirstName, String babyMiddleName,
                                     String babyLastName, String babyDOB,
                                     String babyHeight, String babyWeight, String gender) {
//        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair(ZUtil.KEY_BABY_ID, babyId));
//        params.add(new BasicNameValuePair(ZUtil.KEY_FIRST_NAME, babyFirstName));
//        params.add(new BasicNameValuePair(ZUtil.KEY_MIDDLE_NAME, babyMiddleName));
//        params.add(new BasicNameValuePair(ZUtil.KEY_LAST_NAME, babyLastName));
//        params.add(new BasicNameValuePair(ZUtil.KEY_BABY_DOB, babyDOB));
//        params.add(new BasicNameValuePair(ZUtil.KEY_GENDER, gender));
//        params.add(new BasicNameValuePair(ZUtil.KEY_BABY_HEiGHT, babyHeight));
//        params.add(new BasicNameValuePair(ZUtil.KEY_BABY_WEIGHT, babyWeight));
//        params.add(new BasicNameValuePair(ZUtil.KEY_SERVER_RESPONSE_TOKEN, authToken));
//        sendApiRequest(context, requestCode, params, onWebServiceResponse,
//                NetworkMananger.HTTP_PUT_REQUEST_TYPE, URLManager.UPDATE_BABY);

    }

    public static void inviteUserApi(Context context, String invitees, OnWebServiceResponse
            webServiceResponse, int requestCode) {
//        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair(ZUtil.KEY_ACCESS_TOKEN, KPrefs.getString(context, KPrefs.KEY_TOKEN, "")));
//        params.add(new BasicNameValuePair(ZUtil.KEY_INVITEES, invitees));
//        sendApiRequest(context, requestCode, params, webServiceResponse,
//                NetworkMananger.HTTP_PUT_REQUEST_TYPE, URLManager.INVITE_USER);

    }

//    public static void uploadImageApi(final Context context, OnWebServiceResponse onWebServiceResponse,
//                                      int requestCode, ImageModel imageModel, String authToken)
//            throws UnsupportedEncodingException {
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        imageModel.getImageFile().compress(Bitmap.CompressFormat.JPEG, 75, byteArrayOutputStream);
//        byte[] data = byteArrayOutputStream.toByteArray();
//        ByteArrayBody byteArrayBody = new ByteArrayBody(data, ZUtil.KEY_MAIN_CATEGORY);
//        CustomMultipartEntity multipartEntity = new CustomMultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, new CustomMultipartEntity.ProgressListener() {
//            public void transferred(long num) {
//                context.sendBroadcast(new Intent(ZUtil.BROADCAST_ACTION_IMAGE_UPLOADING).putExtra(ZUtil.KEY_INDEX, num));
//            }
//        });
//
//
//        multipartEntity.addPart(ZUtil.KEY_IMAGE_FILE, byteArrayBody);
//        multipartEntity.addPart(ZUtil.KEY_RELATED_TO, new StringBody(imageModel.getRelatedToImage()));
//        multipartEntity.addPart(ZUtil.KEY_RELATED_ID, new StringBody(imageModel.getRelatedId()));
//        multipartEntity.addPart(ZUtil.KEY_CATEGORY, new StringBody(imageModel.getCategory()));
//        multipartEntity.addPart(ZUtil.KEY_EXTENSION, new StringBody(imageModel.getExtension()));
//        multipartEntity.addPart(ZUtil.KEY_ACCESS_TOKEN, new StringBody(authToken));
//
//        sendApiRequestForMultipart(context, requestCode, multipartEntity, onWebServiceResponse,
//                NetworkMananger.HTTP_POST_REQUEST_TYPE, URLManager.UPLOAD_IMAGE);
//    }

    public static void updateFriendRelationApi(Context context, OnWebServiceResponse onWebServiceResponse
            , int requestCode, String accessToken, String relation, String requestId) {

//        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair(ZUtil.KEY_ACCESS_TOKEN, accessToken));
//        params.add(new BasicNameValuePair(ZUtil.KEY_REQUEST_ID, requestId));
//        params.add(new BasicNameValuePair(ZUtil.KEY_RELATION, relation));
//        sendApiRequest(context, requestCode, params, onWebServiceResponse, NetworkMananger.HTTP_PUT_REQUEST_TYPE,
//                URLManager.UPDATE_FRIEND_RELATION);
    }

    public static void showFriendListApi(Context context, OnWebServiceResponse onWebServiceResponse,
                                         int requestCode, String accessToken) {
//        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair(ZUtil.KEY_ACCESS_TOKEN, accessToken));
//        sendApiRequest(context, requestCode, params, onWebServiceResponse, HTTP_PUT_REQUEST_TYPE,
//                URLManager.LIST_SENT_REQUEST);

    }



    public static void userProfileApi(Context context, OnWebServiceResponse
            webServiceResponse, String userId, int requestCode) {
//        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair(ZUtil.KEY_ACCESS_TOKEN, KPrefs.getString(context, KPrefs.KEY_TOKEN, "")));
//        params.add(new BasicNameValuePair(ZUtil.KEY_SERVER_RESPONSE_USER_ID, userId));
//        sendApiRequest(context, requestCode, params, webServiceResponse,
//                NetworkMananger.HTTP_PUT_REQUEST_TYPE, URLManager.USER_PROFILE);
    }

    public static void fetchSuggestedFreindApi(Context context,
                                               OnWebServiceResponse onWebServiceResponse,
                                               int requestCode, String accessToken) {
//        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair(ZUtil.KEY_ACCESS_TOKEN, accessToken));
////        params.add(new BasicNameValuePair(ZUtil.KEY_LIMIT, Integer.toString(limit)));
//        sendApiRequest(context, requestCode, params, onWebServiceResponse, HTTP_POST_REQUEST_TYPE,
//                URLManager.SUGGEST_FRIEND_LIST);
    }

    public static void serchFriendFromKidlrApi(Context context, String accessToken, String searchString
            , OnWebServiceResponse onWebServiceResponse, int requestCode) {
//        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair(ZUtil.KEY_ACCESS_TOKEN, accessToken));
//        params.add(new BasicNameValuePair(ZUtil.KEY_SEARCH_FRIEND, searchString));
//        sendApiRequest(context, requestCode, params, onWebServiceResponse, HTTP_POST_REQUEST_TYPE, URLManager.SEARCH_FRIENDS_FROM_KIDLR);
    }

    public static void updateUserProfileAPI(Context context, OnWebServiceResponse onWebServiceResponse,
                                            int requestCode,String accessToken,String firstName,String middleName,String lastName,
                                            String type,String gender,String status) {
        ArrayList<NameValuePair>params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(ZUtil.KEY_ACCESS_TOKEN,accessToken));
        params.add(new BasicNameValuePair(ZUtil.KEY_FIRST_NAME,firstName));
        params.add(new BasicNameValuePair(ZUtil.KEY_MIDDLE_NAME,middleName));
        params.add(new BasicNameValuePair(ZUtil.KEY_LAST_NAME,lastName));
        params.add(new BasicNameValuePair(ZUtil.KEY_TYPE,Integer.toString(1)));
        params.add(new BasicNameValuePair(ZUtil.KEY_STATUS,Integer.toString(1)));
        params.add(new BasicNameValuePair(ZUtil.KEY_GENDER,gender));
        params.add(new BasicNameValuePair(ZUtil.KEY_FIRST_NAME,firstName));
        sendApiRequest(context,requestCode,params,onWebServiceResponse,HTTP_PUT_REQUEST_TYPE,URLManager.UPDATE_USER_PROFILE);

    }

    public static void addFriendRequestApi(Context context, String accessToken, String friendId, String relation
            , OnWebServiceResponse onWebServiceResponse, int requestCode) {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(ZUtil.KEY_ACCESS_TOKEN, accessToken));
        params.add(new BasicNameValuePair(ZUtil.KEY_FRIEND_ID, friendId));
        params.add(new BasicNameValuePair(ZUtil.KEY_RELATION, relation));
        sendApiRequest(context, requestCode, params, onWebServiceResponse, HTTP_POST_REQUEST_TYPE, URLManager.ADD_FRIEND_REQUEST);
    }

    public static void deleteBabyRequestApi(Context context, String accessToken, String childId
            , OnWebServiceResponse onWebServiceResponse, int requestCode) {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(ZUtil.KEY_ACCESS_TOKEN, accessToken));
        params.add(new BasicNameValuePair(ZUtil.KEY_SERVER_RESPONSE_CHILD_ID, childId));
        sendApiRequest(context, requestCode, params, onWebServiceResponse, HTTP_DELETE_REQUEST_TYPE, URLManager.DELETE_BABY);
    }

    public static void friendRequestListApi(Context context, String accessToken
            , OnWebServiceResponse onWebServiceResponse, int requestCode) {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(ZUtil.KEY_ACCESS_TOKEN, accessToken));
        sendApiRequest(context, requestCode, params, onWebServiceResponse, HTTP_POST_REQUEST_TYPE, URLManager.FRIEND_REQUEST_LIST);
    }

    public static void acceptFriendRequestApi(Context context, String accessToken, String requestId
            , OnWebServiceResponse onWebServiceResponse, int requestCode) {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(ZUtil.KEY_ACCESS_TOKEN, accessToken));
        params.add(new BasicNameValuePair(ZUtil.KEY_REQUEST_ID, requestId));
//        params.add(new BasicNameValuePair(ZUtil.KEY_STATUS, ZUtil.KEY_ACCEPT_FRIEND_REQUEST));
        sendApiRequest(context, requestCode, params, onWebServiceResponse, HTTP_PUT_REQUEST_TYPE, URLManager.UPDATE_FRIEND_STATUS);
    }

    public static void deleteFriendRequestApi(Context context, String accessToken, String requestId
            , OnWebServiceResponse onWebServiceResponse, int requestCode) {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(ZUtil.KEY_ACCESS_TOKEN, accessToken));
        params.add(new BasicNameValuePair(ZUtil.KEY_REQUEST_ID, requestId));
        sendApiRequest(context, requestCode, params, onWebServiceResponse, HTTP_DELETE_REQUEST_TYPE, URLManager.DELETE_FRIEND_REQUEST);
    }

    public static void deleteFriendListApi(Context context, String accessToken
            , OnWebServiceResponse onWebServiceResponse, int requestCode) {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(ZUtil.KEY_ACCESS_TOKEN, accessToken));
        sendApiRequest(context, requestCode, params, onWebServiceResponse, HTTP_POST_REQUEST_TYPE, URLManager.DELETE_FRIEND_LIST);
    }



    public static void createAlbumRequestApi(Context context, String accessToken, String name, String description, String childrenIds,
                                             String sharedWith, String visibility, String isPost, String isMilestone,
                                             OnWebServiceResponse onWebServiceResponse, int requestCode) {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(ZUtil.KEY_ACCESS_TOKEN, accessToken));
        params.add(new BasicNameValuePair(ZUtil.KEY_SERVER_RESPONSE_NAME, name));
        params.add(new BasicNameValuePair(ZUtil.KEY_DESCRIPTION, description));
        params.add(new BasicNameValuePair(ZUtil.KEY_CHILDREN_IDS, childrenIds));
        params.add(new BasicNameValuePair(ZUtil.KEY_SHARED_WITH, sharedWith));
        params.add(new BasicNameValuePair(ZUtil.KEY_VISIBILITY, visibility));
        params.add(new BasicNameValuePair(ZUtil.KEY_SERVER_RESPONSE_IS_POST, isPost));
        params.add(new BasicNameValuePair(ZUtil.KEY_SERVER_RESPONSE_IS_MILESTONE, isMilestone));
        sendApiRequest(context, requestCode, params, onWebServiceResponse, HTTP_POST_REQUEST_TYPE, URLManager.CREATE_ALBUM);
    }

    public static void updateAlbumRequestApi(Context context, String accessToken, String name, String description, String childrenIds, String sharedWith, String visibility, OnWebServiceResponse onWebServiceResponse, int requestCode, String albumId) {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(ZUtil.KEY_ACCESS_TOKEN, accessToken));
        params.add(new BasicNameValuePair(ZUtil.KEY_SERVER_RESPONSE_ALBUM_ID, albumId));
        params.add(new BasicNameValuePair(ZUtil.KEY_SERVER_RESPONSE_NAME, name));
        params.add(new BasicNameValuePair(ZUtil.KEY_DESCRIPTION, description));
        params.add(new BasicNameValuePair(ZUtil.KEY_CHILDREN_IDS, childrenIds));
        params.add(new BasicNameValuePair(ZUtil.KEY_SHARED_WITH, sharedWith));
        params.add(new BasicNameValuePair(ZUtil.KEY_VISIBILITY, visibility));
        sendApiRequest(context, requestCode, params, onWebServiceResponse, HTTP_PUT_REQUEST_TYPE, URLManager.UPDATE_ALBUM);
    }

    public static void fetchAlbumMediaListRequestApi(Context context, String accessToken, int requestCode, String albumId, OnWebServiceResponse onWebServiceResponse) {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(ZUtil.KEY_ACCESS_TOKEN, accessToken));
        params.add(new BasicNameValuePair(ZUtil.KEY_SERVER_RESPONSE_ALBUM_ID, albumId));
        sendApiRequest(context, requestCode, params, onWebServiceResponse, HTTP_PUT_REQUEST_TYPE, URLManager.ALBUM_MEDIA_LIST);
    }

    public static void listAlbumApi(Context context, String accessToken, String userId, OnWebServiceResponse
            webServiceResponse, int requestCode) {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(ZUtil.KEY_ACCESS_TOKEN, accessToken));
        params.add(new BasicNameValuePair(ZUtil.KEY_SERVER_RESPONSE_USER_ID, userId));
        sendApiRequest(context, requestCode, params, webServiceResponse,
                NetworkMananger.HTTP_PUT_REQUEST_TYPE, URLManager.LIST_ALBUM);
    }

    public static void listChildAlbumApi(Context context, String accessToken, String userId, String childId, OnWebServiceResponse
            webServiceResponse, int requestCode) {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(ZUtil.KEY_ACCESS_TOKEN, accessToken));
        params.add(new BasicNameValuePair(ZUtil.KEY_SERVER_RESPONSE_USER_ID, userId));
        params.add(new BasicNameValuePair(ZUtil.KEY_SERVER_RESPONSE_CHILD_ID, childId));
        sendApiRequest(context, requestCode, params, webServiceResponse,
                NetworkMananger.HTTP_PUT_REQUEST_TYPE, URLManager.LIST_ALBUM);
    }

    public static void listVideoApi(Context context, String accessToken, String userId, OnWebServiceResponse
            webServiceResponse, int requestCode) {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(ZUtil.KEY_ACCESS_TOKEN, accessToken));
        params.add(new BasicNameValuePair(ZUtil.KEY_SERVER_RESPONSE_USER_ID, userId));
        sendApiRequest(context, requestCode, params, webServiceResponse,
                NetworkMananger.HTTP_PUT_REQUEST_TYPE, URLManager.LIST_VIDEO);
    }

    public static void listChildVideoApi(Context context, String accessToken, String userId, String childId, OnWebServiceResponse
            webServiceResponse, int requestCode) {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(ZUtil.KEY_ACCESS_TOKEN, accessToken));
        params.add(new BasicNameValuePair(ZUtil.KEY_SERVER_RESPONSE_USER_ID, userId));
        params.add(new BasicNameValuePair(ZUtil.KEY_SERVER_RESPONSE_CHILD_ID, childId));
        sendApiRequest(context, requestCode, params, webServiceResponse,
                NetworkMananger.HTTP_PUT_REQUEST_TYPE, URLManager.LIST_VIDEO);
    }

    public static void refreshAlbumApi(Context context,String accessToken,String albumId, OnWebServiceResponse
            webServiceResponse, int requestCode) {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(ZUtil.KEY_ACCESS_TOKEN, ZPrefs.getString(context, ZPrefs.KEY_TOKEN, "")));
        params.add(new BasicNameValuePair(ZUtil.KEY_SERVER_RESPONSE_ALBUM_ID, albumId));
        sendApiRequest(context, requestCode, params, webServiceResponse,
                NetworkMananger.HTTP_PUT_REQUEST_TYPE, URLManager.SHOW_ALBUM_REFRESH);

    }

    public static void deleteAlbumApi(Context context, String accessToken, String albumId
            , OnWebServiceResponse onWebServiceResponse, int requestCode) {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(ZUtil.KEY_ACCESS_TOKEN, accessToken));
        params.add(new BasicNameValuePair(ZUtil.KEY_SERVER_RESPONSE_ALBUM_ID, albumId));
        sendApiRequest(context, requestCode, params, onWebServiceResponse, HTTP_DELETE_REQUEST_TYPE, URLManager.DELETE_ALBUM);
    }

    public static void deleteVideoApi(Context context, String accessToken, String videoId
            , OnWebServiceResponse onWebServiceResponse, int requestCode) {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(ZUtil.KEY_ACCESS_TOKEN, accessToken));
        params.add(new BasicNameValuePair(ZUtil.KEY_SERVER_RESPONSE_VIDEO_ID, videoId));
        sendApiRequest(context, requestCode, params, onWebServiceResponse, HTTP_DELETE_REQUEST_TYPE, URLManager.DELETE_VIDEO);
    }

    public static void deleteAlbumMediaApi(Context context, String accessToken, String albumId, String albumMediaIds
            , OnWebServiceResponse onWebServiceResponse, int requestCode) {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(ZUtil.KEY_ACCESS_TOKEN, accessToken));
        params.add(new BasicNameValuePair(ZUtil.KEY_SERVER_RESPONSE_ALBUM_ID, albumId));
        params.add(new BasicNameValuePair(ZUtil.KEY_SERVER_RESPONSE_ALBUM_MEDIA_IDS, albumMediaIds));
        sendApiRequest(context, requestCode, params, onWebServiceResponse, HTTP_DELETE_REQUEST_TYPE, URLManager.REMOVE_MEDIA);
    }


    public static void resetPasswordApi(Context context, String currentPassword, String newPassword, OnWebServiceResponse
            webServiceResponse, int requestCode) {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(ZUtil.KEY_ACCESS_TOKEN, ZPrefs.getString(context, ZPrefs.KEY_TOKEN, "")));
        params.add(new BasicNameValuePair(ZUtil.KEY_CURRENT_PASSWORD, ZUtil.convertStringToSHA256(currentPassword)));
        params.add(new BasicNameValuePair(ZUtil.KEY_NEW_PASSWORD, ZUtil.convertStringToSHA256(newPassword)));
        sendApiRequest(context, requestCode, params, webServiceResponse,
                NetworkMananger.HTTP_PUT_REQUEST_TYPE, URLManager.RESET_PASSWORD);

    }


    public static void forgotPasswordApi(Context context, String email, OnWebServiceResponse
            webServiceResponse, int requestCode) {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(ZUtil.KEY_EMAIL, email));
        sendApiRequest(context, requestCode, params, webServiceResponse,
                NetworkMananger.HTTP_POST_REQUEST_TYPE, URLManager.FORGET_PASSWORD);

    }


    public static void userNotificationApi(Context context, OnWebServiceResponse
            webServiceResponse, int requestCode) {
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(ZUtil.KEY_ACCESS_TOKEN, ZPrefs.getString(context, ZPrefs.KEY_TOKEN, "")));
        sendApiRequest(context, requestCode, params, webServiceResponse,
                NetworkMananger.HTTP_PUT_REQUEST_TYPE, URLManager.LIST_NOTIFICATION);
    }





}
