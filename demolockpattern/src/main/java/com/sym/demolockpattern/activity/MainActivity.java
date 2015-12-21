package com.sym.demolockpattern.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.sym.demolockpattern.R;
import com.sym.demolockpattern.view.LockPatternIndicator;
import com.sym.demolockpattern.view.LockPatternView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private LockPatternView lockPatternView;
    private LockPatternIndicator lockPatternIndicator;
    private boolean isFirstDraw = true;
    private List<LockPatternView.Cell> preCells;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lockPatternView = (LockPatternView) this.findViewById(R.id.lockPatternView);
        lockPatternIndicator = (LockPatternIndicator) this.findViewById(R.id.lockPatternIndicator);
        lockPatternView.setOnPatternListener(new MyPatternListener());
    }

    private class MyPatternListener implements LockPatternView.OnPatternListener {

        @Override
        public void onPatternStart() {
        }

        @Override
        public void onPatternComplete(List<LockPatternView.Cell> cells) {
            //lockPatternView.drawError(cells);
            if(isFirstDraw){
                isFirstDraw = false;
                addToPreCells(cells);
                lockPatternIndicator.setIndicator(cells);
                lockPatternView.resumeDefault(cells);
                //preCells = cells;

            } else {
                boolean flag = check(cells);
                Log.e(TAG, String.valueOf(flag));
                if(flag){
                    //lockPatternView.drawError(cells);
                    Toast.makeText(MainActivity.this, "两次输入的一样", Toast.LENGTH_SHORT).show();
                } else {
                    lockPatternView.drawError(cells);
                }

            }

        }
    }

    private void addToPreCells(List<LockPatternView.Cell> cells){
        preCells = new ArrayList<LockPatternView.Cell>();
        for(LockPatternView.Cell cell : cells){
            preCells.add(cell);
        }
    }

    private boolean check(List<LockPatternView.Cell> cells){

        boolean flag = true;

        if(preCells.size() != cells.size()){
            flag = false;
        } else {
            for(int i = 0; i < cells.size(); i++) {
                //Log.e(TAG, "cells:" + String.valueOf(cells.get(i).getIndex()));
                if (cells.get(i).getIndex() != preCells.get(i).getIndex()){
                    flag = false;
                }
            }
        }
        return flag;
    }
}
