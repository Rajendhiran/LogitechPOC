package poc.logitech.com.logitechpoc;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import poc.logitech.com.logitechpoc.generic.IConstant;


/**
 * Created by rajendhiran on 9/23/2015.
 */
public class LogitechPOCApp extends Application
{
    public  Context ctx;

    LogitechPOCApp(Context context)
    {
        ctx = context;
    }

    public String loadJSONFromAsset(String fileName)
    {
        String json = null;
        try {
            InputStream is = ctx.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public  String getToJSON(Object src) {
        Gson gDataBean = new Gson();
        return gDataBean.toJson(src);
    }

    public  Object getFromJSON(String responseValue, Class<?> classname) {
        Gson gDataBean = new Gson();
        return gDataBean.fromJson(responseValue, classname);
    }
}
