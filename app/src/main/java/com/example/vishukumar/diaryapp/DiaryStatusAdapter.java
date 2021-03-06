package com.example.vishukumar.diaryapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DiaryStatusAdapter extends RecyclerView.Adapter<DiaryStatusAdapter.StudentViewHolder> {

    private Context context;
    private List<DiaryStatus> diaryStatusList;

    public DiaryStatusAdapter(Context context, List<DiaryStatus> diaryStatusList) {
        this.context = context;
        this.diaryStatusList = diaryStatusList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        //View view = layoutInflater.inflate(R.layout.list_layout, null);
        View view = layoutInflater.inflate(R.layout.list_layout_all_status, null);
        StudentViewHolder studentViewHolder = new StudentViewHolder(view);
        return studentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        final DiaryStatus diaryStatus = diaryStatusList.get(position);

        String mood = diaryStatus.getMood();

        switch (mood.toUpperCase()) {
            case "HAPPY": holder.imageView.setImageResource(R.drawable.happy);  break;
            case "SAD": holder.imageView.setImageResource(R.drawable.sad); break;
            case "ANGRY": holder.imageView.setImageResource(R.drawable.angry); break;
            case "JOYFUL": holder.imageView.setImageResource(R.drawable.joyful); break;
            case "MOTIVATED": holder.imageView.setImageResource(R.drawable.motivated); break;
            case "CONFUSED": holder.imageView.setImageResource(R.drawable.confused); break;
        }

        //holder.imageView.setImageResource(R.drawable.happy);
        holder.textView1.setText(diaryStatus.getDate());
        holder.textView2.setText(mood);
        holder.textView3.setText(diaryStatus.getDescription());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "" + diaryStatus, Toast.LENGTH_SHORT).show();

                Intent toIndividualStatusPage = new Intent(context, DetailedIndividualStatus.class);
                toIndividualStatusPage.putExtra("_DATE", diaryStatus.getDate());
                toIndividualStatusPage.putExtra("_MOOD", diaryStatus.getMood());
                toIndividualStatusPage.putExtra("_DESC", diaryStatus.getDescription());
                context.startActivity(toIndividualStatusPage);

            }
        });
    }

    @Override
    public int getItemCount() {
        return diaryStatusList.size();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView1;
        TextView textView2;
        TextView textView3;
        ConstraintLayout constraintLayout;

        public StudentViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.id_imageView_1);
            textView1 = (TextView) itemView.findViewById(R.id.dateTextViewid);
            textView2 = (TextView) itemView.findViewById(R.id.moodTextViewid);
            textView3 = (TextView) itemView.findViewById(R.id.descTextViewid);
            constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.id_constraint_layout_1);
        }

    }

}
