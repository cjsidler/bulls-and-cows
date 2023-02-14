import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        double p = (a + b + c) / 2;
        double radicand = p * (p - a) * (p - b) * (p - c);
        System.out.println(Math.sqrt(radicand));
    }
}