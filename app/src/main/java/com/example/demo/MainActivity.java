package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private int USER_ID;
    private RadioGroup radioGroup;
    private ImageButton imageButton;
    private ShouyeFragment shouyeFragment;
    private GraphFragment graphFragment;
    private AddFragment addFragment;
    private LicaiFragment licaiFragment;
    private MeFragment meFragment;
    private RadioButton shou_ye;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getIntent() != null)
            USER_ID = getIntent().getExtras().getInt("ID");
        init();
    }

    private void init(){
        radioGroup = findViewById(R.id.radio);
        imageButton = findViewById(R.id.add_bt);
        shou_ye = findViewById(R.id.shou_ye_rb);
        defaultFragment();
        imageButton.setOnClickListener((v)->{
            FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
            hideFragment(fragmentTransaction1);
            if(addFragment == null){
                addFragment = new AddFragment();
                fragmentTransaction1.add(R.id.fragment_container_view_tag,addFragment);
            }
            fragmentTransaction1.commit();
            FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
            if(addFragment != null)
                fragmentTransaction2.show(addFragment);
            Bundle bundle = new Bundle();
            bundle.putInt("ID",USER_ID);
            addFragment.setArguments(bundle);
            fragmentTransaction2.addToBackStack(null);
            fragmentTransaction2.commit();
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
        Bundle bundle = new Bundle();
        bundle.putInt("ID",USER_ID);
        shouyeFragment.setArguments(bundle);
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
                Bundle bundle = new Bundle();
                bundle.putInt("ID",USER_ID);
                shouyeFragment.setArguments(bundle);
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
                else
                    fragmentTransaction.show(meFragment);
                break;
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        FragmentManager fragmentManager = getSupportFragmentManager();
        for(int i = 0;i < fragmentManager.getBackStackEntryCount();i++)
            fragmentManager.popBackStack();
        if(addFragment != null && addFragment.isVisible()){
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
                    fragmentTransaction.show(meFragment);
                    break;
            }
            fragmentTransaction.commit();
        }else
            super.onBackPressed();
    }
}