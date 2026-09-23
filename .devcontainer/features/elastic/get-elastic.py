# Downloads the given WPILib extension release
from requests import get
from urllib.request import urlretrieve

RELEASES_URL = 'https://github.com/Gold872/elastic_dashboard/releases'
ZIP_PATH = '/tmp/elastic.zip'

version = '2027.0.0-alpha9'
zip_url = f'{RELEASES_URL}/download/v{version}/Elastic-Web.zip'

print('Downloading:', zip_url, 'to', ZIP_PATH)
urlretrieve(zip_url, ZIP_PATH)
