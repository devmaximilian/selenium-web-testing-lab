public class Environment {

    public static boolean shouldRunHeadless() {
        String headless = System.getenv("HEADLESS");
        if (headless == null) {
            return false;
        }
        return headless.equalsIgnoreCase("true");
    }

}
