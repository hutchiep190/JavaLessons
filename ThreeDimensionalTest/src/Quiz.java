import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Quiz {
    
    private static List<String> lines =new ArrayList<String>(); 
    private static Frame f;
    public static void main(String[] args) {
        f = new Frame("Quiz Window");
        f.setSize(640, 480);
        f.setResizable(false);
        f.setUndecorated(true);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.setVisible(true);
        load("Stuff.txt");
        f.createBufferStrategy(2);
        final Frame fr = f;
        new Timer(20, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Quiz.draw(fr);
                }
            }).start();
    }
    public static void draw(Frame f){
        int y = 150;
        BufferStrategy bf = f.getBufferStrategy();
        Graphics g = bf.getDrawGraphics();
        for(String line: lines) {
            g.drawString(line, 300, y);
            y+=20;
        }

        

        bf.show();
    }
   
    public static void load(String fileName){
        Scanner s;
        String newLine;
        try{
            File file;
            
            file = new File(fileName);
            System.out.println("Loading file " + fileName + ". Please wait...");
            s = new Scanner(file);
        }catch(FileNotFoundException e) {
            System.err.println("Error, could not find " + fileName + " ... Please make sure the file is in the right directory and try again.");
            System.exit(0);
            return;
        }
        while(s.hasNextLine()) {
            newLine = s.nextLine();
            System.out.println(newLine);
            lines.add(newLine);
        }
    }
}