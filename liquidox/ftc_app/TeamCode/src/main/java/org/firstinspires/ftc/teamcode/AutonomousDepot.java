package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import java.util.concurrent.TimeUnit;

@Autonomous
public class AutonomousDepot extends LO2Library {

    Turning turn1;
    Turning turn2;

    boolean gold = false;
    int step = 1;
    boolean goldNow = false;
    ElapsedTime eTimeObj = new ElapsedTime();
    imuData imu;
    float timeDone = 0;
    ColorSensorV colorSensor;
    float timer1;
    DcMotor latchM;
    boolean isDelay;
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
    void nextStep(float delay, int intStep) {
        if(!isDelay){
            timeDone = timer1 + delay;
            isDelay = true;
        }
        if(timer1 >= timeDone) {
            drive(0, 0, 0, 0);
            isDelay = false;
            step = intStep;
        }
    }
    void nextStep(float delay, Turning turn) {
        if(!isDelay){
            timeDone = timer1 + delay;
            isDelay = true;
        }
        if(timer1 >= timeDone || turn.getError() < 5) {
            drive(0, 0, 0, 0);
            isDelay = false;
            step++;
        }
    }

    void sample(float time) {
        if(timer1 > time + 500 && timer1 < time + 1000)
            if(colorSensor.isGold()){
                goldNow = true;
            }

        if(goldNow){
            if(timer1 > time + 1000 && timer1 < time + 1250){
                drive(1.5f,1.5f,1.5f,1.5f);
            }
            if(timer1 > time + 1250 && timer1 < time + 1500){
                drive(-1.5f,-1.5f,-1.5f,-1.5f);
            }
            if(timer1 < time + 1495){
                goldNow = false;
            }
        }

        nextStep( 1500);
    }
//void unlatch(){ }

    @Override
    public void init() {
        super.initialize_robot();
        turn1 = new Turning(-45);
        turn2 = new Turning(45);
        latchM = hardwareMap.dcMotor.get("latchM");
        colorSensor= new ColorSensorV(hardwareMap);
        imu = new imuData(hardwareMap);
        turn1.setOffset(imu.getAngle());
        turn2.setOffset(imu.getAngle());
        latchM.setPower(1);
    }

    public void loop() {
        timer1 = eTimeObj.time(TimeUnit.MILLISECONDS);

        switch (step) {

            case (1):
                turn1.update(imu);
                turn(turn1.getpComponent());
                nextStep(5000);
                break;
            case (2):
                turn2.update(imu);
                turn(turn2.getpComponent());
                nextStep(5000);
                break;
            default:
                drive(0,0,0,0);
                break;
//            case (1):latchM.setPower(Range.clip(3/timer1, -1, 1));
//                nextStep(3000);//3000 nextStep(1000);//3000
//                break;
//            case (2):
//                //strafing left
//                drive(-0.2f,-0.2f,-0.2f,-0.2f);
//                nextStep(3750);//4000
//                break;
//            case (3):
//                //Contract bar
//                step = 4;
//                break;
//            case (4):
//                //strafing to center
//                drive(0.8f,0.8f,0.8f,0.8f);
//                nextStep(500);//8000
//                break;
//            default:
//                drive(0, 0, 0, 0);
//                break;
        }



        telemetry.addData("FL Power: ", frontLeft.getPower() + " " + speedBar(frontLeft.getPower(),8));
        telemetry.addData("FR Power: ", frontRight.getPower() + " " + speedBar(frontRight.getPower(),8));
        telemetry.addData("BL Power: ", backLeft.getPower() + " " + speedBar(backLeft.getPower(),8));
        telemetry.addData("BR Power: ", backRight.getPower() + " " + speedBar(backRight.getPower(),8));
        telemetry.addData("Time: ", timer1 + "");
        telemetry.addData("Step: ", step + "");
        telemetry.addData("Orientation", turn1.get_Angle() + "");
        telemetry.addData("pComponent", turn1.getpComponent() + "");
        telemetry.addData("turning", turn1.isTurning() + "");
        telemetry.addData("destination", turn1.getDestination() + "");
        telemetry.addData("isGold", colorSensor.isGold() + "");
        telemetry.addData("Error",  turn1.getError() + "" );
        telemetry.addData("Off Set: ", turn1.offSet +"");
        telemetry.addData("Angle",  imu.getAngle() + "");
        telemetry.addData("Hex code", colorSensor.getHexCode() + "");

        telemetry.update();
    }
}
