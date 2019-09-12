package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Turning;
import org.firstinspires.ftc.teamcode.ColorSensorV;
import java.util.concurrent.TimeUnit;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.android.AndroidTextToSpeech;
import org.firstinspires.ftc.robotcore.external.android.AndroidSoundPool;



@Autonomous
public class AutonomousCraterNew extends LO2Library {

    Turning turning = new Turning(-135);

    boolean gold = false;
    int step = 1;
    int condition = 0;
    boolean goldNow = false;
    ElapsedTime eTimeObj = new ElapsedTime();
    imuData imu;
    float timeDone = 0;
    ColorSensorV colorSensor;
    float timer1;
    boolean isDelay;
    DcMotor latchM;
    boolean GoldNow;
    void nextStep(float delay) {
        if(!isDelay) {
            isDelay = true;
            timeDone = timer1 + delay;
        }
        if(timer1 >= timeDone) {
            drive(0, 0, 0, 0);
            isDelay = false;
            step++;
        }

    }
    void nextStep(float delay, int stepNum) {
        if(!isDelay){
            timeDone = timer1 + delay;
            isDelay = true;
        }
        if(timer1 >= timeDone) {
            drive(0, 0, 0, 0);
            isDelay = false;
            step = stepNum;
        }
    }
    boolean haveInit = false;
    @Override
    public void init() {
        AndroidTextToSpeech stt = new AndroidTextToSpeech();
        AndroidSoundPool asp = new AndroidSoundPool();
        stt.initialize();
        stt.speak("Hello, Adam Carita");
        super.initialize_robot();
        latchM = hardwareMap.dcMotor.get("latchM");
        colorSensor= new ColorSensorV(hardwareMap);
        imu = new imuData(hardwareMap);
        turning.setOffset(imu.getAngle());
        latchM.setPower(0.1);
    }

    public void loop() {
        timer1 = eTimeObj.time(TimeUnit.MILLISECONDS);
        haveInit = true;
        if(colorSensor.isGold())
        if(goldNow == false) {
            goldNow = colorSensor.isGold();
        }
        switch (step) {
            case (1):
                latchM.setPower(0.03);
                nextStep(2000);//3000
                break;
            case (2):
                latchM.setPower(0);
                //strafing left
                drive(0.33f,-0.33f,0.33f,-0.33f);
                nextStep(500);//4000
                break;
            case (3):
                //strafing back
                drive(-0.33f,0.33f,-0.33f,0.33f);
                nextStep(500);//7000
                break;
            /**we are now at the centre, unlatched, at 2800ms*/
            case (4):
                // going forward
                drive(-0.4f,-0.4f,-0.4f,-0.4f);
                nextStep(465);//8000
                break;
            case (5):
                drive(-0.15f, 0.15f, -0.15f, 0.15f);
                if(goldNow) {
                    drive(-0.4f, -0.4f, -0.4f, -0.4f);
                    condition = 1;
                    nextStep(200, 50);
                } else {
                    nextStep(1500);
                }
                break;
            case (6):
                drive(-0.15f, 0.15f, -0.15f, 0.15f);
                if(goldNow) {
                    drive(-0.4f, -0.4f, -0.4f, -0.4f);
                    condition = 2;
                    nextStep(200, 51);
                }else {
                    nextStep(1500);
                }

                break;
            case (7):
                drive(-0.15f, 0.15f, -0.15f, 0.15f);
                if(goldNow) {
                    drive(-0.4f, -0.4f, -0.4f, -0.4f);
                    condition = 3;
                    nextStep(200, 52);
                }else {
                    nextStep(1500);
                }
                break;
            case (8):
                //moving to the next thing
                drive(-0.285f, 0.285f, -0.285f, 0.285f);
                nextStep(2000);//11750
                break;
            case (9):
                if(colorSensor.isGold()){
                    drive(-0.4f,-0.4f,-0.4f,-0.4f);
                    condition = 3;
                    nextStep(400, 50);
                }else {
                    nextStep(50, 53);

                }
                break;
//            case (50):
//                drive(0.4f,0.4f,0.4f,0.4f);
//                nextStep(400, 54);
//                break;
            case (51):
                drive(-0.3f, 0.3f, -0.3f, 0.3f);
                nextStep(1200, 54);
                break;
            case (52):
                drive(-0.3f, 0.3f, -0.3f, 0.3f);
                nextStep(800, 54);
                break;
            case (53):
                drive(-0.3f, 0.3f, -0.3f, 0.3f);
                nextStep(400, 54);
                break;
            case (54):
                /**sample done*/

                break;
            case (80):


                break;

            /*case (14):
              drive(-0.24f, -0.24f, -0.24f, -0.24f);
               nextStep(2000);
                break;
            case (15):
                drive(0.5f, 0.5f, 0.5f, 0.5f);
                nextStep(2250);
                break;*/
            default:
                drive(0, 0, 0, 0);
                break;
        }




        telemetry.addData("FL Power: ", frontLeft.getPower() + " " + speedBar(frontLeft.getPower(),8));
        telemetry.addData("FR Power: ", frontRight.getPower() + " " + speedBar(frontRight.getPower(),8));
        telemetry.addData("BL Power: ", backLeft.getPower() + " " + speedBar(backLeft.getPower(),8));
        telemetry.addData("BR Power: ", backRight.getPower() + " " + speedBar(backRight.getPower(),8));
        telemetry.addData("Time: ", timer1 + "");
        telemetry.addData("Step: ", step + "");
        telemetry.addData("Orientation", turning.currentAngle + "");
        telemetry.addData("pComponent", turning.pComponent + "");
        telemetry.addData("turning", turning.turning + "");
        telemetry.addData("destination", turning.destination + "");
        telemetry.addData("isGold", colorSensor.isGold() + "");
        telemetry.addData("Error",  turning.getError() + "" );
        telemetry.addData("Off Set: ", turning.offSet +"");
        telemetry.addData("Angle",  imu.getAngle() + "");
        telemetry.addData("site: ", condition + "");
        telemetry.addData("gold now? ", goldNow + "");

        telemetry.update();
    }
}
