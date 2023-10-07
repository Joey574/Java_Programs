public class playground {
    public static String season (int mon, int day) {
        if (mon >= 9) {
            if (mon == 12 && day > 15) {
                return "winter";
            }
            return "fall";
        } else if (mon >= 6) {
            if (mon == 6 && day < 16) {
                return "spring";
            }
            return "summer";
        } else if (mon >= 3) {
            if (mon == 3 && day < 16) {
                return "winter";
            }
            return "spring";
        } else {
            return "winter";
        }
    }

    public static void main(String[] args) {
        System.out.println(season(3, 15));
    }

}
