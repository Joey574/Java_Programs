public class Ch_01_Project_03
{

    public static void die()
    {
        System.out.println("Perhaps she'll die.\n");
    }

    public static void fly()
    {
        System.out.println("I don't know why she swallowed that fly,");
    }

    public static void spider()
    {
        System.out.println("She swallowed the spider to catch the fly,");
    }

    public static void bird()
    {
        System.out.println("She swallowed the bird to catch the spider,");
    }

    public static void cat()
    {
        System.out.println("She swallowed the cat to catch the bird,");
    }

    public static void dog()
    {
        System.out.println("She swallowed the dog to catch the cat,");
    }

    public static void vOne()
    {
        System.out.println("There was an old lady who swallowed a fly,");
        fly();
        die();
    }

    public static void vTwo()
    {
        System.out.println("There was an old lady who swallowed a spider,\nThat wriggled and iggled and jiggled inside her.");
        spider();
        fly();
        die();
    }

    public static void vThree()
    {
        System.out.println("There was an old lady who swallowed a bird,\nHow absurd to swallow a bird.");
        bird();
        spider();
        fly();
        die();
    }

    public static void vFour()
    {
        System.out.println("There was an old lady who swallowed a cat,\nImagine that to swallow a cat.");
        cat();
        bird();
        spider();
        fly();
        die();
    }

    public static void vFive()
    {
        System.out.println("There was an old lady who swallowed a dog,\nWhat a hog to swallow a dog.");
        dog();
        cat();
        bird();
        spider();
        fly();
        die();
    }

    public static void vSix()
    {
        System.out.println("There was an old lady who swallowed a horse,\nShe died of course.");
    }

    public static void main(String[] args)
    {
        vOne();

        vTwo();

        vThree();

        vFour();

        vFive();

        vSix();
    }

}
