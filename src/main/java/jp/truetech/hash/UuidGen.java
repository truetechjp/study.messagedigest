package jp.truetech.hash;

import java.util.UUID;

public class UuidGen {

    public static void main(String[] args) {
        int count = 1;
        if (args.length >= 1) {
            try {
                count = Integer.parseInt(args[0]);
            }
            catch (Exception ignore) {}
        }

        for (int i = 0; i < count; i++) {
            System.out.println(UUID.randomUUID().toString());
        }
    }

}
