package se.chalmers.cse.wm1819.dit341template;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import se.chalmers.cse.wm1819.dit341template.model.Review;

public class CreateReviewActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private EditText UserName;
    private EditText Review;
    private Button Submit;
    private String Movie_id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createreview);
        Intent intent = getIntent();
        Movie_id = intent.getStringExtra(MainActivity.HTTP_PARAM);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        UserName = (EditText) findViewById(R.id.UserName);
        Review = (EditText) findViewById(R.id.reivew);
        Submit = (Button) findViewById(R.id.SubmitReview);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateReview();
            }
        });
    }
    String _rivew;String _userName;String  _ratingbar;
    private void CreateReview(){
         _rivew = Review.getText().toString();
         _userName = UserName.getText().toString();
          _ratingbar =  String.valueOf(ratingBar.getRating());
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = getString(R.string.server_url) + "/api/movies/" + Movie_id + "/review";

        JSONObject postparams = new JSONObject();
        try {
            postparams.put("Rating", _ratingbar);
            postparams.put("ReviewText", _rivew);
            postparams.put("Username", _userName);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, postparams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        Toast toast = Toast.makeText(getApplicationContext(),"your review has been added",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast toast = Toast.makeText(getApplicationContext(),"error while trying to get information from database!",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() {
                        final Map<String, String> headers = new HashMap<>();
                        headers.put("Content-Type", "application/json");
                        return headers;
                    }
                };

        queue.add(jsonObjReq);
    }
}
