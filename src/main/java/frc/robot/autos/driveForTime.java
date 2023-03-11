package frc.robot.autos;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Swerve;

public class driveForTime extends CommandBase {
  private Swerve drive;
  double time; 
  double TotalTime;
  public driveForTime(Swerve s_Swerve, double timeS) {
    drive = s_Swerve;
    TotalTime = timeS;
    addRequirements(s_Swerve);
  }

  public void initialize(){
    time = Timer.getFPGATimestamp();
  }
  double dt = Timer.getFPGATimestamp() - time;
  
  public void execute(){

        drive.drive(new Translation2d(1, 0), 0, false, true);
        dt = Timer.getFPGATimestamp() - time;

  }

  public void end(boolean interrupted){
    drive.drive(new Translation2d(0, 0), 0, false, true);
  }

  public boolean isFinished(){
      if(dt > TotalTime){
          drive.drive(new Translation2d(0, 0), 0, false, true);
          return true;
          
      }
      else return false;
  }
}
