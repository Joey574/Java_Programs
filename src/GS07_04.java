public class GS07_04
{
    public class CalenderDate implements Comparable<CalenderDate>
    {
        private int day;
        private int month;
        private int year;

        public CalenderDate(int year, int month, int day)
        {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        public int compareTo(CalenderDate other)
        {
            if (year != other.year)
            {
                return year - other.year;
            }
            else if (month != other.month)
            {
                return month - other.month;
            }
            else {
                return day - other.day;
            }
        }

        public int getYear()
        {
            return year;
        }

        public int getMonth()
        {
            return month;
        }

        public int getDay()
        {
            return day;
        }

        public String toString()
        {
            return month + "/" + day + "/" + year;
        }
    }
}
