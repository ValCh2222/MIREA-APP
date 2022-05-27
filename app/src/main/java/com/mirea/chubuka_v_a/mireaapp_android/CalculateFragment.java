package com.mirea.chubuka_v_a.mireaapp_android;

import android.app.Application;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalculateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculateFragment extends Fragment {

    private TextView result;
    private String prevNumber = "";
    private String currentNumber = "";
    private Operation prevOperation;
    private boolean operationFinished = false;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private LayoutInflater inflater;
    private ViewGroup container;
    private Bundle savedInstanceState;

    public CalculateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalculateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalculateFragment newInstance(String param1, String param2) {
        CalculateFragment fragment = new CalculateFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_calculate, container, false);
        result = (TextView) view.findViewById(R.id.textView);
        view.findViewById(R.id.button).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.button2).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.button3).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.button4).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.button5).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.button6).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.button7).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.button8).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.button10).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.button11).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.button12).setOnClickListener(this::onNumberButtonClick);
        view.findViewById(R.id.button9).setOnClickListener(this::onEqualsButtonClick);
        view.findViewById(R.id.button16).setOnClickListener(this::onDivideButtonClick);
        view.findViewById(R.id.button15).setOnClickListener(this::onMultiplicativeButtonClick);
        view.findViewById(R.id.button14).setOnClickListener(this::onSubtractButtonClick);
        view.findViewById(R.id.button13).setOnClickListener(this::onAddButtonClick);
        view.findViewById(R.id.button18).setOnClickListener(this::onClearButtonClick);

        return view;
        }


    private void onNumberButtonClick(View view){
        if(operationFinished){
            currentNumber = "";
            operationFinished = false;
        }
        Button button = (Button) view;
        if(currentNumber.equals("") && button.getText().toString().equals(".")){
            currentNumber += "0";
        }
        currentNumber += button.getText().toString();
        result.setText(currentNumber);
    }
    private void performOperation(){
        if (currentNumber.equals("")) currentNumber = "0";
        double resultNumber = 0.0;
        if (!prevNumber.equals("")) {
            if (prevOperation == Operation.PLUS) {
                resultNumber = Double.parseDouble(prevNumber)
                        + Double.parseDouble(currentNumber);

            } else if (prevOperation == Operation.DIVIDE) {
                resultNumber = Double.parseDouble(prevNumber)
                        / Double.parseDouble(currentNumber);

            } else if (prevOperation == Operation.MULTI) {
                resultNumber = Double.parseDouble(prevNumber)
                        * Double.parseDouble(currentNumber);

            } else if (prevOperation == Operation.MINUS) {
                resultNumber = Double.parseDouble(prevNumber)
                        - Double.parseDouble(currentNumber);
            }
            resultNumber = new BigDecimal(resultNumber)
                    .setScale(2, RoundingMode.HALF_EVEN). doubleValue();
            prevNumber = String.valueOf(resultNumber);
            result.setText(prevNumber);
            currentNumber = "";
            operationFinished = true;
        }
    }


    private void performAction(Operation operation){
        if(prevNumber.equals("")){
            prevOperation = operation;
            prevNumber = currentNumber;
            currentNumber = "";
            result.setText("");
        } else {
            performOperation();
            prevOperation = operation;
        }
    }

    private void onEqualsButtonClick(View view){
        performOperation();
        prevNumber = "";
        currentNumber = result.getText().toString();
    }

    private void onDeleteButtonClick(View view){
        if (currentNumber.length() > 0){
            currentNumber = currentNumber.substring(0, currentNumber.length()-1);
            result.setText(currentNumber);
        }
    }

    private void onAddButtonClick(View view){
        performAction(Operation.PLUS);
    }

    private void onSubtractButtonClick(View view){
        performAction(Operation.MINUS);
    }

    private void onDivideButtonClick(View view){
        performAction(Operation.DIVIDE);
    }

    private void onMultiplicativeButtonClick(View view){
        performAction(Operation.MULTI);
    }

    private void onClearButtonClick(View view){
        prevNumber = "";
        currentNumber = "";
        result.setText(currentNumber);
        prevOperation = null;
    }



}