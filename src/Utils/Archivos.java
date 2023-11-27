package Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Archivos {
    private Archivos() {}
    public static void guardarEnArchivo(String datos) {
        String nombreArchivo = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date());
        nombreArchivo = "informe" + nombreArchivo + ".txt";
        File file = new File("./src/InformesGuardados/" + nombreArchivo);
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write(datos);
        } catch (IOException e) {
            System.out.println("Algo salio mal al guardar el archivo" + e.toString());
        }finally{
            try {
                assert fr != null;
                fr.close();
            } catch (IOException e) {
                System.out.println("Algo salio mal al cerrar el archivo");
            }
        }
    }
}
