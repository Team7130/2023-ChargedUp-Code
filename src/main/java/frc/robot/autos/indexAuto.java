package frc.robot.autos;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.ChenryLib.SettledUtility;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Index.indexStates;
import frc.robot.subsystems.Index.tilterPos;

public class indexAuto extends CommandBase{
    Index index;
    double desired;
    double current;
    double error;
    boolean finish;
    indexStates states;
    SettledUtility settled;

    public indexAuto(Index index, indexStates states){
        this.index = index;
        this.states = states;
        addRequirements(index);
    }

    @Override
    public void initialize() {
        
    }

    @Override
    public void execute() {
        index.setState(states);
        current = index.getTilterPos();

        switch(states) {
            case Indexing:
                desired = tilterPos.downlimit;
                break;
            case AimTop:
                desired = tilterPos.inCom;
                break;
            case Standby:
                desired = tilterPos.upLimit;
                break;
            default:
                break;
        }

        error = desired - current;
        settled = new SettledUtility(60, error, 10);

        finish = settled.isSettled(error);

    }

    @Override
    public boolean isFinished() {
        if(finish){
            return true;
        }else{
            return false;
        }
    }
}
