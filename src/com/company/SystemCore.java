package com.company;

import java.util.ArrayList;

public class SystemCore {
    private ArrayList<Process> processes;
    private int timeNotInterrupt;
    private int timeWithInterrupt;
    private int quantumTime = 30;
    private BlockedProcess blockedProcess;

    public SystemCore() {
        timeNotInterrupt = 0;
        timeWithInterrupt = 0;
        processes = new ArrayList<>();
        int sizeOfProcess = getRandomNumber(5);
        System.out.println("Создано процессов: " + sizeOfProcess);
        for (int i = 0; i < sizeOfProcess; i++) {
            int timeOfProcess = getRandomTimeProcess();
            Process process = new Process(i, timeOfProcess);
            processes.add(process);
            System.out.println("Процесс " + i + " создан, Время: " + timeOfProcess);
        }
        System.out.println();
        blockedProcess = new BlockedProcess();
    }

    public void launchNotBlock() {
        ArrayList<Process> processesNI = new ArrayList<>();
        for(Process p : processes) {
            processesNI.add(new Process(p));
        }
        for(Process process: processesNI) {
            if(process.getBlock()) {
                process.createTimeBlockProcess();
            }
        }
        while (processesNI.size() > 0) {
            for (int i = 0; i < processesNI.size();i++) {
                Process pr = processesNI.get(i);
                for(Process process: processesNI) {
                    if(process.getBlock()) {
                        process.setBlockTrue();
                        timeNotInterrupt += process.getTimeBlockProcess();
                        process.setTimeBlockProcess(0);
                    }
                }
                int time = pr.launch(quantumTime);
                if(time == -1) {
                    timeNotInterrupt += quantumTime;
                }
                else {
                    timeNotInterrupt += time;
                }
                if(pr.getTime() == 0) {
                    processesNI.remove(i);
                    i--;
                }
            }
            System.out.println();
        }
        System.out.println("--------------------------------");
        System.out.println();
        System.out.println("С блокировкой: ");
    }

    public void launchWithBlock() {
        ArrayList<Process> processesWI = new ArrayList<>();
        for(Process p : processes) {
            processesWI.add(new Process(p));
        }
        for(Process process: processesWI) {
            if(process.getBlock()) {
                process.createTimeBlockProcess();
            }
        }
        while (processesWI.size() > 0 || blockedProcess.getSize() > 0) {
            ArrayList<Process> blockPr;
            for (int i = 0; i < processesWI.size();i++) {
                Process process = processesWI.get(i);
                int time;

                if(process.getBlock()) {
                    blockedProcess.addProcess(process);
                    processesWI.remove(i);
                    i--;
                }
                else {
                    time = process.launch(quantumTime);
                    if(time == -1) {
                        timeWithInterrupt += quantumTime;
                        blockPr = blockedProcess.decreaseTime(quantumTime);
                    }
                    else {
                        timeWithInterrupt += time;
                        blockPr = blockedProcess.decreaseTime(time);
                    }
                    if(process.getTime() == 0) {
                        processesWI.remove(i);
                        i--;
                    }
                     for(Process pr : blockPr) {
                         System.out.println("Процесс " + pr.getId() + " возвратился");
                         processesWI.add(i + 1,pr);
                         pr.setBlockTrue();
                     }
                }

            }
            if(processesWI.size() == 0) {
                blockPr = blockedProcess.decreaseTime(quantumTime);
                for(Process pr : blockPr) {
                    System.out.println("Процесс " + pr.getId() + " возвратился");
                    processesWI.add(pr);
                    pr.setBlockTrue();
                }
            }
            System.out.println();
        }

    }

    public void output() {
        System.out.println("Время без блокировки: " + timeNotInterrupt);
        System.out.println("Время с блокировкой: " + timeWithInterrupt);
    }
    private int getRandomTimeProcess() {
        return (int) (Math.random() * 80) + 10;
    }

    private int getRandomNumber(int number) {
        return (int) (Math.random() * number) + 5;
    }
}
