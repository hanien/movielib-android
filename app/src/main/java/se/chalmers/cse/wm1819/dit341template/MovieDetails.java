package se.chalmers.cse.wm1819.dit341template;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
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

import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import se.chalmers.cse.wm1819.dit341template.Adapters.BaseAdpterList;
import se.chalmers.cse.wm1819.dit341template.model.Movie;

public class MovieDetails extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        getSpecificMovie();

    }

    public void getSpecificMovie(){
        Intent intent = getIntent();
        String movieId = intent.getStringExtra(BaseAdpterList.movieId);

        String url = getString(R.string.server_url) + "/api/movies/" + movieId;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("TAG", response.toString());

                Gson gson = new Gson();
                String data = null;

                try {
                    data = response.getString("data");
                } catch (JSONException e) {
                    Log.e(this.getClass().toString(), e.getMessage());
                }

                final Movie currentMovie = gson.fromJson(data, Movie.class);

                TextView tvTitle = (TextView)findViewById(R.id.movieDetailsMovieTitle);
                tvTitle.setText(currentMovie.getMovieTitle());

                TextView tvRelease = (TextView)findViewById(R.id.movieDetailsReleaseYear);
                tvRelease.setText(currentMovie.getReleaseYear() + "");

                TextView tvPlot = (TextView)findViewById(R.id.movieDetailsPlotDescription);
                tvPlot.setText(currentMovie.getPlotDescription());

                TextView tvDirector = (TextView)findViewById(R.id.movieDetailsDirector);
                tvDirector.setText(currentMovie.getDirector());

                TextView tvActors = (TextView)findViewById(R.id.movieDetailsMainActors);
                String actors = "";
                for ( int i = 0; i < currentMovie.getMainActors().length ; i++){
                    actors += currentMovie.getMainActors()[i] + ", ";
                }
                tvActors.setText(actors);

                new DownloadImageTask((ImageView)findViewById(R.id.movieDetailsPoster)).execute(currentMovie.getMainPoster());

                Button trailerButton = (Button)findViewById(R.id.movieDetailsTrailerButton);

                trailerButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        getTrailer(currentMovie);
                    }
                });

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
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        //The request queue makes sure that HTTP requests are processed in the right order.
        RequestQueue rq = VolleySingleton.getInstance(getApplicationContext()).getRequestQueue();
        rq.add(jsonObjectRequest);
    }

    public void getTrailer(Movie movie){
        Uri uri = Uri.parse(movie.getTrailer());
        uri = Uri.parse("vnd.youtube:"  + uri.getQueryParameter("v"));

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        getApplicationContext().startActivity(intent);
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
