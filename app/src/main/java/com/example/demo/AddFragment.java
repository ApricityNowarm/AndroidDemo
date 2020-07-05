package com.example.demo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import com.example.demo.databinding.FragmentAddBinding;
import org.jetbrains.annotations.NotNull;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment implements CompoundButton.OnCheckedChangeListener,View.OnClickListener {
    private CheckBox[] cb = new CheckBox[18];
    private Button[] btn = new Button[15];
    private MyViewModel myViewModel;
    private FragmentAddBinding addBinding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddFragment newInstance(String param1, String param2) {
        AddFragment fragment = new AddFragment();
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
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        addBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_add,container,false);
        addBinding.setLifecycleOwner(this);
        addBinding.setAddData(myViewModel);
        View view = addBinding.getRoot();
        init();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void init(){
        cb[0] = addBinding.yinshiCk;
        cb[1] = addBinding.gouwuCk;
        cb[2] = addBinding.jiaotongCk;
        cb[3] = addBinding.lingshiCk;
        cb[4] = addBinding.yuleCk;
        cb[5] = addBinding.shuiCk;
        cb[6] = addBinding.dianCk;
        cb[7] = addBinding.wangluoCk;
        cb[8] = addBinding.zhufangCk;
        cb[9] = addBinding.jiaoyuCk;
        cb[10] = addBinding.yuerCk;
        cb[11] = addBinding.lvxingCk;
        cb[12] = addBinding.hufuCk;
        cb[13] = addBinding.riyongCk;
        cb[14] = addBinding.xiucheCk;
        cb[15] = addBinding.chongwuCk;
        cb[16] = addBinding.gongziCk;
        cb[17] = addBinding.shejiaoCk;
        btn[0] = addBinding.zero;
        btn[1] = addBinding.one;
        btn[2] = addBinding.two;
        btn[3] = addBinding.three;
        btn[4] = addBinding.four;
        btn[5] = addBinding.five;
        btn[6] = addBinding.six;
        btn[7] = addBinding.seven;
        btn[8] = addBinding.eight;
        btn[9] = addBinding.nine;
        btn[10] = addBinding.delete;
        btn[11] = addBinding.add;
        btn[12] = addBinding.sub;
        btn[13] = addBinding.point;
        btn[14] = addBinding.certain;
        for (CheckBox checkBox : cb) {
            checkBox.setOnCheckedChangeListener(this);
        }
        for (Button button : btn) {
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked)
        for (CheckBox checkBox : cb) {
            if(checkBox != buttonView)
                checkBox.setChecked(false);
            else
                checkBox.setChecked(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.one:
                if(Objects.requireNonNull(myViewModel.getMoney().getValue()).toString().equals("0.00")){
                    myViewModel.getMoney().setValue(new StringBuilder("1"));
                }
                else
                    myViewModel.appendMoney("1");
                break;
            case R.id.two:
                if(Objects.requireNonNull(myViewModel.getMoney().getValue()).toString().equals("0.00")){
                    myViewModel.getMoney().setValue(new StringBuilder("2"));
                }
                else
                    myViewModel.appendMoney("2");
                break;
            case R.id.three:
                if(Objects.requireNonNull(myViewModel.getMoney().getValue()).toString().equals("0.00")){
                    myViewModel.getMoney().setValue(new StringBuilder("3"));
                }
                else
                    myViewModel.appendMoney("3");
                break;
            case R.id.four:
                if(Objects.requireNonNull(myViewModel.getMoney().getValue()).toString().equals("0.00")){
                    myViewModel.getMoney().setValue(new StringBuilder("4"));
                }
                else
                    myViewModel.appendMoney("4");
                break;
            case R.id.five:
                if(Objects.requireNonNull(myViewModel.getMoney().getValue()).toString().equals("0.00")){
                    myViewModel.getMoney().setValue(new StringBuilder("5"));
                }
                else
                    myViewModel.appendMoney("5");
                break;
            case R.id.six:
                if(Objects.requireNonNull(myViewModel.getMoney().getValue()).toString().equals("0.00")){
                    myViewModel.getMoney().setValue(new StringBuilder("6"));
                }
                else
                    myViewModel.appendMoney("6");
                break;
            case R.id.seven:
                if(Objects.requireNonNull(myViewModel.getMoney().getValue()).toString().equals("0.00")){
                    myViewModel.getMoney().setValue(new StringBuilder("7"));
                }
                else
                    myViewModel.appendMoney("7");
                break;
            case R.id.eight:
                if(Objects.requireNonNull(myViewModel.getMoney().getValue()).toString().equals("0.00")){
                    myViewModel.getMoney().setValue(new StringBuilder("8"));
                }
                else
                    myViewModel.appendMoney("8");
                break;
            case R.id.nine:
                if(Objects.requireNonNull(myViewModel.getMoney().getValue()).toString().equals("0.00")){
                    myViewModel.getMoney().setValue(new StringBuilder("9"));
                }
                else
                    myViewModel.appendMoney("9");
                break;
            case R.id.zero:
                if(Objects.requireNonNull(myViewModel.getMoney().getValue()).toString().equals("0.00")){
                    myViewModel.getMoney().setValue(new StringBuilder("0"));
                }
                else
                    myViewModel.appendMoney("0");
                break;
            case R.id.add:
                if(!myViewModel.getMoney().getValue().toString().startsWith("-"))
                    myViewModel.calculation();
                myViewModel.appendMoney("+");
                isBackground();
                break;
            case R.id.sub:
                if(!myViewModel.getMoney().getValue().toString().startsWith("-"))
                    myViewModel.calculation();
                myViewModel.appendMoney("-");
                isBackground();
                break;
            case R.id.delete:
                if(!myViewModel.getMoney().getValue().toString().equals("")) {
                    myViewModel.setMoney(
                            String.valueOf(myViewModel
                                    .getMoney()
                                    .getValue()
                                    .deleteCharAt(
                                            myViewModel
                                                    .getMoney()
                                                    .getValue()
                                                    .length() - 1
                                    ))
                    );
                }
                if(myViewModel.getMoney().getValue().toString().equals("")){
                    myViewModel.getMoney().setValue(new StringBuilder("0.00"));
                }
                if(!myViewModel.getMoney().getValue().toString().equals("") && myViewModel.getMoney() != null) {
                    isBackground();
                }
                break;
            case R.id.point:
                String[] split;
                String sMoney = myViewModel.getMoney()
                        .getValue()
                        .toString();
                if(sMoney.contains("+") && !sMoney.endsWith("+")){
                    split = sMoney.split("\\+");
                    if(!split[1].contains(".")){
                        myViewModel.appendMoney(".");
                    }
                }else if(sMoney.contains("-") && !sMoney.endsWith("-")){
                    split = sMoney.split("-");
                    if(!split[1].contains(".")){
                        myViewModel.appendMoney(".");
                    }
                }else {
                    if(!sMoney.contains("."))
                        myViewModel.appendMoney(".");
                }
                break;
            case R.id.certain:
                if(btn[14].getText().equals("=")){
                    myViewModel.calculation();
                    btn[14].setText("确认");
                    btn[14].setBackgroundResource(R.drawable.certain);
                    break;
                }else{
                    String sort = "其他";
                    for (CheckBox checkBox : cb) {
                        if(checkBox.isChecked()){
                            sort = checkBox.getText().toString();
                            break;
                        }
                    }
                    UserData userData = new UserData(myViewModel.getUSER_ID(),sort,
                            Double.parseDouble(
                                    myViewModel
                                            .getMoney()
                                            .getValue()
                                            .toString()
                            ),
                            LocalDate.now()
                    );
                    myViewModel.SaveData(userData);
                    myViewModel.setMoney("0.00");
                    break;
                }
        }
    }
    private void isBackground(){
        if(!myViewModel.getMoney().getValue().toString().equals("") && myViewModel.getMoney() != null) {
            if (myViewModel.getMoney().getValue().toString().contains("+")
                    || myViewModel.getMoney().getValue().toString().contains("-")) {
                btn[14].setText("=");
                btn[14].setBackgroundResource(R.drawable.bt_number);
            } else {
                btn[14].setText("确认");
                btn[14].setBackgroundResource(R.drawable.certain);
            }
        }
    }
}