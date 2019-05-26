package com.example.gsy.gchat.linkman;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gsy.gchat.R;
import com.example.gsy.gchat.bean.User;

import java.util.List;

/**
 * Created by GSY on 2019/5/19.
 */

public class FriendAdapter extends RecyclerView.Adapter <FriendAdapter.ViewHolder>{

    private List<User> users;
    private Context context;

    public FriendAdapter(Context context, List<User> users){
        this.context = context;
        this.users = users;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
             User user = new User();
             user = users.get(position);
            holder.friendName.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView friendImag;
        private TextView friendName;

        public ViewHolder(View itemView) {
            super(itemView);
            friendImag = (ImageView) itemView.findViewById(R.id.friendImage);
            friendName = (TextView) itemView.findViewById(R.id.friendName);
        }

        public ImageView getFriendImag() {
            return friendImag;
        }

        public void setFriendImag(ImageView friendImag) {
            this.friendImag = friendImag;
        }

        public TextView getFriendName() {
            return friendName;
        }

        public void setFriendName(TextView friendName) {
            this.friendName = friendName;
        }


        @Override
        public String toString() {
            return "ViewHolder{" +
                    "friendImag=" + friendImag +
                    ", friendName=" + friendName +
                    '}';
        }
    }
}
