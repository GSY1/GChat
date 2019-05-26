package com.example.gsy.gchat.linkman;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.gsy.gchat.R;
import com.example.gsy.gchat.bean.Group;

import java.util.List;

/**
 * Created by GSY on 2019/5/10.
 */

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {

    private List<Group> groups;
    private Context context;

    public GroupAdapter(Context context,List<Group> groups) {

        this.context = context;
        this.groups = groups;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
               Group group = new Group();
               group = groups.get(position);
               holder.groupName.setText(group.getName());

    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

       private ImageView groupImag;
       private TextView groupName;

        public ViewHolder(View itemView) {
            super(itemView);
            groupImag = (ImageView) itemView.findViewById(R.id.GroupImage);
            groupName = (TextView) itemView.findViewById(R.id.GroupName);

        }




        public ImageView getGroupImag() {
            return groupImag;
        }

        public void setGroupImag(ImageView groupImag) {
            this.groupImag = groupImag;
        }

        public TextView getGroupName() {
            return groupName;
        }

        public void setGroupName(TextView groupName) {
            this.groupName = groupName;
        }

        @Override
        public String toString() {
            return "ViewHolder{" +
                    "groupImag=" + groupImag +
                    ", groupName=" + groupName +
                    '}';
        }
    }



}
