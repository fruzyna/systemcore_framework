# WPILib Dev Container

This included dev container provides all dependencies needed to build and simulate robot code.

## Elastic Web

An instance of Elastic Web is installed inside the container and is automatically launched on startup.
It can be accessed at [http://localhost:5803/](http://localhost:5803/).

## Simulation

This container does support simulation, however, it does not support the Sim GUI.
To interact with the simulation you can use the included instance of [Elastic Web](http://localhost:5803/) and the Real DriverStation.
To connect the simulator to Elastic, go to settings, then enter your team number.
The default IP Address Mode of `Driver Station` is correct.

Before running the simulation execute `Install tools from GradleRIO` and reload.
