/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.misc;

import java.util.Vector;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.CriteriaCollection;
import edu.wpi.first.wpilibj.image.NIVision.MeasurementType;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

/**
 * 
 * @author Neal
 */
public class CameraVision implements Constants {

    private static CameraVision instance;   // CameraVision is a singleton
    AxisCamera camera;          // the axis camera object (connected to the switch)
    CriteriaCollection cc;      // the criteria for doing the particle filter operation
    private Relay targetLight;              // Are-we-aligned? indicator
    private Relay reflectiveLight;          // To make the targets luminous
    private int targetCenter;               // x-coord os the particle center-of-mass
    int CAMERA_CENTER;                      // center of camera width
    Vector massCenter = new Vector();       // list of center-of-mass-es

    public static CameraVision getInstance() {  // CameraVision is a singleton
        if (instance == null) {
            instance = new CameraVision();
        }
        return instance;
    }

    private CameraVision() {
//        targetLight = new Relay(RobotMap.TARGET_LIGHT);
//        targetLight.setDirection(Relay.Direction.kForward);

//        reflectiveLight = new Relay(RobotMap.REFLECTIVE_LIGHT);
//        reflectiveLight.setDirection(Relay.Direction.kForward);

        camera = AxisCamera.getInstance();  // get an instance of the camera
        CAMERA_CENTER = camera.getResolution().width / 2;
        cc = new CriteriaCollection();      // create the criteria for the particle filter
        cc.addCriteria(MeasurementType.IMAQ_MT_BOUNDING_RECT_WIDTH, 30, 240, false);  // any particles at least 30 pixels wide
        cc.addCriteria(MeasurementType.IMAQ_MT_BOUNDING_RECT_HEIGHT, 40, 320, false); // any particles at least 40 pixels high
    }

    public void doCamera() {
        try {
                 // Do the image capture with the camera and apply the algorithm described above.
                ColorImage image = camera.getImage(); // get the image from the camera
                BinaryImage rectImage = image.thresholdHSL(145, 182, 0, 255, 120, 255); // mark only areas that have high
                image.free();                                                                        // luminance (the last two numbers
                                                                                        // are low-high limits
                BinaryImage bigObjectsImage = rectImage.removeSmallObjects(false, 2);  // remove small artifacts
                rectImage.free();
                BinaryImage convexHullImage = bigObjectsImage.convexHull(false);          // fill in occluded rectangles
                bigObjectsImage.free();
                BinaryImage filteredImage = convexHullImage.particleFilter(cc);           // filter particles using our criteria
                convexHullImage.free();
                ParticleAnalysisReport[] reports = filteredImage.getOrderedParticleAnalysisReports();  // get list of results
                massCenter.removeAllElements();                                         // remove previous center-of-mass-es
                                                                                        // for each detected particle
                    ParticleAnalysisReport r = reports[0];
                    int fovFeet = (360 * 2) / r.boundingRectWidth;                          // use it to calculate our field of view
                    massCenter.addElement(new Integer(r.center_mass_x));                    // add each center-of-mass to our list

                
                if (massCenter.size() > 0) {            // if there are center-of-mass-es in the list
                    targetCenter = getCenterMass(0);    // set targetCenter to the largest one (the first one)
                }

                filteredImage.free();
               
            } catch (AxisCameraException ex) {          // this is needed if the camera.getImage() is called
                ex.printStackTrace();
            } catch (NIVisionException ex) {
                ex.printStackTrace();
        }
    }

    /**
     * Displays the center of the largest, rectangular target detected
     * @return targetCenter
     */
    public double getTargetCenter() {
        return targetCenter;    // returns the largest center-of-mass
    }

    public int getCenterMass(int rectid) {
        if (rectid < massCenter.size()) {
            return ((Integer) massCenter.elementAt(rectid)).intValue(); // return the center-of-mass for the specified rectangle
        }
        return -1; // but if trying to find a non-existent rectangle, return nonsense
    }

    public boolean isAligned() {
        double absValue = Math.abs(CAMERA_CENTER - targetCenter);
        return absValue < 20; // if the pixel difference between the center-of-mass
                              // and the center-of-camera, return True

    }

}
