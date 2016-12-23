package enterprise.sample.com.battery_status_and_level;

import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;

public class MainActivity extends ActionBarActivity {

    Button btnReadBattery;
    TextView textBatteryStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReadBattery = (Button)findViewById(R.id.readbattery);
        textBatteryStatus = (TextView)findViewById(R.id.batterystatus);

        btnReadBattery.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                textBatteryStatus.setText(readBattery());
            }});

    }

    private String readBattery(){
        StringBuilder sb = new StringBuilder();
        IntentFilter batteryIntentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryIntent = registerReceiver(null, batteryIntentFilter);

        int status = batteryIntent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        if(status == BatteryManager.BATTERY_STATUS_CHARGING){
            sb.append("BATTERY_STATUS_CHARGING\n");
        }
        if(status == BatteryManager.BATTERY_STATUS_FULL){
            sb.append("BATTERY_STATUS_FULL\n");
        }

        int plugged = batteryIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        if(plugged == BatteryManager.BATTERY_PLUGGED_USB){
            sb.append("BATTERY_PLUGGED_USB\n");
        }
        if(plugged == BatteryManager.BATTERY_PLUGGED_AC){
            sb.append("BATTERY_PLUGGED_AC\n");
        }

        int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        sb.append("level: " + level + "\n");

        int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        sb.append("scale: " + scale + "\n");

        return sb.toString();
    }
}
