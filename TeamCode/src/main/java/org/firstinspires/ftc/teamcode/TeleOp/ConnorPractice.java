package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public class ConnorPractice extends LinearOpMode {
    private Servo boot;
    private DcMotor sorter;
    private DcMotorEx shooterl;
    private DcMotorEx shooterr;
    @Override
    public void runOpMode() throws InterruptedException {
        boot = hardwareMap.get(Servo.class, "boot");
        sorter = hardwareMap.get(DcMotor.class, "sorter");
        shooterl = hardwareMap.get(DcMotorEx.class, "shooterl");
        shooterr = hardwareMap.get(DcMotorEx.class, "shooterr");

        waitForStart();
        boolean running = true;
        shooterr.setVelocity(800);
        shooterl.setVelocity(800);
        boolean abletorotate = true;
        while (opModeIsActive())
        {
            sorter.setDirection(DcMotorSimple.Direction.FORWARD);
            sorter.setTargetPosition(0);
            sorter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sorter.setPower(0.67);

            if (gamepad1.dpad_left && abletorotate){
                sorter.setTargetPosition(sorter.getTargetPosition()+128);
                abletorotate = false;
            } else if (!gamepad1.dpad_left) {

                if (gamepad1.dpad_right && abletorotate){
                    sorter.setTargetPosition(sorter.getTargetPosition()-128);
                    abletorotate = false;
                } else if (!gamepad1.dpad_right) {

                    if (gamepad1.y && abletorotate){
                        sorter.setTargetPosition(sorter.getTargetPosition()+64);
                        abletorotate = false;
                    } else if (!gamepad1.y) {
                        abletorotate = true;
                    }


                }
            }
            if (gamepad1.dpad_up){
                sorter.setPower(-0.1);
            }
            if (gamepad1.dpad_down){
                sorter.setPower(0.1);
            }
            if (gamepad1.b){
                sorter.setTargetPosition(0);
                sorter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }
            //need == for assigning needed value
            if (gamepad1.x && running){
                shooterl.setVelocity(0);
                shooterr.setVelocity(0);
                running = false;
            }
            else if (gamepad1.x && !running){
                shooterr.setVelocity(800);
                shooterl.setVelocity(800);
                running = true;
            }
            if(gamepad1.left_trigger > 0){
                shooterl.setVelocity(875);
                shooterr.setVelocity(875);
            }
            if(gamepad1.right_trigger > 0){
                boot.setPosition(1);
            } else if (gamepad1.right_trigger == 0) {
                boot.setPosition(0.4);
            }
        }

    }
}