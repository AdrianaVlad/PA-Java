/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import static java.lang.Thread.sleep;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import mappers.Elevator;

/**
 *
 * @author Vlad Adriana
 */
public class ElevatorGrid extends JPanel implements Runnable{
    GridFloors floorNumbers;
    MainFrame frame;
    String buildingName;
    int minFloor,maxFloor,nrColumns,nrLines;
    int W,H;
    BufferedImage image;
    List<Elevator> elevatorList;
    Graphics2D graphics;
    boolean running=true;
    public ElevatorGrid(MainFrame frame, String buildingName){
        this.buildingName=buildingName;
        this.frame=frame;
        floorNumbers = new GridFloors(frame,0,0);
    }
    @Override
    public void run() {
        while(running){
            initValues();
            if(elevatorList.isEmpty())
                return;
            createOffscreenImage();
            initPanel();
            placeElevators();
            repaint();
            floorNumbers.updateValues(minFloor,maxFloor);
            frame.repaint();
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ElevatorGrid.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private void initValues(){
        elevatorList = frame.comms.getElevatorsFor(buildingName);
        if(elevatorList.isEmpty())
            return;
        minFloor = elevatorList.stream().mapToInt(Elevator::getLowestFloor).min().orElseThrow();
        maxFloor = elevatorList.stream().mapToInt(Elevator::getHighestFloor).max().orElseThrow();
        nrColumns=elevatorList.size();
        nrLines=maxFloor-minFloor+1;
        W=750/nrColumns*nrColumns;
        H=450/nrLines*nrLines;
    }
    private void placeElevators(){
        Color color;
        for(int i=0;i<elevatorList.size();i++){
            color = switch (elevatorList.get(i).getStatus()) {
                case "working" -> Color.GREEN;
                case "broken" -> Color.RED;
                default -> Color.GRAY;
            };
            drawLines(i,elevatorList.get(i).getHighestFloor(),elevatorList.get(i).getLowestFloor());
            place(elevatorList.get(i).getCurrentFloor(),i,color);
        }
        drawColumns();
    }
    private void createOffscreenImage() {
        image = new BufferedImage(W+10, H+10, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, W+10, H+10);
    }
     final public void initPanel() {
        setPreferredSize(new Dimension(W+10, H+10));
        setBorder(BorderFactory.createEtchedBorder());
    }
     private void drawColumns(){
        graphics.setColor(Color.BLACK);
        int i,coord;
        for(i=0; i <= nrColumns; i++){
            coord=i*(W/nrColumns);
            graphics.drawLine(coord+5,5,coord+5,H+5);
        }
     }
    private void drawLines(int col, int linHigh, int linLow) {
        graphics.setColor(Color.BLACK);
        int i,coordLin,coordCol;
        coordCol=col*(W/nrColumns);
        for(i=0;i<=(linHigh-linLow)+1;i++){
            coordLin=(maxFloor-linHigh+i)*(H/nrLines);
            graphics.drawLine(coordCol+5, coordLin+5, coordCol+5+W/nrColumns, coordLin+5);
        }
    }
    public void place(int lin, int col, Color color){
        graphics.setColor(color);
        lin=maxFloor-lin;
        graphics.fillRect(col*(W/nrColumns)+5,lin*(H/nrLines)+5,W/nrColumns, H/nrLines);
        repaint();
    }
    @Override
    public void update(Graphics g) {
    }
    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.drawImage(image, 0, 0, this);
    }
}
