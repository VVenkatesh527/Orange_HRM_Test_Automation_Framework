pipeline
{
    agent any
    tools {
		  maven 'Maven'
		  java 'Java'
		    }
    stages 
    {
		stage('Build') {
    steps {
		      echo("deployed from Dev ")
		        sh 'mvn -B -DskipTests clean package'
		          }
		            }          
       
        stage('Regression Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'SUCCESS') {
                    git 'https://github.com/VVenkatesh527/Orange_HRM_Test_Automation_Framework'
                    cd /home/digvijay/Venkatesh_WorkSapce/Orange_HRM_Test_Automation_Framework/
                    sh 'mvn clean test'
                    
                }
            }
        }
         stage('Publish Extent Report'){
            steps {
              publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false, 
                                  keepAll: true, 
                                  reportDir: 'build', 
                                  reportFiles: 'OrangeHRMTestAutomationReport.html', 
                                  reportName: 'Orange HRM Test Automation Report', 
                                  reportTitles: ''])
            }
        }
        
    }
