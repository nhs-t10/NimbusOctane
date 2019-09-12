package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.LO2Library;


@TeleOp
public class BasicTankMode extends OpMode {

    DcMotor frontLeft, backLeft, frontRight, backRight;
    float t = 0;
    int dir = 0;
    public void init() {
        /*Namiyng the Motors for phone*/
        frontLeft = hardwareMap.dcMotor.get("FL");
        backLeft = hardwareMap.dcMotor.get("BL");
        frontRight = hardwareMap.dcMotor.get("FR");
        backRight = hardwareMap.dcMotor.get("BR");

    }
    public void drive(float bl, float fl, float fr, float br ) {
        float coef = 0.8f;
        frontLeft.setPower(-fl * coef);
        backRight.setPower(br* coef);
        frontRight.setPower(fr* coef);
        backLeft.setPower(-bl* coef);

    }

    float[] sum = {0 ,0 , 0, 0};



    public void loop() {
//
//        float lX = Range.clip(gamepad1.left_stick_x, -1, 1);
//        float lY = Range.clip(gamepad1.left_stick_y, -1, 1);
//        float rX = Range.clip(gamepad1.right_stick_x, -1, 1);
//        float rY = Range.clip(gamepad1.right_stick_y, -1, 1);
        t = (float) System.currentTimeMillis();
        if(gamepad1.x){
            t = 0;
            dir = 2000;
            drive(1, 1 ,1 ,1 );
        }
        if(gamepad1.a){
            t = 0;
            dir = 1000;
        }
        if(gamepad1.b){
            t = 0;
            dir = 3000;
        }
        if(gamepad1.y){
            t = 0;
            dir = 4000;
        }
        if(t < dir){
            drive(1, 1 ,1, 1);
        }

        telemetry.addData("FL Power: ", frontLeft.getPower() + " " + LO2Library.speedBar(frontLeft.getPower(),8));
        telemetry.addData("FR Power: ", frontRight.getPower() + " " + LO2Library.speedBar(frontRight.getPower(),8));
        telemetry.addData("BL Power: ", backLeft.getPower() + " " + LO2Library.speedBar(backLeft.getPower(),8));
        telemetry.addData("BR Power: ", backRight.getPower() + " " + LO2Library.speedBar(backRight.getPower(),8));

        }



//        if((rX > -0.6 && rX < 0.6) || (lX > -0.6 && lX < 0.6)) {
//            drive(lY, lY, rY, rY);
//        } else {
//            drive((rX+lX)/2,-((rX+lX)/2),-((rX+lX)/2),(rX+lX)/2);
//        }
        //drive(lY,lY,rY,rY);//multiply by x?
    }


    //Adds motor values for bug fixing*/





