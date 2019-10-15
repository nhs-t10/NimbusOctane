package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.LO2Library;



@TeleOp
public class BasicTankMode extends OpMode {
    DcMotor lf, lb, rf, rb;
    float [] omniValues = new float[4];


    public void drive (float Lf, float Lb, float Rf, float Rb){
        lf.setPower(-Lf);
        lb.setPower(-Lb);
        rf.setPower(Rf);
        rb.setPower(Rb);

    }

    public void init() {
        lf = hardwareMap.dcMotor.get("lf");
        lb = hardwareMap.dcMotor.get("lb");
        rf = hardwareMap.dcMotor.get("rf");
        rb = hardwareMap.dcMotor.get("rb");
    }

    public void loop() {
       float lX = gamepad1.left_stick_x;
       float lY = gamepad1.left_stick_y;
       float rX = gamepad1.right_stick_x;
       omniValues[0] = (lY + rX - lX)/3;
       omniValues[1] = (lY + rX + lX)/3;
       omniValues[2] = (lY - rX - lX)/3;
       omniValues[3] = (lY - rX + lX)/3;
       drive(omniValues[0], omniValues[1], omniValues[2], omniValues[3]);

    }


}







