package com.xzjc.damont.xzjc;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    //热处理
    private TextView rcl_gqwd;//干球温度
    private TextView rcl_sqwd;//湿球温度
    private TextView rcl_hjwd;//环境温度
    private TextView rcl_mxwd;//木芯温度
    private TextView rcl_xdsd;//相对湿度

    private TextView rcl_gqwd_text;
    private TextView rcl_sqwd_text;
    private TextView rcl_hjwd_text;
    private TextView rcl_mxwd_text;
    private TextView rcl_xdsd_text;

    private TextView rcl_gqwd_text_tag;
    private TextView rcl_sqwd_text_tag;
    private TextView rcl_hjwd_text_tag;
    private TextView rcl_mxwd_text_tag;
    private TextView rcl_xdsd_text_tag;

    private TextView rcl_gqwd_tag;
    private TextView rcl_sqwd_tag;
    private TextView rcl_hjwd_tag;
    private TextView rcl_mxwd_tag;
    private TextView rcl_xdsd_tag;
    //熏蒸室
    private TextView xzs_qtnd;//气体浓度
    private TextView xzs_wd;//温度
    private TextView xzs_sd;//湿度

    private TextView xzs_qtnd_text;
    private TextView xzs_wd_text;
    private TextView xzs_sd_text;

    private TextView xzs_qtnd_text_tag;
    private TextView xzs_wd_text_tag;
    private TextView xzs_sd_text_tag;

    private TextView xzs_qtnd_tag;
    private TextView xzs_wd_tag;
    private TextView xzs_sd_tag;

    //处理开始时间、结束时间
    private Date time_start_set,time_end_set;
    private TextView start_time_text;
    private TextView end_time_text;
    private TextView start_time;
    private TextView end_time;
    private TextView set_time;
    private EditText showDateStart;
    private EditText showDateEnd;

    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;

    private static final int SHOW_DATAPICK = 0;
    private static final int DATE_DIALOG_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    private void initView(){
        //干球溫度
        LinearLayout layout1 = (LinearLayout)findViewById(R.id.show_data_gqwd).findViewById(R.id.layout_show_data);
        rcl_gqwd = (TextView)layout1.findViewById(R.id.data_num);
        rcl_gqwd_text = (TextView) layout1.findViewById(R.id.rcl_item_name);
        rcl_gqwd_text.setText(R.string.rcl_gqwd);
        rcl_gqwd.setText("35");

        //濕球溫度
        LinearLayout layout2 = (LinearLayout)findViewById(R.id.show_data_sqwd).findViewById(R.id.layout_show_data);
        rcl_sqwd_text = (TextView)layout2.findViewById(R.id.rcl_item_name);
        rcl_sqwd_text.setText(R.string.rcl_sqwd);
        rcl_sqwd = (TextView) findViewById(R.id.data_num);
        rcl_sqwd.setText("26");

        //環境溫度
        LinearLayout layout3 = (LinearLayout) findViewById(R.id.rcl_layout_hjwd).findViewById(R.id.item_tag);
        rcl_hjwd_text = (TextView)layout3.findViewById(R.id.item_name);
        rcl_hjwd_text.setText(R.string.rcl_hjwd);
        rcl_hjwd = (TextView)layout3.findViewById(R.id.data);
        rcl_hjwd.setText("32");
        int k = Integer.parseInt(rcl_hjwd.getText().toString());
        rcl_hjwd_text_tag = (TextView)layout3.findViewById(R.id.item_color_tag);
        setBkColor(rcl_hjwd_text_tag,k);

        //木芯溫度
        LinearLayout layout4 = (LinearLayout)findViewById(R.id.rcl_layout_mxwd).findViewById(R.id.item_tag);
        rcl_mxwd_text = (TextView)layout4.findViewById(R.id.item_name);
        rcl_mxwd_text.setText(R.string.rcl_mxwd);
        rcl_mxwd = (TextView)layout4.findViewById(R.id.data);
        rcl_mxwd.setText("42");
        k = Integer.parseInt(rcl_mxwd.getText().toString());
        rcl_mxwd_text_tag = (TextView)layout4.findViewById(R.id.item_color_tag);
        setBkColor(rcl_mxwd_text_tag,k);

        //相對濕度
        LinearLayout layout5 = (LinearLayout)findViewById(R.id.rcl_layout_xdsd).findViewById(R.id.item_tag);
        rcl_xdsd = (TextView)layout5.findViewById(R.id.data);
        rcl_xdsd.setText("56");
        rcl_xdsd_text = (TextView)layout5.findViewById(R.id.item_name);
        rcl_xdsd_text.setText(R.string.rcl_xdsd);
        rcl_xdsd_tag = (TextView)layout5.findViewById(R.id.item_tag_1);
        rcl_xdsd_tag.setText(R.string.sd);
        rcl_xdsd_text_tag = (TextView)layout5.findViewById(R.id.item_color_tag);
        k = Integer.parseInt(rcl_xdsd.getText().toString());
        setBkColor(rcl_xdsd_text_tag,k);

        //開始時間設置
        LinearLayout layout6 = (LinearLayout)findViewById(R.id.time_start).findViewById(R.id.item_set_data);
        start_time_text = (TextView)layout6.findViewById(R.id.s_e_time_text);
        start_time_text.setText(R.string.time_start);
        start_time = (TextView)layout6.findViewById(R.id.set_date);
        setDate(start_time);

        start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_time = (TextView)v;
                Message msg = new Message();
                msg.what = MainActivity.SHOW_DATAPICK;
                MainActivity.this.dateAndTimeHandler.sendMessage(msg);

            }
        });
        //結束時間設置
        LinearLayout layout7 = (LinearLayout)findViewById(R.id.time_end).findViewById(R.id.item_set_data);
        end_time_text = (TextView)layout7.findViewById(R.id.s_e_time_text);
        end_time_text.setText(R.string.time_end);
        end_time = (TextView)layout7.findViewById(R.id.set_date);
        setDate(end_time);

        end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_time = (TextView)v;
                Message msg = new Message();
                msg.what = MainActivity.SHOW_DATAPICK;
                MainActivity.this.dateAndTimeHandler.sendMessage(msg);
            }
        });

        //氣體濃度
        LinearLayout layout8 = (LinearLayout)findViewById(R.id.xzs_layout_qtnd).findViewById(R.id.item_tag);
        xzs_qtnd_text = (TextView)layout8.findViewById(R.id.item_name);
        xzs_qtnd = (TextView)layout8.findViewById(R.id.data);
        xzs_qtnd_text_tag = (TextView)layout8.findViewById(R.id.item_tag_1);
        xzs_qtnd_tag = (TextView)layout8.findViewById(R.id.item_color_tag);

        xzs_qtnd_text.setText(R.string.xzs_qtnd);
        xzs_qtnd_text_tag.setText(R.string.qtnd);
        xzs_qtnd.setText("89");
        k = Integer.parseInt(xzs_qtnd.getText().toString());
        setBkColor(xzs_qtnd_tag,k);

        //溫度
        LinearLayout layout9 = (LinearLayout)findViewById(R.id.xzs_layout_wd).findViewById(R.id.item_tag);
        xzs_wd = (TextView) layout9.findViewById(R.id.data);
        xzs_wd_text = (TextView) layout9.findViewById(R.id.item_name);
        xzs_wd_text_tag = (TextView) layout9.findViewById(R.id.item_tag_1);
        xzs_wd_tag = (TextView) layout9.findViewById(R.id.item_color_tag);

        xzs_wd_text.setText(R.string.xzs_wd);
        xzs_wd.setText("34");
        xzs_wd_text_tag.setText(R.string.wd);
        k = Integer.parseInt(xzs_wd.getText().toString());
        setBkColor(xzs_wd_tag,k);

        //濕度
        LinearLayout layout10 = (LinearLayout)findViewById(R.id.xzs_layout_sd).findViewById(R.id.item_tag);
        xzs_sd = (TextView) layout10.findViewById(R.id.data);
        xzs_sd_text = (TextView)layout10.findViewById(R.id.item_name);
        xzs_sd_text_tag = (TextView) layout10.findViewById(R.id.item_tag_1);
        xzs_sd_tag = (TextView) layout10.findViewById(R.id.item_color_tag);
        xzs_sd.setText("44");
        xzs_sd_text.setText(R.string.xzs_sd);
        xzs_sd_text_tag.setText(R.string.sd);
        k = Integer.parseInt(xzs_sd.getText().toString());
        setBkColor(xzs_sd_tag,k);
    }
    private void setDate(View v){
        final Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        set_time = (TextView)v;
        updateDateDisplay();
    }

    private void updateDateDisplay(){
        set_time.setText(new StringBuilder().append(mYear).append("-")
                .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append("-")
                .append((mDay < 10) ? "0" + mDay : mDay));
    }
    /**
     * 处理日期和时间控件的Handler
     */
    Handler dateAndTimeHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MainActivity.SHOW_DATAPICK:
                    showDialog(DATE_DIALOG_ID);
                    break;
            }
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
                        mDay);
        }
        return null;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        switch (id) {
            case DATE_DIALOG_ID:
                ((DatePickerDialog) dialog).updateDate(mYear, mMonth, mDay);
                break;

        }
    }
    /**
     * 日期控件的事件
     */
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;

            updateDateDisplay();
        }
    };
    private void setBkColor(View v,int k){
        if( k <= 40 ){
            v.setBackgroundDrawable(getResources().getDrawable(R.drawable.green));
        }else if ( k > 40 && k <50){
            v.setBackgroundDrawable(getResources().getDrawable(R.drawable.yellow));
        }else {
            v.setBackgroundDrawable(getResources().getDrawable(R.drawable.red));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
