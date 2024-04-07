package com.trodev.mypasswordgenerator.activity.payment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.trodev.mypasswordgenerator.R;
import com.trodev.mypasswordgenerator.activity.NotificationActivity;

public class PaymentOnlineActivity extends AppCompatActivity {

    private Switch mySwitch;
    private SharedPreferences sharedPreferences;
    private static final String SWITCH_STATE = "switch_state";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_online);

        mySwitch = findViewById(R.id.mySwitch);
        sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE);

        // Load the saved switch state (if any)
        boolean switchState = sharedPreferences.getBoolean(SWITCH_STATE, false);
        mySwitch.setChecked(switchState);

        // Send switch state to Activity2
        Intent intent = new Intent(this, NotificationActivity.class);
        SwitchState state = new SwitchState(switchState);
        intent.putExtra("switch_state", state);
        startActivity(intent);
    }

    // Parcelable class to hold switch state
    public static class SwitchState implements Parcelable {
        public final boolean isChecked;

        public SwitchState(boolean isChecked) {
            this.isChecked = isChecked;
        }

        protected SwitchState(Parcel in) {
            isChecked = in.readByte() != 0;
        }

        public static final Parcelable.Creator<SwitchState> CREATOR = new Creator<SwitchState>() {
            @Override
            public SwitchState createFromParcel(Parcel in) {
                return new SwitchState(in);
            }

            @Override
            public SwitchState[] newArray(int size) {
                return new SwitchState[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeByte((byte) (isChecked ? 1 : 0));
        }
    }
}