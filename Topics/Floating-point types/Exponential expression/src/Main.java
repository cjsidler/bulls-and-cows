import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double val = scanner.nextDouble();
        double result = Math.pow(val, 3) + Math.pow(val, 2) + val + 1;
        System.out.println(result);
    }
}