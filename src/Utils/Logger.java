package Utils;

public class Logger {
    public static <T> void log(T mensaje) {
        System.out.print(mensaje);
    }

    public static <T> void logln(T mensaje) {
        System.out.println(mensaje);
    }

    public static <T> void logError(T error) {
        System.out.println(ANSIColors.ANSI_RED + error + ANSIColors.ANSI_RESET);
    }

    public static <T> void logSuccess(T success) {
        System.out.println(ANSIColors.ANSI_GREEN + success + ANSIColors.ANSI_RESET);
    }

    public static <T> void logImportant(T mensajeImportante) {
        System.out.println(ANSIColors.ANSI_YELLOW + mensajeImportante + ANSIColors.ANSI_RESET);
    }
}
