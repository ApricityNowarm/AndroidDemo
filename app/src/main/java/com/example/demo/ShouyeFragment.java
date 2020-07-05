package com.example.demo;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demo.databinding.FragmentShouyeBinding;

import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShouyeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShouyeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private MyViewModel myViewModel;
    private FragmentShouyeBinding shouyeBinding;
    private TextView income,expend;
    public ShouyeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShouyeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShouyeFragment newInstance(String param1, String param2) {
        ShouyeFragment fragment = new ShouyeFragment();
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
        myViewModel = new ViewModelProvider(Objects.requireNonNull(this.getActivity())).get(MyViewModel.class);
        myViewModel.getUSER_ID();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        shouyeBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_shouye,container,false);
        shouyeBinding.setSYdata(myViewModel);
        shouyeBinding.setLifecycleOwner(this);
        View view = shouyeBinding.getRoot();
        init();
        // Inflate the layout for this fragment
        return view;
    }

    private void init(){
        recyclerView = shouyeBinding.recycleView;
        income = shouyeBinding.income;
        expend = shouyeBinding.expend;
        myAdapter = new MyAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(myAdapter);
        myViewModel.getAllData(myViewModel.getUSER_ID()).observe(getViewLifecycleOwner(), new Observer<List<UserData>>() {
            @Override
            public void onChanged(List<UserData> userData) {
                myAdapter.setAllData(userData);
                myAdapter.notifyDataSetChanged();
                double incomeMoney = 0;
                double expendMoney = 0;
                for (UserData userDatum : userData) {
                    if(userDatum.getSort().equals("工资"))
                        incomeMoney += userDatum.getData();
                    else
                        expendMoney += userDatum.getData();
                }
                income.setText("收入：+" + String.valueOf(incomeMoney));
                expend.setText("支出：-" + String.valueOf(expendMoney));
            }
        });
    }
}