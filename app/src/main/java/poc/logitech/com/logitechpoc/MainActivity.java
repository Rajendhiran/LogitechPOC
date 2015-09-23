package poc.logitech.com.logitechpoc;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import poc.logitech.com.logitechpoc.adapter.DeviceAdapter;
import poc.logitech.com.logitechpoc.bean.DevicesBean;
import poc.logitech.com.logitechpoc.generic.IConstant;

public class MainActivity extends AppCompatActivity
{
    private ProgressDialog pDialog;
    LogitechPOCApp logApplication;
    ListView devicelstView;
    DeviceAdapter deviceAdapter;

    DevicesBean data;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        process();
    }

    private void process()
    {
        showpDialog();

        // Either use the handler or runOnUiThread by uncommenting the code;

        /* Handler
        handler.sendEmptyMessage(IConstant.PARSE_DEVICE);
        */

        // runOnUiThread

        runOnUiThread(new Runnable() {
            public void run() {
                PopulateTheView();
            }
        });
    }

    private void init()
    {
        // Application Instance
        logApplication = new LogitechPOCApp(MainActivity.this);
        devicelstView = (ListView) findViewById(R.id.lstView);

        //Progress Dialog
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);

    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            final int what = msg.what;
            switch(what)
            {
                case IConstant.PARSE_DEVICE:
                {
                    PopulateTheView();
                }
                break;
            }
        }
    };

    public void PopulateTheView()
    {
        String jsonValues = logApplication.loadJSONFromAsset(IConstant.JSON_FILE_NAME);
        Log.d("JSONValues: ","data: "+jsonValues);
        data = (DevicesBean) logApplication.getFromJSON(jsonValues,DevicesBean.class);
        Log.d("DeviceSize: ","data:"+data.getDevices().size());

        for(DevicesBean.Devices d: data.getDevices())
        {
            Log.d("----","# --------------");
            Log.d("Name :","# "+d.getName());
            Log.d("Type :","# "+d.getDeviceType());
            Log.d("Model:","# "+d.getModel());
            Log.d("----","# --------------");
        }
        deviceAdapter = new DeviceAdapter(MainActivity.this,data);
        devicelstView.setAdapter(deviceAdapter);
        hidepDialog();
    }

}
