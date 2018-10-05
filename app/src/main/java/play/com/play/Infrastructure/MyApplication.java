package play.com.play.Infrastructure;

import android.app.Application;
import android.content.Context;


//import net.gotev.uploadservice.UploadService;


/**
 * Created by Home on 9/7/2017.
 */

public class MyApplication extends Application {
    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
    public static MyApplication getInstance() {
        return mInstance;
    }

    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }
}
