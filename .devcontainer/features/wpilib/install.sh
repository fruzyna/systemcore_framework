#!/bin/bash

# install dependencies
apt update
apt install -y python3-pip git-lfs
pip3 install --break-system-packages requests

# download latest wpilib
python3 get-wpilib.py