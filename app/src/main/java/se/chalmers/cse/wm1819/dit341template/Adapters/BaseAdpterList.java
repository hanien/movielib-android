package se.chalmers.cse.wm1819.dit341template.Adapters;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import se.chalmers.cse.wm1819.dit341template.R;
import se.chalmers.cse.wm1819.dit341template.ReviewActivity;
import se.chalmers.cse.wm1819.dit341template.SecondActivity;
import se.chalmers.cse.wm1819.dit341template.model.Movie;

public class BaseAdpterList extends BaseAdapter {

    Context mContext;
    List <Movie> Movies;
    private static final String HTTP_PARAM = "httpResponse";

    public BaseAdpterList(Movie[] movies,Context mContext)
    {
        this.mContext = mContext;
        this.Movies = new ArrayList<>();
        for(int i = 0 ; i < movies.length ; i++){
            this.Movies.add(movies[i]);
        }
    }

    @Override
    public int getCount() {
        return Movies.size();
    }

    @Override
    public Object getItem(int position) {
        return this.Movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);


        convertView = inflater.inflate(R.layout.row_list, null);


        TextView MovieTitle=(TextView)convertView.findViewById(R.id.MovieTitle);
        TextView ReleaseYear=(TextView)convertView.findViewById(R.id.ReleaseYear);

        MovieTitle.setText(Movies.get(position).getMovieTitle());
        ReleaseYear.setText(Integer.toString(Movies.get(position).getReleaseYear()));
        new DownloadImageTask((ImageView) convertView.findViewById(R.id.MainPoster)).execute(Movies.get(position).getMainPoster());

        ImageView deleteBtn = convertView.findViewById(R.id.Delete);
        ImageView editBtn = convertView.findViewById(R.id.Edit);

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "you clicked on delete button for the movie " + Movies.get(position).getMovieTitle(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                DeleteMovie(Movies.get(position).get_id());
            }
        });
        editBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //TODO
                Snackbar.make(v, "you clicked on edit button for the movie " + Movies.get(position).getMovieTitle(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ReviewActivity.class);
                intent.putExtra(HTTP_PARAM,Movies.get(position).get_id());
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            if(urldisplay!= null){
                try {
                    InputStream in = new java.net.URL(urldisplay).openStream();
                    mIcon11 = BitmapFactory.decodeStream(in);
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
            }

            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    private void DeleteMovie(String Movie_id){
        RequestQueue queue = Volley.newRequestQueue(mContext);
        String url = mContext.getString(R.string.server_url) + "/api/admin/movies/" + Movie_id;
        StringRequest dr = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Toast.makeText(mContext, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast toast = Toast.makeText(mContext.getApplicationContext(),"error while trying to get information from database!",Toast.LENGTH_SHORT);
                    }
                }
        );
        queue.add(dr);
    }
}
