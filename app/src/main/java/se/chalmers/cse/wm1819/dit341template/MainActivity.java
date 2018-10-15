package se.chalmers.cse.wm1819.dit341template;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import se.chalmers.cse.wm1819.dit341template.model.Camel;


public class MainActivity extends AppCompatActivity {

    //Field for parameter name
    public static final String HTTP_PARAM = "httpResponse";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickNewActivity (View view) {
        TextView mCamelView = findViewById(R.id.camelTextView);

        //Starts a new activity, providing the text from my HTTP text field as an input
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(HTTP_PARAM, mCamelView.getText().toString());
        startActivity(intent);
    }

    public void onClickGetCamels (View view) {
        //Get the text view in which we will show the result.
        final TextView mCamelView = findViewById(R.id.camelTextView);

        String url = getString(R.string.server_url) + "/api/camels";

        //This uses Volley (Threading and a request queue is automatically handled in the background)
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //GSON allows to parse a JSON string/JSONObject directly into a user-defined class
                        Gson gson = new Gson();

                        String dataArray = null;

                        try {
                            dataArray = response.getString("data");
                        } catch (JSONException e) {
                            Log.e(this.getClass().toString(), e.getMessage());
                        }

                        StringBuilder camelString = new StringBuilder();
                        camelString.append("This is the list of my camels: \n");

                        Camel[] camels = gson.fromJson(dataArray, Camel[].class);

                        for (Camel current : camels) {
                            camelString.append("Camel is " + current.color + " at "
                                    + current.position + "\n");
                        }

                        mCamelView.setText(camelString.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mCamelView.setText("Error! " + error.toString());
                    }
                });

        //The request queue makes sure that HTTP requests are processed in the right order.
        queue.add(jsonObjectRequest);
    }
}
