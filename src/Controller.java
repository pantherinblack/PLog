import java.io.File;
import java.util.Vector;

public class Controller {
    Vector<PLog> pLogs = new Vector<>();
    public void getAllEntry(){
        String[] files = new File(new File("").getAbsolutePath()+"\\Plog").list();

        for (String file : files){
            pLogs.add(new PLog(file));
        }
        pLogs.sort(new PLogDateComparator());
    }

    public void clearAllEntry(){
        pLogs.clear();
    }

    public void addEntry(PLog pLog){
        pLogs.add(pLog);
    }

    public int searchIndexByDocName(String docName){
        for (int i=0; i<pLogs.size(); i++){
            if (pLogs.get(i).getDocName().equalsIgnoreCase(docName)){
                return i;
            }
        }
        return -1;
    }


    public String getText(int index){
        return pLogs.get(index).getText();
    }

    public void setText(int index, String text){
        pLogs.get(index).setText(text);
    }

    public void printAllEntry(){
        for (PLog pLog : pLogs){
            System.out.println(pLog.getDocName());
        }
    }

    public void saveAllEntry(){
        for (PLog pLog : pLogs){
            pLog.storeFile();
        }
    }


    public static void main(String[] args) {
        Einleser parse = new Einleser();
        Controller controller = new Controller();
        controller.getAllEntry();
        controller.printAllEntry();

        String docName="";
        do{
            System.out.print("Neuen Eintrag erstellen oder Bearbeiten: ");
            docName = parse.readString();

            if (docName.equalsIgnoreCase("reload")){
                System.out.println("Lade alle Dateien neu.\n");
                controller.saveAllEntry();
                controller.clearAllEntry();
                controller.getAllEntry();
                controller.printAllEntry();
            } else if (docName!="") {
                int index = 0;
                if (controller.searchIndexByDocName(docName) == -1) {
                    controller.addEntry(new PLog((docName+".txt")));
                    System.out.println("Neuer Eintrag angelegt.");
                }
                index = controller.searchIndexByDocName(docName+".txt");
                System.out.print("Text: ");
                String text = parse.readString();
                if (text!="") {
                    controller.setText(index, text);
                }
            } else {
                System.out.println("Speicherung der Dateien...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } while (docName!="");
    }
}
