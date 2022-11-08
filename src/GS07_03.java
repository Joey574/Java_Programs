public class GS07_03
{
    public class Point implements Comparable<Point>
    {
        private int x;
        private int y;

        public Point()
        {
            this(0, 0);
        }

        public Point (int x, int y)
        {
            setLocation(x, y);
        }

        public double distanceFromOrigin()
        {
            return Math.sqrt(x * x + y * y);
        }

        public int getX()
        {
            return x;
        }

        public int getY()
        {
            return y;
        }

        public void setLocation(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        public String toString()
        {
            return "(" + x + ", " + y + ")";
        }

        public void translate(int dx, int dy)
        {
            setLocation(x + dx, y + dy);
        }

        public int compareTo(Point p2)
        {
            if (y != p2.y)
            {
                return y - p2.y;
            }
            else
            {
                return x - p2.x;
            }
        }
    }


}
