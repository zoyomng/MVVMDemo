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

import com.zoyo.data.R;
import com.zoyo.data.dialog.impl.ConfirmDialogFragment;

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

    public void confirm(View v) {

        ConfirmDialogFragment confirmationDialogFragment = new ConfirmDialogFragment();
        confirmationDialogFragment.show(getSupportFragmentManager(), "dialog");
    }

}
