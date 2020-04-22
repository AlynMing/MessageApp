package com.example.teamup.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.teamup.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHome extends Fragment implements View.OnClickListener {

    private IFragmentHome mListener;

    public FragmentHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentHome.
     */
    public static FragmentHome newInstance() {
        FragmentHome fragment = new FragmentHome();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        view.findViewById(R.id.ll_friends).setOnClickListener(this);
        //view.findViewById(R.id.ll_groups).setOnClickListener(this);
        view.findViewById(R.id.ll_log_out).setOnClickListener(this);
        view.findViewById(R.id.ll_messages).setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IFragmentHome) {
            mListener = (IFragmentHome) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            /*case R.id.ll_groups: {
                mListener.onViewGroups();
                break;
            }*/
            case R.id.ll_friends: {
                mListener.onViewFriends();
                break;
            }
            case R.id.ll_messages: {
                mListener.onViewMessages();
                break;
            }
            case R.id.ll_log_out: {
                mListener.onLogout();
                break;
            }
        }
    }


    public interface IFragmentHome {
        public void onViewFriends();
        public void onLogout();
        //public void onViewGroups();
        public void onViewMessages();
    }
}
