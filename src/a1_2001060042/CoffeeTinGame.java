package a1_2001060042;

import java.util.Arrays;
import java.util.Random;

public class CoffeeTinGame {
    private static final char[] BeansBag = new char[30];
    private static final Random random = new Random();

    private static final char GREEN = 'G';

    private static final char BLUE = 'B';

    private static final char REMOVED = '-';




    static {
        int i = 0;
        for (; i < BeansBag.length / 3; i++) BeansBag[i] = BLUE; // Blue beans
        for (; i < 2 * (BeansBag.length / 3); i++) BeansBag[i] = GREEN; // Green beans
        for (; i < BeansBag.length; i++) BeansBag[i] = '-'; // Empty spaces
    }

    public static int randInt(int n) {
        return random.nextInt(n);
    }

    /**
     * @requires tin has at least one bean
     * @modifies tin
     * @effects
     *   remove any bean from tin and return it
     */
    public static char takeOne(char[] tin) {
        int index = randInt(tin.length);

        while (tin[index] == REMOVED) index = randInt(tin.length);

        char bean = tin[index];
        tin[index] = REMOVED; // Replace the bean with '-'
        return bean;
    }

    public static char getBean(char beanType) {
        for (int i = 0; i < BeansBag.length; i++) {
            if (BeansBag[i] == beanType) {
                BeansBag[i] = REMOVED; // Remove the bean
                return beanType;
            }
        }
        return '-';
    }
    /**
     * @requires tin has vacant positions for new beans
     * @modifies tin
     * @effects
     *   place bean into any vacant position in tin
     */
    public static void putBean(char bean, char[] tin) {

        for (int i = 0; i < tin.length; i++) {
            if (tin[i] == REMOVED) {
                tin[i] = bean;
                break;
            }

        }
    }
    /**
     * Performs the coffee tin game to determine the colour of the last bean
     *
     * @requires tin is not null /\ tin.length > 0
     * @modifies tin
     * @effects <pre>
     *   take out two beans from tin
     *   if same colour
     *     throw both away, put one blue bean back
     *   else
     *     put green bean back
     *   let p0 = initial number of green beans
     *   if p0 = 1
     *     result = `G'
     *   else
     *     result = `B'
     *   </pre>
     */
    public static void updateTin(char[] tin, char bean1, char bean2) {
        System.out.println("Took out: " + bean1 + " and " + bean2);
        if (bean1 == bean2) {
            char newBean = getBean(BLUE);
            putBean(newBean,tin);
            System.out.println("Both beans are the same. Threw them away and added a blue bean to the tin.");
        } else {
            if (bean1 == GREEN) putBean(bean1, tin);
            else putBean(bean2,tin);

            System.out.println("Beans are different. Threw away a blue one and added a green bean to the tin.");

        }
        // Print the current state of the tin
        System.out.print("Current state of the tin: ");
        for (char bean : tin) {
            System.out.print(bean + " ");
        }
        System.out.println();
    }

    public static void tinGame(char[] tin) {

        System.out.println(Arrays.toString(tin));

        while (atLeastTwo(tin)) {
            char bean1 = takeOne(tin);

            char bean2 = takeOne(tin);

            updateTin(tin, bean1, bean2);
        }

        char last = '-';
        for (char ch : tin) {
            if (ch != REMOVED) {
                last = ch;
                break;
            }
        }
            System.out.println("The last bean in the tin is " + last);
            System.out.println("Game Over.");

    }
    /**
     * @effects
     *  if tin has at least two beans
     *    return true
     *  else
     *    return false
     */
    public static boolean atLeastTwo(char[] tin) {
        int count = 0;
        for (char ch : tin) {
            if (ch != REMOVED) count++;
        }

        return count >= 2;
    }
    public static void main(String[] args) {
        char[] tin = {REMOVED, GREEN, BLUE, GREEN, BLUE}; // Initial tin setup
        tinGame(tin);
    }
}
