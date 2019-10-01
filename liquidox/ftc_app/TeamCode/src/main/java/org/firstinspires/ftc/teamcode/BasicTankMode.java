package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.LO2Library;


@TeleOp
public class BasicTankMode extends OpMode {
<<<<<<< HEAD

    DcMotor lf, lb, rf, rb;
    public void init() {
        lf = hardwareMap.dcMotor.get ("lf");
        lb = hardwareMap.dcMotor.get ("lb");
        rf = hardwareMap.dcMotor.get ("rf");
        rb = hardwareMap.dcMotor.get ("rb");
=======
    DcMotor lf, lb, rf, rb;
    public void init() {
        lf = hardwareMap.dcMotor.get("lf");
        lb = hardwareMap.dcMotor.get("lb");
        rf = hardwareMap.dcMotor.get("rf");
        rb = hardwareMap.dcMotor.get("rb");
>>>>>>> 17dcb6157c826c5f1d779a6be89325206f8e00a8
    }

    public void loop() {
<<<<<<< HEAD
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
=======
        lf = -(gamepad1.leftstick_y);
        lb = -(gamepad1.leftstick_y);
        rf = -(gamepad1.leftstick_y);
        rb = -(gamepad1.leftstick_y);
    }
}

>>>>>>> 17dcb6157c826c5f1d779a6be89325206f8e00a8




