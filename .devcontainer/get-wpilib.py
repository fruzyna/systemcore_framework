# Downloads the given WPILib extension release
from requests import get
from urllib.request import urlretrieve

RELEASES_URL = 'https://github.com/wpilibsuite/vscode-wpilib/releases'
EXTENSION_PATH = '/tmp/wpilib.vsix'

version = '2027.0.0-alpha-5'
extension_url = f'{RELEASES_URL}/download/v{version}/vscode-wpilib-{version}.vsix'

print('Downloading:', extension_url, 'to', EXTENSION_PATH)
urlretrieve(extension_url, EXTENSION_PATH)
