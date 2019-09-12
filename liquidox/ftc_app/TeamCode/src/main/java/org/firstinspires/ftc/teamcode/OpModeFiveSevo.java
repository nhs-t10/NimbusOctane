package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.LO2Library;

@TeleOp
public class OpModeFiveSevo extends OpMode {
    //private double random;
    //instantiate hardware devices
    boolean a = true;
    boolean b = true;
    DcMotor frontLeft, backLeft, frontRight, backRight;
    Servo john;
    CRServo latchS;
    Servo rightChestShoulder, leftChestShoulder, leftOuterShoulder, rightOuterShoulder;

    float speed = 0.5f;
    public void init() {
        /*Naming the Motors for phone*/
        frontLeft = hardwareMap.dcMotor.get("FL");
        backLeft = hardwareMap.dcMotor.get("BL");
        frontRight = hardwareMap.dcMotor.get("FR");
        backRight = hardwareMap.dcMotor.get("BR");

        // assign shoulders (motors involved in arms)
        rightChestShoulder = hardwareMap.servo.get("RCS");
        leftChestShoulder = hardwareMap.servo.get("LCS");
        rightOuterShoulder = hardwareMap.servo.get("ROS");
        leftOuterShoulder = hardwareMap.servo.get("LOS");
        leftOuterShoulder.setDirection(Servo.Direction.REVERSE);
        //assign lift motors
        john = hardwareMap.servo.get("john");
        latchS = hardwareMap.crservo.get("latchS");
    }
    public final void drive(float bl, float fl, float fr, float br ) {
/** Tells the robot how to drive */
        frontLeft.setPower(-fl*speed); //Scaled by 0.8
        backRight.setPower(br*speed);
        frontRight.setPower(fr*speed);
        backLeft.setPower(-bl*speed);

    }

    float[] sum = {0,0,0,0};


    public void loop() {
/** finds the values from the controller*/
        float lX = Range.clip(gamepad1.left_stick_x, -1, 1);
        float lY = Range.clip(gamepad1.left_stick_y, -1, 1);
        float rX = Range.clip(gamepad1.right_stick_x / 1.5f, -1, 1);

/** creates driving modes */
        float[] vertical = {0.7f * lY, 0.7f * lY, 0.7f * lY, 0.7f * lY};
        float[] horizontal = {lX, -lX, lX, -lX};
        float[] rotational = {-0.7f * rX, -0.7f * rX, 0.7f * rX, 0.7f * rX};
/** Adds all of the driving modes together */
        for (int i = 0; i < 4; i++) {
            sum[i] = vertical[i] + horizontal[i] + rotational[i];
        }

        float highest = Math.max(Math.max(sum[0], sum[1]), Math.max(sum[2], sum[3]));
/** Makes sure the robot doesnt drive above the maximum speed */
        if (Math.abs(highest) > 1) {
            for (int i = 0; i < 4; i++) {
                sum[i] = sum[i] / highest;
            }


        }

        /* makes it go vroom*/
        drive(sum[0], sum[1], sum[2], sum[3]);

        //okay now that the pretty code is done, have some disgusting pasta.
        //if the button is down, move left and right shoulders forwards.
        /*moves outer servos if a button is pressed*/
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

        //Throttle Code

        //If both bumpers are down, revert the speed to default
        if (gamepad1.left_trigger == 0f && gamepad1.right_trigger == 0f && !gamepad1.dpad_right && !gamepad1.dpad_left) {
            speed = 0.5f;
            //otherwise, if the left bumper is down, decrease the speed (with a minumum of 0)
        } else {
                speed = 0.5f + (gamepad1.right_trigger-gamepad1.left_trigger) * 0.5f;
            if (gamepad1.dpad_right) {
                //then, if the right bumper is down, increase the speed (max of 5) Austin says Hi
                speed = 1f;
            }
            if (gamepad1.dpad_left) {
                speed = 0.3f;
            }

        }
        //////////////////////////////

        //////////////////////////////
        telemetry.addData("FL Power: ", frontLeft.getPower() + " " + LO2Library.speedBar(frontLeft.getPower(),8));
        telemetry.addData("FR Power: ", frontRight.getPower() + " " + LO2Library.speedBar(frontRight.getPower(),8));
        telemetry.addData("BL Power: ", backLeft.getPower() + " " + LO2Library.speedBar(backLeft.getPower(),8));
        telemetry.addData("BR Power: ", backRight.getPower() + " " + LO2Library.speedBar(backRight.getPower(),8));
        telemetry.addData("Left Gamepad X-Coordinate: ", lX);
        telemetry.addData("Left Gamepad Y-Coordinate: ", lY);
        telemetry.addData("leftChestShoulder: ", leftChestShoulder.getPosition());
        telemetry.addData("leftOuterShoulder: ", leftOuterShoulder.getPosition());
        telemetry.addData("rightChestShoulder: ", rightChestShoulder.getPosition());
        telemetry.addData("leftOuterShoulder: ", leftOuterShoulder.getPosition());
        telemetry.addData("Left Trigger: ", gamepad1.left_trigger);
        telemetry.addData("Right Trigger: ", gamepad1.right_trigger);
        telemetry.addData("Current speed: ", speed);
        telemetry.update();
    }


}

//Test Comment
