package Controlador;

import java.time.LocalDate;

public class OperacionesLocalDate {
    protected int funcionMagica(LocalDate d1, LocalDate d2){
        if(d1.getYear() > d2.getYear()) return 1;

        if(d1.getYear() == d2.getYear()){
            if(d1.getMonthValue() > d2.getMonthValue()) return 1;

            if(d1.getMonthValue() == d2.getMonthValue()){
                if(d1.getDayOfMonth() > d2.getDayOfMonth()) return 1;

                if(d1.getDayOfMonth() == d2.getDayOfMonth()) return 0;
            }
        }

        return -1;
    }
}
