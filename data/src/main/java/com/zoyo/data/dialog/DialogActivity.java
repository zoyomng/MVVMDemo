package com.zoyo.data.dialog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.zoyo.data.R;
import com.zoyo.data.dialog.adapter.BottomSheetAdapter;
import com.zoyo.data.dialog.base.BaseDialogFragment;
import com.zoyo.data.dialog.base.DialogListener;
import com.zoyo.data.dialog.impl.ConfirmDialogFragment;
import com.zoyo.data.dialog.impl.GeneralDialogFragment;
import com.zoyo.data.dialog.impl.MyBottomSheetDialogFragment;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: java类作用描述
 * @CreateDate: 2019/10/16 14:55
 */
public class DialogActivity extends AppCompatActivity {
    private String[] items = new String[]{"北京", "天津", "河北", "河南", "山东", "上海", "山西",
            "陕西", "贵州", "云南", "江苏", "浙江", "广东", "深圳", "湖南"};
    private boolean[] checked = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

    }

    /**
     * 一般提示
     */
    public void simpleDialog(View v) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("标题")
                .setMessage("message")
                .setIcon(R.drawable.car)
                .create();
        alertDialog.show();
    }

    /**
     * 2种选择
     */
    public void confirmDialog(View v) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("标题")
                .setMessage("message")
                .setCancelable(false)
                .setIcon(R.drawable.car)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "确认", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "取消", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .create();
        alertDialog.show();

    }

    /**
     * 单选
     */
    public void singleSelect(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("title")
                .setCancelable(false)
                .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, items[which] + "被选中", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        alertDialog.show();

    }


    /**
     * 多选
     */
    public void multiSelect(View v) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("标题")
                .setCancelable(false)
                .setIcon(R.drawable.car)
                .setMultiChoiceItems(items, checked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        Toast.makeText(DialogActivity.this, items[which] + (isChecked ? "被选中" : "被取消"), Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        alertDialog.show();
    }

    /**
     * 3种选择:"确认","取消", "中立"
     */
    public void threeSelect(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("title")
                .setMessage("message")
                .setCancelable(false)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "确认", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "取消", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setNeutralButton("忽略", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "忽略", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .create();
        alertDialog.show();

    }


    /**
     * 选项列表
     */
    public void selectList(View v) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("标题")
                .setCancelable(false)
                .setIcon(R.drawable.car)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, items[which], Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .create();
        alertDialog.show();
    }

    /**
     * 选项列表
     */
    public void adapter(View v) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("标题")
                .setCancelable(false)
                .setIcon(R.drawable.car)
                .setAdapter(new ArrayAdapter<String>(DialogActivity.this, R.layout.item_dialog, R.id.textView12, items)
                        , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DialogActivity.this, items[which], Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        })
                .create();
        alertDialog.show();
    }

    /**
     * 时间选择
     */
    public void datePickerDialog(View v) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(DialogActivity.this, DatePickerDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            }
        }, 2018, 0, 1);
        datePickerDialog.show();
    }

    public void timePickerDialog(View v) {

        TimePickerDialog timePickerDialog = new TimePickerDialog(DialogActivity.this, TimePickerDialog.THEME_HOLO_LIGHT, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            }
        }, 1, 1, true);
        timePickerDialog.show();
    }

    public void generalDialog(View v) {

        BaseDialogFragment generalDialogFragment = new GeneralDialogFragment.Builder()
                .setMessage("一般提示框")
                .setCancelable(false)
//                .setPositiveButtonListener("确定", new DialogListener.OnClickListener() {
//                    @Override
//                    public void onClick(BaseDialogFragment dialogFragment) {
//                        dialogFragment.dismiss();
//                    }
//                })
                .create();
        generalDialogFragment.show(getSupportFragmentManager(), "dialog");

    }

    public void confirm(View v) {
        BaseDialogFragment confirmationDialogFragment = new ConfirmDialogFragment.Builder()
                .setMessage("message")
                .setCancelable(false)
                .setPositiveButtonListener("确定", new DialogListener.OnClickListener() {
                    @Override
                    public void onClick(BaseDialogFragment dialogFragment) {
                        Toast.makeText(DialogActivity.this, "确定", Toast.LENGTH_SHORT).show();
                        dialogFragment.dismiss();
                    }
                })
                .setNegativeButtonListener("取消", new DialogListener.OnClickListener() {
                    @Override
                    public void onClick(BaseDialogFragment dialogFragment) {
                        Toast.makeText(DialogActivity.this, "取消", Toast.LENGTH_SHORT).show();
                        dialogFragment.dismiss();
                    }
                })
                .create();
        confirmationDialogFragment.show(getSupportFragmentManager(), "dialog");
    }

    public void bottomPopDialog(View v) {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(DialogActivity.this);
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setContentView(R.layout.dialog_bottom_sheet);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.setTitle("BottomSheetDialog");

        RecyclerView recyclerview = bottomSheetDialog.findViewById(R.id.recycler_view);
        recyclerview.setLayoutManager(new LinearLayoutManager(DialogActivity.this));
        BottomSheetAdapter bottomSheetAdapter = new BottomSheetAdapter(Arrays.asList(items));
        bottomSheetAdapter.setOnItemClickListener(new BottomSheetAdapter.OnItemClickListener() {

            @Override
            public void onItemClick() {
                Toast.makeText(DialogActivity.this, "点击了", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            }
        });
        recyclerview.setAdapter(bottomSheetAdapter);
        bottomSheetDialog.show();
    }

    public void bottomSheetDialogFragment(View v) {
        MyBottomSheetDialogFragment fragment = new MyBottomSheetDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray("items", items);
        fragment.setArguments(bundle);
        fragment.show(getSupportFragmentManager(), "dialog");
    }

}
