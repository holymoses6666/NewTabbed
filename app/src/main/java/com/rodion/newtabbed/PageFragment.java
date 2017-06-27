package com.rodion.newtabbed;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;



public class PageFragment extends Fragment implements View.OnClickListener {

    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";

    int pageNumber;
    int backColor;

    LinearLayout punkt_layout;
    View[][] Punkt = new View[100][100];
    LayoutInflater ltInflater;

    int deloNumber;
    int punktNumber;





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
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);//номер страницы

        Random rnd = new Random();
        backColor = Color.argb(40, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
         ltInflater = LayoutInflater.from(getActivity());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, null);//создаем View из fragment.xml

        TextView tvPage = (TextView) view.findViewById(R.id.tvPage);//находим там textView
        tvPage.setText("Page " + pageNumber);
        tvPage.setBackgroundColor(backColor);

        Button createBtn = (Button)view.findViewById(R.id.create_btn);
        createBtn.setOnClickListener(this);

        //Button deleteBtn = (Button)view.findViewById(R.id.delete_btn);
        //deleteBtn.setOnClickListener(this);



        punkt_layout = (LinearLayout)view.findViewById(R.id.punkt_layout);

        return view;
    }


    @Override
    public void onClick(View view) {

        for(int i = 0; i<100; i++) {
            if (view.getId() == i)
                punkt_layout.removeView(Punkt[pageNumber][i]);
        }

        switch (view.getId())
        {
            case R.id.create_btn://добавляем кнопку во fragment.xml в LinearLayout punkt_layout

               Punkt[pageNumber][punktNumber]  = ltInflater.inflate (R.layout.punkt, punkt_layout, false);
                Button deleteField = (Button) Punkt[pageNumber][punktNumber].findViewById(R.id.delete_btn);
                deleteField.setId(punktNumber);
                deleteField.setOnClickListener(this);
               Punkt[pageNumber][punktNumber].setBackgroundColor(Color.parseColor("#55336699"));
               punkt_layout.addView(Punkt[pageNumber][punktNumber]);
                punktNumber++;


                //Button deleteBtn = (Button)Punkt[pageNumber][punktNumber].findViewById(R.id.delete_btn);
                //deleteBtn.setId(0);
                //deleteBtn.setOnClickListener(this);

               //Punkt.setId(numberPunkt);



                   /*
                Button btn =  new Button(getActivity());
                btn.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,  LinearLayout.LayoutParams.WRAP_CONTENT));
                btn.setId(0);
                btn.setText("click!");
                punkt_layout.addView(btn);
*/
                break;


        }
    }





}