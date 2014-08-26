import java.awt.Frame;
import javax.swing.Timer;

public class Problem1 extends Frame{

    public Problem1() {
        loadRectangles();
        // Set the width and height of the window to 300x300


        // Make it undecorated so it doesn't have any borders


        // Add a windowAdapter to quit when it gets the windowClosing signal


        // Make it visible


        // Enable the backbuffer


        // Add a timer to draw every 20 milliseconds

        // This allows the variable problem1 to be used within the
        // anonymous ActionListener class
        final Problem1 problem1 = this;

        // Timer t = new Timer(ms, new ActionListener() {
        //         public void actionPerformed(ActionEvent e) {
        //             // Draw using the variable problem1
        //         }
        //     });
        // t.start();
    }

    public void loadRectangles() {
        // Read the file 'problem1.txt'


        // For each line of the file, see if the first token is an R


            // If the first token is an R


                // read the x, y, width, height, and r, g, b values of the color


                // Save the rectange


    }

    public void draw() {
        // Get the BufferStrategy from the window (the window is 'this')


        // Get the Graphics object from the BufferStrategy


        // Draw a black rectangle to clear the screen (big enough to
        // fill the screen)


        // Draw each rectangle


        // Flip the BufferStrategy's off-screen image to screen

    }

    public static void main(String[] args) {
        // Make a new Problem1 object

    }
}
