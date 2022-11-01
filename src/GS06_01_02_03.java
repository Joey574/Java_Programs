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
        private int days;
        private int ticketNumber;
        private boolean student;

        advanceTicket a = new advanceTicket();
        studentAdvanceTicket sA = new studentAdvanceTicket();
        walkUpTicket w = new walkUpTicket();

        public String toString()
        {
            if
        }


    }

    class advanceTicket {
        private int days;

        private int ticketNumber = 1;

        public int getTicketNumber() {
            ticketNumber++;
            return ticketNumber - 1;
        }

        public float getPrice() {
            float price = 0;

            if (days > 9) {
                price = 30;
            } else if (days < 9 && days > 0) {
                price = 40;
            }

            return price;
        }

        public String toString() {
            String ticket = "Number: " + ticketNumber + ", Price: " + getPrice();

            ticketNumber++;

            return ticket;
        }

    }

    class studentAdvanceTicket {

        private int days;

        public String toString() {
            return "Number: " + a.getTicketNumber() + ", Price: " + getPrice();
        }

        public float getPrice()
        {
            float price;

            if (days > 9)
            {
                price = 15;
            }
            else
            {
                price = 20;
            }

            return price;
        }
    }

    class walkUpTicket {
        advanceTicket a = new advanceTicket();

        public String toString()
        {
            return "Number: " + a.getTicketNumber() + ", Price: " + getPrice();
        }

        public float getPrice()
        {
            return (float) 50;
        }
    }

}

