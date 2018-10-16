package se.chalmers.cse.wm1819.dit341template.Adapters;
import android.content.Context;
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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import se.chalmers.cse.wm1819.dit341template.R;
import se.chalmers.cse.wm1819.dit341template.model.Movie;

public class BaseAdpterList extends BaseAdapter {

    Context mContext;
    List <Movie> Movies;

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


        TextView MovieTitle=(TextView)convertView.findViewById(R.id.Name);
        TextView ReleaseYear=(TextView)convertView.findViewById(R.id.Date);

        MovieTitle.setText(Movies.get(position).getMovieTitle());
        ReleaseYear.setText(Integer.toString(Movies.get(position).getReleaseYear()));
        new DownloadImageTask((ImageView) convertView.findViewById(R.id.imageview)).execute(Movies.get(position).getMainPoster());

        ImageView deleteBtn = convertView.findViewById(R.id.Delete);
        ImageView editBtn = convertView.findViewById(R.id.Edit);

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //TODO
                Snackbar.make(v, "you clicked on delete button for the movie " + Movies.get(position).getMovieTitle(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
                //TODO
                Snackbar.make(view, "you clicked on a movie " + Movies.get(position).getMovieTitle(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
