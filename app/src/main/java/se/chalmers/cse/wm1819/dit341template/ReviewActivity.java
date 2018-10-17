package se.chalmers.cse.wm1819.dit341template;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import se.chalmers.cse.wm1819.dit341template.Adapters.BaseAdpterList;
import se.chalmers.cse.wm1819.dit341template.model.Movie;
import se.chalmers.cse.wm1819.dit341template.model.Review;

public class ReviewActivity extends AppCompatActivity {

    FloatingActionButton fab;
    private Context mcontext;
    private String Movie_id;
    private static final String HTTP_PARAM = "httpResponse";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        getReview();
        mcontext = this;
        Intent intent = getIntent();
        Movie_id = intent.getStringExtra(MainActivity.HTTP_PARAM);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, CreateReviewActivity.class);
                intent.putExtra(HTTP_PARAM,Movie_id);
                startActivity(intent);
            }
        });
    }

    public void getReview(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                getString(R.string.server_url) + "/api/reviews/" + getIntent().getStringExtra(BaseAdpterList.reviewId),
                null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("TAG", response.toString());
                String data = null;
                try {
                    data = response.getString("data");
                } catch (JSONException e) {
                    Log.e(this.getClass().toString(), e.getMessage());
                }
                final Review review = new Gson().fromJson(data, Review.class);

                TextView tvReviewRating = findViewById(R.id.reviewRating);
                tvReviewRating.setText(review.getRating() + "");
                TextView tvReviewText = findViewById(R.id.reviewText);
                tvReviewText.setText(review.getReviewText());
                TextView tvReviewUser = findViewById(R.id.reviewUser);
                tvReviewUser.setText(review.getUser());

                Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR.RESPONSE", error.toString());
                Toast.makeText(getApplicationContext(),"error while trying to get information from database!",Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        VolleySingleton.getInstance(getApplicationContext()).getRequestQueue().add(jsonObjectRequest);
    }

}
