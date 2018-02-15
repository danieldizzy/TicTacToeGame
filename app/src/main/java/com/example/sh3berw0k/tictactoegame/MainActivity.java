package com.example.sh3berw0k.tictactoegame;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    TextView welcome_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        welcome_text = (TextView) findViewById(R.id.welcome_text);
    }
    //Added this code for the Menu icons
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        //Adding the search view
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getApplicationContext(), query, Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.ic_accessible_black:
                Toast.makeText(this, "No Discriminarion", Toast.LENGTH_LONG).show();
                return true;
            case R.id.ic_bluetooth:
                Toast.makeText(this, "Set Bluetooth", Toast.LENGTH_LONG).show();
                return true;
            case R.id.take_off:
                Toast.makeText(this, "Get Ready Take Off", Toast.LENGTH_LONG).show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void buttonClick(View view) {
        Button buttonSelected = (Button) view;
        //buttonSelected.setBackgroundColor(Color.BLACK);
        //Identify which button is clicked
        int buttonCellID = 0;
        switch ((buttonSelected.getId())) {

            case R.id.button1:
                buttonCellID = 1;
                break;

            case R.id.button2:
                buttonCellID = 2;
                break;

            case R.id.button3:
                buttonCellID = 3;
                break;

            case R.id.button4:
                buttonCellID = 4;
                break;

            case R.id.button5:
                buttonCellID = 5;
                break;

            case R.id.button6:
                buttonCellID = 6;
                break;

            case R.id.button7:
                buttonCellID = 7;
                break;

            case R.id.button8:
                buttonCellID = 8;
                break;

            case R.id.button9:
                buttonCellID = 9;
                break;
        }
        PlayGame(buttonCellID, buttonSelected);
    }

    int ActivePlayer = 1; //1 for First player, 2 for Second player
    ArrayList<Integer> Player1 = new ArrayList<Integer>(); //Holds Player 1 data
    ArrayList<Integer> Player2 = new ArrayList<Integer>(); //Holds Player 2 data
    void PlayGame(int buttonCellID, Button buttonSelected) {
        //buttonSelected.setBackgroundColor(Color.BLACK);
        Log.d("Player : ", String.valueOf(buttonCellID));

        if (ActivePlayer == 1) {
            buttonSelected.setText("X");
            buttonSelected.setTextColor(Color.WHITE);
            buttonSelected.setTextSize(50);
            buttonSelected.setBackgroundColor(Color.BLACK);
            Player1.add(buttonCellID);
            ActivePlayer = 2;
            AutoPlay();
        }
        else if (ActivePlayer == 2) {
            buttonSelected.setText("O");
            buttonSelected.setTextColor(Color.BLACK);
            buttonSelected.setTextSize(50);
            buttonSelected.setBackgroundColor(Color.YELLOW);
            Player2.add(buttonCellID);
            ActivePlayer = 1;
        }
        buttonSelected.setEnabled(false); //Prevents another player to select the same button twice
        CheckWinner();         //Call the Check Winner Method to check the winner of the game

    }

    void CheckWinner() {
        int Winner = -1;
        //ROW 1
        if (Player1.contains(1) && Player1.contains(2) && Player1.contains(3)) {
            Winner = 1;
        }
        if (Player2.contains(1) && Player2.contains(2) && Player2.contains(3)) {
            Winner = 2;
        }
        //ROW 2
        if (Player1.contains(4) && Player1.contains(5) && Player1.contains(6)) {
            Winner = 1;
        }
        if (Player2.contains(4) && Player2.contains(5) && Player2.contains(6)) {
            Winner = 2;
        }
        //ROW 3
        if (Player1.contains(7) && Player1.contains(8) && Player1.contains(9)) {
            Winner = 1;
        }
        if (Player2.contains(7) && Player2.contains(8) && Player2.contains(9)) {
            Winner = 2;
        }
        //COL 1
        if (Player1.contains(1) && Player1.contains(4) && Player1.contains(7)) {
            Winner = 1;
        }
        if (Player2.contains(1) && Player2.contains(4) && Player2.contains(7)) {
            Winner = 2;
        }
        //COL 2
        if (Player1.contains(2) && Player1.contains(5) && Player1.contains(8)) {
            Winner = 1;
        }
        if (Player2.contains(2) && Player2.contains(5) && Player2.contains(8)) {
            Winner = 2;
        }
        //COL 3
        if (Player1.contains(3) && Player1.contains(6) && Player1.contains(9)) {
            Winner = 1;
        }
        if (Player2.contains(3) && Player2.contains(6) && Player2.contains(9)) {
            Winner = 2;
        }
        //DIAG 1
        if (Player1.contains(1) && Player1.contains(5) && Player1.contains(9)) {
            Winner = 1;
        }
        if (Player2.contains(1) && Player2.contains(5) && Player2.contains(9)) {
            Winner = 2;
        }
        //DIAG 2
        if (Player1.contains(3) && Player1.contains(5) && Player1.contains(7)) {
            Winner = 1;
        }
        if (Player2.contains(3) && Player2.contains(5) && Player2.contains(7)) {
            Winner = 2;
        }
        if (Winner != -1) {
            if (Winner == 1) {
                Toast.makeText(this, " Player 1 is the Winner", Toast.LENGTH_LONG).show();
            } else if (Winner == 2) {
                Toast.makeText(this, "Player 2 is the Winner", Toast.LENGTH_LONG).show();
            }
        }

    }


    void AutoPlay(){
        ArrayList<Integer> EmptyCells = new ArrayList<Integer>(); //All unselected cells
        //Find Empty Cells, at the end of this loop all the empty cells will be in the ArrayList comprehension
        for (int cellID = 1; cellID<10; cellID++) {
            //if the item is not used by Player one or Player 2 we will take it an put it in the arraylist
            if (!(Player1.contains(cellID) || Player2.contains(cellID))) {
                EmptyCells.add(cellID);

            }
        }
        Random r = new Random();

        //Finding the random value between the range of Maximum Value to the Minimum Value so we are selecting the Radom Index between Zero and
        //Example if the size  = 3, Select (0, 1, 2)
        int RandIndex = r.nextInt( EmptyCells.size() + 0) + 0;
        //int RandIndex = r.nextInt( EmptyCells.size());
        int buttonCellID = EmptyCells.get(RandIndex); //This will select the cell ID randomly between any empty cells

        //Defining the button used by the Empty cells

        Button buttonSelected;
        //buttonSelected.setBackgroundColor(Color.BLACK);
        //Identify which button is clicked
        switch (buttonCellID) {

            case 1:
                buttonSelected = (Button) findViewById(R.id.button1);
                break;

            case 2:
                buttonSelected = (Button) findViewById(R.id.button2);
                break;

            case 3:
                buttonSelected = (Button) findViewById(R.id.button3);
                break;

            case 4:
                buttonSelected = (Button) findViewById(R.id.button4);
                break;

            case 5:
                buttonSelected = (Button) findViewById(R.id.button5);
                break;

            case 6:
                buttonSelected = (Button) findViewById(R.id.button6);
                break;

            case 7:
                buttonSelected = (Button) findViewById(R.id.button7);
                break;

            case 8:
                buttonSelected = (Button) findViewById(R.id.button8);
                break;

            case 9:
                buttonSelected = (Button) findViewById(R.id.button9);
                break;
            default:
          buttonSelected = (Button) findViewById(R.id.button1);
//             break;
        }
        PlayGame(buttonCellID, buttonSelected);



    }
}
