public class Ch_01_Project_04
{
    public static void dayOfX(int x)
    {
        System.out.print("On the " + x);

        if (x == 1)
        {
            System.out.print("st");
        }
        else if (x == 2)
        {
            System.out.print("nd");
        }
        else if (x == 3)
        {
            System.out.print("rd");
        }
        else
        {
            System.out.print("th");
        }
        System.out.println(" day of Christmas,");

        System.out.println("my true love sent to me");
    }

    public static void pearTree()
    {
        System.out.println("a partridge in a pear tree.");
    }

    public static void turtDoves()
    {
        System.out.println("two turtle doves, and");
    }

    public static void hens()
    {
        System.out.println("three French hens,");
    }

    public static void birds()
    {
        System.out.println("four calling birds,");
    }

    public static void rings()
    {
        System.out.println("five golden rings,");
    }

    public static void geese()
    {
        System.out.println("six geese a-laying,");
    }

    public static void swans()
    {
        System.out.println("seven swans a-swimming,");
    }

    public static void maids()
    {
        System.out.println("eight maids a-milking,");
    }

    public static void ladies()
    {
        System.out.println("nine ladies dancing,");
    }

    public static void lords()
    {
        System.out.println("ten lords a-leaping,");
    }

    public static void pipers()
    {
        System.out.println("eleven pipers piping,");
    }

    public static void drummers()
    {
        System.out.println("twelve drummers drumming,");
    }

    public static void dayManager(int day)
    {
        dayOfX(day);
        
       if (day >= 12)
       {
            drummers();
       }
       if (day >= 11)
       {
            pipers();
       }
       if (day >= 10)
       {
            lords();
       }
       if (day >= 9)
       {
            ladies();
       }
       if (day >= 8)
       {
            maids();
       }
       if (day >= 7)
       {
            swans();
       }
       if (day >= 6)
       {
            geese();
       }
       if (day >= 5)
       {
            rings();
       }
       if (day >= 4)
       {
            birds();
       }
       if (day >= 3)
       {
            hens();
       }
       if (day >= 2)
       {
            turtDoves();
       }
       if (day >= 1)
       {
            pearTree();
       }
    }
    
    public static void main(String[] args)
    {
        
        for (int day = 1; day < 13; day++)
        {
            dayManager(day);
            
            System.out.println("");

        }

    }

}
