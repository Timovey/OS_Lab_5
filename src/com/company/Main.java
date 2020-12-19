package com.company;

public class Main {

    public static void main(String[] args) {
	SystemCore systemCore = new SystemCore();
	systemCore.launchNotBlock();
	systemCore.launchWithBlock();
	systemCore.output();
    }
}
