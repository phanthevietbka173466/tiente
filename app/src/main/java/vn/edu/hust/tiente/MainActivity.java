package vn.edu.hust.tiente;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.media.VolumeShaper;
import android.os.Bundle;
import android.os.OperationCanceledException;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener , AdapterView.OnItemSelectedListener {

    TextView texresult1,texresult2;
    Spinner spinner1,spinner2;
    int state;// bien trang thai nhap o textview1 hay textview2
    int cur1,cur2;//bien loai dong tien o textview1 va textview2
    float op1,op2;//2 bien luu ket qua o textview1 và textview2
    int statedot;//bien trang thai an dau cham chuyen thap phan
    int time;
    float hs1,hs2;

    String[] items = {"Vietnam - Dong","United States - Dollar","Japan - Yen","Korean - Won",
            "Europe - Euro"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texresult1=findViewById(R.id.tv1);
        Typeface tf=Typeface.createFromAsset(getAssets(),"DS-DIGIT.TTF");
        texresult1.setTypeface(tf);

        texresult2=findViewById(R.id.tv2);
        Typeface tf1=Typeface.createFromAsset(getAssets(),"DS-DIGIT.TTF");
        texresult2.setTypeface(tf);

        spinner1=(Spinner) findViewById(R.id.spinner1);
        spinner2=(Spinner) findViewById(R.id.spinner2);

        ArrayAdapter<String> adapter= new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,items);

        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) hs1=(float)0.00004264392;
                if(position==1) hs1=1;
                if(position==2) hs1=(float)0.00925925925;
                if(position==3) hs1=(float)0.00082576383;
                if(position==4) hs1= (float) 1.09;
                op2=(op1*hs1)/hs2;
                op2=Math.round((op2*100)/100);
                texresult2.setText(String.valueOf(op2));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) hs2=(float)0.00004264392;
                if(position==1) hs2=1;
                if(position==2) hs2=(float)0.00925925925;
                if(position==3) hs2=(float)0.00082576383;
                if(position==4) hs2= (float) 1.09;
                op1=(op2*hs2)/hs1;
                op1=Math.round((op1*100)/100);
                texresult1.setText(String.valueOf(op1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        findViewById(R.id.btn0).setOnClickListener(this);
        findViewById(R.id.bnt1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);

        findViewById(R.id.CE).setOnClickListener(this);
        findViewById(R.id.BS).setOnClickListener(this);
        findViewById(R.id.doc).setOnClickListener(this);

        findViewById(R.id.tv1).setOnClickListener(this);
        findViewById(R.id.tv2).setOnClickListener(this);

        // Khoi tao cac gia tri bien
        state =0;
        time=0;
        statedot=0;
        op1=op2=0;
        cur1=cur2=0;
        texresult1.setText(String.valueOf(0));
        texresult2.setText(String.valueOf(0));

    }
    public void onClick(View view){
        int id=view.getId();
        if(id==R.id.btn0)
            adddigit(0);
        else if (id==R.id.bnt1)
            adddigit(1);
        else if (id==R.id.btn2)
            adddigit(2);
        else if (id==R.id.btn3)
            adddigit(3);
        else if (id==R.id.btn4)
            adddigit(4);
        else if (id==R.id.btn5)
            adddigit(5);
        else if (id==R.id.btn6)
            adddigit(6);
        else if (id==R.id.btn7)
            adddigit(7);
        else if (id==R.id.btn8)
            adddigit(8);
        else if (id==R.id.btn9)
            adddigit(9);
        else if (id==R.id.doc)
            changedoc();
        else if (id==R.id.CE)
            clear();
        else if (id==R.id.BS)
            clearone();
        else if(id==R.id.tv1)
            changview1();
        else if(id==R.id.tv2)
            changview2();

    }
    // An vao cac nut so
    public void adddigit(float digit){
        if(state==0&&statedot==0){
            op1=op1*10+digit;
            texresult1.setText(String.valueOf((int) op1));
            op2=(op1*hs1)/hs2;
            op2=Math.round((op2*100)/100);
            texresult2.setText(String.valueOf(op2));
        }
        else if (state==0&&statedot==1)
        {
            if(time==0){
                op1=op1+ digit/10;
                op1=Math.round((op1*100)/100);
                texresult1.setText(String.valueOf(op1));
                time=time+1;
                op2=(op1*hs1)/hs2;
                op2=Math.round((op2*100)/100);
                texresult2.setText(String.valueOf(op2));
            }
            else if(time==1){
                op1=op1+ digit/100;
                op1=Math.round((op1*100)/100);
                texresult1.setText(String.valueOf(op1));
                time=time+1;
                op2=(op1*hs1)/hs2;
                op2=Math.round((op2*100)/100);
                texresult2.setText(String.valueOf(op2));
            }
        }
        else if (state==1&&statedot==0)
        {
            op2=op2*10+digit;
            texresult2.setText(String.valueOf((int) op2));
            op1=(op2*hs2)/hs1;
            op1=Math.round((op1*100)/100);
            texresult1.setText(String.valueOf( op1));
        }
        else
        {
            if(time==0){
                op2=op2+ digit/10;
                texresult2.setText(String.valueOf(op2));
                time=time+1;
                op1=(op2*hs2)/hs1;
                op1=Math.round((op1*100)/100);
                texresult1.setText(String.valueOf( op1));
            }
            else if(time==1){
                op2=op2+ digit/100;
                texresult2.setText(String.valueOf(op2));
                time=time+1;
                op1=(op2*hs2)/hs1;
                op1=Math.round((op1*100)/100);
                texresult1.setText(String.valueOf( op1));
            }
        }
    }
    // An vao dau cham
    public void changedoc(){
        if(state==0&&statedot==0){
            texresult1.setText(String.valueOf(op1));
        }
        if(state==1&&statedot==0){
            texresult2.setText(String.valueOf(op2));
        }
        statedot=1;
    }

    // Xoa toan bo
    public void clear()
    {
        op1 = 0;
        op2 = 0;
        statedot=0;
        texresult1.setText(String.valueOf((int)op1));
        texresult1.setText(String.valueOf((int)op2));
    }

    // Xoa 1 chu
    public void clearone()
    {
        if(state==0&&statedot==0)
        {
            op1=op1/10;
            op1=(int) op1;
            texresult1.setText(String.valueOf((int) op1));
            op2=(op1*hs1)/hs2;
            op2=Math.round((op2*100)/100);
            texresult2.setText(String.valueOf(op2));
        }
        else if(state==0&&statedot==1)
        {
            if(time==2){
                op1=op1*10;
                op1=(int) op1;
                op1=op1/10;
                time=time-1;
                texresult1.setText(String.valueOf(op1));
                op2=(op1*hs1)/hs2;
                op2=Math.round((op2*100)/100);
                texresult2.setText(String.valueOf(op2));
            }
            else if(time==1){
                op1=(int) op1;
                time=time-1;
                texresult1.setText(String.valueOf(op1));
                op2=(op1*hs1)/hs2;
                op2=Math.round((op2*100)/100);
                texresult2.setText(String.valueOf(op2));
            }
            else {
                texresult1.setText(String.valueOf((int) op1));
                statedot=0;
                op2=(op1*hs1)/hs2;
                op2=Math.round((op2*100)/100);
                texresult2.setText(String.valueOf( op2));
            }
        }
        if(state==1&&statedot==0)
        {
            op2=op2/10;
            op2=(int) op2;
            texresult1.setText(String.valueOf((int) op2));
            op1=(op2*hs2)/hs1;
            op1=Math.round((op1*100)/100);
            texresult1.setText(String.valueOf( op1));
        }
        else if(state==1&&statedot==1)
        {
            if(time==2){
                op2=op2*10;
                op2=(int) op2;
                op2=op2/10;
                time=time-1;
                texresult2.setText(String.valueOf(op2));
                op1=(op2*hs2)/hs1;
                op1=Math.round((op1*100)/100);
                texresult1.setText(String.valueOf( op1));
            }
            else if(time==1){
                op2=(int) op2;
                time=time-1;
                texresult2.setText(String.valueOf(op2));
                op1=(op2*hs2)/hs1;
                op1=Math.round((op1*100)/100);
                texresult1.setText(String.valueOf( op1));
            }
            else {
                texresult2.setText(String.valueOf((int) op2));
                statedot=0;
                op1=(op2*hs2)/hs1;
                op1=Math.round((op1*100)/100);
                texresult1.setText(String.valueOf( op1));
            }
        }

    }
    public void changview1()
    {
        state=0;
        op1=0;
    }
    public void changview2()
    {
        state=1;
        op2=0;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
