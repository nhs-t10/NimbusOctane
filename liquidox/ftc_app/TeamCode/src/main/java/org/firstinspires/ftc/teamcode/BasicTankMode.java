package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.LO2Library;


@TeleOp
public class BasicTankMode extends OpMode {

    DcMotor lf, lb, rf, rb;
    public void init() {
        lf = hardwareMap.dcMotor.get ("lf");
        lb = hardwareMap.dcMotor.get ("lb");
        rf = hardwareMap.dcMotor.get ("rf");
        rb = hardwareMap.dcMotor.get ("rb");
    }
    public void loop() {
                lf.setPower = -(gamepad1.left_stick_y);
                lb.setPower = -(gamepad1.left_stick_y);
                rf.setPower = (gamepad1.left_stick_y);
                rb.setPower = (gamepad1.left_stick_y);

                lf.setPower = (gamepad1.left_stick_x);
                lb.setPower = (gamepad1.left_stick_x);
                rf.setPower = -(gamepad1.left_stick_x);
                rb.setPower = -(gamepad1.left_stick_x);
    }
}




