package com.zoyo.data.dataformat;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zoyo.data.R;

import java.util.ArrayList;

/**
 * @Description: java类作用描述
 * @CreateDate: 2019/11/4 9:12
 */
public class DataFormatActivity extends AppCompatActivity {

    private ArrayList<String> data;
    private float value = 13.1415f;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_format);
        initData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(DataFormatActivity.this));
        recyclerView.setAdapter(new MyAdapter());
    }

    private void initData() {
        if (data == null) {
            data = new ArrayList<>();
        }

        data.add(getString(R.string.decimal, "0.00", DecimalFormatUtil.decimalFormat("0.00", value)));
        data.add(getString(R.string.decimal, "00.00", DecimalFormatUtil.decimalFormat("00.00", value)));
        data.add(getString(R.string.decimal, "000.000000", DecimalFormatUtil.decimalFormat("000.000000", value)));
        data.add(getString(R.string.decimal, "0.0%", DecimalFormatUtil.decimalFormat("0.0%", value)));
        data.add(getString(R.string.decimal, "0.00衔接%", DecimalFormatUtil.decimalFormatAppend("0.00").append("%")));
        data.add(getString(R.string.decimal, "#.##", DecimalFormatUtil.decimalFormat("#.##", value)));
        data.add(getString(R.string.decimal, "##.##", DecimalFormatUtil.decimalFormat("##.##", value)));
        data.add(getString(R.string.decimal, "###.######", DecimalFormatUtil.decimalFormat("###.######", value)));
        data.add(getString(R.string.decimal, "#.#%", DecimalFormatUtil.decimalFormat("#.#%", value)));

        data.add(getString(R.string.decimal, "0.0", DecimalFormatUtil.decimalFormat("9.35")));
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_text, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.textView.setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            private TextView textView;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.text_view);
            }
        }
    }
}
