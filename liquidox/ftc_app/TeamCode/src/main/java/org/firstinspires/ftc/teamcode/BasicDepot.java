package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Turning;
import org.firstinspires.ftc.teamcode.ColorSensorV;
import java.util.concurrent.TimeUnit;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;


@Autonomous
public class BasicDepot extends LO2Library {


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
    Servo mark;
    void nextStep(float delay) {
        if(!isDelay){
            timeDone = timer1 + delay;
            isDelay = true;
        }
        if(timer1 >= timeDone) {
            drive(0, 0, 0, 0);
            isDelay = false;
            step++;
        }

    }
    void nextStepX(float delay, int stepNum) {
        if(!isDelay){
            timeDone = timer1 + delay;
            isDelay = true;
        }
        if(timer1 >= timeDone) {
            drive(0, 0, 0, 0);
            isDelay = false;
            step = stepNum;
            goldNow = false;
        }
    }
    boolean haveInit = false;
    @Override
    public void init() {
        super.initialize_robot();
        latchM = hardwareMap.dcMotor.get("latchM");
        mark = hardwareMap.servo.get("mark");
        colorSensor= new ColorSensorV(hardwareMap);
        imu = new imuData(hardwareMap);
        latchM.setPower(0.1);
        mark.setPosition(0);
    }

    public void loop() {
        timer1 = eTimeObj.time(TimeUnit.MILLISECONDS);
        haveInit = true;
        if(goldNow = false){
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
                nextStep(400);//4000
                break;
            case (3):
                //strafing back
                drive(-0.33f,0.33f,-0.33f,0.33f);
                nextStep(400);//7000
                break;
            /**we are now at the centre, unlatched, at 2800ms*/
            case (4):
                drive(-1,-1,-1,-1);
                nextStep(100);
            case (5):
                drive(-0.33f,-0.33f,-0.33f,-0.33f);
                nextStep(600);
            case (6):
                mark.setPosition(0.8);
                nextStep(250);
            case (7):
                mark.setPosition(0);
                nextStep(250);
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
        telemetry.addData("isGold", colorSensor.isGold() + "");
        telemetry.addData("Angle",  imu.getAngle() + "");
        telemetry.addData("site: ", condition + "");

        telemetry.update();
    }
}
