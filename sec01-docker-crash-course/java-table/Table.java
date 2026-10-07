public class Table {

    public static void main(String[] args) {
        String input = System.getenv("input");
        System.out.println("Received input: " + input);

        if (input == null) {
            return;
        }

        int value = Integer.parseInt(input);

        for (int i = 1; i <= value; i++) {
            System.out.printf("%d x %d = %d%n", value, i, value * i);
        }
    }
}