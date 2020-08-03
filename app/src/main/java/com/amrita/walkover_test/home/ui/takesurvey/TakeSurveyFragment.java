package com.amrita.walkover_test.home.ui.takesurvey;

import android.database.SQLException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.amrita.walkover_test.R;
import com.amrita.walkover_test.utils.DBHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TakeSurveyFragment extends Fragment {

    @BindView(R.id.name_et)
    EditText name;

    @BindView(R.id.address_et)
    EditText address;

    @BindView(R.id.age_et)
    EditText age;

    @BindView(R.id.gender_et)
    EditText gender;

    @BindView(R.id.last_education_et)
    EditText lasteducation;

    @BindView(R.id.want_to_be_et)
    EditText wanttobe;

    @BindView(R.id.save_button)
    Button save;

    private DBHelper dbHelper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_take_survey, container, false);
        ButterKnife.bind(this, root);

        setOnClickListeners();

        dbHelper = new DBHelper(getContext());
        try{
            dbHelper.openDatabase();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return root;
    }

    private void setOnClickListeners() {

        save.setOnClickListener( view -> {
                if(name.getText().toString().isEmpty()){
                    name.setError("Enter your name");
                } else if(address.getText().toString().isEmpty()){
                    address.setError("Enter your address");
                } else if(age.getText().toString().isEmpty()){
                    age.setError("Enter your age");
                } else if(gender.getText().toString().isEmpty()){
                    gender.setError("Enter your phone");
                } else if(lasteducation.getText().toString().isEmpty()){
                    lasteducation.setError("Enter your password");
                } else if(wanttobe.getText().toString().isEmpty()){
                    wanttobe.setError("Re enter your password");
                } else {
                    String stringName = name.getText().toString();
                    String stringAddress = address.getText().toString();
                    String stringAge = age.getText().toString();
                    String stringGender = gender.getText().toString();
                    String stringLastEducation = lasteducation.getText().toString();
                    String stringWantToBe = wanttobe.getText().toString();

                    //DB Logic here
                    dbHelper.insertInSurveyTable(stringName, stringAddress, Integer.parseInt(stringAge),
                            stringGender, stringLastEducation, stringWantToBe);

                }
        });
    }
}