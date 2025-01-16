package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Movement_V1", group = "Autonomous")
public class Movement_V1 extends LinearOpMode {
    private DcMotor FrontDriver = null;
    private DcMotor FrontPassenger = null;
    private DcMotor BackDriver = null;
    private DcMotor BackPassenger = null;

    @Override
    public void runOpMode() throws InterruptedException {

        // Initialize motors
        FrontDriver = hardwareMap.dcMotor.get("FrontDriver");
        BackDriver = hardwareMap.dcMotor.get("BackDriver");
        FrontPassenger = hardwareMap.dcMotor.get("FrontPassenger");
        BackPassenger = hardwareMap.dcMotor.get("BackPassenger");

        // Set motor directions
        FrontPassenger.setDirection(DcMotor.Direction.REVERSE);
        BackPassenger.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for start
        waitForStart();

        // Move forward
        move(0.5, 0.5, 0.5, 0.5, 1000);

        // Move backward
        move(-0.5, -0.5, -0.5, -0.5, 1000);

        // Spin left
        move(-0.5, 0.5, -0.5, 0.5, 1000);

        // Spin right
        move(0.5, -0.5, 0.5, -0.5, 1000);

        // Move diagonally: top-left to bottom-right
        move(0.5, 0, 0, 0.5, 1000);

        // Move diagonally: top-right to bottom-left
        move(0, 0.5, 0.5, 0, 1000);

        // Move diagonally: bottom-left to top-right
        move(0.5, 0, 0, 0.5, 1000);

        // Move diagonally: bottom-right to top-left
        move(0, 0.5, 0.5, 0, 1000);
        
        //Move Left
        move(-0.5, 0.5, 0.5, -0.5, 1000);
        
        //Move Right
        move(0.5, -0.5, -0.5, 0.5, 1000);


        // Stop the robot
        stopMotors();
    }

    /**
     * Moves the robot by setting power to each motor for a specified duration.
     * @param fl Front left motor power
     * @param fr Front right motor power
     * @param bl Back left motor power
     * @param br Back right motor power
     * @param duration Time to run in milliseconds
     * @throws InterruptedException
     */
    private void move(double fl, double fr, double bl, double br, long duration) throws InterruptedException {
        FrontDriver.setPower(fl);
        FrontPassenger.setPower(fr);
        BackDriver.setPower(bl);
        BackPassenger.setPower(br);
        sleep(duration);
    }

    /**
     * Stops all motors.
     */
    private void stopMotors() {
        FrontDriver.setPower(0);
        FrontPassenger.setPower(0);
        BackDriver.setPower(0);
        BackPassenger.setPower(0);
    }
}
