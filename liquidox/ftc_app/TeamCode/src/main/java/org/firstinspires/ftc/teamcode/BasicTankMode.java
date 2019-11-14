package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import org.firstinspires.ftc.teamcode.LO2Library;

@TeleOp
public class BasicTankMode extends OpMode {
    DcMotor lf, lb, rf, rb;
    Servo claw, hook;
    CRServo ladder;
    float [] omniValues = new float [4];
    public void clawControl(boolean button){
        if (button = true) {
            openClaw();
        }else{
            closeClaw();
        }
    }
    public void lift(){
        if (gamepad1.left_trigger > gamepad1.right_trigger){
            ladder.setPower(-gamepad1.left_trigger);
        }else {
            ladder.setPower(gamepad1.right_trigger);
        }
    }
    public void hookUp(){
        hook.setPosition(1);
    }
    public void hookDown(){
        hook.setPosition(0.5);
    }
    public void openClaw(){
        claw.setPosition(0.5); //subject to change for ease of hardware
    }
    public void closeClaw(){
        claw.setPosition(0); //subject to change for ease of hardware
    }
    public float inchConversion(float inches){
        return inches/4;
    }
    public void drive(float x, float y, float h, float k){
        lf.setPower(-x);
        lb.setPower(-y);
        rf.setPower(h);
        rb.setPower(k);
    }


    public void init() {
        lf = hardwareMap.dcMotor.get("lf");
        lb = hardwareMap.dcMotor.get("lb");
        rf = hardwareMap.dcMotor.get("rf");
        rb = hardwareMap.dcMotor.get("rb");
        hook = hardwareMap.cervo.get("Hook");
        claw = hardwareMap.servo.get("Claw");
        ladder = hardwareMap.crservo.get("Ladder");
        hook.setPosition(1);
        claw.setPosition(0);
    }
    public void loop() {

        float lY = gamepad1.leftstick_y;
        float lX = gamepad1.leftstick_x;
        float rX = gamepad1.rightstick_x;
        omniValues [0] = lY + rX - lX;
        omniValues [1] = lY + rX + lX;
        omniValues [2] = lY - rX - lX;
        onmiValues [3] = lY - rX + lX;

        float highest = 0;
        for (int i=0, i<4; i++) {
            if(omniValues[i].abs > highest) {
                highest = omniValues[i].abs;
            }
        }
        if(highest > 1) {
            for (int i = 0, i<4;i++){
                omniValues[i] = omniValues[i] / highest;
            }
        }
        drive(omniValues[0], omniValues[1], omniValues[2], onmiValues[3]);
        lift();
        clawControl(gamepad1.button_a);
    }
}

    public void forward (float rotations) {
        int position = lf.getCurrentPosition():
        while lf.getCurrentPosition() < position - (rotations * 560) {
            lf.setPower(-1);
            lb.setPower(-1);
            rf.setPower(1);
            rb.setPower(1);
        }
        lf.setPower(0);
        lb.setPower(0);
        rf.setPower(0);
        rb.setPower(0);
    }
    public void right (float rotations) {
        int position = lf.getCurrentPosition():
        while lf.getCurrentPosition() < position - (rotations * 560) {
            lf.setPower(-1);
            lb.setPower(-1);
            rf.setPower(-1);
            rb.setPower(-1);
        }
        lf.setPower(0);
        lb.setPower(0);
        rf.setPower(0);
        rb.setPower(0);
    }
    public void left (float rotations) {
        int position = lf.getCurrentPosition():
        while lf.getCurrentPosition() < position + (rotations * 560) {
            lf.setPower(1);
            lb.setPower(1);
            rf.setPower(1);
            rb.setPower(1);
        }
        lf.setPower(0);
        lb.setPower(0);
        rf.setPower(0);
        rb.setPower(0);
    }
    public void back (float rotations) {
        int position = lf.getCurrentPosition():
        while lf.getCurrentPosition() < position + (rotations * 560) {
            lf.setPower(1);
            lb.setPower(1);
            rf.setPower(-1);
            rb.setPower(-1);
        }
        lf.setPower(0);
        lb.setPower(0);
        rf.setPower(0);
        rb.setPower(0);
    }
