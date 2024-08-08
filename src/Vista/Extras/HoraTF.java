/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista.Extras;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import javax.swing.JTextField;
import javax.swing.text.Document;

/**
 *
 * @author rubenor
 */
public class HoraTF extends JTextField{

    public HoraTF() {
    }

    public HoraTF(String text) {
        super(text);
    }

    public HoraTF(int columns) {
        super(columns);
    }

    public HoraTF(String text, int columns) {
        super(text, columns);
    }

    public HoraTF(Document doc, String text, int columns) {
        super(doc, text, columns);
    }
    
    public LocalTime getHour() throws DateTimeParseException {
        String input = this.getText().trim();
        
        if(input.length() == 0) return null;
        
        if(!input.contains(":")) 
            throw new DateTimeParseException("Text '" + input + "' could not be parsed", "proj", ERROR);
        
        String[] tiempo = input.split(":");
        if(tiempo.length < 2) 
            throw new DateTimeParseException("Text '" + input + "' could not be parsed", "proj", ERROR);
        
        return LocalTime.of(Integer.parseInt(tiempo[0]),Integer.parseInt(tiempo[1]));
    }
}
