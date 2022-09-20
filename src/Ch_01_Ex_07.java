public class Ch_01_Ex_07
{
    public static void forwardSlash()
    {
        System.out.println("//////////////////////");
    }

    public static void backSlash()
    {
        System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
    }

    public static void vIsM()
    {
        System.out.println("|| Victory is mine! ||");
    }

    public static void main(String[] args)
    {
        forwardSlash();

        for (int i = 0; i < 5; i++)
        {
            vIsM();
            backSlash();
        }
    }
}
