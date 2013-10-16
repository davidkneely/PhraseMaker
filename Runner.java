
import javalib.worldimages.*;
import javalib.worldcanvas.*;


// test the display of images
class Runner {

    ExamplesStringSong ss = new ExamplesStringSong();

    WorldCanvas c1 = new WorldCanvas(400, 100);
    WorldCanvas c2 = new WorldCanvas(400, 100);

  
    
    WorldImage worldImage = this.ss.myWorld.makeImage();

    
    // run the game
    void run() {
        this.ss.myWorld.bigBang(400, 100, 0.1);
    }

    // invoke this method to run the game
    public static void main(String[] argv) {
        Runner cr = new Runner();
        cr.run();
    }
    
}