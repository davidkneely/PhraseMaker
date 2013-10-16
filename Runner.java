
import javalib.worldimages.*;
import javalib.worldcanvas.*;


// test the display of images for the apple orchard game and run the game
class Runner {

    ExamplesStringSong ss = new ExamplesStringSong();

    WorldCanvas c1 = new WorldCanvas(400, 100);
    WorldCanvas c2 = new WorldCanvas(400, 100);

  
    
    WorldImage worldImage = this.ss.myWorld.makeImage();
/*
    // show the virus and the computer on one canvas
    boolean makeDrawing1 = c1.show() && c1.drawImage(this.carImage1)
            && c1.drawImage(this.frogImage);

    // show the virus and the computer on one canvas
    boolean makeDrawing2 = c2.show() && c2.drawImage(this.worldImage);
*/
    // run the game
    void run() {
        this.ss.myWorld.bigBang(400, 100, 0.1);
    }

    // invoke this method to run the froggers game
    public static void main(String[] argv) {
        Runner cr = new Runner();
        cr.run();
    }
    
}