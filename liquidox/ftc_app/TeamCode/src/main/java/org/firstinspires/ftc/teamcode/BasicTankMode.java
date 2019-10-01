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
        lf = hardwareMap.dcMotor.get("lf");
        lb = hardwareMap.dcMotor.get("lb");
        rf = hardwareMap.dcMotor.get("rf");
        rb = hardwareMap.dcMotor.get("rb");
    }

    public void loop() {
        lf = -(gamepad1.leftstick_y);
        lb = -(gamepad1.leftstick_y);
        rf = -(gamepad1.leftstick_y);
        rb = -(gamepad1.leftstick_y);
    }

    //eric test
}





