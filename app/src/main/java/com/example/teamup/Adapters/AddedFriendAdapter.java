package com.example.teamup.Adapters;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamup.R;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.List;

public class AddedFriendAdapter extends RecyclerView.Adapter<AddedFriendAdapter.ViewHolder> {

    private List<ParseUser> users;

    public AddedFriendAdapter(List<ParseUser> users) {
        this.users = users;
    }

    public List<ParseUser> users() {
        return users;
    }

    public boolean containsUser(String targetUsername) {
        for (ParseUser addedFriend : this.users) {
            String username = addedFriend.getUsername();
            if (TextUtils.equals(username, targetUsername)) {
                return true;
            }
        }

        return false;
    }

    public void updateUser(List<ParseUser> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.added_friend_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ParseUser parseUser = this.users.get(position);
        String fname = (String) parseUser.get("fname");
        String lname = (String) parseUser.get("lname");

        holder.tvFirstName.setText(fname);
        holder.tvLastName.setText(lname);
    }

    @Override
    public int getItemCount() {
        if (users == null) return 0;
        return users.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvFirstName;
        TextView tvLastName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvFirstName = itemView.findViewById(R.id.tv_first_name);
            tvLastName = itemView.findViewById(R.id.tv_last_name);
        }
    }
}
