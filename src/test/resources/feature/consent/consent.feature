Feature: Consents

Scenario: Get any consent without an AT, it should not be allowed
	Given url consents_url  + '/1'
	When method GET 
	Then status 401

Scenario: Create and authorise a consent
	Given url consents_url
	And header Authorization = 'Bearer ' + accessToken
	And request read('post_consent.json')
	When method POST
	Then status 201
	Then def consentId = response.consentId
	And assert response.length != null
	And assert response.length >= 10
	Given url consents_url + '/' + consentId
	And header Authorization = 'Bearer ' + accessToken
	When method GET
	Then status 200
	And assert response.length != null
	And assert response.length >= 10
	Given url consents_url + '/' + consentId
	And header Authorization = 'Bearer ' + accessToken
	And request read('authorise_consent.json')
	When method PUT
	Then status 200
	And assert response.length != null
	And assert response.length >= 10
	And assert response.status == 'Authorised'

Scenario: Create and reject consent
	Given url consents_url
	And header Authorization = 'Bearer ' + accessToken
	And request read('post_consent.json')
	When method POST
	Then status 201
	Then def consentId = response.consentId
	And assert response.length != null
	And assert response.length >= 10
	Given url consents_url + '/' + consentId
	And header Authorization = 'Bearer ' + accessToken
	When method GET
	Then status 200
	And assert response.length != null
	And assert response.length >= 10
	Given url consents_url + '/' + consentId
	And header Authorization = 'Bearer ' + accessToken
	And request read('reject_consent.json')
	When method PUT
	Then status 200
	And assert response.length != null
	And assert response.length >= 10
	And assert response.status == 'Rejected'

Scenario: Create and delete consent
	Given url consents_url
	And header Authorization = 'Bearer ' + accessToken
	And request read('post_consent.json')
	When method POST
	Then status 201
	Then def consentId = response.consentId
	And assert response.length != null
	And assert response.length >= 10
	Given url consents_url + '/' + consentId
	And header Authorization = 'Bearer ' + accessToken
	When method DELETE
	Then status 200
	And assert response.length != null
	Given url consents_url + '/' + consentId
	And header Authorization = 'Bearer ' + accessToken
	When method GET
	Then status 200
	And assert response.length != null
	And assert response.length >= 10
	And assert response.status == 'Revoked'