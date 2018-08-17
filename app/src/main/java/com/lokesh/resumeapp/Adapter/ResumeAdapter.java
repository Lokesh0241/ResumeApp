package com.lokesh.resumeapp.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import com.lokesh.resumeapp.Activity.HomeActivity;
import com.lokesh.resumeapp.Activity.PersonDetailsActivity;
import com.lokesh.resumeapp.Model.CreateBioModel;
import com.lokesh.resumeapp.Model.ResumeModel;
import com.lokesh.resumeapp.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by kishumewara on 10/08/18.
 */

public class ResumeAdapter extends RecyclerView.Adapter<ResumeAdapter.ViewHolder> {

    HomeActivity homeActivity;
    List<CreateBioModel> createBioModels;
    List<CreateBioModel> filterArr;
    RecyclerView rvResumeList;
    String data;
    Bitmap decodedByte;
    Bitmap profileImg;
    List<CreateBioModel> newList = new ArrayList<>();



    public ResumeAdapter(HomeActivity homeActivity, List<CreateBioModel> createBioModels, RecyclerView rvResumeList) {
        this.homeActivity = homeActivity;
        this.createBioModels = createBioModels;
        this.rvResumeList = rvResumeList;

    }

    @NonNull
    @Override
    public ResumeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resume_list, null);
        return new ResumeAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ResumeAdapter.ViewHolder holder, final int position) {

        final CreateBioModel createBioModel = createBioModels.get(position);

        holder.tvName.setText(createBioModel.getEtName());
        holder.tvPhone.setText(createBioModel.getEtMobileStr());

        SharedPreferences shared = homeActivity.getSharedPreferences("login_detail", MODE_PRIVATE);
        String image = shared.getString("ImageString", null);

        holder.profile_image.setImageBitmap(profileImg);

        if (createBioModel.getUserImg()== null) {

            holder.profile_image.setImageResource(R.drawable.userprofile);

        } else

            imageConvertIntoBitmap(createBioModel.getUserImg());
        holder.profile_image.setImageBitmap(decodedByte);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CreateBioModel createBioModel=new CreateBioModel();
                createBioModel.setUserImg((createBioModels.get(position).getUserImg()));
                createBioModel.setEtName((createBioModels.get(position).getEtName()));
                createBioModel.setEtFatherName((createBioModels.get(position).getEtFatherName()));
                createBioModel.setEtFatherOccupation((createBioModels.get(position).getEtFatherOccupation()));
                createBioModel.setEtMotherName((createBioModels.get(position).getEtMotherName()));
                createBioModel.setEtMotherOcp((createBioModels.get(position).getEtMotherOcp()));
                createBioModel.setEtMobileStr((createBioModels.get(position).getEtMobileStr()));
                createBioModel.setEtDob((createBioModels.get(position).getEtDob()));
                createBioModel.setEtTob((createBioModels.get(position).getEtTob()));
                createBioModel.setEtBrother((createBioModels.get(position).getEtBrother()));
                createBioModel.setEtSister((createBioModels.get(position).getEtSister()));
                createBioModel.setEtWeight((createBioModels.get(position).getEtWeight()));
                createBioModel.setEtHeight((createBioModels.get(position).getEtHeight()));
                createBioModel.setEtGotra((createBioModels.get(position).getEtGotra()));
                createBioModel.setEtMamaGotra((createBioModels.get(position).getEtMamaGotra()));
                createBioModel.setEtComplexion((createBioModels.get(position).getEtComplexion()));
                createBioModel.setEtManglic((createBioModels.get(position).getEtManglic()));
                createBioModel.setEtSex((createBioModels.get(position).getEtSex()));
                createBioModel.setEtMarried((createBioModels.get(position).getEtMarried()));
                createBioModel.setEtEducation((createBioModels.get(position).getEtEducation()));
                createBioModel.setEtOccuption((createBioModels.get(position).getEtOccuption()));
                createBioModel.setEtAddress((createBioModels.get(position).getEtAddress()));
                createBioModel.setEtState((createBioModels.get(position).getEtState()));
                createBioModel.setEtCity((createBioModels.get(position).getEtCity()));
                createBioModel.setEtChilds((createBioModels.get(position).getEtChilds()));
                createBioModel.setEtDescription((createBioModels.get(position).getEtDescription()));

                newList.add(createBioModel);

                Intent intent = new Intent(homeActivity, PersonDetailsActivity.class);
                intent.putExtra("listUser",(Serializable)newList);
                homeActivity.startActivity(intent);
            }
        });

    }


    public Bitmap imageConvertIntoBitmap(String encodedImage) {
        byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
        decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }

    @Override
    public int getItemCount() {
        return createBioModels.size();
        //return  5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView profile_image;
        TextView tvName;
        TextView tvPhone;

        public ViewHolder(View itemView) {
            super(itemView);

            profile_image = itemView.findViewById(R.id.profile_image);
            tvName = itemView.findViewById(R.id.tvName);
            tvPhone = itemView.findViewById(R.id.tvPhone);

        }
    }


    @SuppressWarnings("unchecked")
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<CreateBioModel> FilteredArrList = new ArrayList<CreateBioModel>();

                if (filterArr == null) {
                    filterArr = new ArrayList<CreateBioModel>(createBioModels); // saves the original data in mOriginalValues
                }

                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = filterArr.size();
                    results.values = filterArr;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < filterArr.size(); i++) {

                        data = filterArr.get(i).getEtName();
                        /*data = filterArr.get(i).getEtMobileStr();
                        data = filterArr.get(i).getEtAddress();
                        data = filterArr.get(i).getEtMarried();*/

                        if (data.toLowerCase().contains(constraint.toString())) {
                            FilteredArrList.add(filterArr.get(i));
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                createBioModels = (ArrayList<CreateBioModel>) filterResults.values;

//                PageFragment frag = PageFragment.getInstance();
//                frag.sortArr = (List<GalleyAllDesignModel>) filterResults.values;

                if (createBioModels == null)
                    createBioModels = new ArrayList<>();

                notifyDataSetChanged();
            }
        };
    }

}
