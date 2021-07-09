Feature: Delete Operation
@delmethod
Scenario: Deleting one user
Given User is with endpoint and id
When User sends the request
Then Delete that data