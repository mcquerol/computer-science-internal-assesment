package com.isb.mateoceballos.computerscienceinternalassesment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
//import android.content.Context;


public class FirstFragment extends Fragment{
    View myView;

    Button plusbtn1, minusbtn1, plusbtn2, minusbtn2, savebtn, loadbtn;
    TextView textView1, textView2, displayVals;

    static int Item1;
    static int Item2;

    String FileName;
    String it1;
    String it2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.first_objective, container, false);

        plusbtn1 = (Button)myView.findViewById(R.id.plsubtn1);
        plusbtn2 = (Button)myView.findViewById(R.id.plusbtn1);
        minusbtn1 = (Button)myView.findViewById(R.id.minusbtn1);
        minusbtn2 = (Button)myView.findViewById(R.id.minusbtn2);

        savebtn = (Button)myView.findViewById(R.id.savebtn);
        loadbtn = (Button)myView.findViewById(R.id.loadbtn);

        textView1 = (TextView)myView.findViewById(R.id.textView1);
        textView2 = (TextView)myView.findViewById(R.id.textView2);

        displayVals = (TextView)myView.findViewById(R.id.displayVals);


        Item1 = 0;
        Item2 = 0;

        plusbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item1++;
                textView1.setText(Integer.toString(Item1));
            }
        });
        plusbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item2++;
                textView2.setText(Integer.toString(Item2));

            }
        });
        minusbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Item1 <= 0) {
                   Item1 = 0;
                   textView1.setText(Integer.toString(Item1));
                } else {
                   Item1--;
                   textView1.setText(Integer.toString(Item1));
                }
            }
        });
        minusbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Item2 <= 0) {
                    Item2 = 0;
                    textView2.setText(Integer.toString(Item2));
                } else {
                    Item2--;
                    textView2.setText(Integer.toString(Item2));
                }

            }
        });
        savebtn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   FileName = "Obj1vals";
                   it1 = textView1.getText().toString()+"\n";
                   it2 = textView2.getText().toString()+"\n";
                   try {
                       FileOutputStream fileOutputStream = getActivity().openFileOutput(FileName, Context.MODE_PRIVATE);;
                       fileOutputStream.write(it1.getBytes());
                       fileOutputStream.write(it2.getBytes());
                       fileOutputStream.close();
                       Toast.makeText(getActivity(), "Text saved to " + FileName, Toast.LENGTH_LONG).show();

                       textView1.setText("0");
                       textView2.setText("0");

                   } catch (FileNotFoundException e) {
                       e.printStackTrace();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
           });

        loadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Message;
                try {
                    FileInputStream fileInputStream = getActivity().openFileInput(FileName);
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuffer stringBuffer = new StringBuffer();
                    while((Message=bufferedReader.readLine()) !=null ){
                        stringBuffer.append(Message + "\n");
                    }
                    displayVals.setText(stringBuffer.toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });



        return myView;


    }
}
