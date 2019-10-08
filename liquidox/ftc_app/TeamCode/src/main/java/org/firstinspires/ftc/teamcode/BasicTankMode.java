package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.LO2Library;


@TeleOp
public class BasicTankMode extends OpMode {
    DcMotor lf, lb, rf, rb;
    Public void drive (float lf, float lb, float rf, float rb){
        lf.setPower(-lf);
        lb.setPower(-lb);
        rf.setPower(rf);
        rb.setPower(rb);
    }


    public void init() {
        lf = hardwareMap.dcMotor.get("lf");
        lb = hardwareMap.dcMotor.get("lb");
        rf = hardwareMap.dcMotor.get("rf");
        rb = hardwareMap.dcMotor.get("rb");
    }

    public void loop() {
        float lX = gamepad1.leftstick_x;
        float lY = gamepad1.leftstick_y;
        float rX = gamepad1.rightstick_x;
 //       drive(lY, lY, lY, lY);
        if (lf == lb) {
            rX.setPower(lf);
        }
        if (lb == lf) {
            rX.setPower(lb);
        }
        if (rf == rb) {
            rX.setPower(rf);
        }
        if (rb == rf) {
            rX.setPower(rb);
        }

        drive(rX, rX, rY, rY);
    }

}





