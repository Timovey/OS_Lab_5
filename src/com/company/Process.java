package com.company;

import java.util.ArrayList;

public class Process {
    private final int id;
    private int timeProcess;
    private boolean block = false;
    private int timeBlockProcess = 0;

    public Process(int id, int timeProcess) {
        this.id = id;
        this.timeProcess = timeProcess;
        this.block =getRandomBlock();
    }

    public Process(Process process) {
        this.id = process.id;
        this.timeProcess = process.timeProcess;
        this.block = process.block;
        this.timeBlockProcess = process.timeBlockProcess;
    }

   public int launch(int time) {
        if (timeProcess <= time) {
            System.out.println("Процесс " + id + " завершён за время: " + timeProcess);
            int yetTime = timeProcess;
            timeProcess = 0;
            return yetTime;
        } else {
            timeProcess -= time;
            System.out.println("Процесс " + id + " не завершён" + " Осталось времени: " + timeProcess);
            return  -1;
        }
    }

    public void setBlockTrue() {
        this.block = false;
    }
    public int getTime() {
        return timeProcess;
    }

    public boolean getBlock() {
        return block;
    }

    public void createTimeBlockProcess() {
        this.timeBlockProcess = (int)(Math.random() * 30) + 20;
    }

    public void setTimeBlockProcess(int time) {
        this.timeBlockProcess -= time;
    }

    public int getTimeBlockProcess() {
        return timeBlockProcess;
    }
    private boolean getRandomBlock(){
        double r = Math.random();
        if(r < 0.65) {
            return false;
        }
        else {
            return true;
        }
    }
    public int getId(){
        return id;
    }
}
