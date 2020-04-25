package com.example.teamup.Activities;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamup.R;
import com.parse.ParseObject;
import java.util.List;

public class AddFriendRVAdapter extends RecyclerView.Adapter<AddFriendRVAdapter.MViewHolder> {
    private static final String TAG = "AddFriendRVAdapter";
    private Context context;
    private List<ParseObject> users;
    private IAddFriendRVAdapter iListener;
    private boolean isAddFriendList;        // Is it coming from addFirendList or addedFirendList

    public AddFriendRVAdapter(Context ctx, List<ParseObject> users, IAddFriendRVAdapter iaf, boolean isAddFriendList) {
        this.context = ctx;
        this.users = users;
        this.iListener = iaf;
        this.isAddFriendList = isAddFriendList;
    }
    @NonNull
    @Override
    public MViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.friend_item, parent, false);
        return new MViewHolder(view);
    }

    public void removeUser(ParseObject user) {
        this.users.remove(user);
        notifyDataSetChanged();
    }

    public void updateUsersList(List<ParseObject> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull MViewHolder holder, int position) {
        ParseObject po = users.get(position);
        Log.d(TAG, "friendID: " + po.getObjectId());
        holder.tvNames.setText(String.format("%s %s", po.get("fname"), po.get("lname")));
    }

    @Override
    public int getItemCount() {
        int count = (null == users) ? 0 : users.size();
        return count;
    }

    class MViewHolder extends RecyclerView.ViewHolder {
        TextView tvNames;
        ImageView ivAddFriend;
        public MViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNames = itemView.findViewById(R.id.tv_names);
            ivAddFriend = itemView.findViewById(R.id.iv_add_friend);
            ivAddFriend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = getAdapterPosition();
                    iListener.onAddFriend(users.get(i));
                }
            });

            if (!isAddFriendList) {
                ivAddFriend.setVisibility(View.GONE);
            }
        }
    }

    public interface IAddFriendRVAdapter {
        void onAddFriend(ParseObject user);

    }
}
