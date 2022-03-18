//TODO
/**
 * In Odometry, implement the updatePosition Limelight
 *  - this method should use the heading from the robot, the heading of the turet, and the horizontal angle displacement of the limelight,
 *    as well as the distance, to calculate the x, y cooridnate of the robot.
 */
package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;




public class LimeLight extends SubsystemBase {
  /** Creates a new LimeLight. */
  NetworkTable table;
  NetworkTableEntry tx;
  NetworkTableEntry ty;
  NetworkTableEntry ta;
  NetworkTableEntry tv;
  
  public LimeLight() {
  
    table = NetworkTableInstance.getDefault().getTable("limelight");
    tx = table.getEntry("tx");
    ty = table.getEntry("ty");
    ta = table.getEntry("ta");
    tv = table.getEntry("tv");
    
  }

  

  public double getDistance(){
    double theta = Constants.camera_angle + ty.getDouble(0.0);
    double height = Constants.hub_height - Constants.LimeLightHeight;
    double distance = height / java.lang.Math.tan(theta);
    return distance;
  }
  public double getHorizontalOffset(){
    return tx.getDouble(0.0);
  }

  public boolean isInView(){
    return tv.getBoolean(false);
  }
  

  @Override
  public void periodic() {
   System.out.println(ta.getDouble(0)); //prints area of object
  }
}
//hi