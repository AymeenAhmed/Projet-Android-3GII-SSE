package com.example.user;




import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class MainActivity3 extends AppCompatActivity {
    TextView text ;



    private BluetoothAdapter myBluetooth = null;
    private Set<BluetoothDevice> pairedDevices;
    public String paired_device_adress=null;
    public BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private ProgressDialog progress;
    Button button;
    int i=0;
    int j=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        button = (Button) findViewById(R.id.btnstat);
        text = (TextView) findViewById(R.id.textView) ;
        Bundle extras= getIntent().getExtras();
        String n= extras.getString("cle");
        String n2= extras.getString("cle2");
        text.setText("Hello  "+n);


        myBluetooth = BluetoothAdapter.getDefaultAdapter();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                text.setText("aaaaa");

                //enabling bluetooth
                /*if ( myBluetooth==null ) {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();
                }
                else if ( !myBluetooth.isEnabled() ) {
                    Toast.makeText(MainActivity3.this, "Bluetooth disabled", Toast.LENGTH_LONG).show();
                    Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(turnBTon, 1);
                }
                else if (i == 0) {
                    Toast.makeText(MainActivity3.this, "Bluetooth is available on your device", Toast.LENGTH_LONG).show();
                    i++;
                }
*/
                //getting paired remote device address
                pairedDevices = myBluetooth.getBondedDevices();
                if ( pairedDevices.size() > 0 ) {
                    for ( BluetoothDevice bt : pairedDevices ) {
                        if((bt.getName().toString()).equals("HC-05")){
                            paired_device_adress=bt.getAddress();
                        }
                    }
                    if(paired_device_adress.isEmpty()){
                        Toast.makeText(MainActivity3.this, "You need to pair with HC-05", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if(j==0) {
                            Toast.makeText(MainActivity3.this, "paired with HC-05 [adress: " + paired_device_adress + " ]", Toast.LENGTH_SHORT).show();
                            j++;
                        }
                        new MainActivity3.ConnectBT().execute();
                    }
                }

            }});
    }

    //connection and sending class
    private class ConnectBT extends AsyncTask<Void, Void, Void> {
        private boolean ConnectSuccess = true;

        @Override
        protected  void onPreExecute () {
            progress = ProgressDialog.show(MainActivity3.this, "Connecting...", "Please Wait!!!");
        }

        @Override
        protected Void doInBackground (Void... devices) {
            try {
                if ( btSocket==null || !isBtConnected ) {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(paired_device_adress);
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();
                }
            } catch (IOException e) {
                ConnectSuccess = false;
            }

            return null;
        }

        @Override
        protected void onPostExecute (Void result) {
            Bundle extras= getIntent().getExtras();
            final   String n= extras.getString("cle");
            final String n2= extras.getString("cle2");
            super.onPostExecute(result);

            if (!ConnectSuccess) {
                //display not connected msg
                finish();
            } else {
                //display connected msg
                isBtConnected = true;
            }

            progress.dismiss();
            //sending data
            if ( btSocket != null ) {
                try {
                    btSocket.getOutputStream().write((n+"/"+n2+"*").getBytes());
                } catch (IOException e) {
                    //display error msg
                }
            }
        }
    }
}

