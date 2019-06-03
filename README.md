
# Setup manual


### Windows

1. Download a repository "AdTask".
    * In Command Prompt navigate to desired directory.
    * Download Git repository by executing a command "git clone https://github.com/arturs-m/AdTask.git".

2. Install Java (Oracle Java JDK 12) and configure PATH according to specific Java version.
	* Download latest JDK from https://www.oracle.com/technetwork/java/javase/downloads/index-jsp-138363.html#javasejdk.
	* From https://www.guru99.com/install-java.html, follow to the first four steps.
	* Follow in https://javatutorial.net/set-java-home-windows-10

3. Install Maven.
	* More information about a process: https://www.mkyong.com/maven/how-to-install-maven-in-windows/ 
	
*N.B. Make sure that only inside folder, inside an archive is extracted. In "MAVEN_HOME" PATH variable value must point to folder which contain "bin" folder!*

4. Install Google Chrome browser if not installed already.

5. Install ChromeDriver.
	* Download ChromeDriver (http://chromedriver.chromium.org/downloads) for your Chrome version (Chrome major version (e.g. "74.xxx") can be seen by entering "chrome://settings/help" in the URL bar).
	* Create new directory with path - "C:\opt\chromedriver".
	* Unzip downloaded archive to directory ("C:\opt\chromedriver").
	
*N.B. Executable file path must be "C:\opt\chromedriver\chromedriver.exe".*

6. Setup "config.properties" file.
	* In project directory "AdTask", find a file "config.properties.example".
	* Copy this file in the same directory and rename it to "config.properties".
	* IF NEEDED, configure "config.properties" for project (chromedriver custom path)
		* Open file "config.properties" in some text editor.
		* If needed, make changes and save it.

7. Launch tests.
	* Open CMD and navigate to project "AdTask" directory.
	* Execute a command "mvn clean test".



### Linux

1. Download a repository "AdTask".
    * In Terminal app navigate to desired directory.
    * Download Git repository by executing a command "git clone https://github.com/arturs-m/AdTask.git".

2. Install Java (Oracle Java JDK 12).
	* Open Terminal app in OS search bar or by shortcut (e.g. ElementaryOS shortcut is "Win key + T").
	* In Terminal execute these commands (instruction for manual Debian installation: https://www.linuxuprising.com/2019/03/how-to-install-oracle-java-12-jdk-12-in.html):<br>
		* sudo su<br>
		* echo "deb http://ppa.launchpad.net/linuxuprising/java/ubuntu bionic main" | tee /etc/apt/sources.list.d/linuxuprising-java.list<br>
		* apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys 73C3DB2A<br>
		* apt-get update<br>
		* apt-get install oracle-java12-installer<br>
		* sudo apt install oracle-java12-set-default
    
3. Install Maven.
	* In Terminal, run "apt-cache search maven".
	* Then run "sudo apt-get install maven".
	* If something went wrong execute a command "sudo apt --fix-broken install". Then repeat previous step.
	* Run "mvn -v". If all went right then this command will return Apache Maven and Java versions.

4. Install Google Chrome browser if not installed already. Follow to instructions - 
https://linuxize.com/post/how-to-install-google-chrome-web-browser-on-ubuntu-18-04/

5. Install ChromeDriver.
	* Download chromedriver from https://sites.google.com/a/chromium.org/chromedriver/downloads for your Chrome version and OS (Chrome major version (e.g. "74.xxx") can be seen by entering "chrome://settings/help" in the URL bar).
	* In Terminal switch to downloaded file directory.
	* Unzip downloaded archive by running "unzip chromedriver_linux64.zip".
	* In Terminal run these commands (https://tecadmin.net/setup-selenium-chromedriver-on-ubuntu/):<br>
		* sudo mv chromedriver /usr/bin/chromedriver<br>
		* sudo chown root:root /usr/bin/chromedriver<br>
		* sudo chmod +x /usr/bin/chromedriver


6. Setup "config.properties" file.
	* In project directory "AdTask", find a file "config.properties.example".
	* Copy this file in the same directory and rename it to "config.properties".
	* IF NEEDED, configure "config.properties" for project
		* Open file "config.properties" in some text editor.
		* If needed, make changes and save it.

*N.B. Property "chromedriver" is used for Windows (OS), so there is no need to change it.*

7. Launch tests.
	* In Terminal navigate to project directory "AdTask"
	* Execute a command "mvn clean test".
