package putitout.zipjetclone.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import putitout.zipjetclone.R;

/**
 * Created by Ehsan on 5/31/2016.
 */
public class TimeListAdapter extends BaseAdapter {


    private  String[] calendarDateAndTime;
    private int[] eventImage;
    private Context context;
    private LayoutInflater inflater;

    private int PositionSelected = 0;


    public TimeListAdapter(Context context,  String[] calendarDateAndTime) {
        this.context = context;
        this.calendarDateAndTime = calendarDateAndTime;
        inflater = LayoutInflater.from(context);

    }
    public void setPositionSelected(int position){
        PositionSelected = position;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return calendarDateAndTime.length;
    }

    @Override
    public Object getItem(int position) {
        return calendarDateAndTime[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_time_layout,parent,false);

            viewHolder = new ViewHolder();

            inflater = (LayoutInflater) convertView.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            viewHolder.dateAndTimeTextView = (TextView) convertView.findViewById(R.id.dateAndTimeTextView);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.dateAndTimeTextView.setText(calendarDateAndTime[position]);
//        viewHolder.imageView.setImageResource(eventImage[position]);

        return convertView;
    }
}


class ViewHolder {
    TextView dateAndTimeTextView;
    ImageView imageView;
}




//
//        if (position == PositionSelected)
//            viewHolder.dateAndTimeTextView.setTextColor(context.getResources().getColor(R.color.greenColor));
//        else
//            viewHolder.dateAndTimeTextView.setTextColor(convertView.getResources().getColor(R.color.darkGreen));
//        if (position == 0) {
//
//            viewHolder.dateAndTimeTextView.setTextColor(convertView.getResources().getColor(R.color.greenColor));
//        }
//        else {
//            viewHolder.dateAndTimeTextView.setTextColor(convertView.getResources().getColor(R.color.darkGreen));
//
//        }