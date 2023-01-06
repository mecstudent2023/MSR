package com.mec.msr;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mec.msr.dbclasses.MyDatabaseHelper;
import com.mec.msr.dbclasses.MyRequest;

import java.util.ArrayList;

public class UpdateEquipmentActivity extends AppCompatActivity {


    private ImageView imageViewEquipment;
    private Spinner spinnerEquipmentType;
    private EditText editTextEquipmentReserveTime;
    private EditText editTextRequestBy;

    ArrayList<String> _EquipmentTypes;
    String _SelectedEquipmentType;
    private MyRequest _MyRequestObject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_equipment);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            _MyRequestObject = (MyRequest) extras.getSerializable("my_request");
        }

        imageViewEquipment = findViewById(R.id.imageViewEquipment);
        spinnerEquipmentType = findViewById(R.id.spinnerEquipmentType);
        editTextEquipmentReserveTime = findViewById(R.id.editTextEquipmentReserveTime);
        editTextRequestBy = findViewById(R.id.editTextRequestBy);
        Button buttonAddRequest = findViewById(R.id.buttonAddRequest);


        _EquipmentTypes = new ArrayList<>();
        _EquipmentTypes.add("Big Forklift");
        _EquipmentTypes.add("Container Forklift");
        _EquipmentTypes.add("Teal-handler Forklift");
        _EquipmentTypes.add("Medium Forklift");
        _EquipmentTypes.add("Small Forklift");
        _EquipmentTypes.add("Stacker Forklift");

        spinnerEquipmentType.setOnItemSelectedListener(onItemSelected());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, _EquipmentTypes);
        spinnerEquipmentType.setAdapter(adapter);

        buttonAddRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkEntries();
            }
        });

        changeEquipmentImage();


        spinnerEquipmentType.setSelection(_EquipmentTypes.indexOf(_MyRequestObject.getEquipmentType()));
        editTextEquipmentReserveTime.setText(_MyRequestObject.getReserveTime());
        editTextRequestBy.setText(_MyRequestObject.getRequestedBy());
    }


    private void checkEntries() {

        String reserveTime = editTextEquipmentReserveTime.getText().toString();
        String requestedBy = editTextRequestBy.getText().toString();

        if (reserveTime.length() <= 0) {
            Toast.makeText(this, "Please fill Reserve Time", Toast.LENGTH_SHORT).show();
        } else if (requestedBy.length() <= 0) {
            Toast.makeText(this, "Please fill Requested by", Toast.LENGTH_SHORT).show();
        } else {
            addRequestToMySqlite(reserveTime, requestedBy);
        }

    }

    private void addRequestToMySqlite(String reserveTime, String requestedBy) {
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(UpdateEquipmentActivity.this);
        dbHelper.updateRequest(new MyRequest(_MyRequestObject.getId(),_SelectedEquipmentType, reserveTime, requestedBy));
        finish();
    }


    private AdapterView.OnItemSelectedListener onItemSelected() {
        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                _SelectedEquipmentType = _EquipmentTypes.get(i);
                changeEquipmentImage();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };

        return listener;
    }


    private void changeEquipmentImage() {
        switch (_SelectedEquipmentType) {
            case "Big Forklift":
                imageViewEquipment.setImageResource(R.drawable.fl_big);
                break;
            case "Container Forklift":
                imageViewEquipment.setImageResource(R.drawable.fl_containers);
                break;
            case "Teal-handler Forklift":
                imageViewEquipment.setImageResource(R.drawable.fl_talehandler);
                break;
            case "Medium Forklift":
                imageViewEquipment.setImageResource(R.drawable.fl_medium);
                break;
            case "Small Forklift":
                imageViewEquipment.setImageResource(R.drawable.fl_small);
                break;
            case "Stacker Forklift":
                imageViewEquipment.setImageResource(R.drawable.fl_stacker);
                break;
        }
    }

}