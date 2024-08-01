import Vista.Singletons.LoginSingleton;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class Main {
    public static void main(String[] args) {
        LoginSingleton.getInstancia().setVisible(true);
    }
}
