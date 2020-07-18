package com.example.demo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateAccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateAccountFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MyViewModel myViewModel;
    private Button update;
    private EditText account;

    public UpdateAccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdateAccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateAccountFragment newInstance(String param1, String param2) {
        UpdateAccountFragment fragment = new UpdateAccountFragment();
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
        myViewModel = new ViewModelProvider(this.getActivity()).get(MyViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_update_account, container, false);
        update = v.findViewById(R.id.certain_up_ac);
        account = v.findViewById(R.id.ac_edit);
        update.setOnClickListener((View view) -> {
            String s = account.getText().toString();
            User user = myViewModel.FindUser(s);
            User updateAc = myViewModel.findUserByKey(myViewModel.getUSER_ID());
            if (user != null && user.getId() != myViewModel.getUSER_ID()) {
                Toast.makeText(this.getContext(), "账号已存在", Toast.LENGTH_SHORT).show();
            } else {
                if (s.contains(" "))
                    Toast.makeText(this.getContext(), "账号不能包含空格", Toast.LENGTH_SHORT).show();
                else if (user !=null && user.getUserId().equals(updateAc.getUserId())) {
                    Toast.makeText(this.getContext(),"与上次账号一致",Toast.LENGTH_SHORT).show();
                } else if(s == null || s.equals("") || s.isEmpty()){
                    Toast.makeText(this.getContext(),"账号不能为空",Toast.LENGTH_SHORT).show();
                }
                else{
                    updateAc.setUserId(s);
                    myViewModel.updateAc(updateAc);
                    Toast.makeText(this.getContext(), "修改成功", Toast.LENGTH_SHORT).show();
                    FragmentTransaction parentManager = getParentFragmentManager().beginTransaction();
                    parentManager.hide(this);
                    getParentFragment().getView().getRootView()
                            .findViewById(R.id.me_view).setVisibility(View.VISIBLE);
                    parentManager.commit();
                }
            }
        });
        return v;
    }
}