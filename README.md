# TransLinkRepo

Download driver executables:
Chrome: https://sites.google.com/a/chromium.org/chromedriver/downloads
Firefox: https://github.com/mozilla/geckodriver
Add WebDriver to classpath:

Windows: 
setx /m path "%path%;C:\selenium-drivers\bin"

Linux/Macintosh:
sudo mkdir -p $HOME/selenium-drivers
export PATH=$PATH:$HOME/selenium-drivers >> ~/.profile

Install Gradle:
Gradle needs Java JDK or JRE version 8 or higher to be installed as a prerequisite.
The current latest version of Gradle can be downloaded from here: https://gradle.org/releases/

To install Gradle on Linux or Windows, follow the user guide: https://docs.gradle.org/current/userguide/installation.html

For installation on Mac via command line, you can use the below brew command:
## install gradle using brew on MAC
brew install gradle

## install gradle on linux 
sudo apt-get install gradle -y

## check the gradle installation
gradle -v 

Clone the Remote Repo to your Local host:
git clone  https://github.com/pinkcollar/TransLinkRepo.git

Compile and Build Gradle project:
Go to the project root to run the following command:
./gradlew clean build -x test

Commands to run the test files from command line:
./gradlew clean test --tests “com.saucedemo.test.TranslinkTest”

Generated report paths:
Gradle - ./build/reports/tests/test/index.html
 
Compilation, building Gradle project and text execution could be done with Idea Intellij IDE.
