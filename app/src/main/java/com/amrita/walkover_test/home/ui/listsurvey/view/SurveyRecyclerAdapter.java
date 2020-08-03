package com.amrita.walkover_test.home.ui.listsurvey.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amrita.walkover_test.R;
import com.amrita.walkover_test.home.ui.listsurvey.model.SurveyListDetails;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SurveyRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<SurveyListDetails> surveyListDetails = new ArrayList<>();

    public SurveyRecyclerAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_survey_list, parent, false);
        return new SurveyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SurveyListDetails surveyListDetailsList = surveyListDetails.get(position);
        final SurveyViewHolder surveyViewHolder = (SurveyViewHolder) holder;

        surveyViewHolder.name.setText("Name: " + surveyListDetailsList.getName());
        surveyViewHolder.address.setText("Address: " + surveyListDetailsList.getAddress());
        surveyViewHolder.age.setText("Age: " + surveyListDetailsList.getAge());
        surveyViewHolder.gender.setText("Gender: " + surveyListDetailsList.getGender());
        surveyViewHolder.lasteducation.setText("Last Education: " + surveyListDetailsList.getLastEducation());
        surveyViewHolder.wanttobe.setText("Want to be: " + surveyListDetailsList.getWantTopBe());
    }

    @Override
    public int getItemCount() {
        return surveyListDetails.size();
    }

    void setData(List<SurveyListDetails> surveyListDetails) {
        this.surveyListDetails = surveyListDetails;
    }

    class SurveyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.address)
        TextView address;

        @BindView(R.id.age)
        TextView age;

        @BindView(R.id.gender)
        TextView gender;

        @BindView(R.id.last_education)
        TextView lasteducation;

        @BindView(R.id.want_to_be)
        TextView wanttobe;

        SurveyViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
