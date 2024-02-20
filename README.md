Prerequisite:
* All the dependencies in the POM file should be downloaded
* Chrome driver should be downloaded https://googlechromelabs.github.io/chrome-for-testing/ -I used chrome 121 version -
* Maven path should be added to environment variable
* Java_Home should be added in environment variables 
* Make sure to add the web site password in environment variables 

Steps to run the test 
1. Go to the project path 
2. Open command line
3. run mvn clean test
4. Test report is found in target\surefire-reports

References:
* https://googlechromelabs.github.io/chrome-for-testing/
* https://bobbyhadz.com/blog/mvn-is-not-recognized-as-internal-or-external-command
* https://confluence.atlassian.com/doc/setting-the-java_home-variable-in-windows-8895.html
