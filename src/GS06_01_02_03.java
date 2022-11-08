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

    class Ticket
    {
        private int number;

        Ticket(int number)
        {
            this.number = number;
        }

        public float getPrice()
        {
            return 0;
        }

        public String toString()
        {
            return "Number: " + number + ", Price: " + getPrice();
        }
    }

    class advanceTicket extends Ticket{

        int days;

        advanceTicket(int number, int days)
        {
            super(number);
            this.days = days;
        }

        public float getPrice()
        {
            float p;
            
            if (days > 9)
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

        studentAdvanceTicket(int number, int days) {
            super(number, days);
        }

        public float getPrice() {
            return super.getPrice() / 2;
        }

        public String toString()
        {
            return super.toString() + " (ID Required)";
        }
    }

    class walkUpTicket extends Ticket{
        walkUpTicket(int number) {
            super(number);
        }

        public float getPrice()
        {
            return 50;
        }
    }

}
