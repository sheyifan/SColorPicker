package white_theme;

import com.sun.javafx.stage.StageHelper;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ObservableList<Stage> stages = StageHelper.getStages();
        //Parent root = FXMLLoader.load(getClass().getResource("black.fxml"));
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("white.fxml")));
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
        pieChart.setLegendVisible(false);
        pieChart.setLegendSide(Side.RIGHT);
        pieChart.setTitle("#ffffff");
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


        colorPane.setOnMousePressed(event -> {
            colorPane.setEffect(new InnerShadow());
            String color = colorPane.getBackground().getFills().get(0).getFill().toString();
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.putString("#".concat(color.substring(2)));
            clipboard.setContent(clipboardContent);
        });
        colorPane.setOnMouseReleased(event -> colorPane.setEffect(null));
        chooseButton.setValue(Color.WHITE);
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
                FileInputStream inFile = new FileInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("res/4_1.jpg")).getPath());
                cut.setImage(new Image(inFile));
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        });
        cut.setOnMouseExited(event -> {
            try{
                FileInputStream inFile = new FileInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("res/3_1.jpg")).getPath());
                cut.setImage(new Image(inFile));
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        });
        cut.setOnMousePressed(event -> cut.setFitWidth(18));
        cut.setOnMouseReleased(event -> cut.setFitWidth(20));
        photo.setOnMouseEntered(event -> {
            try{
                FileInputStream inFile = new FileInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("res/2_1.png")).getPath());
                photo.setImage(new Image(inFile));
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        });
        photo.setOnMouseExited(event -> {
            try{
                FileInputStream inFile = new FileInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("res/1_1.png")).getPath());
                photo.setImage(new Image(inFile));
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        });
        photo.setOnMousePressed(event -> photo.setFitWidth(18));
        photo.setOnMouseReleased(event -> photo.setFitWidth(20));
        file.setOnMouseEntered(event -> {
            try{
                FileInputStream inFile = new FileInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("res/6_1.jpg")).getPath());
                file.setImage(new Image(inFile));
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        });
        file.setOnMouseExited(event -> {
            try{
                FileInputStream inFile = new FileInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("res/5_1.jpg")).getPath());
                file.setImage(new Image(inFile));
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        });
        file.setOnMousePressed(event -> file.setFitWidth(18));
        file.setOnMouseReleased(event -> file.setFitWidth(20));
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
                        primaryStage.setX(10);
                        primaryStage.setY(250);
                        childStage.setX(620);
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
                        imageView.setOnMouseMoved(event1 -> {
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
                        imageView.setOnMousePressed(event1 -> {
                            imageView.setEffect(new InnerShadow());
                            double x = event1.getX();
                            double y = event1.getY();
                            String color = imageView.getImage().getPixelReader().getColor((int)x, (int)y).toString();
                            Clipboard clipboard = Clipboard.getSystemClipboard();
                            ClipboardContent clipboardContent = new ClipboardContent();
                            clipboardContent.putString("#".concat(color.substring(2)));
                            clipboard.setContent(clipboardContent);
                        });
                        imageView.setOnMouseReleased(event1 -> imageView.setEffect(null));
                    }catch(IOException ioe){
                        ioe.printStackTrace();
                    }
                }
            }
        });
        theme.setOnMouseEntered(event -> {
            try{
                FileInputStream inFile = new FileInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("res/8_1.jpg")).getPath());
                theme.setImage(new Image(inFile));
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        });
        theme.setOnMouseExited(event -> {
            try{
                FileInputStream inFile = new FileInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("res/7_1.jpg")).getPath());
                theme.setImage(new Image(inFile));
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        });
        theme.setOnMousePressed(event -> theme.setFitWidth(18));
        theme.setOnMouseReleased(event -> {
            theme.setFitWidth(20);
            SingleSelectionModel selectionModel = tabPane.getSelectionModel();
            selectionModel.select(0);
            RSlider.setValue(255);
            GSlider.setValue(255);
            BSlider.setValue(255);
            OSlider.setValue(255);
        });

        primaryStage.setTitle("Color Pane | Soochow University | version5");
        primaryStage.setX(350);
        primaryStage.setY(250);
        primaryStage.initStyle(StageStyle.DECORATED);
        Scene scene = new Scene(root, 604, 252);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
