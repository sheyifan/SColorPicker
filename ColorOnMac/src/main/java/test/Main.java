package test;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.*;
import org.bytedeco.javacv.Frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public class Main
{
    public static void main(String[] args)throws FrameGrabber.Exception
    {
        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
        grabber.start();
        CanvasFrame canvas = new CanvasFrame("摄像头");//新建一个窗口
        grabber.setImageHeight(100);
        canvas.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        canvas.setAlwaysOnTop(true);
        while (true) {
            if (!canvas.isDisplayable()) {//窗口是否关闭
                grabber.stop();//停止抓取
                System.exit(-1);//退出
            }
            //BufferedImage bImage = new BufferedImage(1000, 500, BufferedImage.TYPE_INT_RGB);

            Frame frame = grabber.grab();
            canvas.showImage(frame);//获取摄像头图像并放到窗口上显示， 这里的Frame frame=grabber.grab(); frame是一帧视频图像
            //Thread.sleep(10);//50毫秒刷新一次图像
            canvas.getCanvas().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //使用ToIplImage()将Frame图片转换成Mat类型的图片
                    OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
                    opencv_core.IplImage image = converter.convert(frame);
                    //将Mat类型的图片写入到BufferedImage中去
                    BufferedImage bufferedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_USHORT_GRAY);
                    WritableRaster raster = bufferedImage.getRaster();
                    DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
                    byte[] data = dataBuffer.getData();
                    ((ByteBuffer) image.createBuffer()).get(data);
                    try{
                        ImageIO.write(bufferedImage, "jpg", new File("a.jpg"));
                    }catch(IOException ioe){
                        ioe.printStackTrace();
                    }
                    //采用逐个像素点写入的方式将BufferedImage转换成Image对象
                    WritableImage wr = new WritableImage(bufferedImage.getWidth(), bufferedImage.getHeight());
                    PixelWriter pw = wr.getPixelWriter();
                    for (int x = 0; x < bufferedImage.getWidth(); x++) {
                        for (int y = 0; y < bufferedImage.getHeight(); y++) {
                            pw.setArgb(x, y, bufferedImage.getRGB(x, y));
                        }
                    }
                    Image img = new ImageView(wr).getImage();
                }
            });
        }
    }
}
