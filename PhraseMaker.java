
import javalib.worldimages.*;
import javalib.soundworld.World;
import javalib.tunes.Note;
import java.awt.Color;

class PhraseMaker extends World {

    
    String string;
    int i;

    // CONSTANTS:
    Color backgroundColor = new Color(0, 0, 0);
    int width = 400;
    int height = 100;
    RectangleImage typeField = new RectangleImage(new Posn(width / 2,
            height / 2 - 12), width - 40, 22, new Color(255, 255, 255));

    WorldImage background = new RectangleImage(new Posn(width / 2, height / 2),
            width, height, backgroundColor).overlayImages(typeField);

    // constructor for empty string
    PhraseMaker() {
        this.string = "";
        this.i = 0;
    }

    // contructor for pre-composed string
    PhraseMaker(String string) {
        this.string = string;
        this.i = 0;
    }

    // The update the "graphics"
    public void onTick() {
        this.makeImage();

    }

    // update the scene
    public void updateScene() {

    }

    public void onKeyEvent(String ke) {

        if (ke.equals("left")) {
            this.delete();
        } else if (ke.equals("down")) {
            this.i = 0;
            this.finalizeString();
        } else if (ke.equals("up")) {
            this.playNote();

        } else if (ke.equals("a") || ke.equals("A") || ke.equals("b")
                || ke.equals("B") || ke.equals("c") || ke.equals("C")
                || ke.equals("d") || ke.equals("D") || ke.equals("e")
                || ke.equals("E") || ke.equals("f") || ke.equals("F")
                || ke.equals("g") || ke.equals("G") || ke.equals("h")
                || ke.equals("H") || ke.equals("i") || ke.equals("I")
                || ke.equals("j") || ke.equals("J") || ke.equals("k")
                || ke.equals("K") || ke.equals("l") || ke.equals("L")
                || ke.equals("m") || ke.equals("M") || ke.equals("n")
                || ke.equals("N") || ke.equals("o") || ke.equals("O")
                || ke.equals("p") || ke.equals("P") || ke.equals("q")
                || ke.equals("Q") || ke.equals("r") || ke.equals("R")
                || ke.equals("s") || ke.equals("S") || ke.equals("t")
                || ke.equals("T") || ke.equals("u") || ke.equals("U")
                || ke.equals("v") || ke.equals("V") || ke.equals("w")
                || ke.equals("W") || ke.equals("x") || ke.equals("X")
                || ke.equals("y") || ke.equals("Y") || ke.equals("z")
                || ke.equals("Z") || ke.equals(" "))
            this.concat(ke);

        else
            return;
    }

    // produce the image that represents the notes, cursor, and prompt
    public WorldImage makeImage() {
        return this.background.overlayImages(this.stringPrompt(),
                this.yourString(), this.showNotes(), this.markNote());
    }

    // add the characters to the string
    void concat(String c) {
        this.string += c;
    }

    void delete() {
        if (this.string.length() == 0)
            return;
        else
            this.string = this.string.substring(0, this.string.length() - 1);
    }

    boolean isAlph(String s) {
        if (s == "a" || s == "A" || s == "b" || s == "B" || s == "c"
                || s == "C" || s == "d" || s == "D" || s == "e" || s == "E"
                || s == "f" || s == "F" || s == "g" || s == "G" || s == "h"
                || s == "H" || s == "i" || s == "I" || s == "j" || s == "J"
                || s == "k" || s == "K" || s == "l" || s == "L" || s == "m"
                || s == "M" || s == "n" || s == "N" || s == "o" || s == "O"
                || s == "p" || s == "P" || s == "q" || s == "Q" || s == "r"
                || s == "R" || s == "s" || s == "S" || s == "t" || s == "T"
                || s == "u" || s == "U" || s == "v" || s == "V" || s == "w"
                || s == "W" || s == "x" || s == "X" || s == "y" || s == "Y"
                || s == "z" || s == "Z")
            return true;
        else
            return false;

    }

    void finalizeString() {
        this.string = this.string.toLowerCase();
        String f = "";
        String s = string.substring(0, 1);
        while (string.length() > 0) {
            if (s.equals("a") || s.equals("b") || s.equals("c")
                    || s.equals("d") || s.equals("e") || s.equals("f")
                    || s.equals("g") || s.equals("h") || s.equals("i")
                    || s.equals("j") || s.equals("k") || s.equals("l")
                    || s.equals("m") || s.equals("n") || s.equals("o")
                    || s.equals("p") || s.equals("q") || s.equals("r")
                    || s.equals("s") || s.equals("t") || s.equals("u")
                    || s.equals("v") || s.equals("w") || s.equals("x")
                    || s.equals("y") || s.equals("z")) {
                f += s;
                if (string.length() == 1) {
                    string = string.substring(1, string.length());
                    s = "";
                } else {
                    string = string.substring(1, string.length());
                    s = string.substring(0, 1);
                }
            } else {
                if (string.length() == 1) {
                    string = string.substring(1, string.length());
                    s = "";
                } else {
                    string = string.substring(1, string.length());
                    s = string.substring(0, 1);
                }
            }
        }
        string = f;
    }

    // produce the string prompt
    WorldImage stringPrompt() {
        return new TextImage(new Posn(width / 2, 15),
                "Enter a word or a phrase: ", 14, Color.white);
    }

    // produce the string in the typeField
    WorldImage yourString() {
        return new TextImage(new Posn(width / 2, height / 2 - 10), this.string,
                Color.black);
    }

    void playNote() {

        if (this.string.length() == 0)
            return;
        else {
            this.tickTunes.addNote(PIANO, this.pickNote(this.string.charAt(i)));
            i += 1;
            if (i == this.string.length())
                i = 0;
            else
                return;
        }
    }

    // mark the current note
    WorldImage markNote() {
        return new LineImage(new Posn(width / 2 - (this.showNotes().getWidth() / 2) + (i * 18), height / 2 + 16),
                new Posn(width / 2 - (this.showNotes().getWidth() / 2) + (i * 18) + 15, height / 2 + 16), Color.white);
    }
    
    // show the notes
    WorldImage showNotes() {
        return new TextImage(new Posn(width / 2, height / 2 + 10),
                this.translatePhrase(), Color.white);
    }

    String translatePhrase() {
        String s = "";
        for (char c : string.toCharArray()) {
            if (c == 'a' || c == 'm' || c == 'y')
                s += "C  ";
            else if (c == 'b' || c == 'n' || c == 'z')
                s += "C# ";
            else if (c == 'c' || c == 'o')
                s += "D  ";
            else if (c == 'd' || c == 'p')
                s += "D# ";
            else if (c == 'e' || c == 'q')
                s += "E  ";
            else if (c == 'f' || c == 'r')
                s += "F  ";
            else if (c == 'g' || c == 's')
                s += "F# ";
            else if (c == 'h' || c == 't')
                s += "G  ";
            else if (c == 'i' || c == 'u')
                s += "G# ";
            else if (c == 'j' || c == 'v')
                s += "A  ";
            else if (c == 'k' || c == 'w')
                s += "A# ";
            else if (c == 'l' || c == 'z')
                s += "B  ";
            else
                s += "    ";
        }
        return s;
    }

    // pick the appropriate note
    Note pickNote(char c) {
        if (c == 'a' || c == 'm' || c == 'y')
            return new Note("C3n2");
        else if (c == 'b' || c == 'n' || c == 'z')
            return new Note("C3s2");
        else if (c == 'c' || c == 'o')
            return new Note("D3n2");
        else if (c == 'd' || c == 'p')
            return new Note("D3s2");
        else if (c == 'e' || c == 'q')
            return new Note("E3n2");
        else if (c == 'f' || c == 'r')
            return new Note("F3n2");
        else if (c == 'g' || c == 's')
            return new Note("F3s2");
        else if (c == 'h' || c == 't')
            return new Note("G3n2");
        else if (c == 'i' || c == 'u')
            return new Note("G3s2");
        else if (c == 'j' || c == 'v')
            return new Note("A4n2");
        else if (c == 'k' || c == 'w')
            return new Note("A4s2");
        else if (c == 'l' || c == 'z')
            return new Note("B4n2");
        else
            return new Note(0, 0);
    }

    // play the song
    Note[] tickyTackSong = new Note[] {
            // for (char c : this.string.toCharArray()) {
            // this.pickNote(c);
            // }
            // this.pickNote('c'), this.pickNote('d')

            new Note("E4n1"), new Note("E4n1") };

}
