package com.company;

import java.util.ArrayList;

public class BlockedProcess {
    private ArrayList<Process> blockedProcesses;
    public BlockedProcess(){
        blockedProcesses =  new ArrayList();
    }
    public void addProcess(Process process) {
        blockedProcesses.add(process);
        System.out.println("Процесс " + process.getId() + " заблокирован на время " + process.getTimeBlockProcess());
    }
    public ArrayList<Process> decreaseTime(int time) {
        ArrayList<Process> readyProcesses = new ArrayList<>();
        for(int i = 0; i < blockedProcesses.size();i++) {
            Process process = blockedProcesses.get(i);
            if(process.getBlock() && process.getTimeBlockProcess() <= time) {
                process.setTimeBlockProcess(0);
                readyProcesses.add(process);
                blockedProcesses.remove(i);
                i--;
            }
            else {
                process.setTimeBlockProcess(process.getTimeBlockProcess() - time);
            }
        }
        return readyProcesses;
    }
    public int getSize() {
        return blockedProcesses.size();
    }
}
