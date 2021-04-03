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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kubiksk8ter
 */
public class Position {
    private File file = new File("pozice.txt");
    private FileReader fr;
    private FileWriter fw;
    /**
    * Saves the position and size of window to file souboru pozice.txt
    */
    public void ulozPozici() {
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
    * Loads X window position from pozice.txt
    */
    public int getX() {
        String line = "";
        try {
            fr = new FileReader(file);
            BufferedReader in = new BufferedReader(fr);
            line = in.readLine();            
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
        if(line == null) {
            line = String.valueOf(0);
        }
        return Integer.parseInt(line);
    }
    /**
    * Loads Y window position from pozice.txt
    */
    public int getY() {
        String line = "";
        try {
            fr = new FileReader(file);
            BufferedReader in = new BufferedReader(fr);
            for(int x = 0; x < 2; x++){
                line = in.readLine();
            }          
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
        if(line == null) {
            line = String.valueOf(0);
        }
        return Integer.parseInt(line);
    }
    /**
    * Loads width of window position from pozice.txt
    */
    public int getWidth() {
        String line = "";
        try {
            fr = new FileReader(file);
            BufferedReader in = new BufferedReader(fr);
            for(int x = 0; x < 3; x++){
                line = in.readLine();
            }          
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
        if(line == null) {
            line = String.valueOf(320);
        }
        return Integer.parseInt(line);
    }
    /**
    * Loads height of window position from pozice.txt
    */
        public int getHeight() {
        String line = "";
        try {
            fr = new FileReader(file);
            BufferedReader in = new BufferedReader(fr);
            for(int x = 0; x < 4; x++){
                line = in.readLine();
            }          
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
        if(line == null) {
            line = String.valueOf(420);
        }
        return Integer.parseInt(line);
    }
    
    
    @Override
    public String toString() {
        int pozicex = GUINotes.w.getX();
        int pozicey = GUINotes.w.getY();
        int velx = GUINotes.w.getWidth();
        int vely = GUINotes.w.getHeight();
        String s = String.valueOf(pozicex) + "\n" +
                   String.valueOf(pozicey) + "\n" + 
                   String.valueOf(velx) + "\n" +
                   String.valueOf(vely) + "\n";
        return s;
    }
}
