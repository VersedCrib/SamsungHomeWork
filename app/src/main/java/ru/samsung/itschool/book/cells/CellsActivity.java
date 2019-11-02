package ru.samsung.itschool.book.cells;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.HeaderViewListAdapter;


import java.util.concurrent.TimeUnit;

import task.Stub;
import task.Task;

public class CellsActivity extends Activity implements OnClickListener,
        OnLongClickListener {

    private int WIDTH = 4;
    private int HEIGHT = 6;
    private Button[][] cells;

    Field field = new Field(WIDTH, HEIGHT,2);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cells);
        makeCells();

        generate();

    }

    void generate() {

        //Эту строку нужно удалить
        //Task.showMessage(this, "Добавьте код в функцию активности generate() для генерации клеточного поля");


       /* int num = 1;
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++) {
                cells[i][j].setText(num + "");
                num++;
            }*/
    }

    @Override
    public boolean onLongClick(View v) {
        Button tappedCell = (Button) v;
        Integer tappedX = getX(tappedCell);
        Integer  tappedY = getY(tappedCell);

        if(field.flag) {
            if (!field.ar_field[tappedY][tappedX].open) {
                if (!field.ar_field[tappedY][tappedX].lock) {
                    cells[tappedY][tappedX].setBackgroundColor(Color.GREEN);
                    field.ar_field[tappedY][tappedX].lock = true;
                } else {
                    cells[tappedY][tappedX].setBackgroundColor(Color.WHITE);
                    field.ar_field[tappedY][tappedX].lock = false;
                }
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        Button tappedCell = (Button) v;

        Integer tappedX = getX(tappedCell);
        Integer  tappedY = getY(tappedCell);

        if(field.flag) {
            if (!field.ar_field[tappedY][tappedX].lock) {
                if(!field.ar_field[tappedY][tappedX].bombs) {
                    cells[tappedY][tappedX].setText(field.ar_field[tappedY][tappedX].num.toString());
                    field.ar_field[tappedY][tappedX].open = true;
                } else {
                    cells[tappedY][tappedX].setText(field.ar_field[tappedY][tappedX].num.toString());
                    cells[tappedY][tappedX].setBackgroundColor(Color.RED);
                    field.flag = false;
                }
            }
        }
    }

	/*
     * NOT FOR THE BEGINNERS
	 * ==================================================
	 */

    int getX(View v) {
        return Integer.parseInt(((String) v.getTag()).split(",")[1]);
    }

    int getY(View v) {
        return Integer.parseInt(((String) v.getTag()).split(",")[0]);
    }

    void makeCells() {
        cells = new Button[HEIGHT][WIDTH];
        GridLayout cellsLayout = (GridLayout) findViewById(R.id.CellsLayout);
        cellsLayout.removeAllViews();
        cellsLayout.setColumnCount(WIDTH);
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                cells[i][j] = (Button) inflater.inflate(R.layout.cell, cellsLayout, false);
                cells[i][j].setOnClickListener(this);
                cells[i][j].setOnLongClickListener(this);
                cells[i][j].setTag(i + "," + j);
                cellsLayout.addView(cells[i][j]);
            }
    }



    public class Field{
        boolean flag = true;
        int width, height;
        int bombs;
        Cell ar_field[][];

        public Field(int width, int height, int bombs){
            this.width = width;
            this.height = height;
            this.bombs = bombs;
            this.ar_field = new Cell[height][width];
            enter_cell();
            enter_bombs();
            enter_num();
        }

        private void enter_cell(){
            for(int i = 0; i<this.height; i++){
                for(int j = 0; j<this.width; j++){
                    ar_field[i][j] = new Cell(false,-1);
                }
            }
        }

        private void enter_bombs(){
            int bombs = this.bombs;
            int x,y;

            while (bombs!=0){
                x = rand(this.width-1);
                y = rand(this.height-1);
                if(!this.ar_field[y][x].bombs){
                    this.ar_field[y][x].bombs =true;
                    bombs--;
                }
            }

        }

        private void enter_num(){
            for(int i = 0; i<=this.height; i++ ){
                for(int j = 0 ; j<=this.width; j++ ){
                    if(check(i,j)){
                        if(!ar_field[i][j].bombs){
                            ar_field[i][j].num = count_bombs(i,j);
                        }
                    }
                }
            }
        }

        public Integer count_bombs(int y, int x){
            int count = 0;

            for(int i = y-1; i<=(y+1); i++ ){
                for(int j = x-1; j<=(x+1); j++ ){
                    if(check(i,j)){
                        if(ar_field[i][j].bombs){
                            count++;
                        }
                    }
                }
            }

            return count;
        }

        private boolean check(int y, int x){
            boolean b = true;

            if(x>(width-1)|| y>(height-1) || x<0 || y<0){
                b = false;
            }
            return b;
        }

        private int rand(int x){
            return (int)(Math.random()*x);
        }

        private class Cell{
            boolean bombs ;
            Integer num ;
            boolean lock = false;
            boolean open = false;
            public Cell(boolean bombs, int num){
                this.bombs = bombs;
                this.num = num;
            }
        }


    }


}