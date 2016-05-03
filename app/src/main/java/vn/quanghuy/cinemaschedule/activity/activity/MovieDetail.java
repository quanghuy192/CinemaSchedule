package vn.quanghuy.cinemaschedule.activity.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import vn.quanghuy.cinemaschedule.activity.R;

/**
 * Created by huy on 13/02/2016.
 */
public class MovieDetail extends AppCompatActivity {

    private String detailLink ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.film_detail);

        // init
        Bundle bundle = getIntent().getExtras();
        detailLink = bundle.getString("Link");

        Toast.makeText(this,detailLink,Toast.LENGTH_SHORT).show();
    }
}
