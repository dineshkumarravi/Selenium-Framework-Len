# Selenium-Hybrid-Framework-Lennox

(1) First we have to copy git repository on our local system and below command from command line:

git clone https://github.com/dineshkumarravi/Selenium-Framework-Len.git
cd SeleniumHybridFramework
mvn clean compile test

(2) Ncessary Libraries and Software:

(1) Install maven 
(2) Install openjdk11
(3) Type java --version and mvn --version.
(4) Latest chromedirver and make it executable by chmod +x chromdriver 
(5) It is maven based porject so all necessary dependecnies will download it automatically once you import
    this project in any preferable IDE.

(3) run test from different area:

* Once we copy this project on local system either we can run the test cases from command line 
  or from any IDE of your preference. In Intelij IDE we can right click on textng.xml inside 
  /src/main/resources folder and run as a TestNG suites.
* We can also run from ValidateCompressorDetailsTest.java file right click on it and run as a TestNG suites.

* Report will be generated inside test-output folder. For test repoert I am using extent report.
  Extent.html file will be generated after every run.
  
* Attached Extent_FailedReport.html in framework root folder reference.
