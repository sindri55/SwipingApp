package com.example.swipingapp.fragments.payment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swipingapp.R;
import com.example.swipingapp.activities.main.MainActivity;

import org.w3c.dom.Text;

/**
 * Created by Sindri on 12/02/16.
 */
public class customAdapterForReceiptFragment extends BaseAdapter {
    String [] items;
    String [] amounts;
    Context context;

    private static LayoutInflater inflater=null;

    public customAdapterForReceiptFragment(Context context, String[] items, String[] amounts) {
        // TODO Auto-generated constructor stub
        this.items = items;
        this.amounts = amounts;

        this.context=context;

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView id;
        TextView item;
        TextView amount;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.customlistview_receipt_items, null);


        holder.id = (TextView) rowView.findViewById(R.id.receipt_customlistview_id);
        holder.item = (TextView) rowView.findViewById(R.id.receipt_customlistview_item_name);
        holder.amount = (TextView) rowView.findViewById(R.id.receipt_customlistview_amount);


        holder.id.setText(String.valueOf(position + 1));
        holder.item.setText(items[position]);
        holder.amount.setText(amounts[position]);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked " + items[position], Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }

}