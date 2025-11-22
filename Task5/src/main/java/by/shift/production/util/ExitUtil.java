package by.shift.production.util;

import lombok.experimental.UtilityClass;

import java.util.Scanner;

@UtilityClass
public class ExitUtil {

    public void exit() {
        new Scanner(System.in).nextLine();
        System.exit(0);
    }
}
