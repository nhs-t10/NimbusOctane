package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


public abstract class LO2Library extends OpMode {

    public static DcMotor frontLeft, backLeft, frontRight, backRight;
    public static Servo rightChestShoulder, leftChestShoulder, leftOuterShoulder, rightOuterShoulder;

    public void initialize_robot() {
        /*Naming the Motors for phone*/
        frontLeft = hardwareMap.dcMotor.get("FL");
        backLeft = hardwareMap.dcMotor.get("BL");
        frontRight = hardwareMap.dcMotor.get("FR");
        backRight = hardwareMap.dcMotor.get("BR");



    }

    public static String repeatString(String s, int n) {
        for(int i = 0; i < n; i++ ) {
            s = s + s;
        }
        return s;
    }

    public static void drive(float bl, float fl, float fr, float br) {
        frontLeft.setPower(Range.clip(-fl, -1, 1));
        backRight.setPower(Range.clip(br, -1, 1));
        frontRight.setPower(Range.clip(fr, -1, 1));
        backLeft.setPower(Range.clip(-bl, -1, 1));

    }
    public static void turn(double howMuch) {
       drive(-howMuch, -howMuch, howMuch, howMuch);
    }
    public static void strafe(float howMuch) {
        drive(-howMuch, howMuch, -howMuch, howMuch);
    }
    public static void forward(float howMuch) {
        drive(-howMuch, -howMuch, -howMuch, -howMuch);
    }
    public static void drive(double bl, double fl, double fr, double br) {
        frontLeft.setPower(Range.clip(-fl, -1, 1));
        backRight.setPower(Range.clip(br, -1, 1));
        frontRight.setPower(Range.clip(fr, -1, 1));
        backLeft.setPower(Range.clip(-bl, -1, 1));

    }
    public static void TurnDrive(double bl, double fl, double fr, double br) {
        frontLeft.setPower(-fl);
        backRight.setPower(br);
        frontRight.setPower(fr);
        backLeft.setPower(-bl);
    }
    public static String speedBar(double percent, int resolution) {
        percent = Math.abs(percent);
        return "["+(repeatString("\u25A0",(int)Math.ceil(percent*(resolution))) + repeatString("\u25A1",(int)Math.ceil(resolution - (percent*(resolution))))).substring(0,resolution)+"]";
    }
}