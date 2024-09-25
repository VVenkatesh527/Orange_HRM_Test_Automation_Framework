pipeline 
{
    agent any
    
    tools{
    	maven 'Maven'
    	java 'Java'
        }

    stages 
    {
        stage('Build') 
        {
            steps
            {
                 git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                 sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post 
            {
                success
                {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
        stage("Deploy to QA"){
            steps{
                echo("deploy to qa")
            }
        }
                
        stage('Regression Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/VVenkatesh527/Orange_HRM_Test_Automation_Framework.git'
                    sh 'mvn -D clean test'
                    
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
}
