package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;
    private ImageButton imageButton;
    private ShouyeFragment shouyeFragment;
    private GraphFragment graphFragment;
    private AddFragment addFragment;
    private LicaiFragment licaiFragment;
    private MeFragment meFragment;
    private RadioButton shou_ye;
    private MyViewModel myViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        if(getIntent() != null)
            myViewModel.setUSER_ID(getIntent().getExtras().getInt("ID"));
        init();
    }

    private void init(){
        radioGroup = findViewById(R.id.radio);
        imageButton = findViewById(R.id.add_bt);
        shou_ye = findViewById(R.id.shou_ye_rb);
        defaultFragment();
        imageButton.setOnClickListener((v)->{
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            hideFragment(fragmentTransaction);
            if(addFragment == null){
                addFragment = new AddFragment();
                fragmentTransaction.add(R.id.fragment_container_view_tag,addFragment);
            }
            else fragmentTransaction.show(addFragment);
            fragmentTransaction.commit();
        });
        radioGroup.setOnCheckedChangeListener(this);
    }
    private void defaultFragment(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideFragment(fragmentTransaction);
        if(R.id.shou_ye_rb == radioGroup.getCheckedRadioButtonId()){
            shouyeFragment = new ShouyeFragment();
            fragmentTransaction.add(R.id.fragment_container_view_tag,shouyeFragment);
        }
        else{
            shou_ye.setChecked(true);
            if(shouyeFragment == null) {
                shouyeFragment = new ShouyeFragment();
                fragmentTransaction.add(R.id.fragment_container_view_tag,shouyeFragment);
            }
            else
                fragmentTransaction.show(shouyeFragment);
        }
        fragmentTransaction.commit();
    }
    private void hideFragment(FragmentTransaction transaction){
        if(shouyeFragment != null)
            transaction.hide(shouyeFragment);
        if(graphFragment != null)
            transaction.hide(graphFragment);
        if(addFragment != null)
            transaction.hide(addFragment);
        if(licaiFragment != null)
            transaction.hide(licaiFragment);
        if(meFragment != null)
            transaction.hide(meFragment);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideFragment(fragmentTransaction);
        switch (checkedId){
            case R.id.shou_ye_rb:
                if(shouyeFragment == null) {
                    shouyeFragment = new ShouyeFragment();
                    fragmentTransaction.add(R.id.fragment_container_view_tag, shouyeFragment);
                }
                else
                    fragmentTransaction.show(shouyeFragment);
                break;
            case R.id.tu_biao_rb:
                if(graphFragment == null){
                    graphFragment = new GraphFragment();
                    fragmentTransaction.add(R.id.fragment_container_view_tag,graphFragment);
                }
                else
                    fragmentTransaction.show(graphFragment);
                break;
            case R.id.li_cai_rb:
                if(licaiFragment == null){
                    licaiFragment = new LicaiFragment();
                    fragmentTransaction.add(R.id.fragment_container_view_tag,licaiFragment);
                }
                else
                    fragmentTransaction.show(licaiFragment);
                break;
            case R.id.me_rb:
                if(meFragment == null){
                    meFragment = new MeFragment();
                    fragmentTransaction.add(R.id.fragment_container_view_tag,meFragment);
                }
                else {
                    FragmentTransaction child_transaction = meFragment.getChildFragmentManager().beginTransaction();
                    meFragment.hideFragment(child_transaction);
                    child_transaction.commit();
                    meFragment.setVis(View.VISIBLE);
                    fragmentTransaction.show(meFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed(){
        if(addFragment != null && addFragment.isVisible()){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            hideFragment(fragmentTransaction);
            switch (radioGroup.getCheckedRadioButtonId()){
                case R.id.shou_ye_rb:
                    fragmentTransaction.show(shouyeFragment);
                    break;
                case R.id.tu_biao_rb:
                    fragmentTransaction.show(graphFragment);
                    break;
                case R.id.li_cai_rb:
                    fragmentTransaction.show(licaiFragment);
                    break;
                case R.id.me_rb:
                    FragmentTransaction child_transaction = meFragment.getChildFragmentManager().beginTransaction();
                    meFragment.hideFragment(child_transaction);
                    child_transaction.commit();
                    meFragment.setVis(View.VISIBLE);
                    fragmentTransaction.show(meFragment);
                    break;
            }
            fragmentTransaction.commit();
        }else
            super.onBackPressed();
    }
}