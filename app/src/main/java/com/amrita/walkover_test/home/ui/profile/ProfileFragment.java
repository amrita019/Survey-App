package com.amrita.walkover_test.home.ui.profile;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.amrita.walkover_test.R;
import com.amrita.walkover_test.auth.login.LoginView;
import com.amrita.walkover_test.home.HomeView;
import com.amrita.walkover_test.home.ui.listsurvey.model.SurveyListDetails;
import com.amrita.walkover_test.utils.DBHelper;
import com.amrita.walkover_test.utils.SharedPrefs;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.amrita.walkover_test.utils.DBHelper.COLUMN_EMAIL;
import static com.amrita.walkover_test.utils.DBHelper.COLUMN_NAME;
import static com.amrita.walkover_test.utils.DBHelper.COLUMN_PHONE;
import static com.amrita.walkover_test.utils.DBHelper.COLUMN_USERNAME;
import static com.amrita.walkover_test.utils.DBHelper.SURVEY_COLUMN_ADDRESS;
import static com.amrita.walkover_test.utils.DBHelper.SURVEY_COLUMN_AGE;
import static com.amrita.walkover_test.utils.DBHelper.SURVEY_COLUMN_GENDER;
import static com.amrita.walkover_test.utils.DBHelper.SURVEY_COLUMN_LAST_EDUCATION;
import static com.amrita.walkover_test.utils.DBHelper.SURVEY_COLUMN_NAME;
import static com.amrita.walkover_test.utils.DBHelper.SURVEY_COLUMN_WANT_TO_BE;


public class ProfileFragment extends Fragment {

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.username)
    TextView username;

    @BindView(R.id.email)
    TextView email;

    @BindView(R.id.phone)
    TextView phone;

    @BindView(R.id.logout)
    Button logout;

    private SharedPrefs sharedPrefs;
    private DBHelper dbHelper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, root);
        sharedPrefs = new SharedPrefs(getContext());
        dbHelper = new DBHelper(getContext());

        loadUserData(sharedPrefs.getUserName());
        setOnClickListeners();
        return root;
    }

    private void setOnClickListeners() {
        logout.setOnClickListener( view -> {
            sharedPrefs.setUserName("");
            sharedPrefs.setIsLoggedIn(false);
            startActivity(new Intent(getActivity(), LoginView.class));
            getActivity().finish();
        });
    }

    private void loadUserData(String userName) {
        Cursor cursor = dbHelper.getUserDetails(userName);
        if(cursor.moveToFirst()){
                name.setText("Name: " +cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                username.setText("User Name: " +cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)));
                email.setText("Email: " +cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
                phone.setText("Phone: " +cursor.getString(cursor.getColumnIndex(COLUMN_PHONE)));

        }    }
}