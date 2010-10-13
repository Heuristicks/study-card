import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
/**
 *
 * @author Matt
 */
public class ResizeListener implements ComponentListener{

    private Container c;
    private GraphicsDevice gd;

    public ResizeListener(Container container, GraphicsDevice graphDev) {
        c = container;
        gd = graphDev;
    }

    public void componentResized(ComponentEvent e) {
        if(gd.getFullScreenWindow() == null) {
            Dimension resizedSize = c.getSize();
            int changeInWidth = Math.abs(4 - resizedSize.width);
            int changeInHeight = Math.abs(3 - resizedSize.height);
            if(changeInWidth < changeInHeight) {
                double ratio = resizedSize.height/3;
                resizedSize.width = (int)(4*ratio);
            }
            else {
                double ratio = resizedSize.width/4;
                resizedSize.height = (int)(3*ratio);
            }
            c.setSize(resizedSize);
        }
    }
    public void componentMoved(ComponentEvent e) {}
    public void componentHidden(ComponentEvent e) {}
    public void componentShown(ComponentEvent e) {}
}
