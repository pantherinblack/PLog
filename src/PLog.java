import java.io.*;
import java.time.LocalDate;

public class PLog {
    private LocalDate date;
    private BufferedReader reader;
    private FileWriter writer;
    private String docName;
    private String text;

    public String getDocName() {
        return docName;
    }

    public PLog(String docName){
        this.docName = docName;
        if (new File((new File("").getAbsolutePath()+"\\Plog\\"+docName)).exists()){
            try {
                reader = new BufferedReader(new FileReader((new File("").getAbsolutePath()+"\\PLog\\"+docName)));
                readFile();
            } catch (FileNotFoundException e) {
                System.out.println("File could not be read");
            } catch (IOException ignored) {}

        } else {
            date = LocalDate.now();
        }
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                storeFile();
            }
        }));
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void readFile() throws IOException {
        String strDate = reader.readLine();
        String[] strArDate = strDate.split("\\.");
        date = LocalDate.of(Integer.parseInt(strArDate[2]),Integer.parseInt(strArDate[1]),Integer.parseInt(strArDate[0]));
        text = reader.readLine();
    }

    public LocalDate getDate() {
        return date;
    }

    public void storeFile(){
        if (text!=null && text!="") {
            try {
                writer = new FileWriter((new File("").getAbsolutePath()+"\\PLog\\" + docName), false);
                writer.write(date.getDayOfMonth() + "." + date.getMonthValue() + "." + date.getYear() + "\n" + text);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
