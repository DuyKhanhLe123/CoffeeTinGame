package a1_2001060033;

import java.util.Arrays;

public class CoffeeTinGame {

    public static final char[] BeansBag;




    static {
        // Initialize BeansBag with one-third blue beans (B), one-third green beans (G),
        // and one-third empty spaces (-)
        BeansBag = new char[30];
        int beanCount = BeansBag.length / 3;
        for (int i = 0; i < beanCount; i++) {
            BeansBag[i] = 'B'; // Blue beans
            BeansBag[i + beanCount] = 'G'; // Green beans
            BeansBag[i + 2 * beanCount] = '-'; // Empty spaces
        }
    }

    // (b) Change existing procedures to public
    public static void tinGame(char[] tin) {
        while (atLeastTwoBeansInTin(tin)) {
            char[] beans = takeTwo(tin);
            updateTin(tin, beans);
            System.out.println("Takes out" + Arrays.toString(beans));
            System.out.println("Tin updated: " + Arrays.toString(tin) + "\n");
        }
        char lastBean = getLastBean(tin);
        System.out.println("Last bean remaining in the tin: " + lastBean);
        System.out.println("Game Over.");
    }

    public static char takeOne(char[] tin) {
        int index = randInt(tin.length - 1);
        while (tin[index] =='-') index = randInt(tin.length-1);
        char bean = tin[index] ;
        tin[index] = '-';
        return bean;
    }

    // (c) New public procedures
    public static int randInt(int n) {
        return (int)(Math.random() * (n + 1));
    }

    public static char getBean(char[] beansBag, char beanType) {
        int index;
        do {
            index = randInt(beansBag.length - 1);
        } while (beansBag[index] != beanType);
        char bean = beansBag[index];
        beansBag[index] = '-';
        return bean;
    }

    public static void updateTin(char[] tin, char[] beans) {
        if (beans[0] == beans[1]) {
            putBlueBean(tin);
        } else {
            putGreenBean(tin);
        }
    }

    // Helper methods
    private static boolean atLeastTwoBeansInTin(char[] tin) {
        int count = 0;
        for (char bean : tin) {
            if (bean != '-')
                count++;
        }
        return count >= 2;
    }

    private static char[] takeTwo(char[] tin) {
        char[] beans = new char[2];
        beans[0] = takeOne(tin);
        beans[1] = takeOne(tin);
        return beans;
    }

    private static void throwAwayBeans(char[] tin, char[] beans) {
        for (int i = 0; i < tin.length; i++) {
            if (tin[i] == beans[0] || tin[i] == beans[1]) {
                tin[i] = '-';
            }
        }
    }

    private static void throwAwayBlueBean(char[] tin, char[] beans) {
        for (int i = 0; i < tin.length; i++) {
            if (tin[i] == 'B') {
                tin[i] = '-';
                break;
            }
        }
    }

    private static void putBlueBean(char[] tin) {
        for (int i = 0; i < BeansBag.length; i++) {
            if (BeansBag[i] == 'B') {
                BeansBag[i] = '-';
                break;
            }
        }

        for (int i = 0; i < tin.length; i++) {
            if (tin[i] == '-') {
                tin[i] = 'B';
                break;
            }
        }
    }

    private static void putGreenBean(char[] tin) {
        for (int i = 0; i < tin.length; i++) {
            if (tin[i] == '-') {
                tin[i] = 'G';
                break;
            }
        }
    }

    private static char getLastBean(char[] tin) {
        for (char bean : tin) {
            if (bean != '-')
                return bean;
        }
        return '-';
    }

    public static void main(String[] args) {
        // Example usage
        char[] tin = {'B','G','B','G','G','G'};
        System.out.println("Initial contents of the tin: " + Arrays.toString(tin));
        tinGame(tin);
    }

    private static void fillTinWithBeans(char[] tin) {
        for (int i = 0; i < tin.length; i++) {
            tin[i] = getBean(BeansBag, 'B'); // Fill tin with blue beans
        }
    }
}
