package putitout.zipjetclone.ui.webservices;

import android.content.Context;

import org.apache.http.NameValuePair;

import java.util.ArrayList;

import putitout.zipjetclone.ui.interfaces.OnWebServiceResponse;
import putitout.zipjetclone.ui.network.NetworkMananger;
import putitout.zipjetclone.ui.util.ZUtil;

;

public class GetDeviceContactsService extends BaseAsyncTask<String,Void, String>{
    private OnWebServiceResponse listnerOnWebServiceResponse;
    private Context context;
    private int requestCode;
    private ArrayList<NameValuePair> params;
    private int requestType;
    private String url;

    public void setOnWebServiceResponseListener (OnWebServiceResponse onwebServiceResponseListener) {
     this.listnerOnWebServiceResponse = onwebServiceResponseListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listnerOnWebServiceResponse.onStartWebService(requestCode);
    }

    @Override
    protected String doInBackground(String... strings) {
        String response = null;
        try {
            response = NetworkMananger.getApiResponse(url, params, requestType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        //KLog.info("ApiWebService response : " + result);
        boolean isJsonResponse = ZUtil.isJsonResponse(result);
        listnerOnWebServiceResponse.onCompletedWebService(result, requestCode, isJsonResponse);
    }

    public GetDeviceContactsService(Context context, int requestCode, ArrayList<NameValuePair> param, int requestType, String url) {
        this.context = context;
        this.requestCode = requestCode;
        this.params = param;
        this.requestType = requestType;
        this.url = url;
    }
}
