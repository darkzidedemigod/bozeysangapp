package darkzidestudios.boseysangspeed;

import android.graphics.Typeface;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Formatter;
import java.util.Locale;

public class MainSpeedActivity extends AppCompatActivity implements IBaseGpsListener{
    TextView txtKm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_speed);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        txtKm = findViewById(R.id.txtKm);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/DS-DIGIB.TTF");

        txtKm.setTypeface(custom_font);
    }

    public void finish()
    {
        super.finish();
        System.exit(0);
    }

    private void updateSpeed(CLocation location) {
        // TODO Auto-generated method stub
        float nCurrentSpeed = 0;

        if(location != null)
        {
            location.setUseMetricunits(this.useMetricUnits());
            nCurrentSpeed = location.getSpeed();
        }

        Formatter fmt = new Formatter(new StringBuilder());
        fmt.format(Locale.US, "%5.1f", nCurrentSpeed);
        String strCurrentSpeed = fmt.toString();
        strCurrentSpeed = strCurrentSpeed.replace(' ', '0');

        String strUnits = "mph";
        if(this.useMetricUnits())
        {
            strUnits = "m/s";
        }

        //txtKm.setText(strCurrentSpeed + " km/s");
        //txtMiles.setText(strCurrentSpeed + " mph");

        //TextView txtCurrentSpeed = (TextView) this.findViewById(R.id.txtCurrentSpeed);
        txtKm.setText(strCurrentSpeed + " " + strUnits);
    }


    private boolean useMetricUnits() {
        // TODO Auto-generated method stub
        CheckBox chkUseMetricUnits = this.findViewById(R.id.chkMetricUnits);
        return chkUseMetricUnits.isChecked();
    }


    @Override
    public void onLocationChanged(Location location) {
        // TODO Auto-generated method stub
        if(location != null)
        {
            CLocation myLocation = new CLocation(location, this.useMetricUnits());
            this.updateSpeed(myLocation);
        }
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onGpsStatusChanged(int event) {
        // TODO Auto-generated method stub

    }
}
