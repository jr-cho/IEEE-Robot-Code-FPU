package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "copper2")
public class ZaneCode extends LinearOpMode {


    private DcMotor FLMotor;
    private DcMotor FRMotor;
    private DcMotor BLMotor;
    private DcMotor BRMotor;

    @Override
    public void runOpMode() throws InterruptedException {
       this.FLMotor = hardwareMap.get(DcMotor.class, "FrontDriver");
        this.FRMotor = hardwareMap.get(DcMotor.class, "FrontPassenger");
        this.BLMotor = hardwareMap.get(DcMotor.class, "BackDriver");
        this.BRMotor = hardwareMap.get(DcMotor.class, "BackPassenger");

        waitForStart();
        FLMotor.setPower(1f);
        //FRMotor.setPower(1f);
        //BRMotor.setPower(1f);
        //BLMotor.setPower(1f);
        sleep(5000);
        FLMotor.setPower(0);
        FRMotor.setPower(0);
        BRMotor.setPower(0);
        BLMotor.setPower(0);

    }


}
