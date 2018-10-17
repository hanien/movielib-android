package se.chalmers.cse.wm1819.dit341template;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import se.chalmers.cse.wm1819.dit341template.Adapters.BaseAdpterList;

public class MovieDetails extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intent = getIntent();
        String movieId = intent.getStringExtra(BaseAdpterList.movieId);

        TextView tv = (TextView)findViewById(R.id.movieDetailsMovieTitle);
        tv.setText(movieId);
    }

}
