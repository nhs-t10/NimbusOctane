package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class TeleOpExperiments extends OpMode {
    //private double random;
    //instantiate hardware devices
    double fl,fr,bl,br;
    double power;
    boolean a = true;
    boolean b = true;
    double CA = 0;
    Servo john, mark;
    DcMotor frontLeft, backLeft, frontRight, backRight, latchM;

    CRServo latchS;

    float speed = 0.8f;


    public double BA(double num) {
        return num + 0.1;
    }

    public double LA(double num) {
        return num + 0.01;
    }

    public double BS(double num) {
        return num - 0.1;
    }

    public double LS(double num) {
        return num - 0.01;
    }

    public void init() {
        /*Naming the Motors for phone*/
        frontLeft = hardwareMap.dcMotor.get("FL");
        backLeft = hardwareMap.dcMotor.get("BL");
        frontRight = hardwareMap.dcMotor.get("FR");
        backRight = hardwareMap.dcMotor.get("BR");


        /*naming the latching devices*/
        latchS = hardwareMap.crservo.get("latchS");
        latchM = hardwareMap.dcMotor.get("latchM");
        mark = hardwareMap.servo.get("mark");
    }

    public final void drive(double bl, double fl, double fr, double br) {
/** Tells the robot how to drive */
        frontLeft.setPower(-fl ); //Scaled by 1.8
        backRight.setPower(br );
        frontRight.setPower(fr );
        backLeft.setPower(-bl );

    }

    float[] sum = {0, 0, 0, 0};


    public void loop() {
        drive(-bl, -fl, fr, br);
        fl = power;
        fr = power;
        bl = power;
        br = power;
        /** testing section
         * Basically just adjusts the power of the latching mechanism to find useful values*/
        if (gamepad1.dpad_right) {
            power = 0.2;
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            power = 0;
        }

        //Throttle Code


            //////////////////////////////

            //////////////////////////////
        telemetry.addData("FL Power: ", frontLeft.getPower() + " " + LO2Library.speedBar(frontLeft.getPower(),8));
        telemetry.addData("FR Power: ", frontRight.getPower() + " " + LO2Library.speedBar(frontRight.getPower(),8));
        telemetry.addData("BL Power: ", backLeft.getPower() + " " + LO2Library.speedBar(backLeft.getPower(),8));
        telemetry.addData("BR Power: ", backRight.getPower() + " " + LO2Library.speedBar(backRight.getPower(),8));
        telemetry.addData("Actual Power: ", latchM.getPower());
        telemetry.addData("Theoretical Power: ", power);



    }
}