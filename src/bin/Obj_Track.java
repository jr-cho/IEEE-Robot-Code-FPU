package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.robotcore.hardware.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.MovingImageFrameGenerator;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.TfodParameters;

@Autonomous(name = "Object Tracking", group = "Autonomous")
public class Obj_Track extends LinearOpMode {

    private DcMotor FrontDriver, FrontPassenger, BackDriver, BackPassenger;

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize motors
        FrontDriver = hardwareMap.dcMotor.get("FrontDriver");
        BackDriver = hardwareMap.dcMotor.get("BackDriver");
        FrontPassenger = hardwareMap.dcMotor.get("FrontPassenger");
        BackPassenger = hardwareMap.dcMotor.get("BackPassenger");

        // Set directions for motors
        FrontPassenger.setDirection(DcMotor.Direction.REVERSE);
        BackPassenger.setDirection(DcMotor.Direction.REVERSE);

        // Initialize TensorFlow Object Detection
        WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam 1"); // Adjust to your webcam name
        MovingImageFrameGenerator frameGenerator = new MovingImageFrameGenerator("Webcam 1");
        TFObjectDetector tfod = new TFObjectDetector(new TfodParameters.Builder().build(), frameGenerator);
        tfod.initialize(hardwareMap.appContext);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            AnnotatedYuvRgbFrame annotatedFrame = tfod.takeAnnotatedFrame();

            // Example logic for detecting a purple object
            if (annotatedFrame != null) {
                telemetry.addData("Frame Info", annotatedFrame.getFrame().getSize());
                // TODO: Add logic to detect purple objects in the frame
                // e.g., use frame pixel analysis or pre-trained TensorFlow model
            }

            telemetry.update();
        }

        tfod.shutdown();
        frameGenerator.shutdown();
    }
}
