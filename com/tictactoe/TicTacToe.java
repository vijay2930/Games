package com.tictactoe;

import java.util.Scanner;
import java.util.Timer;

public class TicTacToe {
    private String player="X";
    private boolean isX=true;
    private Scanner sc=new Scanner(System.in);

    private String board[]=new String[10];
    private void setBoard(){
        for (int i = 0; i < 10; i++) {
            board[i]="";
        }
        this.player="X";
        this.isX=true;
    }
    private void printBoard(){
        System.out.printf("%13s\n","Board");
        System.out.printf("\t  Player %s\n",player);
        for (int i = 0; i < 9; i++) {
            if(board[i].isEmpty()){
                System.out.printf(" |  %d ",i+1);
            }else {
                System.out.printf(" |  %s ", board[i]);
            }
            if((i+1)%3==0){
                System.out.println(" |");
            }
        }
    }
    private boolean checkWin(){
        int possibility[][]=new int[][]{{1,2,3},{4,5,6},{7,8,9},{1,4,7},{2,5,8},{3,6,9},{1,5,9},{3,5,7}};
        for (int i = 0; i < possibility.length; i++) {
            if(board[possibility[i][0]].equals(player) && board[possibility[i][1]].equals(player) && board[possibility[i][2]].equals(player)){
                return true;
            }
        }
        return false;
    }
    private void changePlayerState(){
        this.isX=!isX;
        this.player=isX?"X":"O";
    }
    private void play(){
        this.setBoard();
            while (true){
                this.printBoard();
                System.out.println("Enter the Position: ");
                try {
                    int pos = sc.nextInt();
                    if(this.posAvailable(pos)){
                        this.updateBoard(pos);
                    }
                    if(this.checkWin()){
                        this.printBoard();
                        System.out.printf("\nPlayer %s Win",this.player);
                        break;
                    } else if (this.checkDraw()) {
                        this.printBoard();
                        System.out.printf("\n Match Draw");
                        break;
                    } else {
                        changePlayerState();
                    }
                }catch (IndexOutOfBoundsException e){
                    System.out.println("Please Enter a Valid Position");
                }catch (NumberFormatException e){
                    System.out.println("Please enter a number");
                }
            }
    }

    private boolean checkDraw() {
        for (String s:board) {
            if(s.isEmpty()){
                return false;
            }
        }
        return true;
    }


    private void updateBoard(int pos) {
        board[pos-1]=this.player;
    }

    private boolean posAvailable(int pos) throws IndexOutOfBoundsException {
        if(board[pos-1].isEmpty()){
            return true;
        }
        return false;
    }



    public static void main(String[] args) {
        TicTacToe t= new TicTacToe();
        t.play();
    }
}
