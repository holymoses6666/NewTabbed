package com.rodion.newtabbed;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;


public class PageFragment extends Fragment implements View.OnClickListener {

    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";

    int pageNumber;//номер страницы
    int backColor;//цвет фона


    LinearLayout punkt_layout;//layout в fragment для новых кнопок
    View[][] Punkt = new View[100][100];//view содержащий кнопки
    LayoutInflater ltInflater;

    int punktNumber;//номер пункта


    private static final String TAG = "myLogs";//тег для дебага

    Button[][] Timerr = new Button[100][100];//кнопки с таймером
    Button restartTimerBtn[][] = new Button[100][100];


    CountDownTimer yourCountDownTimer;
    public boolean canClickTimer;//можно ли нажимать на таймер
    int count;//текущий номер пункта нажатой кнопки

    boolean check;


    static PageFragment newInstance(int page) {
        PageFragment pageFragment = new PageFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);//номер страницы только при ее создании
        Random rnd = new Random();
        backColor = Color.argb(40, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        ltInflater = LayoutInflater.from(getActivity());
        canStartTimer = true;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, //метод вызывается при перелистывании
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, null);//создаем View из fragment.xml
        Log.d(TAG ,"OnCreateView");
        TextView tvPage = (TextView) view.findViewById(R.id.tvPage);//находим там textView
        tvPage.setText("Page " + pageNumber);
        tvPage.setBackgroundColor(backColor);

        Button createBtn = (Button)view.findViewById(R.id.create_btn);
        createBtn.setOnClickListener(this);

        punkt_layout = (LinearLayout)view.findViewById(R.id.punkt_layout);

        return view;
    }


    boolean[][] restartTimer = new boolean[100][100];
    boolean canStartTimer;
    @Override
    public void onClick(View view) {


        for (int i = 0; i < 100; i++) {


            if (view.getId() == i) {
                if (view.getTag() == "deleteBtn") {
                    punkt_layout.removeView(Punkt[pageNumber][i]);

                    yourCountDownTimer.cancel();
                    canStartTimer = true;
                    Timerr[pageNumber][i].setText("60");
                    restartTimer[pageNumber][i] = false;//отключаем R в нужном ряду
                }

                if (view.getTag() == "checkBtn") {
                    if(!check)
                    Punkt[pageNumber][i].setBackgroundColor(Color.parseColor("#6AB06A"));
                }
                if (view.getTag() == "Timer" && canStartTimer )
                {
                    count = i;

                    yourCountDownTimer = new CountDownTimer(60000, 1000) {
                        public void onTick(long millisUntilFinished) {
                            Timerr[pageNumber][count].setText("" + millisUntilFinished / 1000);
                            //counter++;
                        }
                        @Override
                        public void onFinish() {
                        }
                    }.start();

                    restartTimer[pageNumber][i] = true;//работоспособен только R в нужном ряду
                    canStartTimer = false;
                }

                if (view.getTag() == "restartTimerBtn" && restartTimer[pageNumber][i] )//если R в нужно ряду нажат
                {

                        yourCountDownTimer.cancel();

                    canStartTimer = true;
                    Timerr[pageNumber][i].setText("60");
                    restartTimer[pageNumber][i] = false;//отключаем R в нужном ряду
                }



            }
        }

        switch (view.getId()) {
            case R.id.create_btn://добавляем кнопку во fragment.xml в LinearLayout punkt_layout


                Punkt[pageNumber][punktNumber] = ltInflater.inflate(R.layout.punkt, punkt_layout, false);

                //кнопка удаления
                Button deleteBtn = (Button) Punkt[pageNumber][punktNumber].findViewById(R.id.delete_btn);
                deleteBtn.setId(punktNumber);
                deleteBtn.setTag("deleteBtn");
                deleteBtn.setOnClickListener(this);

                //галочка
                Button checkBtn = (Button) Punkt[pageNumber][punktNumber].findViewById(R.id.check_btn);
                checkBtn.setId(punktNumber);
                checkBtn.setTag("checkBtn");
                checkBtn.setOnClickListener(this);

                //кнопка перезапускающая таймер
                restartTimerBtn[pageNumber][punktNumber] = (Button) Punkt[pageNumber][punktNumber].findViewById(R.id.restart);
                restartTimerBtn[pageNumber][punktNumber].setId(punktNumber);
                restartTimerBtn[pageNumber][punktNumber].setTag("restartTimerBtn");
                restartTimerBtn[pageNumber][punktNumber].setOnClickListener(this);


                //кнопка запускающая таймер
                Timerr[pageNumber][punktNumber] = Punkt[pageNumber][punktNumber].findViewById(R.id.button5);
                Timerr[pageNumber][punktNumber].setId(punktNumber);
                Timerr[pageNumber][punktNumber].setTag("Timer");
                Timerr[pageNumber][punktNumber].setOnClickListener(this);





                // Timerr[pageNumber][0].setText(""+punktNumber);
                punkt_layout.addView(Punkt[pageNumber][punktNumber]);
                punktNumber++;

                break;
        }

    }

    }
