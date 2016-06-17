import file.ManageFile;

/**
 * Created by Nattaporn on 17/6/2559.
 */
public class Main {
    private static String url_file = "d:\\promotion1.log";
    private static Float money_per_hour = 295f/8f;

    private static ManageFile manageFile;

    public static void main(String[] args) {
        //readFileAndPrint("d:\\promotion1.log");
        manageFile = new ManageFile(url_file, money_per_hour);

    }
}
