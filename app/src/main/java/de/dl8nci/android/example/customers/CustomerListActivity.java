package de.dl8nci.android.example.customers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class CustomerListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);

        // Get the Intent that started this activity and extract the string
//        Intent intent = getIntent();
//        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        String s = "";

        String targetURL = "http://services.odata.org/V2/Northwind/Northwind.svc/Customers?$top=3";

        try {
            URL url = new URL(targetURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("accept", "application/atom+xml,application/atomsvc+xml,application/xml");

            int status = con.getResponseCode();

            if (status == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuffer content = new StringBuffer();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                s = content.toString();

            } else {
                s = "status = " + status + "\r\n";
            }
            con.disconnect();


        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            s = "MalformedURLException";
//            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            s = "IOException";
//            e.printStackTrace();
        }



        textView.setText(s);


    }
}
