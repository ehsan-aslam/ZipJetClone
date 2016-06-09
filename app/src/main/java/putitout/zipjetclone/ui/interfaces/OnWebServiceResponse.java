package putitout.zipjetclone.ui.interfaces;

public interface OnWebServiceResponse {
	void onStartWebService(int requestCode);
	void onCompletedWebService(String response, int requestCode, boolean isValidResponse);
}
