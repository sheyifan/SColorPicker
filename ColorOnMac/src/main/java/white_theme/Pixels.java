package white_theme;

import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Pixels
{
    private Pane p0p4;
    private Pane p0p3;
    private Pane p0p2;
    private Pane p0p1;
    private Pane p0p0;
    private Pane p0n1;
    private Pane p0n2;
    private Pane p0n3;
    private Pane p0n4;

    private Pane n1p4;
    private Pane n1p3;
    private Pane n1p2;
    private Pane n1p1;
    private Pane n1p0;
    private Pane n1n1;
    private Pane n1n2;
    private Pane n1n3;
    private Pane n1n4;

    private Pane p1p4;
    private Pane p1p3;
    private Pane p1p2;
    private Pane p1p1;
    private Pane p1p0;
    private Pane p1n1;
    private Pane p1n2;
    private Pane p1n3;
    private Pane p1n4;

    private Pane n2p3;
    private Pane n2p2;
    private Pane n2p1;
    private Pane n2p0;
    private Pane n2n1;
    private Pane n2n2;
    private Pane n2n3;

    private Pane p2p3;
    private Pane p2p2;
    private Pane p2p1;
    private Pane p2p0;
    private Pane p2n1;
    private Pane p2n2;
    private Pane p2n3;

    private Pane n3p3;
    private Pane n3p2;
    private Pane n3p1;
    private Pane n3p0;
    private Pane n3n1;
    private Pane n3n2;
    private Pane n3n3;

    private Pane p3p3;
    private Pane p3p2;
    private Pane p3p1;
    private Pane p3p0;
    private Pane p3n1;
    private Pane p3n2;
    private Pane p3n3;

    private Pane n4p1;
    private Pane n4p0;
    private Pane n4n1;

    private Pane p4p1;
    private Pane p4p0;
    private Pane p4n1;
    public Pixels(
            Pane p0p4,
            Pane p0p3,
            Pane p0p2,
            Pane p0p1,
            Pane p0p0,
            Pane p0n1,
            Pane p0n2,
            Pane p0n3,
            Pane p0n4,

            Pane n1p4,
            Pane n1p3,
            Pane n1p2,
            Pane n1p1,
            Pane n1p0,
            Pane n1n1,
            Pane n1n2,
            Pane n1n3,
            Pane n1n4,

            Pane p1p4,
            Pane p1p3,
            Pane p1p2,
            Pane p1p1,
            Pane p1p0,
            Pane p1n1,
            Pane p1n2,
            Pane p1n3,
            Pane p1n4,

            Pane n2p3,
            Pane n2p2,
            Pane n2p1,
            Pane n2p0,
            Pane n2n1,
            Pane n2n2,
            Pane n2n3,

            Pane p2p3,
            Pane p2p2,
            Pane p2p1,
            Pane p2p0,
            Pane p2n1,
            Pane p2n2,
            Pane p2n3,

            Pane n3p3,
            Pane n3p2,
            Pane n3p1,
            Pane n3p0,
            Pane n3n1,
            Pane n3n2,
            Pane n3n3,

            Pane p3p3,
            Pane p3p2,
            Pane p3p1,
            Pane p3p0,
            Pane p3n1,
            Pane p3n2,
            Pane p3n3,

            Pane n4p1,
            Pane n4p0,
            Pane n4n1,

            Pane p4p1,
            Pane p4p0,
            Pane p4n1)
    {
        this.p0p4 = p0p4;
        this.p0p3 = p0p3;
        this.p0p2 = p0p2;
        this.p0p1 = p0p1;
        this.p0p0 = p0p0;
        this.p0n1 = p0n1;
        this.p0n2 = p0n2;
        this.p0n3 = p0n3;
        this.p0n4 = p0n4;

        this.n1p4 = n1p4;
        this.n1p3 = n1p3;
        this.n1p2 = n1p2;
        this.n1p1 = n1p1;
        this.n1p0 = n1p0;
        this.n1n1 = n1n1;
        this.n1n2 = n1n2;
        this.n1n3 = n1n3;
        this.n1n4 = n1n4;

        this.p1p4 = p1p4;
        this.p1p3 = p1p3;
        this.p1p2 = p1p2;
        this.p1p1 = p1p1;
        this.p1p0 = p1p0;
        this.p1n1 = p1n1;
        this.p1n2 = p1n2;
        this.p1n3 = p1n3;
        this.p1n4 = p1n4;

        this.n2p3 = n2p3;
        this.n2p2 = n2p2;
        this.n2p1 = n2p1;
        this.n2p0 = n2p0;
        this.n2n1 = n2n1;
        this.n2n2 = n2n2;
        this.n2n3 = n2n3;

        this.p2p3 = p2p3;
        this.p2p2 = p2p2;
        this.p2p1 = p2p1;
        this.p2p0 = p2p0;
        this.p2n1 = p2n1;
        this.p2n2 = p2n2;
        this.p2n3 = p2n3;

        this.n3p3 = n3p3;
        this.n3p2 = n3p2;
        this.n3p1 = n3p1;
        this.n3p0 = n3p0;
        this.n3n1 = n3n1;
        this.n3n2 = n3n2;
        this.n3n3 = n3n3;

        this.p3p3 = p3p3;
        this.p3p2 = p3p2;
        this.p3p1 = p3p1;
        this.p3p0 = p3p0;
        this.p3n1 = p3n1;
        this.p3n2 = p3n2;
        this.p3n3 = p3n3;

        this.n4p1 = n4p1;
        this.n4p0 = n4p0;
        this.n4n1 = n4n1;

        this.p4p1 = p4p1;
        this.p4p0 = p4p0;
        this.p4n1 = p4n1;
    }
    public void paint(ImageView imageView, int x, int y)
    {
        PixelReader pixelReader = imageView.getImage().getPixelReader();
        double width = imageView.getImage().getWidth();
        double height = imageView.getImage().getHeight();
        if(x<=3 || y<=3 || x>width-8 || y>height-8)
        {
            System.out.print("");
        }
        else{
            Color colorP0P0 = pixelReader.getColor(x, y);
            if(colorP0P0 != null)
                p0p0.setStyle("-fx-background-color: #"+ colorP0P0.toString().substring(2));
            else{
                p0p0.setStyle("-fx-background-color: black");
            }
            Color colorP0P4 = pixelReader.getColor(x, y-4);
            if(colorP0P4 != null)
                p0p4.setStyle("-fx-background-color: #"+ colorP0P4.toString().substring(2));
            else{
                p0p4.setStyle("-fx-background-color: black");
            }
            Color colorP0P3 = pixelReader.getColor(x, y-3);
            if(colorP0P3 != null)
                p0p3.setStyle("-fx-background-color: #"+ colorP0P3.toString().substring(2));
            else{
                p0p3.setStyle("-fx-background-color: black");
            }
            Color colorP0P2 = pixelReader.getColor(x, y-2);
            if(colorP0P2 != null)
                p0p2.setStyle("-fx-background-color: #"+ colorP0P2.toString().substring(2));
            else{
                p0p2.setStyle("-fx-background-color: black");
            }
            Color colorP0P1 = pixelReader.getColor(x, y-1);
            if(colorP0P1 != null)
                p0p1.setStyle("-fx-background-color: #"+ colorP0P1.toString().substring(2));
            else{
                p0p1.setStyle("-fx-background-color: black");
            }
            Color colorP0N1 = pixelReader.getColor(x, y+1);
            if(colorP0N1 != null)
                p0n1.setStyle("-fx-background-color: #"+ colorP0N1.toString().substring(2));
            else{
                p0n1.setStyle("-fx-background-color: black");
            }
            Color colorP0N2 = pixelReader.getColor(x, y+2);
            if(colorP0N2 != null)
                p0n2.setStyle("-fx-background-color: #"+ colorP0N2.toString().substring(2));
            else{
                p0n2.setStyle("-fx-background-color: black");
            }
            Color colorP0N3 = pixelReader.getColor(x, y+3);
            if(colorP0N3 != null)
                p0n3.setStyle("-fx-background-color: #"+ colorP0N3.toString().substring(2));
            else{
                p0n3.setStyle("-fx-background-color: black");
            }
            Color colorP0N4 = pixelReader.getColor(x, y+4);
            if(colorP0N4 != null)
                p0n4.setStyle("-fx-background-color: #"+ colorP0N4.toString().substring(2));
            else{
                p0n4.setStyle("-fx-background-color: black");
            }
            Color colorN1P4 = pixelReader.getColor(x-1, y-4);
            if(colorN1P4 != null)
                n1p4.setStyle("-fx-background-color: #"+ colorN1P4.toString().substring(2));
            else{
                n1p4.setStyle("-fx-background-color: black");
            }
            Color colorN1P3 = pixelReader.getColor(x-1, y-3);
            if(colorN1P3 != null)
                n1p3.setStyle("-fx-background-color: #"+ colorN1P3.toString().substring(2));
            else{
                n1p3.setStyle("-fx-background-color: black");
            }
            Color colorN1P2 = pixelReader.getColor(x-1, y-2);
            if(colorN1P2 != null)
                n1p2.setStyle("-fx-background-color: #"+ colorN1P2.toString().substring(2));
            else{
                n1p2.setStyle("-fx-background-color: black");
            }
            Color colorN1P1 = pixelReader.getColor(x-1, y-1);
            if(colorN1P1 != null)
                n1p1.setStyle("-fx-background-color: #"+ colorN1P1.toString().substring(2));
            else{
                n1p1.setStyle("-fx-background-color: black");
            }
            Color colorN1P0 = pixelReader.getColor(x-1, y);
            if(colorN1P0 != null)
                n1p0.setStyle("-fx-background-color: #"+ colorN1P0.toString().substring(2));
            else{
                n1p0.setStyle("-fx-background-color: black");
            }
            Color colorN1N1 = pixelReader.getColor(x-1, y+1);
            if(colorN1N1 != null)
                n1n1.setStyle("-fx-background-color: #"+ colorN1N1.toString().substring(2));
            else{
                n1n1.setStyle("-fx-background-color: black");
            }
            Color colorN1N2 = pixelReader.getColor(x-1, y+2);
            if(colorN1N2 != null)
                n1n2.setStyle("-fx-background-color: #"+ colorN1N2.toString().substring(2));
            else{
                n1n2.setStyle("-fx-background-color: black");
            }
            Color colorN1N3 = pixelReader.getColor(x-1, y+3);
            if(colorN1N3 != null)
                n1n3.setStyle("-fx-background-color: #"+ colorN1N3.toString().substring(2));
            else{
                n1n3.setStyle("-fx-background-color: black");
            }
            Color colorN1N4 = pixelReader.getColor(x-1, y+4);
            if(colorN1N4 != null)
                n1n4.setStyle("-fx-background-color: #"+ colorN1N4.toString().substring(2));
            else{
                n1n4.setStyle("-fx-background-color: black");
            }
            Color colorP1P4 = pixelReader.getColor(x+1, y-4);
            if(colorP1P4 != null)
                p1p4.setStyle("-fx-background-color: #"+ colorP1P4.toString().substring(2));
            else{
                p1p4.setStyle("-fx-background-color: black");
            }
            Color colorP1P3 = pixelReader.getColor(x+1, y-3);
            if(colorP1P3 != null)
                p1p3.setStyle("-fx-background-color: #"+ colorP1P3.toString().substring(2));
            else{
                p1p3.setStyle("-fx-background-color: black");
            }
            Color colorP1P2 = pixelReader.getColor(x+1, y-2);
            if(colorP1P2 != null)
                p1p2.setStyle("-fx-background-color: #"+ colorP1P2.toString().substring(2));
            else{
                p1p2.setStyle("-fx-background-color: black");
            }
            Color colorP1P1 = pixelReader.getColor(x+1, y-1);
            if(colorP1P1 != null)
                p1p1.setStyle("-fx-background-color: #"+ colorP1P1.toString().substring(2));
            else{
                p1p1.setStyle("-fx-background-color: black");
            }
            Color colorP1P0 = pixelReader.getColor(x+1, y);
            if(colorP1P0 != null)
                p1p0.setStyle("-fx-background-color: #"+ colorP1P0.toString().substring(2));
            else{
                p1p0.setStyle("-fx-background-color: black");
            }
            Color colorP1N1 = pixelReader.getColor(x+1, y+1);
            if(colorP1N1 != null)
                p1n1.setStyle("-fx-background-color: #"+ colorP1N1.toString().substring(2));
            else{
                p1n1.setStyle("-fx-background-color: black");
            }
            Color colorP1N2 = pixelReader.getColor(x+1, y+2);
            if(colorP1N2 != null)
                p1n2.setStyle("-fx-background-color: #"+ colorP1N2.toString().substring(2));
            else{
                p1n2.setStyle("-fx-background-color: black");
            }
            Color colorP1N3 = pixelReader.getColor(x+1, y+3);
            if(colorP1N3 != null)
                p1n3.setStyle("-fx-background-color: #"+ colorP1N3.toString().substring(2));
            else{
                p1n3.setStyle("-fx-background-color: black");
            }
            Color colorP1N4 = pixelReader.getColor(x+1, y+4);
            if(colorP1N4 != null)
                p1n4.setStyle("-fx-background-color: #"+ colorP1N4.toString().substring(2));
            else{
                p1n4.setStyle("-fx-background-color: black");
            }
            Color colorN2P3 = pixelReader.getColor(x-2, y-3);
            if(colorN2P3 != null)
                n2p3.setStyle("-fx-background-color: #"+ colorN2P3.toString().substring(2));
            else{
                n2p3.setStyle("-fx-background-color: black");
            }
            Color colorN2P2 = pixelReader.getColor(x-2, y-2);
            if(colorN2P2 != null)
                n2p2.setStyle("-fx-background-color: #"+ colorN2P2.toString().substring(2));
            else{
                n2p2.setStyle("-fx-background-color: black");
            }
            Color colorN2P1 = pixelReader.getColor(x-2, y-1);
            if(colorN2P1 != null)
                n2p1.setStyle("-fx-background-color: #"+ colorN2P1.toString().substring(2));
            else{
                n2p1.setStyle("-fx-background-color: black");
            }
            Color colorN2P0 = pixelReader.getColor(x-2, y);
            if(colorN2P0 != null)
                n2p0.setStyle("-fx-background-color: #"+ colorN2P0.toString().substring(2));
            else{
                n2p0.setStyle("-fx-background-color: black");
            }
            Color colorN2N1 = pixelReader.getColor(x-2, y+1);
            if(colorN2N1 != null)
                n2n1.setStyle("-fx-background-color: #"+ colorN2N1.toString().substring(2));
            else{
                n2n1.setStyle("-fx-background-color: black");
            }
            Color colorN2N2 = pixelReader.getColor(x-2, y+2);
            if(colorN2N2 != null)
                n2n2.setStyle("-fx-background-color: #"+ colorN2N2.toString().substring(2));
            else{
                n2n2.setStyle("-fx-background-color: black");
            }
            Color colorN2N3 = pixelReader.getColor(x-2, y+3);
            if(colorN2N3 != null)
                n2n3.setStyle("-fx-background-color: #"+ colorN2N3.toString().substring(2));
            else{
                n2n3.setStyle("-fx-background-color: black");
            }
            Color colorP2P3 = pixelReader.getColor(x+2, y-3);
            if(colorP2P3 != null)
                p2p3.setStyle("-fx-background-color: #"+ colorP2P3.toString().substring(2));
            else{
                p2p3.setStyle("-fx-background-color: black");
            }
            Color colorP2P2 = pixelReader.getColor(x+2, y-2);
            if(colorP2P2 != null)
                p2p2.setStyle("-fx-background-color: #"+ colorP2P2.toString().substring(2));
            else{
                p2p2.setStyle("-fx-background-color: black");
            }
            Color colorP2P1 = pixelReader.getColor(x+2, y-1);
            if(colorP2P1 != null)
                p2p1.setStyle("-fx-background-color: #"+ colorP2P3.toString().substring(2));
            else{
                p2p1.setStyle("-fx-background-color: black");
            }
            Color colorP2P0 = pixelReader.getColor(x+2, y);
            if(colorP2P0 != null)
                p2p0.setStyle("-fx-background-color: #"+ colorP2P0.toString().substring(2));
            else{
                p2p0.setStyle("-fx-background-color: black");
            }
            Color colorP2N1 = pixelReader.getColor(x+2, y+1);
            if(colorP2N1 != null)
                p2n1.setStyle("-fx-background-color: #"+ colorP2N1.toString().substring(2));
            else{
                p2n1.setStyle("-fx-background-color: black");
            }
            Color colorP2N2 = pixelReader.getColor(x+2, y+2);
            if(colorP2N2 != null)
                p2n2.setStyle("-fx-background-color: #"+ colorP2N2.toString().substring(2));
            else{
                p2n2.setStyle("-fx-background-color: black");
            }
            Color colorP2N3 = pixelReader.getColor(x+2, y+3);
            if(colorP2N3 != null)
                p2n3.setStyle("-fx-background-color: #"+ colorP2N3.toString().substring(2));
            else{
                p2n3.setStyle("-fx-background-color: black");
            }
            Color colorN3P3 = pixelReader.getColor(x-3, y-3);
            if(colorN3P3 != null)
                n3p3.setStyle("-fx-background-color: #"+ colorN3P3.toString().substring(2));
            else{
                n3p3.setStyle("-fx-background-color: black");
            }
            Color colorN3P2 = pixelReader.getColor(x-3, y-2);
            if(colorN3P2 != null)
                n3p2.setStyle("-fx-background-color: #"+ colorN3P2.toString().substring(2));
            else{
                n3p2.setStyle("-fx-background-color: black");
            }
            Color colorN3P1 = pixelReader.getColor(x-3, y-1);
            if(colorN3P1 != null)
                n3p1.setStyle("-fx-background-color: #"+ colorN3P1.toString().substring(2));
            else{
                n3p1.setStyle("-fx-background-color: black");
            }
            Color colorN3P0 = pixelReader.getColor(x-3, y);
            if(colorN3P0 != null)
                n3p0.setStyle("-fx-background-color: #"+ colorN3P0.toString().substring(2));
            else{
                n3p0.setStyle("-fx-background-color: black");
            }
            Color colorN3N1 = pixelReader.getColor(x-3, y+1);
            if(colorN3N1 != null)
                n3n1.setStyle("-fx-background-color: #"+ colorN3N1.toString().substring(2));
            else{
                n3n1.setStyle("-fx-background-color: black");
            }
            Color colorN3N2 = pixelReader.getColor(x-3, y+2);
            if(colorN3N2 != null)
                n3n2.setStyle("-fx-background-color: #"+ colorN3N2.toString().substring(2));
            else{
                n3n2.setStyle("-fx-background-color: black");
            }
            Color colorN3N3 = pixelReader.getColor(x-3, y+3);
            if(colorN3N3 != null)
                n3n3.setStyle("-fx-background-color: #"+ colorN3N3.toString().substring(2));
            else{
                n3n3.setStyle("-fx-background-color: black");
            }
            Color colorP3P3 = pixelReader.getColor(x+3, y-3);
            if(colorP3P3 != null)
                p3p3.setStyle("-fx-background-color: #"+ colorP3P3.toString().substring(2));
            else{
                p3p3.setStyle("-fx-background-color: black");
            }
            Color colorP3P2 = pixelReader.getColor(x+3, y-2);
            if(colorP3P2 != null)
                p3p2.setStyle("-fx-background-color: #"+ colorP3P2.toString().substring(2));
            else{
                p3p2.setStyle("-fx-background-color: black");
            }
            Color colorP3P1 = pixelReader.getColor(x+3, y-1);
            if(colorP3P1 != null)
                p3p1.setStyle("-fx-background-color: #"+ colorP3P1.toString().substring(2));
            else{
                p3p1.setStyle("-fx-background-color: black");
            }
            Color colorP3P0 = pixelReader.getColor(x+3, y);
            if(colorP3P0 != null)
                p3p0.setStyle("-fx-background-color: #"+ colorP3P0.toString().substring(2));
            else{
                p3p0.setStyle("-fx-background-color: black");
            }
            Color colorP3N1 = pixelReader.getColor(x+3, y+1);
            if(colorP3N1 != null)
                p3n1.setStyle("-fx-background-color: #"+ colorP3N1.toString().substring(2));
            else{
                p3n1.setStyle("-fx-background-color: black");
            }
            Color colorP3N2 = pixelReader.getColor(x+3, y+2);
            if(colorP3N2 != null)
                p3n2.setStyle("-fx-background-color: #"+ colorP3N2.toString().substring(2));
            else{
                p3n2.setStyle("-fx-background-color: black");
            }
            Color colorP3N3 = pixelReader.getColor(x+3, y+3);
            if(colorP3N3 != null)
                p3n3.setStyle("-fx-background-color: #"+ colorP3N3.toString().substring(2));
            else{
                p3n3.setStyle("-fx-background-color: black");
            }
            Color colorN4P1 = pixelReader.getColor(x-4, y-1);
            if(colorN4P1 != null)
                n4p1.setStyle("-fx-background-color: #"+ colorN4P1.toString().substring(2));
            else{
                n4p1.setStyle("-fx-background-color: black");
            }
            Color colorN4P0 = pixelReader.getColor(x-4, y);
            if(colorN4P0 != null)
                n4p0.setStyle("-fx-background-color: #"+ colorN4P0.toString().substring(2));
            else{
                n4p0.setStyle("-fx-background-color: black");
            }
            Color colorN4N1 = pixelReader.getColor(x-4, y+1);
            if(colorN4N1 != null)
                n4n1.setStyle("-fx-background-color: #"+ colorN4N1.toString().substring(2));
            else{
                n4n1.setStyle("-fx-background-color: black");
            }
            Color colorP4P1 = pixelReader.getColor(x+4, y-1);
            if(colorP4P1 != null)
                p4p1.setStyle("-fx-background-color: #"+ colorP4P1.toString().substring(2));
            else{
                p4p1.setStyle("-fx-background-color: black");
            }
            Color colorP4P0 = pixelReader.getColor(x+4, y);
            if(colorP4P0 != null)
                p4p0.setStyle("-fx-background-color: #"+ colorP4P0.toString().substring(2));
            else{
                p4p0.setStyle("-fx-background-color: black");
            }
            Color colorP4N1 = pixelReader.getColor(x+4, y+1);
            if(colorP4N1 != null)
                p4n1.setStyle("-fx-background-color: #"+ colorP4P1.toString().substring(2));
            else{
                p4n1.setStyle("-fx-background-color: black");
            }
        }
    }
}
