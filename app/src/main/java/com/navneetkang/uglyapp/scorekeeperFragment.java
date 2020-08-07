package com.navneetkang.uglyapp;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;

import android.preference.PreferenceFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;


public class scorekeeperFragment extends Fragment {

    int countA=0;
    int countB=0;
    int scoreA=0;
    int scoreB=0;
    int score=0;
    Bundle bundle;
    TextView teamAScore;
    TextView teamBScore;
    RadioButton wide,four,six;
    TextView result;
    TextView setRules;
    RadioGroup rg;
    RadioButton r1,r4,r6;

    MediaPlayer mp;
    TableLayout l1;
    TextView themepercent;
    SeekBar seek;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    String check;

    public scorekeeperFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    public void update()
    {


        teamAScore.setText(scoreA+"");
        teamBScore.setText(scoreB+"");
        if(check!=null)
        {
            Log.d("MyMessage",score+"");

            if(check.equals("checked"))
            {
                if(score==1)
                {
                    r1.setChecked(true);
                }
                else if(score==4)
                {
                    r4.setChecked(true);
                }
                else if(score==6)
                {
                    r6.setChecked(true);
                }

                putData();
                editor.putInt("teamA",scoreA);
                editor.putInt("teamB",scoreB);

                editor.putInt("score",score);
                editor.commit();



            }
            else
            {
                check=sharedPreferences.getString("remValue", null);
            }
        }





    }


    public void selectRadio()
    {
        switch(rg.getCheckedRadioButtonId())
        {
            case R.id.score_1:
                score=1;
                break;


            case R.id.score_4:
                score=4;
                break;


            case R.id.score_6:
                score=6;
                break;

        }

    }
    public void putData()
    {

        bundle = new Bundle();
        bundle.putInt("teamA",scoreA);
        bundle.putInt("teamB",scoreB);
        Log.d("MyMessage Put Data",score+"");
        bundle.putInt("score",score);


    }

    @Override
    public void onResume() {
        super.onResume();
        l1= getActivity().findViewById(R.id.scrollViewBackground);
        r1=getActivity().findViewById(R.id.score_1);
        r4=getActivity().findViewById(R.id.score_4);
        r6=getActivity().findViewById(R.id.score_6);

        //seek=findViewById(R.id.seekBar);
        //themepercent=findViewById(R.id.themePercent);
        sharedPreferences=getActivity().getSharedPreferences("mypref",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        //final Button activeTeamB=findViewById(R.id.active_teamB);



        Button increaseScoreA=getActivity().findViewById(R.id.team_a_increase);
        Button increaseScoreB=getActivity().findViewById(R.id.team_b_increase);
        Button decreaseScoreA=getActivity().findViewById(R.id.team_a_decrease);
        Button decreaseScoreB=getActivity().findViewById(R.id.team_b_decrease);
        rg=getActivity().findViewById(R.id.scoreChange);


        wide=getActivity().findViewById(R.id.score_1);
        four=getActivity().findViewById(R.id.score_4);
        six=getActivity().findViewById(R.id.score_6);

        teamAScore=getActivity().findViewById(R.id.teamAScore);
        teamBScore=getActivity().findViewById(R.id.teamBScore);
        int a = sharedPreferences.getInt("teamA",0);
        int b= sharedPreferences.getInt("teamB", 0);
        int c=sharedPreferences.getInt("score",0);
        Log.d("MyMessage",c+"");
        check=sharedPreferences.getString("remValue", null);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                selectRadio();
            }
        });

        Toast.makeText(getContext(),check,Toast.LENGTH_LONG).show();
        if(check!=null) {
            if (check.equals("checked")) {
                scoreA = a ;
                scoreB = b ;
                score=c;

                update();
            }
            else
            {

                update();
            }
        }






        increaseScoreA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectRadio();
                scoreA+=score;
                update();

            }
        });
        increaseScoreB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectRadio();
                scoreB+=score;
                update();
            }
        });

        decreaseScoreA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectRadio();
                scoreA-=score;
                update();


            }
        });

        decreaseScoreB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectRadio();
                scoreB-=score;

                update();

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true); /*--------------VERY IMPORTANT FOR FRAGMENT FOR MENU ----------------*/

        return inflater.inflate(R.layout.fragment_scorekeeper, container, false);
    }

    /********************MENU********************/

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflator) {



        Fragment settingsFragment=getFragmentManager()
                .findFragmentById(R.id.settings_fragment);

        if(settingsFragment == null){
            inflator.inflate(R.menu.menu_many,menu);
        }
        else{
            inflator.inflate(R.menu.my_menu,menu);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.settings:
                Toast.makeText(getActivity(), "Settings", Toast.LENGTH_SHORT).show();
                putData();

                Intent intent=new Intent(getActivity(),settingActivity.class);

                startActivity(intent);

                break;

            case R.id.info:
                Toast.makeText(getActivity(), "Info", Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(getActivity(),infoActivity.class);
                startActivity(intent1);
                break;

            default:
                Toast.makeText(getActivity(), "Default", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }







}