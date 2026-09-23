#!/bin/bash

# install dependencies
apt update
apt install -y python3-pip
pip3 install --break-system-packages requests

# download latest elastic
python3 get-elastic.py

# extract to /opt/elastic
unzip /tmp/elastic.zip -d /opt/elastic