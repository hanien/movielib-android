package se.chalmers.cse.wm1819.dit341template;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import se.chalmers.cse.wm1819.dit341template.Adapters.BaseAdpterList;
import se.chalmers.cse.wm1819.dit341template.model.Movie;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final Button createMovie = findViewById(R.id.createMovieButton);

        createMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createMovie();
            }
        });

    }

    public void createMovie() {
        String url = getString(R.string.server_url) + "/api/admin/movies";

        EditText movieTitle = (EditText)findViewById(R.id.movieTitleInput);
        final String movieTitleText = movieTitle.getText().toString();
        EditText releaseYear = (EditText)findViewById(R.id.releaseYearInput);
        final String releaseYearText = releaseYear.getText().toString();
        EditText plotDescription = (EditText)findViewById(R.id.plotDescriptionInput);
        final String plotDescriptionText = plotDescription.getText().toString();
        EditText director = (EditText)findViewById(R.id.directorInput);
        final String directorText = director.getText().toString();
        EditText trailer = (EditText)findViewById(R.id.trailerInput);
        final String trailerText = trailer.getText().toString();
        EditText mainPoster = (EditText)findViewById(R.id.mainPosterInput);
        final String mainPosterText = mainPoster.getText().toString();
        EditText mainActors = (EditText)findViewById(R.id.mainActorsInput);
        final String mainActorsText = mainActors.getText().toString();

        //This uses Volley (Threading and a request queue is automatically handled in the background)
        //RequestQueue queue = Volley.newRequestQueue(this);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("MovieTitle", movieTitleText);
            jsonObject.put("ReleaseYear", releaseYearText);
            jsonObject.put("PlotDescription", plotDescriptionText);
            jsonObject.put("Director", directorText);
            jsonObject.put("Trailer", trailerText);
            jsonObject.put("MainPoster", mainPosterText);
            jsonObject.put("MainActors", mainActorsText);
        } catch (JSONException e){
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
                //VolleyLog.wtf(response.toString(), "utf-8");
                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(getApplicationContext(),"error while trying to get information from database!",Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        //The request queue makes sure that HTTP requests are processed in the right order.
        RequestQueue rq = VolleySingleton.getInstance(getApplicationContext()).getRequestQueue();
        rq.add(jsonObjectRequest);
    }
}