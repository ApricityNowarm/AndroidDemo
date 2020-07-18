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
 * Use the {@link UpdatePswFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdatePswFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText oldPsw,newPsw,cNewPsw;
    private Button certain;
    private MyViewModel myViewModel;
    public UpdatePswFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdatePswFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdatePswFragment newInstance(String param1, String param2) {
        UpdatePswFragment fragment = new UpdatePswFragment();
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
        View v = inflater.inflate(R.layout.fragment_update_psw, container, false);
        oldPsw = v.findViewById(R.id.old_psw);
        newPsw = v.findViewById(R.id.new_psw);
        cNewPsw = v.findViewById(R.id.certain_new_psw);
        certain = v.findViewById(R.id.update_psw);
        certain.setOnClickListener((View view)->{
            String old = oldPsw.getText().toString();
            String newP = newPsw.getText().toString();
            String cNewP = cNewPsw.getText().toString();
            User up_user = myViewModel.findUserByKey(myViewModel.getUSER_ID());
            if(old.isEmpty() || newP.isEmpty() || cNewP.isEmpty()){
                Toast.makeText(this.getContext(),"不能为空",Toast.LENGTH_SHORT).show();
            }else if(!up_user.getUserId().equals(old)){
                Toast.makeText(this.getContext(),"密码错误",Toast.LENGTH_SHORT).show();
            }else if(newP.contains(" ") || cNewP.contains(" ")){
                Toast.makeText(this.getContext(),"密码不能包含空格",Toast.LENGTH_SHORT).show();
            }else if(!newP.equals(cNewP)){
                Toast.makeText(this.getContext(),"两次密码不一致",Toast.LENGTH_SHORT).show();
            }else{
                up_user.setUserPassword(newP);
                myViewModel.updateAc(up_user);
                Toast.makeText(this.getContext(),"修改成功",Toast.LENGTH_SHORT).show();
                FragmentTransaction parentManager = getParentFragmentManager().beginTransaction();
                parentManager.hide(this);
                getParentFragment().getView().getRootView()
                        .findViewById(R.id.me_view).setVisibility(View.VISIBLE);
                parentManager.commit();
            }
        });
        return v;
    }
}