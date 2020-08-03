package com.amrita.walkover_test.home.ui.listsurvey.view;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amrita.walkover_test.R;
import com.amrita.walkover_test.home.ui.listsurvey.model.SurveyListDetails;
import com.amrita.walkover_test.utils.DBHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.amrita.walkover_test.utils.DBHelper.SURVEY_COLUMN_ADDRESS;
import static com.amrita.walkover_test.utils.DBHelper.SURVEY_COLUMN_AGE;
import static com.amrita.walkover_test.utils.DBHelper.SURVEY_COLUMN_GENDER;
import static com.amrita.walkover_test.utils.DBHelper.SURVEY_COLUMN_LAST_EDUCATION;
import static com.amrita.walkover_test.utils.DBHelper.SURVEY_COLUMN_NAME;
import static com.amrita.walkover_test.utils.DBHelper.SURVEY_COLUMN_WANT_TO_BE;


public class ListSurveyFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.empty_state_message)
    TextView emptyStateMessage;

    private DBHelper dbHelper;
    private SurveyRecyclerAdapter surveyRecyclerAdapter;
    private List<SurveyListDetails> dataList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, root);
        dbHelper = new DBHelper(getContext());
        
        // Recycler View
        surveyRecyclerAdapter = new SurveyRecyclerAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(surveyRecyclerAdapter);
        
        //Load Survey
        loadSurvey();
        
        return root;
    }

    private void loadSurvey() {
        dataList.clear();
        Cursor cursor = dbHelper.getAllSurvey();
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()) {
                SurveyListDetails noteListDetails = new SurveyListDetails(
                        cursor.getString(cursor.getColumnIndex("_id")),
                        cursor.getString(cursor.getColumnIndex(SURVEY_COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(SURVEY_COLUMN_ADDRESS)),
                        cursor.getInt(cursor.getColumnIndex(SURVEY_COLUMN_AGE)),
                        cursor.getString(cursor.getColumnIndex(SURVEY_COLUMN_GENDER)),
                        cursor.getString(cursor.getColumnIndex(SURVEY_COLUMN_LAST_EDUCATION)),
                        cursor.getString(cursor.getColumnIndex(SURVEY_COLUMN_WANT_TO_BE))
                );
                dataList.add(noteListDetails);
                cursor.moveToNext();
            }
        }
        if(dataList.isEmpty()){
            emptyStateMessage.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyStateMessage.setVisibility(View.GONE);
        }
        surveyRecyclerAdapter.setData(dataList);
        surveyRecyclerAdapter.notifyDataSetChanged();
    }
}