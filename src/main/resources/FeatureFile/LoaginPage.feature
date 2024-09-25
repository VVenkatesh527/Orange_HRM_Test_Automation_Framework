Feature:

  Scenario Outline: Verify user logged in and validate Home page title
    Given User should navigate to orange HRM url "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
    When user enter credentials with "<username>" and "<password>" and hit on Login button
    Then User should be on Home page and Title should be "OrangeHRM"
    Examples:
             username|password
                Admin|invalid
              Invalid|admin123
              invalid|invalid
                Admin|admin123

