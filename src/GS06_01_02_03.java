public class GS06_01_02_03 {

/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */


    public static void main(String[] args)
    {

    }

    class tickets
    {
        private int tickerNumber;
        private int days;
        private float price;

        public int getTickerNumber()
        {
            return tickerNumber;
        }

        public int getDays()
        {
            return days;
        }

        public float getPrice()
        {
            return price;
        }

        public String toString()
        {
            return "Number: " + getTickerNumber() + ", Price: " + getPrice();
        }
    }

    class advanceTicket extends tickets{

        
        public float getPrice()
        {
            float p;
            
            if (getDays() > 9)
            {
                p = 30;
            }
            else
            {
                p = 40;
            }
            return p;
        }
    }

    class studentAdvanceTicket extends advanceTicket{

        public float getPrice() {
            return super.getPrice() / 2;
        }

        public String toString()
        {
            return super.toString() + "(ID Required)";
        }
    }

    class walkUpTicket extends tickets{
        public float getPrice()
        {
            return 50;
        }
    }

}
