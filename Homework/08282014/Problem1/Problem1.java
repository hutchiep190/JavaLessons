import java.awt.Frame;
import java.awt.Color;
import java.awt.event.*;
import java.awt.Graphics;
import javax.swing.Timer;
import java.awt.image.BufferStrategy;
import java.util.*;
import java.io.*;

public class Problem1 extends Frame{
    List<Rectangle> rectangleList = new ArrayList<Rectangle>(); 
    public Problem1() {
        loadRectangles();
        // Set the width and height of the window to 300x300
        this.setSize(300,300);

        // Make it undecorated so it doesn't have any borders
        this.setUndecorated(true);

        // Add a windowAdapter to quit when it gets the windowClosing signal
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        // Make it visible
        this.setVisible(true);

        // Enable the backbuffer
        this.createBufferStrategy(2);

        // Add a timer to draw every 20 milliseconds
        // This allows the variable problem1 to be used within the
        // anonymous ActionListener class
        final Problem1 problem1 = this;
        
         Timer t = new Timer(20, new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                    // Draw using the variable problem1
                    problem1.draw();
                 }
             });
         t.start();
    }
    private class Rectangle {
        private int x,y,width,height,r,g,b;
        public  Rectangle(int x, int y, int width, int height, int r, int g, int b) {
            this.x=x;
            this.y=y;
            this.width=width;
            this.height=height;
            this.r=r;
            this.g=g;
            this.b=b; 
        }
        public void draw(Graphics gr) {
            gr.setColor(new Color(r,g,b));
            gr.fillRect(x,y,width,height);
        }
    }
    public void loadRectangles() {
        // Read the file 'problem1.txt'
        Scanner s = null;
        try{
            File file = new File("problem1.txt");
            s = new Scanner(file);
        }catch(FileNotFoundException e){
            System.err.println("You failed");
        }
        // For each line of the file, see if the first token is an R
        while(s.hasNextLine()){
            String line = s.nextLine();
            System.out.println(line);
            Scanner ls = new Scanner(line);
            if(ls.hasNext()) {
                String rChecker = ls.next();
                // If the first token is an R
                if(rChecker.equals("R")) {
                    // read the x, y, width, height, and r, g, b values of the color
                    int x = ls.nextInt();
                    int y = ls.nextInt();
                    int width = ls.nextInt();
                    int height = ls.nextInt();
                    int r = ls.nextInt();
                    int g = ls.nextInt();
                    int b = ls.nextInt();
                    // Save the rectangle
                    Rectangle rectangle = new Rectangle(x,y,width,height,r,g,b);
                    rectangleList.add(rectangle);
                }
            }
        }
    }

    public void draw() {
        // Get the BufferStrategy from the window (the window is 'this')
        BufferStrategy buffer = this.getBufferStrategy();

        // Get the Graphics object from the BufferStrategy
        Graphics g = buffer.getDrawGraphics();
        /* Draw a black rectangle to clear the screen (big enough to
         fill the screen) */
        g.setColor(Color.BLACK);
        g.fillRect(0,0,300,300);
        // Draw each rectangle
        for(Rectangle rectangle : rectangleList) {
           rectangle.draw(g);
        }

        // Flip the BufferStrategy's off-screen image to screen
        buffer.show();
    }

    public static void main(String[] args) {
        // Make a new Problem1 object
        Problem1 window = new Problem1();
    }
}
