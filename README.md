# 2020-roboRIO

## Intro
This code is for internal development of the 2020 Robot Code.

## Structure
The code is based on the 2020 *new* Command Subsystem format.

### Subsystems

Our subsystems are responsible for keeping track of their current motion state. You can see a complete list of the subsystems in `./src/main/java/frc/robot/subsystems`. They also have commands in `./src/main/java/frc/robot/commands`. If the subsystem is part of the **supersystem**, their commands will be in `./src/main/java/frc/robot/commands/supersystem`.

#### Supersystem Subsystems
These subsystems are very closely related to each other. It consists of the:
- Indexer
- Intake
- Shooter
- Turret

These subsystems should report any state elements that affect other subsystems to `frc.robot.GlobalManager.SupersystemManager`, which is reponsible for state orchestration that affects other subsystems. This is a replacement of poorly-organized global state management in the 2019 code (which for the most part was tracked in the `Elevator` subsystem).