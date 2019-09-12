package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LiftHandler {
     CRServo latchS;
    DcMotor latchM;

    public LiftHandler(HardwareMap h) {
        this.latchS = h.crservo.get("latchS");
    }

    public void upArm() throws InterruptedException {
        this.latchS.setPower(0.2);
        latchM.setPower(0.2f);
        Thread.sleep(1000);
        this.latchS.setPower(0);
        latchM.setPower(0f);
    }
    public void downArm() throws InterruptedException {
        this.latchS.setPower(-0.2);
        latchM.setPower(-0.2f);
        Thread.sleep(1000);
        this.latchS.setPower(0);
        latchM.setPower(0f);
    }
    public void landBot() {
        latchM.setPower(0.5);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //unlatch servo



        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        latchM.setPower(0.3);
        try {
            Thread.sleep(450);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latchM.setPower(0);
    }

}
