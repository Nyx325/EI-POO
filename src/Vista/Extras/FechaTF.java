/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista.Extras;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.swing.JTextField;
import javax.swing.text.Document;

/**
 *
 * @author rubenor
 */
public class FechaTF extends JTextField {
    public FechaTF() {
    }

    public FechaTF(String text) {
        super(text);
    }

    public FechaTF(int columns) {
        super(columns);
    }

    public FechaTF(String text, int columns) {
        super(text, columns);
    }

    public FechaTF(Document doc, String text, int columns) {
        super(doc, text, columns);
    }
    
    public LocalDate getDate() throws DateTimeParseException {
        String input = this.getText().trim();
        if(input.length() == 0) return null;
        return LocalDate.parse(input);
    }
    
    public void setDate(LocalDate date){
        setText(date.toString());
    }
}
