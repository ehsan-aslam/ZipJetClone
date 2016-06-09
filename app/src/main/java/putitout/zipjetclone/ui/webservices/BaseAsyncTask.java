package putitout.zipjetclone.ui.webservices;

import android.os.AsyncTask;

import putitout.zipjetclone.R;
import putitout.zipjetclone.ui.util.ZDialog;
import putitout.zipjetclone.ui.util.ZLog;
import putitout.zipjetclone.ui.util.ZUtil;


/**
 * Created by Junaid Shabbir on 12/28/15.
 */
abstract class BaseAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {
    @Override
    protected void onPostExecute(Result result) {
        super.onPostExecute(result);
        try {
            if (!ZUtil.isJsonResponse(result.toString())) {
                ZLog.info("WebService Result " + result.toString());
                ZDialog.showAlert(R.string.serverError, R.string.ok);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
