package com.example.selfdevelopmentapplication;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainHelper {
    private int positionCategory = 0,positionCharacter=0,positionSituation=0,positionPriority=0,positionComplete=0;

    public void onItemSelectedListener(Spinner spinner, final int selectedSpinner) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (selectedSpinner == 1)
                    setPositionCategory(position);
                else if(selectedSpinner==2)
                    setPositionCharacter(position);
                else if (selectedSpinner==3)
                    setPositionSituation(position);
                else if(selectedSpinner==4)
                    setPositionPriority(position);
                else if(selectedSpinner==5)
                    setPositionComplete(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setSpinnerAdapter(Context context, String[] stringArray, Spinner spinner) {
        ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.spinner_item, stringArray);
        spinner.setAdapter(arrayAdapter);
    }

    public void setPositionCategory(int position) {
        this.positionCategory = position;
    }

    public int getPositionCategory() {
        return this.positionCategory;
    }

    public int getPositionCharacter() {
        return positionCharacter;
    }

    public void setPositionCharacter(int positionCharacter) {
        this.positionCharacter = positionCharacter;
    }

    public int getPositionSituation() {
        return positionSituation;
    }

    public void setPositionSituation(int positionSituation) {
        this.positionSituation = positionSituation;
    }

    public int getPositionPriority() {
        return positionPriority;
    }

    public void setPositionPriority(int positionPriority) {
        this.positionPriority = positionPriority;
    }

    public int getPositionComplete() {
        return positionComplete;
    }

    public void setPositionComplete(int positionComplete) {
        this.positionComplete = positionComplete;
    }
}

