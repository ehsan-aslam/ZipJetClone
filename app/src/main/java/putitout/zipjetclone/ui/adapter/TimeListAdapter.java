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


    private  String[] eventName;
    private int[] eventImage;
    private Context context;
    private LayoutInflater inflater;

    private int PositionSelected = 0;


    public TimeListAdapter(Context context,  String[] eventName) {
        this.context = context;
        this.eventName = eventName;
        inflater = LayoutInflater.from(context);

    }
    public void setPositionSelected(int position){
        PositionSelected = position;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return eventName.length;
    }

    @Override
    public Object getItem(int position) {
        return eventName[position];
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

            TextView timeLineTextView;
            ImageView timeLineImageView;

            inflater = (LayoutInflater) convertView.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            viewHolder.timeLineTextView = (TextView) convertView.findViewById(R.id.timeLineTextView);
            viewHolder.timeLineImageView = (ImageView) convertView.findViewById(R.id.timeLineImageView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

//
//        if (position == PositionSelected)
//            viewHolder.timeLineTextView.setTextColor(context.getResources().getColor(R.color.greenColor));
//        else
//            viewHolder.timeLineTextView.setTextColor(convertView.getResources().getColor(R.color.darkGreen));
//        if (position == 0) {
//
//            viewHolder.timeLineTextView.setTextColor(convertView.getResources().getColor(R.color.greenColor));
//        }
//        else {
//            viewHolder.timeLineTextView.setTextColor(convertView.getResources().getColor(R.color.darkGreen));
//
//        }
        viewHolder.timeLineTextView.setText(eventName[position]);
//        viewHolder.timeLineImageView.setImageResource(eventImage[position]);

        return convertView;
    }
}


class ViewHolder {
    TextView timeLineTextView;
    ImageView timeLineImageView;
}

