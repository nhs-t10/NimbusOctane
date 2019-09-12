package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.CRServo;
import org.firstinspires.ftc.teamcode.Turning;
import org.firstinspires.ftc.teamcode.ColorSensorV;
import org.firstinspires.ftc.teamcode.imuData;
import org.firstinspires.ftc.teamcode.LO2Library;
import org.firstinspires.ftc.teamcode.LiftHandler;

@TeleOp
public class AutonomousTesting extends OpMode {
    //private double random;
    //instantiate hardware devices
    boolean a = true;
    boolean b = true;
    DcMotor frontLeft, backLeft, frontRight, backRight;
    Servo john;
    CRServo latchS;
    Turning turning;
    imuData imu;
    LiftHandler lift;
    ColorSensorV colorSensor;
    double offSet;
    double error;
    DcMotor latchM;


    float speed = 0.8f;
    public void init() {
        turning = new Turning(-45);
//        /*Naming the Motors for phone*/
//        frontLeft = hardwareMap.dcMotor.get("FL");
//        backLeft = hardwareMap.dcMotor.get("BL");
//        frontRight = hardwareMap.dcMotor.get("FR");
//        backRight = hardwareMap.dcMotor.get("BR");
//
//        // assign shoulders (motors involved in arms)
//        rightChestShoulder = hardwareMap.servo.get("RCS");
//        leftChestShoulder = hardwareMap.servo.get("LCS");
//        rightOuterShoulder = hardwareMap.servo.get("ROS");
//        leftOuterShoulder = hardwareMap.servo.get("LOS");
//        leftOuterShoulder.setDirection(Servo.Direction.REVERSE);
//
//        colorSensor = new ColorSensorV(hardwareMap);
//        imu = new imuData(hardwareMap);
//        turning.offSet = imu.getAngle();
//
//        lift = new LiftHandler(hardwareMap);
        frontLeft = hardwareMap.dcMotor.get("FL");
        backLeft = hardwareMap.dcMotor.get("BL");
        frontRight = hardwareMap.dcMotor.get("FR");
        backRight = hardwareMap.dcMotor.get("BR");
        latchM = hardwareMap.dcMotor.get("latchM");

        // assign shoulders (motors involved in arms)
        /*rightChestShoulder = hardwareMap.servo.get("RCS");
        leftChestShoulder = hardwareMap.servo.get("LCS");
        rightOuterShoulder = hardwareMap.servo.get("ROS");
        leftOuterShoulder = hardwareMap.servo.get("LOS");
        leftOuterShoulder.setDirection(Servo.Direction.REVERSE);*/
       //  latchM = hardwareMap.dcMotor.get("latchM");
        john = hardwareMap.servo.get("john");
        latchS = hardwareMap.crservo.get("latchS");

        colorSensor= new ColorSensorV(hardwareMap);
        imu = new imuData(hardwareMap);
        turning.setOffset(imu.getAngle());
//        offSet = imu.getAngle();
        //latchM.setPower(0.6);
       // lift = new LiftHandler(hardwareMap);
    }
    public final void drive(float bl, float fl, float fr, float br ) {
/** Tells the robot how to drive */
        frontLeft.setPower(-fl*speed); //Scaled by 1.8
        backRight.setPower(br*speed);
        frontRight.setPower(fr*speed);
        backLeft.setPower(-bl*speed);

    }

    float[] sum = {0,0,0,0};


    public void loop() {
        colorSensor.runSample();
/** finds the values from the controller*/
        float lX = Range.clip(gamepad1.left_stick_x , -1, 1);
        float lY = Range.clip(gamepad1.left_stick_y, -1, 1);
        float rX = Range.clip(gamepad1.right_stick_x/1.5f, -1, 1);

/** creates driving modes */
        float[] vertical = {0.7f * lY, 0.7f * lY, 0.7f * lY, 0.7f * lY};
        float[] horizontal = {lX, -lX, lX, -lX};
        float[] rotational = {-0.7f * rX, -0.7f * rX, 0.7f * rX, 0.7f * rX};
/** Adds all of the driving modes together */
        for(int i=0; i<4; i++) {
            sum[i] = vertical[i] + horizontal[i] + rotational[i];
        }


        float highest = Math.max(Math.max(sum[0], sum[1]), Math.max(sum[2], sum[3]));
/** Makes sure the robot doesnt drive above the maximum speed */
        if(Math.abs(highest)>1) {
            for (int i=0; i<4; i++) {
                sum[i]=sum[i]/highest;
            }
        }

/** makes it go vroom*/

        drive(sum[0], sum[1], sum[2], sum[3]);
        //okay now that that masterpiece of coding is done, have some disgusting pasta.
        //if the button is down, move left and right shoulders forwards.
        /**moves outer servos if a button is pressed*/
      //  if(gamepad1.a) {


//            leftOuterShoulder.setPosition(0.5);
//            rightOuterShoulder.setPosition(0.5);
     //   } /*no else because we don't want one button to "take precedence" over another-- might be jittery, but there you go `\_('-')_/` */
        /**moves outer servos in opposite direction when b button is pressed*/
        if(gamepad1.a){
            latchS.setPower(0.5f);
        } else if(gamepad1.b){
            latchS.setPower(-0.5f);
        } else {
            latchS.setPower(0);
        }
        if(gamepad1.y) {
            if(john.getPosition() == 0.8){
                john.setPosition(0);
            } else{
                john.setPosition(0.8);
            }
        }
        if(gamepad1.x) {
            turning.update(imu);
//            double currentAngle = imu.getAngle() - offSet;
//            error = currentAngle - 45;
//            double pComponent = Range.clip(error * 0.005, -1, 1);
//
//
//            if (Math.abs(error) < 3) {
//                drive(0, 0, 0, 0);
//            }
//            drive((float) (pComponent), (float) (pComponent), (float) -(pComponent), (float) -(pComponent));
            if (turning.isTurning()) {
                if (Math.abs(turning.getError()) < 10) {
                    turning.stopTurning();
                }
               drive((float)(turning.getpComponent()), (float)(turning.getpComponent()), (float)-(turning.getpComponent()), (float)-(turning.getpComponent()));
            }



        }else {
            drive(sum[0], sum[1], sum[2], sum[3]);
        }

        if (gamepad1.dpad_down){
        latchM.setPower(1);
        //  LMP = latchM.getPower();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



        //Throttle Code

        //If both bumpers are down, revert the speed to default
        if(gamepad1.left_bumper && gamepad2.right_bumper) {
            speed = 0.8f;
            //otherwise, if the left bumper is down, decrease the speed (with a minumum of 0)
        } else if(gamepad1.left_bumper) {
            speed = Math.max(speed - 0.05f, 0);
        } else if(gamepad1.right_bumper) {
            //then, if the right bumper is down, increase the speed (max of 5)
            speed = Math.min(speed + 0.05f, 5);
        }
        //////////////////////////////

        //////////////////////////////
//       string[] arrayOfmemes = {Josh, Joshipoo, TheRealJoshipoo, Short, Squashua};marrrkkk...
//        string meme = arrayofmemes[Math.floor(Math.random()*5)];
//telementry.addData("", meme);
        telemetry.addData("FL Power: ", Math.floor(frontLeft.getPower()*10) + " " + LO2Library.speedBar(frontLeft.getPower(),8));
        telemetry.addData("FR Power: ", Math.floor(frontRight.getPower()*10) + " " + LO2Library.speedBar(frontRight.getPower(),8));
        telemetry.addData("BL Power: ", Math.floor(backLeft.getPower()*10) + " " + LO2Library.speedBar(backLeft.getPower(),8));
        telemetry.addData("BR Power: ", Math.floor(backRight.getPower()*10) + " " + LO2Library.speedBar(backRight.getPower(),8));
        telemetry.addData("Hex code", colorSensor.getHexCode() + "");
        telemetry.addData("Color Int", colorSensor.getColorInt() + "");
        telemetry.addData("Gold? ", colorSensor.isGold() + "");
        telemetry.addData("Color Read State", colorSensor.getReadState() + "");
        telemetry.addData("Turning Error", turning.getError() + "");
        telemetry.addData("Turning Destination", turning.getDestination() + "");
        telemetry.addData("Turning Angle", imu.getAngle() + "");
        telemetry.addData("Turning pComponent", turning.getpComponent() + "");
        telemetry.addData("Turning", turning.isTurning() + "");
        telemetry.addData("X", gamepad1.x );

        telemetry.update();
    }


}