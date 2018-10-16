package se.chalmers.cse.wm1819.dit341template;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ReviewActivity extends AppCompatActivity {

    FloatingActionButton fab;
    private Context mcontext;
    private String Movie_id;
    private static final String HTTP_PARAM = "httpResponse";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
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
}
