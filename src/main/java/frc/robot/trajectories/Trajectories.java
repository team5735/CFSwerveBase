package frc.robot.trajectories;

import java.io.IOException;
import java.nio.file.Path;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import frc.robot.Constants;

// Pathweaver Interpretation Imports
import java.util.List;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.math.trajectory.TrajectoryUtil;

public class Trajectories {
        public static final Trajectory DO_NOT_MOVE = TrajectoryGenerator.generateTrajectory(
                        new Pose2d(0, 0, new Rotation2d(0)),
                        List.of(
                                        new Translation2d(0.01, 0)),
                        new Pose2d(0.02, 0, new Rotation2d(0)),
                        Constants.AutoConstants.AUTO_TRAJECTORY_CONFIG);

        public static final Trajectory TEST_TRAJECTORY = TrajectoryGenerator.generateTrajectory(
                        new Pose2d(0, 0, new Rotation2d(0)),
                        List.of(
                                        new Translation2d(1, 0), // 1 meter forward
                                        new Translation2d(1, -1)), // 1 meter forward, 1 meter right
                        new Pose2d(2, -1, Rotation2d.fromDegrees(180)), // 2 meters forward, 1 meter right, 180 degrees
                        Constants.AutoConstants.AUTO_TRAJECTORY_CONFIG);

        public static final Trajectory ONE_METER_STRAIGHT = TrajectoryGenerator.generateTrajectory(
                        new Pose2d(0, 0, new Rotation2d(0)),
                        List.of(),
                        new Pose2d(1, 0, Rotation2d.fromDegrees(0)),
                        Constants.AutoConstants.AUTO_TRAJECTORY_CONFIG);

        public static final Trajectory TWO_METER_STRAIGHT = TrajectoryGenerator.generateTrajectory(
                        new Pose2d(0, 0, new Rotation2d(0)),
                        List.of(),
                        new Pose2d(2, 0, Rotation2d.fromDegrees(0)),
                        Constants.AutoConstants.AUTO_TRAJECTORY_CONFIG);

        public static final Trajectory ONE_METER_CURVE_LEFT = TrajectoryGenerator.generateTrajectory(
                        new Pose2d(0, 0, new Rotation2d(0)),
                        List.of(),
                        new Pose2d(1, 1, Rotation2d.fromDegrees(90)),
                        Constants.AutoConstants.AUTO_TRAJECTORY_CONFIG);

        public static final Trajectory TWO_METER_FLIP = TrajectoryGenerator.generateTrajectory(
                        new Pose2d(0, 0, new Rotation2d(0)),
                        List.of(),
                        new Pose2d(2, 0, Rotation2d.fromDegrees(180)),
                        Constants.AutoConstants.AUTO_TRAJECTORY_CONFIG);

        /**
         * Reads a trajectory from the file system. The path must be relative to
         * src/main/deploy.
         */
        public static final Trajectory loadTrajectory(String trajectoryFile) {
                System.out.println("here 1");
                // String trajectoryJSON = "ForwardMove.wpilib.json"; //"TrajectoryName"
                Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryFile);
                System.out.println("here" + Filesystem.getDeployDirectory());
                Trajectory loadedTrajectory;
                try {
                        System.out.println("trying");
                        loadedTrajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
                        System.out.println("loaded");
                } catch (IOException e) {
                        System.out.println("ERROR");
                        e.printStackTrace();
                        System.out.println("returning");
                        // DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON,
                        // ex.getStackTrace());
                        return Trajectories.DO_NOT_MOVE;
                }
                return loadedTrajectory;
        }
}
