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

import java.awt.Button;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.border.Border;

/**
 *
 * @author kubiksk8ter
 */
public class Window extends JFrame {
    private JTextArea inputTA;
    private final JButton saveBT;
    private final Button deleteAll;
    private final JButton deleteBT;
    private final Button uprav;
    private Diary diary = new Diary();
    private Position pozition = new Position();
    private final List list;   
    private final Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);

    public Window() {
        super.setTitle("Notepad"); 
        //Zavírání okna
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                diary.uloz(inputTA);
                pozition.ulozPozici();
                System.exit(1);
            }
        });
        //Constructors
        list = new List();
        diary.nacti(list);
        inputTA = new JTextArea("", 2, 20); inputTA.setBorder(border);
        saveBT = new JButton("Save");
        deleteAll = new Button("Save all");
        deleteBT = new JButton("Delete");
        uprav = new Button("Edit");
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        //TextArea
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2; //výška
        gbc.gridwidth = 2;  //šířka
        //gbc.ipadx = 65;
        this.add(inputTA, gbc);
        //Seznam
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 2; //výška
        gbc.gridwidth = 3;  //šířka
        //gbc.ipadx = 170;
        this.add(list, gbc);
        //ulož Button
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        this.add(saveBT, gbc);
        saveBT.addActionListener(new saveBTAL());
        //gbc.ipadx = 28;
        //smaž Button
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        //gbc.ipadx = 21;
        this.add(deleteBT, gbc);
        deleteBT.addActionListener(new deleteBTAL());
        /*       
        //uprav Button
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        //gbc.ipadx = 20;
        uprav.addActionListener(new editAL());
        
        //smaž vše Button
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        //gbc.ipadx = 10;
        deleteAll.addActionListener(new deleteAllAL());
        */
        this.setSize(pozition.getWidth(), pozition.getHeight()); 
        this.setLocation(pozition.getX(), pozition.getY());
    }
        
    public class saveBTAL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {           
        diary.uloz(inputTA);
        diary.nacti(list);
        inputTA.setText(null);
        pozition.ulozPozici();
        }       
    }
    
    public class deleteBTAL implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String s = list.getSelectedItem();
            list.remove(s);
            diary.diary.remove(s);
            diary.uloz(inputTA);
        }        
    }
    
    public class editAL implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            diary.uprav(list.getSelectedIndex(),diary.getDatum() + inputTA.getText());
            diary.nacti(list); 
            inputTA.setText(null);
        }        
    }
    
    public class deleteAllAL implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            list.removeAll();
            diary.diary.removeAll(diary.diary);
        }        
    }
    @Override
    public Insets getInsets() {
        return new Insets (40, 10, 10, 10);
    }
}
