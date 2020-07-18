package com.example.demo;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button update_ac,update_psw;
    private UpdateAccountFragment updateAccountFragment;
    private UpdatePswFragment updatePswFragment;
    private LinearLayout linearLayout;
    public MeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MeFragment newInstance(String param1, String param2) {
        MeFragment fragment = new MeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_me, container, false);
        init(v);
        return v;
    }
    public void hideFragment(FragmentTransaction fragmentTransaction){
        if (updateAccountFragment != null){
            fragmentTransaction.hide(updateAccountFragment);
        }
        if(updatePswFragment != null){
            fragmentTransaction.hide(updatePswFragment);
        }
    }

    private void init(View v){
        update_ac = v.findViewById(R.id.to_update_account);
        update_psw = v.findViewById(R.id.to_update_psw);
        linearLayout = v.findViewById(R.id.me_view);
        update_ac.setOnClickListener((View view)->{
            this.setVis(View.GONE);
            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
            hideFragment(fragmentTransaction);
            if(updateAccountFragment == null){
                updateAccountFragment = new UpdateAccountFragment();
                fragmentTransaction.add(R.id.update_view_container, updateAccountFragment);
            }
            fragmentTransaction.show(updateAccountFragment);
            fragmentTransaction.commit();
        });
        update_psw.setOnClickListener((View view)->{
            this.setVis(View.GONE);
            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
            hideFragment(fragmentTransaction);
            if(updatePswFragment == null){
                updatePswFragment = new UpdatePswFragment();
                fragmentTransaction.add(R.id.update_view_container, updatePswFragment);
            }
            fragmentTransaction.show(updatePswFragment);
            fragmentTransaction.commit();
        });
    }

    public void setVis(int s){
        linearLayout.setVisibility(s);
    }
    public int getVis(){
        return linearLayout.getVisibility();
    }

}