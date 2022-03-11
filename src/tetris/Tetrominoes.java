package tetris;


import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Tetrominoes {
    public abstract void rotate();
    protected Color color;
    protected int orientation;
    protected ArrayList<Integer> pieceXLoc;
    protected ArrayList<Integer> pieceYLoc;

    Tetrominoes(){
        orientation = 0;
        pieceXLoc = new ArrayList<>();
        pieceYLoc = new ArrayList<>();
    }
    public static Tetrominoes generatePiece(int x){
        switch(x){
            case 0:
                return new Straight();
            case 1:
                return new TShape();
            case 2:
                return new Square();
            case 3:
                return new LShapeLeft();
            case 4:
                return new LShapeRight();
            case 5:
                return new ZShapeRight();
            case 6:
                return new ZShapeLeft();
            default:
                return null;
        }
    }

    public Color getColor(){
        return color;
    }
}

class Straight extends Tetrominoes{
    public Straight(){
        super();

        this.color = Color.CORNFLOWERBLUE;
        pieceXLoc.add(3);
        pieceXLoc.add(4);
        pieceXLoc.add(5);
        pieceXLoc.add(6);
        //______________
        pieceYLoc.add(1);
        pieceYLoc.add(1);
        pieceYLoc.add(1);
        pieceYLoc.add(1);
    }
    @Override
    public void rotate() {

        switch(orientation){
            case 0:

                pieceXLoc.set(0, pieceXLoc.get(1));
                pieceYLoc.set(0, pieceYLoc.get(1)-1);

                pieceXLoc.set(2, pieceXLoc.get(1));
                pieceYLoc.set(2, pieceYLoc.get(1)+1);

                pieceXLoc.set(3, pieceXLoc.get(1));
                pieceYLoc.set(3, pieceYLoc.get(1)+2);
                break;
            case 1:
                if(pieceXLoc.get(0) > 0 && pieceXLoc.get(3) < 9){
                    pieceXLoc.set(0, pieceXLoc.get(1)-1);
                    pieceYLoc.set(0, pieceYLoc.get(1));

                    pieceXLoc.set(2, pieceXLoc.get(1)+1);
                    pieceYLoc.set(2, pieceYLoc.get(1));

                    pieceXLoc.set(3, pieceXLoc.get(1)+2);
                    pieceYLoc.set(3, pieceYLoc.get(1));
                }

                break;
            case 2:
                pieceXLoc.set(0, pieceXLoc.get(2));
                pieceYLoc.set(0, pieceYLoc.get(2)+2);

                pieceXLoc.set(1, pieceXLoc.get(2));
                pieceYLoc.set(1, pieceYLoc.get(2)+1);

                pieceXLoc.set(3, pieceXLoc.get(2));
                pieceYLoc.set(3, pieceYLoc.get(2)-1);
                break;
            case 3:
                if(pieceXLoc.get(0) > 0 && pieceXLoc.get(3) < 9) {
                    pieceXLoc.set(0, pieceXLoc.get(2) - 2);
                    pieceYLoc.set(0, pieceYLoc.get(2));

                    pieceXLoc.set(1, pieceXLoc.get(2) - 1);
                    pieceYLoc.set(1, pieceYLoc.get(2));

                    pieceXLoc.set(3, pieceXLoc.get(2) + 1);
                    pieceYLoc.set(3, pieceYLoc.get(2));
                }
                break;
        }
        orientation = (orientation + 1) % 4;
    }

}

class TShape extends Tetrominoes{

    public TShape(){
        super();
        this.color = Color.MEDIUMPURPLE;
        pieceXLoc.add(4);
        pieceXLoc.add(4);
        pieceXLoc.add(3);
        pieceXLoc.add(5);
        //______________
        pieceYLoc.add(0);
        pieceYLoc.add(1);
        pieceYLoc.add(1);
        pieceYLoc.add(1);
    }

    @Override
    public void rotate() {
        if(pieceXLoc.get(0) > 0 && pieceXLoc.get(3) < 9) {
            switch(orientation){
                case 0:

                    pieceXLoc.set(0, pieceXLoc.get(1)+1);
                    pieceYLoc.set(0, pieceYLoc.get(1));

                    pieceXLoc.set(2, pieceXLoc.get(1));
                    pieceYLoc.set(2, pieceYLoc.get(1)-1);

                    pieceXLoc.set(3, pieceXLoc.get(1));
                    pieceYLoc.set(3, pieceYLoc.get(1)+1);
                    break;
                case 1:
                    pieceXLoc.set(0, pieceXLoc.get(1));
                    pieceYLoc.set(0, pieceYLoc.get(1)+1);

                    pieceXLoc.set(2, pieceXLoc.get(1)+1);
                    pieceYLoc.set(2, pieceYLoc.get(1));

                    pieceXLoc.set(3, pieceXLoc.get(1)-1);
                    pieceYLoc.set(3, pieceYLoc.get(1));
                    break;
                case 2:
                    pieceXLoc.set(0, pieceXLoc.get(1)-1);
                    pieceYLoc.set(0, pieceYLoc.get(1));

                    pieceXLoc.set(2, pieceXLoc.get(1));
                    pieceYLoc.set(2, pieceYLoc.get(1)+1);

                    pieceXLoc.set(3, pieceXLoc.get(1));
                    pieceYLoc.set(3, pieceYLoc.get(1)-1);
                    break;
                case 3:
                    pieceXLoc.set(0, pieceXLoc.get(1));
                    pieceYLoc.set(0, pieceYLoc.get(1)-1);

                    pieceXLoc.set(2, pieceXLoc.get(1)-1);
                    pieceYLoc.set(2, pieceYLoc.get(1));

                    pieceXLoc.set(3, pieceXLoc.get(1)+1);
                    pieceYLoc.set(3, pieceYLoc.get(1));
                    break;
            }
            orientation = (orientation + 1) % 4;
        }


    }
}

class Square extends Tetrominoes{
    public Square(){
        super();
        this.color = Color.YELLOW;
        pieceXLoc.add(4);
        pieceXLoc.add(5);
        pieceXLoc.add(4);
        pieceXLoc.add(5);
        //_____________
        pieceYLoc.add(0);
        pieceYLoc.add(0);
        pieceYLoc.add(1);
        pieceYLoc.add(1);
    }
    @Override
    public void rotate() {

    }

}

class LShapeLeft extends Tetrominoes{
    public LShapeLeft(){
        super();
        this.color = Color.BLUE;
        pieceXLoc.add(4);
        pieceXLoc.add(4);
        pieceXLoc.add(5);
        pieceXLoc.add(6);
        //_____________
        pieceYLoc.add(0);
        pieceYLoc.add(1);
        pieceYLoc.add(1);
        pieceYLoc.add(1);
    }
    @Override
    public void rotate() {
        if(pieceXLoc.get(0) > 0 && pieceXLoc.get(3) < 9) {
            switch(orientation){
                case 0:
                    pieceXLoc.set(0, pieceXLoc.get(2)+1);
                    pieceYLoc.set(0, pieceYLoc.get(2)-1);

                    pieceXLoc.set(1, pieceXLoc.get(2));
                    pieceYLoc.set(1, pieceYLoc.get(2)-1);

                    pieceXLoc.set(3, pieceXLoc.get(2));
                    pieceYLoc.set(3, pieceYLoc.get(2)+1);
                    break;
                case 1:
                    pieceXLoc.set(0, pieceXLoc.get(2)+1);
                    pieceYLoc.set(0, pieceYLoc.get(2)+1);

                    pieceXLoc.set(1, pieceXLoc.get(2)+1);
                    pieceYLoc.set(1, pieceYLoc.get(2));

                    pieceXLoc.set(3, pieceXLoc.get(2)-1);
                    pieceYLoc.set(3, pieceYLoc.get(2));
                    break;
                case 2:
                    pieceXLoc.set(0, pieceXLoc.get(2)-1);
                    pieceYLoc.set(0, pieceYLoc.get(2)+1);

                    pieceXLoc.set(1, pieceXLoc.get(2));
                    pieceYLoc.set(1, pieceYLoc.get(2)+1);

                    pieceXLoc.set(3, pieceXLoc.get(2));
                    pieceYLoc.set(3, pieceYLoc.get(2)-1);
                    break;
                case 3:
                    pieceXLoc.set(0, pieceXLoc.get(2)-1);
                    pieceYLoc.set(0, pieceYLoc.get(2)-1);

                    pieceXLoc.set(1, pieceXLoc.get(2)-1);
                    pieceYLoc.set(1, pieceYLoc.get(2));

                    pieceXLoc.set(3, pieceXLoc.get(2)+1);
                    pieceYLoc.set(3, pieceYLoc.get(2));
                    break;
            }
            orientation = (orientation + 1) % 4;
        }


    }

}

class LShapeRight extends Tetrominoes{
    public LShapeRight(){
        super();
        this.color = Color.ORANGERED;
        pieceXLoc.add(5);
        pieceXLoc.add(5);
        pieceXLoc.add(4);
        pieceXLoc.add(3);
        //_____________
        pieceYLoc.add(0);
        pieceYLoc.add(1);
        pieceYLoc.add(1);
        pieceYLoc.add(1);
    }
    @Override
    public void rotate() {
        if(Collections.min(pieceXLoc) > 0 && Collections.max(pieceXLoc) < 9) {
            switch(orientation){
                case 0:
                    pieceXLoc.set(0, pieceXLoc.get(2)+1);
                    pieceYLoc.set(0, pieceYLoc.get(2)+1);

                    pieceXLoc.set(1, pieceXLoc.get(2));
                    pieceYLoc.set(1, pieceYLoc.get(2)+1);

                    pieceXLoc.set(3, pieceXLoc.get(2));
                    pieceYLoc.set(3, pieceYLoc.get(2)-1);
                    break;
                case 1:
                    pieceXLoc.set(0, pieceXLoc.get(2)-1);
                    pieceYLoc.set(0, pieceYLoc.get(2)+1);

                    pieceXLoc.set(1, pieceXLoc.get(2)-1);
                    pieceYLoc.set(1, pieceYLoc.get(2));

                    pieceXLoc.set(3, pieceXLoc.get(2)+1);
                    pieceYLoc.set(3, pieceYLoc.get(2));
                    break;
                case 2:
                    pieceXLoc.set(0, pieceXLoc.get(2)-1);
                    pieceYLoc.set(0, pieceYLoc.get(2)-1);

                    pieceXLoc.set(1, pieceXLoc.get(2));
                    pieceYLoc.set(1, pieceYLoc.get(2)-1);

                    pieceXLoc.set(3, pieceXLoc.get(2));
                    pieceYLoc.set(3, pieceYLoc.get(2)+1);
                    break;
                case 3:
                    pieceXLoc.set(0, pieceXLoc.get(2)+1);
                    pieceYLoc.set(0, pieceYLoc.get(2)-1);

                    pieceXLoc.set(1, pieceXLoc.get(2)+1);
                    pieceYLoc.set(1, pieceYLoc.get(2));

                    pieceXLoc.set(3, pieceXLoc.get(2)-1);
                    pieceYLoc.set(3, pieceYLoc.get(2));
                    break;
            }
            orientation = (orientation + 1) % 4;
        }


    }

}
class ZShapeRight extends Tetrominoes{
    public ZShapeRight(){
        super();
        color = Color.GREEN;
        pieceXLoc.add(6);
        pieceXLoc.add(5);
        pieceXLoc.add(5);
        pieceXLoc.add(4);
        //______________
        pieceYLoc.add(0);
        pieceYLoc.add(0);
        pieceYLoc.add(1);
        pieceYLoc.add(1);
    }
    @Override
    public void rotate() {
        switch(orientation){
            case 0:
                pieceXLoc.set(0, pieceXLoc.get(2)+1);
                pieceYLoc.set(0, pieceYLoc.get(2)+1);

                pieceXLoc.set(1, pieceXLoc.get(2)+1);
                pieceYLoc.set(1, pieceYLoc.get(2));

                pieceXLoc.set(3, pieceXLoc.get(2));
                pieceYLoc.set(3, pieceYLoc.get(2)-1);
                break;
            case 1:
                pieceXLoc.set(0, pieceXLoc.get(2)-1);
                pieceYLoc.set(0, pieceYLoc.get(2)+1);

                pieceXLoc.set(1, pieceXLoc.get(2));
                pieceYLoc.set(1, pieceYLoc.get(2)+1);

                pieceXLoc.set(3, pieceXLoc.get(2)+1);
                pieceYLoc.set(3, pieceYLoc.get(2));
                break;
            case 2:
                pieceXLoc.set(0, pieceXLoc.get(2)-1);
                pieceYLoc.set(0, pieceYLoc.get(2)-1);

                pieceXLoc.set(1, pieceXLoc.get(2)-1);
                pieceYLoc.set(1, pieceYLoc.get(2));

                pieceXLoc.set(3, pieceXLoc.get(2));
                pieceYLoc.set(3, pieceYLoc.get(2)+1);
                break;
            case 3:
                pieceXLoc.set(0, pieceXLoc.get(2)+1);
                pieceYLoc.set(0, pieceYLoc.get(2)-1);

                pieceXLoc.set(1, pieceXLoc.get(2));
                pieceYLoc.set(1, pieceYLoc.get(2)-1);

                pieceXLoc.set(3, pieceXLoc.get(2)-1);
                pieceYLoc.set(3, pieceYLoc.get(2));
                break;
        }
        orientation = (orientation + 1) % 4;
    }
}
class ZShapeLeft extends Tetrominoes {
    public ZShapeLeft(){
        super();
        color = Color.RED;
        pieceXLoc.add(4);
        pieceXLoc.add(5);
        pieceXLoc.add(5);
        pieceXLoc.add(6);
        //______________
        pieceYLoc.add(0);
        pieceYLoc.add(0);
        pieceYLoc.add(1);
        pieceYLoc.add(1);
    }
    @Override
    public void rotate() {

        switch(orientation){
            case 0:
                pieceXLoc.set(0, pieceXLoc.get(2)+1);
                pieceYLoc.set(0, pieceYLoc.get(2)-1);

                pieceXLoc.set(1, pieceXLoc.get(2)+1);
                pieceYLoc.set(1, pieceYLoc.get(2));

                pieceXLoc.set(3, pieceXLoc.get(2));
                pieceYLoc.set(3, pieceYLoc.get(2)+1);
                break;
            case 1:
                pieceXLoc.set(0, pieceXLoc.get(2)+1);
                pieceYLoc.set(0, pieceYLoc.get(2)+1);

                pieceXLoc.set(1, pieceXLoc.get(2));
                pieceYLoc.set(1, pieceYLoc.get(2)+1);

                pieceXLoc.set(3, pieceXLoc.get(2)-1);
                pieceYLoc.set(3, pieceYLoc.get(2));
                break;
            case 2:
                pieceXLoc.set(0, pieceXLoc.get(2)-1);
                pieceYLoc.set(0, pieceYLoc.get(2)+1);

                pieceXLoc.set(1, pieceXLoc.get(2)-1);
                pieceYLoc.set(1, pieceYLoc.get(2));

                pieceXLoc.set(3, pieceXLoc.get(2));
                pieceYLoc.set(3, pieceYLoc.get(2)-1);
                break;
            case 3:
                pieceXLoc.set(0, pieceXLoc.get(2)-1);
                pieceYLoc.set(0, pieceYLoc.get(2)-1);

                pieceXLoc.set(1, pieceXLoc.get(2));
                pieceYLoc.set(1, pieceYLoc.get(2)-1);

                pieceXLoc.set(3, pieceXLoc.get(2)+1);
                pieceYLoc.set(3, pieceYLoc.get(2));
                break;
        }
        orientation = (orientation + 1) % 4;
    }
}