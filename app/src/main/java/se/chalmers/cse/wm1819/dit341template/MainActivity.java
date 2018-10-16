package se.chalmers.cse.wm1819.dit341template;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.cse.wm1819.dit341template.Adapters.BaseAdpterList;
import se.chalmers.cse.wm1819.dit341template.model.Movie;


public class MainActivity extends AppCompatActivity {

    //Field for parameter name
    public static final String HTTP_PARAM = "httpResponse";
    private  ListView lv;
    private Context mcontext;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=(ListView)findViewById(R.id.listview);
        mcontext = this;
        GetMovies(this);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "you clicked on add float button", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void GetMovies(Activity activity) {
        String url = getString(R.string.server_url) + "/api/Movies";

        //This uses Volley (Threading and a request queue is automatically handled in the background)
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //GSON allows to parse a JSON string/JSONObject directly into a user-defined class
                        Gson gson = new Gson();

                        String dataArray = null;
                        BaseAdpterList baseAdpterList;
                        try {
                            dataArray = response.getString("Movies");
                        } catch (JSONException e) {
                            Log.e(this.getClass().toString(), e.getMessage());
                        }


                        Movie[] Movies = gson.fromJson(dataArray, Movie[].class);

                        baseAdpterList=new BaseAdpterList(Movies,mcontext);
                        lv.setAdapter(baseAdpterList);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast toast = Toast.makeText(getApplicationContext(),"error while trying to get information from database!",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });

        //The request queue makes sure that HTTP requests are processed in the right order.
        queue.add(jsonObjectRequest);
    }
}
