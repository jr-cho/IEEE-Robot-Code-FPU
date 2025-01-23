package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Gamepad_Movement", group = "TeleOp")
public class Gamepad_Movement extends LinearOpMode {
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

        while (opModeIsActive()) {
            // Calculate joystick-driven movement
            final double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            final double robotAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
            final double rightX = gamepad1.right_stick_x;

            double v1 = r * Math.sin(robotAngle) - rightX;
            double v2 = r * Math.cos(robotAngle) + rightX;
            double v3 = r * Math.cos(robotAngle) - rightX;
            double v4 = r * Math.sin(robotAngle) + rightX;

            // Normalize powers if necessary
            double max = Math.max(Math.max(Math.abs(v1), Math.abs(v2)), Math.max(Math.abs(v3), Math.abs(v4)));
            if (max > 1.0) {
                v1 /= max;
                v2 /= max;
                v3 /= max;
                v4 /= max;
            }

            // Set motor powers
            FrontPassenger.setPower(v1);
            FrontDriver.setPower(v2);
            BackPassenger.setPower(v3);
            BackDriver.setPower(v4);

            // Update telemetry
            telemetry.addData("Robot Angle", robotAngle);
            telemetry.update();
        }
    }
}
