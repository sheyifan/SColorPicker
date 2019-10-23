package black_theme;

import com.sun.javafx.stage.StageHelper;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.UUID;

/* H:Hue，色调，取值范围是0到360°。红色为0°，绿色为120°,蓝色为240°
 * S:Saturation，饱和度。表示某种颜色与白光的混合程度，取值范围是0%～100%
 * V:value，亮度。取值范围是0%～100%
 *
 * RGB到HSV的转换公式：
 * R` = R / 255
 * G` = G / 255
 * B` = B / 255
 * cMax = max(R`, G`, B`)
 * cMin = min(R`, G`, B`)
 * v = cMax - cMin
 *
 * H(单位：度):
 *   +-    0°                     ,  v = 0
 *   |
 *H= |     60° * (G`-B`)/v        ,  cMax = R`
 *   |
 *   |     60° * ((B`-R`)/v + 2)  ,  cMax = G`
 *   |
 *   +-    60° * ((R`-G`)/v + 4)  ,  cMax = B`
 *
 * S
 *    +-   0                      ,  cMax = 0
 * S= |
 *    +-   v / cMax               ,  cMax ≠ 0
 *
 * V = cMax
 *
 *
 *
 * HSV到RGB的转换公式
 * C = V * S
 * X = C * (1 - |(H/60)mod2 - 1|)
 * m = V - C
 *                +—— (C, X, 0)    ,    0° ≤ H ≤ 60°
 *                |
 *                |   (X, C, 0)    ,    60° ≤ H ≤ 120°
 *                |
 *                |   (0, C, X)    ,    120° ≤ H ≤ 180°
 * (R`, G`, B`) = |
 *                |   (0, X, C)    ,    180° ≤ H ≤ 240°
 *                |
 *                |   (X, 0, C)    ,    240° ≤ H ≤ 300°
 *                |
 *                +—— (C, 0, X)    ,    300° ≤ H ≤ 360°
 * (R, G, B) = ((R`+m)*255, (G`+m)*255, (B`+m)*255)
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("black.fxml")));
        primaryStage.setTitle("调色取色板");
        primaryStage.setX(350);
        primaryStage.setY(250);
        Scene scene = new Scene(root, 248, 434);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        //建立弹窗，显示软件的具体信息
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("                    软件 : 调色取色器"+"\n"+"                    作者：佘一凡"+"\n"+"                    单位：苏州大学"+"\n"+"                    时间：2019.10"+"\n"+"                    版本 : 5.0");
        alert.setTitle("软件信息");
        alert.setResizable(false);
        alert.setX(300);
        alert.setY(250);
        alert.setHeaderText(null);
        DialogPane sDialogue = alert.getDialogPane();
        ObservableList<Node> ns = alert.getDialogPane().getChildren();
        ns.get(1).setStyle("-fx-background-color: #333333 ; -fx-text-fill: linear-gradient(to right,#00fffc,#fff600)");
        ((ButtonBar)ns.get(2)).getStylesheets().add("button_style.css");
        sDialogue.setStyle("-fx-background-color: #333333 ; -fx-padding: 10px ; -fx-alignment: center");
        ns.get(0).setStyle("-fx-background-color: #333333");
        //显示弹窗。这里会等待用户确认。
        alert.showAndWait();
        //获取所有的窗口Stage
        ObservableList<Stage> stages = StageHelper.getStages();
        //Parent root = FXMLLoader.load(getClass().getResource("black.fxml"));
        //在Maven项目中加载资源文件需要采取下面的写法
        PieChart pieChart = (PieChart)root.lookup("#pieChart");
        ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList(
                new PieChart.Data("R", 0.33333),
                new PieChart.Data("G", 0.33333),
                new PieChart.Data("B", 0.33333)
        );
        pieChart.setData(chartData);
        chartData.get(0).getNode().setStyle("-fx-background-color: red");
        chartData.get(1).getNode().setStyle("-fx-background-color: green");
        chartData.get(2).getNode().setStyle("-fx-background-color: blue");
        pieChart.setStyle("CHART_COLOR_1: red ; CHART_COLOR_2: green ; CHART_COLOR_3: blue");
        pieChart.setLabelsVisible(false);
        pieChart.setLegendVisible(true);
        pieChart.setLegendSide(Side.LEFT);
        pieChart.setTitle("#000000");
        pieChart.setTitleSide(Side.TOP);
        TabPane tabPane = (TabPane)root.lookup("#tabPane");
        tabPane.setSide(Side.TOP);
        Slider RSlider = (Slider)root.lookup("#RSlider");
        RSlider.setMajorTickUnit(1);
        RSlider.setMinorTickCount(1);
        RSlider.setBlockIncrement(1);
        Slider GSlider = (Slider)root.lookup("#GSlider");
        GSlider.setMajorTickUnit(1);
        GSlider.setMinorTickCount(1);
        GSlider.setBlockIncrement(1);
        Slider BSlider = (Slider)root.lookup("#BSlider");
        BSlider.setMajorTickUnit(1);
        BSlider.setMinorTickCount(1);
        BSlider.setBlockIncrement(1);
        Slider OSlider = (Slider)root.lookup("#OSlider");
        OSlider.setMajorTickUnit(1);
        OSlider.setMinorTickCount(1);
        OSlider.setBlockIncrement(1);
        TextField RText = (TextField)root.lookup("#RText");
        TextField GText = (TextField)root.lookup("#GText");
        TextField BText = (TextField)root.lookup("#BText");
        TextField OText = (TextField)root.lookup("#OText");
        TextField webText = (TextField)root.lookup("#webText");
        Pane colorPane = (Pane)root.lookup("#colorPane");
        ColorPicker chooseButton = (ColorPicker)root.lookup("#chooseButton");
        FileChooser fileChooser = new FileChooser();
        ImageView cut = (ImageView)root.lookup("#cut");
        ImageView photo = (ImageView)root.lookup("#photo");
        ImageView file = (ImageView)root.lookup("#file");
        ImageView theme = (ImageView)root.lookup("#theme");
        DirectoryChooser directoryChooser = new DirectoryChooser();
        Slider HSlider = (Slider)root.lookup("#HSlider");
        HSlider.setMajorTickUnit(1);
        HSlider.setMinorTickCount(1);
        HSlider.setBlockIncrement(1);
        Slider SSlider = (Slider)root.lookup("#SSlider");
        SSlider.setMajorTickUnit(1);
        SSlider.setMinorTickCount(1);
        SSlider.setBlockIncrement(1);
        Slider VSlider = (Slider)root.lookup("#VSlider");
        VSlider.setMajorTickUnit(1);
        VSlider.setMinorTickCount(1);
        VSlider.setBlockIncrement(1);
        TextField HText = (TextField)root.lookup("#HText");
        TextField SText = (TextField)root.lookup("#SText");
        TextField VText = (TextField)root.lookup("#VText");
        Label SSLabel = (Label)root.lookup("#SSLabel");
        SSLabel.setText("%");
        SSLabel.setStyle("-fx-text-fill: darkred");
        Label SVLabel = (Label)root.lookup("#SVLabel");
        SVLabel.setText("%");
        SVLabel.setStyle("-fx-text-fill: darkred");
        Label SHLabel = (Label)root.lookup("#SHLabel");
        SHLabel.setText("°");
        SHLabel.setStyle("-fx-text-fill: darkred");


        Pane p0p4 = (Pane)root.lookup("#p0p4");
        Pane p0p3 = (Pane)root.lookup("#p0p3");
        Pane p0p2 = (Pane)root.lookup("#p0p2");
        Pane p0p1 = (Pane)root.lookup("#p0p1");
        Pane p0p0 = (Pane)root.lookup("#p0p0");
        Pane p0n1 = (Pane)root.lookup("#p0n1");
        Pane p0n2 = (Pane)root.lookup("#p0n2");
        Pane p0n3 = (Pane)root.lookup("#p0n3");
        Pane p0n4 = (Pane)root.lookup("#p0n4");

        Pane n1p4 = (Pane)root.lookup("#n1p4");
        Pane n1p3 = (Pane)root.lookup("#n1p3");
        Pane n1p2 = (Pane)root.lookup("#n1p2");
        Pane n1p1 = (Pane)root.lookup("#n1p1");
        Pane n1p0 = (Pane)root.lookup("#n1p0");
        Pane n1n1 = (Pane)root.lookup("#n1n1");
        Pane n1n2 = (Pane)root.lookup("#n1n2");
        Pane n1n3 = (Pane)root.lookup("#n1n3");
        Pane n1n4 = (Pane)root.lookup("#n1n4");

        Pane p1p4 = (Pane)root.lookup("#p1p4");
        Pane p1p3 = (Pane)root.lookup("#p1p3");
        Pane p1p2 = (Pane)root.lookup("#p1p2");
        Pane p1p1 = (Pane)root.lookup("#p1p1");
        Pane p1p0 = (Pane)root.lookup("#p1p0");
        Pane p1n1 = (Pane)root.lookup("#p1n1");
        Pane p1n2 = (Pane)root.lookup("#p1n2");
        Pane p1n3 = (Pane)root.lookup("#p1n3");
        Pane p1n4 = (Pane)root.lookup("#p1n4");

        Pane n2p3 = (Pane)root.lookup("#n2p3");
        Pane n2p2 = (Pane)root.lookup("#n2p2");
        Pane n2p1 = (Pane)root.lookup("#n2p1");
        Pane n2p0 = (Pane)root.lookup("#n2p0");
        Pane n2n1 = (Pane)root.lookup("#n2n1");
        Pane n2n2 = (Pane)root.lookup("#n2n2");
        Pane n2n3 = (Pane)root.lookup("#n2n3");

        Pane p2p3 = (Pane)root.lookup("#p2p3");
        Pane p2p2 = (Pane)root.lookup("#p2p2");
        Pane p2p1 = (Pane)root.lookup("#p2p1");
        Pane p2p0 = (Pane)root.lookup("#p2p0");
        Pane p2n1 = (Pane)root.lookup("#p2n1");
        Pane p2n2 = (Pane)root.lookup("#p2n2");
        Pane p2n3 = (Pane)root.lookup("#p2n3");

        Pane n3p3 = (Pane)root.lookup("#n3p3");
        Pane n3p2 = (Pane)root.lookup("#n3p2");
        Pane n3p1 = (Pane)root.lookup("#n3p1");
        Pane n3p0 = (Pane)root.lookup("#n3p0");
        Pane n3n1 = (Pane)root.lookup("#n3n1");
        Pane n3n2 = (Pane)root.lookup("#n3n2");
        Pane n3n3 = (Pane)root.lookup("#n3n3");

        Pane p3p3 = (Pane)root.lookup("#p3p3");
        Pane p3p2 = (Pane)root.lookup("#p3p2");
        Pane p3p1 = (Pane)root.lookup("#p3p1");
        Pane p3p0 = (Pane)root.lookup("#p3p0");
        Pane p3n1 = (Pane)root.lookup("#p3n1");
        Pane p3n2 = (Pane)root.lookup("#p3n2");
        Pane p3n3 = (Pane)root.lookup("#p3n3");

        Pane n4p1 = (Pane)root.lookup("#n4p1");
        Pane n4p0 = (Pane)root.lookup("#n4p0");
        Pane n4n1 = (Pane)root.lookup("#n4n1");

        Pane p4p1 = (Pane)root.lookup("#p4p1");
        Pane p4p0 = (Pane)root.lookup("#p4p0");
        Pane p4n1 = (Pane)root.lookup("#p4n1");

        colorPane.setOnMousePressed(event -> colorPane.setEffect(new InnerShadow()));
        colorPane.setOnMouseReleased(event -> {
            colorPane.setEffect(null);
            String color = colorPane.getBackground().getFills().get(0).getFill().toString();
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.putString("#".concat(color.substring(2)));
            clipboard.setContent(clipboardContent);
        });
        chooseButton.setValue(Color.BLACK);
        chooseButton.setOnAction(event -> {
            String color16 = chooseButton.getValue().toString();
            String red16;
            red16 = color16.substring(2, 4);
            String green16;
            green16 = color16.substring(4, 6);
            String blue16 = color16.substring(6, 8);
            int redValue = Integer.valueOf(red16, 16);
            int greenValue = Integer.valueOf(green16, 16);
            int blueValue = Integer.valueOf(blue16, 16);
            RSlider.setValue(redValue);
            GSlider.setValue(greenValue);
            BSlider.setValue(blueValue);
        });
        RText.textProperty().addListener((observable, oldValue, newValue) -> {
            char[] RTextChars = newValue.toCharArray();
            boolean flag = true;
            if(RTextChars.length == 0)
                flag = false;
            else{
                for(char s : RTextChars)
                {
                    if(s<'0' || s>'9') {
                        flag = false;
                        RText.setText(oldValue);
                        break;
                    }
                }
            }
            if(flag)
            {
                int RValue = Integer.parseInt(newValue);
                if(RValue>=0 && RValue<=255)
                    RSlider.setValue(RValue);
                else{
                    RText.setText(oldValue);
                    RSlider.setValue(Integer.parseInt(oldValue));
                }
            }
        });
        RText.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue)
            {
                if(RText.getText().equals(""))
                    RText.setText("0");
            }
        });
        GText.textProperty().addListener((observable, oldValue, newValue) -> {
            char[] GTextChars = newValue.toCharArray();
            boolean flag = true;
            if(GTextChars.length == 0)
                flag = false;
            else{
                for(char s : GTextChars)
                {
                    if(s<'0' || s>'9') {
                        flag = false;
                        GText.setText(oldValue);
                        break;
                    }
                }
            }
            if(flag)
            {
                int GValue = Integer.parseInt(newValue);
                if(GValue>=0 && GValue<=255)
                    GSlider.setValue(GValue);
                else{
                    GText.setText(oldValue);
                    GSlider.setValue(Integer.parseInt(oldValue));
                }
            }
        });
        GText.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue)
            {
                if(GText.getText().equals(""))
                    GText.setText("0");
            }
        });
        BText.textProperty().addListener((observable, oldValue, newValue) -> {
            char[] BTextChars = newValue.toCharArray();
            boolean flag = true;
            if(BTextChars.length == 0)
                flag = false;
            else{
                for(char s : BTextChars)
                {
                    if(s<'0' || s>'9') {
                        flag = false;
                        BText.setText(oldValue);
                        break;
                    }
                }
            }
            if(flag)
            {
                int BValue = Integer.parseInt(newValue);
                if(BValue>=0 && BValue<=255)
                    BSlider.setValue(BValue);
                else{
                    BText.setText(oldValue);
                    BSlider.setValue(Integer.parseInt(oldValue));
                }
            }
        });
        BText.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue)
            {
                if(BText.getText().equals(""))
                    BText.setText("0");
            }
        });
        OText.textProperty().addListener((observable, oldValue, newValue) -> {
            char[] OTextChars = newValue.toCharArray();
            boolean flag = true;
            if(OTextChars.length == 0)
                flag = false;
            else{
                for(char s : OTextChars)
                {
                    if(s<'0' || s>'9') {
                        flag = false;
                        OText.setText(oldValue);
                        break;
                    }
                }
            }
            if(flag)
            {
                int OValue = Integer.parseInt(newValue);
                if(OValue>=0 && OValue<=255)
                    OSlider.setValue(OValue);
                else{
                    OText.setText(oldValue);
                    OSlider.setValue(Integer.parseInt(oldValue));
                }
            }
        });
        OText.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue)
            {
                if(OText.getText().equals(""))
                    OText.setText("0");
            }
        });
        webText.textProperty().addListener((observable, oldValue, newValue) -> {
            char[] webTextChars = newValue.toCharArray();
            boolean flag = true;
            if(webTextChars.length == 0)
                flag = false;
            if(webTextChars.length == 9)
                webText.setText(oldValue);
            else{
                for(char s : webTextChars)
                {
                    if(s>='0' && s<='9' || s>='a' && s<='f')
                    {
                        System.out.print("");
                    }
                    else{
                        flag = false;
                        webText.setText(oldValue);
                        break;
                    }
                }
            }
            if(flag)
            {
                if(webTextChars.length==6 || webTextChars.length==8) {
                    if(webTextChars.length == 6)
                    {
                        String R16 = newValue.substring(0, 2);
                        String G16 = newValue.substring(2, 4);
                        String B16 = newValue.substring(4, 6);
                        RSlider.setValue(Integer.valueOf(R16, 16));
                        GSlider.setValue(Integer.valueOf(G16, 16));
                        BSlider.setValue(Integer.valueOf(B16, 16));
                    }
                    else{
                        String R16 = newValue.substring(0, 2);
                        String G16 = newValue.substring(2, 4);
                        String B16 = newValue.substring(4, 6);
                        String O16 = newValue.substring(6);
                        RSlider.setValue(Integer.valueOf(R16, 16));
                        GSlider.setValue(Integer.valueOf(G16, 16));
                        BSlider.setValue(Integer.valueOf(B16, 16));
                        OSlider.setValue(Integer.valueOf(O16, 16));
                    }
                }
            }
        });
        webText.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue)
            {
                if(OSlider.getValue() == 255)
                {
                    webText.setText(Integer.toString(Integer.parseInt(RText.getText()), 16).concat(Integer.toString(Integer.parseInt(GText.getText()), 16)).concat(Integer.toString(Integer.parseInt(BText.getText()), 16)));
                }
                else{
                    webText.setText(Integer.toString(Integer.parseInt(RText.getText()), 16).concat(Integer.toString(Integer.parseInt(GText.getText()), 16)).concat(Integer.toString(Integer.parseInt(BText.getText()), 16)).concat(Integer.toString(Integer.parseInt(OText.getText()), 16)));
                }
            }
        });
        RSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            RText.setText(Integer.toString(newValue.intValue()));
            String RText16 = Integer.toString((int)(RSlider.getValue()), 16);
            if(RText16.length() == 1)
                RText16 = "0".concat(RText16);
            String chartTitle = pieChart.getTitle();
            String color16 = chartTitle.substring(0, 1).concat(RText16).concat(chartTitle.substring(3));
            pieChart.setTitle(color16);
            webText.setText(color16.substring(1));
            colorPane.setStyle("-fx-background-color: " + color16);
            double redValue = RSlider.getValue();
            double greenValue = GSlider.getValue();
            double blueValue = BSlider.getValue();
            double redPercent, greenPercent, bluePercent;
            if(redValue==0 && greenValue==0 && blueValue==0)
            {
                redPercent = 0.33333;
                greenPercent = 0.33333;
                bluePercent = 0.33333;
            }
            else{
                redPercent = redValue/(redValue+greenValue+blueValue);
                greenPercent = greenValue/(redValue+greenValue+blueValue);
                bluePercent = blueValue/(redValue+greenValue+blueValue);
            }
            chartData.get(0).setPieValue(redPercent);
            chartData.get(1).setPieValue(greenPercent);
            chartData.get(2).setPieValue(bluePercent);
            //下面有一大段被注释的程序。它的作用是监听RGB的数值从而控制HSV。但是这是危险的，以为HSV已经被监听用来控制RGB，两者相互控制会造成死锁。
            /*
            double r1 = redValue / 255;
            double g1 = greenValue / 255;
            double b1 = blueValue / 255;
            double cMax = Math.max(Math.max(r1, g1), b1);
            double cMin = Math.min(Math.min(r1 , g1), b1);
            double del = cMax - cMin;
            double h = 0;
            if(del == 0)
                h = 0;
            else if(cMax == r1) {
                h = 60 * (g1-b1)/del;
            }
            else if(cMax == g1) {
                h = 60 * ((b1-r1)/del + 2);
            }
            else if(cMax == b1) {
                h = 60 * ((r1-g1)/del + 4);
            }
            double s;
            if(cMax == 0)
                s = 0;
            else{
                s = del/cMax;
            }
            double v = cMax;
            HSlider.setValue(h);
            SSlider.setValue(s*255);
            VSlider.setValue(v*255);
             */
        });
        GSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            GText.setText(Integer.toString(newValue.intValue()));
            String GText16 = Integer.toString((int)(GSlider.getValue()), 16);
            if(GText16.length() == 1)
                GText16 = "0".concat(GText16);
            String chartTitle = pieChart.getTitle();
            String color16 = chartTitle.substring(0, 3).concat(GText16).concat(chartTitle.substring(5));
            pieChart.setTitle(color16);
            webText.setText(color16.substring(1));
            colorPane.setStyle("-fx-background-color: " + color16);
            double redValue = RSlider.getValue();
            double greenValue = GSlider.getValue();
            double blueValue = BSlider.getValue();
            double redPercent, greenPercent, bluePercent;
            if(redValue==0 && greenValue==0 && blueValue==0)
            {
                redPercent = 0.33333;
                greenPercent = 0.33333;
                bluePercent = 0.33333;
            }
            else{
                redPercent = redValue/(redValue+greenValue+blueValue);
                greenPercent = greenValue/(redValue+greenValue+blueValue);
                bluePercent = blueValue/(redValue+greenValue+blueValue);
            }
            chartData.get(0).setPieValue(redPercent);
            chartData.get(1).setPieValue(greenPercent);
            chartData.get(2).setPieValue(bluePercent);
            /*
            double r1 = redValue / 255;
            double g1 = greenValue / 255;
            double b1 = blueValue / 255;
            double cMax = Math.max(Math.max(r1 , g1), b1);
            double cMin = Math.min(Math.min(r1 , g1), b1);
            double del = cMax - cMin;
            double h;
            if(del == 0)
                h = 0;
            else if(cMax == r1)
                h = 60 * (g1-b1)/del;
            else if(cMax == g1)
                h = 60 * ((b1-r1)/del + 2);
            else
                h = 60 * ((r1-g1)/del + 4);
            double s;
            if(cMax == 0)
                s = 0;
            else{
                s = del/cMax;
            }
            double v = cMax;
            HSlider.setValue(h);
            SSlider.setValue(s*255);
            VSlider.setValue(v*255);
             */
        });
        BSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            BText.setText(Integer.toString(newValue.intValue()));
            String BText16 = Integer.toString((int)(BSlider.getValue()), 16);
            if(BText16.length() == 1)
                BText16 = "0".concat(BText16);
            String chartTitle = pieChart.getTitle();
            String color16 = chartTitle.substring(0, 5).concat(BText16);
            pieChart.setTitle(color16);
            webText.setText(color16.substring(1));
            colorPane.setStyle("-fx-background-color: " + color16);
            double redValue = RSlider.getValue();
            double greenValue = GSlider.getValue();
            double blueValue = BSlider.getValue();
            double redPercent, greenPercent, bluePercent;
            if(redValue==0 && greenValue==0 && blueValue==0)
            {
                redPercent = 0.33333;
                greenPercent = 0.33333;
                bluePercent = 0.33333;
            }
            else{
                redPercent = redValue/(redValue+greenValue+blueValue);
                greenPercent = greenValue/(redValue+greenValue+blueValue);
                bluePercent = blueValue/(redValue+greenValue+blueValue);
            }
            chartData.get(0).setPieValue(redPercent);
            chartData.get(1).setPieValue(greenPercent);
            chartData.get(2).setPieValue(bluePercent);
            /*
            double r1 = redValue / 255;
            double g1 = greenValue / 255;
            double b1 = blueValue / 255;
            double cMax = Math.max(Math.max(r1 , g1), b1);
            double cMin = Math.min(Math.min(r1 , g1), b1);
            double del = cMax - cMin;
            double h;
            if(del == 0)
                h = 0;
            else if(cMax == r1) {
                h = 60 * (g1-b1)/del;
            }
            else if(cMax == g1)
                h = 60 * ((b1-r1)/del + 2);
            else
                h = 60 * ((r1-g1)/del + 4);
            double s;
            if(cMax == 0)
                s = 0;
            else{
                s = del/cMax;
            }
            double v = cMax;
            HSlider.setValue(h);
            SSlider.setValue(s*255);
            VSlider.setValue(v*255);
             */
        });
        HSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.intValue() != oldValue.intValue())
            {
                HText.setText(Integer.toString(newValue.intValue()));
                double H = HSlider.getValue();
                double S = SSlider.getValue()/255;
                double V = VSlider.getValue()/255;
                double C = V * S;
                double X = C * (1 - Math.abs((H/60)%2 - 1));
                double m = V - C;
                double R0, G0, B0;
                if(H>=0 && H<=60)
                {
                    R0 = C;
                    G0 = X;
                    B0 = 0;
                }
                else if(H>=60 && H<=120)
                {
                    R0 = X;
                    G0 = C;
                    B0 = 0;
                }
                else if(H>=120 && H<=180)
                {
                    R0 = 0;
                    G0 = C;
                    B0 = X;
                }
                else if(H>=180 && H<=240)
                {
                    R0 = 0;
                    G0 = X;
                    B0 = C;
                }
                else if(H>=240 && H<=300)
                {
                    R0 = X;
                    G0 = 0;
                    B0 = C;
                }
                else{
                    R0 = C;
                    G0 = 0;
                    B0 = X;
                }
                double R = (R0+m) * 255;
                double G = (G0+m) * 255;
                double B = (B0+m) * 255;
                RSlider.setValue((int)R);
                GSlider.setValue((int)G);
                BSlider.setValue((int)B);
            }
        });
        SSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.intValue() != oldValue.intValue())
            {
                SText.setText(new DecimalFormat("##0.0").format(newValue.doubleValue()/255*100));
                double S = SSlider.getValue()/255;
                double V = VSlider.getValue()/255;
                double H = HSlider.getValue();
                double C = V * S;
                double X = C * (1 - Math.abs((H/60)%2 - 1));
                double m = V - C;
                double R0, G0, B0;
                if(H>=0 && H<=60)
                {
                    R0 = C;
                    G0 = X;
                    B0 = 0;
                }
                else if(H>=60 && H<=120)
                {
                    R0 = X;
                    G0 = C;
                    B0 = 0;
                }
                else if(H>=120 && H<=180)
                {
                    R0 = 0;
                    G0 = C;
                    B0 = X;
                }
                else if(H>=180 && H<=240)
                {
                    R0 = 0;
                    G0 = X;
                    B0 = C;
                }
                else if(H>=240 && H<=300)
                {
                    R0 = X;
                    G0 = 0;
                    B0 = C;
                }
                else{
                    R0 = C;
                    G0 = 0;
                    B0 = X;
                }
                double R = (R0+m) * 255;
                double G = (G0+m) * 255;
                double B = (B0+m) * 255;
                RSlider.setValue((int)R);
                GSlider.setValue((int)G);
                BSlider.setValue((int)B);
            }
        });
        VSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            VText.setText(new DecimalFormat("##0.0").format(newValue.doubleValue()/255*100));
            double H = HSlider.getValue();
            double S = SSlider.getValue()/255;
            double V = VSlider.getValue()/255;
            double C = V * S;
            double X = C * (1 - Math.abs((H/60)%2 - 1));
            double m = V - C;
            double R0, G0, B0;
            if(H>=0 && H<=60)
            {
                R0 = C;
                G0 = X;
                B0 = 0;
            }
            else if(H>=60 && H<=120)
            {
                R0 = X;
                G0 = C;
                B0 = 0;
            }
            else if(H>=120 && H<=180)
            {
                R0 = 0;
                G0 = C;
                B0 = X;
            }
            else if(H>=180 && H<=240)
            {
                R0 = 0;
                G0 = X;
                B0 = C;
            }
            else if(H>=240 && H<=300)
            {
                R0 = X;
                G0 = 0;
                B0 = C;
            }
            else{
                R0 = C;
                G0 = 0;
                B0 = X;
            }
            double R = (R0+m) * 255;
            double G = (G0+m) * 255;
            double B = (B0+m) * 255;
            RSlider.setValue((int)R);
            GSlider.setValue((int)G);
            BSlider.setValue((int)B);
        });
        OSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            OText.setText(Integer.toString(newValue.intValue()));
            String OText16 = Integer.toString((int)(OSlider.getValue()), 16);
            if(OText16.length() == 1)
                OText16 = "0".concat(OText16);
            String chartTitle = pieChart.getTitle();
            String color16 = chartTitle.substring(0, 7).concat(OText16);
            colorPane.setStyle("-fx-background-color: " + color16);
            if(OText.getText().equals("255"))
            {
                webText.setText(chartTitle.substring(1,7));
            }
            else{
                webText.setText(chartTitle.substring(1,7).concat(OText16));
            }
        });
        cut.setOnMouseEntered(event -> {
            try{
                //FileInputStream inFile = new FileInputStream(getClass().getResource("res/4.jpg").getPath());
                FileInputStream inFile = new FileInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("res/2.jpg")).getPath());
                cut.setImage(new Image(inFile));
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        });
        cut.setOnMouseExited(event -> {
            try{
                FileInputStream inFile = new FileInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("res/4.jpg")).getPath());
                cut.setImage(new Image(inFile));
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        });
        cut.setOnMousePressed(event -> cut.setFitWidth(15));
        cut.setOnMouseReleased(event -> cut.setFitWidth(17));
        photo.setOnMouseEntered(event -> {
            try{
                FileInputStream inFile = new FileInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("res/3.png")).getPath());
                photo.setImage(new Image(inFile));
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        });
        photo.setOnMouseExited(event -> {
            try{
                FileInputStream inFile = new FileInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("res/1.png")).getPath());
                photo.setImage(new Image(inFile));
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        });
        photo.setOnMousePressed(event -> photo.setFitWidth(15));
        photo.setOnMouseReleased(event -> photo.setFitWidth(17));
        photo.setOnMouseClicked(event -> {
            int stageQuantity = stages.size();
            if(stageQuantity == 1)
            {
                try{
                    Parent photoChild = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("photo_child.fxml")));
                    Stage childStage = new Stage();
                    primaryStage.setX(10);
                    primaryStage.setY(250);
                    childStage.setX(260);
                    childStage.setY(10);
                    //childStage.setResizable(false);
                    ImageView capturePhotoView = (ImageView)photoChild.lookup("#capture_photo");
                    Button quitButton = (Button)photoChild.lookup("#quit");
                    Button saveButton = (Button)photoChild.lookup("#save");
                    Separator separator = (Separator)photoChild.lookup("#separator");


                    OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
                    grabber.start();
                    Frame frame = grabber.grab();
                    OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
                    opencv_core.IplImage image = converter.convert(frame);
                    //将Mat类型的图片写入到BufferedImage中去
                    BufferedImage bufferedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_3BYTE_BGR);
                    WritableRaster raster = bufferedImage.getRaster();
                    DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
                    byte[] data = dataBuffer.getData();
                    ((ByteBuffer) image.createBuffer()).get(data);
                    //采用逐个像素点写入的方式将BufferedImage转换成Image对象
                    WritableImage wr = new WritableImage(bufferedImage.getWidth(), bufferedImage.getHeight());
                    PixelWriter pw = wr.getPixelWriter();
                    for (int x = 0; x < bufferedImage.getWidth(); x++) {
                        for (int y = 0; y < bufferedImage.getHeight(); y++) {
                            pw.setArgb(x, y, bufferedImage.getRGB(x, y));
                        }
                    }
                    Image img = new ImageView(wr).getImage();
                    capturePhotoView.setImage(img);
                    double width = img.getWidth();
                    double height = img.getHeight();

                    grabber.stop();
                    separator.setPrefWidth(width);
                    separator.setLayoutY(height);
                    Scene childScene = new Scene(photoChild, width, height+300);
                    childStage.setScene(childScene);

                    capturePhotoView.setFitWidth(width);
                    capturePhotoView.setFitHeight(height);

                    childStage.setHeight(height);
                    childStage.show();

                    capturePhotoView.setOnMouseDragExited(event1 -> System.out.println("Release"));
                    capturePhotoView.setOnMouseDragged(event1 -> {
                        double x = event1.getX();
                        double y = event1.getY();
                        String color = img.getPixelReader().getColor((int)x, (int)y).toString();
                        int red = Integer.valueOf(color.substring(2, 4), 16);
                        int green = Integer.valueOf(color.substring(4, 6), 16);
                        int blue = Integer.valueOf(color.substring(6, 8), 16);
                        int opacity = Integer.valueOf(color.substring(8), 16);
                        RSlider.setValue(red);
                        GSlider.setValue(green);
                        BSlider.setValue(blue);
                        OSlider.setValue(opacity);
                        Pixels pixels = new Pixels(
                                p0p4, p0p3, p0p2, p0p1, p0p0, p0n1, p0n2, p0n3, p0n4,

                                n1p4, n1p3, n1p2, n1p1, n1p0, n1n1, n1n2, n1n3, n1n4,
                                p1p4, p1p3, p1p2, p1p1, p1p0 ,p1n1, p1n2, p1n3, p1n4,

                                n2p3, n2p2, n2p1, n2p0, n2n1, n2n2, n2n3,
                                p2p3, p2p2, p2p1, p2p0, p2n1, p2n2, p2n3,

                                n3p3, n3p2, n3p1, n3p0, n3n1, n3n2, n3n3,
                                p3p3, p3p2, p3p1, p3p0, p3n1, p3n2, p3n3,

                                n4p1, n4p0, n4n1,
                                p4p1, p4p0, p4n1
                        );
                        pixels.paint(capturePhotoView, (int)x, (int)y);
                    });
                    capturePhotoView.setOnMousePressed(event1 -> capturePhotoView.setEffect(new InnerShadow()));
                    capturePhotoView.setOnMouseReleased(event1 -> {
                        capturePhotoView.setEffect(null);
                        double x = event1.getX();
                        double y = event1.getY();
                        String color = img.getPixelReader().getColor((int)x, (int)y).toString();
                        Clipboard clipboard = Clipboard.getSystemClipboard();
                        ClipboardContent clipboardContent = new ClipboardContent();
                        clipboardContent.putString("#".concat(color.substring(2)));
                        clipboard.setContent(clipboardContent);
                        int red = Integer.valueOf(color.substring(2, 4), 16);
                        int green = Integer.valueOf(color.substring(4, 6), 16);
                        int blue = Integer.valueOf(color.substring(6, 8), 16);
                        int opacity = Integer.valueOf(color.substring(8), 16);
                        RSlider.setValue(red);
                        GSlider.setValue(green);
                        BSlider.setValue(blue);
                        OSlider.setValue(opacity);
                        Pixels pixels = new Pixels(
                                p0p4, p0p3, p0p2, p0p1, p0p0, p0n1, p0n2, p0n3, p0n4,

                                n1p4, n1p3, n1p2, n1p1, n1p0, n1n1, n1n2, n1n3, n1n4,
                                p1p4, p1p3, p1p2, p1p1, p1p0 ,p1n1, p1n2, p1n3, p1n4,

                                n2p3, n2p2, n2p1, n2p0, n2n1, n2n2, n2n3,
                                p2p3, p2p2, p2p1, p2p0, p2n1, p2n2, p2n3,

                                n3p3, n3p2, n3p1, n3p0, n3n1, n3n2, n3n3,
                                p3p3, p3p2, p3p1, p3p0, p3n1, p3n2, p3n3,

                                n4p1, n4p0, n4n1,
                                p4p1, p4p0, p4n1
                        );
                        pixels.paint(capturePhotoView, (int)x, (int)y);
                    });

                    saveButton.setOnAction(event1 -> {
                        File dir = directoryChooser.showDialog(childStage);
                        if(dir == null)
                            System.out.print("");
                        else{
                            try{
                                String fileName = "capture.jpg";
                                File checkFile = new File(dir + File.separator + fileName);
                                if(checkFile.exists())
                                    fileName = UUID.randomUUID().toString().concat("_capture.jpg");
                                ImageIO.write(bufferedImage, "jpg", new File(dir + File.separator + fileName));
                                Alert saveSuccessfully = new Alert(Alert.AlertType.INFORMATION);
                                saveSuccessfully.setTitle("保存照片");
                                File sr = new File(dir + File.separator + "capture.jpg");
                                if(sr.exists())
                                {
                                    saveSuccessfully.setContentText("保存成功！");
                                    saveSuccessfully.setResizable(false);
                                    saveSuccessfully.showAndWait();
                                }
                                else{
                                    saveSuccessfully.setAlertType(Alert.AlertType.ERROR);
                                    saveSuccessfully.setContentText("保存失败！");
                                    saveSuccessfully.setResizable(false);
                                    saveSuccessfully.showAndWait();
                                }
                            }catch(IOException ioe){
                                ioe.printStackTrace();
                            }
                        }
                    });
                    quitButton.setOnAction(event1 -> childStage.close());
                    childStage.showingProperty().addListener((observable, oldValue, newValue) -> {
                        if (!newValue) {
                            primaryStage.setX(350);
                            primaryStage.setY(250);
                        }
                    });
                    capturePhotoView.setOnMouseMoved(event1 -> {

                    });
                }catch(IOException ioe){
                    ioe.printStackTrace();
                }
            }
        });
        file.setOnMouseEntered(event -> {
            try{
                FileInputStream inFile = new FileInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("res/6.jpg")).getPath());
                file.setImage(new Image(inFile));
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        });
        file.setOnMouseExited(event -> {
            try{
                FileInputStream inFile = new FileInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("res/5.jpg")).getPath());
                file.setImage(new Image(inFile));
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        });
        file.setOnMousePressed(event -> file.setFitWidth(15));
        file.setOnMouseReleased(event -> file.setFitWidth(17));
        file.setOnMouseClicked(event -> {
            int stageQuantity = stages.size();
            if(stageQuantity == 1)
            {
                File path = fileChooser.showOpenDialog(primaryStage);
                if(path == null)
                {
                    System.err.println("图片打开失败。\n");
                }
                else{
                    System.out.println(path);
                    try{
                        primaryStage.setX(10);
                        primaryStage.setY(250);
                        FileInputStream inFile = new FileInputStream(path);
                        Image image = new Image(inFile);
                        double width = image.getWidth();
                        double height = image.getHeight();
                        if(width>750.0)
                            System.err.println("图片宽度超标，建议缩放至750px之内再进行处理。");
                        if(height>900.0)
                            System.err.println("图片高度超标，建议缩放至900px之内再进行处理。");
                        System.out.println("图片宽度："+ width+ "; 图片高度：" + height + ";");
                        // Parent children = FXMLLoader.load(getClass().getResource("children.fxml"));
                        Parent children = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("children.fxml")));
                        ImageView imageView = (ImageView)children.lookup("#imageView");
                        imageView.setImage(image);
                        imageView.setFitWidth(width);
                        imageView.setFitHeight(height);
                        Stage childStage = new Stage();
                        Scene childScene = new Scene(children, width, height);
                        childStage.setScene(childScene);
                        childStage.setResizable(false);
                        childStage.initStyle(StageStyle.UTILITY);
                        childStage.show();
                        childStage.setX(260);
                        childStage.setY(10);

                        childStage.showingProperty().addListener((observable, oldValue, newValue) -> {
                            if(!newValue)
                            {
                                primaryStage.setX(350);
                                primaryStage.setY(250);
                                p0p4.setStyle("-fx-background-color: black");
                                p0p3.setStyle("-fx-background-color: black");
                                p0p2.setStyle("-fx-background-color: black");
                                p0p1.setStyle("-fx-background-color: black");
                                p0p0.setStyle("-fx-background-color: black");
                                p0n1.setStyle("-fx-background-color: black");
                                p0n2.setStyle("-fx-background-color: black");
                                p0n3.setStyle("-fx-background-color: black");
                                p0n4.setStyle("-fx-background-color: black");
                                n1p4.setStyle("-fx-background-color: black");
                                n1p3.setStyle("-fx-background-color: black");
                                n1p2.setStyle("-fx-background-color: black");
                                n1p1.setStyle("-fx-background-color: black");
                                n1p0.setStyle("-fx-background-color: black");
                                n1n1.setStyle("-fx-background-color: black");
                                n1n2.setStyle("-fx-background-color: black");
                                n1n3.setStyle("-fx-background-color: black");
                                n1n4.setStyle("-fx-background-color: black");
                                p1p4.setStyle("-fx-background-color: black");
                                p1p3.setStyle("-fx-background-color: black");
                                p1p2.setStyle("-fx-background-color: black");
                                p1p1.setStyle("-fx-background-color: black");
                                p1p0.setStyle("-fx-background-color: black");
                                p1n1.setStyle("-fx-background-color: black");
                                p1n2.setStyle("-fx-background-color: black");
                                p1n3.setStyle("-fx-background-color: black");
                                p1n4.setStyle("-fx-background-color: black");
                                n2p3.setStyle("-fx-background-color: black");
                                n2p2.setStyle("-fx-background-color: black");
                                n2p1.setStyle("-fx-background-color: black");
                                n2p0.setStyle("-fx-background-color: black");
                                n2n1.setStyle("-fx-background-color: black");
                                n2n2.setStyle("-fx-background-color: black");
                                n2n3.setStyle("-fx-background-color: black");
                                p2p3.setStyle("-fx-background-color: black");
                                p2p2.setStyle("-fx-background-color: black");
                                p2p1.setStyle("-fx-background-color: black");
                                p2p0.setStyle("-fx-background-color: black");
                                p2n1.setStyle("-fx-background-color: black");
                                p2n2.setStyle("-fx-background-color: black");
                                p2n3.setStyle("-fx-background-color: black");
                                n3p3.setStyle("-fx-background-color: black");
                                n3p2.setStyle("-fx-background-color: black");
                                n3p1.setStyle("-fx-background-color: black");
                                n3p0.setStyle("-fx-background-color: black");
                                n3n1.setStyle("-fx-background-color: black");
                                n3n2.setStyle("-fx-background-color: black");
                                n3n3.setStyle("-fx-background-color: black");
                                p3p3.setStyle("-fx-background-color: black");
                                p3p2.setStyle("-fx-background-color: black");
                                p3p1.setStyle("-fx-background-color: black");
                                p3p0.setStyle("-fx-background-color: black");
                                p3n1.setStyle("-fx-background-color: black");
                                p3n2.setStyle("-fx-background-color: black");
                                p3n3.setStyle("-fx-background-color: black");
                                n4p1.setStyle("-fx-background-color: black");
                                n4p0.setStyle("-fx-background-color: black");
                                n4n1.setStyle("-fx-background-color: black");
                                p4p1.setStyle("-fx-background-color: black");
                                p4p0.setStyle("-fx-background-color: black");
                                p4n1.setStyle("-fx-background-color: black");
                            }
                        });
                        imageView.setOnMouseDragged(event1 -> {
                            double x = event1.getX();
                            double y = event1.getY();
                            String color = imageView.getImage().getPixelReader().getColor((int)x, (int)y).toString();
                            int red = Integer.valueOf(color.substring(2, 4), 16);
                            int green = Integer.valueOf(color.substring(4, 6), 16);
                            int blue = Integer.valueOf(color.substring(6, 8), 16);
                            int opacity = Integer.valueOf(color.substring(8), 16);
                            RSlider.setValue(red);
                            GSlider.setValue(green);
                            BSlider.setValue(blue);
                            OSlider.setValue(opacity);
                            Pixels pixels = new Pixels(
                                    p0p4, p0p3, p0p2, p0p1, p0p0, p0n1, p0n2, p0n3, p0n4,

                                    n1p4, n1p3, n1p2, n1p1, n1p0, n1n1, n1n2, n1n3, n1n4,
                                    p1p4, p1p3, p1p2, p1p1, p1p0 ,p1n1, p1n2, p1n3, p1n4,

                                    n2p3, n2p2, n2p1, n2p0, n2n1, n2n2, n2n3,
                                    p2p3, p2p2, p2p1, p2p0, p2n1, p2n2, p2n3,

                                    n3p3, n3p2, n3p1, n3p0, n3n1, n3n2, n3n3,
                                    p3p3, p3p2, p3p1, p3p0, p3n1, p3n2, p3n3,

                                    n4p1, n4p0, n4n1,
                                    p4p1, p4p0, p4n1
                            );
                            pixels.paint(imageView, (int)x, (int)y);
                        });
                        imageView.setOnMousePressed(event1 -> imageView.setEffect(new InnerShadow()));
                        imageView.setOnMouseReleased(event1 -> {
                            imageView.setEffect(null);
                            double x = event1.getX();
                            double y = event1.getY();
                            String color = imageView.getImage().getPixelReader().getColor((int)x, (int)y).toString();
                            Clipboard clipboard = Clipboard.getSystemClipboard();
                            ClipboardContent clipboardContent = new ClipboardContent();
                            clipboardContent.putString("#".concat(color.substring(2)));
                            clipboard.setContent(clipboardContent);
                        });
                    }catch(IOException ioe){
                        ioe.printStackTrace();
                    }
                }
            }
        });
        theme.setOnMouseEntered(event -> {
            try{
                FileInputStream inFile = new FileInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("res/8.jpg")).getPath());
                theme.setImage(new Image(inFile));
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        });
        theme.setOnMouseExited(event -> {
            try{
                FileInputStream inFile = new FileInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("res/7.jpg")).getPath());
                theme.setImage(new Image(inFile));
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        });
        theme.setOnMousePressed(event -> theme.setFitWidth(15));
        theme.setOnMouseReleased(event -> {
            theme.setFitWidth(17);
            SingleSelectionModel selectionModel = tabPane.getSelectionModel();
            selectionModel.select(0);
            RSlider.setValue(0);
            GSlider.setValue(0);
            BSlider.setValue(0);
            OSlider.setValue(255);
            HSlider.setValue(0);
            SSlider.setValue(0);
            VSlider.setValue(0);
            int stageQuantity = stages.size();
            if(stageQuantity != 1)
            {
                for(int i=1 ; i<stageQuantity ; i++)
                    stages.get(i).close();
            }
        });


        primaryStage.show();
    }
}
