package com.example.loginregisterphpvolley;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class insert_data extends AppCompatActivity {
    EditText txtkodemobil, txtmerkmobil, txtnamapenyewa, txtsewaperhari, txtlamasewa;
    Button btnInsert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        txtkodemobil = findViewById(R.id.edt_kodemobil);
        txtmerkmobil = findViewById(R.id.edt_merkmobil);
        txtnamapenyewa = findViewById(R.id.edt_namapenyewa);
        txtsewaperhari = findViewById(R.id.edt_sewaperhari);
        txtlamasewa = findViewById(R.id.edt_lamasewa);

        btnInsert = findViewById(R.id.btnInsert);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData ();
            }
        });
    }

    private void insertData() {
        final String kodemobil = txtkodemobil.getText().toString().trim();
        final String merkmobil = txtmerkmobil.getText().toString().trim();
        final String namapenyewa = txtnamapenyewa.getText().toString().trim();
        final String sewaperhari = txtsewaperhari.getText().toString().trim();
        final String lamasewa = txtlamasewa.getText().toString().trim();

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");

        if (kodemobil.isEmpty()) {
            Toast.makeText(this, "Masukan Kode Mobil", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(merkmobil.isEmpty()) {
            Toast.makeText(this, "Masukan Merk Mobil", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(namapenyewa.isEmpty()) {
            Toast.makeText(this, "Masukan Nama Penyewa", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (sewaperhari.isEmpty()) {
            Toast.makeText(this, "Masukan Sewa Perhari", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (lamasewa.isEmpty()) {
            Toast.makeText(this, "Masukan Lama Sewa", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, MyLink.MY_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response.equalsIgnoreCase("Data Inserted")) {
                                Toast.makeText(insert_data.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(insert_data.this, response, Toast.LENGTH_SHORT).show();
                            }
                            progressDialog.dismiss();

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(insert_data.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
            ){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String,String>();

                    params.put("kodemobil",kodemobil);
                    params.put("merkmobil", merkmobil);
                    params.put("namapenyewa", namapenyewa);
                    params.put("sewaperhari", sewaperhari);
                    params.put("lamasewa", lamasewa);

                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(insert_data.this);
            requestQueue.add(request);
        }
    }


}