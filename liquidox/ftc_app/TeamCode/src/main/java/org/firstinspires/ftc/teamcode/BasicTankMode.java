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
        motor=hardwareMap.dcMotor.get ("lf");
        motor=hardwareMap.dcMotor.get ("lb");
        motor=hardwareMap.dcMotor.get ("rf");
    }
    public void loop() {
        lf.setPower =gamepad1.left_stick_y;
        lb.setPower =gamepad1.left_stick_y;
        rf.setPower =gamepad1.left_stick_y;
        rb.setPower =gamepad1.left_stick_y;
    }
}


