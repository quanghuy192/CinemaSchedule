package vn.quanghuy.cinemaschedule.activity.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import vn.quanghuy.cinemaschedule.activity.R;

public class MainActivity extends AppCompatActivity {

    private FragmentTabHost fragmentTabHost;

    // Default the first tab always is selected
    private static int idTabSelected = 0;

    /*
     * (non-Javadoc)
     *
     * @see android.support.v7.app.AppCompatActivity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init tabHost
        fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        // Create view for each tab
        // @param1 : context
        // @param2 : layout to load layout
        // @param3 : image resource for tab
        // @param4 : set title for tab
        View curentMovieTab = createTab(this, R.layout.tab_bg, R.drawable.current_movie32, "Phim đang chiếu");
        View newMovieTab = createTab(this, R.layout.tab_bg, R.drawable.new_movie_32, "Phim sắp chiếu");
        View cinemaTab = createTab(this, R.layout.tab_bg, R.drawable.cinema32, "Rạp");

        // Add tab
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec("Current").setIndicator(curentMovieTab), MovieCinemaFragment.class,
                null);
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec("New").setIndicator(newMovieTab), MovieCinemaFragment.class, null);
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec("Cinema").setIndicator(cinemaTab), MovieCinemaFragment.class,
                null);

        fragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            @Override
            public void onTabChanged(String tabId) {
                // TODO Auto-generated method stub
                int i = fragmentTabHost.getCurrentTab();

                // id tab = index of tab
                idTabSelected = i;
            }
        });

    }

    public static int getIdTabSelected() {
        return idTabSelected;
    }

    // Method to create View for tab
    private View createTab(Context context, int idLayout, int idImage, String title) {
        View view = LayoutInflater.from(context).inflate(idLayout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.tabsImage);
        TextView textView = (TextView) view.findViewById(R.id.tabsTitle);

        // Set resource
        imageView.setImageResource(idImage);
        textView.setText(title);
        return view;
    }

}