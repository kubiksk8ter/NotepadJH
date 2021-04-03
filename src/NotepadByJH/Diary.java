/*
MIT License

Copyright (c) 2019 Jakub Holeček

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/

package NotepadByJH;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
/**
 *
 * @author kubiksk8ter
 */
public class Diary {
    public ArrayList <String> diary = new ArrayList();
    private File file = new File("pozn.txt");
    private FileReader fr;
    private FileWriter fw;
    private LocalDateTime dateTime;
    private String date;
    
    /**
    * Constructor will load every notes from pozn.txt
    * and saves them in diary ArrayList
    */
    public Diary() {
        try {
            fr = new FileReader(file);
            BufferedReader in = new BufferedReader(fr);
            String radka;
            while((radka = in.readLine()) != null){
                diary.add(radka);
            }
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Delete list and load new one from ArrayList
     * @param list
     */
    public void nacti(List list) {        
        list.removeAll();
        diary.forEach((s) -> {
            list.add(s);
        });
    }
    /**
     * Sets actual date and time, String from TextArea and saves the in ArrayListu and
     * also in pozn.txt file
     * @param TA 
     */   
    public void uloz(JTextArea TA) {
        dateTime = LocalDateTime.now();
        date = dateTime.format(DateTimeFormatter.ofPattern("d.M.y H:m")) + ": ";
        String s = date + TA.getText();
        if(!TA.getText().isEmpty()){
                diary.add(s);
            }
        try {
            fw = new FileWriter(file);
            fw.write(toString());
            fw.close();
        }
        catch (IOException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Edit highlighted note from List
     * @param index
     * @param s 
     */
    public void uprav(int index, String s) {
        diary.set(index, s);
    }
    
    public String getDatum() {
        dateTime = LocalDateTime.now();
        date = dateTime.format(DateTimeFormatter.ofPattern("d.M.y H:m")) + ": ";
        return date;
    }
    
    @Override
    public String toString() {
        String s = "";
        for(String note: diary) {
            s += note + "\n";
        }
        return s;
    }
}
