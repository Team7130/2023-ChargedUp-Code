package frc.robot.commands;

import org.apache.commons.collections4.functors.IfClosure;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Upper;
import frc.robot.subsystems.Upper.States;

public class TeleopUpper extends CommandBase {
  private Upper superstructure;
  private XboxController controller;
  private XboxController operator;

  public TeleopUpper(Upper iSuperstructure, XboxController controller, XboxController operator) {
    this.superstructure = iSuperstructure;
    addRequirements(iSuperstructure);
    this.controller = controller;
    this.operator = operator;
  }

  @Override
  public void execute() {
    // operator
    // if (controller.getYButton()) superstructure.setStates(States.human);
    // if (controller.getBButton()) superstructure.setStates(States.coneMid);
    // if (controller.getAButton()) superstructure.setStates(States.down);
    // if (controller.getXButton()) superstructure.setStates(States.placing);
    superstructure.elbowSet(operator.getRightY());
    if (operator.getLeftTriggerAxis() > 0.4) superstructure.stringSet(operator.getLeftTriggerAxis());
    else if (operator.getRightTriggerAxis() > 0.4) superstructure.stringSet(-operator.getRightTriggerAxis());

    // driver
    // if (controller.getPOV() == 0) superstructure.setStates(States.human);
    // if (controller.getPOV() == 90) superstructure.setStates(States.coneMid);
    // if (controller.getPOV() == 180) superstructure.setStates(States.down);
    // if (controller.getPOV() == 270) superstructure.setStates(States.placing);
    SmartDashboard.putBoolean("human", controller.getPOV() == 0);
    SmartDashboard.putBoolean("coneMid", controller.getPOV() == 90);
    SmartDashboard.putBoolean("down", controller.getPOV() == 180);
    SmartDashboard.putBoolean("placing", controller.getPOV() == 270);
  }
}