function() {
	
	var stream = read('classpath:karate.properties');
	var props = new java.util.Properties();
	props.load(stream);

    var env = props.get('karate.env'); // get java system property 'karate.env'
    karate.configure("ssl", true);
    
    if (!env) {
        env = 'dev'; //env can be anything: dev, qa, staging, etc.
    }
    
    var url = props.get('karate.test.url');
    var port = props.get('karate.test.port');
    var baseUrl = url + (port ? ':' + port : '');
    var config = {
	    env: env,
	    baseUrl: baseUrl,
        accessToken: 'aaa949e6-eada-42d2-a9c5-e36f14623435',
	    healthUrl: baseUrl + '/health',
        consents_url: baseUrl + '/api/v1/open-banking/consents'
    };

    karate.configure('connectTimeout', 30000);
    karate.configure('readTimeout', 60000);
    
    return config;
}