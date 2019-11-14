package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.LO2Library;


@Override
public class Autonomous1 extends OpMode {
    DcMotor lf, lb, rf, rb;
    float [] omniValues = new float [4];
    public float inchConversion(float inches){
        return inches/4;
    }
    public void drive(float x, float y, float h, float k){
        lf.setPower(-lf);
        lb.setPower(-lb);
        rf.setPower(rf);
        rb.setPower(rb);
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

    public void init() {
        lf = hardwareMap.dcMotor.get("lf");
        lb = hardwareMap.dcMotor.get("lb");
        rf = hardwareMap.dcMotor.get("rf");
        rb = hardwareMap.dcMotor.get("rb");
    }
    public void loop() {
    switch (step)
        case (1):
            right (inchConversion( inches 29));
            step++;
            break;
        case (2):
            back (inchConversion( inches 60));
            if (isYellow() == false){
                drive (x 0, y 0, h 0, k 0);
                step++;
                break;
            }
        case (3):
            back (inchConversion(inches 25));
            step++;
            break;
        case (4):
            right (inchConversion(inches 6));
            step++;
            break;
        case (5):
            forward (inchConversion(inches 2));
            step++;
            break;
        case (6):
            claw close;
            step++;
            break;
        case (7):
            left (inchConversion(inches 12);
            step++;
            break;
        case (8):
            forward (inchConversion(inches 96));
            step++;
            break;




    }
}
