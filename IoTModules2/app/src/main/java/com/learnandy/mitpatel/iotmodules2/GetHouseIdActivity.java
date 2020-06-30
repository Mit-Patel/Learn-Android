package com.learnandy.mitpatel.iotmodules2;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;

public class GetHouseIdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_house_id);

        //setting up the layout of the scanner
        final Activity activity = this;
        IntentIntegrator intentIntegrator = new IntentIntegrator(activity);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        intentIntegrator.setPrompt("Scan QR Code from other user to set yous House Id");
        intentIntegrator.setCameraId(0);
        intentIntegrator.setBeepEnabled(false);
        intentIntegrator.setBarcodeImageEnabled(false);
        intentIntegrator.setCaptureActivity(CaptureActivity.class);
        intentIntegrator.initiateScan();
    }

    //this hsould be done after getting result from the scanned qr code
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        String title=null,message=null;
        if (result != null) {
            if (result.getContents() != null) {
                String[] strings = updateHouseId(result.getContents());
                title = strings[0];
                message = strings[1];
            } else {
                Toast.makeText(this, "Scanning was cancelled", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

        //sending result to main activity
        Intent intent = new Intent();
        if (title == null && message == null) {
            setResult(RESULT_CANCELED);
        } else {
            intent.putExtra("message",message);
            intent.putExtra("title",title);
            setResult(RESULT_OK,intent);
        }
        finish();
    }

    //updating the house id of the current user
    private String[] updateHouseId(String contents) {
        DBHelper mydb = new DBHelper(this);
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        String[] strings = new String[2];
        int house_id = Integer.parseInt(contents);

        if (mydb.updateHouseId(sessionManager.getUserId() + "", house_id)) {
            strings[0] = "Done";
            strings[1] = "House Id has been set!";
        } else {
            strings[0] = "Error";
            strings[1] = "Error while setting House Id!";
        }

        return strings;
    }
}