package vn.quanghuy.cinemaschedule.activity.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.quanghuy.cinemaschedule.activity.R;
import vn.quanghuy.cinemaschedule.activity.activity.MainActivity;
import vn.quanghuy.cinemaschedule.activity.bean.BaseObject;
import vn.quanghuy.cinemaschedule.activity.bean.Cinema;
import vn.quanghuy.cinemaschedule.activity.bean.Movie;

public class MovieCinemaAdapter extends ArrayAdapter<BaseObject> {
    private List<BaseObject> dataList;
    private Context context;
    private int resource;
    private int currentTab;

    // to know the tab is selected
    private short CINEMA_ID = 2;


    public MovieCinemaAdapter(Context context, int resource, List<BaseObject> objects) {
        super(context, resource, objects);
        // TODO Auto-generated constructor stub
        this.dataList = objects;
        this.context = context;
        this.resource = resource;
        currentTab = MainActivity.getIdTabSelected();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        // sign view = convertView
        // if convertView == null => create new view

        View view = convertView;
        ViewHolder holder;
        if (convertView == null) {
            view = View.inflate(context, resource, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) view.findViewById(R.id.itemIcon);
            holder.textView1 = (TextView) view.findViewById(R.id.itemTv1);
            holder.textView2 = (TextView) view.findViewById(R.id.itemTv2);
            holder.textView3 = (TextView) view.findViewById(R.id.itemTv3);

            // set tag to save ViewHolder
            view.setTag(holder);
        } else {

            // if view exist => get ViewHolder to sign
            holder = (ViewHolder) view.getTag();
        }

        holder.imageView.setImageBitmap(dataList.get(position).getIcon());

        // If current tab is Cinema
        // Set property for listview
        // Other is current movie tab and new movie tab
        if (currentTab == CINEMA_ID) {
            holder.textView1.setText(((Cinema) dataList.get(position)).getCinemaName());
            holder.textView2.setText("ĐC : " + ((Cinema) dataList.get(position)).getAddress());
            holder.textView3.setText("SĐT : " + ((Cinema) dataList.get(position)).getPhoneNumber());
        } else {
            holder.textView1.setText(((Movie) dataList.get(position)).getTitle());
            holder.textView2.setText(((Movie) dataList.get(position)).getType());
            holder.textView3.setText("Ngày công chiếu : " + ((Movie) dataList.get(position)).getDayStart());
        }


        return view;
    }

    // ViewHolder pattern
    private class ViewHolder {
        ImageView imageView;
        TextView textView1;
        TextView textView2;
        TextView textView3;
    }

}
