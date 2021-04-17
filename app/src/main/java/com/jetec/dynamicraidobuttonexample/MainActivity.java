package com.jetec.dynamicraidobuttonexample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.AtomicFile;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName() + "My";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btDisplay = findViewById(R.id.button_display);
        Button btDialog = findViewById(R.id.button_showDialog);
        NumberPicker numberPicker = findViewById(R.id.numberPicker);

        numberPicker.setMaxValue(5);
        numberPicker.setMinValue(1);

        btDisplay.setOnClickListener((v) -> {
            setRadioAction(numberPicker);
            copy(numberPicker.getValue(),findViewById(R.id.linearLayout_Main));

        });

        btDialog.setOnClickListener(v -> {
            setDialogRadioAction(numberPicker);

        });

    }
    /**
     * 設置Dialog上的RadioButton
     */
    private void setDialogRadioAction(NumberPicker numberPicker) {
        /*取得標籤*/
        int rgAmount = numberPicker.getValue();
        ArrayList<String> rgLabel = new ArrayList<>();

        for (int i = 0; i < rgAmount; i++) {
            rgLabel.add("第" + (i + 1) + "項目");
        }
        /*設置AlertDialog以及內容物*/
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.choose_dialog, null);
        mBuilder.setView(view);
        /*根據標籤數量設置幾個RadioButton*/
        LinearLayout layoutDialog = view.findViewById(R.id.linearLayout_Dialog);
        RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(RadioGroup.VERTICAL);
        layoutDialog.removeAllViewsInLayout();
        RadioGroup.LayoutParams rlTable;
        for (int i = 0; i < rgLabel.size(); i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(rgLabel.get(i));
            rlTable = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT
                    , RadioGroup.LayoutParams.MATCH_PARENT);
            radioGroup.addView(radioButton, rlTable);
        }
        layoutDialog.addView(radioGroup);

        /*設置點擊事件*/
        mBuilder.setNegativeButton("取消", null);
        mBuilder.setPositiveButton("OK", (dialog, which) -> {
            try {
                RadioButton radioButton = view.findViewById(radioGroup.getCheckedRadioButtonId());
                String getDialogLab = radioButton.getText().toString();
                int getDialogId= radioGroup.indexOfChild(radioButton);
                Toast.makeText(this, getDialogLab + ", id=" + getDialogId, Toast.LENGTH_LONG).show();
            }catch (Exception e){
                Toast.makeText(this, "未點選任何項目", Toast.LENGTH_LONG).show();
            }

        });

        mBuilder.show();
    }

    /**
     * 設置主畫面上的RadioButton
     */
    private void setRadioAction(NumberPicker numberPicker) {
        /*取得標籤*/
        int rgAmount = numberPicker.getValue();
        ArrayList<String> rgLabel = new ArrayList<>();

        for (int i = 0; i < rgAmount; i++) {
            rgLabel.add("第" + (i + 1) + "項目");
        }
        Log.d(TAG, "setRadioAction: "+rgLabel);
        /*根據標籤數量設置幾個RadioButton*/
        LinearLayout layoutMain = findViewById(R.id.linearLayout_Main);
        RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(RadioGroup.VERTICAL);
        layoutMain.removeAllViewsInLayout();
        RadioGroup.LayoutParams rlTable;
        for (int i = 0; i < rgLabel.size(); i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(rgLabel.get(i));
            rlTable = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT
                    , RadioGroup.LayoutParams.MATCH_PARENT);
            radioGroup.addView(radioButton, rlTable);
        }
        layoutMain.addView(radioGroup);
        /*設置點擊事件*/
        /*點擊並獲取標籤內容與"真正的"ID*/
        radioGroup.setOnCheckedChangeListener(((group, checkedId) -> {
            RadioButton radioButton = findViewById(group.getCheckedRadioButtonId());
            String getLab = radioButton.getText().toString();
            int getGroupId = group.indexOfChild(radioButton);

            Toast.makeText(this, getLab + ", id=" + getGroupId, Toast.LENGTH_LONG).show();
        }));
    }
    /**直接複製區域*/
    private void copy(int amount,LinearLayout layout){
        ArrayList<String> rgLabel = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            rgLabel.add("第" + (i + 1) + "項目");
        }
        /*根據標籤數量設置幾個RadioButton*/
        RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(RadioGroup.VERTICAL);
        layout.removeAllViewsInLayout();
        RadioGroup.LayoutParams rlTable;
        for (int i = 0; i < rgLabel.size(); i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(rgLabel.get(i));
            rlTable = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT
                    , RadioGroup.LayoutParams.MATCH_PARENT);
            radioGroup.addView(radioButton, rlTable);
        }
        layout.addView(radioGroup);
        /*設置點擊事件*/
        /*點擊並獲取標籤內容與"真正的"ID*/
        radioGroup.setOnCheckedChangeListener(((group, checkedId) -> {
            RadioButton radioButton = findViewById(group.getCheckedRadioButtonId());
            String getLab = radioButton.getText().toString();
            int getGroupId = group.indexOfChild(radioButton);
            Toast.makeText(this, getLab + ", id=" + getGroupId, Toast.LENGTH_LONG).show();
        }));
    }
}
