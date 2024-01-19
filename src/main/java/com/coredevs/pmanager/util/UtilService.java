package com.coredevs.pmanager.util;

import java.util.Random;

public class UtilService {

    private UtilService() {
    }

    public static int getRandomSixDigit() {
        Random rnd = new Random();
        int number = rnd.nextInt(900000) + 100000;
        return number;
    }

}
