package com.example.friendsapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewFriends extends AppCompatActivity {

    TextView t1;
    String apiUrl="https://friendsapi-re5a.onrender.com/view";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_friends);
        t1=(TextView) findViewById(R.id.textviewid);

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(
                Request.Method.GET,
                apiUrl,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        StringBuilder result=new StringBuilder();
                        for(int i=0;i<response.length();i++)
                        {
                            try {
                                JSONObject frnd=response.getJSONObject(i);
                                String name=frnd.getString("name");
                                String frndname=frnd.getString("friendName");
                                String friendnickname=frnd.getString("friendNickName");
                                String describeyourfrnd=frnd.getString("DescribeYourFriend");

                                result.append(name).append(" ").append(frndname).append(" ").append(friendnickname).append(" ").append(describeyourfrnd).append("\n\n\n");

                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }

                        }
                        t1.setText(result.toString());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
        );

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);



    }
}