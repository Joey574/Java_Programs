public class ThreadClasses
{
    public static class MainThread extends Thread
    {
        private String firstWord;
        private String secondWord;
        private String threadName;

        MainThread(String firstWord, String secondWord, String threadName) {
            this.firstWord = firstWord;
            this.secondWord = secondWord;
            this.threadName = threadName;
        }

        public void run() {
            try {

            } catch (Exception e) {
                System.out.println(threadName + " error: " + e.getCause() + " :: " + e);
            }
        }
    }
}
