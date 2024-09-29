public class Ex1 {
    public static void main(String[] args) {
        if(args.length < 3) {
            System.out.print("Invalid expression");
            return;
        }

        double num1 = Double.parseDouble(args[0]);
        double num2 = Double.parseDouble(args[2]);

        String operator = args[1];

        switch(operator){
            case "/": System.out.print(num1 / num2);
                break;

            case "x": System.out.print(num1 * num2);
                break;

            case "^": System.out.print(Math.pow(num1, num2));
                break;

            case "+": System.out.print(num1 + num2);
                break;

            case "-": System.out.print(num1 - num2);
                break;

            default: System.out.print("Unsupported operator");
        }
    }
}