package vn.quanghuy.cinemaschedule.activity.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

import vn.quanghuy.cinemaschedule.activity.R;
import vn.quanghuy.cinemaschedule.activity.adapter.MovieCinemaAdapter;
import vn.quanghuy.cinemaschedule.activity.bean.BaseObject;
import vn.quanghuy.cinemaschedule.activity.bean.Movie;
import vn.quanghuy.cinemaschedule.activity.utilities.HtmlParser;

public class MovieCinemaFragment extends Fragment implements AdapterView.OnItemClickListener {

    private List<BaseObject> movieCinemaList;
    private ListView lvMovie;
    private ArrayAdapter<BaseObject> adapterMovie;
    private ArrayAdapter<BaseObject> adapterCinema;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.movie_cinema_layout, null);
        lvMovie = (ListView) view.findViewById(R.id.lvCurrent);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        new CinemaAsynTask().execute();
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        BaseObject object = movieCinemaList.get(position);
        if(object instanceof Movie){
            Movie movie = (Movie) object;
            String movieDetailLink = movie.getLinkDetail();

            Intent movieDetail = new Intent(getActivity(),MovieDetail.class);
            movieDetail.putExtra("Link",movieDetailLink);
            startActivity(movieDetail);
        }


    }

    class CinemaAsynTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            lvMovie.setOnItemClickListener(null);
        }

        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub
            HtmlParser htmlParser = new HtmlParser(getContext());
            movieCinemaList = htmlParser.getDataList();

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            progressBar.setVisibility(View.GONE);
            adapterMovie = new MovieCinemaAdapter(getActivity(), R.layout.item_movie_cinema, movieCinemaList);
            lvMovie.setAdapter(adapterMovie);
            lvMovie.setOnItemClickListener(MovieCinemaFragment.this);
        }

    }

}
