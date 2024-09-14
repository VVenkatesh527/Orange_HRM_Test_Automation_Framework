pipeline
{
    agent any

    tools{
    	maven 'maven'
        }

    stages
    {

       stage("Deploy to QA"){
            steps{
                echo("deploy to qa")
            }
        }
    stage ('Git Checkout') {
       steps {
         git branch: 'main', url: 'https://github.com/AutomationTester19/OrangeHRMTestAutomationFrameWork'
      }
    }
        stage('Regression Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/AutomationTester19/OrangeHRMTestAutomationFrameWork'
                    bat 'mvn -D clean test'
                }
            }
        }

    }

    stage('Publish Extent Report'){
            steps{
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
